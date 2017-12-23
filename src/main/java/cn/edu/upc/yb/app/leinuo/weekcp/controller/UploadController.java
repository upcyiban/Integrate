package cn.edu.upc.yb.app.leinuo.weekcp.controller;

import cn.edu.upc.yb.app.leinuo.weekcp.result.Result;
import cn.edu.upc.yb.app.leinuo.weekcp.util.UploadFileUtil;
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
    public Result uploadImage(@ModelAttribute("file")MultipartFile file,
                              @ModelAttribute("input-text")String text) throws IOException {
        logger.info(file.getOriginalFilename());
        logger.info(file.getContentType());
        logger.info(text);
        Long code = System.currentTimeMillis();
        try {
            file.transferTo(fileUtil.createFile(code+"-"+file.getOriginalFilename()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.success("你成功了" , code+"-"+file.getOriginalFilename());
    }
}
