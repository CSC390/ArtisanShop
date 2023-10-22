package csc394.artisanshop.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "images")
@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String url;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private ItemDto item;
}
