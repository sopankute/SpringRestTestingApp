package com.springunittest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springunittest.entites.Vendor;
import com.springunittest.exceptions.VendorNotFoundException;
import com.springunittest.repo.VendorRepository;

@Service
public class VendorServiceImpl implements VendorService {

	@Autowired
	private VendorRepository vendorRepo;
	
	public VendorServiceImpl(VendorRepository vendorRepo) {
		this.vendorRepo = vendorRepo;
	}
	
	@Override
	public String createVendor(Vendor vendor) {
		Vendor savedVendor = vendorRepo.save(vendor);
		System.out.println("Current Vendor :: "+savedVendor);
		return "SUCCESS";
	}

	@Override
	public Vendor getVendor(Integer vendorId) {
		Optional<Vendor> vendor = vendorRepo.findById(vendorId);
		if(vendor.isEmpty()) {
			throw new RuntimeException("Requested Vendor Doesn't Exist.");
		}
		return vendor.get();	
	}

	@Override
	public List<Vendor> getVendorInfoByVendorName(String vendorName) {
		List<Vendor> vendors = vendorRepo.findByVendorName(vendorName);
		return vendors;
	}

	@Override
	public List<Vendor> getAllVendors() {
		List<Vendor> vendors = vendorRepo.findAll();
		return vendors;
	}

	@Override
	public String deleteVendor(Integer vendorId) {
		vendorRepo.deleteById(vendorId);
		return "Delete Success";
	}

}
