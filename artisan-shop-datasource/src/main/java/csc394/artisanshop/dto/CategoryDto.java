package csc394.artisanshop.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String categoryName;

//    @OneToMany(mappedBy = "categoryDto")
//    private List<ItemDto> items;

    @OneToMany(mappedBy = "categoryDto")
    private List<ItemDto> items = new ArrayList<>();

}
