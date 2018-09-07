/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityBeans;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Divij Bhatia
 */
@Entity
@Table(name = "collaborator")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Collaborator.findAll", query = "SELECT c FROM Collaborator c")
    , @NamedQuery(name = "Collaborator.findByUsername", query = "SELECT c FROM Collaborator c WHERE c.collaboratorPK.username = :username")
    , @NamedQuery(name = "Collaborator.findByNoteId", query = "SELECT c FROM Collaborator c WHERE c.collaboratorPK.noteId = :noteId")})
public class Collaborator implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CollaboratorPK collaboratorPK;

    public Collaborator() {
    }

    public Collaborator(CollaboratorPK collaboratorPK) {
        this.collaboratorPK = collaboratorPK;
    }

    public Collaborator(String username, String noteId) {
        this.collaboratorPK = new CollaboratorPK(username, noteId);
    }

    public CollaboratorPK getCollaboratorPK() {
        return collaboratorPK;
    }

    public void setCollaboratorPK(CollaboratorPK collaboratorPK) {
        this.collaboratorPK = collaboratorPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (collaboratorPK != null ? collaboratorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Collaborator)) {
            return false;
        }
        Collaborator other = (Collaborator) object;
        if ((this.collaboratorPK == null && other.collaboratorPK != null) || (this.collaboratorPK != null && !this.collaboratorPK.equals(other.collaboratorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityBeans.Collaborator[ collaboratorPK=" + collaboratorPK + " ]";
    }
    
}
