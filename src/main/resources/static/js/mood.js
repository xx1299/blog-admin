$(function(){
    $.ajaxSettings.async = false
	//装心情随笔的盒子
    var moodbox = document.querySelector("#moodbox")
    //装分页的盒子
    var page = document.querySelector("#page")
    //当前分页
    var moodpage;
    //侧边栏装最新文章的ul
    var ul = document.querySelector(".titleul")
    //查询显示5条最新文章
    $.get("./articleListByTime",function (data) {
        var li = '';
        data.splice(0,5).forEach(value=>{
            li+= `
                <li><a href='./article.jsp?aid=${value.aid}'>${value.title}</a></li>
                `
        })
        ul.innerHTML = li;
    })
    //查询显示第一页心情随笔内容（按时间排序）
	$.get("./moodPageList/1",function (data) {

		var html = '';
		data.forEach(value=>{
            html+=`
			 <div class="suibi col-sm-12 m-1 mb-md-2">
                <p>日期：${value.releasetime}</p>
                <p>${value.content}</p>
             </div>
			`
        })
		moodbox.innerHTML = html
    })
    //查询mood分页数据
    $.get("./findPageByMood",function(data){
        var pagenum = Math.ceil(data/10)
        var html = ''
        for (var n = 0;n<pagenum;n++){
            if (n===0){
                html+=`
                <li class="page-item active" style="display: inline-block;float: left;">
                    <span class="page-link" onclick="moodClick(this)">${n+1}</span>
                </li>
                `
            } else {
                html+=`<li class="page-item" style="display: inline-block;float: left;">
                    <span class="page-link" onclick="moodClick(this)">${n+1}</span>
                    </li>
                    `
            }
        }
        if(pagenum===0){
            $("#mood .previous").className = "previous page-link btn disabled"
            $("#mood .next").className="next page-link btn disabled"
            $("#mood .page-item")[0].className = "page-item disabled"
            $("#mood .page-item")[$("#index_programa .page-item").length-1].className = "page-item disabled"
        }else{
            $("#mood .previous").className = "previous page-link btn disabled"
            $("#mood .page-item")[0].className = "page-item disabled"
        }
        page.innerHTML = html

        moodpage = 1;
    })

    $.get("./programaList",function (data) {
        var link_ul = document.querySelector(".linkbox>ul");
        var li =
            `
            <li><a href="./index.jsp"  class="linkd">博客主页</a></li>
            <li><a href="./mood.jsp"  class="linkd">心情随笔</a></li>
            `
        for (var i = 0;i<data.length;i++){
            li+=
                `
                <li><a href="./index_programa.jsp?pid=${data[i].pid}"  class="linkd">${data[i].name}</a></li>
            `
        }
        link_ul.innerHTML = li;
    })
    //分页点击
    moodClick = function(page){
        moodpage = parseInt(page.innerText)
        showMood(moodpage)
        moodPageUtil(moodpage)

    }


    //上一页点击
    $("#mood .previous").click(function () {
        moodpage = moodpage-1;
        showMood(moodpage)
        moodPageUtil(moodpage)
    })
    //下一页点击
    $("#mood .next").click(function () {
        moodpage = moodpage+1;
        showMood(moodpage)
        moodPageUtil(moodpage)
    })

    function showMood(pagenum){
        $.get("./moodPageList/"+pagenum,function (data) {
            var html = '';
            data.forEach(value=>{
            html+=`
                 <div class="suibi col-sm-12 m-1 mb-md-2">
                    <p>日期：${value.releasetime}</p>
                    <p>${value.content}</p>
                 </div>
			`
            })
            moodbox.innerHTML = html
        })
    }
    function moodPageUtil(moodpage){
        if(moodpage==1&&$("#mood .page-item").length==3){
            $("#mood .previous").className = "previous page-link btn disabled"
            $("#mood .next").className="next page-link btn disabled"
            $("#mood .page-item")[0].className = "page-item disabled"
            $("#mood .page-item")[$("#index_programa .page-item").length-1].className = "page-item disabled"
        }else if (moodpage==1){
            $("#mood .previous").className = "previous page-link btn disabled"
            $("#mood .page-item")[0].className = "page-item disabled"
            $("#mood .next").className="next page-link btn"
            $("#mood .page-item")[$("#mood .page-item").length-1].className = "page-item"
        } else if (moodpage ==  $("#mood .page-item").length-2){
            $("#mood .next").className="next page-link btn disabled"
            $("#mood .page-item")[$("#mood .page-item").length-1].className = "page-item disabled"
            $("#mood .previous").className = "previous page-link btn"
            $("#mood .page-item")[0].className = "page-item"
        }else{
            $("#mood .previous").className = "previous page-link btn"
            $("#mood .next").className="next page-link btn"
            $("#mood .page-item")[0].className = "page-item"
            $("#mood .page-item")[$("#mood .page-item").length-1].className = "page-item"
        }
        for (var n = 0 ; n < $("#mood .page-item").length ; n++){
            if (n==0){
            } else if(n==moodpage){
                $("#mood .page-item")[n].className = "page-item active"
            }else if (n==$("#mood .page-item").length-1) {
            }else{
                $("#mood .page-item")[n].className = "page-item"
            }
        }
    }

    $.ajaxSettings.async = true
	
})
