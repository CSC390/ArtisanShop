//package csc394.artisanshop.controller;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class ArtisanShopControllerTest {
//
//    @InjectMocks
//    private ArtisanShopController artisanShopController;
//
//    @Mock
//    private DataProvider dataProvider;
//
//    @BeforeEach
//    public void setUp() {
//        // Initialization or common setup, if needed
//    }
//
//    @Test
//    public void testGetUserProfile_Success() {
//
//        final String username = "adam007";
//        final UserProfile mockUserProfile = new UserProfile(username, "Adam Davis", "111 Main Street", "111-222-3333");
//        when(dataProvider.getUserByUsername(username)).thenReturn(mockUserProfile);
//
//        final ResponseEntity<UserProfile> responseEntity = artisanShopController.getUserProfile(username);
//
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(mockUserProfile, responseEntity.getBody());
//    }
//
//    @Test
//    public void testGetUserProfile_UserNotFound() {
//
//        final String username = "nonExistentUser";
//        when(dataProvider.getUserByUsername(username)).thenReturn(null);
//
//        final ResponseEntity<UserProfile> responseEntity = artisanShopController.getUserProfile(username);
//
//        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
//    }
//}
