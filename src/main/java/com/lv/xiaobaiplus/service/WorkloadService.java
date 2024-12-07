package com.lv.xiaobaiplus.service;

import java.util.List;
import java.util.Map;

public interface WorkloadService {
    List<Map<String,String>> queryWorkload(Map<String,String> param);
    List<Map<String,String>> queryWorkloadById(Map<String,String> param);
    int updateWorkload(Map<String,String> param);
    int editCoe(Map<String,String> param);
    int insertChecklist(List<Map<String,String>> listWork);
    List<Map<String,String>> checkWorkList(String param);
    Map<String,Object> queryworkData(Map<String,String> param);

    Map<String,Object>  queryperiodById(Map<String,String> param);
    int insertPeriod(Map<String,String> param);

    List<Map<String,String>> queryWorkDataBase(Map<String,String> param);
}
