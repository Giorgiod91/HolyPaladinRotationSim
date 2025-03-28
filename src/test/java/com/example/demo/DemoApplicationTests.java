package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.Paladin;
@SpringBootTest
class DemoApplicationTests {
	//::TODO: test the dmg output with the current buffs from the path 11.0 !

	@Test
	void testHolyShock() {
		Paladin paladin = new Paladin();
		paladin.setMainStat(20000);
		paladin.setCritChance(20);
		paladin.setVersatility(20);


		int totalDamage = paladin.holyShock();


		assertThat(totalDamage).isGreaterThan(0);
		System.out.println("holyshock dmg is:" + totalDamage);
	}
	@Test 
	void testDamageModifer(){
		Paladin paladin = new Paladin();
		paladin.setMainStat(20000);
		paladin.setCritChance(20);
		paladin.setVersatility(20);
		//check if ingame dmg matches the calculated one
		paladin.basicRotationForOneTarget();



	}

	// test if the highest stat method work
	@Test
	void testHighestStatGetterSetter(){
		Paladin paladin = new Paladin();
		paladin.setMainStat(6);
		paladin.setCritChance(40);
		paladin.setVersatility(20);

		int shouldBeHighest = paladin.gethighestStat();

		assertThat(shouldBeHighest).isEqualTo(40);
	}



	// test the trinket if it really increases the highest
	@Test
	void testtrinket(){
		Paladin paladin = new Paladin();
		paladin.setMainStat(6);
		paladin.setCritChance(40);
		paladin.setVersatility(20);

	}
	// HolyShock should be prio with the current tuning
	@Test
	void testIfHolyShockIsPrio() {
		Paladin paladin = new Paladin();
		paladin.setMainStat(20000);
		paladin.setCritChance(20);
		paladin.setVersatility(20);
		
	



	}
		




}
