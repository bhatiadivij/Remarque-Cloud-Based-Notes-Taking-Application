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
public interface DocNoteFacadeLocal {

    void create(DocNote docNote);

    void edit(DocNote docNote);

    void remove(DocNote docNote);

    DocNote find(Object id);

    List<DocNote> findAll();

    List<DocNote> findRange(int[] range);

    int count();
    
    public Boolean createNote(String fileName, String fileType, Date timestamp, String username);
    
    public Boolean deleteNote(String noteId);
    
    public ArrayList<DocNote> ListAllNotes(String username);
    
    public ArrayList<DocNote> findNotesById(ArrayList<String> noteIds);
}
