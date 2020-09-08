/**
 * This view is an example list of people.
 */
Ext.define('Admin.view.main.ApplicationStatusList', {
    extend: 'Ext.grid.Panel',
    xtype: 'applicationstatuslist',

    requires: [
        'Admin.store.ApplicationStatus'
    ],

    title: 'Application Status Management',

    store: {
        type: 'applicationstatus'
    },

    columns: [ {
                text: '',
                align: 'center',
                width: 30,
                dataIndex: 'column_status',
                	menuDisabled: true
                },  {
                text: 'Application Status',
                flex: 2,
                dataIndex: 'status_code',
                editor: {
                    xtype: 'textfield',
                    allowBlank: false
                },
                filter : {
                	type : 'string',

                }
            }, {
                text: 'Application Status Description',
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
