package com.java017.tripblog.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class Collect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "article_collect_id")
    private Article articlesCollectId;

    @ManyToOne(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "User_collect_id")
    private User userCollectId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Article getArticlesCollectId() {
        return articlesCollectId;
    }

    public void setArticlesCollectId(Article articlesCollectId) {
        this.articlesCollectId = articlesCollectId;
    }

    public User getUserCollectId() {
        return userCollectId;
    }

    public void setUserCollectId(User userCollectId) {
        this.userCollectId = userCollectId;
    }
}
