import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

//:TODO: add more trinkets to the class

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

        // Schedule the timer task to run when an ability is used
        buffTimer.schedule(buffTimerTask, 0, 1000); // Example: every second for the simulation
    }

    // Method to simulate ability usage that triggers the Gale of Shadows effect
    public void useAbility(Paladin paladin) {
        GaleOfShadows(paladin);
    }

    // This embellishment will provide the player with a random secondary stat by 89 for 15 seconds and it stacks up to 10 times every 8 seconds in combat
    // This buff will be up 100% cause the simulation is in for a full combat
    public void AscendanceEmbellishment(Paladin paladin) {
        int maxProcs = 10;
        AtomicInteger procCount = new AtomicInteger(0);
        Random random = new Random();

        // Creating a list of secondary stats because the embellishment will provide a random secondary stat also the mastery stat does not enhance the dmg output
        List<String> secondaryStats = Arrays.asList("Haste", "Critical Strike", "Mastery", "Versatility");
        String selectedStat = secondaryStats.get(random.nextInt(secondaryStats.size()));
        Timer buffTimer = new Timer();

        buffTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (procCount.get() < maxProcs) {
                    // Randomly select a stat to increase
                   

                    // Apply the stat increase to the Paladin
                    switch (selectedStat) {
                        case "Haste":
                            paladin.setHasteChance(paladin.getHasteChance() + 89);
                            break;
                        case "Critical Strike":
                            paladin.setCritChance(paladin.getCritChance() + 89);
                            break;
                        case "Mastery":
                            // No action defined for Mastery
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
                    buffTimer.cancel();
                }
            }
        }, 0, 8000);
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
            public void run() {
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
