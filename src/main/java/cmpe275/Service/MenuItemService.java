package cmpe275.Service;

import cmpe275.Model.MenuItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


/**
 * Created by wanghao on 3/23/16.
 */
@Service
public class MenuItemService {


    @PersistenceContext
    private EntityManager em;

    @Transactional
    public List<MenuItem> getAll() {
        List<MenuItem> result = em.createQuery("SELECT p from MenuItem p", MenuItem.class).getResultList();
        return result;
    }

    public List<MenuItem> getMenuByCategory(Integer category) {
        TypedQuery<MenuItem> query = em.createNamedQuery("MenuItem.findByCategory", MenuItem.class);
        query.setParameter("category", category);
        List<MenuItem> results = query.getResultList();
        return results;
    }

    @Transactional
    public MenuItem getById(String id) {

        MenuItem p1 = (MenuItem)em.find(MenuItem.class,id);
        return p1;
    }

    public List<MenuItem> getByName(String name) {
        TypedQuery<MenuItem> query = em.createNamedQuery("MenuItem.findByName", MenuItem.class);
        query.setParameter("name", name);
        List<MenuItem> results = query.getResultList();
        return results;
    }



    @Transactional
    public void add(MenuItem p) {
//        System.out.println("in add method");
        em.persist(p);
    }

    @Transactional
    public void update(MenuItem p) {
        em.merge(p);
    }

    @Transactional
    public void delete(String id) {
        MenuItem p1 = (MenuItem)em.find(MenuItem.class,id);
        em.remove(p1);
    }
}
