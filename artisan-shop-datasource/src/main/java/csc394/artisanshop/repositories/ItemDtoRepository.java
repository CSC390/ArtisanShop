package csc394.artisanshop.repositories;

import csc394.artisanshop.dto.ItemDto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ItemDtoRepository  extends JpaRepository<ItemDto, Long> {
    List<ItemDto> findBySellerDto_Id(Long sellerId);
}
