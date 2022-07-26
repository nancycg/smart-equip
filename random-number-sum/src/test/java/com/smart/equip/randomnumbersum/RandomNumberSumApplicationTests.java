package com.smart.equip.randomnumbersum;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart.equip.randomnumbersum.controller.RandomNumSumController;
import com.smart.equip.randomnumbersum.model.Request;
import com.smart.equip.randomnumbersum.model.Response;
import com.smart.equip.randomnumbersum.service.RandomNumCreateService;
import com.smart.equip.randomnumbersum.service.RandomNumCreateServiceImpl;
import com.smart.equip.randomnumbersum.service.RandomNumSumCheckerService;
import com.smart.equip.randomnumbersum.service.RandomNumSumCheckerServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = RandomNumSumController.class)
public class RandomNumberSumApplicationTests {

	private static final String API_URI = "/smart-equip/verify";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RandomNumCreateService randomNumCreateService;

	@MockBean
	private RandomNumSumCheckerService randomNumSumCheckerService;

	@InjectMocks
	private RandomNumSumCheckerServiceImpl randomNumSumCheckerServiceImpl;

	@InjectMocks
	private RandomNumCreateServiceImpl randomNumCreateServiceImpl;

	private Request request;
	private Response response;
	private static ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	public void init() {
		request = mock(Request.class);
		response = mock(Response.class);
		randomNumSumCheckerServiceImpl = new RandomNumSumCheckerServiceImpl();
		randomNumCreateServiceImpl = new RandomNumCreateServiceImpl();
	}

	@AfterEach
	public void tearDown() {
		request = null;
		response = null;
	}

	public static String readStringFromFile(String filName) throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		Files.lines(Paths.get("src/test/resources", filName)).forEach(stringBuilder::append);
		return stringBuilder.toString();
	}

	public static <T> T readObjectFromFile(String filName, Class<T> classType) throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		Files.lines(Paths.get("src/test/resources", filName)).forEach(stringBuilder::append);
		return objectMapper.readValue(stringBuilder.toString(), classType);
	}

	@Test
	public void test_success_verify_sum_of_random_numbers() throws IOException {
		randomNumSumCheckerServiceImpl = spy(randomNumSumCheckerServiceImpl);

		Request request = readObjectFromFile("valid-request.json", Request.class);
		Response response = readObjectFromFile("valid-response.json", Response.class);

		Response actualResponse = randomNumSumCheckerServiceImpl.verifySum(request);
		assertEquals(response, actualResponse);
	}

	@Test
	public void test_wrong_sum_of_random_numbers() throws IOException {
		randomNumSumCheckerServiceImpl = spy(randomNumSumCheckerServiceImpl);

		Request request = readObjectFromFile("wrong-sum-request.json", Request.class);
		Response response = readObjectFromFile("valid-response.json", Response.class);

		Response actualResponse = randomNumSumCheckerServiceImpl.verifySum(request);
		assertNotEquals(response, actualResponse);
	}

	@Test
	void test_bad_request_http_status_on_wrong_sum() throws Exception {

		String request = readStringFromFile("wrong-sum-request.json");

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(API_URI)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(request);

		// assert here
		mockMvc.perform(requestBuilder).andExpect(status().isBadRequest());
	}

	@Test
	public void test_success_random_number_generated() {
		randomNumCreateServiceImpl = spy(randomNumCreateServiceImpl);

		Response actualResponse = randomNumCreateServiceImpl.getRandomNumbers();
		assert(actualResponse.getRandomNumbers().size() >= 2);
	}

}
