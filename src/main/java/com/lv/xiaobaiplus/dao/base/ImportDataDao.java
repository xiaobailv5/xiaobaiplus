package com.lv.xiaobaiplus.dao.base;

import java.util.List;
import java.util.Map;

public interface ImportDataDao {
    int delWorkloadCheckById(Map<String,String> param);
    int updateWorkBaseload(Map<String,String> param);
    int insertWorkload(List<Map<String,String>> Valuelist);
    int delWorkloadById(Map<String, String> param);
    int insertChecks(List<Map<String,String>> listWork);
    List<Map<String,String>> checkWorks(Map<String, String> param);
}
