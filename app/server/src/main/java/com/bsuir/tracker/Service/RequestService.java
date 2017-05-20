package com.bsuir.tracker.Service;

import com.bsuir.tracker.entity.RequestEntity;

import java.util.List;

/**
 * Created by Pavel on 26.04.2017.
 */
public interface RequestService {

    public void addRequest(RequestEntity request);

    public RequestEntity getRequest(int idRequest);

    public List<RequestEntity> getAllRequests();

    public List<RequestEntity> getAllRequestsByDestEmployeeId(int id);

    public RequestEntity updateRequest(RequestEntity request);

    public void deleteRequest(int idRequest);

}