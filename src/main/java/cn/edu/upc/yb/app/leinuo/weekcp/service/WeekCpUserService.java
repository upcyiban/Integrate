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
     * @param userId userId
     * @return WeekCpUser 一个实体
     * @throws WeekCpUserException 用户异常
     */
    WeekCpUser getUserById(Integer userId) throws WeekCpUserException;

    /**
     * 插入一个user,如果当前有超过100个user没有被cp(isCp()==false)
     * 那么就将所有可能匹配到的随机配置成cp并插入到match表中
     * @param user 一个实体
     * @return bool
     * @throws  WeekCpMatchException match异常
     * @throws WeekCpUserException 用户异常
     */
    boolean addUser(WeekCpUser user) throws WeekCpUserException , WeekCpMatchException;

    /**
     * &#x6839;&#x636e;userId&#x66f4;&#x65b0;user&#x5b9e;&#x4f53;
     * @param userId 一个主键
     * @param user 一个实体
     * @return bool
     * @throws WeekCpUserException 用户异常
     */
    boolean updateUserById(Integer userId , WeekCpUser user) throws WeekCpUserException;

    /**
     * 将userId对应的实体的deleted字段设置为true表示已经删除
     * @param userId 一个主键
     * @return bool
     * @throws  WeekCpUserException 用户异常
     */
    boolean deleteUserById(Integer userId) throws WeekCpUserException;

    /**
     * 将实体中的deleted字段设置为false
     * @param userId 一个主键
     * @return bool
     * @throws  WeekCpUserException 用户异常
     */
    boolean reliefUserById(Integer userId) throws WeekCpUserException;

    /**
     * 获取所有isCp()==false&&isDeleted()==false的entity
     * @return 实体列表
     * @throws  WeekCpUserException 用户异常
     * @throws  WeekCpMatchException match一场
     */
    List<WeekCpUser> getNotCpUserList() throws WeekCpUserException , WeekCpMatchException;

    /**
     * 根据一个易班账号获取一个实体
     * @param yibanId 一个不可重复字段的值
     * @return 一个实体
     * @throws WeekCpUserException 用户异常
     */
    WeekCpUser getUserByYibanId(String yibanId) throws WeekCpUserException;
}
