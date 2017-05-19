package com.bsuir.tracker.Service;

import com.bsuir.tracker.entity.PeriodEntity;

import java.util.List;

/**
 * Created by Pavel on 26.04.2017.
 */

public interface PeriodService {

    public void addPeriod(PeriodEntity period);

    public PeriodEntity getPeriod(int idPeriod);

    public List<PeriodEntity> getAllPeriods();

    public PeriodEntity updatePeriod(PeriodEntity period);

    public void deletePeriod(int idPeriod);

}