package org.example.socialnetwork.gift.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Gift {

    private Long giftId;

    private Long fromUserId;

    private Long toUserId;

    private String description;

}
