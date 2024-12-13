package org.example.socialnetwork.gift.service;

import org.example.socialnetwork.gift.domain.Gift;

import java.util.List;

public interface GiftService {

    void sendGift(Gift gift);

    List<Gift> getReceivedGifts(Long userId);

}
