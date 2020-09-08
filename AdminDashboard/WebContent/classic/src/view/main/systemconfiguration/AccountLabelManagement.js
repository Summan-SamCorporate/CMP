/**
 * This view is an example list of people.
 */
Ext.define('Admin.view.main.AccountLabelList', {
    extend: 'Ext.grid.Panel',
    xtype: 'accountlabellist',

    requires: [
        'Admin.store.AccountLabel'
    ],

    title: 'Accounts Label Management',

    store: {
        type: 'accountlabel'
    },

    columns: [{
                text: '',
                align: 'center',
                width: 30,
                dataIndex: 'column_status',
                	menuDisabled: true

            },  {
                text: 'Label Code',
                flex: 2,
                dataIndex: 'label_code',
                editor: {
                    xtype: 'textfield',
                    allowBlank: false
                },
                filterable : true,
                filter : {
                	type : 'string'
                }
            }, {
                text: 'Label Description',
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
