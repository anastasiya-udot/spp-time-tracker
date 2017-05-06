package com.bsuir.tracker.entity;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Pavel on 03.05.2017.
 */
public class RequestEntityTest {
    @Test(expected = IllegalArgumentException.class)
    public void setIdrequest() throws Exception {
        RequestEntity requestEntity = new RequestEntity();
        requestEntity.setIdrequest(-5);
    }

}