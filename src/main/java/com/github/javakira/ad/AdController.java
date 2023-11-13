package com.github.javakira.ad;

import com.github.javakira.jwt.JwtService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ad")
class AdController {
    private final AdService service;
    private final JwtService jwtService;

    @PostMapping
    @SecurityRequirement(name = "Bearer Authentication")
    AdDto create(@RequestBody NewAdRequest newAdRequest, HttpServletRequest request) {
        long id = jwtService.extractId(request);
        return service.create(newAdRequest, id);
    }

    @PutMapping("/{adId}")
    @SecurityRequirement(name = "Bearer Authentication")
    AdDto update(@RequestBody PutAdRequest putAdRequest, HttpServletRequest request, @PathVariable long adId) {
        long id = jwtService.extractId(request);
        return service.update(putAdRequest, adId, id);
    }

    @DeleteMapping("/{adId}")
    @SecurityRequirement(name = "Bearer Authentication")
    void delete(HttpServletRequest request, @PathVariable long adId) {
        long id = jwtService.extractId(request);
        service.delete(adId, id);
    }

    @GetMapping
    List<AdDto> find(AdsRequest request) { //todo шляпа с русским в query
        return service.find(request);
    }

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
