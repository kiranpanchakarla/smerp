package com.smerp.repository.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smerp.model.admin.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Integer> {

	Vendor findById(int id);

	@Query("SELECT c FROM Vendor c WHERE isActive=:isActive order by updatedAt desc")
	List<Vendor> findByIsActive(@Param("isActive") Boolean isActive);

	@Query("SELECT name FROM Vendor WHERE isActive=true")
	List<String> findAllVendorNames();

	@Query("SELECT v FROM Vendor v WHERE LOWER(v.name) = LOWER(:name)")
	Vendor findByName(String name);
}
