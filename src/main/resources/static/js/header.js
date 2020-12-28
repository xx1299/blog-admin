$(function(){

    // var btn = document.querySelector(".navbar-toggler")
    // var heade = document.querySelector(".header-bottom")
    // var i = 0;
    // btn.onclick = function(){
    //     if(i==0){
    //         heade.style["margin-bottom"]="270px"
    //         i=1
    //     }else{
    //         heade.style["margin-bottom"]="0px"
    //         i=0
    //     }
    // }
    if ($(window).outerWidth(true)<768){
        var top = $("#header .header-bottom").offset().top
        $(window).scroll(function () {
            if ($(document).scrollTop()>top){
                $("#header .header-bottom").addClass("fixed-top")
            }else{
                $("#header .header-bottom").removeClass("fixed-top")
            }
        })

    }
    $("#search_button").click(function () {
        $("#search_form").submit()
    })
})