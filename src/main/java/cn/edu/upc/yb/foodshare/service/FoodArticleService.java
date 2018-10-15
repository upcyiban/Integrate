package cn.edu.upc.yb.foodshare.service;

/**
 * Created By Kazusa in 2018/7/6 15:54
 */

import cn.edu.upc.yb.foodshare.dto.Message;
import cn.edu.upc.yb.foodshare.model.FoodArticle;
import cn.edu.upc.yb.foodshare.model.FoodCollection;
import cn.edu.upc.yb.foodshare.model.FoodLike;
import cn.edu.upc.yb.foodshare.model.FoodReview;
import cn.edu.upc.yb.foodshare.repository.FoodArticleRepository;
import cn.edu.upc.yb.foodshare.repository.FoodCollectionRepository;
import cn.edu.upc.yb.foodshare.repository.FoodLikeRepository;
import cn.edu.upc.yb.foodshare.repository.FoodReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.*;

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
        return new Message(1,"上传成功，等待审核");
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
            return new Message(1,"Like success!");
        }
        else {
            foodLikeRepository.delete(foodLike);
            foodArticle.setLikeCount(likeCount-1);
            foodArticleRepository.save(foodArticle);
            return new Message(1,"Cancel like success!");
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
    public Object deleteFood(int foodid,int ybid){
        FoodArticle foodArticle = foodArticleRepository.findOne(foodid);

        if(foodArticle==null){
            return new Message(0,"null article");
        }else{
            if(foodArticle.getUserid()!=ybid){
                return new Message(0,"无权删除此菜品");
            }else{
                if(foodArticle.getState()==-2){
                    return new Message(0,"article has been deleted by administrator");
                }
                else{
                    foodArticle.setState(-1);
                    foodArticleRepository.save(foodArticle);
                    return new Message(1,"delete success!");
                }
            }
        }


    }

    //获取用户点赞的菜品
    public Object getLikeFood(int ybid){
        List<FoodLike> foodLikes = foodLikeRepository.findByUseridOrderByCreatetime(ybid);
        List<Integer> foodIds = new ArrayList<>();
        for(FoodLike food : foodLikes){
            foodIds.add(food.getFoodid());
        }
        return foodArticleRepository.findByIdInAndState(foodIds,0);
    }
    //获取用户收藏的菜品
    public Object getCollectionFood(int ybid){
        List<FoodCollection> foodCollections = foodCollectionRepository.findByUseridOrderByCreatetime(ybid);
        List<Integer> foodIds = new ArrayList<>();
        for(FoodCollection food : foodCollections){
            foodIds.add(food.getFoodid());
        }
        return foodArticleRepository.findByIdInAndState(foodIds,0);
    }
    //获取用户评论过的菜品
    public Object getReviewFood(int ybid){
        Set<FoodReview> set = foodReviewRepository.findByUseridAndIsdelete(ybid,0);
        List<Integer> foodIds = new ArrayList();
        for(FoodReview foodReview : set){
            foodIds.add(foodReview.getFoodid());
        }
        return foodArticleRepository.findByIdInAndState(foodIds,0);
    }

    //获取已发布的菜品包括通过审核和为通过审核以及在审核的,美食状态0表示通过审核，1表示待审核，-2表示未通过审核
    public Object getOwnFood(int ybid){
        int[] states = {0,1,-2};
        List<FoodArticle> foodArticles = foodArticleRepository.findByUseridAndStateInOrderByCreatetimeDesc(ybid,states);
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
        foodArticle.setReview(foodReviewRepository.countByFoodidAndIsdelete(foodid,0));
        foodArticle.setLikeCount(foodLikeRepository.countByFoodid(foodid));
        foodArticle.setCollection(foodCollectionRepository.countByFoodid(foodid));
        foodArticleRepository.save(foodArticle);
    }



    //  当查看我的发布时，对提交发布的通过审核且ischeck属性为0置1
    public void checkFood(int userid){
        int[] states = {0,-2};
        List<FoodArticle> foodArticleList = foodArticleRepository.findByUseridAndStateInOrderByCreatetimeDesc(userid,states);
        for(FoodArticle foodArticle: foodArticleList ){
            if(foodArticle.getIscheck()==0){
                foodArticle.setIscheck(1);
                foodArticleRepository.save(foodArticle);
            }
        }
    }

    //标签搜索，返回分页搜索的情况
    public Object kindSearch(String kind){
        return foodArticleRepository.findByStateAndKindLikeOrderByCreatetimeDesc(kind);
    }
    //菜品名搜索，返回分页搜索的情况
    public Object nameSearch(String name){
        return foodArticleRepository.findByNameLikeAndStateOrOrderByCreatetime(name);
    }
}