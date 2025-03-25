package com.example.demo.service;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

import org.springframework.stereotype.Service;

import com.example.demo.model.Paladin;
import com.example.demo.model.TrinketsOrEnchants;

@Service
public class PaladinService {

    private Paladin holymoly = new Paladin();
    private TrinketsOrEnchants trinket = new TrinketsOrEnchants();
    private Timer testTimer;
    private int usageCounter = 0; // to count the number of users in a simple way for now 

    public void initializePaladin() {
        // Setting initial stats and attributes for Paladin
        holymoly.setMainStat(71160);
        holymoly.setMainStat((int) (holymoly.getMainStat() * 1.04)); // Increase by 4% cause i simulate with the lightsmith enchant
        //::TODO: change the mainstat 4% to be  picked over input or something !
        holymoly.setVersatility(5400);
        holymoly.setCritChance(22);
        trinket.AscendanceEmbellishment(holymoly);
    }

   public synchronized String runSimulation() {
    // i took the last part mostly from a boilerplate 
    // Start the timer
    holymoly.StartTimer();

    // Run the simulation for 60 seconds
    testTimer = new Timer();
    StringBuilder results = new StringBuilder();
    
    // Using a CountDownLatch or another mechanism to wait until the timer finishes withouth that i couldnt get the return value has to work asynchron
    final CountDownLatch latch = new CountDownLatch(1);
    
    testTimer.scheduleAtFixedRate(new TimerTask() {
        @Override
        public void run() {
            holymoly.basicRotationForOneTarget();
            results.append("Time is: ").append(holymoly.getTime()).append("\n");
            results.append("The Overall Damage is: ").append(holymoly.OverAllDamage()).append("\n");
            results.append("DPS damage per second: ").append(holymoly.OverAllDamage() / holymoly.getTime()).append("\n");
            int holyshockdmg = holymoly.holyShock();
            System.out.println("Holy Shock dealt: " + holyshockdmg);
            results.append("Holy Shock Damage: ").append(holyshockdmg).append("\n");

            
            if (holymoly.getTime() > 30) {
                testTimer.cancel();
                latch.countDown();  // Signal that simulation is complete then it lets the main thread to continue
            }
        }
    }, 0, 1000);

    try {
        latch.await();  // Wait for the simulation to complete so it block the main thread until countDown is called
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }

    System.out.println("Final Results:\n" + results.toString());

    return results.toString();
}

    public Paladin getPaladinStats() {
        return holymoly; 
    }

    public void setMainStat(int mainStat) {
        holymoly.setMainStat(mainStat);
    }

    // Method to increment usage counter
    public void incrementUsageCounter() {
        usageCounter++;
    }

    // Method to get the current usage count
    public int getUsageCounter() {
        return usageCounter;
    }

    public int getShieldDamage(){
        return  holymoly.ShieldOfRighteous();

    }
}
