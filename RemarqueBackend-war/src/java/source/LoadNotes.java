package source;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entityBeans.DocNote;
import entityBeans.TextNote;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;
import org.codehaus.jackson.map.ObjectMapper;
import resources.NoteList;
import sessionBeans.CollaboratorFacadeLocal;
import sessionBeans.DocNoteFacadeLocal;
import sessionBeans.TextNoteFacadeLocal;
import sessionBeans.UserFacade;

/**
 *
 * @author Divij Bhatia
 */
public class LoadNotes extends HttpServlet {
    @EJB
    TextNoteFacadeLocal tnf;
    
    @EJB
    DocNoteFacadeLocal dnf;
    
    @EJB
    CollaboratorFacadeLocal cfl;
    
    @EJB
    UserFacade uf;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //String username=request.getParameter("username");
        String username=request.getSession(false).getAttribute("username").toString();
        ArrayList<TextNote> textNotes= tnf.ListAllNotes(username);
        ArrayList<DocNote> docNotes= dnf.ListAllNotes(username);
        ArrayList<String> collaboratedNoteIds=cfl.ListAllNotes(username);
        ArrayList<TextNote> collaboratedTextNotes=tnf.findNotesById(collaboratedNoteIds);
        ArrayList<DocNote> collaboratedDocNotes=dnf.findNotesById(collaboratedNoteIds);
        ObjectMapper mapper = new ObjectMapper();
        NoteList noteList=new NoteList();
        noteList.setTextNotes(textNotes);
        noteList.setDocNotes(docNotes);
        noteList.setCollaboratedDocNotes(collaboratedDocNotes);
        noteList.setCollaboratedTextNotes(collaboratedTextNotes);
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String json=mapper.writeValueAsString(noteList);
            out.write(json);
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
