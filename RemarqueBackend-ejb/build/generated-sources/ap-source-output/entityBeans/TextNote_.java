package entityBeans;

import entityBeans.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-10T14:01:10")
@StaticMetamodel(TextNote.class)
public class TextNote_ { 

    public static volatile SingularAttribute<TextNote, Boolean> isTodoList;
    public static volatile SingularAttribute<TextNote, String> noteId;
    public static volatile SingularAttribute<TextNote, String> noteData;
    public static volatile SingularAttribute<TextNote, Date> noteTimestamp;
    public static volatile SingularAttribute<TextNote, User> username;

}