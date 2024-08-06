import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class Main {

    public static void main(String[] args) { 
        // Creating a new Paladin instance
        Paladin holymoly = new Paladin();
        // Setting the main stat, versatility and crit of the Paladin
        holymoly.setMainStat(19797);
        holymoly.setVersatility(6246);
        holymoly.setCritChance(36);
        
         // Testing Crusader Strike to build Holy Power and potentially activate Dusk and Dawn
        // System.out.println("Testing Crusader Strike:");
        // for (int i = 0; i < 9; i++) {
          //   int crusaderStrikeDamage = holymoly.crusaderStrike();
           //  System.out.println("Current Holy Power: " + holymoly.getHolyPower());
           //  System.out.println("Crusader Strike damage: " + crusaderStrikeDamage);
        // }

            // simulation consecration dmg 
          //  for(int i=0; i<5; i++) {
                int consecrationDamage = holymoly.Consecration();
                System.out.println(consecrationDamage);
           // }
    
         
         // Now test Shield of the Righteous with enough Holy Power
       //  System.out.println("\nTesting Shield of the Righteous:");
        // for (int i= 0; i < 3; i++) {
          //  int shieldOfRighteousDamage = holymoly.ShieldOfRighteous();
           // System.out.println("Shield of the Righteous damage: " + shieldOfRighteousDamage);

        // }
      
      
 
 
         // Test Holy Shock
        // System.out.println("\nTesting Holy Shock:");
        // int holyShockDamage = holymoly.holyShock();
        // System.out.println("Holy Shock damage: " + holyShockDamage);
 
         // Test Judgment
        // System.out.println("\nTesting Judgment:");
        // int judgmentDamage = holymoly.JudgeMent();
        // System.out.println("Judgment damage: " + judgmentDamage);

         // showing overall dmg

        // System.out.println("Overall dmg :" +holymoly.OverAllDamage());
         // starting timer 
         holymoly.StartTimer();
         // testing rotation method with 60 seconds dps rotation

         Timer testTimer = new Timer();
         testTimer.scheduleAtFixedRate(new TimerTask() {
            
            public void run() {
                holymoly.basicRotation();
                System.out.println("Time is :" + holymoly.getTime());
                System.out.println("The Overall Dmg is :" + holymoly.OverAllDamage());
                // dividing time by overall dmg to display the actual DPS u do so the damage per second
                System.out.println("DPS damage per second :" + holymoly.OverAllDamage() / holymoly.getTime());
                if(holymoly.getTime() > 25) {
                                  
                    testTimer.cancel();
                }
            }
                     
              }, 0 ,1000);
            }
            
                
public static class Paladin {
    // variables to keep track of
    private int holyPower = 0;
    private int holyShockCharges = 1;
    private int mainStat;
    private int versatility;
    private int builderCount = 0;
    private boolean duskAndDawnActive = false; 
    private int critChance = 0;
    private Random random = new Random();
    private boolean consecrationActive = false;
    private int totalDamageOne = 0;
    private int Time =0;
    private boolean hasSunsAvatarSkilled = true;
    private boolean AvengingWrathIsUsed = false;
    private Timer avengingWrathTimer = new Timer();
    
    // returning the values to later use them
    public int getHolyShockCharges() {
        return holyShockCharges;
    }
    public int getHolyPower() {
        return holyPower;
    }

    public void regenerateHolyShockCharge() {
        if (holyShockCharges < 2) {
            holyShockCharges++;
        }
    }

    public int OverAllDamage() {
        return totalDamageOne;
    }

    public int getTime() {
            return Time;
        }
    
    // using the java timer to track the seconds 
    public void StartTimer() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                Time++;
             
            }
        }, 0, 1000);
        
    }
    //method to simulate the rotation for holypaladins with the methods i declared and all dmg modifiers
    public void basicRotation() {
       
        int maxRotations = 0;
        AvengingWrath();

       
        // i check if the AvengingWrath buff is applied

        System.out.println("crit chance after using Wrath is"+ critChance);
        for(int i = 0; i< 3; i++){
            if(Time  % 12 == 0 && Time >0  && maxRotations <= 4){
            Consecration();
            maxRotations++;
        

            }
        }
        System.out.println("current crit is"+ critChance);
        JudgeMent();
        holyShock();
        ShieldOfRighteous();

       
               
    
    }
   // if AvengingWrath is active u get 15% increased crit chance
    public void AvengingWrath() {
        if (AvengingWrathIsUsed) {
            return; 
        }

        AvengingWrathIsUsed = true;
        critChance += 15; 
        avengingWrathTimer.cancel();
        // using the java timer to track the ability that increases the crit chance by another 15% for 20 seconds
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                // adding the 15% crit chance
                critChance -= 15; 
                // and reset it again with the variable i declared earlier
                AvengingWrathIsUsed = false;

                System.out.println("AvengingWrath ended crit chance removed and back to:" + critChance);
            }
        },20000); 
    }


    public int ShieldOfRighteous() {
        
        if (holyPower < 3) {
            System.out.println("Not enough Holy Power!");
            return 0;
        }
        // checking if consecration is active cause that gives u 20% increased dmg on ShiledOfTheRighteous

        
        double baseDamage = mainStat * 1.28;
        holyPower -= 3; // Consumes 3 Holy Power to use SoR

        if(consecrationActive) {
            baseDamage = baseDamage *1.2;
            System.out.println("20% increased dmg active ");
        }

        double totalDamage = baseDamage;

     

        
        // Apply Dusk and Dawn buff if active
        if (duskAndDawnActive) {
            totalDamage *= 1.3; // Apply 30% additional damage
            duskAndDawnActive = false; // Reset the buff after use
        }

        // Apply versatility and crit modifiers
        totalDamage = applyVersatilityAndCrit(totalDamage);

        // adding the number to the TotalDamageOne variable to later be able to give out the overall dmg number

        totalDamageOne += (int) totalDamage;
        
        System.out.println("Shield of the Righteous damage: " + totalDamage);
        return (int) totalDamage;
    }

    public int Consecration() {
        int numberOfTicks = 18;
        double totalDamage = 0;

        consecrationActive = true;

        double baseDamage = mainStat * 0.05;
        double versatilityPercent = versatility / 205.0;
        double versatilityBonus = baseDamage * versatilityPercent / 100.0;
        double critMultiplier = 2.0;

        for (int i = 0; i < numberOfTicks; i++) {
            double tickDamage = baseDamage + versatilityBonus;
            boolean isCriticalHit = random.nextDouble() < (critChance / 100.0);
            if (isCriticalHit) {
                tickDamage *= critMultiplier;
            }
            totalDamage += tickDamage;
        }

        System.out.println("Total Consecration damage: " + totalDamage);
        // add to totaldamageone 
        totalDamageOne += (int) totalDamage;
        return (int) totalDamage;
    }

    public int crusaderStrike() {
        int baseDamage = (int) (mainStat * 1.071);
        holyPower += 1;
        builderCount += 1;

        // Check if we have used 3 builders
        if (builderCount == 3) {
            duskAndDawnActive = true; // Activate Dusk and Dawn buff
            builderCount = 0; // Reset builder count
        }

        double totalDamage = applyVersatilityAndCrit(baseDamage);

        System.out.println("Crusader Strike damage: " + totalDamage);
        // add dmg to totaldamageone
        totalDamageOne += (int) totalDamage;
        return (int) totalDamage;
    }
    //this is a method that also modifiers the dmg output if u have it talented 
    public int SunsAvatarPassive() {
        int basicDamage = 13463;
        return basicDamage;
    }

    // method to simulate holyshock dmg depending on the holyshock charges u have
    public int holyShock() {
        if (holyShockCharges <= 0) {
            return 0;
        }

        int baseDamage = (int) (mainStat * 1.2);
        holyPower += 1;
        builderCount += 1;
        holyShockCharges -= 1;

        // Check for Dusk and Dawn buff activation
        if (builderCount >= 3) {
            duskAndDawnActive = true;
            builderCount = 0;
        }

        double totalDamage = applyVersatilityAndCrit(baseDamage);
        System.out.println("Holy Shock damage: " + totalDamage);
        // adding dmg to totaldamageone
        totalDamageOne += (int) totalDamage;
        return (int) totalDamage;
    }
    // method to simulate Judgment dmg and also build holypower thats later needed for spenders to be used
    public int JudgeMent() {
        int baseDamage = (int) (mainStat * 1.125);
        holyPower += 1;
        builderCount += 1;

        if (builderCount >= 3) {
            duskAndDawnActive = true;
            builderCount = 0;
        }

        double totalDamage = applyVersatilityAndCrit(baseDamage);
        System.out.println("Judgment damage: " + totalDamage);
        // adding to totaldamageone
        totalDamageOne += (int) totalDamage;
        return (int) totalDamage;
    }
    // this is the dmg modifier so our dmg gets modified by a couple of thing like stats we have to keep that in mind
    private double applyVersatilityAndCrit(double baseDamage) {
        double versatilityPercent = versatility / 205.0;
        double versatilityBonus = baseDamage * versatilityPercent / 100.0;
        double critMultiplier = random.nextDouble() < (critChance / 100.0) ? 2.0 : 1.0;
        double totalDamage = (baseDamage + versatilityBonus) * critMultiplier;
        

       
        return totalDamage;
    }

  
    public void setMainStat(int mainStat) { this.mainStat = mainStat; }
    public void setVersatility(int versatility) { this.versatility = versatility; }
    public void setCritChance(int critChance) { this.critChance = critChance; }
}
}