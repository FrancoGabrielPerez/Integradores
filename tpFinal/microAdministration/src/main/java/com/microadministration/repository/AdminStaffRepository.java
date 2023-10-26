package com.microadministration.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microadministration.dto.AdminStaffDTO;
import com.microadministration.model.AdminStaff;

@Repository ("adminStaffRepository")
public interface AdminStaffRepository extends JpaRepository<AdminStaff, Long> {

    Collection<AdminStaffDTO> findByRol(String rol);
}

