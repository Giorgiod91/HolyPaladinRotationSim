import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


//TODO: add the check if judgment crits so then hammer of wrath can be freely used

public class Paladin {
    private int holyPower = 0;
    private int holyShockCharges = 2;
    private int mainStat;
    private int versatility;
    private int builderCount = 0;
    private int holyShockCounter = 0;
    private int holyShockOverAllDamage = 0;
    private int critChance = 0;
    private Random random = new Random();
    private int totalDamageOne = 0;
    private int Time = 0;
    private int haste;
    private int weaponDamage;
    private int shieldOfRighteousCount = 0;
    private int WeaponCharges = 1;	
   
    //booleans 
    private boolean hasSunsAvatarSkilled = false;
    private boolean AvengingWrathIsUsed = false;
    private boolean consecrationActive = false;
    private boolean duskAndDawnActive = false;
    private boolean RisingSunlight = false;
    private boolean HammerOfWrathUsable = false;
    private boolean EnemyIsBelow20 = false;
    private boolean HammerOfWrathOnCooldown = false;
    private boolean HolyShockOnCooldown = false;
    private boolean JudgmentOnCooldown = false;
    private boolean crusaderStrikeOnCooldown = false;
    private boolean divineTollOnCooldown = false;
    private boolean DawnLightIsActive =false;
    private boolean hasVenerationSkilled = false;
    private boolean critHappened = false;
    private boolean hasMorningStarSkilled = true;
    private boolean hasLightsmithSkilled = false;


    /// timers 
    private Timer avengingWrathTimer = new Timer();
    private Timer holyShockCooldownTimer = new Timer();
    private Timer judgmentCooldownTimer = new Timer();
    private Timer crusaderStrikeCooldownTimer = new Timer();
    private Timer divineTollCooldownTimer = new Timer();

    // user input filter
    private String theRealInput = "";
    //user input
    private String InputFromUser = "";


    // Getters and Setters
    public int getHolyShockCharges() { return holyShockCharges; }
    public int getHolyPower() { return holyPower; }
    public int OverAllDamage() { return totalDamageOne; }
    public int getTime() { return Time; }
    public boolean DoeshasSunsAvatarSkilled() { return hasSunsAvatarSkilled; }
    public void sethasSunsAvatarSkilled(boolean hasSunsAvatarSkilled) { this.hasSunsAvatarSkilled = hasSunsAvatarSkilled; }
    public void sethasVenerationSkilled(boolean hasVenerationSkilled) {this.hasVenerationSkilled = hasVenerationSkilled;}
    public void sethasLightsmithSkilled(boolean hasLightsmithSkilled) {this.hasLightsmithSkilled = hasLightsmithSkilled;}

    // Start the timer to track the duration
    public void StartTimer() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() { Time++; }
        }, 0, 1000);
    }

    //method to check what the user wants to display 

    public void WhatToDisplay(String InputFromUser) {

        //::TODO:: switch up this if statement to a switch statement or something more efficient

        // if the user wants to see the overall damage done by the abilities we have used 
        if (InputFromUser.contains("overall damage")) {
            System.out.println("The overall damage done by the abilities is: " + totalDamageOne);
        }
        if (InputFromUser.contains("holy shock charges")) {
            System.out.println("The amount of Holy Shock charges are: " + holyShockCharges);
        }
        if (InputFromUser.contains("holy power")) {
            System.out.println("The amount of Holy Power is: " + holyPower);
        }
        if (InputFromUser.contains("time")) {
            System.out.println("The time is: " + Time);
        }
        if (InputFromUser.contains("main stat")) {
            System.out.println("The main stat is: " + mainStat);
        }
        if (InputFromUser.contains("versatility")) {
            System.out.println("The versatility is: " + versatility);
        }
        if (InputFromUser.contains("crit chance")) {
            System.out.println("The crit chance is: " + critChance);
        }
        if (InputFromUser.contains("haste")) {
            System.out.println("The haste is: " + haste);
        }
        if (InputFromUser.contains("weapon damage")) {
            System.out.println("The weapon damage is: " + weaponDamage);
        }
        if (InputFromUser.contains("shield of righteous count")) {
            System.out.println("The amount of Shield of Righteous used is: " + shieldOfRighteousCount);
        }
        if (InputFromUser.contains("holy shock counter")) {
            System.out.println("The amount of Holy Shock used is: " + holyShockCounter);
        }
        if (InputFromUser.contains("holy shock overall damage")) {
            System.out.println("The overall damage done by Holy Shock is: " + holyShockOverAllDamage);
        }
        if (InputFromUser.contains("dusk and dawn active")) {
            System.out.println("Dusk and Dawn is active: " + duskAndDawnActive);
        }
        if (InputFromUser.contains("rising sunlight active")) {
            System.out.println("Rising Sunlight is active: " + RisingSunlight);
        }
        if (InputFromUser.contains("hammer of wrath usable")) {
            System.out.println("Hammer of Wrath is usable: " + HammerOfWrathUsable);
        }
        if (InputFromUser.contains("enemy below 20")) {
            System.out.println("Enemy is below 20%: " + EnemyIsBelow20);
        }
        if (InputFromUser.contains("hammer of wrath on cooldown")) {
            System.out.println("Hammer of Wrath is on cooldown: " + HammerOfWrathOnCooldown);
        }
        if (InputFromUser.contains("holy shock on cooldown")) {
            System.out.println("Holy Shock is on cooldown: " + HolyShockOnCooldown);
        }
        if (InputFromUser.contains("judgment on cooldown")) {
            System.out.println("Judgment is on cooldown: " + JudgmentOnCooldown);
        }
        if (InputFromUser.contains("crusader strike on cooldown")) {
            System.out.println("Crusader Strike is on cooldown: " + crusaderStrikeOnCooldown);
        }
        if (InputFromUser.contains("divine toll on cooldown")) {
            System.out.println("Divine Toll is on cooldown: " + divineTollOnCooldown);
        }
        if (InputFromUser.contains("dawn light active")) {
            System.out.println("Dawn Light is active: " + DawnLightIsActive);
        }


        // setting the methods local value to the instance variable 
        this.InputFromUser = InputFromUser;
    }

    //creating a List to later filter out the the print statements cause there are many overlapping of them atm
     private List<String> printStatementsToFilter = new ArrayList<>();

    public void SortInputForUserNeeds() {
        String userInput = ""; 
        for (String printStatementsToFilter1 : printStatementsToFilter) {
           
        }
        


       
    }



    //:TODO:: use some sorting algorithym to filter the needed values depending on the users need
    // trying to do this 

    



    // Execute the basic rotation of abilities
    public void basicRotationForOneTarget() {
        // createing hashmap for the rotation priority it turns those strings into integers to then be used in the rotation
        Map<String, Integer> abilityPriorities = new HashMap<>();
        abilityPriorities.put("Consecration", 2);
        abilityPriorities.put("AvengingWrath", 1);
        abilityPriorities.put("JudgeMent", 3);
        abilityPriorities.put("divineToll", 4);
        abilityPriorities.put("ShieldOfRighteous", 5);
        abilityPriorities.put("crusaderStrike", 6);
        abilityPriorities.put("holyShock", 7);
        abilityPriorities.put("HammerOfWrath", 8);

        if(!consecrationActive) {
            abilityPriorities.put("Consecration", 1);
        }
        if(!AvengingWrathIsUsed) {
            abilityPriorities.put("AvengingWrath", 2);
        }
        if(!HolyShockOnCooldown) {
            abilityPriorities.put("holyShock", 3);
        }
        
        
        int maxRotations = 0;
        AvengingWrath();
        System.out.println("Crit chance after using Wrath is: " + critChance);
        for (int i = 0; i < 3; i++) {
            if (Time % 12 == 0 && Time > 0 && maxRotations <= 4) {
                Consecration();
                maxRotations++;
            }
        }
        System.out.println("Current crit is: " + critChance);
        JudgeMent();
        divineToll();
        ShieldOfRighteous();
        crusaderStrike();
        
       
        System.out.println("Checking Holy Shock status...");
        if (!HolyShockOnCooldown) {
            System.out.println("Holy Shock is not on cooldown. Using it now...");
            holyShock();
        } else {
            System.out.println("Holy Shock is on cooldown.");
        }
    
        HammerOfWrath();
    }
    
    //::TODO:: Add the Blessing of Summer dmg convert healing into dmg
    public void BlessingOfSummer() {
        
       
    }
    public void SacredWeapon() {
        double baseDamage = mainStat * 1.04;
        WeaponCharges --;
        double totalDamage = applyVersatilityAndCrit(baseDamage);
        totalDamageOne += (int) totalDamage;
        printStatementsToFilter.add("Sacred Weapon damage: " + totalDamage);




    }


    // Simulate the Hammer of Wrath ability and its cooldown of 16 seconds
    public void HammerOfWrath() {
        if (HammerOfWrathOnCooldown) {
            System.out.println("Hammer of Wrath is on cooldown!");
            return;
        }
        
        double baseDamage = mainStat * 1.1067;
        // applying the buff from the latest patch notes hammer of wrath got buffed by 20%
        baseDamage *= 1.2;
        
       
        if (EnemyIsBelow20 || HammerOfWrathUsable) {
            double totalDamage = baseDamage;
            totalDamage = applyVersatilityAndCrit(totalDamage);
            totalDamageOne += (int) totalDamage;
            // adding the print statement into the List so i can later filter and search for thos specific ones in the frontend depending on what the user wants to see
            printStatementsToFilter.add("Hammer of Wrath damage: " + totalDamage);
    
            // Start cooldown
            HammerOfWrathOnCooldown = true;
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    HammerOfWrathOnCooldown = false;
                    System.out.println("Hammer of Wrath is ready to use again.");
                }
            }, 16000);
        }
    }

     
    //holyprism method
    //TODO: add the prism aoe dmg !!!!
    public void HolyPrism() {
        int baseDamage = (int) (mainStat * 2.16);
        // applying the buff from the latest patch notes holy prism got buffed by 100%
        baseDamage *= 2;
        holyPower += 1;
        builderCount += 1;
        double totalDamage = applyVersatilityAndCrit(baseDamage);
        // adding the print statement into the List so i can later filter and search for thos specific ones in the frontend depending on what the user wants to see
        printStatementsToFilter.add("Holy Prism damage: " + totalDamage);
        
        totalDamageOne += (int) totalDamage;
    }

    // Activate Avenging Wrath for increased crit chance
    //:TODO: refactor the suns avatar dmg modifier to be in line with the current game patch
    public void AvengingWrath() {
        if (AvengingWrathIsUsed) return;
        if (hasSunsAvatarSkilled) {
          
            double sunsAvatarDamage = 12 * (mainStat * 0.18);
            totalDamageOne += sunsAvatarDamage;
        }
        HammerOfWrathUsable = true;
        RisingSunlight = true;
        holyShockCharges +=3;
        AvengingWrathIsUsed = true;
        critChance += 15;
        avengingWrathTimer.cancel();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                critChance -= 15;
                AvengingWrathIsUsed = false;
                HammerOfWrathUsable = false;
            }
        }, 20000);
    }
    // Simulate the damage from the Dawnlight ability

    public void DawnLight(){
        DawnLightIsActive = true;
        
        int baseDamage = (int) (mainStat * 3);
        int partOfTheDamage = baseDamage /100 *8;
        // this ability is up for 8 seconds
        double totalDamage = 0;
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int tickCount = 0;
            @Override
            public void run() {

                double tickDamage;
                if(tickCount ==0){
                    // using the dmg modifier tthat icnreased the first hit of the dawnlight by 50%
                    tickDamage = applyVersatilityAndCrit(baseDamage) * 1.5;
                } else {
                    // and else i use the regular default modfifiers
                    tickDamage = applyVersatilityAndCrit(baseDamage);


                }




                totalDamage += tickDamage;
                totalDamageOne += tickDamage;
                tickCount++;

                if(tickCount >=8){
                    timer.cancel();
                }


            }
        },0, 1000); // 0 delay, repeat every 1000 ms

    }

    // Simulate the Shield of the Righteous ability
    public int ShieldOfRighteous() {
       

        if (holyPower < 3) {
            System.out.println("Not enough Holy Power!");
            return 0;
        }

        double baseDamage = mainStat * 1.28;
        holyPower -= 3; // Consumes 3 Holy Power to use SoR

        if (consecrationActive) {
            baseDamage = baseDamage * 1.2;
            System.out.println("20% increased damage active ");
        }

        double totalDamage = baseDamage;
        if (DawnLightIsActive) {
            totalDamage *= 1.05;
            System.out.println("5% increased damage from  Dawnlight.");
        }


        // Apply Dusk and Dawn buff if active
        if (duskAndDawnActive) {
            totalDamage *= 1.3; // Apply 30% additional damage
            duskAndDawnActive = false; // Reset the buff after use
        }

        // Apply versatility and crit modifiers
        totalDamage = applyVersatilityAndCrit(totalDamage);

        totalDamageOne += (int) totalDamage;

        shieldOfRighteousCount++;
        // adding the print statement into the List so i can later filter and search for thos specific ones in the frontend depending on what the user wants to see
        printStatementsToFilter.add("Shield of the Righteous damage: " + totalDamage);       
        System.out.println("the amount of shield of the righteous are used" + shieldOfRighteousCount);
        return (int) totalDamage;
    }

    // Simulate the Consecration ability
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
        printStatementsToFilter.add("Total Consecration damage: " + totalDamage);
        totalDamageOne += (int) totalDamage;
        return (int) totalDamage;
    }

    // Simulate the Divine Toll ability
    public int divineToll() {
        if(divineTollOnCooldown) {
            System.out.println("Divine Toll is on cooldown!");
            return 0;
        }
        holyPower += 5;
        RisingSunlight = true;  // Activating Rising Sunlight buff
        divineTollOnCooldown = true;
    TimerTask cooldownTask = new TimerTask() {
        @Override
        public void run() {
            divineTollOnCooldown = false;
            System.out.println("Divine Toll is ready to use again.");
        }
    };
    divineTollCooldownTimer.schedule(cooldownTask, 45000);
        return 0;
    }

    // Simulate the Crusader Strike ability
    public int crusaderStrike() {
        if (crusaderStrikeOnCooldown) {
            System.out.println("Crusader Strike is on cooldown!");
            return 0;
        }
    
        int baseDamage = (int) (mainStat * 1.071);
        // Applying the buff from the lastest patch notes crusader strike got buffed by 60%
        baseDamage *= 1.6;
        holyPower += 1;
        builderCount += 1;
    
        // Check if we have used 3 builders
        if (builderCount == 3) {
            duskAndDawnActive = true; 
            System.out.println("Dusk and Dawn buff is now active");
            builderCount = 0; 
        }
    
        double totalDamage = applyVersatilityAndCrit(baseDamage);
        printStatementsToFilter.add("Crusader Strike damage: " + totalDamage);
        totalDamageOne += (int) totalDamage;
    
        // Start cooldown
        crusaderStrikeOnCooldown = true;
        TimerTask cooldownTask = new TimerTask() {
            @Override
            public void run() {
                crusaderStrikeOnCooldown = false;
                System.out.println("Crusader Strike is ready to use again.");
            }
        };
        crusaderStrikeCooldownTimer.schedule(cooldownTask, 9200); 
        return (int) totalDamage;
    }
    


   
    // Simulate the Holy Shock ability with the Rising Sunlight talent
  // Simulate the Holy Shock ability with the Rising Sunlight talent

  public int holyShock() {
    int totalHolyShockDamage = 0;
    int howMuchDidShockDo = 0;
    

    // Check if Holy Shock is on cooldown
    if (HolyShockOnCooldown) {
        System.out.println("Holy Shock is on cooldown!");
        return totalHolyShockDamage; 
    }
    if(hasSunsAvatarSkilled) {
        critChance += 5;
    }

    // Check if Rising Sunlight is active
    if (RisingSunlight) {
    // Activate Rising Sunlight: Holy Shock will be cast 3 times so we add 3 charges
        holyShockCharges += 3;
        System.out.println("Rising Sunlight active: Holy Shock will be cast 3 times!");

        // Cast Holy Shock 3 times, but only consume 1 charge
        for (int i = 0; i < 3; i++) {
            if (holyShockCharges <= 0) break;             
            holyShockCounter++;


            System.out.println("Holy Shock cast #" + holyShockCounter);

            int baseDamage = (int) (mainStat * 1.08);
            holyPower += 1;
            builderCount += 1;
            holyShockOverAllDamage += baseDamage;


            // Check for Dusk and Dawn buff activation
            if (builderCount >= 3) {
                duskAndDawnActive = true;
                builderCount = 0;
                System.out.println("Dusk and Dawn buff is now active.");
                
            }

            double totalDamage = applyVersatilityAndCrit(baseDamage);
            printStatementsToFilter.add("Holy Shock damage: " + totalDamage);
            totalHolyShockDamage += (int) totalDamage;  
            totalDamageOne += (int) totalDamage;  
            holyShockOverAllDamage += (int) totalDamage;
            
           
        }

        holyShockCharges -= 1;
        RisingSunlight = false;


    } else {
        // Normal single Holy Shock cast
        if (holyShockCharges > 0) {
            holyShockCounter++;
            System.out.println("Holy Shock cast #" + holyShockCounter);

            int baseDamage = (int) (mainStat * 1.08);
            holyPower += 1;
            builderCount += 1;
            holyShockCharges -= 1;
            holyShockOverAllDamage += baseDamage;

            // Check for Dusk and Dawn buff activation
            if (builderCount >= 3) {
                duskAndDawnActive = true;
                builderCount = 0;
                System.out.println("Dusk and Dawn buff is now active.");
            }

            double totalDamage = applyVersatilityAndCrit(baseDamage);
           
            totalHolyShockDamage += (int) totalDamage;  
            totalDamageOne += (int) totalDamage;  
            holyShockOverAllDamage += (int) totalDamage;

            
            if (holyShockCharges <= 0) {
                HolyShockOnCooldown = true;
                TimerTask cooldownTask = new TimerTask() {
                    @Override
                    public void run() {
                        HolyShockOnCooldown = false;
                        System.out.println("Holy Shock is ready to use again.");
                        holyShockCharges = 1; // Reset charges after cooldown
                    }
                };
                holyShockCooldownTimer.schedule(cooldownTask, 7100); 
            }

        } else {
            System.out.println("No Holy Shock charges available.");
        }
    }

    //System.out.println("overall Holy Shock dmg :" );
    printStatementsToFilter.add("total Holy Shock damage: " + totalHolyShockDamage);
    printStatementsToFilter.add("Overall Holy Shock damage: " + holyShockOverAllDamage);
    
      
    return totalHolyShockDamage;
   
}

    // Simulate Melee Attack
    public int MeleeAttack() {
        //TODO: add the melee attack dmg modifier

    }
    

    

    // Simulate the Judgment ability
    public int JudgeMent() {
        if (JudgmentOnCooldown) {
            System.out.println("Judgment is on cooldown!");
            return 0;  
        }

        int baseDamage = (int) (mainStat * 0.610542);
        // applying current class buffs to my method judgement got buffed by 6ß%
        baseDamage *= 1.6;
        holyPower += 1;
        builderCount += 1;

        // Check if we have used 3 builders
        if (builderCount >= 3) {
            duskAndDawnActive = true;
            builderCount = 0;
        }

        double totalDamage = applyVersatilityAndCrit(baseDamage);
        printStatementsToFilter.add("Judgment damage: " + totalDamage);
       
        totalDamageOne += (int) totalDamage;

        // Start cooldown
        JudgmentOnCooldown = true;
        judgmentCooldownTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                JudgmentOnCooldown = false;
                System.out.println("Judgment is ready to use again.");
            }
        }, 9200); 

        return (int) totalDamage;
    }
    //method to add damage to the total damage from trinkets or enchants for example
    public void addDamage(int damage) {
        this.totalDamageOne += damage;
    }


    // Method to apply versatility and crit modifiers to base damage
    private double applyVersatilityAndCrit(double baseDamage) {
        double versatilityPercent = versatility / 205.0;
        double versatilityBonus = baseDamage * versatilityPercent / 100.0;
        //crit chance depending on the crit value the user has and then double the damage if the crit appears to be true
        double critMultiplier = random.nextDouble() < (critChance / 100.0) ? 2.0 : 1.0 ;
        
        double hasteMultiplier = 1.0; // Haste not implemented yet
        return (baseDamage + versatilityBonus) * critMultiplier;
    }

    // Setters for stats
    public void setMainStat(int mainStat) { this.mainStat = mainStat; }
    public void setVersatility(int versatility) { this.versatility = versatility; }
    public void setCritChance(int critChance) { this.critChance = critChance; }
    public void setHasteChance(int hasteChance) { this.critChance = hasteChance; }

    //getters for stats
    public int getMainStat() {
        return mainStat;
    }

    public int getVersatility() {
        return versatility;
    }

    public int getCritChance() {
        return critChance;
    }
    public int getHasteChance() {
        return haste;
    }

}

   