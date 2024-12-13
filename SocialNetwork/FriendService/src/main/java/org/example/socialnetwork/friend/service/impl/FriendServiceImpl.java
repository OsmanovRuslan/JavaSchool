package org.example.socialnetwork.friend.service.impl;

import org.example.socialnetwork.friend.service.FriendService;
import org.example.socialnetwork.profile.service.ProfileService;

import java.util.*;

public class FriendServiceImpl implements FriendService {

    private final ProfileService profileService;
    private final Map<Long, Set<Long>> storage = new HashMap<>();

    public FriendServiceImpl(ProfileService profileService) {
        this.profileService = profileService;
    }

    @Override
    public void addFriend(Long userId, Long friendId) {
        if (userId == null || friendId == null) {
            throw new IllegalArgumentException("UserId and friendId cannot be null.");
        }
        if (userId.equals(friendId)) {
            throw new IllegalArgumentException("Cannot add yourself as a friend.");
        }
        if (!profileService.exists(userId) || !profileService.exists(friendId)) {
            throw new IllegalArgumentException("One of the users doesn't exist.");
        }

        storage.computeIfAbsent(userId, k -> new HashSet<>()).add(friendId);
        storage.computeIfAbsent(friendId, k -> new HashSet<>()).add(userId);
    }

    @Override
    public List<Long> getFriends(Long userId) {
        return new ArrayList<>(storage.getOrDefault(userId, Collections.emptySet()));
    }
}
