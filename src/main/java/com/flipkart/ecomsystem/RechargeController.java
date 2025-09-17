package com.flipkart.ecomsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recharges")
public class RechargeController {

    @Autowired
    private RechargeRepository repo;

    @PostMapping
    public Recharge create(@RequestBody Recharge recharge) {
        recharge.setId(null);
        // Note: In a real app, you would also update the member's balance here
        return repo.save(recharge);
    }

    @GetMapping
    public List<Recharge> findAll() {
        return repo.findAll();
    }

    @GetMapping("/member/{memberId}")
    public List<Recharge> findByMember(@PathVariable String memberId) {
        return repo.findByMemberId(memberId);
    }
}