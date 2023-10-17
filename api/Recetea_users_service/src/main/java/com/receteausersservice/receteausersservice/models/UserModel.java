package com.receteausersservice.receteausersservice.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.util.Date;


@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
//This ignores this 2 properties on the jsons.
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

}
