import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


public class Main {

    public static void main(String[] args) { 
        // Creating a new Paladin instance
        Paladin holymoly = new Paladin();
        // Setting the main stat, versatility and crit of the Paladin
        holymoly.setMainStat(60170);
        holymoly.setVersatility(13670);
        holymoly.setCritChance(19);

        // create enchants or trinket
        TrinktesOrEnchants trinket = new TrinktesOrEnchants();
        trinket.RadiantPowerEnchant(holymoly);
        // using java scanner to get user input
        Scanner scanner = new Scanner(System.in);
        System.out.print("are you using herald of the Sun Hero Talent? type yes or no");
        String heraldYesOrNo = scanner.nextLine();
        if(heraldYesOrNo.contains("yes")) {
            holymoly.sethasSunsAvatarSkilled(true);

        }else if (heraldYesOrNo.contains("no")){
            holymoly.sethasSunsAvatarSkilled(false);
        }

        
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
                holymoly.basicRotationForOneTarget();
                System.out.println("Time is :" + holymoly.getTime());
                System.out.println("The Overall Dmg is :" + holymoly.OverAllDamage());
                // dividing time by overall dmg to display the actual DPS u do so the damage per second
                System.out.println("DPS damage per second :" + holymoly.OverAllDamage() / holymoly.getTime());
                if(holymoly.getMainStat() == 62833 + 350) {
                    System.out.println("Radiant Power Enchant is working");
                }
                if(holymoly.getTime() > 30) {
                                  
                    testTimer.cancel();
                }
            }
                     
              }, 0 ,1000);
            }
        }

            