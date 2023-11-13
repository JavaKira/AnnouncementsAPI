package com.github.javakira.ad;

import java.util.List;

public interface AdService {
    List<AdCategory> categories();

    List<AdUnderCategory> underCategories();

    List<AdType> types();

    AdDto create(NewAdRequest request, long userId);

    List<AdDto> find(AdsRequest request);

    AdDto update(PutAdRequest putAdRequest, long adId, long id);

    void delete(long adId, long id);
}
