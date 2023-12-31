package csc394.artisanshop.repositories;

import csc394.artisanshop.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface UserDtoRepository extends JpaRepository<UserDto, Long> {
    @Query("SELECT u FROM UserDto u WHERE u.username = ?1")
    Optional<UserDto> findByUsername(final String username);
}
