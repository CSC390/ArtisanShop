package csc394.artisanshop.repositories;

import csc394.artisanshop.dto.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductDtoRepository extends JpaRepository<ProductDto, Long> {
    List<ProductDto> findBySellerDto_sellerId(Long sellerId);

    @Query("SELECT p FROM ProductDto p WHERE p.id = :id")
    Optional<ProductDto> findById(@Param("id") Long id);
}

