package cn.edu.upc.yb.graduation.controller;

import cn.edu.upc.yb.graduation.service.ArticleService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/5/24  17:40
 */
@RestController
@RequestMapping("/graduation")
public class ArticleController {


    @Autowired
    private ArticleService articleService;

    @GetMapping("/")
    @ApiOperation(value = "主页随机显示当前的数据")
    public Object index(){
        return articleService.randomOne();
    }

    @ApiOperation(value = "点赞数量最多的留言")
    @GetMapping("/best")
    public Object best(){

        return articleService.theBesttop16();

    }

    @PostMapping("/create_article")
    @ApiOperation(value = "创建一个留言")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "author",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "to",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "content",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "url",required = true,dataType = "String")}
    )
    public Object createArticle(String author,String to,String content,String url){

        return articleService.createArticle(author,to,content,url);

    }

    @PostMapping("/agree")
    @ApiOperation("点赞功能的实现")
    @ApiImplicitParam(paramType = "query",name = "articleId",value = "留言的Id",required = true,dataType = "long")
    public Object agree(long articleId){
        return articleService.agreeWithYou(articleId);
    }
}
