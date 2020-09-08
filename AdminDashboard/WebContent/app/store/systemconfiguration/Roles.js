Ext.define('Admin.store.Roles', {
    extend: 'Ext.data.Store',

    alias: 'store.roles',

    model: 'Admin.model.Roles',

    proxy: {
        type: 'ajax',
        url: 'RolesServlet',
        api: {
            read: 'RolesServlet?func=getAll',
             },
         reader: {
                    	type: 'json',
                    	root:'data',
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
