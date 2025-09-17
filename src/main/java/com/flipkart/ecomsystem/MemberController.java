package com.flipkart.ecomsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberRepository repo;

    @PostMapping
    public Member create(@RequestBody Member member) {
        member.setId(null);
        return repo.save(member);
    }

    @GetMapping
    public List<Member> findAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Member findById(@PathVariable String id) {
        return repo.findById(id).get();
    }

    @PutMapping("/{id}")
    public Member update(@PathVariable String id, @RequestBody Member member) {
        Member oldMember = repo.findById(id).get();
        oldMember.setName(member.getName());
        oldMember.setPhoneNumber(member.getPhoneNumber());
        oldMember.setEmail(member.getEmail());
        oldMember.setBalance(member.getBalance());
        oldMember.setActive(member.isActive());
        return repo.save(oldMember);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable String id) {
        Optional<Member> optionalMember = repo.findById(id);
        if (optionalMember.isEmpty()) {
            return false;
        }
        repo.deleteById(id);
        return true;
    }
}