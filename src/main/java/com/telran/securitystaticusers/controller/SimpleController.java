package com.telran.securitystaticusers.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("message")
public class SimpleController {

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<String> getAll(){
        System.out.println("Test");
        return list;
    }

    private CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

    @PostMapping
    public void addMessage(@RequestBody String message){
        list.add(message);
    }

    @DeleteMapping
    public void deleteAll(){
        list.clear();
    }

    @GetMapping("hello")
    public String getHello(){
        return "Hello";
    }
}
