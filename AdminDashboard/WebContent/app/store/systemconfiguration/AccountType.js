Ext.define('Admin.store.AccountType', {
    extend: 'Ext.data.Store',

    alias: 'store.accounttype',

    model: 'Admin.model.AccountType',

     proxy: {
            type: 'ajax',
           url: 'JsonData',
           api: {
                create:   'TypeManagementServlet?func=add',
                read: 'TypeManagementServlet?func=get',
                update:  'TypeManagementServlet?func=edit',
                destroy: 'TypeManagementServlet?func=delete'
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
