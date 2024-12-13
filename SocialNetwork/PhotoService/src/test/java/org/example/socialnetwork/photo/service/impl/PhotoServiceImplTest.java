package org.example.socialnetwork.photo.service.impl;

import org.example.socialnetwork.photo.domain.Photo;
import org.example.socialnetwork.photo.service.PhotoService;
import org.example.socialnetwork.profile.service.ProfileService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PhotoServiceImplTest {

    @Mock
    private ProfileService profileService;
    private PhotoService photoService;

    private static Photo photo1;
    private static Photo photo2;
    private static Photo photo3;

    @BeforeAll
    public static void setUpData() {
        photo1 = new Photo(1L, 1L, "url1", "description1");
        photo2 = new Photo(2L, 1L, "url2", "description2");
        photo3 = new Photo(3L, 1L, "url3", "description3");
    }

    @BeforeEach
    public void init() {
        photoService = new PhotoServiceImpl(profileService);
    }

    @Test
    @DisplayName("Test uploadPhoto Ok")
    void givenPhoto_whenUploadPhoto_thenOk() {
        when(profileService.exists(1L)).thenReturn(true);
        photoService.uploadPhoto(photo1);
        List<Photo> photos = photoService.getPhotosByUserId(1L);

        assertEquals(1, photos.size());
        assertEquals(photo1, photos.get(0));
        verify(profileService, times(1)).exists(1L);
    }

    @Test
    @DisplayName("Test uploadPhoto Exception")
    void givenPhoto_whenUploadPhoto_thenException() {
        when(profileService.exists(1L)).thenReturn(false);
        assertThrows(IllegalArgumentException.class, () -> photoService.uploadPhoto(photo1));
        verify(profileService, times(1)).exists(1L);
    }

    @Test
    @DisplayName("Test getPhotosByUserId Ok")
    void givenPhotos_whenGetPhotosByUserId_thenOk() {
        when(profileService.exists(1L)).thenReturn(true);

        photoService.uploadPhoto(photo1);
        photoService.uploadPhoto(photo2);
        photoService.uploadPhoto(photo3);

        List<Photo> actualPhotos = photoService.getPhotosByUserId(1L);
        List<Photo> expectedPhoto = List.of(photo1, photo2, photo3);

        assertTrue(expectedPhoto.containsAll(actualPhotos));
        assertEquals(expectedPhoto.size(), actualPhotos.size());
        verify(profileService, times(3)).exists(1L);
    }
}