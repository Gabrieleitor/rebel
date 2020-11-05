package com.starWars.rebels.repository;

import com.starWars.rebels.domain.InformacionSatelite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InformacionSateliteRepository extends JpaRepository<InformacionSatelite, Long> {

    List<InformacionSatelite> findAllByGeneradoIsFalse();
}
