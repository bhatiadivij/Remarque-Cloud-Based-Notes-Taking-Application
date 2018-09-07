/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import com.google.gson.Gson;
import entityBeans.TextNote;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.EJBs;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sessionBeans.DocNoteFacadeLocal;
import sessionBeans.TextNoteFacade;
import sessionBeans.TextNoteFacadeLocal;
import sessionBeans.UserFacade;

/**
 *
 * @author Divij Bhatia
 */
public class NoteOperations extends HttpServlet {
    @EJB
    TextNoteFacadeLocal tnf;
    
    @EJB
    DocNoteFacadeLocal dnf;
    
    @EJB
    UserFacade uf;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String operation=request.getParameter("op");
        Gson gson=new Gson();
        String noteId;
        String data;
        String username;
        Boolean isTodoList;
        String fileName; 
        String fileType;
        Boolean result=false;
        String json="";
        String user="";
        String password="";
        try{
            HttpSession httpSession=request.getSession(false);
            if(httpSession!=null)
            {
                user=httpSession.getAttribute("username").toString();
                password=httpSession.getAttribute("password").toString();
            }
            else
            {
                response.sendRedirect("login.html");
            }
//           String user=request.getParameter("username");
//           String password=request.getParameter("password");
           result=uf.verifyCredential(user, password);
        
        }catch(Exception ex)
        {
           result=false;
        }
        
        if(result){
        
            switch(operation)
            {
                case "ctn":
                            data=request.getParameter("data");
                            isTodoList=Boolean.parseBoolean(request.getParameter("isTodoList"));
//                            username=request.getParameter("username");
                            username=user;
                            json=gson.toJson(tnf.createNote(data, new Date(), username, isTodoList));
                            break;
                case "dtn":
                            noteId=request.getParameter("noteId");
                            json=gson.toJson(tnf.deleteNote(noteId));
                            break;
                case "utn":
                            noteId=request.getParameter("noteId");
                            data=request.getParameter("data");
                            json=gson.toJson(tnf.updateNote(noteId, data));
                            break;
                case "cdn":
                            fileName=request.getParameter("fileName");
                            fileType=request.getParameter("fileType");
                            username=request.getParameter("username");
                            json=gson.toJson(dnf.createNote(fileName, fileType,new Date(), username));
                            break;
                case "ddn":
                            noteId=request.getParameter("noteId");
                            json=gson.toJson(dnf.deleteNote(noteId));
                            break;        
            }
            response.sendRedirect("NoteOperations.jsp?msg=Operation_Successful");
        }
        else
        {
            json=gson.toJson("Access Denied");
            response.sendRedirect("NoteOperations.jsp?msg=Access_Denied");
        }
                
//        RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF\\result.jsp?rs"+json);
//        dispatcher.forward(request, response);
        
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
