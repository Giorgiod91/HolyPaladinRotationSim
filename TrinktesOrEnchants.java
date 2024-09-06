import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class TrinktesOrEnchants {
    








    public void RadiantPowerEnchant(Paladin paladin) {
        int damageProc = 130689;
        int maxProcs = 2;
        int procCount = 0;

        Random random = new Random();

        double procChance = 30/2;  // chance to proc according to my dummy testing is max 2 times in a 30 second interval


        Timer procTimer = new Timer();
        TimerTask procTask - new TimerTask(){
            @Override
            public void run(){

                if(maxProcs <= procCount) {
                    int newMainStat = paladin.getMainStat() + 2230;
                    paladin.setMainStat(newMainStat);
                    System.out.println("Radiant Power Enchant is working");
                    procCount++;
                    paladin.addDamage(damageProc); 
                }
                


            }
        }

     

    }
    
}
