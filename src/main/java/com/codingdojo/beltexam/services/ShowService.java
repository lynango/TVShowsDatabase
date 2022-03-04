package com.codingdojo.beltexam.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.beltexam.models.Show;
import com.codingdojo.beltexam.models.User;
import com.codingdojo.beltexam.repositories.ShowRepository;
import com.codingdojo.beltexam.repositories.UserRepository;

@Service
public class ShowService {
	
	private final ShowRepository showRepo;
	private final UserRepository userRepo;
	public ShowService(ShowRepository showRepo, UserRepository userRepo) {
		this.showRepo = showRepo;
		this.userRepo = userRepo;
	}
	
//	Retrieves all the shows 
		
	public ArrayList<Show> findAll() {
		return (ArrayList<Show>) showRepo.findAll();
	}	
	
//	Retrieves a specific show

	public Show findShowById(Long id) {
		Optional<Show> thisShow=showRepo.findById(id);
		if (thisShow.isPresent()) {
			return thisShow.get();
		}
		return null;
	}
	
//	Allows a new show to be created and saved

	public Show createShow(Show show) {
		return showRepo.save(show);
	}	
	
//	Allows a show to be edited and saved

	public Show updateShow(Show show) {
		Optional<Show> isShow=showRepo.findById(show.getId());
		if (isShow.isPresent()) {
			Show edit=isShow.get();
			edit.setTitle(show.getTitle());
			edit.setNetwork(show.getNetwork());
			edit.setDescription(show.getDescription());
			showRepo.save(edit);
			return edit;
		}
		else return null;
	}
	
	
//	Deletes a show

	public void deleteShow(Long id) {
		showRepo.deleteById(id);
	 }
}
