Ext.define('Admin.store.AccountVersion', {
    extend: 'Ext.data.Store',

    alias: 'store.accountversion',

    model: 'Admin.model.AccountVersion',

    proxy: {
            type: 'ajax',
           url: 'AccountVersionManagementServlet',
           api: {
                create:   'AccountVersionManagementServlet?func=add',
                read: 'AccountVersionManagementServlet?func=get',
                update:  'AccountVersionManagementServlet?func=edit',
                destroy: 'AccountVersionManagementServlet?func=delete'
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
