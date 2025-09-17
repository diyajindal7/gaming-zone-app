package com.flipkart.ecomsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionRepository repo;

    @PostMapping
    public Transaction create(@RequestBody Transaction transaction) {
        transaction.setId(null);
        // Note: In a real app, you would also deduct the amount from the member's balance
        return repo.save(transaction);
    }

    @GetMapping
    public List<Transaction> findAll() {
        return repo.findAll();
    }

    @GetMapping("/member/{memberId}")
    public List<Transaction> findByMember(@PathVariable String memberId) {
        return repo.findByMemberId(memberId);
    }
}