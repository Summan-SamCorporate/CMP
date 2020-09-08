/**
 * This view is an example list of people.
 */
  var cellEditor = Ext.create('Ext.grid.plugin.CellEditing', {
         	  clicksToEdit: 2
         });
Ext.define('Admin.view.main.ModelsList', {
    extend: 'Ext.grid.Panel',
    xtype: 'modelslist',

    requires: [
        'Admin.store.Models'
    ],
    /*plugins: [{
             ptype: 'gridexporter'
         }],
     *//*plugins: {
             gridexporter: true
         },*/
    title: 'Models Management',

    store: {
        type: 'models'
    },
                selType: 'rowmodel',
                    selModel:new Ext.selection.RowModel({
                    enableKeyNav:false
                }),
                plugins: [cellEditor,'gridfilters','gridexporter'/*,{ gridexporter: true }*/],
               dockedItems: [{
                        xtype: 'toolbar',
                        items: [{
                                text: 'Add',
                                handler: function() {
                                	cellEditor.cancelEdit();
                                    var r = new model({
                                    	model_code: '',
                                    	model_description: '',
                                    	model_attribute_01:'',
                                    	model_attribute_02: '',
                                    	model_attribute_03:'',
                                    	created_user:'',
                                    	created_date:'',
                                    	updated_date:'',
                                    	column_status:'A'
                                    });
                                    modeldetails.insert(0, r);
                                    cellEditor.startEditByPosition({'row':0,'column':1})
                                }
                            }, {
                            text:'Export',
                            handler:function(){
                                  var grid = Ext.ComponentQuery.query('modelslist');
                                 grid[0].saveDocumentAs({
                                     type: 'xlsx',
                                     title: 'My export',
                                     fileName: 'myExport.xlsx'
                                 });
                            }

                            },{
                                text: 'Delete',
                                handler: function() {
                                    var sm = modeledit_gridview.getSelectionModel();
                                    var rs = sm.getSelection();
                                    if (!rs.length) {
                                      Ext.Msg.alert('Info', 'No Reocord Selected');
                                      return;
                                    }
                                    cellEditor.cancelEdit();
                                    Ext.MessageBox.confirm('Delete', 'Are you sure you want to Delete?', function(btn) {
                                        if (btn === 'yes') {
                                        	for(i=0;i<rs.length; i++){
                                        		if(rs[i].data.column_status == "B"){
                                                     Ext.Msg.alert('Info', 'Cannot Delete In Built Models');
                                                     return;
                                                }
                                           //donot delete only mark to be deleted
                                        	rs[i].set("column_status","D");
                                        }
                                     }

                                    });
                                }
                            }, {
                                disabled: true,
                                width: 50
                            }, {
                                text:'Sync',
                                handler: function() {
                                	//Validate store before sync
            						var flag = true;
            						Ext.each(modeldetails.data.items,function(d){

            							if(d.data.column_status != ''){// Validate when changed
            							if((d.data.model_code == '')){
            								flag = false;
            								error_message='Required field missing : Code';
            							}
            							else if(!d.data.model_code.match(/[A-Z0-9]/)){
            								flag=false;
            								error_message='Feild contains invalid data : Code';
            							}
            							else if (d.data.model_description== ''){
            								flag= false;
            								error_message='Required field missing : Description';
            							}

            							}
            						});
            						if(flag){
            						 modeldetails.sync({
            							success : function() {
            								Ext.MessageBox.alert('Info', 'Record Updated Successfully!');
            								 modeldetails.load({
            									 params: {
            										    start: 0,
            										    limit: itemsPerPage
            										         }
            										});
            							},
            							failure : function(batch, opts) {

            								var error_code = batch.operations[0].error;
            								var responseText;

            								if(error_code.statusText == 'Method Not Allowed'){
            									responseText = "User Name Allready Exists";
            								}
            								else if(error_code.status == 405) //Method not allowed
            								{
            									responseText = "Cannot Delete Object Referenced To Another Object";
            								}
            								else if (error_code.status == 500){
            									responseText = "Internal Server Error";
            								}
            								else if(error_code.status == 504){ //session timed out
            									responseText = "Session Expired Please Login Again"
            								}
            								 Ext.MessageBox.alert('Error', responseText);

            							}
            						});
            						}
            						else{
            							Ext.MessageBox.alert('Error', error_message);
            						}


                                }
                            }]
                    }],
                	bbar : Ext.create('Ext.toolbar.Paging', {
            			store : Ext.data.StoreManager.lookup('modeldetails'),
            			displayInfo : true,
            			displayMsg : 'Displaying topics {0} - {1} of {2}',
            			emptyMsg : "No topics to display",
            			items : [ '-', {
            				text : 'Remove Filter',
            	            handler: function() {
            	            	modeledit_gridview.filters.clearFilters(); //remove and load records
            	            }
            			} ]
            		}),
columns: [ {
                    text: '',
                    align: 'center',
                    width: 30,
                    dataIndex: 'column_status',
                    	menuDisabled: true

                }, {
                    text: 'Model',
                    flex: 2,
                    dataIndex: 'model_code',
                    editor: {
                        xtype: 'textfield',
                        allowBlank: false,
                        maskRe: /[A-Z0-9]/
                    },
                    filterable : true,
                    filter : {
                    	type : 'string'
                    }

                }, {
                    text: 'Model Description',
                    flex: 4,
                    dataIndex: 'model_description',
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
