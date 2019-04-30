package com.arouri.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Nidhal on 30/04/2019.
 */
@Entity
@Data
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(unique = true)
    String username;
    @Email
    String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;
    @NotBlank
    @NotNull
    String firstName;
    @NotBlank @NotNull
    String lastName;
    String gitlabPrivateToken;
    String avatarURL;
    private boolean activated;
    @ManyToMany(fetch = FetchType.EAGER) // EAGER : each time we load a user, it's own roles will be loaded
            Collection<AppRole> roles = new ArrayList<>();

    public AppUser() {
    }

    public AppUser(String username, @Email String email, String password, @NotBlank @NotNull String firstName, @NotBlank @NotNull String lastName, String gitlabPrivateToken, String avatarURL, boolean activated, Collection<AppRole> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gitlabPrivateToken = gitlabPrivateToken;
        this.avatarURL = avatarURL;
        this.activated = activated;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGitlabPrivateToken() {
        return gitlabPrivateToken;
    }

    public void setGitlabPrivateToken(String gitlabPrivateToken) {
        this.gitlabPrivateToken = gitlabPrivateToken;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public Collection<AppRole> getRoles() {
        return roles;
    }

    public void setRoles(Collection<AppRole> roles) {
        this.roles = roles;
    }
}

