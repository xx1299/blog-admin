$(function() {

    $("#commentButton").click(function () {
        var message = $("#message").val()
        var aid = $("#aid").val()
        var uid = $("#uid").val()
        if (message!=''){
            var formData = new FormData();
            formData.append("message",message )
            formData.append("aid",aid)
            formData.append("user.uid",uid)
            $.ajax({
                url: "/comment",
                type: 'POST',
                cache: false,
                data: formData,
                processData: false,
                contentType: false,
                success: function (data) {
                    if(data){
                        window.location.href="/article/"+aid;
                    }else{
                        $("#commentMsg").text("发表评论失败，请重新发表")
                        $("#commentMsg").css("display","inline")
                    }
                },
                error: function () {

                }
            })
        } else{
            $("#commentMsg").text("评论不得为空！")
           $("#commentMsg").css("display","inline")
        }

    })

    $("#message").focus(function(){
        $("#commentMsg").css("display","none")
    })

    $("#loginButton").click(function () {
        window.location.href="/login"
    })
})