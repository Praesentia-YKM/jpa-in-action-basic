package hellojpa.practice.inheritance;

import jakarta.persistence.*;

@Entity(name = "Item_Inheritance")
@Inheritance(strategy = InheritanceType.JOINED) // 기본전략이 SINGLE_TABLE 이라서 한테이블에 상속컬럼이 다 들어온다
@DiscriminatorColumn    // 기본값은 DTYPE 이라는 컬럼명으로 마스터 테이블에 매핑컬럼을 만든다
public abstract class Item {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
