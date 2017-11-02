package cn.edu.upc.yb.app.leinuo.weekcp.service.impl;

import cn.edu.upc.yb.app.leinuo.weekcp.dao.WeekCpMatchDao;
import cn.edu.upc.yb.app.leinuo.weekcp.dao.WeekCpUserDao;
import cn.edu.upc.yb.app.leinuo.weekcp.entity.WeekCpMatch;
import cn.edu.upc.yb.app.leinuo.weekcp.entity.WeekCpUser;
import cn.edu.upc.yb.app.leinuo.weekcp.enums.WeekCpMatchEnum;
import cn.edu.upc.yb.app.leinuo.weekcp.enums.WeekCpUserEnum;
import cn.edu.upc.yb.app.leinuo.weekcp.exception.WeekCpMatchException;
import cn.edu.upc.yb.app.leinuo.weekcp.exception.WeekCpUserException;
import cn.edu.upc.yb.app.leinuo.weekcp.service.WeekCpMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WeekCpMatchServiceImpl implements WeekCpMatchService {
    @Autowired
    private WeekCpMatchDao matchDao;
    @Autowired
    private WeekCpUserDao userDao;
    @Override
    public void addMatchByMatchMap(WeekCpMatch match) throws WeekCpUserException, WeekCpMatchException {
        List<WeekCpMatch> left = matchDao.getMatchByUser(match.getCpIdLeft());
        List<WeekCpMatch> right = matchDao.getMatchByUser(match.getCpIdRight());

        if (left.size() != 0 || right.size() != 0) {
            throw new WeekCpMatchException(WeekCpMatchEnum.USER_HAVE_MATCH.getMessage());
        }
        WeekCpUser userLeft = userDao.getUserById(match.getCpIdLeft());
        WeekCpUser userRight = userDao.getUserById(match.getCpIdRight());
        if (userLeft == null || userRight == null) {
            throw new WeekCpUserException(WeekCpUserEnum.NOT_FOUND_USER_ID.getMessage());
        }
        userLeft.setCp(1);
        userRight.setCp(1);
        userDao.updateUserById(userLeft.getUserId(),userLeft);
        userDao.updateUserById(userRight.getUserId(),userRight);
        matchDao.addMatch(match.getCpIdLeft(),match.getCpIdRight());
    }

    @Override
    public List<WeekCpMatch> getMatchByUserId(Integer userId) throws WeekCpUserException, WeekCpMatchException {
        List<WeekCpMatch> match = matchDao.getMatchByUser(userId);
        if (match.size() > 1) {
//            一个用户出现了多个cp队，
//            是一个bug，应该将错误信息返回给用户。并联系开发者修改代码
            throw new WeekCpMatchException(WeekCpMatchEnum.TOO_MANY_CP.getMessage());
        }
        return match;
    }
}
