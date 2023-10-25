package csc394.artisanshop.repositories;

import csc394.artisanshop.dto.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDtoRepository extends JpaRepository<ProductDto, Long> {
    List<ProductDto> findBySellerDto_Id(Long sellerId);
//    @Query("SELECT p FROM ProductDto p JOIN FETCH p.images WHERE p.id = :productId")
//    Optional<ProductDto> findByIdWithImages(@Param("productId") Long productId);
}
