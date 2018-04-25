package cn.edu.upc.yb.secondhand.service;

import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import cn.edu.upc.yb.secondhand.dto.CollectionInfo;
import cn.edu.upc.yb.secondhand.model.Article;
import cn.edu.upc.yb.secondhand.model.Collection;
import cn.edu.upc.yb.secondhand.repository.ArticleRepository;
import cn.edu.upc.yb.secondhand.repository.CollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class CollectionService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CollectionRepository collectionRepository;

    @Value("${jwt.header}")
    private String tokenHeader;

    public List<CollectionInfo> getCollectionInfo(HttpServletRequest request){
        String token=request.getParameter(this.tokenHeader);
        Article article=new Article();
        int yibanId=Integer.valueOf(jwtTokenUtil.getYBidFromTocken(token));
        List<Collection> collections=collectionRepository.findByUserIdOrderByCreateTimeDesc(yibanId);
        List<CollectionInfo> collectionInfos = new ArrayList<>();
        for (Collection collection:collections){
            CollectionInfo collectionInfo=new CollectionInfo();
            article=articleRepository.findOne(collection.getArticleId());
            collectionInfo.setId(collection.getId());
            collectionInfo.setUserId(collection.getUserId());
            collectionInfo.setArticleId(collection.getArticleId());
            collectionInfo.setArticleDetail(article.getDetail());
            collectionInfo.setArticleName(article.getName());
            collectionInfo.setArticleKind(article.getKind());
            collectionInfo.setArticlePrice(article.getPrice());
            collectionInfo.setArticleUserYBhead(article.getYbhead());
            collectionInfo.setArticleUserYBName(article.getYbname());
            collectionInfos.add(collectionInfo);
        }

          return collectionInfos;

    }



}
