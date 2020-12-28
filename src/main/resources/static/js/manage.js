$(function() {
    $("#del").click(function () {
        var rowdate = $("#attable").datagrid("getSelected")
        console.log(rowdate)
        if (!rowdate) {
            $.messager.alert("温馨提示","请选择一条博客信息进行删除");
            return;
        }
        $.get("./deleteArticle?aid="+rowdate.aid,function (data) {
            if (data.success){
                $.messager.alert("温馨提示",data.msg);
                //    刷新表格
                $("#attable").datagrid("reload");
            } else{
                $.messager.alert("温馨提示",data.msg);
            }
        })
    })

    $("#attable").datagrid({
        url: './articlePageList',
        singleSelect:true,
        columns: [
            [{
                field: 'title',
                title: '标题',
                width: 100,
                align: 'center'
            },
                {
                    field: 'releasetime',
                    title: '发布时间',
                    width: 100,
                    align: 'center'
                },
                {
                    field: 'like',
                    title: '点赞',
                    width: 100,
                    align: 'center'
                },
                {
                    field: 'view',
                    title: '浏览次数',
                    width: 100,
                    align: 'center'
                },
                {
                    field: 'programa',
                    title: '发表栏目',
                    width: 100,
                    align: 'center',
                    formatter: function(value, row, index) {
                        if(row.programa.name) {
                            return row.programa.name;
                        }
                    }
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