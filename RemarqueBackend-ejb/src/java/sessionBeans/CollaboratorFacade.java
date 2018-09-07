/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entityBeans.Collaborator;
import entityBeans.CollaboratorPK;
import entityBeans.DocNote;
import entityBeans.TextNote;
import entityBeans.User;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Divij Bhatia
 */
@Stateless
public class CollaboratorFacade extends AbstractFacade<Collaborator> implements CollaboratorFacadeLocal {

    @EJB
    UserFacade uf;

    @EJB
    TextNoteFacadeLocal tnf;

    @EJB
    DocNoteFacadeLocal dnf;

    @PersistenceContext(unitName = "RemarqueBackend-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CollaboratorFacade() {
        super(Collaborator.class);
    }

    @Override
    public Boolean addCollaborator(String username, String noteid) {
        try {
            if (uf.find(username).getName() != null) {
                CollaboratorPK collaboratorPK = new CollaboratorPK();
                collaboratorPK.setNoteId(noteid);
                collaboratorPK.setUsername(username);
                Collaborator collaborator = new Collaborator();
                collaborator.setCollaboratorPK(collaboratorPK);
                create(collaborator);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public Boolean removeAllCollaborators(String noteId) {
        try {

            Boolean flag = false;
            ArrayList<Collaborator> collaborators = new ArrayList(findAll());
            for (Collaborator c : collaborators) {
                flag = false;
                if (c.getCollaboratorPK().getNoteId().equals(noteId)) {
                    flag = removeCollaborator(noteId, c.getCollaboratorPK().getUsername());
                    if (flag == false) {
                        return false;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean removeCollaborator(String noteId, String collaboratorUsername) {
        try {
            remove(new Collaborator(collaboratorUsername, noteId));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ArrayList<String> ListAllNotes(String username) {
        ArrayList<Collaborator> allTextNotes = new ArrayList<>(findAll());
        ArrayList<String> noteIds = new ArrayList<>();
        System.out.println("Found");
        for (Collaborator c : allTextNotes) {
            if (c.getCollaboratorPK().getUsername().equals(username)) {

                noteIds.add(c.getCollaboratorPK().getNoteId());
            }
        }
        return noteIds;
    }

    @Override
    public Collaborator find(Object id) {
         return getEntityManager().createNamedQuery("Collaborator.findByNoteId", Collaborator.class).setParameter("noteId", id).setMaxResults(1).getSingleResult();
    }


    @Override
    public ArrayList<String> listAllCollaborators(String noteID) {
        ArrayList<Collaborator> collaborators=new ArrayList<>(findAll()); 
        ArrayList<String> collaborator=new ArrayList<>();
        for(Collaborator c:collaborators) {
            if(c.getCollaboratorPK().getNoteId().equals(noteID))
                collaborator.add(c.getCollaboratorPK().getUsername());
        }
        System.out.println(collaborator);
        return collaborator;
    }
}
