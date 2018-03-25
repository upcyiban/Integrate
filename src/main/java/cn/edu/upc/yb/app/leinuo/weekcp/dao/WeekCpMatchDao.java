package cn.edu.upc.yb.app.leinuo.weekcp.dao;

import cn.edu.upc.yb.app.leinuo.weekcp.entity.WeekCpMatch;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author leinuo
 *
 */
public interface WeekCpMatchDao extends JpaRepository<WeekCpMatch,Integer> {

    /**
     * 根据一个cpIdLeft获取一个match实体
     * @param cpIdLeft 一个key表示搭档中的某一个人
     * @return
     */
    WeekCpMatch getWeekCpMatchByCpIdLeft(Integer cpIdLeft);

    /**
     * 根据一个cpIdRight获取一个match实体
     * @param cpIdRight 一个key表示搭档中的某一个人
     * @return WeekCpMatch
     */
    WeekCpMatch getWeekCpMatchByCpIdRight(Integer cpIdRight);

    /**
     * 根据一个match获取这个与match的等价的match
     * 并将之返回
     * @param cpIdLeft 搭档中的某一个人
     * @param cpIdRight 搭档中的某一个人
     * @return WeekCpMatch
     */
    WeekCpMatch getWeekCpMatchByCpIdLeftAndCpIdRight(Integer cpIdLeft , Integer cpIdRight);
}
