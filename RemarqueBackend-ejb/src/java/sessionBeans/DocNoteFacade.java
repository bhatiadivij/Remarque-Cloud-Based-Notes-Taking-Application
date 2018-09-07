/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entityBeans.DocNote;
import entityBeans.TextNote;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Divij Bhatia
 */
@Stateless
public class DocNoteFacade extends AbstractFacade<DocNote> implements DocNoteFacadeLocal {

    @PersistenceContext(unitName = "RemarqueBackend-ejbPU")
    private EntityManager em;
    
    @EJB 
    UserFacade uf;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DocNoteFacade() {
        super(DocNote.class);
    }
     
    @Override
    public Boolean createNote(String fileName, String fileType, Date timestamp, String username)
    {
        try {
            String noteId="";
            synchronized(this){
                while(true){
                    Random random = new Random();
                    int range = 100000 - 10000 + 1;
                    int randomNum =  random.nextInt(range) + 10000;
                    noteId=username.substring(0,2)+"DN"+randomNum;
                    if(find(noteId)==null)
                        break;
                }
            }
            
            DocNote docNote=new DocNote();
            docNote.setFileName(fileName);
            docNote.setFiletype(fileType);
            docNote.setNoteTimestamp(timestamp);
            docNote.setUsername(uf.find(username));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean deleteNote(String noteId) {
        try {
            DocNote docNote=find(noteId);
            remove(docNote);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public ArrayList<DocNote> ListAllNotes(String username)
    {
        ArrayList<DocNote> allDocNotes=new ArrayList<>(findAll());
        ArrayList<DocNote> docNotes=new ArrayList<>();
        System.out.println("Found");
        for(DocNote dn:allDocNotes)
        {
            if(dn.getUsername().getUsername().equals(username))
            {
                
                docNotes.add(dn);
            }
        }
        System.out.println(docNotes.size());
        return docNotes;
    }
    
    @Override
    public ArrayList<DocNote> findNotesById(ArrayList<String> noteIds)
    {
        DocNote temp;
        ArrayList<DocNote> notes=new ArrayList<>();
        for(String id : noteIds)
        {
            try {
                if((temp=find(id))!=null)
                notes.add(temp);
            } catch (Exception e) {
            }
        }
        return notes;
    }
}
