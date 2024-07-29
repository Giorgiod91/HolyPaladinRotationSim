import java.util.Random;



public class Main {

    public static void main(String[] args) { 
        // Creating a new Paladin instance
        Paladin holymoly = new Paladin();
        // Setting the main stat and versatility of the Paladin
        holymoly.setMainStat(19797);
        holymoly.setVersatility(6246);
        holymoly.setCritChance(36);
        
        // Calling the holyShock method to calculate the damage
            
          
        int holyShockDamage = holymoly.holyShock();
        for (int i = 0; i < 5; i++) {
            int crusaderStrikeDamage = holymoly.crusaderStrike();
            System.out.println("damage: " + crusaderStrikeDamage);
        }
       


         
        System.out.println("damage: " + holyShockDamage);
       
  

    }
    //::TODO::Implement the dawm and dusk buff that increases the damage of the next holy shock by 30%  if there are 3 builders used

    public static class Paladin {
        // Declaring variables for the Paladin class 
        private int holyPower = 0;
        private int holyShockCharges = 1;
        private int mainStat;
        private int versatility;
        private int builder = 0;
        private int duskAndDawn = 0; // dusk and dawm buff that increases the damage of the next holy shock by 30%
        private int critChance = 0;
        private Random random = new Random(); // Random number generator using that one to simulate the crit chance
        
        public int getHolyShockCharges() {
            return holyShockCharges;
        }
     
        // Method to regenerate the holyShock charges
        public void regenerateHolyShockCharge() {
            if (holyShockCharges < 2) {
                holyShockCharges++;
            }
        }
        // method to calculate the damage of the crusader strike ability
        public int crusaderStrike() {
            // add 1 to the builder cause i want to be able to keep track of the dusk and dawn buff that increases the damage of the next holy shock by 30% if u used 3 builders and crusader strike is a builder
            builder += 1;
            // if statement to check if the builder is equal to 3 then add 1 to the dusk and dawn buff and set the builder to 0
            if (builder >= 3) {
                duskAndDawn = 1;
                builder = 0;
            }
          
            // Calculate base damage as 107.1% of mainStat
            int baseDamage = (int) (mainStat * 1.071);
            System.out.println("Base Damage: " + baseDamage);
            holyPower += 1; // Gain Holy Power
            
            // Convert versatility to percentage versatility has the value of 205 so we divide it by 205 to get the percentage
            double versatilityPercent = versatility / 205.0; 
            int roundedVersatilityAsInt = (int) Math.round(versatilityPercent);
            
            // Convert crit chance to a fraction
            double critChancePossibility = critChance / 100.0; // Use 100 for percentage
            
            // Calculate versatility bonus damage based on rounded value
            double versatilityBonus = baseDamage / 100.0 * roundedVersatilityAsInt;
            
            // Calculate crit bonus damage
            double critDamageMultiplier = 2.0; // 200% damage on crit
            double normalDamage = baseDamage + versatilityBonus;
            double critBonus = (baseDamage + versatilityBonus) * critDamageMultiplier;
            
            // Use randomness to determine if it's a critical hit
            boolean isCriticalHit = random.nextDouble() < critChancePossibility;
            double totalDamage = isCriticalHit ? critBonus : normalDamage;
             // Apply Dusk and Dawn buff if active
            if (duskAndDawn == 1) {
                totalDamage *= 1.3; // Apply 30% additional damage
                duskAndDawn = 0; // Reset buff after use
                }
          
            System.out.println("Critical Hit: " + isCriticalHit);
            // return the total damage
            return (int) totalDamage;
        }
  
        
        
        
        
        
        
        // Method to calculate the damage of the holyShock ability
      
        public int holyShock() {

            if (holyShockCharges <= 0) {
                return 0; // No charges available
            }
        
            int baseDamage = (int) (mainStat * 1.2);
            holyPower += 1; // Gain Holy Power
            holyShockCharges -= 1; // Consume a charge
            
            // Convert versatility to percentage versatility has the value of 205 so we divide it by 205 to get the percentage
            double versatilityPercent = versatility / 205.0; 
            int roundedValueAsInt = (int) Math.round(versatilityPercent);
            
            // calculate the crit chance  and set it to a double named critChancePossiblility
            double critChancePossiblility = critChance / 100.0; 
        
            // Calculate versatility bonus damage based on rounded value
            double versatilityBonus = baseDamage / 100.0 * roundedValueAsInt;
        
            // Calculate crit bonus damage
            double critDamageMultiplier = 2.0; // 200% damage on crit
            double normalDamage = baseDamage + versatilityBonus;
            double critBonus = (baseDamage + versatilityBonus) * critDamageMultiplier;
        
            // Use randomness to determine if it's a critical hit
            boolean isCriticalHit = random.nextDouble() < critChancePossiblility;
            double totalDamage = isCriticalHit ? critBonus : normalDamage;
            System.out.println("crit: " + isCriticalHit);
        
            return (int) totalDamage;
        }
        // method to calculate the damage of Judgment ability
        // ::TODO:: Implement the Judgment ability
        public int JudgeMent() {
            int baseDamage = (int) (mainStat * 1.2);
            holyPower += 1; // Gain Holy Power

            double totalDamage = baseDamage;

            return (int) totalDamage;
        }

        public int getHolyPower() {
            return holyPower;
        }

        // Method to set main stat
        public void setMainStat(int mainStat) {
            this.mainStat = mainStat;
        }

        // Method to set versatility
        public void setVersatility(int versatility) {
            this.versatility = versatility;
        }
        // Method to set crit chance
        public void setCritChance(int critChance) {
            this.critChance = critChance;
        }
    }
}
