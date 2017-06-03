package cn.edu.upc.yb.common.security.service;

import java.io.Serializable;

public class JwtAuthenticationRequest implements Serializable {

    private static final long serialVersionUID = -8445943548965154778L;

    private String appname;
    private String vq;

    public JwtAuthenticationRequest() {
        super();
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getVq() {
        return vq;
    }

    public void setVq(String vq) {
        this.vq = vq;
    }
}
