package com.springunittest.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.springunittest.entites.Vendor;
import com.springunittest.repo.VendorRepository;

public class VendorServiceImplTest {

	@Mock
	private VendorRepository vendorRepository;
	
	private VendorService vendorService;
	
	private AutoCloseable autoCloseable;
	
	private Vendor vendor;
	
	@BeforeEach
	public void setUp() {
		autoCloseable = MockitoAnnotations.openMocks(this);
		vendorService = new VendorServiceImpl(vendorRepository);	// use @InjectMocks
		vendor = new Vendor(15, "XYZ", "Dallas", "xyz@example.com");
	}
	
	@Test
	@DisplayName("Test : Creating Vendor Record")
	public void testCreateVendor() {
		// Setting behavior for Dao method call
		when(vendorRepository.save(any())).thenReturn(vendor);
		assertThat(vendorService.createVendor(vendor)).isEqualTo("SUCCESS");
	}
	
	
	@Test
	@DisplayName("Test Getting Vedor Details By ID")
	public void testGetVendorById() {
		// Setting behavior for Dao method call
		when(vendorRepository.findById(15)).thenReturn(Optional.ofNullable(vendor));
		
		assertThat(vendorService.getVendor(15).getVendorName()).isEqualTo(vendor.getVendorName());
		assertThat(vendorService.getVendor(15).getVendorLoc()).isEqualTo(vendor.getVendorLoc());
		assertThat(vendorService.getVendor(15).getContact()).isEqualTo(vendor.getContact());
	}
	
	@Test
	@DisplayName("Test for getting vendor details Exception")
	public void testGetVendorByIdException() {
		when(vendorRepository.findById(123)).thenReturn(Optional.empty());
		RuntimeException exception = assertThrows(RuntimeException.class, ()-> vendorService.getVendor(123));
		assertEquals("Requested Vendor Doesn't Exist.", exception.getMessage());
	}
	
	
	@Test
	@DisplayName("Test for get vendor details by name")
	public void testGetVendorInfoByVendorName() {
		when(vendorRepository.findByVendorName("XYZ")).thenReturn(new ArrayList<Vendor>(Collections.singleton(vendor)));
		assertThat(vendorService.getVendorInfoByVendorName("XYZ").get(0).getVendorId()).isEqualTo(vendor.getVendorId());		
	}
	
	
	@Test
	@DisplayName("Test for getting all vendors")
	public void testGetAllVendors() {
		when(vendorRepository.findAll()).thenReturn(new ArrayList<Vendor>(Collections.singleton(vendor)));
		assertThat(vendorService.getAllVendors().get(0).getVendorName()).isEqualTo(vendor.getVendorName());
		assertThat(vendorService.getAllVendors().size()).isEqualTo(1);
	}
	
	
	@Test
	@DisplayName("test for deleting vendor")
	public void testDeleteVendor() {
		mock(VendorRepository.class, Mockito.CALLS_REAL_METHODS);
		doNothing().when(vendorRepository).deleteById(any());
		assertThat(vendorService.deleteVendor(15)).isEqualTo("Delete Success");
	}
	
	@AfterEach
	public void tearDown() throws Exception {
		autoCloseable.close();
	}
}
