$(function() {// 初始化内容
//写Cookie
    function addCookie(objName, objValue, objHours) {
        var str = objName + "=" + escape(objValue); //编码
        if (objHours > 0) {//为0时不设定过期时间，浏览器关闭时cookie自动消失
            var date = new Date();
            var ms = objHours * 3600 * 1000;
            date.setTime(date.getTime() + ms);
            str += "; expires=" + date.toGMTString();
        }
        document.cookie = str;
    }


    $("#loin").on("click",function(){
        var username= $("#username").val();
        var password= $("#password").val();
        var config={
            url:"login",
            data:{
                "username":username,
                "password":password
            }
        };
        EasyAjax.post(config,function(result){
            var res =JSON.parse(result);
            if(res.code=="0"){
                //登录成功，保存cokies
                addCookie("username",username,3);
                window.location.href="/index.html";
            }else{
                alert(res.msg);
            }
        });
    });


});