package com.bsuir.tracker.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.bsuir.tracker.entity.ImageEntity;
import com.bsuir.tracker.Service.ImageService;

/**
 * Created by Pavel on 15.04.2017.
 */

@Controller
public class ImageController {
    private static  final Logger logger = Logger.getLogger(ImageController.class);

    public ImageController(){
        System.out.println("ImageController Initializer");
    }

    @Autowired
    private  ImageService imageService;

    @RequestMapping(value = "/Images")
    public ModelAndView listImage(ModelAndView model) throws IOException {
        List<ImageEntity> listImage = imageService.getAllImages();
        model.addObject("listImage", listImage);
        model.setViewName("Images");
        return model;
    }

    @RequestMapping(value = "/newImage", method = RequestMethod.GET)
    public ModelAndView newImage(ModelAndView model) {
        ImageEntity image = new ImageEntity();
        model.addObject("image", image);
        model.setViewName("ImageForm");
        return model;
    }

    @RequestMapping(value = "/saveImage", method = RequestMethod.POST)
    public ModelAndView saveImage(@ModelAttribute ImageEntity image) {
        if (image.getIdimage() == 0) {
            imageService.addImage(image);
        } else {
            imageService.updateImage(image);
        }
        return new ModelAndView("redirect:/Images");
    }

    @RequestMapping(value = "/deleteImage", method = RequestMethod.GET)
    public ModelAndView deleteImage(HttpServletRequest request) {
        int idImage = Integer.parseInt(request.getParameter("id"));
        imageService.deleteImage(idImage);
        return new ModelAndView("redirect:/Images");
    }

    @RequestMapping(value = "/editImage", method = RequestMethod.GET)
    public ModelAndView editImage(HttpServletRequest request) {
        int idImage = Integer.parseInt(request.getParameter("id"));
        ImageEntity image = imageService.getImage(idImage);
        ModelAndView model = new ModelAndView("ImageForm");
        model.addObject("image", image);

        return model;
    }
}


