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
import javax.servlet.http.HttpSession;
import trungnd.BLO.HotelBLO;
import trungnd.BLO.RoomBLO;
import trungnd.entity.Hotel;

/**
 *
 * @author HOME
 */
public class RoomDetailController extends HttpServlet {

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
        try {
            int id = Integer.parseInt(request.getParameter("txtHotelID"));
            String name = request.getParameter("txtHotelName");
            String idHotel = request.getParameter("txtHotelID");
            String image = request.getParameter("txtHotelImage");
            RoomBLO blo = new RoomBLO();
            HttpSession session = request.getSession();
            session.setAttribute("NAME", name);
            session.setAttribute("IDHOTEL", idHotel);
            session.setAttribute("IMAGE", image);
            HotelBLO hotelBLO = new HotelBLO();
            Hotel hotelID = hotelBLO.getHotelFromDB(id);
            List result = blo.getRoomByHotelID(hotelID);
            session.setAttribute("INFO", result);

        } catch (NumberFormatException e) {
            log("Error At RoomDetailController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("room.jsp").forward(request, response);
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
