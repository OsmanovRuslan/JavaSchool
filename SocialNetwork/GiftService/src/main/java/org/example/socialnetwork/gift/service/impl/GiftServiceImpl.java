package org.example.socialnetwork.gift.service.impl;

import org.example.socialnetwork.gift.domain.Gift;
import org.example.socialnetwork.gift.service.GiftService;
import org.example.socialnetwork.profile.service.ProfileService;

import java.util.*;

public class GiftServiceImpl implements GiftService {

    private final ProfileService profileService;
    private final Map<Long, List<Gift>> storage = new HashMap<>();

    public GiftServiceImpl(ProfileService profileService) {
        this.profileService = profileService;
    }

    @Override
    public void sendGift(Gift gift) {
        if (gift == null || gift.getGiftId() == null || gift.getFromUserId() == null || gift.getToUserId() == null) {
            throw new IllegalArgumentException("Invalid gift: giftId, fromUserId, and toUserId are required.");
        }
        if (!profileService.exists(gift.getFromUserId()) || !profileService.exists(gift.getToUserId())) {
            throw new IllegalArgumentException("Sender or receiver does not exist.");
        }

        storage.computeIfAbsent(gift.getToUserId(), k -> new ArrayList<>()).add(gift);
    }

    @Override
    public List<Gift> getReceivedGifts(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("userId cannot be null.");
        }
        if (!profileService.exists(userId)) {
            throw new IllegalArgumentException("User does not exist.");
        }
        return storage.getOrDefault(userId, Collections.emptyList());
    }
}
