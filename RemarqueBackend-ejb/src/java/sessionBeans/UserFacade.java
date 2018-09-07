/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entityBeans.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Divij Bhatia
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "RemarqueBackend-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
   public void createUser(String username, String name, String email, String password, String photo)
    {
        User user=new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhoto(photo);
        user.setUsername(username);
        System.out.println("user created");
        create(user);
    }
   public String getName(String username)
    {
      return find(username).getName();
    }
   public User getUser(String username)
    {
      return find(username);
    }
   public boolean verifyCredential(String username, String password)
   {
       if(password.equals(find(username).getPassword()))
       {
           return true;
       }
       else
       {
           return false;
       }
   }
}
