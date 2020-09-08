Ext.define('Admin.store.Applications', {
    extend: 'Ext.data.Store',

    alias: 'store.applications',

    model: 'Admin.model.Applications',
     	proxy : {
     		type : 'ajax',
     		url : 'ApplicationManagementServlet',
     		api : {
     			read : 'ApplicationManagementServlet?func=getAll',
     		},
     		root : 'applications',
     		reader : {
     			type : 'json',
     		}

     	},

    autoLoad:true

});
