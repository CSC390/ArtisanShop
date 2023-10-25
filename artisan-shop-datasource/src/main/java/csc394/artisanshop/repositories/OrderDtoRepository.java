package csc394.artisanshop.repositories;

import csc394.artisanshop.dto.OrderDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDtoRepository extends JpaRepository<OrderDto, Long> {

}
