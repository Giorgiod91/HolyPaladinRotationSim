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

}
