package cn.edu.upc.yb.sports.service.impl;

import cn.edu.upc.yb.sports.model.Record;
import cn.edu.upc.yb.sports.reporistory.RecordReporistory;
import cn.edu.upc.yb.sports.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/5/14  23:02
 */
@Service
public class RecordServiceImp implements RecordService {
    @Autowired
    private RecordReporistory recordReporistory;

    @Override
    public Object findAll() {

        return recordReporistory.findAll();
    }

    @Override
    public Object deleteOne(int id) {
        recordReporistory.delete(id);

        return "删除成功";
    }

    @Override
    public Object addRecord(String username, String projectName, String score, String ranking, String recordInfo) {


        Record record = new Record();
        record.setProjectName(projectName);
        record.setRanking(ranking);
        record.setRecordInfo(recordInfo);
        record.setScore(score);
        record.setUsername(username);
        recordReporistory.save(record);
        return "添加成功";
    }
}
