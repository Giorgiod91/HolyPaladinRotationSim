import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class TrinktesOrEnchants {
    








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
