package com.lv.xiaobaiplus.dao.base;

import com.lv.xiaobaiplus.bean.base.SysLog;
import org.apache.ibatis.annotations.Param;

/**
 * @author lmh
 */
public interface SysLogMapper {
    /**
     * 根据id删除日志记录
     * @param id
     * @return int
     */
    int deleteByPrimaryKey(@Param("id") Long id);

    /**
     * 新增日志
     * @param log
     * @return
     */
    int insert(SysLog log);
}