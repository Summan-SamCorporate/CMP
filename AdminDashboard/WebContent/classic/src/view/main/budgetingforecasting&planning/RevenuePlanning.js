/**
 * This view is an example list of people.
 */
Ext.define('Admin.view.main.RevenuePlanning', {
    extend: 'Ext.pivot.Grid',
    xtype: 'revenueplanning',

    requires: [
        'Admin.store.RevenuePlanning'
    ],

    title: 'Revenue Planning',

    store: {
        type: 'revenueplanning'
    },

    autoScroll: true,
    			leftAxis : [ {
    				dataIndex : 'account',
    				direction : 'DESC',
    				header : 'Account',
    				width : 100
    				},{
    					dataIndex : 'department',
    					direction : 'DESC',
    					header : 'Department',
    					width : 100
    				},{
    					dataIndex : 'division',
    					direction : 'DESC',
    					header : 'Division',
    					width : 100
    				},{
    					dataIndex : 'product',
    					direction : 'DESC',
    					header : 'Products',
    					width : 100
    				},{
    					dataIndex : 'brand',
    					direction : 'DESC',
    					header : 'Brand',
    					width : 100
    				}],
    				topAxis : [{
    					dataIndex : 'month',
    					direction : 'ASC',
    					 sortIndex: 'month_id',
    					width:75
    					}],
    					aggregate : [{
    						dataIndex : 'value',
    						header : 'Total',
    						aggregator : 'sum',
    						width : 75
    						}],
    						height: 750



});
