package com.lv.xiaobaiplus.service.impl;

import com.lv.xiaobaiplus.dao.base.WorkloadDao;
import com.lv.xiaobaiplus.service.WorkloadService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("workloadService")
@Transactional()
public class WorkloadServiceImpl implements WorkloadService {
    @Resource
    WorkloadDao workloadDao;
    @Override
    public List<Map<String, String>> queryWorkload(Map<String,String> param) {
        List<Map<String, String>> result=  workloadDao.queryWorkload(param);
        System.out.println(result.toString());
        return result;
    }

    @Override
    public List<Map<String, String>> queryWorkloadById(Map<String, String> param) {

        return workloadDao.queryWorkloadById(param);
    }

    @Override
    public int updateWorkload(Map<String, String> param) {
        return workloadDao.updateWorkload(param);
    }

    @Override
    public int editCoe(Map<String, String> param) {
        return workloadDao.editCoe(param);
    }

    @Override
    public int insertChecklist(List<Map<String,String>> listWork) {
        return workloadDao.insertChecklist(listWork);
    }

    @Override
    public List<Map<String, String>> checkWorkList(String param) {
        return workloadDao.checkWorkList(param);
    }

    @Override
    public Map<String,Object> queryworkData(Map<String, String> param) {
        Map<String,String>  workData =  workloadDao.queryworkData(param);
        Map<String,Object> result = new HashMap<>();
        result.put("workTime",workData.get("workTime"));
        System.out.println("组装结果=============="+result.toString());
        return result;
    }

    @Override
    public Map<String, Object> queryperiodById(Map<String, String> param) {

        return workloadDao.queryperiodById(param);
    }

    @Override
    public int insertPeriod(Map<String, String> param) {

        return workloadDao.insertPeriod(param);
    }

    @Override
    public List<Map<String, String>> queryWorkDataBase(Map<String,String> param) {
        return workloadDao.queryWorkDataBase(param);
    }
}
