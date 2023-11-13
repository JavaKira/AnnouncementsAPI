package com.github.javakira.ad;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ad")
class AdController {
    private final AdService service;

    @GetMapping("/categories")
    List<AdCategory> categories() {
        return service.categories();
    }

    @GetMapping("/types")
    List<AdType> types() {
        return service.types();
    }

    @GetMapping("/underCategories")
    List<AdUnderCategory> underCategories() {
        return service.underCategories();
    }
}
