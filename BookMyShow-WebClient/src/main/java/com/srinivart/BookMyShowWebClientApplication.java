package com.srinivart;



import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
@RequestMapping("/bookMyShow-client")
public class BookMyShowWebClientApplication {
	
    WebClient webclient;
    
    @PostConstruct
    public void init() {
    	webclient = WebClient.builder()
    			             .baseUrl("http://localhost:9090/BookMyShow/Service")
    			             .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
    			             .build(); 	
    }
    
    
    @PostMapping("/bookNow")
    public Mono<String> BookNow(@RequestBody BookRequest request){
    	return webclient.post()
    			        .uri("/bookingShow")
    			        .syncBody(request)
    			        .retrieve().bodyToMono(String.class);		        
    }
    
    
    @GetMapping("/trackBookings")
    public Flux<BookRequest> trackAllBooking(){
    	return webclient.get()
    			        .uri("/getAllBooking")
    			        .retrieve()
    			        .bodyToFlux(BookRequest.class);
    }
    
    
    
    
    @GetMapping("/trackBookings/{bookingId}")
    public Mono<BookRequest> getBookingById(@PathVariable int bookingId){
    	return webclient.get()
    					.uri("/getBooking/"+bookingId)
    					.retrieve()
    					.bodyToMono(BookRequest.class);
    }
    
    
    @PutMapping("/changeBooking/{bookingId}")
    public Mono<BookRequest> updateBooking(@PathVariable int bookingId,@RequestBody BookRequest request){
    	return webclient.put()
    			        .uri("/updateBooking/"+bookingId)
    			        .syncBody(request)
    			        .retrieve().bodyToMono(BookRequest.class);		        
    }
    
    
    @DeleteMapping("/removeBooking/{bookingId}")
    public Mono<String> CancelBooking(@PathVariable int bookingId){
    	return webclient.delete()
    			.uri("/cancelBooking/"+bookingId)
    			.retrieve()
    			.bodyToMono(String.class);
    }
    
    
	public static void main(String[] args) {
		SpringApplication.run(BookMyShowWebClientApplication.class, args);
	}

}
