Ext.define('Admin.store.ApplicationStatus', {
    extend: 'Ext.data.Store',

    alias: 'store.applicationstatus',

    model: 'Admin.model.ApplicationStatus',

        proxy: {
            type: 'ajax',
           url: 'ApplicationStatusManagementServlet',
           api: {
                create:   'ApplicationStatusManagementServlet?func=add',
                read: 'ApplicationStatusManagementServlet?func=get',
                update:  'ApplicationStatusManagementServlet?func=edit',
                destroy: 'ApplicationStatusManagementServlet?func=delete'
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

});
