package com.bsuir.tracker.DAO;

import com.bsuir.tracker.entity.RequestEntity;

import java.util.List;

/**
 * Created by Pavel on 26.04.2017.
 */

public interface RequestDAO {

    public void addRequest(RequestEntity request);

    public RequestEntity getRequest(int idRequest);

    public List<RequestEntity> getAllRequests();

    public RequestEntity updateRequest(RequestEntity request);

    public void deleteRequest(int idRequest);

}