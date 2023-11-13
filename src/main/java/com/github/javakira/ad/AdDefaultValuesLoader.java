package com.github.javakira.ad;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdDefaultValuesLoader {
    public AdDefaultValuesLoader(
            AdCategoryRepository adCategoryRepository,
            AdUnderCategoryRepository adUnderCategoryRepository,
            AdTypeRepository adTypeRepository
    ) {
        List<AdType> adTypes = List.of(
                new AdType("куплю"),
                new AdType("продам"),
                new AdType("меняю"),
                new AdType("найдено"),
                new AdType("пропажа"),
                new AdType("сдам в аренду")
        );
        adTypeRepository.saveAll(adTypes);

        List<AdCategory> adCategories = List.of(
                new AdCategory("авто"),
                new AdCategory("недвижимость"),
                new AdCategory("бытовая техника")
        );
        adCategoryRepository.saveAll(adCategories);

        List<AdUnderCategory> adUnderCategories = List.of(
                new AdUnderCategory("дом", adCategoryRepository.getReferenceById("недвижимость")),
                new AdUnderCategory("дача", adCategoryRepository.getReferenceById("недвижимость")),
                new AdUnderCategory("иномарки", adCategoryRepository.getReferenceById("авто")),
                new AdUnderCategory("отечественные автомобили", adCategoryRepository.getReferenceById("авто"))
        );
        adUnderCategoryRepository.saveAll(adUnderCategories);
    }
}
