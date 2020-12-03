/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungnd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import trungnd.BLO.RoomBLO;
import trungnd.entity.Account;
import trungnd.entity.Booking;
import trungnd.entity.BookingDetail;
import trungnd.entity.Room;

/**
 *
 * @author HOME
 */
public class AddToCartController extends HttpServlet {
private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "RoomDetailController";
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
            String idHotel = request.getParameter("txtHotelID");
            int id = Integer.parseInt(request.getParameter("txtId"));
            request.setAttribute("txtHotelID", idHotel);
            HttpSession session = request.getSession();
            Booking cart = (Booking) session.getAttribute("CART");
            if(cart == null){
                cart = new Booking();
            }
            RoomBLO blo = new RoomBLO();
            Room room = blo.getRoomFromDB(id);
            
            Account acc = (Account) session.getAttribute("USER");
            cart.setUserID(acc);
            
            boolean isFound = false;
            for(int i = 0; i < cart.getBookingDetailList().size(); i++){
                if(cart.getBookingDetailList().get(i).getRoomID().getRoomID() == id){
                    cart.getBookingDetailList().get(i).setQuantity(cart.getBookingDetailList().get(i).getQuantity() + 1);
                    isFound = true;
                }
            }
            System.out.println(isFound + "idhfsadvhkjavdhuiahv");
            if(!isFound) {
                BookingDetail bt = new BookingDetail(1, 1, (int)room.getPrice(), room);
                cart.getBookingDetailList().add(bt);
            }
            int total = 0;
            for(int i = 0; i < cart.getBookingDetailList().size();i++){
                total += (cart.getBookingDetailList().get(i).getQuantity() * cart.getBookingDetailList().get(i).getTotal());
            }
            url = SUCCESS;
            cart.setTotal(total);
            session.setAttribute("CART", cart);
            
        } catch (Exception e) {
            log("Error at AddToCartController: " + e.getMessage());
            e.printStackTrace();
        } finally{
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
