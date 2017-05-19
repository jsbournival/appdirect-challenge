package com.appdirect.controllers;

import static com.appdirect.controllers.AbstractController.URL_PARAMETER;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.appdirect.ErrorCode;
import com.appdirect.services.SubscriptionService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class CreateSubscriptionControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SubscriptionService subscriptionService;

	@Test
	public void testWithNoUrlParameter() throws Exception {
		this.mockMvc.perform(get("/subscription/create")).andDo(print()).andExpect(status().isBadRequest());
	}

	@Test
	public void testWithInvalidEventUrl() throws Exception {
		this.mockMvc.perform(get("/subscription/create").param(URL_PARAMETER, "hggp://www.appdirect")).andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(content().string(containsString(ErrorCode.UNKNOWN_ERROR.name())));
	}
	
	@Test
	public void testWithBadEventUrl() throws Exception {
		this.mockMvc.perform(get("/subscription/create").param(URL_PARAMETER, "http://www.jsbournival.com")).andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(content().string(containsString(ErrorCode.UNKNOWN_ERROR.name())));
	}
}
