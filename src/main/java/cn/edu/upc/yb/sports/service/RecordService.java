package cn.edu.upc.yb.sports.service;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/5/14  22:57
 */
public interface RecordService {


    public Object findAll();
public Object deleteOne(int id);
    public Object addRecord(String username,String projectName,String score,String ranking,String recordInfo);
}
