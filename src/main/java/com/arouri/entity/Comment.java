package com.arouri.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Nidhal on 30/04/2019.
 */
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @ManyToOne
    private Mooc mooc;
    // ---------------------------------

    public Comment() {
    }

    public Comment(String content, Date creationDate, Mooc mooc) {
        this.content = content;
        this.creationDate = creationDate;
        this.mooc = mooc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Mooc getMooc() {
        return mooc;
    }

    public void setMooc(Mooc mooc) {
        this.mooc = mooc;
    }
}
