package com.github.javakira.ad;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {
    private final AdCategoryRepository adCategoryRepository;
    private final AdUnderCategoryRepository adUnderCategoryRepository;
    private final AdTypeRepository adTypeRepository;

    @Override
    public List<AdCategory> categories() {
        return adCategoryRepository.findAll();
    }

    @Override
    public List<AdUnderCategory> underCategories() {
        return adUnderCategoryRepository.findAll();
    }

    @Override
    public List<AdType> types() {
        return adTypeRepository.findAll();
    }
}
