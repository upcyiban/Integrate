package cn.edu.upc.yb.secondhand.service;

import cn.edu.upc.yb.secondhand.dto.ReviewInfo;
import cn.edu.upc.yb.secondhand.model.SecondArticle;
import cn.edu.upc.yb.secondhand.model.SecondReview;
import cn.edu.upc.yb.secondhand.repository.ArticleRepository;
import cn.edu.upc.yb.secondhand.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ArticleRepository articleRepository;


    public Object historyReviewBrowse(int yibanId){

        List<SecondReview> secondReviewList = new ArrayList<>();
        secondReviewList = reviewRepository.findByYbidAndIsdeleteOrderByCreatetimeDesc(yibanId,0);
        List<ReviewInfo> reviewInfoList = new ArrayList<>();
        SecondArticle secondArticle = new SecondArticle();
        for (SecondReview secondReview :secondReviewList){
            System.out.println(secondReview.getId());
            int artilceId=secondReview.getArticleId();
            secondArticle = articleRepository.findOne(artilceId);
            ReviewInfo reviewInfo = new ReviewInfo(secondReview);
            reviewInfo.setArticleImgUrl(secondArticle.getImgurl());
            reviewInfoList.add(reviewInfo);
        }
        return reviewInfoList;

    }


}
