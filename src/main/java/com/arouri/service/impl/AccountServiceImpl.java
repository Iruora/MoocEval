package com.arouri.service.impl;

import com.arouri.constants.UserConstants;
import com.arouri.repository.AppRoleRepository;
import com.arouri.repository.AppUserRepository;
import com.arouri.dto.RegistrationForm;
import com.arouri.entity.AppRole;
import com.arouri.entity.AppUser;
import com.arouri.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by Nidhal on 13/03/2019.
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService{

    final
    AppUserRepository userRepository;
    final
    AppRoleRepository roleRepository;
    final
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AccountServiceImpl(AppUserRepository userRepository, AppRoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // =======================================
    @Override
    public AppUser saveUser(RegistrationForm data) {
        String username = data.getUsername();
        AppUser user = this.findUserByUsername(username);
        if (user != null) throw new RuntimeException("This user already exists");
        String password = data.getPassword();
        String confirmPassword = data.getConfirmPassword();

        if (! password.equals(confirmPassword))
            throw new RuntimeException("Confirm password exception");
        AppUser u = new AppUser();
        u.setUsername(username);
        u.setFirstName(data.getFirstName());
        u.setLastName(data.getLastName());
        u.setPassword(
                bCryptPasswordEncoder.encode(
                        password
                )
        );
        u.setActivated(true);
        // -------------------------------
        AppUser ur = userRepository.save(u);
        this.addRoleToUser(username, "USER");

        return ur;
    }

    @Override
    public AppRole saveRole(AppRole appRole) {
        return roleRepository.save(appRole);
    }


    @Override
    public AppUser findUserByUsername(String username) {
        return userRepository.findAppUserByUsername(username);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser user = userRepository.findAppUserByUsername(username);
        AppRole role = roleRepository.findByRoleName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public Collection<AppUser> getAllUsers() {
        return userRepository.findAll();
    }
//    @Bean
//    private BCryptPasswordEncoder getBCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
