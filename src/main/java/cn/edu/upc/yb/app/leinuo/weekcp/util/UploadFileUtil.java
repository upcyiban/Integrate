package cn.edu.upc.yb.app.leinuo.weekcp.util;

import cn.edu.upc.yb.app.leinuo.weekcp.enums.FileEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author UPCdevelopment
 */
@Component
public class UploadFileUtil {

    @Value("${upload.imagePath}")
    private String imagePath;
    @Value("${upload.textPath}")
    private String textPath;
    @Value("${upload.videoPath}")
    private String videoPath;

    @Value("${upload.imageList}")
    private String imageList;
    @Value("${upload.textList}")
    private String textList;
    @Value("${upload.videoList}")
    private String videoList;
    @Value("${linux}")
    private boolean flag;

    @Value("${upload.filePath}")
    private String filePath;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String getPath(String fileName) {
        String fileType = fileName.substring(fileName.lastIndexOf('.')+1,fileName.length());

        HashMap<String,List<String>> typeMap = new HashMap<>();
        typeMap.put(imagePath, Arrays.asList(imageList.split(",")));
        typeMap.put(textPath,Arrays.asList(textList.split(",")));
        typeMap.put(videoPath,Arrays.asList(videoList.split(",")));

        for (String key : typeMap.keySet()) {
            List<String> item = typeMap.get(key);
            for (String s : item) {
                if (s.equals(fileType)) {
                    return this.filePath+key+"\\";
                }
            }
        }
        return this.filePath+"file\\";
    }

    public File createFile(String fileName) throws Exception {
        String path = this.getPath(fileName)+fileName;
        if(flag) {
            path = path.replace('\\','/');
        }
        System.out.println(path.replace('\\','/'));
        File file = new File(path);
        logger.info("完整的路径{}" ,path);
        if (file.exists()) {
            throw new Exception(FileEnum.REPEATED_FILE_PATH);
        }
        boolean flag = file.createNewFile();
        if (!flag) {
            throw new Exception(FileEnum.UNEXPECTED_WRONG);
        }
        return file;
    }
}
