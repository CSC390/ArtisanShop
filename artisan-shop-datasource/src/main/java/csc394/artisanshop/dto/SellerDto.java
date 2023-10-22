package csc394.artisanshop.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sellers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerDto {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String sellerName;

    @Column(unique = true, nullable = false)
    private String sellerEmail;

    @Column
    private String firstName;

    @Column
    private String lastName;
}
