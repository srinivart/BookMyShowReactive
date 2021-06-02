package com.srinivart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srinivart.model.BookRequest;

public interface BookMyShowRepository extends JpaRepository<BookRequest, Integer> {

}
