package csc394.artisanshop.repositories;

import csc394.artisanshop.dto.ShoppingCartDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCartDto, Long> {
    List<ShoppingCartDto> findByUser_UserId(Long userId);

}
