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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonBackReference;

/**
 *
 * @author Divij Bhatia
 */
@Entity
@Table(name = "text_note")
@XmlRootElement
@NamedQueries({
    
    @NamedQuery(name = "TextNote.findAll", query = "SELECT t FROM TextNote t")
    , @NamedQuery(name = "TextNote.findByNoteId", query = "SELECT t FROM TextNote t WHERE t.noteId = :noteId")
    , @NamedQuery(name = "TextNote.findByNoteTimestamp", query = "SELECT t FROM TextNote t WHERE t.noteTimestamp = :noteTimestamp")
    , @NamedQuery(name = "TextNote.findByIsTodoList", query = "SELECT t FROM TextNote t WHERE t.isTodoList = :isTodoList")})
public class TextNote implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "note_id")
    private String noteId;
    @Lob
    @Size(max = 65535)
    @Column(name = "note_data")
    private String noteData;
    @Basic(optional = false)
    @NotNull
    @Column(name = "note_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date noteTimestamp;
    @Column(name = "is_todo_list")
    private Boolean isTodoList;
    @JoinColumn(name = "username", referencedColumnName = "username")
    @ManyToOne
    @JsonBackReference
    private User username;

    public TextNote() {
    }

    public TextNote(String noteId) {
        this.noteId = noteId;
    }

    public TextNote(String noteId, Date noteTimestamp) {
        this.noteId = noteId;
        this.noteTimestamp = noteTimestamp;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public String getNoteData() {
        return noteData;
    }

    public void setNoteData(String noteData) {
        this.noteData = noteData;
    }

    public Date getNoteTimestamp() {
        return noteTimestamp;
    }

    public void setNoteTimestamp(Date noteTimestamp) {
        this.noteTimestamp = noteTimestamp;
    }

    public Boolean getIsTodoList() {
        return isTodoList;
    }

    public void setIsTodoList(Boolean isTodoList) {
        this.isTodoList = isTodoList;
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
        if (!(object instanceof TextNote)) {
            return false;
        }
        TextNote other = (TextNote) object;
        if ((this.noteId == null && other.noteId != null) || (this.noteId != null && !this.noteId.equals(other.noteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityBeans.TextNote[ noteId=" + noteId + " ]";
    }
    
}
