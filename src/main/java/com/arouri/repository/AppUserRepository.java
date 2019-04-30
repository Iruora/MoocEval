package com.arouri.repository;

import com.arouri.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Nidhal on 30/04/2019.
 */
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    public AppUser findAppUserByUsername(String username);
}

