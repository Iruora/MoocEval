package com.arouri.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nidhal on 30/04/2019.
 */
@Entity
public class Mooc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String url;
    private String category;
    private Integer evaluation;

    @ManyToMany(fetch = FetchType.EAGER)
    List<AppUser> evaluators = new ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER)
    List<Comment> comments = new ArrayList<>();
    // ---------------------------

    public Mooc() {
    }

    public Mooc(String title, String description, String url, String category, Integer evaluation, List<AppUser> evaluators, List<Comment> comments) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.category = category;
        this.evaluation = evaluation;
        this.evaluators = evaluators;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Integer evaluation) {
        this.evaluation = evaluation;
    }

    public List<AppUser> getEvaluators() {
        return evaluators;
    }

    public void setEvaluators(List<AppUser> evaluators) {
        this.evaluators = evaluators;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
