package cn.edu.upc.yb.graduation.service.impl;

import cn.edu.upc.yb.graduation.model.Article;
import cn.edu.upc.yb.graduation.repository.GraduationArticleRepository;
import cn.edu.upc.yb.graduation.service.ArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/5/24  17:32
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private GraduationArticleRepository graduationArticleRepository;

    @Override
    public Object createArticle(String author, String to, String content, String url) {
        Article article = new Article();
        article.setAuthor(author);
        article.setContent(content);
        article.setSendTo(to);
        article.setUrl(url);
        return graduationArticleRepository.save(article);
    }

    @Override
    public Object agreeWithYou(long articleId) {

        Article article = graduationArticleRepository.findById(articleId);
        article.setAgree(article.getAgree() + 1);

        return article.getAgree();
    }

    @Override
    public Object findtop10() {


        Iterable<Article> articles = graduationArticleRepository.findAll();

        return graduationArticleRepository.findTop10ByOrderById();
    }

    @Override
    public Object theBesttop10() {

        Iterable<Article> articles = graduationArticleRepository.findAll(new Sort(Sort.Direction.DESC, "agree"));
        List<Article> articles1 = new ArrayList<>();

        int i = 0;
        for (Article article : articles) {
            if (i == 16) {
                break;
            }
            i++;
            articles1.add(article);
        }
        return articles1;

    }
}
