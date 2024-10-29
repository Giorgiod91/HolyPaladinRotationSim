package com.example.demo.service;

import java.util.Timer;
import java.util.TimerTask;

import org.springframework.stereotype.Service;

import com.example.demo.model.Paladin;
import com.example.demo.model.TrinketsOrEnchants;

@Service
public class PaladinService {

    private Paladin holymoly = new Paladin();
    private TrinketsOrEnchants trinket = new TrinketsOrEnchants();
    private Timer testTimer;

    public void initializePaladin() {
        // Setting initial stats and attributes for Paladin
        holymoly.setMainStat(70394);
        holymoly.setMainStat((int) (holymoly.getMainStat() * 1.04)); // Increase by 4%
        holymoly.setVersatility(5684);
        holymoly.setCritChance(23);
        trinket.AscendanceEmbellishment(holymoly);
    }

    public String runSimulation() {
        // Start the timer
        holymoly.StartTimer();
        
        // Run the simulation for 60 seconds
        testTimer = new Timer();
        StringBuilder results = new StringBuilder();
        testTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                holymoly.basicRotationForOneTarget();
                results.append("Time is: ").append(holymoly.getTime()).append("\n");
                results.append("The Overall Damage is: ").append(holymoly.OverAllDamage()).append("\n");
                results.append("DPS damage per second: ").append(holymoly.OverAllDamage() / holymoly.getTime()).append("\n");
                if (holymoly.getTime() > 30) {
                    testTimer.cancel();
                }
            }
        }, 0, 1000);

        return results.toString();
    }

    public Paladin getPaladinStats() {
        return holymoly; 
    }

    public void setMainStat(int mainStat) {
        holymoly.setMainStat(mainStat);
    }
}
