package com.bsuir.tracker.entity;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Pavel on 26.04.2017.
 */
public class ProjectEntityTest {
    @Test(expected = IllegalArgumentException.class)
    public void setIdproject() throws Exception {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setIdproject(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setCompanyIdcompany() throws Exception {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setCompanyIdcompany(-1);
    }

}