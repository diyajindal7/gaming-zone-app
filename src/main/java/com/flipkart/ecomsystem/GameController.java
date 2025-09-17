package com.flipkart.ecomsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameRepository repo;

    @PostMapping
    public Game create(@RequestBody Game game) {
        game.setId(null);
        return repo.save(game);
    }

    @GetMapping
    public List<Game> findAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Game findById(@PathVariable String id) {
        return repo.findById(id).get();
    }

    @PutMapping("/{id}")
    public Game update(@PathVariable String id, @RequestBody Game game) {
        Game oldGame = repo.findById(id).get();
        oldGame.setName(game.getName());
        oldGame.setDescription(game.getDescription());
        oldGame.setPrice(game.getPrice());
        oldGame.setGenre(game.getGenre());
        oldGame.setStatus(game.getStatus());
        return repo.save(oldGame);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable String id) {
        Optional<Game> optionalGame = repo.findById(id);
        if (optionalGame.isEmpty()) {
            return false;
        }
        repo.deleteById(id);
        return true;
    }
}