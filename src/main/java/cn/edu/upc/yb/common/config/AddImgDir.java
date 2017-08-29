package cn.edu.upc.yb.common.config;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Component
public class AddImgDir {

    @PostConstruct
    public void makeimgdir() throws IOException {
        File file = new File("file/img/");

        if(!file.exists()){
            file.mkdirs();
        }
    }
}
