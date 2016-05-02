package cmpe275.Service;
import cmpe275.Model.MenuItem;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import cmpe275.Model.Order;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;

/**
 * Created by wanghao on 5/1/16.
 */
@Service
public class OrderService {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public List<Order> getAll() {
        List<Order> result = em.createQuery("SELECT p from Order p", Order.class).getResultList();

        return result;
    }

    @Transactional
    public Order getById(String id) {

        Order p1 = (Order)em.find(Order.class,id);
        return p1;
    }

    @Transactional
    public List<Order> getByStartTime(Date starttime) {
        TypedQuery<Order> query = em.createNamedQuery("Order.findByStartTime", Order.class);
        query.setParameter("start_time", starttime);
        List<Order> results = query.getResultList();
        return results;
    }

    @Transactional
    public void add(Order p) {
        System.out.println("in add order");
        em.persist(p);
    }

    @Transactional
    public void update(Order p) {
        em.merge(p);
    }

    @Transactional
    public void delete(String id) {
        Order p1 = (Order)em.find(Order.class,id);
        em.remove(p1);
    }

}
