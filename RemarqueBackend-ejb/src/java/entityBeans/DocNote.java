/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityBeans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Divij Bhatia
 */
@Entity
@Table(name = "doc_note")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DocNote.findAll", query = "SELECT d FROM DocNote d")
    , @NamedQuery(name = "DocNote.findByNoteId", query = "SELECT d FROM DocNote d WHERE d.noteId = :noteId")
    , @NamedQuery(name = "DocNote.findByFileName", query = "SELECT d FROM DocNote d WHERE d.fileName = :fileName")
    , @NamedQuery(name = "DocNote.findByNoteTimestamp", query = "SELECT d FROM DocNote d WHERE d.noteTimestamp = :noteTimestamp")
    , @NamedQuery(name = "DocNote.findByFiletype", query = "SELECT d FROM DocNote d WHERE d.filetype = :filetype")})
public class DocNote implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "note_id")
    private String noteId;
    @Size(max = 100)
    @Column(name = "file_name")
    private String fileName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "note_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date noteTimestamp;
    @Size(max = 2)
    @Column(name = "filetype")
    private String filetype;
    @JoinColumn(name = "username", referencedColumnName = "username")
    @ManyToOne
    private User username;

    public DocNote() {
    }

    public DocNote(String noteId) {
        this.noteId = noteId;
    }

    public DocNote(String noteId, Date noteTimestamp) {
        this.noteId = noteId;
        this.noteTimestamp = noteTimestamp;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getNoteTimestamp() {
        return noteTimestamp;
    }

    public void setNoteTimestamp(Date noteTimestamp) {
        this.noteTimestamp = noteTimestamp;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public User getUsername() {
        return username;
    }

    public void setUsername(User username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (noteId != null ? noteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocNote)) {
            return false;
        }
        DocNote other = (DocNote) object;
        if ((this.noteId == null && other.noteId != null) || (this.noteId != null && !this.noteId.equals(other.noteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityBeans.DocNote[ noteId=" + noteId + " ]";
    }
    
}
