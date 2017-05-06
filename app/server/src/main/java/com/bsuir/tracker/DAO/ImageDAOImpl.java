package com.bsuir.tracker.DAO;

import com.bsuir.tracker.entity.ImageEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Created by Pavel on 15.04.2017.
 */

@Repository
public class ImageDAOImpl implements ImageDAO {
    @Autowired
    public   SessionFactory sessionFactory;

    public void addImage(ImageEntity image){
        if (image == null){
            throw new IllegalArgumentException();
        }
        sessionFactory.getCurrentSession().saveOrUpdate(image);
    }

    public ImageEntity getImage(int idImage){
        if (idImage < 1){
            throw new IllegalArgumentException();
        }
        ImageEntity result;
        try {
            result = (ImageEntity) sessionFactory.getCurrentSession().get(ImageEntity.class, idImage);
        }
        catch (Exception e){
            System.out.println(e);
            throw new IllegalArgumentException();
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public List<ImageEntity> getAllImages() {
        List<ImageEntity> result = null;
        try {
            result = sessionFactory.getCurrentSession().createQuery("from ImageEntity").list();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return result;
    }

    public ImageEntity updateImage(ImageEntity image){
        if (image == null){
            throw new IllegalArgumentException();
        }
        sessionFactory.getCurrentSession().update(image);
        return image;
    }

    public void deleteImage(int idImage){
        if (idImage < 0){
            throw new IllegalArgumentException();
        }
        ImageEntity image = (ImageEntity) sessionFactory.getCurrentSession().load(ImageEntity.class, idImage);
        if (null != image){
            this.sessionFactory.getCurrentSession().delete(image);
        }
    }
}
