Ext.define('Admin.store.NavigationTree', {
    extend: 'Ext.data.TreeStore',

    storeId: 'NavigationTree',

    fields: [{
        name: 'text'
    }],

    root: {
        expanded: true,
        children: [
            {
                text: 'Dashboard',
               // iconCls: 'x-fa fa-desktop',
                rowCls: 'nav-tree-badge',
                viewType: 'admindashboard',
                routeId: 'dashboard', // routeId defaults to viewType
                leaf: true
            },
            {
                text: 'System Configuration',
                expanded: false,
                selectable: false,
                children: [
                        {
                            text: 'General Configuration',
                            viewType: 'pageblank',
                            leaf: true
                         },{
                            text: 'Model Management',
                            viewType: 'modelslist',
                            leaf: true
                            },
                            {
                            text: 'User Management',
                            viewType: 'userslist',
                            leaf: true
                            },
                            {
                            text: 'Roles Management',
                                        viewType: 'roleslist',
                                        leaf: true
                                    },
                                    {
                                        text: 'Other Configurations',
                                        viewType: 'lockscreen',
                                        expanded: false,
                                        selectable: false,

                                                        children: [
                                                            {
                                                                text: 'User Status Master',
                                                                viewType: 'userstatuslist',
                                                                leaf: true
                                                            },

                                                            {
                                                                text: 'Application Status Master',
                                                                viewType: 'applicationstatuslist',
                                                                leaf: true
                                                            },
                                                            {
                                                                text: 'Account Nature',
                                                                viewType: 'accountnaturelist',
                                                                leaf: true
                                                            },
                                                            {
                                                                text: 'Account Type',
                                                                viewType: 'accounttypelist',
                                                                leaf: true
                                                            },

                                                            {
                                                                text: 'Account Version',
                                                                viewType: 'accountversionlist',
                                                                leaf: true
                                                            },
                                                            {
                                                                text: 'Scenario Version',
                                                                viewType: 'scenarioversionlist',
                                                                leaf: true
                                                            },
                                                            {
                                                                text: 'Account Label',
                                                                viewType: 'accountlabellist',
                                                                leaf: true
                                                            }
                                                        ]
                                    },

                                    {
                                        text: 'Query Analyzer',
                                        viewType: 'login',
                                        leaf: true
                                    }
                                ]
            },
            {
                text: 'Application Configuration',
                expanded: false,
                selectable: false,
                children: [
                    {
                        text: 'Manage Application',
                        viewType: 'pageblank',
                        leaf: true
                    },
                    {
                        text: 'App Configuration',
                        viewType: 'pageblank',
                        leaf: true
                    },
                    {
                        text: 'Data Modeling',
                        viewType: 'pageblank',
                        leaf: true
                     },
                     {
                        text: 'Financial Statement Template',
                        viewType: 'pageblank',
                        leaf: true
                     },
                    ]
            },
            {
                text: 'Data Management',
               // iconCls: 'x-fa fa-search',
                viewType: 'searchresults',
                leaf: true
            },
            {
                text: 'Budgeting,Forecasting & Planning',
               expanded: false,
                               selectable: false,
                               children: [
                                   {
                                       text: 'Revenue Planning',
                                       viewType: 'revenueplanning',
                                       leaf: true
                                   },
                               ]
            },
            {
                text: 'CMP Generator',
                //iconCls: 'x-fab fa-leanpub',
                leaf:true
            },
            {
                text: 'CMP Generator',
                //iconCls: 'x-fab fa-leanpub',
                viewType:'charts',
                leaf:true
            }
        ]
    }
});
