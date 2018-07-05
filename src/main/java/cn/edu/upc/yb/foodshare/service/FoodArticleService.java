package cn.edu.upc.yb.foodshare.service;

import cn.edu.upc.yb.foodshare.dto.Message;
import cn.edu.upc.yb.foodshare.model.FoodArticle;
import cn.edu.upc.yb.foodshare.model.FoodLike;
import cn.edu.upc.yb.foodshare.model.FoodReview;
import cn.edu.upc.yb.foodshare.repository.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FoodArticleService {

    @Autowired
    private FoodArticleRepository foodArticleRepository;
    @Autowired
    private FoodLikeRepository foodLikeRepository;
    @Autowired
    private FoodCollectionRepository foodCollectionRepository;
    @Autowired
    private FoodReviewRepository foodReviewRepository;
    @Autowired
    private FoodKindRepository foodKindRepository;

//发布菜品
    public Object publicFood(int ybid, String name, String kind, String detail, String imgurl, String price, String address){
        FoodArticle foodArticle = new FoodArticle();
        foodArticle.setUserid(ybid);
        foodArticle.setAddress(address);
        foodArticle.setDetail(detail);
        foodArticle.setImgurl(imgurl);
        foodArticle.setKind(kind);
        foodArticle.setName(name);
        foodArticle.setPrice(price);
        foodArticle.setCreatetime(new Date());

        foodArticleRepository.save(foodArticle);
        return new Message(1,"成功发布，等待审核");
    }
//更新菜品
    public Object updateFood(int foodId,String name,String kind,String detail,String imgurl,String price,String address){
        FoodArticle foodArticle = foodArticleRepository.findOne(foodId);
        if(foodArticle==null){
            return new Message(0,"null article");
        }
        foodArticle.setAddress(address);
        foodArticle.setDetail(detail);
        foodArticle.setImgurl(imgurl);
        foodArticle.setKind(kind);
        foodArticle.setName(name);
        foodArticle.setPrice(price);
        foodArticle.setCreatetime(new Date());
        foodArticleRepository.save(foodArticle);
        return new Message(1,"update success!");
    }
//点赞，取消点赞
    public Object like(int ybid,int foodId){
        FoodArticle foodArticle = foodArticleRepository.findOne(foodId);
        if(foodArticle==null){
            return new Message(0,"this article not exist");
        }else if(foodArticle.getState()!=0){
            return new Message(0,"can't like,this article maybe deleted by administrator or creator");
        }
        int likeCount = foodArticle.getLikeCount();
        FoodLike foodLike = foodLikeRepository.findByUseridAndFoodid(ybid,foodId);
        if(foodLike==null){
            foodLike = new FoodLike(ybid,foodId,new Date());
            foodLikeRepository.save(foodLike);
            foodArticle.setLikeCount(likeCount+1);
            foodArticleRepository.save(foodArticle);
            return new Message(0,"Like success!");
        }
        else {
            foodLikeRepository.delete(foodLike);
            foodArticle.setLikeCount(likeCount-1);
            foodArticleRepository.save(foodArticle);
            return new Message(0,"Cancel like success!");
        }
    }
//判断是否点赞
    public Object isLike(int ybid,int foodId){
        FoodLike foodLike = foodLikeRepository.findByUseridAndFoodid(ybid,foodId);
        if(foodLike==null){
            return false;
        }
        else return true;
    }
//删除菜品
    public Object deleteFood(int foodid){
        FoodArticle foodArticle = foodArticleRepository.findOne(foodid);
        if(foodArticle==null){
            return new Message(0,"null article");
        }
        if(foodArticle.getState()==-2){
            return new Message(0,"article has been deleted by administrator");
        }
        foodArticle.setState(-1);
        foodArticleRepository.save(foodArticle);
        return new Message(1,"delete success!");
    }
//获取用户点赞的菜品
    public Object getLikeFood(int ybid,int pageSize,int page){
        Pageable pageable = new PageRequest(page,pageSize);
        return foodArticleRepository.findBylike(ybid,pageable);
    }
//获取用户收藏的菜品
    public Object getCollectionFood(int ybid,int pageSize,int page){
        Pageable pageable = new PageRequest(page,pageSize);
        return foodArticleRepository.findByCollection(ybid,pageable);
    }
//获取用户评论过的菜品
    public Object getReviewFood(int ybid,int pageSize,int page){
        List<FoodReview> foodReviews = foodReviewRepository.findByUseridAndDelete(ybid,false);
        Set set = new HashSet();
        for(FoodReview foodReview :foodReviews){
            set.add(foodReview.getFoodid());
        }
        Pageable pageable = new PageRequest(page,pageSize);
        return foodArticleRepository.findByIdInAndState(set,0,pageable);
    }

    //获取已发布的菜品包括通过审核和为通过审核以及在审核的,美食状态0表示通过审核，1表示待审核，-2表示未通过审核
    public Object getOwnFood(int ybid,int pageSize,int page){

        Pageable pageable = new PageRequest(page,pageSize);
        int[] states = {0,1,-2};
        Page<FoodArticle> foodArticles=foodArticleRepository.findByUseridAndStateInOrderByCreatetimeDesc(ybid,states,pageable);
        checkFood(ybid);
        return foodArticles;
    }

    //    获取菜品信息
    public Object getFood(int foodid){
        FoodArticle foodArticle = foodArticleRepository.findOne(foodid);
        return foodArticle;
    }


//   更新一个文章的评论、收藏、点赞数
    public void update(int foodid){
        FoodArticle foodArticle = foodArticleRepository.findOne(foodid);
        foodArticle.setReview(foodReviewRepository.countByFoodidAndDelete(foodid,false));
        foodArticle.setLikeCount(foodLikeRepository.countByFoodid(foodid));
        foodArticle.setCollection(foodCollectionRepository.countByFoodid(foodid));
    }



//  当查看我的发布时，对提交发布的通过审核且ischeck属性为false置true
    public void checkFood(int userid){
        int[] states = {0,-2};
        List<FoodArticle> foodArticleList = foodArticleRepository.findByUseridAndStateIn(userid,states);
        for(FoodArticle foodArticle: foodArticleList ){
            if(foodArticle.getIscheck()==false){
                foodArticle.setIscheck(true);
                foodArticleRepository.save(foodArticle);
            }
        }
    }

//搜索，返回分页搜索的情况
    public Object search(String detail,int pageSize,int page){
        return true;
    }

//返回标签库中所有标签
    public Object getKind(){
        return foodKindRepository.findAll();
    }
}
