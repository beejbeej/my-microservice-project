package com.bijanghanei.firstjobapp.company.repository;

import com.bijanghanei.firstjobapp.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
}
