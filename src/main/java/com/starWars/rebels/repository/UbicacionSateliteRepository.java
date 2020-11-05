package com.starWars.rebels.repository;

import com.starWars.rebels.domain.UbicacionSatelite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UbicacionSateliteRepository extends JpaRepository<UbicacionSatelite, Long> {


    List<UbicacionSatelite> findByNameIgnoreCaseIn(List<String> listaSatellitesName);

}
