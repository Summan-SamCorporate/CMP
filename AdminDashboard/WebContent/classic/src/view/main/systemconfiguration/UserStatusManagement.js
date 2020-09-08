/**
 * This view is an example list of people.
 */
Ext.define('Admin.view.main.UserStatusList', {
    extend: 'Ext.grid.Panel',
    xtype: 'userstatuslist',

    requires: [
        'Admin.store.UserStatus'
    ],

    title: 'User Status Management',

    store: {
        type: 'userstatus'
    },

    columns: [{
                text: '',
                align: 'center',
                width: 30,
                dataIndex: 'column_status',
                	menuDisabled: true

            },  {
                text: 'User Status',
                flex: 2,
                dataIndex: 'status_code',
                editor: {
                    xtype: 'textfield',
                    allowBlank: false
                },
                filterable : true,
                filter : {
                	type : 'string'
                }
            }, {
                text: 'User Status Description',
                flex: 4,
                dataIndex: 'description',
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
