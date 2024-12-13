package org.example.socialnetwork.profile.domain;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {

    private Long userId;

    private String name;

    private String email;

}
