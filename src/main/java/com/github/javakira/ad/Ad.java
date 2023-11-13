package com.github.javakira.ad;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ad {
    @Id
    private long id;
    private String text;
    private LocalDateTime creationDate;
    @OneToOne
    private AdType type;
    @OneToOne
    private AdCategory category;
    //todo файлы
}
