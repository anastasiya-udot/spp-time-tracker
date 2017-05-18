package com.bsuir.tracker.Service;

import com.bsuir.tracker.DAO.CompanyDAO;
import com.bsuir.tracker.entity.CompanyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Created by Pavel on 25.04.2017.
 */

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyDAO companyDAO;

    @Transactional
    public void addCompany(CompanyEntity company) {
        companyDAO.addCompany(company);
    }

    public CompanyEntity getCompany(int idCompany) {
        return companyDAO.getCompany(idCompany);
    }

    @Transactional
    public List<CompanyEntity> getAllCompanies() {
        return companyDAO.getAllCompanies();
    }

    public List<Object> getAllCompaniesNameId(){
        return  companyDAO.getAllCompaniesNamesId();
    }

    public CompanyEntity updateCompany(CompanyEntity company) {
        return companyDAO.updateCompany(company);
    }

    @Transactional
    public void deleteCompany(int idCompany) {
        companyDAO.deleteCompany(idCompany);
    }

    public void setCompanyDAO(CompanyDAO companyDAO){
        this.companyDAO = companyDAO;
    }
}
