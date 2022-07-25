package com.smart.equip.randomnumbersum;

import com.smart.equip.randomnumbersum.controller.RandomNumSumController;
import com.smart.equip.randomnumbersum.service.RandomNumCreateService;
import com.smart.equip.randomnumbersum.service.RandomNumSumCheckerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = RandomNumSumController.class)
public class RandomNumberSumApplicationTests {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private RandomNumCreateService randomNumCreateService;

	@MockBean
	private RandomNumSumCheckerService randomNumSumCheckerService;

//	@Test
//	void contextLoads() {
//	}

}
