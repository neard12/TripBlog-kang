package com.java017.tripblog.service_impl;

import com.java017.tripblog.entity.Article;
import com.java017.tripblog.entity.Collect;
import com.java017.tripblog.entity.User;
import com.java017.tripblog.repository.CollectRepository;
import com.java017.tripblog.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CollectServicelmpl implements CollectService {

    @Autowired
    private final CollectRepository collectRepository;

    public CollectServicelmpl(CollectRepository collectRepository) {
        this.collectRepository = collectRepository;
    }

    // 使用者按讚 by 大方

    public Collect updateUserCollect(Collect collect) {
        User collectUserId = collect.getUserCollectId();
        System.out.println("4你?");
        Article articleId = collect.getArticlesCollectId();
        System.out.println("4你?");


        if (!collectRepository.existsByUserCollectIdAndArticlesCollectId(collectUserId, articleId)) { //判斷資料庫是否有重覆資料 還未寫完
            System.out.println("有沒有近來使用者按讚執行成功");
            collectRepository.save(collect);
            System.out.println("使用者按讚執行成功");

        } else if (collectRepository.existsByUserCollectIdAndArticlesCollectId(collectUserId, articleId)) {
            System.out.println("資料庫已有相同資料(recommendUserId+articleId)");
            return null;
        }
        return collect;
    }

    //庭妤    顯示[已推薦]
    public ArrayList<Collect> findByUserCollectId(User userCollectId) {
        return collectRepository.findByUserCollectId(userCollectId);
    }

    @Override
    public ArrayList<Article> changeToArticle(List<Collect> messagedList) {
        ArrayList<Article> articles = new ArrayList<>();
        for (Collect collect:messagedList)
        {
            Article article = new Article();
            article.setArticleTitle(collect.getArticlesCollectId().getArticleTitle());
            article.setArticleId(collect.getArticlesCollectId().getArticleId());
            article.setCreateTime(collect.getArticlesCollectId().getCreateTime());
            article.setFreeTag(collect.getArticlesCollectId().getFreeTag());
            article.setTextEditor(collect.getArticlesCollectId().getTextEditor());
            article.setSaveImgPath(collect.getArticlesCollectId().getSaveImgPath());
            article.setEnterAddressName(collect.getArticlesCollectId().getEnterAddressName());
            article.setEnterAddressLng(collect.getArticlesCollectId().getEnterAddressLng());
            article.setEnterAddressLat(collect.getArticlesCollectId().getEnterAddressLat());
            article.setSubjectCategory(collect.getArticlesCollectId().getSubjectCategory());
            articles.add(article);
        }
        return articles;
    }
}
