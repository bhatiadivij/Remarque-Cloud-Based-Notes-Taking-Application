/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import entityBeans.DocNote;
import entityBeans.TextNote;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Divij Bhatia
 */
public class NoteList implements Serializable{
   private ArrayList<TextNote> textNotes;
   private ArrayList<DocNote> docNotes;
   private ArrayList<TextNote> collaboratedTextNotes;
   private ArrayList<DocNote> collaboratedDocNotes;
   
    public ArrayList<TextNote> getCollaboratedTextNotes() {
        return collaboratedTextNotes;
    }

    public void setCollaboratedTextNotes(ArrayList<TextNote> collaboratedTextNotes) {
        this.collaboratedTextNotes = collaboratedTextNotes;
    }

    public ArrayList<DocNote> getCollaboratedDocNotes() {
        return collaboratedDocNotes;
    }

    public void setCollaboratedDocNotes(ArrayList<DocNote> collaboratedDocNotes) {
        this.collaboratedDocNotes = collaboratedDocNotes;
    }
   
    public ArrayList<TextNote> getTextNotes() {
        return textNotes;
    }

    public void setTextNotes(ArrayList<TextNote> textNotes) {
        this.textNotes = textNotes;
    }

    public ArrayList<DocNote> getDocNotes() {
        return docNotes;
    }

    public void setDocNotes(ArrayList<DocNote> docNotes) {
        this.docNotes = docNotes;
    }

    public NoteList() {
        textNotes=new ArrayList<>();
        docNotes=new ArrayList<>();
    }
    
}
