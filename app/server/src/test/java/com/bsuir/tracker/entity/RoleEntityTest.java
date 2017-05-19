package com.bsuir.tracker.entity;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Pavel on 26.04.2017.
 */
public class RoleEntityTest {
    @Test(expected = IllegalArgumentException.class)
    public void setIdrole() throws Exception {
        RoleEntity role = new RoleEntity();
        role.setIdrole(-1);
    }

}