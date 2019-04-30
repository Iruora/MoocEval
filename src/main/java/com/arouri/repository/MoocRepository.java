package com.arouri.repository;

import com.arouri.entity.Mooc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Nidhal on 30/04/2019.
 */
public interface MoocRepository extends JpaRepository<Mooc, Long> {
    public List<Mooc> findByTitle(String title);
    public List<Mooc> findByAndCategoryAndOrderByEvaluation(String category);
}
