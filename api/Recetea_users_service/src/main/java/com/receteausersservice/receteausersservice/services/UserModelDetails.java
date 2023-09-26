package com.receteausersservice.receteausersservice.services;
import com.receteausersservice.receteausersservice.models.UserModel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserModelDetails implements UserDetails {

    private Long id;
    private String name;
    private String password;
    private String email;
    @CreationTimestamp
    private Date creation_date;
    private boolean authenticated;
    private String registration_code;
    private String password_update_code;
    private String diet;
    private String favorite_recipes;
    private List<GrantedAuthority> authorities;


    public UserModelDetails(UserModel userModel) {
        this.id = userModel.getId();
        this.name = userModel.getName();
        this.password = userModel.getPassword();
        this.email = userModel.getEmail();
        this.creation_date = userModel.getCreation_date();
        this.authenticated = userModel.isAuthenticated();
        this.registration_code = userModel.getRegistration_code();
        this.password_update_code = userModel.getPassword_update_code();
        this.diet = userModel.getDiet();
        this.favorite_recipes = userModel.getFavorite_recipes();
        /**authorities = Arrays.stream(userModel.getRoles().split(","))
         .map(SimpleGrantedAuthority::new)
         .collect(Collectors.toList());
         */
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
