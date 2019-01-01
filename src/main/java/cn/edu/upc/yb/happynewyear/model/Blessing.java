package cn.edu.upc.yb.happynewyear.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/1/1  0:45
 **/
@Entity

public class Blessing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String blessing;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBlessing() {
        return blessing;
    }

    public void setBlessing(String blessing) {
        this.blessing = blessing;
    }
}
