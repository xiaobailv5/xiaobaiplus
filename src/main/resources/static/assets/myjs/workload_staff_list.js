$(function() {// 初始化内容
    function getQueryString(name) {
        //正则表达式，获取地址中的参数
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);

        if (r != null) return unescape(r[2]); return null;

    };
    //初始化赋值给
    var project= decodeURI(getQueryString("project"));
    var work_dataId = getQueryString("work_dataId");



    //静态表格
    layui.use(['table','layer','laypage','form'],function(){
        var table=layui.table;
        var layer = layui.layer;
        var form = layui.form;
        var laypage = layui.laypage;
        var data;  //保存数据
        var postData={
            url:"http://localhost:18090/workload/queryWorkload",
            async:false,
            data:{
                work_dataId:work_dataId,
                pageNumber:1,
                pageSize:40
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
            elem: '#workload_list',
            // width: 347,
            limit: 40,
            limits: [40,50],
            cellMinWidth: 60, //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            cols: [[
                //{field: 'id', title: 'ID',width:20, fixed: 'left'}
                {field: 'work_dataId', title: 'ID',width:48, type:'checkbox', fixed: 'left'}
                ,{field: 'period', title: '周期',width:260,  align:'center' }
                ,{field: 'username', title: '姓名', width:100,  align:'center'}
                ,{field: 'workCont', title: '工作量',  align:'center',templet:totextarea}
                ,{field: 'workTime', title: '正常工时', width:60,  align:'center'}
                ,{field: 'overTime', title: '加班工时', width:60,   align:'center'}
                ,{field: 'remark', title: '加班备注',  align:'center'}
            ]],
            data: []
        };

        // 表格初始化 ------------------
        function init (){
            // 列表请求
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
        init();

        //定义方法
        function totextarea (d) {
            var html = '<textarea class="layui-textarea" style="margin: 10px 0px;background-color: transparent;border: 0px;height: auto!important;padding:0px;overflow:auto">'+d.workCont+'</textarea>';
            return html
        };


    });
});