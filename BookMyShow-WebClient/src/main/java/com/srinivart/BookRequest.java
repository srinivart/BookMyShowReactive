package com.srinivart;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {

	private int bookingId;
	private String userName;
	private String showName;
	@JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING, timezone = "Asia/Kolkata")
	private LocalDate bookingTime;
	//private Date bookingTime;
	private int userCount;
	private double price;
}
