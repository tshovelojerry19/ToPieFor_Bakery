
package com.topiefor.processservice;

import com.topiefor.controller.ProcessRequest;
import com.topiefor.dao.UnitDao;
import com.topiefor.models.Unit;
import com.topiefor.service.UnitService;
import com.topiefor.service.impl.UnitServiceImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Train
 */
public class processTheUnitRequest implements ProcessRequest {

    private String page;
    private UnitService unitService;

    public processTheUnitRequest(UnitDao unitDao) {
        unitService = new UnitServiceImpl(unitDao);

    }

    @Override
    public void processTheRequest(HttpServletRequest request, HttpServletResponse response) {

        //Category category = null;
        List<Unit> allUnits = null;
        String unitAction = request.getParameter("action");
        String unitType;
        String unitStatus;
       
        int unitID = 0;

        if (unitAction != null) {
            switch (unitAction.trim()) {
                case "add":
                    unitType = request.getParameter("UnitType");
                    if (unitType != null && !unitType.isEmpty()) {
                        unitType = unitType.trim();
                        if (unitService.addUnit((new Unit(0, unitType)))) {
                            request.setAttribute("allUnits", new ArrayList<>(unitService.getAllUnits()));
                            request.setAttribute("message", "Unit added!");
                        } else {
                            request.setAttribute("message", "Unit not Added");
                        }

                        page = "Admin/UnitPage.jsp";
                    }

                    break;

                //---------------------------------------------------------------------------------------------------------
                case "edit":

                    unitType = request.getParameter("unitType");
                 unitStatus = request.getParameter("status");
                    if (unitType != null) {
                        try {
                            unitID = Integer.parseInt(request.getParameter("unitID"));
                        } catch (NumberFormatException ex) {
                            System.out.println("error" + ex.getMessage());
                        }
                        if (!unitType.isEmpty() && unitID > 0) {
                            if (unitService.editUnit(new Unit(unitID, unitType))) {
                              request.setAttribute("allUnits", new ArrayList<>(unitService.getAllUnits()));
                                request.setAttribute("message", "unit Updated!");
                            } else {
                                request.setAttribute("message", "unti not Updated!");
                            }
                        }

                    }
                    page = "Admin/UnitPage.jsp";
                    break;
                //---------------------------------------------------------------------------------------------------------
                case "get":
                    allUnits = unitService.getAllUnits();
                    if (allUnits != null && !allUnits.isEmpty()) {

                        request.setAttribute("allUnits", allUnits);
                    } else {
                        request.setAttribute("response", "no data");
                    }
                    page = "Admin/UnitPage.jsp";
                    break;

                //------------------------------------------------------------------------------------

                default:
                    request.setAttribute("response", "something went wrong");
                    break;
            }

        }
    }

    private boolean checkFlag(String categoryStatus, boolean flagStatus, HttpServletRequest request) {
        switch (categoryStatus) {
           case "Activate":
                flagStatus = true;
                break;
            case "Deactivate":
                flagStatus = false;
                break;
            default:
                request.setAttribute("error", "error");
                break;
        }
        return flagStatus;
    }

    @Override
    public void processTheResponse(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
        try {
            requestDispatcher.forward(request, response);

        } catch (ServletException | IOException ex) {
            System.err.println("Error Dispatching View: " + ex.getMessage());
        }
    }

}
