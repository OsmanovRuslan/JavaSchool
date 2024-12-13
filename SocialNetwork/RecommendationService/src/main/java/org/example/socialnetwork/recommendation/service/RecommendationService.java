package org.example.socialnetwork.recommendation.service;

import org.example.socialnetwork.recommendation.domain.Recommendation;

import java.util.List;

public interface RecommendationService {

    List<Recommendation> getRecommendations(Long userId);

}
