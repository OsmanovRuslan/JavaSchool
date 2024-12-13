package org.example.service.impl;

import org.example.socialnetwork.profile.domain.UserProfile;
import org.example.socialnetwork.profile.service.ProfileService;
import org.example.socialnetwork.profile.service.impl.ProfileServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfileServiceImplTest {

    private ProfileService profileService;

    private static UserProfile profile;

    @BeforeAll
    public static void setUpData() {
        profile = new UserProfile(1L, "Ivan", "pochta@mail.ru");
    }

    @BeforeEach
    public void init() {
        profileService = new ProfileServiceImpl();
    }

    @Test
    @DisplayName("Test createProfile Ok")
    public void givenUserProfile_whenCreateProfile_thenOk() {
        profileService.createProfile(profile);

        assertTrue(profileService.exists(1L));
        UserProfile existedProfile = profileService.getProfileById(1L);
        assertNotNull(existedProfile);
        assertEquals(profile, existedProfile);
    }

    @Test
    @DisplayName("Test createProfile Exception")
    public void givenUserProfile_whenCreateProfileDuplicate_thenException() {
        profileService.createProfile(profile);
        assertThrows(IllegalArgumentException.class, () -> profileService.createProfile(profile));
    }

    @Test
    @DisplayName("Test getProfileById Ok")
    void givenId_whenGetProfileById_thenOk() {
        profileService.createProfile(profile);
        assertEquals(profile, profileService.getProfileById(profile.getUserId()));
    }

    @Test
    @DisplayName("Test getProfileById Null")
    void givenId_whenGetProfileById_thenNull() {
        assertNull(profileService.getProfileById(profile.getUserId()));
    }

    @Test
    @DisplayName("Test updateProfile Ok")
    void givenUserProfile_whenUpdateProfile_thenOk() {
        UserProfile newProfile = new UserProfile(1L, "Igor", "pochta@mail.ru");
        profileService.createProfile(profile);
        profileService.updateProfile(newProfile);
        UserProfile updatedProfile = profileService.getProfileById(1L);
        assertEquals("Igor", updatedProfile.getName());
    }

    @Test
    @DisplayName("Test updateProfile Exception")
    void givenUserProfile_whenUpdateProfile_thenException() {
        assertThrows(IllegalArgumentException.class, () -> profileService.updateProfile(profile));
    }

    @Test
    @DisplayName("Test exist True")
    void givenId_whenExists_thenTrue() {
        profileService.createProfile(profile);
        assertTrue(profileService.exists(profile.getUserId()));
    }

    @Test
    @DisplayName("Test exist False")
    void givenId_whenExists_thenFalse() {
        assertFalse(profileService.exists(profile.getUserId()));
    }
}