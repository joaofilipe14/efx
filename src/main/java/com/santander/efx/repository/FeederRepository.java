package com.santander.efx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.santander.efx.entity.Feeder;

@Repository
public interface FeederRepository extends JpaRepository<Feeder, Integer>{
	
	@Query("SELECT f FROM FEEDER f WHERE f.currency = ?1")
    List<Feeder> findByCurrency(String currency);
}
