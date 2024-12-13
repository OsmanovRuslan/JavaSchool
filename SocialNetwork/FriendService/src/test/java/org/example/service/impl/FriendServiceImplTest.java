package org.example.service.impl;

import org.example.socialnetwork.friend.service.FriendService;
import org.example.socialnetwork.friend.service.impl.FriendServiceImpl;
import org.example.socialnetwork.profile.service.ProfileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FriendServiceImplTest {

    @Mock
    private ProfileService profileService;
    private FriendService friendService;

    @BeforeEach
    public void init(){
         friendService = new FriendServiceImpl(profileService);
    }

    @Test
    @DisplayName("Test addFriend Ok")
    void givenIds_whenAddFriend_thenOk() {
        when(profileService.exists(1L)).thenReturn(true);
        when(profileService.exists(2L)).thenReturn(true);

        friendService.addFriend(1L , 2L);

        assertEquals(1, friendService.getFriends(1L).size());
        verify(profileService, times(1)).exists(1L);
        verify(profileService, times(1)).exists(2L);
    }

    @Test
    @DisplayName("Test addFriend Exception")
    void givenIds_whenAddFriend_thenException() {
        when(profileService.exists(1L)).thenReturn(false);
        assertThrows(IllegalArgumentException.class, () -> friendService.addFriend(1L , 2L));
        verify(profileService, times(1)).exists(1L);
    }

    @Test
    @DisplayName("Test getFriends Ok")
    void givenIds_whenGetFriends_thenOk() {
        when(profileService.exists(1L)).thenReturn(true);
        when(profileService.exists(2L)).thenReturn(true);
        when(profileService.exists(3L)).thenReturn(true);

        friendService.addFriend(1L , 2L);
        friendService.addFriend(1L , 3L);

        assertEquals(2, friendService.getFriends(1L).size());
        verify(profileService, times(2)).exists(1L);
        verify(profileService, times(1)).exists(2L);
        verify(profileService, times(1)).exists(3L);
    }

}