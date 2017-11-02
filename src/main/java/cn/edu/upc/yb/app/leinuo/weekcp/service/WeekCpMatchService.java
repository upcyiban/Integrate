package cn.edu.upc.yb.app.leinuo.weekcp.service;

import cn.edu.upc.yb.app.leinuo.weekcp.entity.WeekCpMatch;
import cn.edu.upc.yb.app.leinuo.weekcp.exception.WeekCpMatchException;
import cn.edu.upc.yb.app.leinuo.weekcp.exception.WeekCpUserException;

import java.util.List;

public interface WeekCpMatchService {

    /**
     * 根据一个match的left和right增加到实体表中
     * 需要left和right互相不重复
     * @param match
     * @return
     * @throws WeekCpUserException
     * @throws WeekCpMatchException
     */
    void addMatchByMatchMap(WeekCpMatch match) throws WeekCpUserException,WeekCpMatchException;

    /**
     * 根据一个userId获取一个cp对
     * @param userId
     * @return
     * @throws WeekCpUserException
     * @throws WeekCpMatchException
     */
    List<WeekCpMatch> getMatchByUserId(Integer userId) throws WeekCpUserException,WeekCpMatchException;
}
