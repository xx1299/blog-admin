$(function(){
    //装新文章的盒子
    var newpaperbox = document.querySelector("#newpaperbox")


    likeClick = function(p,aid){
		var a_arr
		if (JSON.parse(localStorage.getItem("aid"))!=null){
            a_arr=JSON.parse(localStorage.getItem("aid"))
			if (a_arr.indexOf(aid)==-1){
                $.get("/updatePraise/"+aid,function (data) {
                    p.innerText=data
                    p.style.backgroundImage = "url(/img/dianzan_active.png)";
                })
                a_arr.push(aid)
			}
		} else{
            a_arr=[aid]
            $.get("/updatePraise/"+aid,function (data) {
                p.innerText=data
				p.style.backgroundImage = "url(/img/dianzan_active.png)";
            })
		}
        localStorage.setItem("aid",JSON.stringify(a_arr));
    }
    
    $("#btn1").click(function(){
				$("#wcimg").css({
					display:"block"
				})
				$("#qqimg").css({
					display:"none"
				})
				
			})
	$("#btn2").click(function(){
				console.log("1111")
				$("#wcimg").css({
					display:"none"
				})
				$("#qqimg").css({
					display:"block"
				})
			})

})