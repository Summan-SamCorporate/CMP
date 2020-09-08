/**
 * This view is an example list of people.
 */
Ext.define('Admin.view.main.AccountVersionList', {
    extend: 'Ext.grid.Panel',
    xtype: 'accountversionlist',

    requires: [
        'Admin.store.AccountVersion'
    ],

    title: 'Accounts Version Management',

    store: {
        type: 'accountversion'
    },

    columns: [{
                text: '',
                align: 'center',
                width: 30,
                dataIndex: 'column_status',
                	menuDisabled: true

            },  {
                text: 'Version Code',
                flex: 2,
                dataIndex: 'version_code',
                editor: {
                    xtype: 'textfield',
                    allowBlank: false
                },
                filterable : true,
                filter : {
                	type : 'string'
                }
            }, {
                text: 'Version Description',
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
