/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungnd.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
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
public class AddToDatabaseController extends HttpServlet {
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
            String txtCode = request.getParameter("txtCode");
            int total = Integer.parseInt(request.getParameter("txtTotal"));
            String txtCheckIn = request.getParameter("txtCheckIn");
            String txtCheckOut = request.getParameter("txtCheckOut");
            String txtCheckCode = request.getParameter("txtCheckCode");
            int status = 1;
            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
            String date = localDateTime.format(dateTimeFormatter);
            HttpSession session = request.getSession();
            Account acc = (Account) session.getAttribute("USER");
            Booking cart = (Booking) session.getAttribute("CART");
            BookingBLO bookingBLO = new BookingBLO();
            int bookingID = bookingBLO.getBookingID();
            if (txtCode.equals(txtCheckCode)) {
                int bookingDetailID, quantity, totalDetail, roomID;
                if (bookingBLO.addToDB(bookingID, total, date, txtCheckIn, txtCheckOut, new Account(acc.getUserID()), new Status(status))) {
                    for (int i = 0; i < cart.getBookingDetailList().size(); i++) {
                        roomID = cart.getBookingDetailList().get(i).getRoomID().getRoomID();
                        quantity = cart.getBookingDetailList().get(i).getQuantity();
                        totalDetail = (int) cart.getBookingDetailList().get(i).getTotal();
                        bookingDetailID = bookingBLO.getBookingDetailID();
                        bookingBLO.addDetailToDB(bookingDetailID, quantity, totalDetail, new Booking(bookingID), new Room(roomID));
                    }
                    url = SUCCESS;
                    System.err.println("Add Complete!");
                } else {
                    System.err.println("Add Uncomplete!");
                }
            }
              
        } catch (Exception e) {
            log("ERROR at AddToDatabaseController: " + e.getMessage());
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
