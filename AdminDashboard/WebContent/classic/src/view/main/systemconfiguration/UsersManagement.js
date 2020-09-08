/**
 * This view is an example list of people.
 */
Ext.define('Admin.view.main.UsersList', {
    extend: 'Ext.grid.Panel',
    xtype: 'userslist',

    requires: [
        'Admin.store.Users'
    ],

    title: 'Users Management',

    store: {
        type: 'users'
    },

    columns: [ {
             			            text: '',
             			            align: 'center',
             			            width: 30,
             			            dataIndex: 'column_status',
             			            	menuDisabled: true

             			        },{
             						dataIndex : 'username',
             						text : 'UserName',
             						flex : 1,
             			            filterable : true,
             			            filter : {
             			            	type : 'string'
             			            }
             					}, {
             						dataIndex : 'first_name',
             						text : 'FirstName',
             						flex : 1,
             						filterable : true,
             						filter : {
             							type : 'string'
             						}
             					}, {
             						dataIndex : 'last_name',
             						text : 'LastName',
             						flex : 1,
             						filterable : true,
             						filter : {
             							type : 'string'
             						}
             					}, {
             					dataIndex : 'role',
             						text : 'Role',
             						id : 'role',
             						flex : 1,
             						sortable: false,
             						filterable : true,
             						filter : {
             							type : 'list',
             							store: {
                                                type: 'roles'
                                            },
             							 idField: 'role_code',
             					         labelField: 'role_description'
             						},
             					 renderer: function(role){
             						 if(role!=null){
             					        return role.role_description;}
             						 }
             					}, {
             						dataIndex : 'status',
             						text : 'Status',
             						id : 'status',
             						flex : 1,
             						hidden : false,
             						filterable : true,
             						sortable: false,
             						filter : {
             							type : 'list',
             							store: {
                                                type: 'userstatus'
                                            },
             							 idField: 'status_code',
             					         labelField: 'description'
             						},
             						 renderer: function(status){
             							 if(status!=null){
             						        return status.description;}
             							 }

             					}, {
             						dataIndex : 'email',
             						text : 'Email',
             						id : 'email',
             						flex : 1,
             						filterable : true,
             						filter : {
             							type : 'string'
             						}
             					}, {
             						dataIndex : 'mobile',
             						text : 'Mobile',
             						id : 'mobile',
             						flex : 1,
             						hidden : true,
             						filterable : true,
             						filter : {
             							type : 'string'
             						},


             					}, {
             						dataIndex : 'phone',
             						text : 'Phone',
             						id : 'phone',
             						flex : 1,
             						hidden : true,
             						filterable : true,
             						filter : {
             							type : 'string'
             						}
             					}, {
             						dataIndex : 'entity',
             						text : 'Entity',
             						id : 'entity',
             						flex : 1,
             						filterable : true,
             						filter : {
             							type : 'string'
             						}
             					}, {
             						dataIndex : 'department',
             						text : 'Department',
             						id : 'department',
             						flex : 1,
             						filterable : true,
             						filter : {
             							type : 'string'
             						}
             					}, {
             						dataIndex : 'designation',
             						text : 'Designation',
             						id : 'designation',
             						flex : 1,
             						filterable : true,
             						filter : {
             							type : 'string'
             						}
             					}, {
             						dataIndex : 'passwordRule',
             						text : 'PasswordRule',
             						id : 'passwordRule',
             						flex : 1,
             						hidden : true,
             						filterable : true,
             						filter : {
             							type : 'string'
             						}
             					}, {
             						dataIndex : 'noOfFailure',
             						text : 'NoOfFailure',
             						id : 'noOfFailure',
             						flex : 1,
             						hidden : true,
             						filterable : true,
             						filter : {
             							type : 'string'
             						}
             					}, {
             						dataIndex : 'adminPassword',
             						text : 'AdminPassword',
             						id : 'adminPassword',
             						flex : 1,
             						hidden : true,
             						filterable : true,
             						filter : {
             							type : 'string'
             						}
             					} ],

    listeners: {
        select: 'onItemSelected'
    }
});
