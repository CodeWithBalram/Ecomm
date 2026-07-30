package com.ecom.productservice.entity;

import com.ecom.productservice.config.IdGenerator;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Entity
@Getter
@Setter
@AllArgsConstructor@NoArgsConstructor
public class Category
{
    @Id
    private String categoryId;
    private String name;
    private String description;
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<Product> products;

    @PrePersist
    public void generatedId()
    {
        if(this.categoryId==null)
        {
            this.categoryId="cat-"+String.format("%05d", IdGenerator.getNextCategoryId());
        }

    }

}
