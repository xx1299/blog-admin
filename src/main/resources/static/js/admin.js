$(function () {
    $("#tabs").tabs({
        fit:true
    })
    $('#tree').tree({
        url:'./getTree',
        method:'GET',
        lines:true,
        onSelect: function(node){
            /*在添加之前, 做判断  判断这个标签是否存在 */
            var exists =   $("#tabs").tabs("exists",node.text);
            if(exists){
                /*存在,就让它选中*/
                $("#tabs").tabs("select",node.text);
            }else {
                if (node.url !=''&& node.url !=null){
                    /*如果不存在 ,添加新标签*/
                    $("#tabs").tabs("add",{
                        title:node.text,
                        /*href:node.attributes.url,*/  /*href  引入的是body当中*/
                        content:"<iframe id='iframe' th:src=@{"+node.url+"} src="+node.url+" frameborder='0' width='100%' height='100%'></iframe>",
                        closable:true
                    })
                }
            }
        }


    });
});