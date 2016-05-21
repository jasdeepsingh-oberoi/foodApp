package edu.sjsu.cmpe275.termprj.model;

import java.util.ArrayList;
import java.util.Date;


import javax.persistence.*;


/**
 * Created by wanghao on 3/23/16.
 */
@Entity
@Table(name = "menu_items")
public class MenuItem {
        private Integer category;
        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private String id;
        @Column
        private String  name;
        @Column
        private String image_path;
        @Column
        private String unit_price;
        @Column
        private Integer calorie_count;
        @Column
        private Integer prep_time;
        @Column
        private Integer is_deleted;
      
    public void setIs_deleted(Integer is_deleted) {
        this.is_deleted = is_deleted;
    }

    public Integer getIs_deleted() {

        return is_deleted;
    }

    public MenuItem() {

    }

    public void setImage_path(String image_path) {
            this.image_path = image_path;
        }

        public String getImage_path() {

            return image_path;
        }



        public void setCategory(Integer category) {
            this.category = category;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setUnit_price(String unit_price) {
            this.unit_price = unit_price;
        }

        public void setCalorie_count(Integer calorie_count) {
            this.calorie_count = calorie_count;
        }

        public Integer getCalorie_count() {

            return calorie_count;
        }

        public void setPrep_time(Integer prep_time) {
            this.prep_time = prep_time;
        }

        public Integer getCategory() {

            return category;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getUnit_price() {
            return unit_price;
        }


        public Integer getPrep_time() {
            return prep_time;
        }
}

