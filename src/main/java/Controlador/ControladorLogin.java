/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import DAO.LoginDao;
import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Levanx
 */
public class ControladorLogin extends HttpServlet {

    LoginDao log = new LoginDao();

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

        String name = request.getParameter("userName");
        String mail = request.getParameter("userEmail");
        String pass = request.getParameter("userPassword");

        HttpSession session = request.getSession();

        Usuario us = new Usuario();
        us.setUsuario(name);
        us.setCorreo(mail);
        us.setPass(pass);

        if (log.IngresarLoginUsuario(us).equals("clientes")) {
            session.setAttribute("usuario", us);
            request.getSession().setAttribute("mensaje", "Bienvenido");
            request.getSession().setAttribute("result", "ok");
            response.sendRedirect("/Chicken/Main.jsp");

        } else if (log.IngresarLoginUsuario(us).equals("usuarios")) {
            session.setAttribute("usuario", us);
            request.getSession().setAttribute("mensaje", "Bienvenido");
            request.getSession().setAttribute("result", "ok");
            response.sendRedirect("/Chicken/ControladorUsuario?tipoc=AdministradorUsuarios");

        } else {
            response.sendRedirect("/Chicken/Vista/login.jsp");
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
