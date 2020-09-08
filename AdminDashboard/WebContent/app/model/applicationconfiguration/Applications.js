Ext.define('Admin.model.Applications', {
    extend: 'Admin.model.Base',
    fields : [ 'app_refno', 'app_description', 'app_long_description',
			'model_code', 'app_status', 'app_owner', 'app_organization',
			'app_attribute1', 'app_attribute2', 'app_attribute3',
			'app_attribute4', 'app_attribute5', 'created_user', 'created_date',
			'updated_date', 'application_status' ]
});
