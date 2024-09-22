import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class TrinktesOrEnchants {



    public void AscendanceEmbelishment(Paladin paladin) {
        // this embeleshment will provide the player with a random secondary stat by 89 for 15 second and it stacks up to 10 times every 8 seconds in combat
        // this buff will be up 100% cause the simulation is in for a full combat
        int maxProcs = 10;
        AtomicInteger procCountReal = new AtomicInteger(0);
        AtomicInteger procCount = new AtomicInteger(0);

        Random random = new Random();
        // creating a list of secondary stats because the embeleshment will provide a random secondary stat also the mastery stat does not enhance the dmg output
        List<String> secondaryStats = Arrays.asList("Haste", "Critical Strike", "Mastery", "Versatility");
        Timer buffTimer = new Timer();

        buffTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (procCount.get() < maxProcs) {
                    // Randomly select a stat to increase
                    String selectedStat = secondaryStats.get(random.nextInt(secondaryStats.size()));
    
                    // Apply the stat increase to the Paladin
                    switch (selectedStat) {
                        case "Haste":
                            paladin.setHasteChance(paladin.getHasteChance() + 89);
                            break;
                        case "Critical Strike":
                            paladin.setCritChance(paladin.getCritChance() + 89);
                            break;
                        case "Mastery":
                            
                            break;
                        case "Versatility":
                            paladin.setVersatility(paladin.getVersatility() + 89);
                            break;
                    }
    
                    // Increment the stack count
                    procCount.incrementAndGet();
    
                    System.out.println("Ascendance Embellishment procs: " + selectedStat + " increased by 89. Current stacks: " + procCount.get());
                } else {
                    System.out.println("Ascendance Embellishment already at max stacks (10).");
                }
            }
        }, 0, 8000);
        

    }
        
    








    public void RadiantPowerEnchant(Paladin paladin) {
        int damageProc = 130689;
        int maxProcs = 2;
        AtomicInteger procCountReal = new AtomicInteger(0);
        AtomicInteger procCount = new AtomicInteger(0);

        Random random = new Random();

        double procChance = 30/2;  // chance to proc according to my dummy testing is max 2 times in a 30 second interval


        Timer procTimer = new Timer();
        // simualting the proc rate i took 2.0 / 30.0 so 6.67% chance to proc every second
        TimerTask procTask = new TimerTask(){
            @Override
            public void run(){

                if(procCount.get() < maxProcs) {
                    if (random.nextDouble() < procChance) {
                        int newMainStat = paladin.getMainStat() + 2230;
                        paladin.setMainStat(newMainStat);
                        System.out.println("Radiant Power Enchant is working");
                        procCount.incrementAndGet(); 
                        procCountReal.incrementAndGet();
                        paladin.addDamage(damageProc); 
                        System.out.println("Radiant Power Enchant procced " + procCountReal.get() + " times");
                }
                else{
                    procTimer.cancel();
                }
            }
                


            }
        };
        // run every second
        procTimer.scheduleAtFixedRate(procTask, 0, 1000);

    }
    
};
