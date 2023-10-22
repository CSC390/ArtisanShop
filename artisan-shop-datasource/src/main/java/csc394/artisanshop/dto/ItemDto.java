package csc394.artisanshop.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImageDto> images = new ArrayList<>();

    @Column
    private String itemName;

    @Column
    private Integer quantity;

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
