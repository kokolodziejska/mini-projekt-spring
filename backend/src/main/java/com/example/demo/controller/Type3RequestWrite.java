package com.example.demo.controller;

import com.example.demo.dto.CreateUserDto;
import com.example.demo.dto.UpdateHobbyDto;
import com.example.demo.dto.UpdateBirthDateDto;
import com.example.demo.dto.UpdateMoviesDto;
import com.example.demo.model.Request;
import com.example.demo.service.RequestQueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/type3")
@RequiredArgsConstructor
@CrossOrigin
public class Type3RequestWrite {

    private final RequestQueueService queueService;

    @PostMapping()
    public ResponseEntity<String> handleType3(@RequestBody String json) {
        queueService.addRequest(new Request(null, "Typ3", json));
        return ResponseEntity.ok("Typ3 wysłany do kolejki.");
    }

    
}
