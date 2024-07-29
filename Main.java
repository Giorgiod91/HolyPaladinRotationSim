import java.util.Random;

import java.util.Random;

public class Main {

    public static void main(String[] args) { 
        // Creating a new Paladin instance
        Paladin holymoly = new Paladin();
        // Setting the main stat and versatility of the Paladin
        holymoly.setMainStat(19797);
        holymoly.setVersatility(6336);
        
        // Calling the holyShock method to calculate the damage
        int damage = holymoly.holyShock();
        // Printing the damage
        System.out.println("Damage: " + damage);
    }

    public static class Paladin {
        // Declaring variables for the Paladin class 
        private int holyPower = 0;
        private int holyShockCharges = 1;
        private int mainStat;
        private int versatility;
        
        public int getHolyShockCharges() {
            return holyShockCharges;
        }
        // Method to regenerate the holyShock charges
        public void regenerateHolyShockCharge() {
            if (holyShockCharges < 2) {
                holyShockCharges++;
            }
        }
        // Method to calculate the damage of the holyShock ability
        public int holyShock() {
            if (holyShockCharges <= 0) {
                return 0; // No charges available
            }

            int baseDamage = (int) (mainStat * 1.2);
            System.out.println("Base Damage: " + baseDamage);
            holyPower += 1; // Gain Holy Power
            holyShockCharges -= 1; // Consume a charge
            

            // Convert versatility to percentage
            double versatilityPercent = versatility / 205.0; // 205.0 = 30.9%
            // round to 2 decimal places
            int roundedValueAsInt = (int) Math.round(versatilityPercent);
            System.out.println("Versatility Percent: " + roundedValueAsInt);
            // calculate versatility bonus damage based on rounded value so we can apply it to the base damage
            double versatilityBonus = baseDamage / 100 * roundedValueAsInt;

            // Apply versatility bonus
            double totalDamage = versatilityBonus + baseDamage;
            System.out.println("baseDamage: " + baseDamage);
            // return the total damage
    
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
    }
}
