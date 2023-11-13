package com.github.javakira.ad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdDto {
    private long id;
    private long ownerId;
    private String text;
    private LocalDateTime creationDate;
    private AdType type;
    private AdCategory category;

    static AdDto from(Ad ad) {
        return AdDto
                .builder()
                .id(ad.getId())
                .ownerId(ad.getOwnerId())
                .creationDate(ad.getCreationDate())
                .text(ad.getText())
                .type(ad.getType())
                .category(ad.getCategory())
                .build();
    }
}
