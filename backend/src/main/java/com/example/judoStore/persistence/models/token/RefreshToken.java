package com.example.judoStore.persistence.models.token;

import com.example.judoStore.persistence.models.Customer;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "refresh_token")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@Builder
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Customer customer;

    private String token;
}
