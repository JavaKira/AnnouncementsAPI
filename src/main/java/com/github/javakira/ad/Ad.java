package com.github.javakira.ad;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private long ownerId;
    private String text;
    private LocalDateTime creationDate;
    @ManyToOne
    private AdType type;
    @ManyToOne
    private AdCategory category;
    //todo файлы
}
