package com.bsuir.tracker.Service;

import com.bsuir.tracker.entity.CompanyEntity;
import com.bsuir.tracker.model.CompanyNameIdModel;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by Pavel on 25.04.2017.
 */
public interface CompanyService {
    public void addCompany(CompanyEntity company);                                                      //C

    public CompanyEntity getCompany(int idCompany);                                                     //R

    public List<CompanyEntity> getAllCompanies();

    public List<CompanyNameIdModel> getAllCompaniesNameId();

    public CompanyEntity updateCompany(CompanyEntity company);                                                  //U

    public void deleteCompany(int idCompany);                                                   //D
}