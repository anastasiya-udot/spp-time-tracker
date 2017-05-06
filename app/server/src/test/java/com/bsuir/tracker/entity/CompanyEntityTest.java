package com.bsuir.tracker.entity;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Pavel on 26.04.2017.
 */
public class CompanyEntityTest {
    @Test(expected = IllegalArgumentException.class)
    public void setIdcompany() throws Exception {
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setIdcompany(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setLogoIdimage() throws Exception {
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setLogoIdimage(-1);
    }

}