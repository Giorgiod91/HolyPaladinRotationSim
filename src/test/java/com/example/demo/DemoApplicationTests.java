package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.Paladin;
@SpringBootTest
class DemoApplicationTests {

	@Test
	void testHolyShock() {
		Paladin paladin = new Paladin();
		paladin.setMainStat(20000);
		paladin.setCritChance(20);
		paladin.setVersatility(20);


		int totalDamage = paladin.holyShock();


		assertThat(totalDamage).isGreaterThan(0);
	}

}
