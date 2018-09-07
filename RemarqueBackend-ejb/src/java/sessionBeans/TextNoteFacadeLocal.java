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
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Divij Bhatia
 */
@Local
public interface TextNoteFacadeLocal {

    void create(TextNote textNote);

    void edit(TextNote textNote);

    void remove(TextNote textNote);

    TextNote find(Object id);

    List<TextNote> findAll();

    List<TextNote> findRange(int[] range);

    int count();
    
    public String createNote(String data, Date timestamp, String username, Boolean isTodoList);
    
    public Boolean updateNote(String noteId, String data);
    
    public Boolean deleteNote(String noteId);
    
    public ArrayList<TextNote> ListAllNotes(String username);
    
    public ArrayList<TextNote> findNotesById(ArrayList<String> noteIds);
}
