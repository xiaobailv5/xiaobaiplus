package com.lv.xiaobaiplus.controller;

import com.lv.xiaobaiplus.bean.base.Result;
import com.lv.xiaobaiplus.service.UserInfoService;
import com.lv.xiaobaiplus.service.WorkloadService;
import com.lv.xiaobaiplus.util.AuditUtils;
import com.lv.xiaobaiplus.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/workload")
public class WorkloadController {
    @Autowired
    WorkloadService workloadService;
    @Autowired
    UserInfoService userInfoService;

    @RequestMapping("/queryWorkload")
    public Result queryWorkload(@RequestParam Map<String,String> param){
        System.out.println("queryWorkload"+param.toString());
        Result result = new Result();
        result.setCode(0);
        result.setMessage("success");
        result.setData(workloadService.queryWorkload(param));
        return result;

    }
    @RequestMapping("/queryWorkloadById")
    public Result WorkloadServiceImpl(@RequestParam Map<String,String> param){
        System.out.println("queryWorkload"+param.toString());
        Result result = new Result();
        result.setCode(0);
        result.setMessage("success");
        result.setData(workloadService.queryWorkloadById(param));

        return result;

    }

    /**
     * 根据入参周期参数，判断是否存在该周期，不存在就进行添加
     * @param param
     * @return
     */
    @RequestMapping("/addPeriodById")
    public Result addPeriodById(@RequestParam Map<String,String> param){
        System.out.println("addPeriodById"+param.toString());
        Map<String,Object>  periodRes =  workloadService.queryperiodById(param);
        Result result = new Result();
        Map<String,String> res = new HashMap<>();
        if(CollectionUtils.isEmpty(periodRes)){
            //查询不到该周期，进行添加 insertPeriod
            param.put("work_dataId", DateUtil.getUUID());
            param.put("updataTime",DateUtil.getFormatData(DateUtil.DEFAULT_FORMAT));
            int  num  = workloadService.insertPeriod(param);
            if (num >0){
                result.setCode(0);
                result.setMessage("添加成功！");
            }else {
                result.setCode(-9999);
                result.setMessage("添加失败！");
            }

        }else {
            //-1表示该数据已存在，无需进行添加
            result.setCode(-1);
            result.setMessage("请勿重复添加");
        }




        return result;

    }

    @RequestMapping("/queryWorkDataBase")
    public Result queryWorkDataBase(@RequestParam Map<String,String> param){
        System.out.println("queryWorkDataBase"+param.toString());
        Result result = new Result();
        result.setCode(0);
        result.setMessage("success");
        result.setData(workloadService.queryWorkDataBase(param));
        return result;

    }

    @RequestMapping("/editCoe")
    public Result editCoe(@RequestParam Map<String,String> param){
        System.out.println("editCoe"+param.toString());
        Result result = new Result();
        result.setCode(0);
        result.setMessage("success");
        result.setData(workloadService.editCoe(param));
        return result;

    }





    @RequestMapping("/queryworkData")
    public Result queryworkData(@RequestParam Map<String,String> param){
        System.out.println(param.toString());
        Result result = new Result();
        result.setCode(0);
        result.setMessage("success");
        result.setData(workloadService.queryworkData(param));
        return result;

    }
    @RequestMapping("/AutoAddWorkloadByUser")
    public Result AutoAddWorkloadByUser(){
       //獲取人員信息
        List<Map<String,String>> userInfo = userInfoService.getAllUserInfo();
        System.out.println(userInfo.toString());

        //根据人员信息生成本周对于的工作量上报信息
        Result result = new Result();
        result.setCode(0);
        result.setMessage("success");
        result.setData("");
        return result;

    }


    @RequestMapping("/insertWorkload")
    public Result insertWorkload(@RequestParam Map<String,String> param){
        System.out.println("insertWorkload========"+param);
        Map<String,String> insertValue =new HashMap<>();
        List<Map<String,String>> checkWorklist = new ArrayList<>();
        List<String> listWork = new ArrayList<String>();
        Result result = new Result();
        result.setMessage("提交成功");
        //稽核正常工时
        String[] strList = param.get("workload").split("\n");
        for (String cont :strList){
            Map<String,String> checkCont = new HashMap<>();
            //1、获取原始数据去除标点符合
            String workContent =  AuditUtils.removePun(cont);
            if (!listWork.contains(workContent)){
                //2、稽核提交数据中是否有重复数据,不包含则将数据放到list中
                listWork.add(workContent);
                //组装入库数据
                //工作量id，与该周数据进行关联
                checkCont.put("workloadId",param.get("workloadId"));
                //ID
                checkCont.put("checkId",DateUtil.getUUID());
                //用户姓名
                checkCont.put("username",param.get("username"));
                //检查项
                checkCont.put("checkCont",workContent);
                //周期
                checkCont.put("period",param.get("zhouqi"));
                //备注
                checkCont.put("remark","正常工时");
                checkWorklist.add(checkCont);
            }else {
                //包含数据，则有重复数据，需要进行修改
                result.setCode(-9999);
                result.setMessage("此工作项(正常工作内容)【"+workContent+"】与本周内上报工作内容有重复");
                result.setData(workContent);
                return result;
            }
        }

        //稽核加班内容
        String[] strArray={"Mon","Tue","Wed","Thu","Fri","Sat","Sun"};
        for(int i=0;i<strArray.length;i++){
            String val =  param.get(strArray[i]);
            if (val != null ){
                //1、拆分数据，去除标点符合
               List<String>  strs=  AuditUtils.spilCheckCont(val);
                for (String str :strs){
                    Map<String,String> checkCont = new HashMap<>();
                    if (!listWork.contains(str)){
                        //2、稽核提交数据中是否有重复数据,不包含则将数据放到list中
                        listWork.add(str);
                        //组装入库数据
                        //工作量id，与该周数据进行关联
                        checkCont.put("workloadId",param.get("workloadId"));
                        //ID
                        checkCont.put("checkId",DateUtil.getUUID());
                        //用户姓名
                        checkCont.put("username",param.get("username"));
                        //检查项
                        checkCont.put("checkCont",str);
                        //周期
                        checkCont.put("period",param.get("zhouqi"));
                        checkCont.put("remark","加班工时");
                        checkWorklist.add(checkCont);
                    }else {
                        //包含数据，则有重复数据，需要进行修改
                        result.setCode(-9999);
                        result.setMessage("此工作项(加班工作内容)【"+val+"】与本周内上报工作内容有重复");
                        result.setData(val);
                        return result;
                    }
                }

            }
        }

        //3、稽核数据库中是否有重复数据
        for (String work : listWork){
            List<Map<String,String>> list = workloadService.checkWorkList(work);
            if (!CollectionUtils.isEmpty(list)){
                result.setCode(-9999);
                result.setMessage("本周此工作项【"+work+"】与历史上报内容有重复");
                result.setData(list);
                return result;
            }
        }

        //4、组装参数
        //加班参数组装
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<strArray.length;i++){
            if (param.get(strArray[i]+"-b") !=null){
                sb.append(param.get(strArray[i]+"-b")+" "+param.get(strArray[i]));
                sb.append("\n");
            }
        }
        /*if(sb.toString() !=null && sb.toString() !=""){
            sb.append("本周加班"+param.get("overTime")+"小时，工作日请假"+param.get("leave")+"天");
        }*/
        System.out.println(sb.toString());
        //加班备注
        insertValue.put("remark",sb.toString());
        //加班时常
        insertValue.put("overTime",(Integer.parseInt(param.get("overTime"))/8)+"");
        //本周工作内容
        insertValue.put("workCont",param.get("workload"));
        //正常工时
        insertValue.put("workTime",param.get("workTime"));
        //flag
        //更新为已上传
        insertValue.put("flag","已上报");
        //上传时间
        insertValue.put("updateTime", DateUtil.getFormatData(DateUtil.DEFAULT_FORMAT));
        //主键
        insertValue.put("workloadId",param.get("workloadId"));
        //5、将稽核后无问题的工作量入库
        int num = workloadService.updateWorkload(insertValue);
        //5、将稽核项入库，作为后比对的稽核数据
        int hecklistNum = workloadService.insertChecklist(checkWorklist);
        return result;

    }

}
