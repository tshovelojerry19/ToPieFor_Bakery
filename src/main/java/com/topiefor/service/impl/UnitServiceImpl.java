package com.topiefor.service.impl;

import com.topiefor.dao.UnitDao;
import com.topiefor.dao.impl.UnitDaoImpl;
import com.topiefor.models.Unit;
import com.topiefor.service.UnitService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UnitServiceImpl implements UnitService {

    private UnitDao unitDao;

    public UnitServiceImpl(UnitDao unitDao) {
        setUnitDao(unitDao);
    }

    @Override
    public boolean addUnit(Unit unit) {
        return unit == null ? false : unitDao.addUnit(unit);
    }

    @Override
    public boolean editUnit(Unit unit) {
        return unit == null ? false : unitDao.editUnit(unit);

    }

    @Override
    public List<Unit> getAllUnits() {
        if (unitDao.getAllUnits() != null || !(unitDao.getAllUnits().isEmpty())) {
            return unitDao.getAllUnits();
        }
        return null;
    }

    private void setUnitDao(UnitDao unitDao) {
        this.unitDao = unitDao;
    }

}
