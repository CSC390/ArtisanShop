package csc394.artisanshop.repositories;

import csc394.artisanshop.dto.CategoryDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDtoRepository extends JpaRepository<CategoryDto, Long> {
}
