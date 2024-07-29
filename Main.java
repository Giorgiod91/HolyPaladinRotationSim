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
        System.out.println("HolyShock damage: " + holyShockDamage);
        
        //System.out.println("JudgeMent damage: " + JudgeMentDamage);

        


         
        //System.out.println("HolyShock damage: " + holyShockDamage);
       
  

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
        // method to calculate the damage of Consecration 
        public int Consecration() {
            int numberOfTicks = 18; //there are 18 ticks in the duration of the spell according to wowhead
            double totalDamage = 0;
        
            // Base damage per tick calculation according to tooltip
            double baseDamage = mainStat * 0.05;
        
            System.out.println("Base Damage per tick before versatility: " + baseDamage);
        
            // Convert versatility to a percentage increase
            double versatilityPercent = versatility / 205.0;
            double versatilityBonus = baseDamage * versatilityPercent / 100.0;
        
            // Calculate crit multiplier
            double critMultiplier = 2.0; // 200% damage on crit
        
            // Loop through each tick
            for (int i = 0; i < numberOfTicks; i++) {
                double tickDamage = baseDamage + versatilityBonus;
                System.out.println(tickDamage);
        
                // Check for critical hit
                boolean isCriticalHit = random.nextDouble() < (critChance / 100.0);
        
                // Apply crit multiplier if it's a critical hit
                if (isCriticalHit) {
                    tickDamage *= critMultiplier;
                }
        
                // the total damage
                totalDamage += tickDamage;
            }
        
            // Print the total damage 
            System.out.println("Total Consecration damage: " + totalDamage);
            
            return (int) totalDamage;
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
            holyPower += 1; // Gain Holy Power cause its a builder
            
            // Convert versatility to percentage versatility has the value of 205 ind world of warcraft so we divide it by 205 to get the percentage
            double versatilityPercent = versatility / 205.0; 
            int roundedVersatilityAsInt = (int) Math.round(versatilityPercent);
            
            // Convert crit chance to percentage 
            double critChancePossibility = critChance / 100.0; 
            
            // Calculate versatility bonus damage based on rounded value
            double versatilityBonus = baseDamage / 100.0 * roundedVersatilityAsInt;
            
            // Calculate crit bonus damage
            double critDamageMultiplier = 2.0; // 200% damage on crit cause crits does 200% damage in world of warcraft
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
            holyPower += 1; // Gain Holy Power cause its a builder
            holyShockCharges -= 1; // Consume a charge of Holy Shock on use 
            
            // Convert versatility to percentage versatility has the value of 205 so we divide it by 205 to get the percentage
            double versatilityPercent = versatility / 205.0; 
            int roundedValueAsInt = (int) Math.round(versatilityPercent);
            
            // calculate the crit chance  and set it to a double named critChancePossiblility
            double critChancePossiblility = critChance / 100.0; 
        
            // Calculate versatility bonus damage based on rounded value
            double versatilityBonus = baseDamage / 100.0 * roundedValueAsInt;
        
            // Calculate crit bonus damage
            double critDamageMultiplier = 2.0; // 200% damage on crit cause crits does 200% damage in world of warcraft
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
             // add 1 to the builder cause i want to be able to keep track of the dusk and dawn buff that increases the damage of the next holypower spender by 30% if u used 3 builders and Judgement is a builder
             builder += 1;
             // if statement to check if the builder is equal to 3 then add 1 to the dusk and dawn buff and set the builder to 0
             if (builder >= 3) {
                 duskAndDawn = 1;
                 builder = 0;
             }
            int baseDamage = (int) (mainStat * 1.125);
            holyPower += 1; // Gain Holy Power cause its a builder 

              // Convert versatility to percentage versatility has the value of 205 so we divide it by 205 to get the percentage
              double versatilityPercent = versatility / 205.0; 
              int roundedValueAsInt = (int) Math.round(versatilityPercent);
              
              // calculate the crit chance  and set it to a double named critChancePossiblility
              double critChancePossiblility = critChance / 100.0; 
          
              // Calculate versatility bonus damage based on rounded value
              double versatilityBonus = baseDamage / 100.0 * roundedValueAsInt;
          
              // Calculate crit bonus damage
              double critDamageMultiplier = 2.0; // 200% damage on crit cause crits does 200% damage in world of warcraft
              double normalDamage = baseDamage + versatilityBonus;
              double critBonus = (baseDamage + versatilityBonus) * critDamageMultiplier;
          
              // Use randomness to determine if it's a critical hit
              boolean isCriticalHit = random.nextDouble() < critChancePossiblility;
              double totalDamage = isCriticalHit ? critBonus : normalDamage;
              System.out.println("crit: " + isCriticalHit);


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
