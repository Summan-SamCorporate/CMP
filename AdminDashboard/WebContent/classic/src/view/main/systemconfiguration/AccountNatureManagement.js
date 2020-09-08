/**
 * This view is an example list of people.
 */
Ext.define('Admin.view.main.AccountNatureList', {
    extend: 'Ext.grid.Panel',
    xtype: 'accountnaturelist',

    requires: [
        'Admin.store.AccountNature'
    ],

    title: 'Account Nature Management',

    store: {
        type: 'accountnature'
    },

    columns: [{
                text: '',
                align: 'center',
                width: 30,
                dataIndex: 'column_status',
                	menuDisabled: true

            },  {
                text: 'Nature Code',
                flex: 2,
                dataIndex: 'nature_code',
                editor: {
                    xtype: 'textfield',
                    allowBlank: false
                },
                filterable : true,
                filter : {
                	type : 'string'
                }
            }, {
                text: 'Nature Description',
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
