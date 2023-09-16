package csc394.artisanshop.model;

import lombok.*;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
@Getter
@Setter
public class UserProfile {
    private String username;
    private String fullName;
    private String address;
    private String phoneNumber;
}
