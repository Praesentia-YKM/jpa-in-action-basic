package hellojpa.practice.manyToManyEx;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Item2")
public class Item {
    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();
}
