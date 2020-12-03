/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungnd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import trungnd.BLO.HotelBLO;
import trungnd.BLO.RoomBLO;

/**
 *
 * @author HOME
 */
public class SearchController extends HttpServlet {
    private static final String SUCCESS = "index.jsp";
    private static final String ERROR = "error.jsp";
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String checkOutDate = request.getParameter("txtCheckOutDate");
            String checkInDate = request.getParameter("txtCheckInDate");
            String roomType = request.getParameter("cbxTypeRoom");
            String area = request.getParameter("cbxArea");
            String hotelName = request.getParameter("cbxHotelName");
            
            String search = request.getParameter("txtSearch");
            search = hotelName;
            if ("null".equals(search)) {
                search = "";
            }
            if (search == null || search.isEmpty()) {
                search = "";
            }
            System.out.println(search + " " + hotelName + " " + area + " " + roomType);
            
            HotelBLO blo = new HotelBLO();
            RoomBLO roomBlo = new RoomBLO();
            List result = null;           
            if ("".equals(search)) {
                result = blo.searchByLikeInput(search);
            } else {
                result = blo.searchByLikeInputMore(checkOutDate, checkInDate, roomType, area, hotelName);
            }         
            List resultHotel = blo.getListHotel();
            List resultRoom = roomBlo.getListRoom();
            System.out.println(resultHotel + " " + resultRoom);
            request.setAttribute("INFO", result);
            request.setAttribute("LIST_HOTEL", resultHotel);
            request.setAttribute("LIST_ROOM", resultRoom);
            
            url = SUCCESS;
        } catch (Exception e) {
            log("Error At SearchController: " + e.getMessage());
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
