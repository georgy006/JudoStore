package com.example.judoStore.persistence.repository;

import com.example.judoStore.persistence.models.token.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {
    boolean existsByTokenAndCustomerId(String token, Long customerId);

    @Modifying
    @Query("delete from RefreshToken rt where rt.customer.id = :customer_id")
    void deleteByCustomerId(@Param("customer_id") Long customerId);
}
