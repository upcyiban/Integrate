package cn.edu.upc.yb.app.leinuo.weekcp.service;

import cn.edu.upc.yb.app.leinuo.weekcp.entity.WeekCpMatch;
import cn.edu.upc.yb.app.leinuo.weekcp.entity.WeekCpUser;
import cn.edu.upc.yb.app.leinuo.weekcp.exception.WeekCpMatchException;
import cn.edu.upc.yb.app.leinuo.weekcp.exception.WeekCpUserException;

import java.util.List;

/**
 * @author leinuoleileinuo
 */
public interface WeekCpUserService {


    /**
     * 根据userId获取实体
     * @param userId
     * @return
     * @throws WeekCpUserException
     */
    WeekCpUser getUserById(Integer userId) throws WeekCpUserException;

    /**
     * 插入一个user,如果当前有超过100个user没有被cp(isCp()==false)
     * 那么就将所有可能匹配到的随机配置成cp并插入到match表中
     * @param user
     * @return
     * @throws  WeekCpMatchException,WeekCpUserException
     */
    boolean addUser(WeekCpUser user) throws WeekCpUserException , WeekCpMatchException;

    /**
     * 根据userId更新user实体
     * @param userId
     * @param user
     * @return
     * @throws  WeekCpUserException
     */
    boolean updateUserById(Integer userId , WeekCpUser user) throws WeekCpUserException;

    /**
     * 将userId对应的实体的deleted字段设置为true表示已经删除
     * @param userId
     * @return
     * @throws  WeekCpUserException
     */
    boolean deleteUserById(Integer userId) throws WeekCpUserException;

    /**
     * 将实体中的deleted字段设置为false
     * @param userId
     * @return
     * @throws  WeekCpUserException
     */
    boolean reliefUserById(Integer userId) throws WeekCpUserException;

    /**
     * 获取所有isCp()==false&&isDeleted()==false的entity
     * @return
     * @throws  WeekCpUserException
     * @throws  WeekCpMatchException
     */
    List<WeekCpUser> getNotCpUserList() throws WeekCpUserException , WeekCpMatchException;

}
