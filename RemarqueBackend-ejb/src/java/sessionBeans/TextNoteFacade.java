/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entityBeans.DocNote;
import entityBeans.TextNote;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Divij Bhatia
 */
@Stateless
public class TextNoteFacade extends AbstractFacade<TextNote> implements TextNoteFacadeLocal {

    @PersistenceContext(unitName = "RemarqueBackend-ejbPU")
    private EntityManager em;
    
    @EJB 
    UserFacade uf;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TextNoteFacade() {
        super(TextNote.class);
    }
    
    @Override
    public String createNote(String data, Date timestamp, String username, Boolean isTodoList)
    {
        try{
            String noteId="";
            synchronized(this){
                while(true){
                    Random random = new Random();
                    int range = 100000 - 10000 + 1;
                    int randomNum =  random.nextInt(range) + 10000;
                    noteId=username.substring(0,2)+"TN"+randomNum;
                    if(find(noteId)==null)
                        break;
                }
            }

            TextNote textNote=new TextNote();
            textNote.setNoteData(data);
            textNote.setIsTodoList(isTodoList);
            textNote.setNoteTimestamp(timestamp);
            textNote.setUsername(uf.getUser(username));
            textNote.setNoteId(noteId);
            create(textNote);
            return noteId;
        }
        catch(Exception ex){
            return "";
        }
    }
    
    @Override
    public Boolean updateNote(String noteId, String data)
    {
        try {
            TextNote textNote = find(noteId);
            textNote.setNoteData(data);
            edit(textNote);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    @Override
     public Boolean deleteNote(String noteId)
    {
        try {
            TextNote textNote = find(noteId);
            remove(textNote);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    @Override
    public ArrayList<TextNote> ListAllNotes(String username)
    {
        ArrayList<TextNote> allTextNotes=new ArrayList<>(findAll());
        ArrayList<TextNote> textNotes=new ArrayList<>();
        System.out.println("Found");
        for(TextNote tn:allTextNotes)
        {
            if(tn.getUsername().getUsername().equals(username))
            {
                
                textNotes.add(tn);
            }
        }
        System.out.println(textNotes.size());
        return textNotes;
    }
    
    @Override
    public ArrayList<TextNote> findNotesById(ArrayList<String> noteIds)
    {
        TextNote temp;
        ArrayList<TextNote> notes=new ArrayList<>();
        for(String id : noteIds)
        {
            try {
                if((temp=find(id))!=null)
                    notes.add(find(id));
            } catch (Exception e) {
            }
        }
        return notes;
    }
}
