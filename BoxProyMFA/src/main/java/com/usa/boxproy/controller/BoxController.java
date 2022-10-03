package com.usa.boxproy.controller;

import com.usa.boxproy.entities.Box;
import com.usa.boxproy.service.BoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Box")
public class BoxController {

    @Autowired
    private BoxService boxService;

    @GetMapping("/all")
    public List<Box> getAll(){
        return  boxService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Box> getBox(@PathVariable("id") int id){
        return boxService.getBox(id);
    }
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Box save(@RequestBody Box b){
        return boxService.save(b);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Box update(@RequestBody Box box){
        return boxService.update(box);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id){
        boxService.delete(id);
    }


}
