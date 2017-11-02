package cn.edu.upc.yb.app.leinuo.weekcp.dao;

import cn.edu.upc.yb.app.leinuo.weekcp.entity.WeekCpUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author leinuo
 */
@Component
public interface WeekCpUserDao {


    /**
     * getUserList
     * @return 返回所有用户的列表
     */
    List<WeekCpUser> getUserList();

    /**
     * 根据userId获取一个user
     * @param userId
     * @return
     */
    WeekCpUser getUserById(Integer userId);
    /**
     * 插入一个用户
     * @param weekCpUser
     * @return boolean
     */
    Integer addUser(WeekCpUser weekCpUser);

    /**
     * 根据ID更新一个用户
     * @param userId = user_id
     * @param weekCpUser
     * @return boolean
     */
    Integer updateUserById(@Param("userId") Integer userId , @Param("weekCpUser") WeekCpUser weekCpUser);

    /**
     * 删除一个用户，也就是将列deleted赋值为TRUE
     * @param userId
     * @return boolean
     */
    Integer deleteUserById(@Param("userId") Integer userId);

    /**
     * 恢复对userId对应实体额度删除操作
     * 将deleted设置为false
     * @param userId
     * @return
     */
    Integer reliefUserById(@Param("userId")Integer userId);


    /**
     * 获取所有cp=0也就是没有被cp的实体
     * 并且要求deleted=0表示没有被删除
     * @return
     */
    List<WeekCpUser> getNotCpUserList();
}
