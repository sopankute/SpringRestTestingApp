package com.springunittest.services;

import java.util.List;

import com.springunittest.entites.Vendor;

public interface VendorService {

	String createVendor(Vendor vendor);
	
	Vendor getVendor(Integer vendorId);
	
	List<Vendor> getVendorInfoByVendorName(String vendorName);
	
	List<Vendor> getAllVendors();
	
	String deleteVendor(Integer vendorId);
}
