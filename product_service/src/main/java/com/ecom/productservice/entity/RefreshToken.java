package com.ecom.productservice.entity; // Apne package ke hisab se change kar lena

import com.ecom.productservice.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "refresh_tokens")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token; // Yeh wahi lamba random token hoga (jaise UUID)

    @Column(nullable = false)
    private Instant expiryDate; // Token kab expire hoga (e.g., 7 days from now)

    // Production Tip: OneToOne ki jagah ManyToOne zyada behtar hota hai,
    // kyunki ek user alag-alag devices (Phone, Laptop) se login karega toh multiple tokens banenge.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}