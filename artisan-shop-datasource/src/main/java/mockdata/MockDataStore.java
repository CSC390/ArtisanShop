package mockdata;

import csc390.artisanshop.model.DataProvider;
import csc390.artisanshop.model.UserProfile;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MockDataStore implements DataProvider {
    private final Map<String, UserProfile> users = new HashMap<>();

    public MockDataStore() {
        // Initialize with some sample user profiles
        users.put("adam007", new UserProfile("adam007", "Adam Davis", "111 Main Street", "111-222-3333"));
        users.put("meera111", new UserProfile("meera111", "Meera Smith", "34 Some Street", "887-3456-9898"));
    }

    @Override
    public UserProfile getUserByUsername(String username) {
        // Retrieve user profile by username
        return users.get(username);
    }
}
