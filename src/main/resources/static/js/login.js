$(function () {



    $("#inputEmail").focus(function(){
        $("#inputEmail").removeClass("is-invalid")
    })
    $("#inputPassword").focus(function(){
        $("#inputPassword").removeClass("is-invalid")
    })

    $("#loginButton").click(function () {
        if($("#inputEmail").val()==''){
            $("#inputEmail").addClass("is-invalid")
        }else if($("#inputPassword").val()==''){
            $("#inputPassword").addClass("is-invalid")
        }else{
            var formData = new FormData();
            formData.append("email", $("#inputEmail").val())
            formData.append("password", $("#inputPassword").val())
            $.ajax({
                url: "/login",
                type: 'POST',
                cache: false,
                data: formData,
                processData: false,
                contentType: false,
                success: function (data) {
                    if(data){
                        window.location.href="/index"
                    }else{
                        $(".error").css("display","block")
                    }
                },
                error: function () {

                }
            })
        }
    })
})