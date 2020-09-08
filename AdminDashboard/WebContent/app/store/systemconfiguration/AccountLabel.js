Ext.define('Admin.store.AccountLabel', {
    extend: 'Ext.data.Store',

    alias: 'store.accountlabel',

    model: 'Admin.model.AccountLabel',

     proxy: {
            type: 'ajax',
           url: 'LabelManagementServlet',
           api: {
                create:   'LabelManagementServlet?func=add',
                read: 'LabelManagementServlet?func=get',
                update:  'LabelManagementServlet?func=edit',
                destroy: 'LabelManagementServlet?func=delete'
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
