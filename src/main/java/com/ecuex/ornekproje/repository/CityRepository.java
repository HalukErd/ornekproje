package com.ecuex.ornekproje.repository;

import com.ecuex.ornekproje.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {
    @Query("SELECT s.id FROM City s WHERE s.name = ?1")
    Optional<Long> findCityByName(String name);


}
