package com.github.javakira.ad;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdTypeRepository extends JpaRepository<AdType, String> {
}
