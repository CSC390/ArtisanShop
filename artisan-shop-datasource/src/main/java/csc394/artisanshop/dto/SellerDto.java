package csc394.artisanshop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sellers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String sellerName;

    @Column(unique = true, nullable = false)
    private String sellerEmail;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @OneToMany(mappedBy = "sellerDto")
    @JsonIgnore
    private List<ItemDto> items = new ArrayList<>();
}
