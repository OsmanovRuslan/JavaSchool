package org.example.socialnetwork.profile.service;

import org.example.socialnetwork.profile.domain.UserProfile;

public interface ProfileService {

    void createProfile(UserProfile profile);

    UserProfile getProfileById(Long userId);

    void updateProfile(UserProfile profile);

    boolean exists(Long userId);

}
