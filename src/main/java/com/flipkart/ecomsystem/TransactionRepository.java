package com.flipkart.ecomsystem;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
    List<Transaction> findByMemberId(String memberId);
    List<Transaction> findByGameId(String gameId);
}