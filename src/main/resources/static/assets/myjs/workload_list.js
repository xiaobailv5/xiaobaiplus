$(function() {// 初始化内容
    //静态表格
    layui.use(['table','layer','laypage','form','element','upload','laydate'],function(){
        var table=layui.table;
        var layer = layui.layer;
        var form = layui.form;
        var laypage = layui.laypage;
        var upload = layui.upload;
        var element = layui.element;
        var laydate = layui.laydate;
        //读Cookie
        function getCookie(objName) {//获取指定名称的cookie的值
            var arrStr = document.cookie.split("; ");
            for (var i = 0; i < arrStr.length; i++) {
                var temp = arrStr[i].split("=");
                if (temp[0] == objName) return unescape(temp[1]);  //解码
            }
            return "";
        }


        // 第一个任意示例
        laydate.render({
            elem: '#date1',
            format: "yyyy-MM-dd~yyyy-MM-dd",
            btns: ['clear', 'now'],
            trigger: 'click',
            done: function(value, date, endDate){
                if(value!="" && value.length>0){
                    var today=new Date(value.substring(0,10));
                    var weekday=today.getDay();
                    var monday;
                    var sunday;
                    if (weekday==0) {
                        monday=new Date(1000*60*60*24*(weekday-6) + today.getTime());
                    }  else {
                        monday=new Date(1000*60*60*24*(1-weekday) + today.getTime());
                    }
                    if (weekday==0) {
                        sunday=today;
                    }  else {
                        sunday=new Date(1000*60*60*24*(7-weekday) + today.getTime());
                    }
                    var month = monday.getMonth()+1;
                    if(month<10)
                    {
                        month = "0"+month;
                    }
                    var day1 = monday.getDate();
                    if(day1<10)
                    {
                        day1 = "0"+day1;
                    }
                    var start = ""+monday.getFullYear()+"-"+month+"-"+day1;
                    var month2 = sunday.getMonth()+1;
                    if(month2<10)
                    {
                        month2 = "0"+month2;
                    }
                    var day2 = sunday.getDate();
                    if(day2<10)
                    {
                        day2 = "0" + day2;
                    }
                    var end = ""+sunday.getFullYear()+"-"+month2+"-"+day2;


                    $("#date1").val(start+"~"+end);

                }else{
                    $("#date1").val('');
                }

            }
        });

        $('#addWeek').click(function(){
            var data = $("#date1").val();
            if (data!=""){
                var  start = data.split("~")[0];
                var  end = data.split("~")[1];
                var dataStr = getWeek(start);
                var datevalue =dataStr+"["+start.replaceAll("-","")+"-"+end.replaceAll("-","")+"]";
                var project=$("#project").children('option:selected').val();
                var staffName = getCookie("username");

                var  boolAdd = confirm("确实要添加"+datevalue+"周期吗");
                if(!boolAdd){
                    return;
                }
                var postData={
                    url:"http://localhost:18090/workload/addPeriodById",
                    async:false,
                    data:{
                        project:project,
                        period:datevalue,
                        staffName:staffName
                    }
                };
                //发送ajax  列表查询ajax
                EasyAjax.post(postData,function(result){
                    if(result.code=="-1"){
                        alert("该周期已添加，请勿重复添加！！");
                    }else{
                        init({project:project});
                        alert(data =result.msg);
                    }
                });
            }else {
                alert("请选择需要添加的周期")
            }

        });

        function getWeek(inStr) {
            // 将字符串转为标准时间格式
            str = Date.parse(inStr);
            str = new Date(str);
            // 先计算出该日期为第几周
            let week = Math.ceil(str.getDate()/7);
            let month = str.getMonth() + 1;
            // 判断这个月前7天是周几，如果不是周一，则计入上个月
            if  (str.getDate() < 7) {
                if (str.getDay() !== 1) {
                    week = 5;
                    month = str.getMonth();
                }
            }
            var dataStr = inStr.substr(0,4)+"年"+month+"月第"+week+"周";
            return dataStr;
        };

        var data;  //保存数据
        var postData={
            url:"http://localhost:18090/workload/queryWorkDataBase",
            async:false,
            data:{
                name:"NULL",
                pageNumber:1,
                pageSize:10
            }
        };
        //发送ajax  列表查询ajax
        function send(postData){
            EasyAjax.post(postData,function(result){
                if(result.code=="0"){
                    data =result.data;
                }else{
                    alert(result.msg);
                }
            });
        }
        // 表单需要的变量
        var infoOptions = {
            elem: '#mylist',
            // width: 347,
            limits: [10],
            cellMinWidth: 60, //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            cols: [[
                //{field: 'id', title: 'ID',width:20, fixed: 'left'}
                {field: 'work_dataId', title: 'ID',width:48, type:'checkbox',sort: true, fixed: 'left'}
                ,{field: 'period', title: '上报周期',width:260,  align:'center', templet:addlink }
                ,{field: 'staffName', title: '上报人',  align:'center'}
                ,{field: 'project', title: '所属项目',  align:'center'}
                ,{field: 'workTime', title: '正常工时',  align:'center'}
                ,{field: 'staffNum', title: '上报人数',  align:'center'}
                ,{field: 'workHours', title: '总工时(人天)',  align:'center'}
                ,{field: 'remark', title: '加班工时(人天)',  align:'center'}
                ,{field: 'avgHours', title: '平均工时(人天)',  align:'center'}
                ,{field: 'coe', title: '系数',  align:'center'}
                ,{fixed: 'right',title: '操作',width:120,   align:'center',toolbar: '#barDemo'}
            ]],
            data: []
        };

        // 表格初始化 ------------------
        function init (params){
            // 列表请求
            $.extend(postData.data, params);
            send(postData);
            // 完成表格数据
            $.extend(infoOptions, {data: data});
            infoOptions.page = {
                curr: 1
            }
            // console.log("data1  "+data[0].id);
            table.render(infoOptions);

            data=null;
        }
        // 页面初始化---------------------------------
        init({project:"用户中心"});


        function importData(data,repeat){
            var default_config = {
                msg:"数据导入成功！",
                uploadUrl:"http://localhost:18090/import/importData"

            };
            // $.extend( default_config);
            var idRandom = "importData" + Math.ceil(Math.random()*10000)
            var htmlContent = '<div class="layui-upload-drag" id="'+idRandom+'">';
            htmlContent += '<i class="layui-icon"></i>';
            htmlContent += '<p>点击上传，或将文件拖拽到此处</p>';
            htmlContent += '</div>';

            layer.open({
                type: 1
                ,offset: "auto" //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                ,id: 'layer_importData' //防止重复弹出
                ,title:'导入记录'
                ,content: htmlContent
                ,maxWidth:800
                ,btn: ['下载模板']
                ,btnAlign: 'c' //按钮居中
                ,shade: 0 //不显示遮罩
                ,yes: function(){//提交
                    var iframe = $("<iframe></iframe>");
                    iframe.attr("src",default_config.downUrl);
                    iframe.css("display","none");
                    $("#"+idRandom).append(iframe);
                }
            });
            form.render();
            //拖拽上传
            upload.render({
                elem: "#"+idRandom
                ,url: default_config.uploadUrl
                ,accept: 'file'
                ,before:function () {
                    layer.load();
                    this.data={workloadId:data.work_dataId,period:data.period,staffName:getCookie("username"),repeat:repeat}
                }
                ,done: function(data){
                    if(data.code == 0){
                        init({});
                        layer.closeAll();
                        if($("#query")){
                            $("#query").click();
                        }
                        if(default_config.done){
                            default_config.done(data);
                        }else{
                            alert(default_config.msg);
                        }
                    }else{
                        layer.closeAll();
                        if($("#query")){
                            $("#query").click();
                        }
                        alert(data.msg)
                    }
                }
            });
        }






        //定义方法
        function addlink (d) {
            var html = '<div><a rel="nofollow" style="color:#1E9FFF" href="javascript:void(0);"  lay-event="showAdd">' + d.period+ '</a></div>';
            return html
        };



        table.on('tool(mylist)', function(obj){ //注：tool 是工具条事件名，mylist 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                ,layEvent = obj.event; //获得 lay-event 对应的值
            if(layEvent === 'del'){
                layer.confirm('真的删除行么', function(index){
                    obj.del(); //删除对应行（tr）的DOM结构
                    layer.close(index);
                    //向服务端发送删除指令
                });
            } else if(layEvent === 'edit'){
                layer.prompt({title: '请输出本周运营系数', formType: 0}, function(pass, index){
                    var config={
                        url:"http://localhost:18090/workload/editCoe",
                        data:{
                            "coe":pass,
                            "work_dataId":obj.data.work_dataId
                        }
                    };
                    EasyAjax.post(config,function(result){
                        if(result.code=="0"){
                            layer.msg('更新成功！');
                            layer.close(index);
                            var project = $("#project").children('option:selected').val();
                            init({project:project});
                        }else{
                            alert(result.msg);
                        }
                    });
                });
            }

            else if(layEvent ==="import"){
                if(obj.data.staffName){
                    layer.confirm('该周期已上报，是否覆盖导入', function(index){
                        layer.close(index);
                        importData(obj.data,"1");
                        //向服务端发送删除指令
                    });
                }else {
                    importData(obj.data,"0");
                }

            } else if(layEvent ==='showAdd'){
                var work_dataId=  obj.data.work_dataId;
                var project=  obj.data.project;
                var index =   layer.open({
                    type: 2,
                    title: '工作量详情',
                    skin: 'demo_class_color',//iframe皮肤
                    shadeClose: true,
                    shade: false,
                    area: ['100%', '100%'],
                    //area: ['380px', '90%'],
                    content: ('/myHtml/workload_staff_list.html?work_dataId='+work_dataId+''+'&project='+encodeURI(encodeURI(project)))
                });
                layer.full(index);
            }
        });

        $("#project").change(function () {
            //alert($(this).children('option:selected').val());
            var project=$(this).children('option:selected').val();//这就是selected的值
            if (project=="全部"){
                project="";
            }
            init({project:project})
        });

    });
});