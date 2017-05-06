package com.bsuir.tracker.entity;

import com.bsuir.tracker.entity.ImageEntity;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Pavel on 26.04.2017.
 */
public class ImageEntityTest {
    @Test(expected = IllegalArgumentException.class)
    public void setIdimage() throws Exception {
        ImageEntity image = new ImageEntity();
        image.setIdimage(-1);
    }
}