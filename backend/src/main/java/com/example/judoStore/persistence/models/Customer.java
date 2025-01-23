package com.example.judoStore.persistence.models;

import com.example.judoStore.persistence.models.token.BlacklistedToken;
import com.example.judoStore.persistence.models.token.RefreshToken;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "customers")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class Customer implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String city;
    private String phoneNumber;
    private String password;
    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Order> orders;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, optional = false)
    @JsonIgnore
    private RefreshToken refreshToken;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<BlacklistedToken> blacklistedToken;

    @OneToOne(mappedBy = "customer")
    @JsonIgnore
    private Cart cart;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }
}
