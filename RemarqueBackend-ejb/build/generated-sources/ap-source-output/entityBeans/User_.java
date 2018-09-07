package entityBeans;

import entityBeans.DocNote;
import entityBeans.TextNote;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-10T14:01:10")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile CollectionAttribute<User, DocNote> docNoteCollection;
    public static volatile CollectionAttribute<User, TextNote> textNoteCollection;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> name;
    public static volatile SingularAttribute<User, String> photo;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, String> username;

}