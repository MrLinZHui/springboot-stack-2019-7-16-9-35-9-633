package com.tw.apistackbase;

import org.json.JSONArray;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApiStackBaseApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@Test
	public void should_return_200_when_get_Employees() throws Exception {
		final MvcResult mvcResult = this.mockMvc.perform(get("/employees")).andExpect(status().isOk()).andReturn();
		JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());
		assertEquals(18,jsonArray.getJSONObject(0).getInt("age"));
		assertEquals("zhangsan",jsonArray.getJSONObject(0).getString("name"));
		assertEquals("male",jsonArray.getJSONObject(0).getString("gender"));
//		this.mockMvc.perform(get("/employees")).andDo(print()).andExpect(status().isOk()).
//				andExpect(content().string(containsString("[{\"age\":18,\"id\":0,\"name\":\"zhangsan\",\"gender\":\"male\"}]")));
	}
	@Test
	public void contextLoads() {

	}

}
