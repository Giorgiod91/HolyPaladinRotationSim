package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Paladin;
import com.example.demo.service.PaladinService;

@RestController
@RequestMapping("/paladin")
public class PaladinController {

    private final PaladinService paladinService;

    public PaladinController(PaladinService paladinService) {
        this.paladinService = paladinService;
        paladinService.initializePaladin(); // Initialize paladin on startup
    }

    @GetMapping("/stats")
    public Paladin getPaladinStats() {
        return paladinService.getPaladinStats();
    }
    @GetMapping("/usageCounter")  // New endpoint to get the usage counter
    public int getUsageCounter() {
        return paladinService.getUsageCounter();
    }

    @PostMapping("/setCounter")
    public void incrementUsageCounter() {
        paladinService.incrementUsageCounter();  // This will increment the counter each time this endpoint is hit
    }


    // Change to @RequestBody to handle JSON payload
    @PostMapping("/setMainStat")
    public void setMainStat(@RequestBody MainStatRequest body) {
        paladinService.setMainStat(body.getMainStat());
    }

    @PostMapping("/setCrit")
    public void setCrit(@RequestBody CritRequest body) {
        paladinService.getPaladinStats().setCritChance(body.getCrit());
    }

    @PostMapping("/setVersatility")
    public void setVersatility(@RequestBody VersatilityRequest body) {
        paladinService.getPaladinStats().setVersatility(body.getVersatility());
    }

    @GetMapping("/simulate")
    public String simulate() {
        return paladinService.runSimulation();
    }
}

// DTO classes to map the incoming request bodies
class MainStatRequest {
    private int mainStat;

    public int getMainStat() {
        return mainStat;
    }

    public void setMainStat(int mainStat) {
        this.mainStat = mainStat;
    }
}

class CritRequest {
    private int crit;

    public int getCrit() {
        return crit;
    }

    public void setCrit(int crit) {
        this.crit = crit;
    }
}

class VersatilityRequest {
    private int versatility;

    public int getVersatility() {
        return versatility;
    }

    public void setVersatility(int versatility) {
        this.versatility = versatility;
    }
}
