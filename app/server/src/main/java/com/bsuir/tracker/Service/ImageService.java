package com.bsuir.tracker.Service;

import com.bsuir.tracker.entity.ImageEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Pavel on 15.04.2017.
 */

public interface ImageService {

    public void addImage(ImageEntity image);

    public ImageEntity getImage(int idImage);

    public List<ImageEntity> getAllImages();

    public ImageEntity updateImage(ImageEntity image);

    public void deleteImage(int idImage);

}
