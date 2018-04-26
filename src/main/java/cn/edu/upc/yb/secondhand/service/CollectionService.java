package cn.edu.upc.yb.secondhand.service;

import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import cn.edu.upc.yb.secondhand.dto.CollectionInfo;
import cn.edu.upc.yb.secondhand.model.SecondArticle;
import cn.edu.upc.yb.secondhand.model.SecondCollection;
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
        SecondArticle secondArticle =new SecondArticle();
        int yibanId=Integer.valueOf(jwtTokenUtil.getYBidFromTocken(token));
        List<SecondCollection> secondCollections =collectionRepository.findByUserIdOrderByCreateTimeDesc(yibanId);
        List<CollectionInfo> collectionInfos = new ArrayList<>();
        for (SecondCollection secondCollection : secondCollections){
            CollectionInfo collectionInfo=new CollectionInfo();
            secondArticle =articleRepository.findOne(secondCollection.getArticleId());
            collectionInfo.setId(secondCollection.getId());
            collectionInfo.setUserId(secondCollection.getUserId());
            collectionInfo.setArticleId(secondCollection.getArticleId());
            collectionInfo.setArticleDetail(secondArticle.getDetail());
            collectionInfo.setArticleName(secondArticle.getName());
            collectionInfo.setArticleKind(secondArticle.getKind());
            collectionInfo.setArticlePrice(secondArticle.getPrice());
            collectionInfo.setArticleUserYBhead(secondArticle.getYbhead());
            collectionInfo.setArticleUserYBName(secondArticle.getYbname());
            collectionInfos.add(collectionInfo);
        }

          return collectionInfos;

    }



}
