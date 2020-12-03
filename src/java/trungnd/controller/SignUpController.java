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
import trungnd.BLO.AccountBLO;
import trungnd.entity.Status;
import trungnd.object.ErrorObj;
import trungnd.object.encryptPassword;

/**
 *
 * @author HOME
 */
public class SignUpController extends HttpServlet {
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "login.jsp";
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
            String userID = request.getParameter("txtUserID");
            String name = request.getParameter("txtName");
            String password = request.getParameter("txtPassword");
            String confirm = request.getParameter("txtConfirm");
            String phone = request.getParameter("txtPhone");
            String address = request.getParameter("txtAddress");
            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("MM-dd-yyyy");
            String date = myDateObj.format(myFormatObj);
            String role = "user";
            int status = 1;
            boolean valid = true;
            ErrorObj error = new ErrorObj();
            AccountBLO blo = new AccountBLO();
            if (userID.length() == 0) {
                valid = false;
                error.setEmailErr("Email Can't Be Blank!!");
            } else if (!userID.matches("[a-zA-Z0-9]{3,50}@[a-zA-Z0-9]{3,50}[.][a-zA-Z0-9]{2,7}([.][a-zA-Z]{1,2})?")) {
                valid = false;
                error.setEmailErr("Wrong email format!! ex:abc@gmail.com(.vn)");
            }
            if (phone.length() == 0) {
                valid = false;
                error.setPhoneError("Phone can't be Blank!");
            } else if (!phone.matches("[0-9]{10}")) {
                valid = false;
                error.setPhoneError("Wrong phone type: ex: 0123456789");
            }
            if (name.length() == 0) {
                valid = false;
                error.setNameErr("Name can't Be Blank!");
            }
            if (password.length() == 0) {
                valid = false;
                error.setPassErr("Password can't Be Blank!");
            }
            if (!confirm.equals(password)) {
                valid = false;
                error.setConfirmErr("Must Match Password!!");
            } else if (confirm.length() == 0) {
                valid = false;
                error.setConfirmErr("Password must be confirmed!");
            }
            if (valid) {
                encryptPassword encrypt = new encryptPassword();
                String passEncrypted = encrypt.encryptPass(password);
                boolean result = blo.insertAccount(userID, passEncrypted, name, phone, address, date, role, new Status(status));
                if (result) {
                    url = SUCCESS;
                } else {
                    request.setAttribute("DUPLICATE", "Email is existed!");
                    url = INVALID;
                }
            } else {
                url = INVALID;
                request.setAttribute("INVALID", error);
            }
        } catch (Exception e) {
            log("Error At SignUpController: " + e.getMessage());
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
