package com.flipkart.ecomsystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private static final Logger log = LoggerFactory.getLogger(MemberService.class);

    @Autowired
    private MemberRepository repo;

    public Member create(Member member) {
        log.info("Creating member: {}", member.getName());
        member.setId(null);
        validate(member);
        return repo.save(member);
    }

    public List<Member> findAll() {
        log.info("Finding all members");
        return repo.findAll();
    }

    public Member findById(String id) {
        log.info("Finding member by id: {}", id);
        return repo.findById(id)
                .orElseThrow(() -> {
                    log.error("Attempted to find non-existing member id: {}", id);
                    return new ResourceNotFoundException("Member not found with id: " + id);
                });
    }

    public Member update(String id, Member memberDetails) {
        log.info("Updating member by id: {}", id);
        Member existingMember = findById(id); // This already handles the not-found case

        existingMember.setName(memberDetails.getName());
        existingMember.setPhoneNumber(memberDetails.getPhoneNumber());
        existingMember.setEmail(memberDetails.getEmail());
        existingMember.setBalance(memberDetails.getBalance());
        existingMember.setActive(memberDetails.isActive());

        validate(existingMember);
        return repo.save(existingMember);
    }

    public void delete(String id) {
        log.info("Deleting member by id: {}", id);
        if (!repo.existsById(id)) {
            log.error("Attempted to delete non-existing member id: {}", id);
            throw new ResourceNotFoundException("Member not found with id: " + id);
        }
        repo.deleteById(id);
    }

    private void validate(Member member) {
        if (!StringUtils.hasText(member.getName())) {
            throw new BusinessException("Member name cannot be empty.");
        }
        if (member.getBalance() < 0) {
            throw new BusinessException("Balance cannot be negative.");
        }
        if (member.getPhoneNumber() == null || member.getPhoneNumber().length() != 10) {
            throw new BusinessException("Phone number must be 10 digits.");
        }
    }
}