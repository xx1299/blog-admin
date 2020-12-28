$(function() {

    $("#add").click(function() {
        $("#dialog").dialog("setTitle","添加友情链接");
        $("#myform").form("clear");
        $("#dialog").dialog("open")
    })
    $("#edit").click(function(){
        $("#dialog").dialog("setTitle","编辑友情链接");
        $("#myform").form("clear");
        var rowdate = $("#brtable").datagrid("getSelected");
        if(!rowdate){
            $.messager.alert("温馨提示","请选择一条友情链接信息进行编辑");
            return;
        }
        $("#dialog").dialog("open");
        $("#myform").form("load",rowdate);
    })
    $("#del").click(function () {
        var rowdate = $("#brtable").datagrid("getSelected")
        if (!rowdate) {
            $.messager.alert("温馨提示","请选择一条友情链接信息进行删除");
            return;
        }
        $.get("./deleteBlogroll?bid="+rowdate.bid,function (data) {
            if (data.success){
                $.messager.alert("温馨提示",data.msg);
                //    刷新表格
                $("#brtable").datagrid("reload");
            } else{
                $.messager.alert("温馨提示",data.msg);
            }
        })
    })

    $("#dialog").dialog({
        width: 300,
        height: 200,
        closed:true,
        buttons: [{
            text: '保存',
            iconCls: "icon-save",
            handler: function() {

                var bid = $("[name='bid").val();
                var url = null;
                if(bid){
                    url = "./updateBlogroll";
                }else{
                    url = "./saveBlogroll";
                }


                $("#myform").form("submit",{
                    url:url,
                    success:function(date){
                        console.log(date);
                        date = $.parseJSON(date);
                        if(date.success){
                            $.messager.alert("温馨提示",date.msg);
                            $("#brtable").datagrid("reload");
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



    $("#brtable").datagrid({
        url: './blogrollPageList',
        singleSelect:true,
        columns: [
            [{
                field: 'bname',
                title: '友链名称',
                width: 100,
                align: 'center'
            },{
                field: 'burl',
                title: '链接地址',
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