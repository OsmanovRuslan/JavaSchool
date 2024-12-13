package org.example.socialnetwork.photo.service.impl;

import org.example.socialnetwork.photo.domain.Photo;
import org.example.socialnetwork.photo.service.PhotoService;
import org.example.socialnetwork.profile.service.ProfileService;

import java.util.*;

public class PhotoServiceImpl implements PhotoService {

    private final ProfileService profileService;

    private final Map<Long, List<Photo>> storage = new HashMap<>();

    public PhotoServiceImpl(ProfileService profileService) {
        this.profileService = profileService;
    }

    @Override
    public void uploadPhoto(Photo photo) {
        if (photo == null || photo.getUserId() == null || photo.getPhotoId() == null) {
            throw new IllegalArgumentException("Photo and photoId/userId cannot be null.");
        }
        if (!profileService.exists(photo.getUserId())) {
            throw new IllegalArgumentException("User doesn't exist: " + photo.getUserId());
        }
        storage.computeIfAbsent(photo.getUserId(), k -> new ArrayList<>()).add(photo);
    }

    @Override
    public List<Photo> getPhotosByUserId(Long userId) {
        return storage.getOrDefault(userId, Collections.emptyList());
    }
}
