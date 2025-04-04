package com.springunittest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springunittest.entites.Vendor;
import com.springunittest.services.VendorService;

@RestController
@RequestMapping("/vendors")
public class VendorController {

	@Autowired
	private VendorService vendorService;
	
	public VendorController(VendorService vendorService) {
		this.vendorService = vendorService;
	}
	
	
	@GetMapping("/{vendorId}")
	public ResponseEntity<Object> getVendorDetails(@PathVariable() Integer vendorId){
		Vendor vendor = vendorService.getVendor(vendorId);
		return new ResponseEntity<Object>(vendor, HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<Object> getAllVendorDetails(){
		List<Vendor> allVendors = vendorService.getAllVendors();
		return new ResponseEntity<Object>(allVendors,  HttpStatus.OK); 
	}
	
	@PostMapping("/")
	public ResponseEntity<String> createCloudVendorDetails(@RequestBody Vendor vendor){
		vendorService.createVendor(vendor);
		return new ResponseEntity<>("Created Successfully", HttpStatus.CREATED); 
	}
	
	@DeleteMapping("/{vendorId}")
	public ResponseEntity<String> deleteVendorDetails(@PathVariable Integer vendorId){
		vendorService.deleteVendor(vendorId);
		return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK); 
	}
	
}
