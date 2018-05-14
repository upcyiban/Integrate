package cn.edu.upc.yb.sports.controller;

import cn.edu.upc.yb.sports.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/5/14  23:08
 */
@RestController
@RequestMapping("/sports")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @GetMapping("/record")
    public Object findAll(){

       return recordService.findAll();
    }


    @PostMapping("/delete_one")
    public Object deleteOne(int id){

        return recordService.deleteOne(id);

    }

    @PostMapping("/add_record")
    public Object addRecord(String username, String projectName, String score, String ranking, String recordInfo){

        return  recordService.addRecord(username,projectName,score,ranking,recordInfo);
    }
}
