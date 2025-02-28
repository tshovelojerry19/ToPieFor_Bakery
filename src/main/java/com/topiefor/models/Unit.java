package com.topiefor.models;

import java.util.Objects;

public class Unit {

  private int unitID;
    private String unitType;
  

    public Unit(int unitID, String unitType) {
        this.unitID = unitID;
        this.unitType = unitType;
        
    }

    public Unit() {
    }

    public Unit(String unitType) {
        this.unitType = unitType;
    }

    public Unit(int unitID) {
        this.unitID = unitID;
    }

    public int getUnitID() {
        return unitID;
    }

    public void setUnitID(int unitID) {
        this.unitID = unitID;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Unit other = (Unit) obj;
        if (this.unitID != other.unitID) {
            return false;
        }
        
        if (!Objects.equals(this.unitType, other.unitType)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Unit{" + "unitID=" + unitID + ", unitType=" + unitType + '}';
    }

}