package com.arouri.repository;

import com.arouri.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by Nidhal on 30/04/2019.
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
    public List<Comment> findTopByCreationDate(Date crationDate);
}
