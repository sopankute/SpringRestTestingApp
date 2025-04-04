package com.springunittest.repo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.springunittest.entites.Vendor;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VendorRepositoryTest {

	@Autowired
	private VendorRepository vendorRepository;
	
	private Vendor vendor;
	
	@BeforeEach
	public void setUP() {
		vendor = new Vendor(15, "XYZ", "Dallas", "xyz@example.com");
		Vendor savedDetails = vendorRepository.save(vendor);
		System.out.println("Vendor Details :: "+savedDetails);
	}
	
	// Positive Test
	@Test
	@DisplayName("Test : for get Vendor by Name - Found")
	public void testFindByVendorName_Found() {
		List<Vendor> vendorList = vendorRepository.findByVendorName("XYZ");
		assertNotNull(vendorList.get(0));
		assertThat(vendorList.get(0).getVendorId()).isEqualTo(vendor.getVendorId());
		assertThat(vendorList.get(0).getVendorName()).isEqualTo(vendor.getVendorName());
		assertThat(vendorList.get(0).getVendorLoc()).isEqualTo(vendor.getVendorLoc());
		assertThat(vendorList.get(0).getContact()).isEqualTo(vendor.getContact());
	}
	
	
	// Negative Test
	@Test
	@DisplayName("Test : for get Vendor by Name - NotFound")
	public void testFindByVendorName_NotFound() {
		List<Vendor> vendorList = vendorRepository.findByVendorName("XYZas");
		assertThat(vendorList.isEmpty()).isTrue();
	}
	
	@AfterEach
	public void tearDown() {
		vendor=null;
		vendorRepository.deleteAll();
	}
}
