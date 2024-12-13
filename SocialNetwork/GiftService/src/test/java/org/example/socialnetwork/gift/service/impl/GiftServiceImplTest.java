package org.example.socialnetwork.gift.service.impl;

import org.example.socialnetwork.gift.domain.Gift;
import org.example.socialnetwork.gift.service.GiftService;
import org.example.socialnetwork.profile.service.ProfileService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GiftServiceImplTest {

    @Mock
    private ProfileService profileService;
    private GiftService giftService;


    private static Gift gift1;
    private static Gift gift2;
    private static Gift gift3;

    @BeforeAll
    public static void setUpData() {
        gift1 = new Gift(1L, 1L, 2L, "Gift1");
        gift2 = new Gift(1L, 2L, 1L, "Gift2");
        gift3 = new Gift(1L, 1L, 2L, "Gift3");
    }

    @BeforeEach
    public void init() {
        giftService = new GiftServiceImpl(profileService);
    }

    @Test
    @DisplayName("Test sendGift Ok")
    void givenGift_whenSendGift_thenOk() {
        when(profileService.exists(1L)).thenReturn(true);
        when(profileService.exists(2L)).thenReturn(true);

        giftService.sendGift(gift1);

        verify(profileService, times(2)).exists(anyLong());

        List<Gift> actualGifts = giftService.getReceivedGifts(2L);
        List<Gift> expectedGifts = List.of(gift1);

        assertEquals(expectedGifts.size(), actualGifts.size());
        assertEquals(expectedGifts.get(0), actualGifts.get(0));
    }

    @Test
    @DisplayName("Test sendGift Exception")
    void givenGift_whenSendGift_thenException() {
        when(profileService.exists(1L)).thenReturn(false);
        assertThrows(IllegalArgumentException.class, () -> giftService.sendGift(gift1));
        verify(profileService, times(1)).exists(1L);
    }

    @Test
    @DisplayName("Test getReceivedGifts Ok")
    void givenId_whenGetReceivedGifts_thenOK() {
        when(profileService.exists(1L)).thenReturn(true);
        when(profileService.exists(2L)).thenReturn(true);

        giftService.sendGift(gift1);
        giftService.sendGift(gift2);
        giftService.sendGift(gift3);

        verify(profileService, times(6)).exists(anyLong());

        List<Gift> actualGifts = giftService.getReceivedGifts(2L);
        List<Gift> expectedGifts = List.of(gift1, gift3);

        assertEquals(expectedGifts.size(), actualGifts.size());
        assertTrue(expectedGifts.containsAll(actualGifts));
    }

    @Test
    @DisplayName("Test getReceivedGifts Exception")
    void givenId_whenGetMessages_thenException() {
        when(profileService.exists(2L)).thenReturn(false);
        assertThrows(IllegalArgumentException.class, () -> giftService.getReceivedGifts(2L));
        verify(profileService, times(1)).exists(2L);
    }

}