package csc394.artisanshop.controller;

import csc394.artisanshop.model.DataProvider;
import csc394.artisanshop.model.UserProfile;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ArtisanShopController {
    private final DataProvider dataProvider;

    @GetMapping("/{username}")
    public ResponseEntity<UserProfile> getUserProfile(@PathVariable String username) {
        final UserProfile userProfile = dataProvider.getUserByUsername(username);
        if (userProfile == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userProfile);
    }
}
