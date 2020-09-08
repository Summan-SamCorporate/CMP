/**
 * This view is an example list of people.
 */
Ext.define('Admin.view.main.RolesList', {
    extend: 'Ext.grid.Panel',
    xtype: 'roleslist',

    requires: [
        'Admin.store.Roles'
    ],

    title: 'Roles Management',

    store: {
        type: 'roles'
    },

    columns: [{
                    text: '',
                    align: 'center',
                    width: 30,
                    dataIndex: 'column_status',
                    	menuDisabled: true

                }, {
                        text: 'Roles',
                        flex: 2,
                        dataIndex: 'role_code',
                        enableKeyEvents: true,      // needed to handle the [Enter] key and Blur event
                	    editor: {
                            xtype: 'textfield',
                            regex: /^[A-Z]*[_]?[A-Z]*$/ ,
                    	    maskRe: /[A-Z_]/,
                    	    allowBlank: false
                        },
                        filterable : true,
                        filter : {
                        	type : 'string'
                        }
                    }, {
                        text: 'Roles Description',
                        flex: 4,
                        dataIndex: 'role_description',
                        editor: {
                            xtype: 'textfield',
                            allowBlank: false
                        },
                        filterable : true,
                        filter : {
                        	type : 'string'
                        }
                    }
                ],

    listeners: {
        select: 'onItemSelected'
    }
});
