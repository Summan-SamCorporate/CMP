Ext.define('Admin.view.main.Main', {
    extend: 'Ext.container.Viewport',

    requires: [
        'Ext.button.Segmented',
        'Ext.list.Tree'
    ],

    controller: 'main',
    viewModel: 'main',

    cls: 'sencha-dash-viewport',
    itemId: 'mainView',

    layout: {
        type: 'vbox',
        align: 'stretch'
    },

    listeners: {
        render: 'onMainViewRender'
    },
   
    items: [
        {
            xtype: 'toolbar',
            cls: 'sencha-dash-dash-headerbar shadow',
            height: 64,
            itemId: 'headerBar',
            items: [
                {
                    xtype: 'component',
                    reference: 'senchaLogo',
                    cls: 'sencha-logo',
                    html: '<div class="main-logo"><img src="resources/images/logo_sam_coorporate.png"></div>',
                    width: 250
                },
                {
                    margin: '0 0 0 8',
                    ui: 'header',
                    iconCls:'x-fa fa-bars',
                    id: 'main-navigation-btn',
                    handler: 'onToggleNavigationSize'
                },
                '->',
/*                {
                    xtype: 'segmentedbutton',
                    margin: '0 16 0 0',

                    platformConfig: {
                        ie9m: {
                            hidden: true
                        }
                    },

                    items: [{
                        iconCls: 'x-fa fa-desktop',
                        pressed: true
                    }, {
                        iconCls: 'x-fa fa-tablet',
                        handler: 'onSwitchToModern',
                        tooltip: 'Switch to modern toolkit'
                    }]
                },*/
                {
                    iconCls:'x-fa fa fa-arrow-down',
                    ui: 'header',
                    href: '#download',
                    hrefTarget: '_self',
                    tooltip: 'Download'
                },
                {
                    iconCls:'x-fa  fa-user ',
                    ui: 'header',
                    href: '#user_profile',
                    hrefTarget: '_self',
                    tooltip: 'User Profile'
                },{
                	xtype:'tbtext',
                	//User name from session.
                	text:Ext.String.format(document.getElementById("userId").value)+' - '+Ext.String.format(document.getElementById("firstName").value)+' '+Ext.String.format(document.getElementById("lastName").value),
                	cls:'top-user-name'
                },
                {
                	iconCls:'x-fa fa-sync-alt',
                    ui: 'header',
                    hrefTarget: '_self',
                    tooltip: 'Refresh',
                    handler: function(){
                    	window.location.reload();
                    }
                },
                {
                    iconCls:'x-fa fa-question',
                	ui: 'header',
                    href: '#faq',
                    hrefTarget: '_self',
                    tooltip: 'Help'
                },{
                    iconCls:'x-fa fa-sign-out-alt',
                    ui: 'header',
                    tooltip: 'Sign Out',
                    handler: function(){
						logout();								
					}
                },
            ]
        },
        {
            xtype: 'maincontainerwrap',
            id: 'main-view-detail-wrap',
            reference: 'mainContainerWrap',
            flex: 1,
            items: [
                {
                    xtype: 'treelist',
                    reference: 'navigationTreeList',
                    itemId: 'navigationTreeList',
                    ui: 'nav',
                    store: 'NavigationTree',
                    width: 250,
                    expanderFirst: false,
                    expanderOnly: false,
                    listeners: {
                        selectionchange: 'onNavigationTreeSelectionChange'
                    }
                },
                {
                    xtype: 'container',
                    flex: 1,
                    reference: 'mainCardPanel',
                    cls: 'sencha-dash-right-main-container',
                    itemId: 'contentPanel',
                    layout: {
                        type: 'card',
                        anchor: '100%'
                    }
                }
            ]
        }
    ]
});
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

