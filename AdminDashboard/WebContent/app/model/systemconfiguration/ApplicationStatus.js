Ext.define('Admin.model.ApplicationStatus', {
    extend: 'Admin.model.Base',

    fields: [
        'status_code', 'description','created_user','created_date','updated_date','column_status'
    ]
});
