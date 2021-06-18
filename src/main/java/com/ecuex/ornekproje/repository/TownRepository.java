package com.ecuex.ornekproje.repository;

import com.ecuex.ornekproje.model.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TownRepository extends JpaRepository<Town, Long> {
//    @Query(value = "SELECT * FROM town WHERE city_id = :city_id",
//            nativeQuery = true
//    )
//    List<Town> getTownsByCityId(
//            @Param("city_id") Long city_id);

    @Query("SELECT t.name FROM Town t WHERE city_id = ?1")
    Optional<List<String>> findTownsByCityId(Long cityId);

//    Optional<List<Town>> findByCity_Id(Long cityId);
//
//    Optional<List<Town>> findAllByCity_Id(Long cityId);
}