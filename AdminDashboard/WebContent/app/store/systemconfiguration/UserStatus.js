Ext.define('Admin.store.UserStatus', {
    extend: 'Ext.data.Store',

    alias: 'store.userstatus',

    model: 'Admin.model.UserStatus',

    proxy: {
        type: 'ajax',
       url: 'JsonData',
       api: {
            create:   'UserStatusManagementServlet?func=add',
            read: 'UserStatusManagementServlet?func=get',
            update:  'UserStatusManagementServlet?func=edit',
            destroy: 'UserStatusManagementServlet?func=delete'
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
