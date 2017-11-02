package cn.edu.upc.yb.app.leinuo.weekcp.dao;

import cn.edu.upc.yb.app.leinuo.weekcp.entity.WeekCpMatch;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author leinuo
 *
 */
@Repository
public interface WeekCpMatchDao {

    /**
     * 获取匹配的列表
     * @return matchList
     */
    List<WeekCpMatch> getMatchList();

    /**
     * 根据matchId 获取一个match实体
     * @param matchId
     * @return
     */
    WeekCpMatch getMatchById(Integer matchId);

    /**
     * 根据两个指向User实体的主键生成一个match实体
     * @param cpId0
     * @param cpId1
     * @return
     */
    Integer addMatch(@Param("cpIdLeft")Integer cpId0,@Param("cpIdRight")Integer cpId1);

    /**
     * 根据一个matchId更新实体=match
     * @param matchId
     * @param match
     * @return
     */
    Integer updateMatchById(@Param("matchId")Integer matchId,@Param("match")WeekCpMatch match);

    /**
     * 根据一个matchId将deleted设置为true表示这个match已经被删除
     * 同时更新updateTime
     * @param matchId
     * @return
     */
    Integer deleteMatchById(@Param("matchId")Integer matchId);

    /**
     * 根据一个matchId设置deleted为false表示这个match已经取消删除操作
     * 同时更新updateTime
     * @param matchId
     * @return
     */
    Integer reliefMatchById(@Param("matchId")Integer matchId);

    /**
     * 根据一个userId获取一个match
     * 如果userId在cp_id0(cpIdLeft) or cp_id1(cpIdRight)中说明这个玩家已经有了cp
     * @param userId
     * @return
     */
    List<WeekCpMatch> getMatchByUser(@Param("userId") Integer userId);

    /**
     * 根据一个match获取这个与match的等价的match
     * 如果cp_id0,与cp_id1在match表中或者交换0，1的值在match表中，那么已经有了等价的match
     * 并将之返回
     * @param match
     * @return
     */
    WeekCpMatch getMatchByMatchMap(@Param("match") WeekCpMatch match);
}
