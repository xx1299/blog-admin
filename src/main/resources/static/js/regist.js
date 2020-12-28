$(function(){

    $("#sendCheck").click(function(){
        var reg = "^[a-z0-9A-Z]+[- | a-z0-9A-Z . _]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$";
        var msg = ''
        var timer;
        console.log($("#inputEmail").val())
        if($("#inputEmail").val()==''){
            msg='邮箱不得为空'
        }else{
            if($("#inputEmail").val().match(reg)){
                $.get("/sendCheck?emailAddress="+$("#inputEmail").val(),function (data) {
                    if (data.success){
                        var time = 180;
                        clearInterval(timer)
                        $("#sendCheck").attr("disabled","")
                        timer=setInterval(function(){
                            if(time==0){
                                $("#sendCheck").removeAttr("disabled")
                                $("#sendCheck").text("发送验证码")
                                clearInterval(timer)
                            }else{
                                $("#sendCheck").text("剩余"+time+"秒")
                                time=time-1;
                            }
                        },1000)
                    } else{
                        $("#emailMsg").text(data.msg)
                        $("#inputEmail").addClass("is-invalid")
                    }
                })
            }else{
                msg='请输入正确的邮箱'
            }
        }
        console.log(msg)
        if(msg!=''){
            $("#inputEmail").addClass("is-invalid")
            $("#emailMsg").text(msg)
        }else{
            $("#inputEmail").removeClass("is-invalid")
        }
    })

    $("#inputUsername").focus(function(){
        $("#inputUsername").removeClass("is-invalid")
    })
    $("#inputPassword").focus(function(){
        $("#inputPassword").removeClass("is-invalid")
    })
    $("#inputCheckPassword").focus(function(){
        $("#inputCheckPassword").removeClass("is-invalid")
    })
    $("#inputEmail").focus(function(){
        $("#inputEmail").removeClass("is-invalid")
    })
    $("#inputCheck").focus(function(){
        $("#inputCheck").removeClass("is-invalid")
    })
    $("#inputCheckPassword").blur(function(){
        if($("#inputPassword").val()!=''){
            if(!($("#inputCheckPassword").val()==$("#inputPassword").val())){
                $("#inputCheckPassword").addClass("is-invalid")
                $("#checkPasswordMsg").text('两次输入密码不一致')
            }
        }
    })

    $("#registButton").click(function(){
        var reg = "^[a-z0-9A-Z]+[- | a-z0-9A-Z . _]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$";
        if($("#inputUsername").val()==''){
            $("#inputUsername").addClass("is-invalid")
        }else if($("#inputPassword").val()==''){
            $("#inputPassword").addClass("is-invalid")
        }else if($("#inputCheckPassword").val()==''){
            $("checkPasswordMsg").text('确认密码不得为空')
            $("#inputCheckPassword").addClass("is-invalid")
        }else if($("#inputEmail").val()==''){
            $("#emailMsg").text('邮箱不得为空')
            $("#inputEmail").addClass("is-invalid")
        }else if(!($("#inputEmail").val().match(reg))){
            $("#emailMsg").text('请输入正确的邮箱')
            $("#inputEmail").addClass("is-invalid")
        }else if($("#inputCheck").val()==''){
            $("#inputCheck").addClass("is-invalid")
        }else if(!($("#inputCheckPassword").val()==$("#inputPassword").val())){
            $("checkPasswordMsg").text('两次输入密码不一致')
            $("#inputCheckPassword").addClass("is-invalid")
        }else{
            var formData = new FormData();
            formData.append("uname", $("#inputUsername").val())
            formData.append("password", $("#inputPassword").val())
            formData.append("email", $("#inputEmail").val())
            formData.append("check", $("#inputCheck").val())
            $.ajax({
                url: "/regist",
                type: 'POST',
                cache: false,
                data: formData,
                processData: false,
                contentType: false,
                success: function (data) {
                    if(data.success){
                        window.location.href="/index"
                    }else{
                        $("#checkMsg").text(data.msg)
                        $("#inputCheck").addClass("is-invalid")
                    }
                },
                error: function () {

                }
            })
        }

    })

})
