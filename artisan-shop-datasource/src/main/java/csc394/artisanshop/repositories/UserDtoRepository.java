package csc394.artisanshop.repositories;

import csc394.artisanshop.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserDtoRepository extends JpaRepository<UserDto, UUID> { // Assuming username is the primary key
    @Query("SELECT u FROM UserDto u WHERE u.username = ?1")
    Optional<UserDto> findByUsername(String username);

    // Add more query methods as per your requirements.
}