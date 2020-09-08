/**
 * This view is an example list of people.
 */
Ext.define('Admin.view.main.List', {
    extend: 'Ext.grid.Panel',
    xtype: 'mainlist',

    requires: [
        'Admin.store.Models'
    ],

    title: 'Models',

    store: {
        type: 'models'
    },

    columns: [
        { text: 'Code',  dataIndex: 'model_code' },
        { text: 'Description', dataIndex: 'model_description', flex: 1 },

    ],

    listeners: {
        select: 'onItemSelected'
    }
});
