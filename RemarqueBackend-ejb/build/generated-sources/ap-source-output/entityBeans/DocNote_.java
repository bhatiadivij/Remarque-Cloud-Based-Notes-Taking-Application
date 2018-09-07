package entityBeans;

import entityBeans.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-10T14:01:10")
@StaticMetamodel(DocNote.class)
public class DocNote_ { 

    public static volatile SingularAttribute<DocNote, String> filetype;
    public static volatile SingularAttribute<DocNote, String> fileName;
    public static volatile SingularAttribute<DocNote, String> noteId;
    public static volatile SingularAttribute<DocNote, Date> noteTimestamp;
    public static volatile SingularAttribute<DocNote, User> username;

}