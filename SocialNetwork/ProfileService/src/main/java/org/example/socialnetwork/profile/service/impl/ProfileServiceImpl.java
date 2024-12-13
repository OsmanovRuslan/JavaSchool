package org.example.socialnetwork.profile.service.impl;

import org.example.socialnetwork.profile.domain.UserProfile;
import org.example.socialnetwork.profile.service.ProfileService;

import java.util.HashMap;
import java.util.Map;

public class ProfileServiceImpl implements ProfileService {

    private final Map<Long, UserProfile> storage = new HashMap<>();

    @Override
    public void createProfile(UserProfile profile) {
        if (profile == null || profile.getUserId() == null) {
            throw new IllegalArgumentException("Invalid user profile: userId is required.");
        }
        if (storage.containsKey(profile.getUserId())) {
            throw new IllegalArgumentException("User with id: " + profile.getUserId() + " already exists.");
        }
        storage.put(profile.getUserId(), profile);
    }

    @Override
    public UserProfile getProfileById(Long userId) {
        return storage.get(userId);
    }

    @Override
    public void updateProfile(UserProfile profile) {
        if (profile == null || profile.getUserId() == null || !storage.containsKey(profile.getUserId())) {
            throw new IllegalArgumentException("User profile not found: " + (profile != null ? profile.getUserId() : "null"));
        }
        storage.put(profile.getUserId(), profile);
    }

    @Override
    public boolean exists(Long userId) {
        return storage.containsKey(userId);
    }
}
