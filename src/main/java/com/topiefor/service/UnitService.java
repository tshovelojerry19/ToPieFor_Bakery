/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.topiefor.service;

import com.topiefor.models.Unit;
import java.util.List;

/**
 *
 * @author Train
 */
public interface UnitService {
    List<Unit> getAllUnits();
    boolean addUnit(Unit unit);
 //   boolean deleteUnit(Unit unit);
    boolean editUnit(Unit unit);
}
