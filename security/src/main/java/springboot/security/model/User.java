package springboot.security.model;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

// ORM - Object Relation Mapping

@Data
@Entity
@NoArgsConstructor
public class User {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String email;
    private String role; //ROLE_USER, ROLE_ADMIN
    private String userid;
    private int phone;
    private String provider;
    private int providerId;

    @CreationTimestamp
    private Timestamp createDate;

    @Builder
    public User(String username, String password, String email, String role, String userid, int phone, Timestamp createDate, String provider, int providerId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.userid = userid;
        this.phone = phone;
        this.createDate = createDate;
        this.provider = provider;
        this.providerId = providerId;
    }
}