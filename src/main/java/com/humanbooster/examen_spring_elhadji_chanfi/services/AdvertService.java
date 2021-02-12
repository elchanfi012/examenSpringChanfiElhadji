package com.humanbooster.examen_spring_elhadji_chanfi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanbooster.examen_spring_elhadji_chanfi.models.Advert;
import com.humanbooster.examen_spring_elhadji_chanfi.repositories.AdvertRepository;

@Service
public class AdvertService {

	@Autowired
	private AdvertRepository advertRepository;
	
	public List<Advert> getAllAdverts() {
		return advertRepository.findAll();
	}
	
	public Optional<Advert> getAdvert(Long id) {
		return advertRepository.findById(id);
	}
	
	public void saveOrUpdateAdvert(Advert advert) {
		advertRepository.save(advert);
	}
	
	public void deleteAdvert(Advert advert) {
		advertRepository.delete(advert);
	}
}
