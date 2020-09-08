/**
 * This view is an example list of people.
 */
Ext.define('Admin.view.main.AccountTypeList', {
    extend: 'Ext.grid.Panel',
    xtype: 'accounttypelist',

    requires: [
        'Admin.store.AccountType'
    ],

    title: 'Accounts Type Management',

    store: {
        type: 'accounttype'
    },

    columns: [
             {
                text: '',
                align: 'center',
                width: 30,
                dataIndex: 'column_status',
                	menuDisabled: true

            },  {
                text: 'Type Code',
                flex: 2,
                dataIndex: 'type_code',
                editor: {
                    xtype: 'textfield',
                    allowBlank: false
                },
                filterable : true,
                filter : {
                	type : 'string'
                }
            }, {
                text:'Type Description',
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
