package cn.edu.upc.yb.graduation.service;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/5/24  17:31
 */
public interface ArticleService {

    /*
     * 创建一个留言
     * 包括署名，给谁， 内容， 图片，*/
    Object createArticle(String author, String to, String content, String url);

    /*
     * 为某个留言点赞
     * */
    Object agreeWithYou(long articleId);


    /*
     *
     * 点赞数最多的10个数据*/
    Object theBesttop16();

    /*
    *
    * 随机显示一条留言*/
    Object randomOne();

}
