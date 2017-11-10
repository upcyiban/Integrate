package cn.edu.upc.yb.app.leinuo.weekcp.dao;

import cn.edu.upc.yb.app.leinuo.weekcp.entity.WeekCpUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author leinuo
 */
public interface WeekCpUserDao extends JpaRepository<WeekCpUser,Integer>{

    /**
     * 根据userId获取一个user
     * @param userId
     * @return
     */
    WeekCpUser getWeekCpUserByUserId(Integer userId);

    /**
     * 获取所有cp=0也就是没有被cp的实体（即没有搭档的实体）
     * 并且要求deleted=0表示没有被删除
     * @return userList
     * @param cp 一个整形的数字，如果数据表中该数字为0说明没有搭档
     * @param deleted 一个整型数字，如果数据表中该数字为0表示没有被删除或者没有被禁言
     */
    List<WeekCpUser> getAllByCpAndDeleted(Integer cp , Integer deleted);

    /**
     * 根据易班的登陆账号查找一个用户实体
     * @param yibanId
     * @return
     */
    WeekCpUser getWeekCpUserByYibanId(String yibanId);
}
