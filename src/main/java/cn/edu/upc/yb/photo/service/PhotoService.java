package cn.edu.upc.yb.photo.service;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class PhotoService {

    public boolean isExist(String stuId){
        File file = new File("file/img/" + stuId + ".jpg");
        return file.exists();
    }
}
