package csc394.artisanshop.repositories;

import csc394.artisanshop.dto.CartItemDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemDtoRepository extends JpaRepository<CartItemDto, Long> {
    List<CartItemDto> findByProductId(Long productId);
}
