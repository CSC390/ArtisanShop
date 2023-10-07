package csc394.artisanshop.repository;

import csc394.artisanshop.model.ConfirmedOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmedOrderRepository extends JpaRepository<ConfirmedOrder, Integer> {
    ConfirmedOrder findConfirmedOrderByOrderNumber(Long orderNumber);
}
