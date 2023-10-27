package csc394.artisanshop.repositories;

import csc394.artisanshop.dto.CartItemDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemDtoRepository extends JpaRepository<CartItemDto, Long> {
    // You can add custom query methods here if needed
}
