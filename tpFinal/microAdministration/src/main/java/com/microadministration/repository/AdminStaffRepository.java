package com.microadministration.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microadministration.dto.AdminStaffDTO;
import com.microadministration.model.AdminStaff;

@Repository ("adminStaffRepository")
public interface AdminStaffRepository extends JpaRepository<AdminStaff, Long> {

    @Query("SELECT a FROM AdminStaff a WHERE a.rol = :rol")
    Collection<AdminStaff> findByRol(String rol);
}

