package cn.edu.upc.yb.secondhand.controller;

import cn.edu.upc.yb.common.dto.SwaggerParameter;
import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import cn.edu.upc.yb.common.ybapi.MsgLetter;
import cn.edu.upc.yb.secondhand.dto.Message;
import cn.edu.upc.yb.secondhand.model.SecondArticle;
import cn.edu.upc.yb.secondhand.model.SecondCollection;
import cn.edu.upc.yb.secondhand.model.SecondUser;
import cn.edu.upc.yb.secondhand.repository.ArticleRepository;
import cn.edu.upc.yb.secondhand.repository.CollectionRepository;
import cn.edu.upc.yb.secondhand.repository.UserRepository;
import cn.edu.upc.yb.secondhand.service.CollectionService;
import cn.edu.upc.yb.secondhand.service.SecondMsgSendService;
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

    @Autowired
    private CollectionService collectionService;

    @Autowired
    private SecondMsgSendService secondMsgSendService;

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
        return collectionService.getCollectionInfo(request);
    }

    /*
    添加一条收藏记录
     */
    @ApiOperation("添加一条收藏记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization, value = "token", dataType ="String",paramType = "query"),
            @ApiImplicitParam(name = "articleid",value = "物品id",dataType = "int",paramType = "query")
    })
    @RequestMapping(value = "/createcollection",method = RequestMethod.POST)
    public Object createCollection(HttpServletRequest request,int articleid){
        String token=request.getParameter(this.tokenHeader);
        int userid=Integer.valueOf(jwtTokenUtil.getYBidFromTocken(token));
        SecondArticle secondArticle =articleRepository.findOne(articleid);
        if (secondArticle ==null){
            return new Message(0,"null secondArticle");
        }
        if (secondArticle.getIsdeal()==-1|| secondArticle.getIsdeal()==-2){
            return new Message(0,"error secondArticle");
        }
        SecondUser user=userRepository.findByUserid(userid);
        if (user==null){
            return new Message(0,"null user");
        }
        if (user.isIsdelete()){
            return new Message(0,"error user");
        }

        SecondCollection secondCollection =new SecondCollection();
        secondCollection =collectionRepository.findByUserIdAndArticleId(userid,articleid);
        if (secondCollection !=null){
            return new Message(0,"don't collect again");
        }
        secondCollection =new SecondCollection();
        secondCollection.setArticleId(articleid);
        secondCollection.setUserId(userid);
        Date time=new Date();
        secondCollection.setCreateTime(time);
        SecondCollection secondCollection1=collectionRepository.save(secondCollection);


        int collections= secondArticle.getCollections();
        collections++;
        secondArticle.setCollections(collections);
        articleRepository.save(secondArticle);
        secondMsgSendService.collectionSend(token,secondArticle);
        return collectionService.getOneCollectionInfo(secondCollection1);
    }

    /*
    删除收藏记录
     */
    @ApiOperation("删除收藏记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization, value = "token", dataType ="String",paramType = "query"),
            @ApiImplicitParam(name = "collectionid",value = "收藏记录id",dataType = "int",paramType = "query")
    })
    @RequestMapping(value = "/deletecollection",method = RequestMethod.POST)
    public Object deleteCollection(HttpServletRequest request,int collectionid){

        SecondCollection secondCollection =collectionRepository.findOne(collectionid);
        if (secondCollection == null){
            return new Message(0,"null secondCollection");
        }
        SecondArticle secondArticle =articleRepository.findOne(secondCollection.getArticleId());
        int collections= secondArticle.getCollections();
        collections--;
        secondArticle.setCollections(collections);
        articleRepository.save(secondArticle);

        collectionRepository.delete(collectionid);
        return new Message(1,"delete secondCollection seccess");

    }

    @ApiOperation("判断用户是否收藏某物品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization, value = "token", dataType ="String",paramType = "query"),
            @ApiImplicitParam(name = "articleid",value = "物品id",dataType = "int",paramType = "query")
    })
    @RequestMapping(value = "/iscollection",method = RequestMethod.GET)
    public Object isCollection(HttpServletRequest request,int articleid){
        String token=request.getParameter(this.tokenHeader);
        int userid=Integer.valueOf(jwtTokenUtil.getYBidFromTocken(token));
        SecondCollection secondCollection = collectionRepository.findByUserIdAndArticleId(userid,articleid);
        if (secondCollection == null){
            return false;
        }else {
            return true;
        }
    }



}
