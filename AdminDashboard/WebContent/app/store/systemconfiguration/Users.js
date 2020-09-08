Ext.define('Admin.store.Users', {
    extend: 'Ext.data.Store',

    alias: 'store.users',

    model: 'Admin.model.Users',

    proxy: {
        type: 'ajax',
        url: 'UserManagementServlet',
        api: {
            create:   'UserManagementServlet?func=add',
			read: 'UserManagementServlet?func=get',
			update:  'UserManagementServlet?func=edit',
			destroy: 'UserManagementServlet?func=delete'
		    },
		reader: {
		    type: 'json',
		    root:'data',
		    totalProperty: 'total_count'
		},
		writer: {
		    type: 'json',
		    writeAllFields: true
	    }
	    },

    autoLoad:true
    /*data: { items: [
            { model_code: 'CMP', model_description: "jeanluc.picard@enterprise.com"},
            { model_code: 'ICAAP',     model_description: "worf.moghsson@enterprise.com" },
            { model_code: 'ASJ',   model_description: "deanna.troi@enterprise.com" },
            { model_code: 'Data',     model_description: "mr.data@enterprise.com"}
        ]},

        proxy: {
            type: 'memory',
            reader: {
                type: 'json',
                rootProperty: 'items'
            }
        }*/
});
