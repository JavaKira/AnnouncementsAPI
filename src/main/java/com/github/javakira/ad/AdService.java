package com.github.javakira.ad;

import java.util.List;

public interface AdService {
    List<AdCategory> categories();

    List<AdUnderCategory> underCategories();

    List<AdType> types();
}
