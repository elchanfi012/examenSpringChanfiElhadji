package com.humanbooster.examen_spring_elhadji_chanfi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.humanbooster.examen_spring_elhadji_chanfi.models.Advert;

@Repository
public interface AdvertRepository extends CrudRepository<Advert, Long>{
	
	List<Advert> findAll();
	
	
	Optional<Advert> findById(Long id);
}
