package csc394.artisanshop.repositories;

import csc394.artisanshop.dto.SellerDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerDtoRepository  extends JpaRepository<SellerDto, Long> {
    Optional<SellerDto> findBySellerName(final String sellerName);

}
