package cn.edu.upc.yb.app.leinuo.weekcp.service;

import cn.edu.upc.yb.app.leinuo.weekcp.entity.WeekCpMatch;
import cn.edu.upc.yb.app.leinuo.weekcp.exception.WeekCpMatchException;
import cn.edu.upc.yb.app.leinuo.weekcp.exception.WeekCpUserException;

import java.util.List;

/**
 * @author UPCdevelopment
 */
public interface WeekCpMatchService {

    /**
     * 根据一个match的left和right增加到实体表中
     * 需要left和right互相不重复
     * @param match 描述一个match实体
     */
    void addMatchByMatchMap(WeekCpMatch match) throws WeekCpUserException;


    /**
     * 根据userId获取实体
     * @param userId userId
     * @return match
     * @throws WeekCpMatchException match异常
     */
    WeekCpMatch getMatchByUserId(Integer userId) throws WeekCpMatchException;
}
