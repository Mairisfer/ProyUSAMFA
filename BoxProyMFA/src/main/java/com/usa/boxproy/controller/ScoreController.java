package com.usa.boxproy.controller;

import com.usa.boxproy.entities.Score;
import com.usa.boxproy.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Score")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @GetMapping("/all")
    public List<Score> getAll(){
        return  scoreService.getAll();
    }
    @GetMapping("/{id}")
    public Optional<Score> getScore(@PathVariable("id") int idScore){
        return scoreService.getScore(idScore);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Score save(@RequestBody Score sc){
        return scoreService.save(sc);
    }
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Score update(@RequestBody Score score){
        return scoreService.update(score);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int idScore){
        scoreService.delete(idScore);
    }

}
