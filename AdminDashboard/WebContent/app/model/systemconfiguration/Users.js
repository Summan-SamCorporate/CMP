Ext.define('Admin.model.Users', {
    extend: 'Admin.model.Base',

    fields: [
       {name:'username',type:'string'},
       						       {name:'password',type:'string'},
       						       {name:'first_name',type:'string'},
       						       {name:'last_name',type:'string'},
       						       {name:'status',type:'auto'},
       						       {name:'email',type:'string'},
       						       {name:'phone',type:'string'},
       						       {name:'mobile',type:'string'},
       						       {name:'entity',type:'string'},
       						       {name:'department',type:'string'},
       						       {name:'designation',type:'string'},
       						       {name:'last_login_date',type:'string'},
       						       {name:'cod_pwd_rule',type:'string'},
       						       {name:'password_change_date',type:'string'},
       						       {name:'no_of_failure',type:'string'},
       						       {name:'flag_password_admin',type:'string'},
       						       {name:'created_date',type:'string'},
       						       {name:'created_user',type:'string'},
       						       {name:'updated_user',type:'string'},
       						       {name:'updated_date',type:'string'},
       						       {name:'role',type:'auto'},
       						       {name:'column_status',type:'string'},
       						       {name:'stylesheet_name',type:'string'}

       ]
});
