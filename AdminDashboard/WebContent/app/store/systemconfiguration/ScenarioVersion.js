Ext.define('Admin.store.ScenarioVersion', {
    extend: 'Ext.data.Store',

    alias: 'store.scenarioversion',

    model: 'Admin.model.ScenarioVersion',

     proxy: {
         type: 'ajax',
        url: 'ScenarioVersionManagementServlet',
        api: {
             create:   'ScenarioVersionManagementServlet?func=add',
             read: 'ScenarioVersionManagementServlet?func=get',
             update:  'ScenarioVersionManagementServlet?func=edit',
             destroy: 'ScenarioVersionManagementServlet?func=delete'
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
