/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sessionBeans.CollaboratorFacadeLocal;

/**
 *
 * @author Divij Bhatia
 */
public class CollaboratorOperations extends HttpServlet {

    @EJB
    CollaboratorFacadeLocal cfl;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("op");
        String noteId,json="",collaboratorUsername;
        
        switch (operation) {
            case "rac":
                        noteId = request.getParameter("noteId");
                        if(cfl.removeAllCollaborators(noteId))
                            json="All collaborators removed successfully.!";
                        else
                            json="Collaborator removal failed";
                        break;
                        
            case "rc":
                        collaboratorUsername=request.getParameter("cUsername");
                        noteId = request.getParameter("noteId");
                        if(cfl.removeCollaborator(noteId,collaboratorUsername))
                            json="Collaborator removed successfully.!";
                        else
                            json="Collaborator removal failed";
                        break;
                        
            case "ac":
                        collaboratorUsername=request.getParameter("cUsername");
                        noteId = request.getParameter("noteId");
                        if(cfl.addCollaborator(collaboratorUsername,noteId))
                            json="Collaborator added successfully.!";
                        else
                            json="Collaborator not added";
                        break;
            case "lac":
                        noteId = request.getParameter("noteId");
                        ArrayList<String> usernames=cfl.listAllCollaborators(noteId);
                        String usernameString="";
                        for(String s : usernames)
                        {
                            usernameString=usernameString+"_"+s;
                        }
                        usernameString=usernameString.trim();
                        json=usernameString;
                        System.out.println(usernameString);
//                        response.sendRedirect("NoteOperations.jsp?msg="+usernameString);
                        break;
        }
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            response.sendRedirect("NoteOperations.jsp?msg="+json);
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
