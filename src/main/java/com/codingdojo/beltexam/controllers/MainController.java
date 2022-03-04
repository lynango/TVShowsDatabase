package com.codingdojo.beltexam.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingdojo.beltexam.models.CurrentUser;
import com.codingdojo.beltexam.models.Rating;
import com.codingdojo.beltexam.models.Show;
import com.codingdojo.beltexam.models.User;
import com.codingdojo.beltexam.services.ShowService;
import com.codingdojo.beltexam.services.RatingService;
import com.codingdojo.beltexam.services.UserService;

@Controller
public class MainController {
	
	@Autowired
	UserService userService;
	@Autowired
	ShowService showService;
	@Autowired
	RatingService ratingService;
	
// Registration, Login, and Logout
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new CurrentUser());
		return "index.jsp";
	}
		
	@PostMapping("/registration")
	public String register(
			@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model, HttpSession session) {	
		
		User user= userService.register(newUser,result);
		if (user==null) {
			model.addAttribute("newLogin", new CurrentUser());
			return "index.jsp";
		}
		if (result.hasErrors()) {
			model.addAttribute("newLogin", new CurrentUser());
			return "index.jsp";
		}		
		session.setAttribute("currentUser", user);
		session.setAttribute("currentID", user.getId());
		return "redirect:/shows";
	}
	
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") CurrentUser newLogin, BindingResult result, Model model, HttpSession session) {
				
		User user=userService.login(newLogin,result);
		
		if (user==null || result.hasErrors()) {
			model.addAttribute("newUser",new User());
			model.addAttribute("errorMessage", "Invalid Credentials, Please try again.");
			return "index.jsp";		
		}
		session.setAttribute("currentUser", user);
		session.setAttribute("currentID", user.getId());
		return "redirect:/shows";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
		
	}

//	Routes the user to the dashboard page
	
	@GetMapping("/shows")
	public String dashboard(@ModelAttribute("show") Show show, Model model, HttpSession session) {
		if(session.getAttribute("currentUser")==null) {
			return "redirect:/";
		}		
		List<Show> shows = showService.findAll();
		model.addAttribute("shows", shows);		
		return "dashboard.jsp";
	}
	
//	Routes the user to a certain show page

	@GetMapping("/shows/{id}")
	public String show(@PathVariable ("id") Long id, @Valid @ModelAttribute("rating") Rating rating, BindingResult result, Model model, HttpSession session) {		
		if(session.getAttribute("currentUser")==null) {
			return "redirect:/";
		}
		User user = userService.findUserById(id);
		Show show = showService.findShowById(id);
		List<Rating> ratings = show.getRatings();
		
		model.addAttribute("thisShow",showService.findShowById(id));
		model.addAttribute("user", user);
		model.addAttribute("ratings", ratings); 
		 
		return "viewShow.jsp";
	}

//	Routes the user to the new show page
	
	@GetMapping("/shows/new")
	public String newShow(@ModelAttribute("show") Show show, Model model, @ModelAttribute("user") User user, HttpSession session) {
		if(session.getAttribute("currentUser")==null) {
			return "redirect:/";
		}
		else return "newShow.jsp";
	}	
	
//	Process the user's request to add a new show

	@PostMapping("/addshow")
	public String addShow(@Valid @ModelAttribute("show") Show show, BindingResult result) {
		if(result.hasErrors()) {
			return "newShow.jsp";
		}
		ArrayList<Show> shows = showService.findAll();
		for(Show uniqueShow: shows) {
			if(uniqueShow.getTitle().equals(show.getTitle())) {
				return "redirect:/shows/new";
			}
		}
		showService.createShow(show);
		return "redirect:/shows";
	}	
	
//	Routes the user to the edit show page

	@GetMapping("/shows/{id}/edit")
	public String editShow(@PathVariable("id") Long id, Model model, HttpSession session) {
		Show check=showService.findShowById(id);
		if (check!=null) {
			if (check.getCreator().getId() != session.getAttribute("currentID")) {
				return "redirect:/shows";
			}
				model.addAttribute("show",check);
				model.addAttribute("object",showService.findShowById(id).getTitle());
				return "editShow.jsp";
			}
		else return "redirect:/shows";		
	}	
	
//	Process the user's request to edit the show

	@PutMapping("/shows/{id}/update")
	public String updateShow(@PathVariable ("id") Long id, @Valid @ModelAttribute("show") Show show, BindingResult result, Model model) {		
		if (result.hasErrors()) {
			model.addAttribute("object",showService.findShowById(id).getTitle());
			return "editShow.jsp";
		}
		ArrayList<Show> shows = showService.findAll();
		for(Show uniqueShow: shows) {
			if(uniqueShow.getTitle().equals(show.getTitle())) {
				return "redirect:/shows/edit";
			}	
		}
		showService.updateShow(show);
		return "redirect:/shows";				
	}
	
//	Process the user's request to add a new rating

	@PostMapping("/shows/{ratingAdded}")
	public String addRating(@PathVariable ("ratingAdded") Long id, @Valid @ModelAttribute("rating") Rating rating, BindingResult result, Model model) {	
		if (result.hasErrors()) {
			Show check=showService.findShowById(id);
			model.addAttribute("ratings", rating);
			model.addAttribute("thisShow",check);
			return "viewShow.jsp";
		}
		else {
			ratingService.createRating(rating);	
			return "redirect:/shows/{ratingAdded}";
		}
	}
	
	
//	Process the user's request to delete a TV show
	
	@RequestMapping("/shows/{id}/delete")
    public String delete(@PathVariable("id") Long id, HttpSession session) {
		if(session.getAttribute("currentUser")==null) {
			return "redirect:/";
		}
    	showService.deleteShow(id);
    	return "redirect:/shows";
    }
}
