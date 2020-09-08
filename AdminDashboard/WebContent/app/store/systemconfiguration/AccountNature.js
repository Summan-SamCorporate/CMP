Ext.define('Admin.store.AccountNature', {
    extend: 'Ext.data.Store',

    alias: 'store.accountnature',

    model: 'Admin.model.AccountNature',

     proxy: {
            type: 'ajax',
           url: 'NatureManagementServlet',
           api: {
                create:   'NatureManagementServlet?func=add',
                read: 'NatureManagementServlet?func=get',
                update:  'NatureManagementServlet?func=edit',
                destroy: 'NatureManagementServlet?func=delete'
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
