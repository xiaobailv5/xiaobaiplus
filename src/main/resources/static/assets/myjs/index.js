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
    //右侧鼠标悬浮事件
    var n=0;
    $(".right_box .info_box .honor .tit a").mouseover(
        function(){
            n=$(this).index();
            $(this).addClass("on");
            $(this).siblings().removeClass("on");
            $(".right_box .info_box .honor .con").hide().eq(n).fadeIn()
        }
    );
    //左侧鼠标悬浮事件
    var m=0;
    $(".right_box .info_box .battle .tit a").mouseover(
        function(){
            m=$(this).index();
            $(this).addClass("on")
            $(this).siblings().removeClass("on")
            $(".right_box .info_box .battle .con").hide().eq(m).fadeIn()
        }
    );
    $("#workload").click(function(){
        var index =   layer.open({
            type: 2,
            title: '很多时候，我们想最大化看，比如像这个页面。',
            skin: 'demo_class_color',//iframe皮肤
            shadeClose: true,
            shade: false,
            area: ['90%', '90%'],
            //area: ['380px', '90%'],
            content: ('./myHtml/workload_list.html')
        });
    });
    $("#worklist").click(function(){
        var index =   layer.open({
            type: 2,
            title: '很多时候，我们想最大化看，比如像这个页面。',
            skin: 'demo_class_color',//iframe皮肤
            shadeClose: true,
            shade: false,
            area: ['90%', '90%'],
            //area: ['380px', '90%'],
            content: ('./myHtml/addWorkCont.html')
        });
    });
    $("#yuanGonglist").click(function(){
        var index =   layer.open({
            type: 2,
            title: '很多时候，我们想最大化看，比如像这个页面。',
            skin: 'demo_class_color',//iframe皮肤
            shadeClose: true,
            shade: false,
            area: ['90%', '90%'],
            //area: ['380px', '90%'],
            content: ('./myHtml/yuanGonglist.html')
        });
    });





 /*   //绑定点击上传工作量事件
    $(document).on("click", ".addwork",function () {
        debugger;
        var workloadId = $(this).find("span").html();
        var zhouqi = $(this).find("dt").html();
        var index =   layer.open({
                type: 2,
                title: '很多时候，我们想最大化看，比如像这个页面。',
                shadeClose: true,
                shade: false,
                area: ['65%', '80%'],
                //area: ['380px', '90%'],
                content: ('/myHtml/addWorkCont.html?workloadId='+workloadId+''+'&zhouqi='+encodeURI(encodeURI(zhouqi)))
            });
            //layer.full(index);
            return false;
        });*/
/*    //创建加载数据的方法
    var inintList=function (){
        var userId=getCookie("username");
        var config={
            url:"/workload/queryWorkload",
            data:{"userId":userId}
        };
        EasyAjax.post(config,function(result){
            if(result.code=="0"){
                debugger;
                for( var i = 0; i < result.data.length; i++ ) {
                    if (result.data[i].flag=="未上报"){
                        $("#worklist-wsb").append("<li class=\"addwork\">\n" +
                            "<i><img src=\"images/weishangbao.png\"></i>\n" +
                            "<dl>\n" +
                            "<dt>"+result.data[i].period+"</dt>\n" +
                            "<dd>"+result.data[i].username+"  工时："+result.data[i].workTime+"天</dd>\n" +
                            "<span style='display:none'>"+result.data[i].workloadId+"</span>"+
                            "</dl>\n" +
                            "</li>");
                    }else {
                        $("#worklist-ysb").append("<li class=\"addwork\">\n" +
                            "<dl>\n" +
                            "<dt>"+result.data[i].period+"</dt>\n" +
                            "<dd>"+result.data[i].username+"  工时："+result.data[i].workTime+"天</dd>\n" +
                            "<span style='display:none'>"+result.data[i].workloadId+"</span>"+
                            "</dl>\n" +
                            "</li>");
                    }

                }
            }else{
                alert("添加失败");
            }
        });
    };

    //初始化调用
    inintList();*/






});
$(document).ready(function(){
    //滚动文字
    function runtxt(){
        $(".scrollnews ul").animate({marginTop:"-54px"},300,
            function(){
                $(".scrollnews ul li:last").after($(".scrollnews ul li:first"))
                $(".scrollnews ul").css("margin-top",0)
            }
        )
    }
    $(".scrollnews ul li a").mouseenter(
        function(){
            clearInterval(timer)
        }
    )
    $(".scrollnews ul li a").mouseleave(
        function(){
            timer=setInterval(runtxt,4000)
        }
    )
    timer=setInterval(runtxt,4000)
})