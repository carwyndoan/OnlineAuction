package miu.edu.auction.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

//@Data
//@Setter
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int category_id;

    @NotBlank
    private String name;

    @NotNull
    private int active;

    @NotBlank
    private String description;
    @ManyToMany(cascade = CascadeType.MERGE, mappedBy = "categories")
    //@JoinTable(name = "category_product", joinColumns = @JoinColumn(name = "category_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;// = new ArrayList<Product>();

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o){
        Category c1=(Category) o;
        if(c1.getName().equals(this.getName()) && c1.description.equals(this.description))
            return true;
        return false;
    }
}
