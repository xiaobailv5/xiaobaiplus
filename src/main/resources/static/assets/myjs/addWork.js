$(function() {// 初始化内容
    //上报id
    var workloadId="";
    var zhouqi= "";
    var form;
    function getQueryString(name) {
        //正则表达式，获取地址中的参数
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);

        if (r != null) return unescape(r[2]); return null;

    };
    //初始化赋值给
    zhouqi= decodeURI(getQueryString("zhouqi"));
    $("#zhouqi").html("【"+zhouqi+"】");
    workloadId = getQueryString("workloadId");


    var inintForm=function (){
        //$("#work_cont_form").append("");
        var config={
            url:"/workload/queryworkData",
            data:{"workloadId":workloadId}
        };
        EasyAjax.post(config,function(result){
            if(result.code=="0"){
                //初始化本周正常工时
                $("#workTime").val(result.data["workTime"]);
                //初始化请假天数
                $("#leave").val("0");
                //初始化加班备注信息
                var arrayObj = new Array("Mon-周一","Tue-周二","Wed-周三","Thu-周四","Fri-周五","Sat-周六","Sun-周日");
                $.each(arrayObj,function(key,val){
                    var  strArr = val.split('-');
                   // var  weekArr = result.data[strArr[0]];
                    if(strArr[0]=="Sat"|| strArr[0]=="Sun"){
                        $("#remark-list").append("<li  style=\"height: 38px; overflow:initial\">\n" +
                            "<div class=\"layui-input-inline\" style=\"width:170px;float: left;margin-right: 20px\"  class=\"left\">\n" +
                            "<select lay-filter=\"remark-select\" id=\"select\"  name=\"remark\"  lay-verify=\"\">\n" +
                            "<option value=\"\">选择加班时长</option>\n" +
                            "<option value=\""+strArr[1]+",09:00-20:00,加班8小时，加班内容："+"\">"+strArr[1]+"加班8小时</option>\n" +
                            "<option value=\""+strArr[1]+",09:00-15:00,加班8小时，加班内容："+"\">"+strArr[1]+"上午加班4小时</option>\n" +
                            "<option value=\""+strArr[1]+",15:00-20:00,加班8小时，加班内容："+"\">"+strArr[1]+"下午加班4小时</option>\n" +
                            "</select>\n" +
                            "</div>\n" +
                            "<input type=\"text\" style=\"margin-right: 20px;width: 230px;float: left;\" disabled class=\"layui-input\" name=\""+strArr[0]+"-b\"  id=\""+strArr[0]+"-b\" >\n" +
                            "<input type=\"text\" style=\"width: 60%; float: left;\" class=\"layui-input\" disabled name=\""+strArr[0]+"\" id=\""+strArr[0]+"\" >\n" +
                            "</li>");
                    }else {
                        $("#remark-list").append("<li  style=\"height: 38px; overflow:initial\">\n" +
                            "<div class=\"layui-input-inline\" style=\"width:170px;float: left;margin-right: 20px\"  class=\"left\">\n" +
                            "<select lay-filter=\"remark-select\" id=\"select\"  name=\"remark\"  lay-verify=\"\">\n" +
                            "<option value=\"\">选择加班时长</option>\n" +
                            "<option value=\""+strArr[1]+",19:00-21:00,加班2小时，加班内容："+"\">"+strArr[1]+"加班2小时</option>\n" +
                            "<option value=\""+strArr[1]+",19:00-22:00,加班3小时，加班内容："+"\">"+strArr[1]+"加班3小时</option>\n" +
                            "<option value=\""+strArr[1]+",19:00-23:00,加班4小时，加班内容："+"\">"+strArr[1]+"加班4小时</option>\n" +
                            "<option value=\""+strArr[1]+",19:00-24:00,加班5小时，加班内容："+"\">"+strArr[1]+"加班5小时</option>\n" +
                            "<option value=\""+strArr[1]+",19:00-1:00,加班6小时，加班内容："+"\">"+strArr[1]+"加班6小时</option>\n" +
                            "<option value=\""+strArr[1]+",19:00-2:00,加班7小时，加班内容："+"\">"+strArr[1]+"加班7小时</option>\n" +
                            "<option value=\""+strArr[1]+",19:00-3:00,加班8小时，加班内容："+"\">"+strArr[1]+"加班8小时</option>\n" +
                            "</select>\n" +
                            "</div>\n" +
                            "<input type=\"text\" style=\"margin-right: 20px;width: 230px;float: left;\"  disabled class=\"layui-input\" name=\""+strArr[0]+"-b\"  id=\""+strArr[0]+"-b\" >\n" +
                            "<input type=\"text\" style=\"width: 60%; float: left;\" disabled  class=\"layui-input\" name= \""+strArr[0]+"\" id=\""+strArr[0]+"\" >\n" +
                            "</li>");
                    }
                });

                $("#work_cont_form").append("<button type=\"submit\" id=\"workloadSub\" class=\"am-btn am-btn-primary\">校验&提交</button>\n");
            }else{
                alert(result.msg);
            }
        });
        return false;

    };
    inintForm();

    layui.use(['form'], function(){
        var form = layui.form;

        form.on('select(remark-select)', function(data){
            var allNum=0;
            $("#remark-list").find(".layui-this").each( function () {
                allNum+= parseInt( $(this). text ().replace(/[^0-9]/ig,""));
            });

            if(allNum>16){
                data.othis.parent().parent().find("select").siblings("div.layui-form-select").find("dd:first").click();
                alert('加班时长不能超过16小时');
            }else {
                $(data.othis.parent().parent().find("input")[1]).val(data.value);
               if($(data.othis.parent().parent().find("input")[2]).attr('disabled')=="disabled" && data.value !=""){
                   $(data.othis.parent().parent().find("input")[2]).removeAttr("disabled");
               }/*else if(data.value=="选择加班时长") {
                   data.othis.parent().parent().find("#remark-cont").attr("disabled","disabled");
               }*/
                $("#overtime").val(allNum);
            }
        });


    });
    $("#addWork").on("click",function(){
        var workload =  $("#workloadDoc").val();
        var workTime= parseInt($("#workTime").val())-parseInt($("#leave").val());
        var overTime=parseInt($("#overtime").val());
        var leave = $("#leave").val();
        var contList = {};
        var values = $('#remark-list input').serializeArray();
        debugger;
        $.each($('#remark-list input'), function() {
            if(this.name !="" && this.value !=""  ){
                contList[this.name] = this.value;
            }

        });
        contList["workload"]=workload;
        contList["workloadId"]=workloadId;
        contList["zhouqi"]=zhouqi;
        contList["workTime"]=workTime;
        contList["overTime"]=overTime;
        contList["leave"]=leave;
        var config={
            url:"/workload/insertWorkload",
            data:contList
        };
        EasyAjax.post(config,function(result){
            if(result.code=="0"){
                alert("添加成功");
            }else{
                alert(result.msg);
            }
        });

        return false;


    });

/*    $(document).on("change", ".layui-input-inline #select",function () {
        debugger;
        var opt =  $(this).find("option:selected").val();
        var allNum=0;
        $ ("#remark-list select option:selected" ). each( function () {
            if ($(this). text ()!="选择加班时长"){
                allNum+= parseInt( $(this). text ().replace(/[^0-9]/ig,""));
            }
        });
        if(allNum>16){
            $(this).find("option:first").prop("selected","selected");
            alert('加班时长不能超过16小时');
        }else {
            $(this).parent().parent().find("#remark-time input").val(opt);
            $("#overtime").val(allNum);
        }
    });*/
});