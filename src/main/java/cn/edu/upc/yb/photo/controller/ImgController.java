package cn.edu.upc.yb.photo.controller;

import cn.edu.upc.yb.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImgController {

    private final StorageService storageService;

    @Autowired
    ImgController(StorageService storageService){
        this.storageService = storageService;
    }

    @GetMapping("/photo/img/{filename:.+}")
    @ResponseBody

    public ResponseEntity<Resource> showFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                .body(file);
    }
}
