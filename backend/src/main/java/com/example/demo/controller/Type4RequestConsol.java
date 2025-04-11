package com.example.demo.controller;

import com.example.demo.model.Request;
import com.example.demo.service.RequestQueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/type4")
@RequiredArgsConstructor
@CrossOrigin
public class Type4RequestConsol {

    private final RequestQueueService queueService;

    @PostMapping()
    public ResponseEntity<String> handleType4(@RequestBody String json) {
        queueService.addRequest(new Request(null, "Typ4", json));
        return ResponseEntity.ok("Typ4 wysłany do kolejki.");
    }
}
