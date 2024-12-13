package org.example.socialnetwork.friend.service;

import java.util.List;

public interface FriendService {

    void addFriend(Long userId, Long friendId);

    List<Long> getFriends(Long userId);

}
