package hellojpa.practice.valueTypeCollection;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    // mappedBy: 연관관계의 주인이 아님을 명시합니다. (AddressEntity의 'member' 필드가 주인)
    // cascade: Member의 생명주기를 AddressEntity도 따라가도록 영속성을 전이합니다.
    // orphanRemoval: Member의 컬렉션에서 AddressEntity가 제거되면 DB에서도 삭제됩니다.
    @OneToMany(
            mappedBy = "member",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<AddressEntity> addressHistory = new ArrayList<>();

    // JPA 스펙상 기본 생성자는 필수입니다.
    // 외부에서 사용하지 못하도록 protected로 선언하는 것이 좋습니다.
    protected Member() {
    }

    // 초기 생성을 위한 생성자
    public Member(String username) {
        this.username = username;
    }

    //== 비즈니스 로직(도메인 로직) ==//
    /**
     * 회원의 주소 이력을 추가합니다.
     * AddressEntity를 생성하고 연관관계를 설정합니다.
     */
    public void addAddressHistory(String city, String street, String zipcode) {
        AddressEntity newAddress = new AddressEntity(city, street, zipcode, LocalDateTime.now());
        // 연관관계 편의 메서드를 통해 양방향 관계를 설정합니다.
        newAddress.setMember(this);
    }


    //== Getter 메서드 ==//
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public List<AddressEntity> getAddressHistory() {
        return addressHistory;
    }
}
