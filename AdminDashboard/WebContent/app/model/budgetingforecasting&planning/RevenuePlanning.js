Ext.define('Admin.model.RevenuePlanning', {
    extend: 'Admin.model.Base',
     fields: [
    		        {name:'id',type:'int'},'account','department','division', 'product','brand',
    		        { name: 'date', type: 'string' },
    		        {
    		        	name: 'month',
    	                calculate: function(data) {
    	                	var dt = new Date(data.date);
    	                	return Ext.Date.format(dt, 'M');
    	                }
    	            },{name:'value',type:'float'}
    		    ]
});
