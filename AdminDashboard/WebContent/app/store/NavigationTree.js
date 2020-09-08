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
             //   iconCls: 'x-fa fa-paper-plane',
               // rowCls: 'nav-tree-badge nav-tree-badge-hot',
              //  viewType: 'profile',
                expanded: false,
                                selectable: false,

                                children: [
                                    {
                                    text: 'General Configuration',
                                     // iconCls: 'x-fa fa-file',
                                     viewType: 'pageblank',
                                     leaf: true
                                    },{
                                        text: 'Model Management',
                                       // iconCls: 'x-fa fa-file',
                                        viewType: 'modelslist',
                                        leaf: true
                                    },

                                    {
                                        text: 'User Management',
                                      //  iconCls: 'x-fa fa-exclamation-triangle',
                                        viewType: 'userslist',
                                        leaf: true
                                    },
                                    {
                                        text: 'Roles Management',
                                        //iconCls: 'x-fa fa-times-circle',
                                        viewType: 'roleslist',
                                        leaf: true
                                    },
                                    {
                                        text: 'Other Configurations',
                                      //  iconCls: 'x-fa fa-lock',
                                        viewType: 'lockscreen',
                                        expanded: false,
                                        selectable: false,

                                                        children: [
                                                            {
                                                                text: 'User Status Master',
                                                                //iconCls: 'x-fa fa-file',
                                                                viewType: 'userstatuslist',
                                                                leaf: true
                                                            },

                                                            {
                                                                text: 'Application Status Master',
                                                               // iconCls: 'x-fa fa-exclamation-triangle',
                                                                viewType: 'applicationstatuslist',
                                                                leaf: true
                                                            },
                                                            {
                                                                text: 'Account Nature',
                                                                //iconCls: 'x-fa fa-times-circle',
                                                                viewType: 'accountnaturelist',
                                                                leaf: true
                                                            },
                                                            {
                                                                text: 'Account Type',
                                                              //  iconCls: 'x-fa fa-lock',
                                                                viewType: 'accounttypelist',
                                                                leaf: true
                                                            },

                                                            {
                                                                text: 'Account Version',
                                                              //  iconCls: 'x-fa fa-check',
                                                                //viewType: 'login',
                                                                viewType: 'accountversionlist',
                                                                leaf: true
                                                            },
                                                            {
                                                                text: 'Scenario Version',
                                                                //iconCls: 'x-fa fa-edit',
                                                               // viewType: 'register',
                                                                viewType: 'scenarioversionlist',
                                                                leaf: true
                                                            },
                                                            {
                                                                text: 'Account Label',
                                                                //iconCls: 'x-fa fa-lightbulb',
                                                               // viewType: 'passwordreset',
                                                                viewType: 'accountlabellist',
                                                                leaf: true
                                                            }
                                                        ]
                                    },

                                    {
                                        text: 'Query Analyzer',
                                       // iconCls: 'x-fa fa-check',
                                        viewType: 'login',
                                        leaf: true
                                    }
                                ]
            },
            {
                text: 'Application Configuration',
              //  iconCls: 'x-fa fa-user',
                viewType: 'profile',
                leaf: true
            },
            {
                text: 'Data Management',
               // iconCls: 'x-fa fa-search',
                viewType: 'searchresults',
                leaf: true
            },
            {
                text: 'Budgeting,Forecasting & Planning',
               // iconCls: 'x-fa fa-question',
                viewType: 'faq',
                leaf: true
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
