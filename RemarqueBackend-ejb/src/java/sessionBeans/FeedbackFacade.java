/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entityBeans.Feedback;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Divij Bhatia
 */
@Stateless
public class FeedbackFacade extends AbstractFacade<Feedback> implements FeedbackFacadeLocal {

    @PersistenceContext(unitName = "RemarqueBackend-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FeedbackFacade() {
        super(Feedback.class);
    }
    
    public Boolean createFeedback(String username, String feedback)
    {
        try {
            Feedback f= new Feedback();
            f.setUsername(username);
            f.setFeedback(feedback);
            create(f);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
