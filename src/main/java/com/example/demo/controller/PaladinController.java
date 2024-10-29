package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Paladin;
import com.example.demo.service.PaladinService;

@RestController
@RequestMapping("/paladin")
public class PaladinController {

    private final PaladinService paladinService;

    @Autowired
    public PaladinController(PaladinService paladinService) {
        this.paladinService = paladinService;
        paladinService.initializePaladin(); // Initialize paladin on startup
    }

    @GetMapping("/stats")
    public Paladin getPaladinStats() {
        return paladinService.getPaladinStats();
    }

    @PostMapping("/setMainStat")
    public void setMainStat(@RequestParam int mainStat) {
        paladinService.setMainStat(mainStat);
    }

    @GetMapping("/simulate")
    public String simulate() {
        return paladinService.runSimulation();
    }
}
