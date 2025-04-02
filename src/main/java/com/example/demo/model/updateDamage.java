package com.example.demo.model;
import com.example.demo.service.PaladinService;
//::TODO: create and finish these for later use for now its hard coded updates 
// Removed invalid import statement

public class updateDamage {

    private static int modifier = 0;
    private static int totalDamage = 0;


    public static void main(String[] args) {
        // Example logic to call updateModifier
        updateModifier(5);
    }

    public static void updateModifier(int modifier) {
        // create new instance and get the current total damage of shield dmg 
        PaladinService paladinService = new PaladinService();
        totalDamage = paladinService.getShieldDamage();
        totalDamage *= modifier;

               


     
        
    }



        
    }


    

