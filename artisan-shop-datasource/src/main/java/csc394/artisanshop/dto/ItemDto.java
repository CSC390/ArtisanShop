package csc394.artisanshop.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "items")
@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    @Column
    private String description;
    @Column
    private Double price;

    @ManyToOne
    @JoinColumn(name = "category")
    private CategoryDto categoryDto;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private SellerDto sellerDto;
}
