/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entityBeans.Collaborator;
import entityBeans.User;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Divij Bhatia
 */
@Local
public interface CollaboratorFacadeLocal {

    void create(Collaborator collaborator);

    void edit(Collaborator collaborator);

    void remove(Collaborator collaborator);

    Collaborator find(Object id);

    List<Collaborator> findAll();

    List<Collaborator> findRange(int[] range);

    int count();
    
    public ArrayList<String> ListAllNotes(String username);
    
    public Boolean addCollaborator(String username, String noteid);
    
    public ArrayList<String> listAllCollaborators(String noteId);
    
    public Boolean removeAllCollaborators(String noteId);
    
    public Boolean removeCollaborator(String noteId, String collaboratorUsername);
    
}
