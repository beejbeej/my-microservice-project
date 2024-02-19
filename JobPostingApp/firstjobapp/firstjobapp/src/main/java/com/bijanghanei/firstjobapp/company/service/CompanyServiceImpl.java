package com.bijanghanei.firstjobapp.company.service;

import com.bijanghanei.firstjobapp.company.entity.Company;
import com.bijanghanei.firstjobapp.company.repository.CompanyRepository;
import com.bijanghanei.firstjobapp.job.entity.Job;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CompanyServiceImpl implements CompanyService{
    CompanyRepository companyRepository;
    public  CompanyServiceImpl(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }
    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(Company company, Long id) {
        Company c = companyRepository.findById(id).orElse(null);

        if (c!=null){
            c.setName(company.getName());
            c.setDescription(company.getDescription());
            c.setJobs(company.getJobs());

            companyRepository.save(c);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        if (companyRepository.existsById(id)){
            companyRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }


}
