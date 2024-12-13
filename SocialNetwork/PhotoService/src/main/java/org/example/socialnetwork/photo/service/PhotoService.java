package org.example.socialnetwork.photo.service;

import org.example.socialnetwork.photo.domain.Photo;

import java.util.List;

public interface PhotoService {

    void uploadPhoto(Photo photo);

    List<Photo> getPhotosByUserId(Long userId);

}
