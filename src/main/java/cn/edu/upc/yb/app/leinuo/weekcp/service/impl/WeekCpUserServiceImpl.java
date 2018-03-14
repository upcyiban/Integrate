package cn.edu.upc.yb.app.leinuo.weekcp.service.impl;

import cn.edu.upc.yb.app.leinuo.weekcp.constant.WeekCpUserConst;
import cn.edu.upc.yb.app.leinuo.weekcp.dao.WeekCpUserDao;
import cn.edu.upc.yb.app.leinuo.weekcp.entity.WeekCpMatch;
import cn.edu.upc.yb.app.leinuo.weekcp.entity.WeekCpUser;
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
    private String maxNumOfNotCp;

    @Override
    public WeekCpUser getUserById(Integer userId) throws WeekCpUserException {
        WeekCpUser user = userDao.getWeekCpUserByUserId(userId);
        if (user == null) {
            throw new WeekCpUserException(WeekCpUserConst.NOT_FOUND_USER_ID);
        }
        return user;
    }

    private void setCp() throws WeekCpUserException {
        //设置CP
        List<WeekCpUser> notCpList = this.getNotCpUserList();
        //boyList 和 gilList保存的是性取向，并非性别
        List<WeekCpUser> boyList = new ArrayList<>();
        List<WeekCpUser> girlList = new ArrayList<>();
        for (WeekCpUser user : notCpList) {
            if (user.getSexualOrientation() == 1) {
                boyList.add(user);
            } else {
                girlList.add(user);
            }
        }
        List<WeekCpUser> smallerUserList = boyList.size() > girlList.size()? girlList:boyList;
        List<WeekCpUser> biggerUserList = boyList.size() <= girlList.size()? girlList:boyList;
        int k = 0;
        for (WeekCpUser item : smallerUserList) {
            matchService.addMatchByMatchMap(new WeekCpMatch(item.getUserId() , biggerUserList.get(k).getUserId()));
            k++;
        }
    }
    @Override
    public void addUser(WeekCpUser user) throws WeekCpUserException {
        user.setUserId(null);
        WeekCpUser user1 = userDao.getWeekCpUserByYibanId(user.getYibanId());
        if (user1 != null) {
            throw new WeekCpUserException(WeekCpUserConst.HAVE_YIBAN_ID);
        }
        userDao.save(user);
        //如果没有搭档的玩家多于配置文件的人数，例如很有可能是4或者20
        if(this.getNotCpUserList().size() >= Integer.parseInt(maxNumOfNotCp)) {
            this.setCp();
        }
    }

    @Override
    public void updateUser(WeekCpUser user) throws WeekCpUserException {
        if (user.getUserId() == null) {
            throw new WeekCpUserException(WeekCpUserConst.MUST_SET_ID);
        }
        userDao.save(user);
    }

    @Override
    public void deleteUserById(Integer userId) throws WeekCpUserException {
        WeekCpUser user = userDao.findOne(userId);
        user.setDeleted(1);
        userDao.save(user);
    }

    @Override
    public void reliefUserById(Integer userId) throws WeekCpUserException {
        WeekCpUser user = userDao.findOne(userId);
        user.setDeleted(0);
        userDao.save(user);
    }

    @Override
    public List<WeekCpUser> getNotCpUserList() {
        return userDao.getAllByCpAndDeleted(notIsCp,notIsDeleted);
    }

    @Override
    public WeekCpUser getUserByYibanId(String yibanId) throws WeekCpUserException {
        WeekCpUser user = userDao.getWeekCpUserByYibanId(yibanId);
        if (user == null) {
            throw new WeekCpUserException(WeekCpUserConst.NOT_FOUND_YIBAN_ID);
        }
        return user;
    }
}
