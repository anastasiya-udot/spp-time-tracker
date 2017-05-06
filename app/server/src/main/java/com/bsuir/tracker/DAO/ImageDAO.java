package com.bsuir.tracker.DAO;

import com.bsuir.tracker.entity.ImageEntity;
import java.util.List;

/**
 * Created by Pavel on 15.04.2017.
 */

public interface ImageDAO {

    public void addImage(ImageEntity image);

    public ImageEntity getImage(int idImage);

    public List<ImageEntity> getAllImages();

    public ImageEntity updateImage(ImageEntity image);

    public void deleteImage(int idImage);

}
