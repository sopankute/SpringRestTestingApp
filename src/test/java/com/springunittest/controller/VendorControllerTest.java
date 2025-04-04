package com.springunittest.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.springunittest.entites.Vendor;
import com.springunittest.services.VendorService;

@WebMvcTest(VendorController.class)
public class VendorControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockitoBean
	private VendorService vendorService;
	
	private Vendor vendor1;
	private Vendor vendor2;
	
	private List<Vendor> list = new ArrayList<>();
	
	@BeforeEach
	public void setUp() {
		vendor1 = new Vendor(15, "XYZ", "Dallas", "xyz@example.com");
		vendor2 = new Vendor(20, "ABCD", "Angeles", "abcd@example.com");
		list.add(vendor1);
		list.add(vendor2);
	}

	@Test
	@DisplayName("Test for GetVendorDetails")
	public void testGetVendorDetails() throws Exception {
		when(vendorService.getVendor(15)).thenReturn(vendor1);
		
		this.mvc.perform(get("/vendors/15"))
			.andDo(print())
			.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Test for GetAllVendorDetails")
	public void testGetAllVendorDetails() throws Exception {
		when(vendorService.getAllVendors()).thenReturn(list);
		
		mvc.perform(get("/vendors/"))
		.andDo(print())
		.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Test for CreateCloudVendorDetails")
	public void testCreateCloudVendorDetails() throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		
		ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
		
		String requestJSON = writer.writeValueAsString(vendor1);
		
		System.out.println("Request JSON :: "+requestJSON);
		
		when(vendorService.createVendor(vendor1)).thenReturn("Created Successfully");
		
		mvc.perform(post("/vendors/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestJSON))
				.andDo(print())
				.andExpect(status().isCreated());
				
	}
	
	@Test
	@DisplayName("Test for DeleteVendorDetails")
	public void testDeleteVendorDetails() throws Exception {
		when(vendorService.deleteVendor(15)).thenReturn("Deleted Successfully");
		
		mvc.perform(delete("/vendors/15"))
		.andDo(print())
		.andExpect(status().isOk());
	}
	
	@AfterEach
	public void tearDown() {
		list.clear();
		vendor1=null;
		vendor2=null;
	}
	
}
