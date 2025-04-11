package com.example.demo.controller;

import com.example.demo.model.Request;
import com.example.demo.service.RequestQueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/requests")
@RequiredArgsConstructor
@CrossOrigin
public class RequestController {

    private final RequestQueueService queueService;

    @GetMapping
    public List<Request> getRequests() {
        return queueService.getAllRequests();
    }

    @PostMapping
    public String addRequest(@RequestBody Request request) {
        queueService.addRequest(request);
        return "Żądanie przyjęte! Typ: " + request.getType();
    }
}
