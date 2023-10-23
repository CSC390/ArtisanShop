package csc394.artisanshop.repositories;

import csc394.artisanshop.dto.ItemDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ItemDtoRepository  extends JpaRepository<ItemDto, Long> {
    List<ItemDto> findBySellerDto_Id(Long sellerId);
    @Query("SELECT i FROM ItemDto i JOIN FETCH i.images WHERE i.id = :itemId")
    Optional<ItemDto> findByIdWithImages(@Param("itemId") Long itemId);

}
