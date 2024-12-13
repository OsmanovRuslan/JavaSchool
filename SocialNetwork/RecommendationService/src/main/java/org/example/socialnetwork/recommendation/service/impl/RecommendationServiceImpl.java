package org.example.socialnetwork.recommendation.service.impl;

import org.example.socialnetwork.friend.service.FriendService;
import org.example.socialnetwork.profile.service.ProfileService;
import org.example.socialnetwork.recommendation.domain.Recommendation;
import org.example.socialnetwork.recommendation.service.RecommendationService;

import java.util.ArrayList;
import java.util.List;

public class RecommendationServiceImpl implements RecommendationService {

    private final ProfileService profileService;
    private final FriendService friendService;

    public RecommendationServiceImpl(ProfileService profileService, FriendService friendService) {
        this.profileService = profileService;
        this.friendService = friendService;
    }

    @Override
    public List<Recommendation> getRecommendations(Long userId) {
        if (userId == null || !profileService.exists(userId)) {
            throw new IllegalArgumentException("User profile not found.");
        }

        List<Long> friends = friendService.getFriends(userId);
        List<Long> recommendations = new ArrayList<>();
        for (Long friendId : friends) {
            List<Long> friendsOfFriend = friendService.getFriends(friendId);
            for (Long fofId : friendsOfFriend) {
                if (!fofId.equals(userId) && !friends.contains(fofId) && !recommendations.contains(fofId)) {
                    recommendations.add(fofId);
                }
            }
        }

        return recommendations.stream()
                .map(recId -> new Recommendation(userId, recId))
                .toList();
    }
}
