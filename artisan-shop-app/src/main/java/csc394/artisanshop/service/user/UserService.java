package csc394.artisanshop.service.user;

import csc394.artisanshop.dto.viewDto.UserViewDto;
import csc394.artisanshop.model.User;
// import csc394.artisanshop.request.UserDeleteRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    User add(User createDto);

    List<User> getAll();

    User getById(int id);

    List<User> slice(Pageable pageable);

    void deleteById(int id);

    List<UserViewDto> getUserViewDto();

    User getByUserName(String userName);

    // void authDeleteByUser(UserDeleteRequest userDeleteRequest);

    User findByEMail(String eMail);

    void updateByUserName(int userId, String userName);

    void updateByNotificationPermission(int userId, boolean permission);
}
