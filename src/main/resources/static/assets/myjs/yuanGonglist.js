$(function() {// 初始化内容
    //静态表格
    layui.use(['table','layer','laypage','form'],function(){
        var table=layui.table;
        var layer = layui.layer;
        var form = layui.form;
        var laypage = layui.laypage;
        var data;  //保存数据
        var postData={
            url:"http://localhost:18090/user/queryUserInfo",
            async:false,
            data:{
                work_dataId:"2021090620210912",
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
            elem: '#mylist'
            ,height: 'full-130' //高度最大化减去差值,也可以自己设置高度值：如 height:300
            ,count: 50 //数据总数 服务端获得
            ,limit: 10 //每页显示条数 注意：请务必确保 limit 参数（默认：10）是与你服务端限定的数据条数一致
            ,page:true //开启分页
            ,toolbar: '#toolbarDemo'//工具栏
            ,defaultToolbar:['filter', 'exports']
            ,limits:[10, 20, 30, 40, 50]//分页显示每页条目下拉选择
            ,cellMinWidth: 60//定义全局最小单元格宽度，其余自动分配宽度
            , cols: [[
                //{field: 'id', title: 'ID',width:20, fixed: 'left'}
                {type:'checkbox',fixed:'left'}
                ,{field:'userId', align:'center',width:160,title:'账号'}
                ,{field:'username',align:'center', minWidth:170,title:'姓名'}
                ,{field:'project',align:'center',minWidth:260,title:'项目'}
                ,{field:'workStartTime',align:'center',minWidth:130,title:'入场时间'}
                ,{field:'outTime',align:'center',width:130,title:'离场时间'}
                ,{field:'IdCard',align:'center',minWidth:130,title:'身份证'}
                ,{field:'edu',align:'center',minWidth:130,title:'学历'}
                ,{field:'headIcon',align:'center',minWidth:130,title:'头像',templet:headIconUrl}
                ,{field:'userInfoZip',align:'center',minWidth:130,title:'入场材料',templet:userInfo}
                ,{field:'resume',align:'center',minWidth:100,title:'简历'}
                ,{field:'option',align:'center',width:130,toolbar:'#barDemo',fixed: 'right',title:'操作'}
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

        //定义方法
        function headIconUrl (d) {
            var html = '<a href="#" class="jianl_list_img" lay-event="showImg"><img src='+encodeURIComponent(d.headIcon)+'></a>';
            return html
        };
        function userInfo (d) {
            var html = '<a href="#" class="jianl_list_img" ><img src='+d.userInfoZip+'></a>';
            return html
        };

        // 页面初始化---------------------------------
        init();
        //监听行工具事件
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
                layer.msg('修改操作1111');
            }else if(layEvent === "showImg"){
                //alert(obj.data.headIcon);
                layer.open({
                    type: 2,//层类型
                    title: "简历图片",//标题
                    closeBtn: 1, //不显示关闭按钮
                    shade: [0.3],//遮罩
                    skin: 'demo_class_color',//iframe皮肤
                    shadeClose:Boolean,//点击遮罩关闭
                    area: ['800px', '460px'],
                    // offset: 'rb', //右下角弹出
                    // time: 2000, //2秒后自动关闭
                    anim: 5,//动画
                    content: ['http://localhost:18090/images/bg_default.jpg', 'no'], //iframe的url，no代表不显示滚动条
                    //content: '<div style="text-align:center"><img src="' + $("images/jl.jpg").attr('src') + '" /></div>'
                });
            }
        });

    });
});