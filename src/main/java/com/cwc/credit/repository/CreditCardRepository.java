package com.cwc.credit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cwc.credit.model.CreditCard;
import com.cwc.credit.payload.CreditCardResponse;


@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Integer> {

	List<CreditCard> findTop5ByOrderByIdDesc();

}
