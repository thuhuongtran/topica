package vn.topica.itlab.spring.sport.entity;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "cart")
public class Cart implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "prod_ids")
    private String prodIds;
    @Column(name = "user_id")
    private Integer userId;
    private float total;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProdIds() {
        return prodIds;
    }

    public void setProdIds(String prodIds) {
        this.prodIds = prodIds;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
