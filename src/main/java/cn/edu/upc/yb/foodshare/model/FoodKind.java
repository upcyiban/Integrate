package cn.edu.upc.yb.foodshare.model;

import javax.persistence.*;

@Entity
@Table(name = "foodShare_kind")
public class FoodKind {//标签描述表

    @Id
    @GeneratedValue
    @Column(name = "kind_id")
    private int id;//

    private String detail;//标签描述

    public int getId() {
        return id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
