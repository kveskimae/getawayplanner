package com.foo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class GetAwayPlannerIntegrationTests {

	@Autowired
	private GetAwayPlanner planner;

	@Test
	public void contextLoads() {
		assertNotNull(planner);
	}

	@Test
	public void testEnd2End() {
		String result = planner.planGetAway();

		assertTrue(
				result.startsWith(
						"Team 1:\n" +
								"09:00 AM : Duck Herding 60min"
				)
		);
	}

}
