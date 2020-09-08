Ext.define('Admin.store.user', {
    extend: 'Ext.data.Store',

    alias: 'store.user',

    model: 'Admin.model.user',

    data: { items: [
                { id: 'SUMMAN', name: "Summan Bahadur"},
                { id: 'SUPERADMIN',     name: "worf.moghsson@enterprise.com" }
            ]},

            proxy: {
                type: 'memory',
                reader: {
                    type: 'json',
                    rootProperty: 'items'
                }
            }
});
