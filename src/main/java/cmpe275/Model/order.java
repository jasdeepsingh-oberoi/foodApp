package cmpe275.Model;
import javax.persistence.*;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import cmpe275.util.JsonDateSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;
/**
 * Created by wanghao on 5/1/16.
 */
@Entity
@Table(name = "transaction")
@NamedQueries({
        @NamedQuery(name = "Order.findByStartTime", query = "SELECT c From Order c WHERE c.start_time = :start_time"),
})
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ORDER_ID")
    private String order_id;
    @Column(name = "CHEF_ID")
    private String chef_id;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "START_TIME")
    private Date start_time;
    @Column(name = "END_TIME")
    private Date end_time;
    @Column(name = "PICKUP_TIME")
    private Date pickup_time;
    @Column(name = "QUANTITY")
    private Integer quantity;
    @Column(name = "MENU_ID")
    private String menu_id;



    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public void setChef_id(String chef_id) {
        this.chef_id = chef_id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public void setPickup_time(Date pickup_time) {
        this.pickup_time = pickup_time;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

    public String getOrder_id() {

        return order_id;
    }

    public String getChef_id() {
        return chef_id;
    }

    public String getEmail() {
        return email;
    }

    @JsonSerialize(using=JsonDateSerializer.class)
    public Date getStart_time() {
        return start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public Date getPickup_time() {
        return pickup_time;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getMenu_id() {
        return menu_id;
    }
}
