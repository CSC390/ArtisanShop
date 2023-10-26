package csc394.artisanshop.repositories;

import csc394.artisanshop.dto.CategoryDto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryDtoRepository extends JpaRepository<CategoryDto, Long> {
    List<CategoryDto> findByCategoryName(String categoryName);

    @Query("FROM CategoryDto where categoryName=:categoryName")
    String findCategoryNameByCategoryName(@Param("categoryName") String categoryName);

    @Query("select c.categoryName from CategoryDto c")
    List<String> getByCategoryNamelst();

    @Query("SELECT c.id AS id, c.categoryName AS categoryName FROM CategoryDto c")
    List<Object[]> getCategoriesIds();
}
