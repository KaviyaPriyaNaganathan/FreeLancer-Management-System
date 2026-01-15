package com.freelancing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freelancing.models.Review;

public interface ReviewRepository extends JpaRepository<Review,Long> {

}
