import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args) { 
        // Creating a new Paladin instance
        Paladin holymoly = new Paladin();
        // Setting the main stat, versatility and crit of the Paladin
        holymoly.setMainStat(66895);
        holymoly.setVersatility(3740);
        holymoly.setCritChance(23);

        // Create enchants or trinket
        TrinketsOrEnchants trinket = new TrinketsOrEnchants();
        //trinket.RadiantPowerEnchant(holymoly);
        trinket.AscendanceEmbellishment(holymoly);
        
        // Using java scanner to get user input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Are you using Herald of the Sun Hero Talent? Type yes or no: ");
        String heraldYesOrNo = scanner.nextLine();
        if (heraldYesOrNo.contains("yes")) {
            holymoly.sethasSunsAvatarSkilled(true);
        } else if (heraldYesOrNo.contains("no")) {
            holymoly.sethasSunsAvatarSkilled(false);
        }

        // Testing Crusader Strike to build Holy Power and potentially activate Dusk and Dawn
        // System.out.println("Testing Crusader Strike:");
        // for (int i = 0; i < 9; i++) {
        //     int crusaderStrikeDamage = holymoly.crusaderStrike();
        //     System.out.println("Current Holy Power: " + holymoly.getHolyPower());
        //     System.out.println("Crusader Strike damage: " + crusaderStrikeDamage);
        // }

        // Simulation consecration damage 
        int consecrationDamage = holymoly.Consecration();
        System.out.println("Consecration Damage: " + consecrationDamage);
        
        // Now test Shield of the Righteous with enough Holy Power
        // System.out.println("\nTesting Shield of the Righteous:");
        // for (int i= 0; i < 3; i++) {
        //     int shieldOfRighteousDamage = holymoly.ShieldOfRighteous();
        //     System.out.println("Shield of the Righteous damage: " + shieldOfRighteousDamage);
        // }

        // Test Holy Shock
        // System.out.println("\nTesting Holy Shock:");
        // int holyShockDamage = holymoly.holyShock();
        // System.out.println("Holy Shock damage: " + holyShockDamage);
        
        // Test Judgment
        // System.out.println("\nTesting Judgment:");
        // int judgmentDamage = holymoly.JudgeMent();
        // System.out.println("Judgment damage: " + judgmentDamage);

        // Showing overall damage
        // System.out.println("Overall damage: " + holymoly.OverAllDamage());

        // Starting timer 
        holymoly.StartTimer();
        
        // Testing rotation method with 60 seconds DPS rotation
        Timer testTimer = new Timer();
        testTimer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                holymoly.basicRotationForOneTarget();
                System.out.println("Time is: " + holymoly.getTime());
                System.out.println("The Overall Damage is: " + holymoly.OverAllDamage());
                // Dividing time by overall damage to display the actual DPS
                System.out.println("DPS damage per second: " + holymoly.OverAllDamage() / holymoly.getTime());
                if (holymoly.getMainStat() == 62833 + 350) {
                    System.out.println("Radiant Power Enchant is working");
                }
                if (holymoly.getTime() > 30) {
                    testTimer.cancel();
                }
            }
        }, 0, 1000);
    }
}
