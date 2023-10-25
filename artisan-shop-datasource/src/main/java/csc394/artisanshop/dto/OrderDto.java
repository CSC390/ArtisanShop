package csc394.artisanshop.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    @Id
    @GeneratedValue
    private Long orderId;

    @Column(nullable = false)
    private Date orderDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDto user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemDto> items = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private SellerDto seller;
}
