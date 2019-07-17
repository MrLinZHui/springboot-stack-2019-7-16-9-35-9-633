package com.tw.apistackbase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tw.apistackbase.controller.Company;
import com.tw.apistackbase.controller.Employee;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
	public void should_a_Employee_when_get_a_id() throws Exception {
		final MvcResult mvcResult = this.mockMvc.perform(get("/employees/1")).andExpect(status().isOk()).andReturn();
		//JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());
		JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
		assertEquals(20,jsonObject.getInt("age"));
		assertEquals("lisi",jsonObject.getString("name"));
		assertEquals("male",jsonObject.getString("gender"));
	}
	@Test
	public void should_return_JsonArrays_when_get_page_and_pagesize() throws Exception {
		final MvcResult mvcResult = this.mockMvc.perform(get("/employees?page=1&pagesize=5")).andExpect(status().isOk()).andReturn();
		JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
		assertEquals(1,jsonObject.getInt("page"));
		assertEquals(5,jsonObject.getInt("pagesize"));
		assertEquals(1,jsonObject.getJSONArray("employees").getJSONObject(0).getInt("id"));
		assertEquals("lisi",jsonObject.getJSONArray("employees").getJSONObject(0).getString("name"));
		assertEquals(20,jsonObject.getJSONArray("employees").getJSONObject(0).getInt("age"));
	}
	@Test
	public void should_return_JsonArrays_when_get_gender() throws Exception {
		final MvcResult mvcResult = this.mockMvc.perform(get("/employees?gender=male")).andExpect(status().isOk()).andReturn();
		JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());
		assertEquals(2,jsonArray.length());
	}
	@Test
	public void should_get_201_when_post_a_employee() throws Exception {
		Employee employee = new Employee(1,"lingling",18,"women");
		//final MvcResult mvcResult = this.mockMvc.perform(post("/employees",employee)).andReturn();
		this.mockMvc.perform(post("/employees") .content(asJsonString(employee))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}
	@Test
	public void should_return_employee_when_put_a_id_and_employee() throws Exception {
		Employee employee = new Employee(1,"lingling",18,"women");
		final MvcResult mvcResult = this.mockMvc.perform(put("/employees/1") .content(asJsonString(employee))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
		assertEquals(18,jsonObject.getInt("age"));
		assertEquals("lingling",jsonObject.getString("name"));
		assertEquals("women",jsonObject.getString("gender"));
	}
	@Test
	public void should_get_201_when_delete_a_id() throws Exception {
		Employee employee = new Employee(1,"lingling",18,"women");
		//final MvcResult mvcResult = this.mockMvc.perform(post("/employees",employee)).andReturn();
		this.mockMvc.perform(delete("/employees/1")).andExpect(status().isOk());
	}
	@Test
	public void should_get_all_companys_when_get_all() throws Exception {
		final MvcResult mvcResult = this.mockMvc.perform(get("/companies")).andExpect(status().isOk()).andReturn();
		JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());
		assertEquals("alibaba",jsonArray.getJSONObject(0).getString("companyName"));
		assertEquals(2,jsonArray.getJSONObject(0).getInt("employeeNumber"));
	}

	@Test
	public void should_get_one_company_when_give_a_id() throws Exception {
		final MvcResult mvcResult = this.mockMvc.perform(get("/companies/1")).andExpect(status().isOk()).andReturn();
		JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
		assertEquals("alibaba",jsonObject.getString("companyName"));
		assertEquals(1,jsonObject.getInt("companyId"));
	}
	@Test
	public void should_get_one_employess_when_give_a_id() throws Exception {
		final MvcResult mvcResult = this.mockMvc.perform(get("/companies/1/employees")).andExpect(status().isOk()).andReturn();
		JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());
		assertEquals(20,jsonArray.getJSONObject(0).getInt("age"));
		assertEquals("lisi",jsonArray.getJSONObject(0).getString("name"));
		assertEquals("male",jsonArray.getJSONObject(0).getString("gender"));
	}
	@Test
	public void should_get_companys_when_give_page_and_pagesize() throws Exception {
		final MvcResult mvcResult = this.mockMvc.perform(get("/companies?page=1&pagesize=5")).andExpect(status().isOk()).andReturn();
		JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());
		assertEquals("alibaba",jsonArray.getJSONObject(0).getString("companyName"));
		assertEquals(2,jsonArray.getJSONObject(0).getInt("employeeNumber"));
	}
	@Test
	public void should_get_201_when_post_a_company() throws Exception {
		Company company = new Company("huawei",2);
		//final MvcResult mvcResult = this.mockMvc.perform(post("/employees",employee)).andReturn();
		this.mockMvc.perform(post("/companies") .content(asJsonString(company))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}
	@Test
	public void should_return_company_when_put_a_id_and_company() throws Exception {
		Company company = new Company("mayualibaba",1);
		final MvcResult mvcResult = this.mockMvc.perform(put("/companies/1") .content(asJsonString(company))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
		assertEquals("mayualibaba",jsonObject.getString("companyName"));
	}
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	@Test
	public void should_get_201_when_delete_a_id_from_companys() throws Exception {
		//Employee employee = new Employee(1,"lingling",18,"women");
		//final MvcResult mvcResult = this.mockMvc.perform(post("/employees",employee)).andReturn();
		this.mockMvc.perform(delete("/companies/1")).andExpect(status().isOk());
	}
	@Test
	public void contextLoads() {

	}

}
