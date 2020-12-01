package com.app.MockingRecordLabel.controller;

import com.app.MockingRecordLabel.model.Session;
import com.app.MockingRecordLabel.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/book")
public class SessionController {
    @Autowired
    private SessionService sessionService;

    @GetMapping(path = "/list", produces = "application/json")
    public List<Session> getSessionList() {
        return sessionService.getSessionList();
    }

    @GetMapping(path = "/list/{name}", produces = "application/json")
    public List<Session> getNameList(@PathVariable String name) {
        return sessionService.getNameSession(name);
    }

    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    public HashMap<String, Object> registerSession(@RequestBody Session newSession) {
        return sessionService.requestSession(newSession);
    }

    @PutMapping(path = "/update", consumes = "application/json", produces = "application/json")
    public HashMap<String, Object> updateSession(@RequestBody Session updatedSession) {
        return sessionService.updateSession(updatedSession);
    }

    @DeleteMapping(path = "/delete/{id}", produces = "application/json")
    public HashMap<String, Object> deleteSession(@PathVariable UUID id) {
        return sessionService.deleteSession(id);
    }
}
