package com.bsuir.tracker.Service;

import com.bsuir.tracker.DAO.RequestDAO;
import com.bsuir.tracker.entity.RequestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Pavel on 26.04.2017.
 */
@Service
@Transactional
public class RequestServiceImpl implements RequestService {
    @Autowired
    private RequestDAO requestDAO;

    @Transactional
    public void addRequest(RequestEntity request) {
        requestDAO.addRequest(request);
    }

    @Override
    public RequestEntity getRequest(int idRequest) {
        return requestDAO.getRequest(idRequest);
    }

    @Transactional
    public List<RequestEntity> getAllRequests() {
        return requestDAO.getAllRequests();
    }

    public List<RequestEntity> getAllRequestsByEmployeeId(int id){
        return requestDAO.getAllRequestsByEmployeeId(id);
    }

    @Override
    public RequestEntity updateRequest(RequestEntity request) {
        return requestDAO.updateRequest(request);
    }

    @Transactional
    public void deleteRequest(int idRequest) {
        requestDAO.deleteRequest(idRequest);
    }

    public void setRequestDAO(RequestDAO requestDAO){
        this.requestDAO = requestDAO;
    }
}