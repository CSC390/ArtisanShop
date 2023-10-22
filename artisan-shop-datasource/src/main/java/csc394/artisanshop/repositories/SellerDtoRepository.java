package csc394.artisanshop.repositories;

import csc394.artisanshop.dto.SellerDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SellerDtoRepository  extends JpaRepository<SellerDto, UUID> {
    Optional<SellerDto> findBySellerName(final String sellerName);

}
