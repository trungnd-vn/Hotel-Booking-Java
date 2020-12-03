/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungnd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import trungnd.BLO.DiscountBLO;
import trungnd.entity.Account;
import trungnd.object.MailObj;

/**
 *
 * @author HOME
 */
public class ConfirmController extends HttpServlet {

    private static final String INVALID = "detailCart.jsp";
    private static final String SUCCESS = "verify.jsp";

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
        String url = INVALID;
        try {
            String checkin = request.getParameter("txtCheckIn");
            String checkout = request.getParameter("txtCheckOut");
            HttpSession session = request.getSession();
            request.setAttribute("txtCheckIn", checkin);
            request.setAttribute("txtCheckOut", checkout);
            Account acc = (Account) session.getAttribute("USER");
            if (Date.valueOf(checkin).after(Date.valueOf(checkout))) {
                request.setAttribute("DATE", "Check out date must after check in date");
                url = INVALID;
            } else {
                String discountId = request.getParameter("txtDiscount");
                float sum = Float.parseFloat(request.getParameter("txtSum"));
                DiscountBLO dBLO = new DiscountBLO();
                int percent = dBLO.getDiscountPercent(discountId);
                System.out.println();
                int total = (int) ((int) sum * (1 - (float) percent / 100));
                String code = MailObj.sendMail(acc.getUserID());
                request.setAttribute("CODE", code);
                request.setAttribute("TOTAL", total);
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at ConfirmController: " + e.getMessage());
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
