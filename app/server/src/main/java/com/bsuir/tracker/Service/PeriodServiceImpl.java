package com.bsuir.tracker.Service;

import com.bsuir.tracker.DAO.PeriodDAO;
import com.bsuir.tracker.entity.PeriodEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Pavel on 26.04.2017.
 */
@Service
@Transactional
public class PeriodServiceImpl implements PeriodService {
    @Autowired
    private PeriodDAO periodDAO;

    @Transactional
    public void addPeriod(PeriodEntity period) {
        periodDAO.addPeriod(period);
    }

    @Override
    public PeriodEntity getPeriod(int idPeriod) {
        return periodDAO.getPeriod(idPeriod);
    }

    @Transactional
    public List<PeriodEntity> getAllPeriods() {
        return periodDAO.getAllPeriods();
    }

    @Transactional
    public List<PeriodEntity> getAllPeriodsByEmployeeId(int id){
        return periodDAO.getAllPeriodsByEmployeeId(id);
    }

    @Override
    public PeriodEntity updatePeriod(PeriodEntity period) {
        return periodDAO.updatePeriod(period);
    }

    @Transactional
    public void deletePeriod(int idPeriod) {
        periodDAO.deletePeriod(idPeriod);
    }

    public void setPeriodDAO(PeriodDAO periodDAO){
        this.periodDAO = periodDAO;
    }
}