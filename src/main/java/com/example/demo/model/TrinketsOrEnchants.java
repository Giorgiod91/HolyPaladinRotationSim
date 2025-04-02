package com.example.demo.model;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

//:TODO: add Season 2 Trinkets !!!! 

public class TrinketsOrEnchants {

    // If a spell is used, the trinket will provide the player with 159 main stat can stack up to 20 times and the buff will last forever in combat
    // This buff will be up 100% cause the simulation is in for a full combat
    public void GaleOfShadows(Paladin paladin) {
        // Maximum number of procs (stacks)
        int maxProcs = 20;
        AtomicInteger procCount = new AtomicInteger(0);

        // Timer to simulate ability usage
        Timer buffTimer = new Timer();

        // TimerTask to increase main stat on ability use
        TimerTask buffTimerTask = new TimerTask() {
            @Override
            public void run() {
                if (procCount.get() < maxProcs) {
                    // Increase main stat by 159
                    int newMainStat = paladin.getMainStat() + 159;
                    paladin.setMainStat(newMainStat);
                    procCount.incrementAndGet();
                    System.out.println("Gale of Shadows procs: " + procCount.get() + " times");
                } else {
                    System.out.println("Gale of Shadows already at max stacks (20).");
                    buffTimer.cancel();
                }
            }
        };

    
        buffTimer.schedule(buffTimerTask, 0, 1000);
    }

    // Method to simulate ability usage that triggers the Gale of Shadows effect
    public void useAbility(Paladin paladin) {
        GaleOfShadows(paladin);
    }

    // adding another dmg modifier a Trinket that increases the players Hase on Proc
    public void UnboundChangeling(Paladin paladin) {
      
        Timer buffTimer = new Timer();

        TimerTask buffTimerTask = new TimerTask() {
            @Override
            public void run() {
                // getting the haste chance first to then modify it with the buffed haste from the trinket metho
                int newHasteChance = paladin.getHasteChance() + 4490;
                // here then i set the newHaste value to the paladin class haste to work with it
                paladin.setHasteChance(newHasteChance);

              
            }
        };
        // 12 second proc
        buffTimer.scheduleAtFixedRate(buffTimerTask,0,12000);




    }

    // This embellishment will provide the player with a random secondary stat by 89 for 15 seconds and it stacks up to 10 times every 8 seconds in combat
    // This buff will be up 100% cause the simulation is in for a full combat
    public void AscendanceEmbellishment(Paladin paladin) {
        int maxProcs = 10;
        AtomicInteger procCount = new AtomicInteger(0);
        Random random = new Random();

        // Creating a list of secondary stats because the embellishment will provide a random secondary stat also the mastery stat does not enhance the dmg output
        List<String> secondaryStats = Arrays.asList("Haste", "Critical Strike", "Mastery", "Versatility");
        // randomly select one stat from the list
        String selectedStat = secondaryStats.get(random.nextInt(secondaryStats.size()));
        Timer buffTimer = new Timer();

        buffTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (procCount.get() < maxProcs) {
                    // Randomly select a stat to increase
                   

                    // Apply the stat increase to the Paladin
                    switch (selectedStat) {
                        case "Haste" -> paladin.setHasteChance(paladin.getHasteChance() + 89);
                        case "Critical Strike" -> paladin.setCritChance(paladin.getCritChance() + 89);
                        case "Mastery" -> {
                        }
                        case "Versatility" -> paladin.setVersatility(paladin.getVersatility() + 89);
                    }
                   

                    // Increment the stack count
                    procCount.incrementAndGet();
                    System.out.println("Ascendance Embellishment procs: " + selectedStat + " increased by 89. Current stacks: " + procCount.get());


                    if(procCount.get() == maxProcs){
                        buffTimer.cancel();

                    }
                } else {
                    System.out.println("Ascendance Embellishment already at max stacks (10).");
                    
                }
            }
        }, 0, 8000);
    }


    // simple trinket that increases vers on proc
    public void CarvedBlazikonWax(Paladin paladin) {
        
       int versatility = paladin.getVersatility();        
       
        Timer buffTimer = new Timer();

        // TimerTask to increase main stat on ability use
        TimerTask buffTimerTask = new TimerTask() {
            @Override
            public void run() {
                // increase the versatility stat by 3790
                paladin.setVersatility(versatility + 3970);
                
            }
        };

    
        buffTimer.schedule(buffTimerTask, 0, 15000);
    }



    public void SignetOfThePriory(Paladin paladin) {
        // get stats first cause this Trinket increased onClick the highest stat u have by a large amount
       int highestStat = paladin.gethighestStat();
        
       paladin.sethighestStat(highestStat, highestStat + 2765);
        Timer buffTimer = new Timer();

        // TimerTask to increase main stat on ability use
        TimerTask buffTimerTask = new TimerTask() {
            @Override
            public void run() {
                // increase the highest stat for 2765
                paladin.sethighestStat(highestStat, highestStat + 2765);
                
            }
        };

    
        buffTimer.schedule(buffTimerTask, 0, 2000);
    }

    // another S2 trinket called mr pick me up https://www.wowhead.com/item=230186/mister-pick-me-up
    // for simpliefing this method i will say the team is allways full health cause i cant access real time game data atm
    public void MisterPickMeUp(Paladin paladin) {
        int extraDamage = 0;
        int procs = 3;

        Timer bufTimer = new Timer();

        TimerTask buffTimerTask = new TimerTask(){
            @Override
            public void run(){

                
                

            }

        };
        bufTimer.schedule(buffTimerTask, 6000);
        
    }

 




    public void EmpoweringCrystal(Paladin paladin) {

        Random random = new Random();

        List<String> secondaryStats = Arrays.asList("Haste", "Critical Strike", "Mastery", "Versatility");
        String selectedStat = secondaryStats.get(random.nextInt(secondaryStats.size()));


        Timer buffTimer = new Timer();

        buffTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                switch (selectedStat) {
                    case "Haste" -> paladin.setHasteChance(paladin.getHasteChance() + 89);
                    case "Critical Strike" -> paladin.setCritChance(paladin.getCritChance() + 89);
                    case "Mastery" -> {
                    }
                    case "Versatility" -> paladin.setVersatility(paladin.getVersatility() + 89);
                }
               


            }
            
        },0, 20000);

    }
    /// mugs moxie jug trinket
    
    public void mugsMoxie(Paladin paladin) {
        // get stats first cause this Trinket increased onClick the highest stat u have by a large amount
       
        
      
        Timer buffTimer = new Timer();

        // TimerTask to increase main stat on ability use
        TimerTask buffTimerTask = new TimerTask() {
            @Override
            public void run() {
                // variable to track is a spell us used
                boolean spellUsed = false;
                // increase critt stat for 2765
                int newCritChance = paladin.getCritChance() + 765;
                // the tricky part here is grants an additional 765 Critical Strike for each spell used
                paladin.setCritChance(newCritChance);
                //::TODO:: add a this function 
                if(spellUsed){
                    int newCritChance2 = paladin.getCritChance() + 765;
                    paladin.setCritChance(newCritChance2);
                }

                
                
            }
        };

        //15 second timer for the trinket
        buffTimer.schedule(buffTimerTask, 0, 1500);
    }


    

    




    // This enchant will provide a damage proc and can occur up to 2 times
    public void RadiantPowerEnchant(Paladin paladin) {
        int damageProc = 130689;
        int maxProcs = 2;
        AtomicInteger procCountReal = new AtomicInteger(0);
        AtomicInteger procCount = new AtomicInteger(0);
        Random random = new Random();
        double procChance = 2.0 / 30.0; // Chance to proc according to my dummy testing is max 2 times in a 30 second interval

        Timer procTimer = new Timer();
        // Simulating the proc rate
        TimerTask procTask = new TimerTask() {
            @Override
            public void run(){ 
                if (procCount.get() < maxProcs) {
                    if (random.nextDouble() < procChance) {
                        int newMainStat = paladin.getMainStat() + 2230;
                        paladin.setMainStat(newMainStat);
                        System.out.println("Radiant Power Enchant is working");
                        procCount.incrementAndGet(); 
                        procCountReal.incrementAndGet();
                        paladin.addDamage(damageProc); 
                        System.out.println("Radiant Power Enchant procced " + procCountReal.get() + " times");
                    }
                } else {
                    System.out.println("Radiant Power Enchant already at max procs (2).");
                    procTimer.cancel();
                }
            }
        };
        // Run every second
        procTimer.scheduleAtFixedRate(procTask, 0, 1000);
    }
}
