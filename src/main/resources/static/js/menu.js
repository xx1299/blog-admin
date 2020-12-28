$(function () {

    $("#menu_datagrid").datagrid({
        url:"../menuList",
        columns:[[
            {field:'text',title:'标题',width:1,align:'center'},
            {field:'url',title:'跳转地址',width:1,align:'center'},
            {field:'parent',title:'父标题',width:1,align:'center',formatter:function(value,row,index){
                    console.log(value)
                    console.log(row)
                    return value?value.text:'';
                }
            }
        ]],
        fit:true,
        rownumbers:true,
        singleSelect:true,
        striped:true,
        pagination:true,
        fitColumns:true,
        toolbar:'#menu_toolbar'
    });

    /*
     * 初始化新增/编辑对话框
     */
    $("#menu_dialog").dialog({
        width:300,
        height:300,
        closed:true,
        buttons:'#menu_dialog_bt'
    });

    $("#add").click(function () {
        $("#menu_form").form("clear");
        $("#menu_dialog").dialog("setTitle","添加菜单")
        $("#menu_dialog").dialog("open");
    });

    $("#edit").click(function () {
        $("#menu_form").form("clear");
        var rowdate = $("#menu_datagrid").datagrid("getSelected")
        if (!rowdate) {
            $.messager.alert("温馨提示","请选择一条菜单信息进行编辑");
            return;
        }
        if (rowdate.parent) {
            rowdate["parent.id"] = rowdate.parent.id
        }
        $("#menu_dialog").dialog("setTitle","编辑菜单")
        $("#menu_dialog").dialog("open");
        $("#menu_form").form("load",rowdate);
    });


    $("#del").click(function () {
        var rowdate = $("#menu_datagrid").datagrid("getSelected")
        if (!rowdate) {
            $.messager.alert("温馨提示","请选择一条菜单信息进行删除");
            return;
        }
        $.get("./deleteMenu?id="+rowdate.id,function (data) {
            if (data.success){
                $.messager.alert("温馨提示",data.msg);
                //    刷新表格
                $("#menu_datagrid").datagrid("reload");
                $("#parentselect").combobox("reload");
                $(window.parent.$("#tree").tree("reload"));
            } else{
                $.messager.alert("温馨提示",data.msg);
            }
        })
    });



    $("#parentselect").combobox({
        url:'./menuSelectList',
        valueField:'id',
        textField:'text',
        width:150,
        panelHeight:"auto",
        editable:false,
        onLoadSuccess:function () {/*数据加载完毕之后回调*/
            //设置placeholder
            $("#parentselect").each(function(i){
                var span = $(this).siblings("span")[i];
                var targetInput = $(span).find("input:first");
                if(targetInput){
                    $(targetInput).attr("placeholder", $(this).attr("placeholder"));
                }
            })
        }
    })

    $("#save").click(function () {
        var id = $("[name=id]").val();
        var url = null
        if (id){
            var parentid = $("[name='parent.id']").val();
            if (id==parentid){
                $.messager.alert("温馨提示","不能设置自己为父菜单");
                return;
            }
            url = './updateMenu'
        } else {
            url = './saveMenu'
        }
        $("#menu_form").form("submit",{
            url:url,
            success:function (data) {
                data = $.parseJSON(data);
                if (data.success){
                    $.messager.alert("温馨提示",data.msg);
                    console.log(data.msg)
                    //    刷新表格
                    $("#menu_dialog").dialog("close");
                    $("#menu_datagrid").datagrid("reload");
                    $("#parentselect").combobox("reload");
                    $(window.parent.$("#tree").tree("reload"));
                } else{
                    console.log(data.msg)
                    $.messager.alert("温馨提示",data.msg);
                }
            }
        })

    })

});