import java.util.Random;


public class Main {

    public static void main(String[] args) { 
        // Creating a new Paladin instance
        Paladin holymoly = new Paladin();
        // Setting the main stat, versatility and crit of the Paladin
        holymoly.setMainStat(19797);
        holymoly.setVersatility(6246);
        holymoly.setCritChance(36);
        
         // Testing Crusader Strike to build Holy Power and potentially activate Dusk and Dawn
         System.out.println("Testing Crusader Strike:");
         for (int i = 0; i < 9; i++) {
             int crusaderStrikeDamage = holymoly.crusaderStrike();
             System.out.println("Current Holy Power: " + holymoly.getHolyPower());
             System.out.println("Crusader Strike damage: " + crusaderStrikeDamage);
         }

            // simulation consecration dmg 
            for(int i=0; i<5; i++) {
                int consecrationDamage = holymoly.Consecration();
                System.out.println(consecrationDamage);
            }
    
         
         // Now test Shield of the Righteous with enough Holy Power
         System.out.println("\nTesting Shield of the Righteous:");
         for (int i= 0; i < 3; i++) {
            int shieldOfRighteousDamage = holymoly.ShieldOfRighteous();
            System.out.println("Shield of the Righteous damage: " + shieldOfRighteousDamage);

         }
      
      
 
 
         // Test Holy Shock
         System.out.println("\nTesting Holy Shock:");
         int holyShockDamage = holymoly.holyShock();
         System.out.println("Holy Shock damage: " + holyShockDamage);
 
         // Test Judgment
         System.out.println("\nTesting Judgment:");
         int judgmentDamage = holymoly.JudgeMent();
         System.out.println("Judgment damage: " + judgmentDamage);
  

    }
public static class Paladin {
    private int holyPower = 0;
    private int holyShockCharges = 1;
    private int mainStat;
    private int versatility;
    private int builderCount = 0;
    private boolean duskAndDawnActive = false; // Indicates if the buff is active
    private int critChance = 0;
    private Random random = new Random();
    private boolean consecrationActive = false;

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
        return (int) totalDamage;
    }

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
        return (int) totalDamage;
    }

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
        return (int) totalDamage;
    }

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