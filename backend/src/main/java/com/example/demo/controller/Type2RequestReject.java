package com.example.demo.controller;

import com.example.demo.model.Request;
import com.example.demo.service.RequestQueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/type2")
@RequiredArgsConstructor
@CrossOrigin
public class Type2RequestReject {

    private final RequestQueueService queueService;

    @PostMapping()
    public ResponseEntity<String> handleType2(@RequestBody String json) {
        queueService.addRequest(new Request(null, "Typ2", json));
        return ResponseEntity.ok("Typ2 wysłany do kolejki.");
    }
}
