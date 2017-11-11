package cn.edu.upc.yb.app.leinuo.weekcp.service.impl;

import cn.edu.upc.yb.app.leinuo.weekcp.constant.WeekCpMatchConst;
import cn.edu.upc.yb.app.leinuo.weekcp.dao.WeekCpMatchDao;
import cn.edu.upc.yb.app.leinuo.weekcp.dao.WeekCpUserDao;
import cn.edu.upc.yb.app.leinuo.weekcp.entity.WeekCpMatch;
import cn.edu.upc.yb.app.leinuo.weekcp.entity.WeekCpUser;
import cn.edu.upc.yb.app.leinuo.weekcp.exception.WeekCpMatchException;
import cn.edu.upc.yb.app.leinuo.weekcp.exception.WeekCpUserException;
import cn.edu.upc.yb.app.leinuo.weekcp.service.WeekCpMatchService;
import cn.edu.upc.yb.app.leinuo.weekcp.service.WeekCpUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author UPCdevelopment
 */
@Service
public class WeekCpMatchServiceImpl implements WeekCpMatchService {

    @Autowired
    private WeekCpMatchDao matchDao;
    @Autowired
    private WeekCpUserService userService;
    @Override
    public void addMatchByMatchMap(WeekCpMatch match) throws WeekCpUserException {
        WeekCpUser userLeft = userService.getUserById(match.getCpIdLeft());
        WeekCpUser userRight = userService.getUserById(match.getCpIdRight());
        userLeft.setCp(1);
        userRight.setCp(1);
        userService.updateUser(userLeft);
        userService.updateUser(userRight);
        System.out.println(userService.getUserById(match.getCpIdLeft()).getCp());
        matchDao.save(match);
    }

    @Override
    public WeekCpMatch getMatchByUserId(Integer userId) throws WeekCpMatchException {
        WeekCpMatch matchLeft = matchDao.getWeekCpMatchByCpIdLeft(userId);
        WeekCpMatch matchRight = matchDao.getWeekCpMatchByCpIdRight(userId);
        if (matchLeft == null && matchRight == null) {
            throw new WeekCpMatchException(WeekCpMatchConst.NOT_FOUND_CP_WITH_ID);
        } else if (matchLeft != null && matchRight != null) {
            throw new WeekCpMatchException(WeekCpMatchConst.TOO_MANY_CP);
        }
        return matchLeft==null? matchRight : matchLeft;
    }
}
