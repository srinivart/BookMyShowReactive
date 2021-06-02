package com.srinivart.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srinivart.model.BookRequest;
import com.srinivart.repository.BookMyShowRepository;

@RestController
@RequestMapping("BookMyShow/Service")
public class BookMyShowController {

	@Autowired
	private BookMyShowRepository repository;
	
	
	@PostMapping("/bookingShow")
	public String bookShow(@RequestBody BookRequest bookRequest) {
		BookRequest response = repository.save(bookRequest);
		return "Hi " + response.getUserName() + " your Request for " + response.getShowName() + " on date "
				+ response.getBookingTime() + "Booking successfully..";
	}

	@GetMapping("/getAllBooking")
	public List<BookRequest> getAllBooking() {
		return repository.findAll();
	}

	@GetMapping("/getBooking/{bookingId}")
	public BookRequest getBooking(@PathVariable int bookingId) {
		return repository.findById(bookingId).get();
	}

	@DeleteMapping("/cancelBooking/{bookingId}")
	public String cancelBooking(@PathVariable int bookingId) {
		repository.deleteById(bookingId);
		return "Booking cancelled with bookingId : " + bookingId;
	}

	@PutMapping("/updateBooking/{bookingId}")
	public BookRequest updateBooking(@RequestBody BookRequest updateBookRequest, @PathVariable int bookingId) {
		BookRequest dbResponse = repository.findById(bookingId).get();
		dbResponse.setBookingTime(updateBookRequest.getBookingTime());
		dbResponse.setPrice(updateBookRequest.getPrice());
		dbResponse.setShowName(updateBookRequest.getShowName());
		dbResponse.setUserCount(updateBookRequest.getUserCount());
		repository.saveAndFlush(dbResponse);
		return dbResponse;
	}
	
//	@PostConstruct
//	public void initBookRequest() {
//		BookRequest b = new BookRequest(1,"srini","abc",new Date( ),1,150);
//		repository.save(b);
//	}
	
//	@PostConstruct
//	public void initBookRequest() {
//		BookRequest b = new BookRequest(1,"srini","abc",LocalDate.of( 2021 , 6 , 2 ),1,150);
//		repository.save(b);
//	}
	
	
	@PostConstruct
	public void initBookRequest() {
		BookRequest b1 = new BookRequest(1,"srini","abc",LocalDate.of( 2021 , 6 , 2 ),1,150);
		BookRequest b2 = new BookRequest(2,"sahi","abc",LocalDate.of( 2021 , 6 , 3 ),1,250);
		repository.saveAll(Arrays.asList(b1,b2));
	}	
	
	
//	@PostConstruct
//	public void initBookRequest() {
//		repository.saveAll(Stream.of(
//				new BookRequest(1,"srini","abc",null,1,150),
//				new BookRequest(2,"sahi","xyz",null,1,200)
//				)).collect(Collectors.toList());	
//	}

	
}
