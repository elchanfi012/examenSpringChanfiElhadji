package com.humanbooster.examen_spring_elhadji_chanfi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.humanbooster.examen_spring_elhadji_chanfi.repositories.AdvertRepository;
import com.humanbooster.examen_spring_elhadji_chanfi.models.Advert;



@SpringBootApplication
public class ExamenSpringElhadjiChanfiApplication {
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(ExamenSpringElhadjiChanfiApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(ExamenSpringElhadjiChanfiApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(AdvertRepository advertRepository) {
		return (args) ->{
			if(advertRepository.count() == 0) {
				
				log.info("Ici, j'ajoute 5 annonces.");
				
				List<Advert> adverts = new ArrayList<Advert>();
				
				String imageLink = "https://d1fmx1rbmqrxrr.cloudfront.net/cnet/optim/i/edit/2019/04/eso1644bsmall__w770.jpg";
				
				adverts.add(new Advert("annonce 1", imageLink, "Le contenu de l'annonce 1", new Date()));
				adverts.add(new Advert("annonce 2", imageLink, "Le contenu de l'annonce 2", new Date()));
				adverts.add(new Advert("annonce 3", imageLink, "Le contenu de l'annonce 3", new Date()));
				adverts.add(new Advert("annonce 4", imageLink, "Le contenu de l'annonce 4", new Date()));
				adverts.add(new Advert("annonce 5", imageLink, "Le contenu de l'annonce 4", new Date()));
				
				advertRepository.saveAll(adverts);
			}
		};
	}
}
