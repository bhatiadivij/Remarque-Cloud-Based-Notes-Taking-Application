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
import sessionBeans.UserFacade;

/**
 *
 * @author Divij Bhatia
 */
public class Signup extends HttpServlet {

    @EJB
    UserFacade uf;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Gson gson=new Gson();
        String message="";
        PrintWriter out = response.getWriter();
        try{
            String username = request.getParameter("username");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String photo = request.getParameter("photo");
            uf.createUser(username, name, email, password, photo);
            if(uf.getName(username).equals(name))
            {
              message="Signup Successful";  
              out.write(message);
              out.write("<a href='login.html'>Click here to login..</a>");
            }
            else
            {
                message="Signup Failed";
                out.write(message);
                out.write("<a href='index.html'>Click here to try again..</a>");
            }
        }
        catch(Exception ex)
        {
            message="Signup Failed";
            out.write(message);
            out.write("<a href='index.html'>Click here to try again..</a>");
        }
        
//        RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF\\result.jsp?rs"+gson.toJson(message));
//        dispatcher.forward(request, response);
    }

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
