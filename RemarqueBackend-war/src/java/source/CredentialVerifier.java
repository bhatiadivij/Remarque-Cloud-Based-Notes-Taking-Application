/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sessionBeans.UserFacade;

/**
 *
 * @author Divij Bhatia
 */
public class CredentialVerifier extends HttpServlet {

    @EJB
    UserFacade uf;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Boolean result;
        HttpSession httpSession=request.getSession(true);
        Gson gson=new Gson();
        PrintWriter out = response.getWriter();
        String username="";
        String password="";
        String json="";
        response.setContentType("text/html;charset=UTF-8");
        try{
           username=request.getParameter("username");
           password=request.getParameter("password");
           result=uf.verifyCredential(username, password);
        }catch(Exception ex)
        {
           result=false;
        }
        
        if(result)
        {
//            json=gson.toJson("Login Successful");
            httpSession.setAttribute("username", username);
            httpSession.setAttribute("password", password);
            response.sendRedirect("NoteOperations.jsp");
        }
        else
        {
            json=gson.toJson("Login Failed");
            RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF\\result.jsp?rs"+json);
            dispatcher.forward(request, response);
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
