package com.japan.demo.repository;

import com.japan.demo.model.JapaneseCity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface JapaneseCityRepository extends JpaRepository<JapaneseCity, Long> {

    JapaneseCity findByName(String name);

    @Query(value = "SELECT name from japanese_city where name LIKE %?1%", nativeQuery = true)
    List<String> autoCompleteByName(String name);

    @Query(value = "SELECT id, name from japanese_city", nativeQuery = true)
    List<JapaneseCitySelect> getIdAndName();

    interface JapaneseCitySelect {
       Long getId();
       String getName();

    }

    Set<JapaneseCity> findByIdIn(List<Long> cityId);
}
