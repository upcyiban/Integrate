package cn.edu.upc.yb.app.leinuo.weekcp.service;

import cn.edu.upc.yb.app.leinuo.weekcp.entity.WeekCpUser;
import cn.edu.upc.yb.app.leinuo.weekcp.exception.WeekCpMatchException;
import cn.edu.upc.yb.app.leinuo.weekcp.exception.WeekCpUserException;

import java.util.List;

/**
 * @author leinuoleileinuo
 */
public interface WeekCpUserService {

    Integer isCp = 1;
    Integer isDeleted = 1;
    Integer notIsCp = 0;
    Integer notIsDeleted = 0;

    /**
     * 根据userId获取实体
     * @param userId userId
     * @return WeekCpUser 一个实体
     * @throws WeekCpUserException 用户异常
     */
    WeekCpUser getUserById(Integer userId) throws WeekCpUserException;


    /**
     * 增加操作
     * @param user 一个实体
     * @throws WeekCpUserException 用户异常
     */
    void addUser(WeekCpUser user) throws WeekCpUserException;

    /**
     * &#x6839;&#x636e;userId&#x66f4;&#x65b0;user&#x5b9e;&#x4f53;
     * @param user 一个实体
     * @throws WeekCpUserException 用户异常
     */
    void updateUser(WeekCpUser user) throws WeekCpUserException;

    /**
     * 将userId对应的实体的deleted字段设置为true表示已经删除
     * @param userId 一个主键
     * @throws  WeekCpUserException 用户异常
     */
    void deleteUserById(Integer userId) throws WeekCpUserException;

    /**
     * 将实体中的deleted字段设置为false
     * @param userId 一个主键
     * @throws  WeekCpUserException 用户异常
     */
    void reliefUserById(Integer userId) throws WeekCpUserException;

    /**
     * 获取所有isCp()==false&&isDeleted()==false的entity
     * @return 实体列表
     */
    List<WeekCpUser> getNotCpUserList();


    /**
     * 12
     * @param yibanId 21
     * @return 2
     * @throws WeekCpUserException 12
     */
    WeekCpUser getUserByYibanId(String yibanId) throws WeekCpUserException;
}
