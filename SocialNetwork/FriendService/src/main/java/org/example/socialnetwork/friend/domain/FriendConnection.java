package org.example.socialnetwork.friend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendConnection {

    private Long userId;

    private Long friendUserId;

}
