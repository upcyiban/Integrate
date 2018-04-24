package cn.edu.upc.yb.secondhand.controller;

import cn.edu.upc.yb.common.dto.SwaggerParameter;
import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import cn.edu.upc.yb.secondhand.dto.Message;
import cn.edu.upc.yb.secondhand.model.Article;
import cn.edu.upc.yb.secondhand.model.Collection;
import cn.edu.upc.yb.secondhand.model.User;
import cn.edu.upc.yb.secondhand.repository.ArticleRepository;
import cn.edu.upc.yb.secondhand.repository.CollectionRepository;
import cn.edu.upc.yb.secondhand.repository.UserRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/secondhand/collention")
public class SecondCollectionController {

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    /*
    用户的收藏记录
     */
    @ApiOperation("用户的收藏记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization, value = "token", dataType ="String",paramType = "query")
    })
    @RequestMapping(value = "/usercollection",method = RequestMethod.GET)
    public Object userCollection(HttpServletRequest request){
        String token=request.getParameter(this.tokenHeader);
        int userid=Integer.valueOf(jwtTokenUtil.getYBidFromTocken(token));
        return collectionRepository.findByUserIdOrderByCreateTimeDesc(userid);
    }

    /*
    添加一条收藏记录
     */
    @ApiOperation("添加一条收藏记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization, value = "token", dataType ="String",paramType = "query"),
            @ApiImplicitParam(name = "articleid",value = "物品id",dataType = "int",paramType = "query")
    })
    @RequestMapping(value = "/createcollection",method = RequestMethod.GET)
    public Object createCollection(HttpServletRequest request,int articleid){
        String token=request.getParameter(this.tokenHeader);
        int userid=Integer.valueOf(jwtTokenUtil.getYBidFromTocken(token));
        Article article=articleRepository.findOne(articleid);
        if (article==null){
            return new Message(0,"null article");
        }
        if (article.getIsdeal()==-1||article.getIsdeal()==-2){
            return new Message(0,"error article");
        }
        User user=userRepository.findByUserid(userid);
        if (user==null){
            return new Message(0,"null user");
        }
        if (user.isIsdelete()){
            return new Message(0,"error user");
        }

        Collection collection=new Collection();
        collection=collectionRepository.findByUserIdAndArticleId(userid,articleid);
        if (collection!=null){
            return new Message(0,"don't collect again");
        }
        collection=new Collection();
        collection.setArticleId(articleid);
        collection.setUserId(userid);
        Date time=new Date();
        collection.setCreateTime(time);
        collectionRepository.save(collection);


        int collections=article.getCollections();
        collections++;
        article.setCollections(collections);
        articleRepository.save(article);

        return new Message(1,"create collection seccess");
    }

    /*
    删除收藏记录
     */
    @ApiOperation("删除收藏记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization, value = "token", dataType ="String",paramType = "query"),
            @ApiImplicitParam(name = "collectionid",value = "收藏记录id",dataType = "int",paramType = "query")
    })
    @RequestMapping(value = "/deletecollection",method = RequestMethod.GET)
    public Object deleteCollection(HttpServletRequest request,int collectionid){

        Collection collection=collectionRepository.findOne(collectionid);
        if (collection==null){
            return new Message(0,"null collection");
        }
        Article article=articleRepository.findOne(collection.getArticleId());
        int collections=article.getCollections();
        collections--;
        article.setCollections(collections);
        articleRepository.save(article);

        collectionRepository.delete(collectionid);
        return new Message(1,"delete collection seccess");

    }


}
