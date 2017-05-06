package com.bsuir.tracker.Service;

import com.bsuir.tracker.DAO.ImageDAO;
import com.bsuir.tracker.entity.ImageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Created by Pavel on 15.04.2017.
 */

@Service
@Transactional
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageDAO imageDAO;

    @Transactional
    public void addImage(ImageEntity image){
        imageDAO.addImage(image);
    }

    public ImageEntity getImage(int idImage){
        return imageDAO.getImage(idImage);
    }

    @Transactional
    public List<ImageEntity> getAllImages() {
        return imageDAO.getAllImages();
    }

    public ImageEntity updateImage(ImageEntity image){
        return imageDAO.updateImage(image);
    }

    @Transactional
    public void deleteImage(int idImage){
        imageDAO.deleteImage(idImage);
    }

    public void setImageDAO(ImageDAO imageDAO){
        this.imageDAO = imageDAO;
    }
}