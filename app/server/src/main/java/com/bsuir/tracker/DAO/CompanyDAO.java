package com.bsuir.tracker.DAO;

import com.bsuir.tracker.entity.CompanyEntity;
import java.util.List;

/**
 * Created by Pavel on 15.04.2017.
 */

public interface CompanyDAO {

    public void addCompany(CompanyEntity company);                                                      //C

    public CompanyEntity getCompany(int idCompany);                                                     //R

    public List<CompanyEntity> getAllCompanies();

    public CompanyEntity updateCompany(CompanyEntity company);                                                  //U

    public void deleteCompany(int idCompany);                                                   //D

}