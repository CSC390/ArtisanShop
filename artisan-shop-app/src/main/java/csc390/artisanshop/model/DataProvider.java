package csc390.artisanshop.model;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public interface DataProvider {
    UserProfile getUserByUsername(final String username);
}
