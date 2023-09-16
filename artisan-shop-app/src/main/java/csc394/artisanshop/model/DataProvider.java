package csc394.artisanshop.model;

import org.springframework.stereotype.Service;

@Service
public interface DataProvider {
    UserProfile getUserByUsername(final String username);
}
