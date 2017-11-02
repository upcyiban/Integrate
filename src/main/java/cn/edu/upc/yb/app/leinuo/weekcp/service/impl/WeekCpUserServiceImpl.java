package cn.edu.upc.yb.app.leinuo.weekcp.service.impl;

import cn.edu.upc.yb.app.leinuo.weekcp.dao.WeekCpMatchDao;
import cn.edu.upc.yb.app.leinuo.weekcp.dao.WeekCpUserDao;
import cn.edu.upc.yb.app.leinuo.weekcp.entity.WeekCpMatch;
import cn.edu.upc.yb.app.leinuo.weekcp.entity.WeekCpUser;
import cn.edu.upc.yb.app.leinuo.weekcp.enums.WeekCpUserEnum;
import cn.edu.upc.yb.app.leinuo.weekcp.exception.WeekCpMatchException;
import cn.edu.upc.yb.app.leinuo.weekcp.exception.WeekCpUserException;
import cn.edu.upc.yb.app.leinuo.weekcp.service.WeekCpMatchService;
import cn.edu.upc.yb.app.leinuo.weekcp.service.WeekCpUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leinuoleileinuo
 */
@Service
public class WeekCpUserServiceImpl implements WeekCpUserService {

    @Autowired
    private WeekCpUserDao userDao;
    @Autowired
    private WeekCpMatchService matchService;

    @Value("${WeekCp.maxNumOfNotCp}")
    private Integer maxNumOfNotCp;

    @Override
    public WeekCpUser getUserById(Integer userId) throws WeekCpUserException {
        WeekCpUser user = userDao.getUserById(userId);
        if (user == null) {
            throw new WeekCpUserException(WeekCpUserEnum.NOT_FOUND_USER_ID.getMessage());
        } else {
            return user;
        }
    }

    private void randomMatch() throws WeekCpMatchException, WeekCpUserException {
        List<WeekCpUser> userList = userDao.getNotCpUserList();
        if (userList.size() < this.maxNumOfNotCp) {
            return;
        }
        List<WeekCpUser> girlOrientationList = new ArrayList<>();
        List<WeekCpUser> boyOrientationList = new ArrayList<>();
        for (WeekCpUser user : userList) {
            if (user.getSexualOrientation() == 0) {
                girlOrientationList.add(user);
            } else {
                boyOrientationList.add(user);
            }
        }
        if (girlOrientationList.size() == 0 || boyOrientationList.size() == 0) {
            return;
        }
        for (int i = 0;i < girlOrientationList.size();i++) {
            if (boyOrientationList.size() == 0) {
                return;
            }
            WeekCpUser left = girlOrientationList.get(i);
            int random = (int)((Math.random()*boyOrientationList.size()));
            WeekCpUser right = boyOrientationList.get(random);
            matchService.addMatchByMatchMap(new WeekCpMatch(left.getUserId(),right.getUserId()));
            boyOrientationList.remove(random);
        }
    }
    @Override
    public boolean addUser(WeekCpUser user) throws WeekCpUserException,WeekCpMatchException {
//        如果不是cp的用户超过了maxNulOfNotCp(一般是20) 那么就将这些用户随机匹配并且写入match表
        this.randomMatch();
        Integer flag = userDao.addUser(user);
        return flag != 0;
    }

    @Override
    public boolean updateUserById(Integer userId, WeekCpUser user) throws WeekCpUserException {
        Integer flag = userDao.updateUserById(userId , user);
        return flag != 0;
    }

    @Override
    public boolean deleteUserById(Integer userId) throws WeekCpUserException {
        WeekCpUser user = this.getUserById(userId);
        Integer flag = 1;
        if (user.isDeleted()) {
            throw new WeekCpUserException(WeekCpUserEnum.USER_IS_DELETED.getMessage());
        } else {
            flag = userDao.deleteUserById(userId);
        }
        return flag != 0;
    }

    @Override
    public boolean reliefUserById(Integer userId) throws WeekCpUserException {
        WeekCpUser user = this.getUserById(userId);
        Integer flag = 1;
        if (user.isDeleted()) {
            throw new WeekCpUserException(WeekCpUserEnum.USER_IS_NOT_DELETED.getMessage());
        } else {
            flag = userDao.reliefUserById(userId);
        }
        return flag != 0;
    }

    @Override
    public List<WeekCpUser> getNotCpUserList() throws WeekCpUserException, WeekCpMatchException {
        return userDao.getNotCpUserList();
    }
}
