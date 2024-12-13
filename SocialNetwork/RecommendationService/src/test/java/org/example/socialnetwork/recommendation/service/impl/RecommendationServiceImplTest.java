package org.example.socialnetwork.recommendation.service.impl;

import org.example.socialnetwork.friend.service.FriendService;
import org.example.socialnetwork.profile.service.ProfileService;
import org.example.socialnetwork.recommendation.domain.Recommendation;
import org.example.socialnetwork.recommendation.service.RecommendationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecommendationServiceImplTest {

    @Mock
    private ProfileService profileService;

    @Mock
    private FriendService friendService;

    private RecommendationService recommendationService;

    @BeforeEach
    public void init() {
        recommendationService = new RecommendationServiceImpl(profileService, friendService);
    }

    @Test
    @DisplayName("Test getRecommendations Ok")
    void givenIds_whenGetRecommendations_thenOk() {
        when(profileService.exists(1L)).thenReturn(true);

        when(friendService.getFriends(1L)).thenReturn(List.of(2L, 3L));
        when(friendService.getFriends(2L)).thenReturn(List.of(1L, 4L));
        when(friendService.getFriends(3L)).thenReturn(List.of(1L, 4L, 5L));

        List<Recommendation> recommendations = recommendationService.getRecommendations(1L);

        assertEquals(2, recommendations.size());

        List<Long> expectedRecIds = List.of(4L, 5L);
        List<Long> actualRecIds = recommendations.stream().map(Recommendation::getRecommendedUserId).toList();

        assertTrue(expectedRecIds.containsAll(actualRecIds));
        assertEquals(expectedRecIds.size(), actualRecIds.size());
        verify(profileService, times(1)).exists(1L);
        verify(friendService, times(3)).getFriends(anyLong());
    }

    @Test
    @DisplayName("Test getRecommendations Exception")
    void givenIds_whenGetRecommendations_thenException() {
        when(profileService.exists(1L)).thenReturn(false);
        assertThrows(IllegalArgumentException.class, () -> recommendationService.getRecommendations(1L));
        verify(profileService, times(1)).exists(1L);
    }

}