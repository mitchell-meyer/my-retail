package com.target.redsky.myretail.repository;

import com.target.redsky.myretail.entity.Price;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends MongoRepository<Price, Long> {
}
