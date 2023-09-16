package csc390.artisanshop.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserProfile {
    private String username;
    private String fullName;
    private String address;
    private String phoneNumber;
}
