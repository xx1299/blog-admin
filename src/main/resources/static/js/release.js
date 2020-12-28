$(function () {
    $('#programa').combobox({
        url:'./programaList',
        valueField:'pid',
        textField:'pname',
        width:150,
        method:"get",
        panelHeight:"auto",
        editable:false
    });
    var E = window.wangEditor
    var editor = new E('#editor')
    // var editor = $('#editor').wangEditor();
    // 或者 var editor = new E( document.getElementById('editor') )
    editor.customConfig.uploadFileName = 'img'
    editor.customConfig.uploadImgServer = 'uploadimg'
    editor.customConfig.uploadImgHooks ={

        before: function before(xhr, editor, files) {
            // 图片上传之前触发

            // 如果返回的结果是 {prevent: true, msg: 'xxxx'} 则表示用户放弃上传
            // return {
            //     prevent: true,
            //     msg: '放弃上传'
            // }
        },
        success: function success(xhr, editor, result) {
            // 图片上传并返回结果，图片插入成功之后触发
            console.log("插入图片成功　success result = " + result.errno + "  path = " + result.data[0] );
        },
        fail: function fail(xhr, editor, result) {
            console.log(" fail result = " + result.errno + "  path = " + result.data[0] );
            // 图片上传并返回结果，但图片插入错误时触发
        },
        error: function error(xhr, editor) {
            // 图片上传出错时触发
            console.log("error result = " + result.errno + "  path = " + result.data[0] );
        },
        timeout: function timeout(xhr, editor) {
            // 图片上传超时时触发
            console.log("timeout result = " + result.errno + "  path = " + result.data );
        }
    }


    editor.create()

    document.getElementById('btn1').addEventListener('click', function() {
        var res = confirm("您确定要发布这条博客吗？")
        if (res){
            // 读取 html
            console.log(editor.txt.html())
            var formData = new FormData();
            formData.append("file", $("#file")[0].files[0])
            formData.append("title", $("#title").val())
            formData.append("content", editor.txt.html())
            formData.append("programa.pid", $("#programa").combobox("getValue"))
            $.ajax({
                url: "./addArticle",
                type: 'POST',
                cache: false,
                data: formData,
                processData: false,
                contentType: false,
                success: function (data) {
                    if(data.success){
                        alert(data.msg);
                        editor.txt.html("")
                        $("#title").val("")
                        $("#programa").combobox("clear")
                        $("#img").attr("src", "/img/jiahao.jpg")
                        $("#file").val("")
                    }else{
                        alert(data.msg);
                    }
                },
                error: function () {

                }
            })

        }



    }, false)


    upload = function(input) {
        const windowURL = window.URL || window.webkitURL;
        //这个函数（window的方法）创建出路径，浏览器能通过原生接口访问本地文件的路径，其中window可以省略，参数为需要创建路径的dom元素。
        var imgUrl = windowURL.createObjectURL(input.files[0]);
        console.log(imgUrl)
        $("#img").attr("src", imgUrl)
    }
    $('#editor').change(function () {
        console.log("222")
        $('pre code').each(function(i, block) {
            hljs.highlightBlock(block);
            console.log("111")
        });
    })


})