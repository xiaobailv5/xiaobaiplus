$(function() {// 初始化内容
    //读Cookie
    function getCookie(objName) {//获取指定名称的cookie的值
        var arrStr = document.cookie.split("; ");
        for (var i = 0; i < arrStr.length; i++) {
            var temp = arrStr[i].split("=");
            if (temp[0] == objName) return unescape(temp[1]);  //解码
        }
        return "";
    }
    function getQueryString(name) {
        //正则表达式，获取地址中的参数
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);

        if (r != null) return unescape(r[2]); return null;

    };
    layui.use('element', function(){
        var element = layui.element;

    });

    //初始化内嵌页面
    var child_url = getQueryString("url");
    if(child_url=="" || child_url==null){
        $(".layui-body").append('<iframe src="workload_list.html" name="main_self_frame" frameborder="0" class="layadmin-iframe" scrolling="yes"></iframe>');
    }else {
        $(".layui-body").append('<iframe src=../myhtml/'+child_url+' name="main_self_frame" frameborder="0" class="layadmin-iframe" scrolling="yes"></iframe>');
    }






});

$(function(){
/*    $(".open_shrink").click(function(){
        if ($(this).hasClass("open")) {
            $(this).removeClass("open");
            $(".layui-side").stop().animate({left:-220},300);
            $(".layui-body").stop().animate({left:0},300);
        }else{
            $(this).addClass("open");
            $(".layui-side").stop().animate({left:0},300);
            $(".layui-body").stop().animate({left:220},300);
        }
    })*/
});