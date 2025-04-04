package com.springunittest.repo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springunittest.entites.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Serializable>{

	public List<Vendor> findByVendorName(String vendorName);
	
}
