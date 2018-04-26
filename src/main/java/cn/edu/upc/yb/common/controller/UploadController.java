package cn.edu.upc.yb.common.controller;

import cn.edu.upc.yb.app.leinuo.weekcp.result.Result;
import cn.edu.upc.yb.common.util.UploadFileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author UPCdevelopment
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private UploadFileUtil fileUtil;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/file")
    public Result uploadImage(@ModelAttribute("file")MultipartFile file) throws IOException {
        logger.info(file.getOriginalFilename());
        String fileName = file.getOriginalFilename();
        String fileType = fileUtil.getFileType(fileName);
        Long code = System.currentTimeMillis();
        try {
            logger.info(code.toString() + "." + fileType);
            file.transferTo(fileUtil.createFile(code.toString() + "." + fileType));
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info(file.getOriginalFilename());
        return Result.success(
                "你成功了" ,
                fileUtil.getKey(fileName) + "\\" + code.toString() + "." + fileType);
    }
}
