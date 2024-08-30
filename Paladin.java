import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
//TODO: Create a priority list for the rotation 

public class Paladin {
    private int holyPower = 0;
    private int holyShockCharges = 2;
    private int mainStat;
    private int versatility;
    private int builderCount = 0;
    private int holyShockCounter = 0;
    private int critChance = 0;
    private Random random = new Random();
    private int totalDamageOne = 0;
    private int Time = 0;
   
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


    /// timers 
    private Timer avengingWrathTimer = new Timer();
    private Timer holyShockCooldownTimer = new Timer();
    private Timer judgmentCooldownTimer = new Timer();
    private Timer crusaderStrikeCooldownTimer = new Timer();
    private Timer divineTollCooldownTimer = new Timer();


    // Getters and Setters
    public int getHolyShockCharges() { return holyShockCharges; }
    public int getHolyPower() { return holyPower; }
    public int OverAllDamage() { return totalDamageOne; }
    public int getTime() { return Time; }
    public boolean DoeshasSunsAvatarSkilled() { return hasSunsAvatarSkilled; }
    public void sethasSunsAvatarSkilled(boolean hasSunsAvatarSkilled) { this.hasSunsAvatarSkilled = hasSunsAvatarSkilled; }

    // Start the timer to track the duration
    public void StartTimer() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() { Time++; }
        }, 0, 1000);
    }

    // Execute the basic rotation of abilities
    public void basicRotationForOneTarget() {
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
        
        // Add debug information here
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

    // Simulate the Hammer of Wrath ability and its cooldown of 16 seconds
    public void HammerOfWrath() {
        if (HammerOfWrathOnCooldown) {
            System.out.println("Hammer of Wrath is on cooldown!");
            return;
        }
        
        double baseDamage = mainStat * 1.1067;
        if (EnemyIsBelow20 || HammerOfWrathUsable) {
            double totalDamage = baseDamage;
            totalDamage = applyVersatilityAndCrit(totalDamage);
            totalDamageOne += (int) totalDamage;
            System.out.println("Hammer of Wrath damage: " + totalDamage);
    
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

    // Activate Avenging Wrath for increased crit chance
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

        // Apply Dusk and Dawn buff if active
        if (duskAndDawnActive) {
            totalDamage *= 1.3; // Apply 30% additional damage
            duskAndDawnActive = false; // Reset the buff after use
        }

        // Apply versatility and crit modifiers
        totalDamage = applyVersatilityAndCrit(totalDamage);

        totalDamageOne += (int) totalDamage;
        System.out.println("Shield of the Righteous damage: " + totalDamage);
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

        System.out.println("Total Consecration damage: " + totalDamage);
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
        holyPower += 1;
        builderCount += 1;
    
        // Check if we have used 3 builders
        if (builderCount == 3) {
            duskAndDawnActive = true; 
            System.out.println("Dusk and Dawn buff is now active");
            builderCount = 0; 
        }
    
        double totalDamage = applyVersatilityAndCrit(baseDamage);
        System.out.println("Crusader Strike damage: " + totalDamage);
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
    public int holyShock() {
        int totalHolyShockDamage = 0;
    
        // Check if Holy Shock is on cooldown
        if (HolyShockOnCooldown) {
            System.out.println("Holy Shock is on cooldown!");
            return totalHolyShockDamage; 
        }
    
        // Check if Rising Sunlight is active
        if (RisingSunlight) {
            System.out.println("Rising Sunlight active: Holy Shock will be cast 3 times!");
    
            // Cast Holy Shock 3 times, but only consume 1 charge
            for (int i = 0; i < 3; i++) {
                if (holyShockCharges <= 0) break; 
                holyShockCharges += 3;
                holyShockCounter++;
                System.out.println("Holy Shock cast #" + holyShockCounter);
    
                int baseDamage = (int) (mainStat * 1.2);
                holyPower += 1;
                builderCount += 1;
    
                // Check for Dusk and Dawn buff activation
                if (builderCount >= 3) {
                    duskAndDawnActive = true;
                    builderCount = 0;
                    System.out.println("Dusk and Dawn buff is now active.");
                }
    
                double totalDamage = applyVersatilityAndCrit(baseDamage);
                System.out.println("Holy Shock damage: " + totalDamage);
                totalHolyShockDamage += (int) totalDamage;  
                totalDamageOne += (int) totalDamage;  
            }
    
            holyShockCharges -= 1;
            RisingSunlight = false;

    
        } else {
            // Normal single Holy Shock cast
            if (holyShockCharges > 0) {
                holyShockCounter++;
                System.out.println("Holy Shock cast #" + holyShockCounter);
    
                int baseDamage = (int) (mainStat * 1.2);
                holyPower += 1;
                builderCount += 1;
                holyShockCharges -= 1;
    
                // Check for Dusk and Dawn buff activation
                if (builderCount >= 3) {
                    duskAndDawnActive = true;
                    builderCount = 0;
                    System.out.println("Dusk and Dawn buff is now active.");
                }
    
                double totalDamage = applyVersatilityAndCrit(baseDamage);
                System.out.println("Holy Shock damage: " + totalDamage);
                totalHolyShockDamage += (int) totalDamage;  
                totalDamageOne += (int) totalDamage;  
    
                
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
    
        return totalHolyShockDamage;
    }
    

    

    // Simulate the Judgment ability
    public int JudgeMent() {
        if (JudgmentOnCooldown) {
            System.out.println("Judgment is on cooldown!");
            return 0;  
        }

        int baseDamage = (int) (mainStat * 1.125);
        holyPower += 1;
        builderCount += 1;

        // Check if we have used 3 builders
        if (builderCount >= 3) {
            duskAndDawnActive = true;
            builderCount = 0;
        }

        double totalDamage = applyVersatilityAndCrit(baseDamage);
        System.out.println("Judgment damage: " + totalDamage);
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


    // Method to apply versatility and crit modifiers to base damage
    private double applyVersatilityAndCrit(double baseDamage) {
        double versatilityPercent = versatility / 205.0;
        double versatilityBonus = baseDamage * versatilityPercent / 100.0;
        double critMultiplier = random.nextDouble() < (critChance / 100.0) ? 2.0 : 1.0;
        return (baseDamage + versatilityBonus) * critMultiplier;
    }

    // Setters for stats
    public void setMainStat(int mainStat) { this.mainStat = mainStat; }
    public void setVersatility(int versatility) { this.versatility = versatility; }
    public void setCritChance(int critChance) { this.critChance = critChance; }
}
