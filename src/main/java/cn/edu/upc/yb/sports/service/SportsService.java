package cn.edu.upc.yb.sports.service;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/5/12  18:45
 */
public interface SportsService {

    public Object addUser(String project,String username,String ranking ,String score ,long ScoreOrd,boolean outRecord,String recordInfo);
    public Object findByUsername(String username);
    public Object findByProject(String project);
    public  Object deleteUser(int id);
    public Object findAllOutRecord(boolean done);
}
