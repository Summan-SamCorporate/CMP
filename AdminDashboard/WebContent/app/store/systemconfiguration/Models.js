Ext.define('Admin.store.Models', {
    extend: 'Ext.data.Store',

    alias: 'store.models',

    model: 'Admin.model.Models',

    /*proxy: {
        type: 'ajax',
        url: 'ModelManagementServlet',
        api: {
            read: 'ModelManagementServlet?func=get',
             },
         reader: {
                    	type: 'json',
                    	root:'data',
                    }
    },

    autoLoad:true*/
    data: { items: [
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
        }
});
