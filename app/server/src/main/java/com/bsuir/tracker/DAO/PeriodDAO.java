package com.bsuir.tracker.DAO;

import com.bsuir.tracker.entity.PeriodEntity;

import java.util.List;

/**
 * Created by Pavel on 26.04.2017.
 */
public interface PeriodDAO {

    public void addPeriod(PeriodEntity period);

    public PeriodEntity getPeriod(int idPeriod);

    public List<PeriodEntity> getAllPeriods();

    public PeriodEntity updatePeriod(PeriodEntity role);

    public void deletePeriod(int idPeriod);

}