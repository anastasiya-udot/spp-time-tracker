package com.bsuir.tracker.entity;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Pavel on 26.04.2017.
 */
public class EmployeeEntityTest {
    @Test(expected = IllegalArgumentException.class)
    public void setIdemployee() throws Exception {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setIdemployee(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setAvatarIdimage() throws Exception {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setAvatarIdimage(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setRoleIdrole() throws Exception {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setRoleIdrole(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setCompanyIdcompany() throws Exception {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setCompanyIdcompany(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setWorkdayIdworkdayType() throws Exception {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setWorkdayIdworkdayType(-1);
    }

}