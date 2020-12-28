$(function() {

    var pid = $("#articlePro").attr("value")
    //装文章的盒子
    var index_programabox = document.querySelector("#index_programaBox")
    //当前分页
    var currentPage =1;
    //分页点击
    index_programaClick = function(page){
        jumpPage = parseInt(page.innerText)
        console.log(jumpPage==currentPage)
        if (jumpPage!=currentPage){
            currentPage = jumpPage
            showindex_programa(currentPage)
            currentPageUtil(currentPage)
        }
    }
    //上一页点击
    $("#index_programa .previous").click(function () {
        currentPage = currentPage-1;
        showindex_programa(currentPage)
        currentPageUtil(currentPage)
    })
    //下一页点击
    $("#index_programa .next").click(function () {
        currentPage = currentPage+1;
        showindex_programa(currentPage)
        currentPageUtil(currentPage)
    })

    var articles


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

    function showindex_programa(pagenum){
       $.get("/articlePageListByPid?pagenum="+pagenum+"&pid="+pid,function (data) {
            var html=''
           data.forEach(value=>{
               html+=`
                <div class="card" style="margin-bottom: 10px;">
                    <div class="row no-gutters">
                        <div class="col-md-4">
                          <img src="${value.img}" class="card-img view_img" style="padding: 5px;" alt="...">
                        </div>
                        <div class="col-md-8">
                           <div class="card-body p-2">
                               <a class="title-a" href="/article/${value.aid}">
                               <h5 class="card-title  mb-2 mb-md-0 mb-lg-2">${value.title}</h5></a>
                               <p class="card-text" style="color: #666666;font-size: 12px;">${value.content.replace(/<[^>]+>/g,"").slice(0,100)}...</p>
                               <a class="d-md-none detail" href="/article/${value.aid}">点击详情</a>
                                <div class="text-bottom d-md-flex" style="display: none">
                                    <p class="card-text card_programa" >
                                        <a class='pga' href="/programa/${value.programa.pid}">${value.programa.pname}</a>
                                    </p>
                                    <p class="card-text card_time">${value.releasetime}</p>
                                    <p class="card-text card_view">${value.pageview}</p>
                                    <p class="card-text card_nice" onclick="likeClick(this,${value.aid})">${value.praise}</p>
                                </div>
                           </div>
                        </div>
                    </div>
                 </div>
               `
           })
            index_programabox.innerHTML = html
           document.documentElement.scrollTop=0
        })
    }
    function currentPageUtil(currentPage){
        if(currentPage==1&&$("#index_programa .page-item").length==3){
            $("#index_programa .previous").className = "previous page-link btn disabled"
            $("#index_programa .next").className="next page-link btn disabled"
            $("#index_programa .page-item")[0].className = "page-item disabled"
            $("#index_programa .page-item")[$("#index_programa .page-item").length-1].className = "page-item disabled"
        }else if (currentPage ==  $("#index_programa .page-item").length-2){
            $("#index_programa .next").className="next page-link btn disabled"
            $("#index_programa .page-item")[$("#index_programa .page-item").length-1].className = "page-item disabled"
            $("#index_programa .previous").className = "previous page-link btn"
            $("#index_programa .page-item")[0].className = "page-item"
        }else if (currentPage==1){
            $("#index_programa .previous").className = "previous page-link btn disabled"
            $("#index_programa .page-item")[0].className = "page-item disabled"
            $("#index_programa .next").className="next page-link btn"
            $("#index_programa .page-item")[$("#index_programa .page-item").length-1].className = "page-item"
        } else{
            $("#index_programa .previous").className = "previous page-link btn"
            $("#index_programa .next").className="next page-link btn"
            $("#index_programa .page-item")[0].className = "page-item"
            $("#index_programa .page-item")[$("#index_programa .page-item").length-1].className = "page-item"
        }
        for (var n = 0 ; n < $("#index_programa .page-item").length ; n++){
            if (n==0){
            } else if(n==currentPage){
                $("#index_programa .page-item")[n].className = "page-item active"
            }else if (n==$("#index_programa .page-item").length-1) {
            }else{
                $("#index_programa .page-item")[n].className = "page-item"
            }
        }
    }

    var timer

        if ($(window).outerWidth(true)<768){
            var articleIndex = 5
            $.get("/getArticleByPid/"+pid,function (data) {
                window.onscroll = function(){
                    var allBox = document.querySelectorAll(".card")
                    var main = document.querySelector("#index_programaBox")
                    var scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
                    var clientHeight = document.documentElement.clientHeight || document.body.clientHeight;
                    console.log()
                    clearInterval(timer)
                    if(clientHeight+scrollTop>allBox[allBox.length-1].offsetTop+allBox[allBox.length-1].offsetHeight*0.75) {
                        if (typeof(data) != "undefined" && data.length>5 && data.length>articleIndex){
                            $("#loading").css('display','block')
                            timer = setTimeout(function () {
                                $("#loading").css('display', 'none')
                                for (var i = 0;i<5;i++){
                                   if (data[articleIndex+i]!=null){
                                       var div = document.createElement("div")
                                       div.className = 'card'
                                       div.style.marginBottom = '10px'
                                       div.innerHTML =
                                           `
                                    <div class="row no-gutters">
                                        <div class="col-md-4">
                                            <img src="${data[articleIndex+i].img}" class="card-img view_img" style="padding: 5px;" alt="...">
                                        </div>
                                        <div class="col-md-8">
                                            <div class="card-body p-2">
                                                <a class="title-a" href="/article/${data[articleIndex+i].aid}">
                                                    <h5 class="card-title  mb-2 mb-md-0 mb-lg-2">${data[articleIndex+i].title}</h5></a>
                                                <p class="card-text" style="color: #666666;font-size: 12px;">${data[articleIndex+i].content.replace(/<[^>]+>/g,"").slice(0,50)}...</p>
                                                <a class="d-md-none detail" href="/article/${data[articleIndex+i].aid}">点击详情</a>
                                                <div class="text-bottom d-md-flex" style="display: none">
                                                    <p class="card-text card_programa" >
                                                        <a class='pga' href="/programa/${data[articleIndex+i].programa.pid}">${data[articleIndex+i].programa.pname}</a>
                                                    </p>
                                                    <p class="card-text card_time">${data[articleIndex+i].releasetime}</p>
                                                    <p class="card-text card_view">${data[articleIndex+i].pageview}</p>
                                                    <p class="card-text card_nice" onclick="likeClick(this,${data[articleIndex+i].aid})">${data[articleIndex+i].praise}</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    `
                                       main.appendChild(div)
                                   }
                                }
                                articleIndex+=5
                            }, 1000)
                        }

                    }

                }
            })


        }









})