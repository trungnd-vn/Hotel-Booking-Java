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
import trungnd.BLO.AccountBLO;
import trungnd.entity.Account;
import trungnd.object.ErrorObj;
import trungnd.object.encryptPassword;

/**
 *
 * @author HOME
 */
public class LoginController extends HttpServlet {
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "SearchController";
    private static final String INVALID = "login.jsp";
    
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
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            boolean valid = true;
            ErrorObj errorObj = new ErrorObj();

            if (username.length() == 0) {
                errorObj.setIdError("*Email can't be blank!");
                valid = false;
            } else {
                if (!username.matches("[a-zA-Z0-9]{3,50}@[a-zA-Z0-9]{3,50}[.][a-zA-Z0-9]{2,7}([.][a-zA-Z]{1,2})?")) {
                    errorObj.setIdError("*Error type of email!");
                    valid = false;
                }
            }
            if (password.length() == 0) {
                errorObj.setPasswordError("*Password can't be blank!");
                valid = false;
            }

            if (valid) {
                AccountBLO blo = new AccountBLO();
                encryptPassword encrypt = new encryptPassword();
                String encrypted = encrypt.encryptPass(password);
//                System.out.println(encrypted);
                boolean result = blo.checkLogin(username, encrypted);
                if (!result) {
                    request.setAttribute("ERROR", "Invalid Email Or Password!");
//                    System.out.println("hello null");
                    url = INVALID;
                } else {
                    HttpSession session = request.getSession();
                    Account acc = new Account();
                    acc = blo.loginPage(username);
                    url = SUCCESS;
                    session.setAttribute("USER", acc);
                    session.setAttribute("EMAILID", acc.getUserID());
                    session.setAttribute("NAME", acc.getName());
                    session.setAttribute("ROLE", acc.getRole());
                    session.setAttribute("ACCOUNT", acc);
                    request.setAttribute("cbxHotelName", "");
                }
            } else {
                url = INVALID;
                request.setAttribute("INVALID", errorObj);
            }
        } catch (Exception e) {
            log("ERROR at LoginController: " + e.getMessage());
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
