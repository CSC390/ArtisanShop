package csc394.artisanshop.repositories;

import csc394.artisanshop.dto.SellerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SellerDtoRepository  extends JpaRepository<SellerDto, Long> {
    Optional<SellerDto> findBySellerName(final String sellerName);

    @Query("SELECT s FROM SellerDto s WHERE s.sellerId = :sellerId")
    Optional<SellerDto> findBySellerId(@Param("sellerId") Long sellerId);

}
