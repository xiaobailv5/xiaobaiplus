package com.lv.xiaobaiplus.dao.base;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WorkloadDao {
    List<Map<String,String>> queryWorkload(Map<String,String> param);
    List<Map<String,String>> queryWorkloadById(Map<String,String> param);
    int updateWorkload(Map<String,String> param);
    int editCoe(Map<String,String> param);
    int insertChecklist(List<Map<String,String>> listWork);
    List<Map<String,String>> checkWorkList(@Param("name") String param);
    Map<String,String> queryworkData(Map<String,String> param);
    List<Map<String,String>> queryWorkDataBase(Map<String,String> param);

    Map<String,Object>  queryperiodById(Map<String,String> param);

    int insertPeriod(Map<String, String> param);




}
