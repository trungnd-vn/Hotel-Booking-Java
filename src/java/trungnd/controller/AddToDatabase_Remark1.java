/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungnd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import trungnd.BLO.BookingBLO;
import trungnd.entity.Account;
import trungnd.entity.Booking;
import trungnd.entity.Room;
import trungnd.entity.Status;

/**
 *
 * @author HOME
 */
public class AddToDatabase_Remark1 extends HttpServlet {
    private static final String SUCCESS = "SearchController";
    private static final String FAIL = "verify.jsp";
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
        String url = FAIL;
        try {
            String code = request.getParameter("txtCode");//verify
            String checkCode = request.getParameter("txtCheckCode");//verify
            String checkIn = request.getParameter("txtCheckIn");//verify
            String checkOut = request.getParameter("txtCheckOut");//verify
            HttpSession session = request.getSession();
            Account acc = (Account) session.getAttribute("USER");//session => để new Account get ID
            Booking cart = (Booking) session.getAttribute("CART");//session 
            BookingBLO blo = new BookingBLO();
            int idBooking = blo.getBookingID();
            int total = Integer.parseInt(request.getParameter("txtTotal"));//verify
            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
            String date = localDateTime.format(dateTimeFormatter);
            int status = 1;//status cố định = 1 => để new Status(status)
            if (code.equals(checkCode)) {
                int roomID, bookingDetailID, quantity, totalDetail;
                if (blo.addToDB(idBooking, total, date, checkIn, checkOut, new Account(acc.getUserID()), new Status(status))) {
                    for (int i = 0; i < cart.getBookingDetailList().size(); i++) {
                        roomID = cart.getBookingDetailList().get(i).getRoomID().getRoomID();
                        bookingDetailID = blo.getBookingDetailID();
                        quantity = cart.getBookingDetailList().get(i).getQuantity();
                        totalDetail = (int) cart.getBookingDetailList().get(i).getTotal();
                        blo.addDetailToDB(bookingDetailID, quantity, totalDetail, new Booking(idBooking), new Room(roomID));
                    }
                    url = SUCCESS;
                    session.removeAttribute("CART");
                }
            }
        } catch (Exception e) {
            log("ERROR at AddToDatabase_Remark1: " + e.getMessage());
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
