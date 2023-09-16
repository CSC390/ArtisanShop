package csc390.artisanshop.controller;

import csc390.artisanshop.model.DataProvider;
import csc390.artisanshop.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class ArtisanShopController {
    private final DataProvider dataProvider;

    @Autowired
    public ArtisanShopController(final DataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserProfile> getUserProfile(@PathVariable String username) {
        final UserProfile userProfile = dataProvider.getUserByUsername(username);
        if (userProfile == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userProfile);
    }
}
