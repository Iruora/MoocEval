package com.arouri.repository;

import com.arouri.entity.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Nidhal on 30/04/2019.
 */
public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
    public AppRole findByRoleName(String role);
}
