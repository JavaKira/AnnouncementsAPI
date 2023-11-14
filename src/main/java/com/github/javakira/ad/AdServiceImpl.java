package com.github.javakira.ad;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {
    private final AdRepository repository;
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

    @Override
    public AdDto create(NewAdRequest request, long userId) {
        Ad ad = Ad
                .builder()
                .ownerId(userId)
                .text(request.getText())
                .type(type(request.getType()))
                .category(category(request.getCategory()))
                .creationDate(LocalDateTime.now())
                .build();
        repository.save(ad);
        return AdDto.from(ad);
    }

    @Override
    public List<AdDto> find(FindAdsRequest request) {
        if (request.getStart() < 0 || request.getCount() < 0)
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "'start' and 'count' must be > 0"
            );

        List<Ad> ads = repository.findAll();
        ads = ads
                .stream()
                .filter(ad -> {
                            boolean result = true;
                            if (request.getCategory() != null && !request.getCategory().isEmpty())
                                result = ad.getCategory().getValue().equals(request.getCategory());

                            if (request.getType() != null && !request.getType().isEmpty())
                                result = result && ad.getType().getValue().equals(request.getType());

                            return result;
                        }
                )
                .toList();

        ads = ads.subList(
                Math.min(request.getStart(), ads.size()),
                Math.min(request.getStart() + request.getCount(), ads.size())
        );

        return ads
                .stream()
                .map(AdDto::from)
                .toList();
    }

    @Override
    public AdDto update(PutAdRequest request, long adId, long userId) {
        Ad ad = ad(adId);
        //todo нужно поработать над правами тут
        if (ad.getOwnerId() != userId) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Forbidden to update this ad"
            );
        }

        ad.setText(request.getText());
        ad.setCategory(category(request.getCategory()));
        ad.setType(type(request.getType()));
        repository.save(ad);
        return AdDto.from(ad);
    }

    @Override
    public void delete(long adId, long userId) { //todo нужно мягкое удаление!!!
        Ad ad = ad(adId);
        //todo нужно поработать над правами тут
        if (ad.getOwnerId() != userId) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Forbidden to delete this ad"
            );
        }

        repository.delete(ad);
    }

    //todo можно pageable сделать как общий тип у запросов
    @Override
    public List<AdDto> get(AdsRequest request) {
        if (request.getStart() < 0 || request.getCount() < 0)
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "'start' and 'count' must be > 0"
            );

        List<Ad> ads = repository.findAll();

        ads = ads.subList(
                Math.min(request.getStart(), ads.size()),
                Math.min(request.getStart() + request.getCount(), ads.size())
        );

        return ads
                .stream()
                .map(AdDto::from)
                .toList();
    }

    public Ad ad(long id) {
        Optional<Ad> optional = repository.findById(id);
        if (optional.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Ad with id %d doesnt exist".formatted(id)
            );

        return optional.get();
    }

    public AdCategory category(String value) {
        Optional<AdCategory> optional = adCategoryRepository.findById(value);
        if (optional.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Category '%s' doesnt exist".formatted(value)
            );

        return optional.get();
    }

    public AdType type(String value) {
        Optional<AdType> optional = adTypeRepository.findById(value);
        if (optional.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Type '%s' doesnt exist".formatted(value)
            );

        return optional.get();
    }
}
