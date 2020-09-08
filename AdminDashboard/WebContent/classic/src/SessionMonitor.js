/**
 * Session Monitor task, alerts the user that their session will expire in 5 minutes and provides the options to continue working or logout. 
 */

Ext.define('Admin.SessionMonitor', {
	  singleton: true,
	  interval: 1000 * 10,  // run every 10 seconds.
	  lastActive: null,
	  maxInactive: 1000 * 60 * 5,  // 5 minutes of inactivity allowed;
	  //maxInactive: 25,
	  remaining: 0,
	  ui: Ext.getBody(),
  
//Dialog to display expiration message.
  
  window: Ext.create('Ext.window.Window', {
    bodyPadding: 5,
    closable: false,
    closeAction: 'hide',
    modal: true,
    resizable: false,
    title: 'Session Timeout Warning!',
    width: 325,
    items: [{
      xtype: 'container',
      frame: true,
      html: "Your session will automatically expires after 5 minutes of inactivity. If your session expires, any unsaved data will be lost and you will be automatically logged out. </br></br>If you want to continue, click the 'Continue' button.</br></br>"    
    },{
      xtype: 'label',
      text: ''
    }],
    buttons: [{
      text: 'Continue',
      handler: function() {
        Ext.TaskManager.stop(Admin.SessionMonitor.countDownTask);
        Admin.SessionMonitor.window.hide();
        Admin.SessionMonitor.start();
      }
    },{
      text: 'Logout',
      action: 'logout',
      handler: function() {
        Admin.SessionMonitor.window.hide();
        logout();		
      }
    }]
  }),

 
  /**
   * Sets up a timer task to monitor for mousemove/keydown events and
   * a count-down timer task to be used by the 60 second count-down dialog.
   */
  constructor: function(config) {
    var me = this;
   
    // session monitor task
    this.sessionTask = {
      run: me.monitorUI,
      interval: me.interval,
      scope: me
    };

    // session timeout task, displays a 60 second countdown
    // message alerting user that their session is about to expire.
    this.countDownTask = {
      run: me.countDown,
      interval: 1000,
      scope: me
    };
  },
 
 
  /**
   * Simple method to register with the mousemove and keydown events.
   */
  captureActivity : function(eventObj, el, eventOptions) {
    this.lastActive = new Date();
  },


  /**
   *  Monitors the UI to determine if you've exceeded the inactivity threshold.
   */
 
  monitorUI : function() {
    var now = new Date();
    var inactive = (now - this.lastActive);
    if (inactive >= this.maxInactive) {
      this.stop();
      this.window.show();
    }
  },

  /**
   * Starts the session timer task and registers mouse/keyboard activity event monitors.
   */
  start : function() {
    this.lastActive = new Date();
    this.ui = Ext.getBody();
    this.ui.on('mousemove', this.captureActivity, this);
    this.ui.on('keydown', this.captureActivity, this);
    Ext.TaskManager.start(this.sessionTask);
  },
 
  /**
   * Stops the session timer task and unregisters the mouse/keyboard activity event monitors.
   */
  stop: function() {
    Ext.TaskManager.stop(this.sessionTask);
    this.ui.un('mousemove', this.captureActivity, this);  
    this.ui.un('keydown', this.captureActivity, this);
  },
});

// Logout Confirmation
function logout(){
	Ext.MessageBox.confirm('Logout','Are you sure you want to logout?',
			function(btn) {
		if (btn === 'yes') {
			Ext.Ajax.request({
				url : 'LogoutServlet',
				method : 'POST',
				async : false,
				success : function() {
					location.href = "login.jsp";
					}
			});
			}
		});
};
