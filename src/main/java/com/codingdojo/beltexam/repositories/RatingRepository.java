package com.codingdojo.beltexam.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.beltexam.models.Rating;

@Repository
public interface RatingRepository extends CrudRepository<Rating,Long> {

}
