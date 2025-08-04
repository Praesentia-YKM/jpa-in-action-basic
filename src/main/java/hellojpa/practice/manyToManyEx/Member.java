package hellojpa.practice.manyToManyEx;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "Member2")
public class Member {
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;
    private String city;
    private String street;
    private String zipcode;

    @OneToMany(mappedBy = "member")
    private List<Order> orders;
}
