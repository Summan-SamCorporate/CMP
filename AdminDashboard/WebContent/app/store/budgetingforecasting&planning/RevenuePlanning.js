Ext.define('Admin.store.RevenuePlanning', {
    extend: 'Ext.data.Store',

    alias: 'store.revenueplanning',

    model: 'Admin.model.RevenuePlanning',
    proxy: {
    			       type: 'ajax',
    				        async:false,
    				       url: 'BudgetServlet',
    				       api: {
    				           read: 'BudgetServlet',
    				        },
    				        rootProperty:'budgets',
    				    reader: {
    				        type: 'json',
    				        async:false
    				    }
    			},
    			autoLoad: true

});
