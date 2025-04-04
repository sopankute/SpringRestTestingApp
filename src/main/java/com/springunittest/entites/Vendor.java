package com.springunittest.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ASHOKIT_VENDORS")
public class Vendor {
	
	@Id
	@Column(name = "VENDORS_ID")
	private Integer vendorId;
	
	@Column(name = "VENDORS_NAME")
	private String vendorName;
	
	@Column(name = "VENDORS_LOC")
	private String vendorLoc;
	
	@Column(name = "VENDORS_CONTACT")
	private String contact;
}
