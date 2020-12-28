$(function() {

    $("#add").click(function() {
        $("#dialog").dialog("setTitle","添加栏目");
        $("#myform").form("clear");
        $("#dialog").dialog("open")
    })
    $("#edit").click(function(){
        $("#dialog").dialog("setTitle","编辑栏目");
        $("#myform").form("clear");
        var rowdate = $("#pgtable").datagrid("getSelected");
        if(!rowdate){
            $.messager.alert("温馨提示","请选择一条栏目信息进行编辑");
            return;
        }
        $("#dialog").dialog("open");
        $("#myform").form("load",rowdate);
    })
    $("#del").click(function () {
        var rowdate = $("#pgtable").datagrid("getSelected")
        if (!rowdate) {
            $.messager.alert("温馨提示","请选择一条栏目信息进行删除");
            return;
        }
        $.get("./deletePrograma?pid="+rowdate.pid,function (data) {
            if (data.success){
                $.messager.alert("温馨提示",data.msg);
                //    刷新表格
                $("#pgtable").datagrid("reload");
            } else{
                $.messager.alert("温馨提示",data.msg);
            }
        })
    })

    $("#dialog").dialog({
        width: 300,
        height: 150,
        closed:true,
        buttons: [{
            text: '保存',
            iconCls: "icon-save",
            handler: function() {

                var pid = $("[name='pid").val();
                var url = null;
                if(pid){
                    url = "./updatePrograma";
                }else{
                    url = "./savePrograma";
                }


                $("#myform").form("submit",{
                    url:url,
                    success:function(date){
                        console.log(date);
                        date = $.parseJSON(date);
                        if(date.success){
                            $.messager.alert("温馨提示",date.msg);
                            $("#pgtable").datagrid("reload");
                            $("#dialog").dialog("close");
                        }else {
                            $.messager.alert("温馨提示",date.msg);
                        }
                    }
                })
            }
        }, {
            text: '关闭',
            iconCls:"icon-cancel",
            handler: function() {
                $("#dialog").dialog("close")
            }
        }]
    })


  
    $("#pgtable").datagrid({
        url: './programaPageList',
        singleSelect:true,
        columns: [
            [{
                field: 'pname',
                title: '栏目名称',
                width: 100,
                align: 'center'
            }
            ]
        ],
        fitColumns: true,
        method: "POST",
        fit: true,
        rownumbers: true,
        pagination: true,
        toolbar: '#tb'

    })

})