package com.humanbooster.examen_spring_elhadji_chanfi.controllers;



import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import com.humanbooster.examen_spring_elhadji_chanfi.models.Advert;
import com.humanbooster.examen_spring_elhadji_chanfi.services.AdvertService;

@Controller
@RequestMapping(path = "/advert")
public class AdvertController {
	
	@Autowired
	private AdvertService advertService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView listAdverts() {
		
		ModelAndView mv = new ModelAndView("advert/list");
		mv.addObject("adverts", advertService.getAllAdverts());
		return mv;
	}
	
	@RequestMapping(value = "/{advert}", method = RequestMethod.GET)
	public ModelAndView detailAdvert(@PathVariable(name = "advert", required = false) Advert advert) {
		
		if(advert == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Advert Not Found");
		}
		
		ModelAndView mv = new ModelAndView("advert/card");
		mv.addObject("advert", advert);
		mv.addObject("export", true);
		mv.addObject("imageLink", advert.getImageLink());
		System.out.println(advert.getImageLink());
		return mv;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addAdvertForm() {
		
		Advert advert = new Advert();
		
		ModelAndView mv = new ModelAndView("advert/form");
		mv.addObject("advert", advert);
		return mv;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addAdvert(@Valid Advert advert, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "advert/form";
		}else {
			advert.setPublishedAt(new Date());
			advertService.saveOrUpdateAdvert(advert);
			return "redirect:/advert/";
		}
	}
	
	
	@RequestMapping(value = "/edit/{advert}", method = RequestMethod.GET)
	public ModelAndView editAdvertForm(@PathVariable(name = "advert", required = false) Advert advert) {
		
		if(advert == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Advert Not Found");
		}
		
		ModelAndView mv = new ModelAndView("advert/form");
		mv.addObject("advert", advert);
		return mv;
	}
	
	@RequestMapping(value = "/edit/{advert}", method = RequestMethod.POST)
	public String editAdvert(@Valid Advert advert, BindingResult bindingResult) {
		
		if(advert == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Advert Not Found");
		}
		
		if(bindingResult.hasErrors()) {
			return "advert/form";
		}else {
			advertService.saveOrUpdateAdvert(advert);
			return "redirect:/advert/";
		}
	}
	
	@RequestMapping(value = "delete/{advert}", method = RequestMethod.GET)
	public String deleteAdvert(@PathVariable(name = "advert", required = false) Advert advert) {
		
		if(advert == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Advert Not Found");
		}
		advertService.deleteAdvert(advert);
		return "redirect:/advert/";
	}
}
