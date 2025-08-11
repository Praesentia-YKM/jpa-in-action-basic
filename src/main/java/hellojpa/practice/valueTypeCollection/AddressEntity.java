package hellojpa.practice.valueTypeCollection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "address_history") // DB에 생성될 테이블 이름 지정
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 연관관계의 주인입니다.
    // 이 엔티티를 통해 외래키(member_id)를 관리하게 됩니다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String zipcode;

    private LocalDateTime createdDate; // 이력 추적을 위한 생성일

    // JPA 스펙상 기본 생성자
    protected AddressEntity() {
    }

    // 초기 생성을 위한 생성자
    public AddressEntity(String city, String street, String zipcode, LocalDateTime createdDate) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
        this.createdDate = createdDate;
    }

    //== 연관관계 편의 메서드 ==//
    /**
     * Member와의 양방향 관계를 설정합니다.
     * 이 메서드를 통해 Member의 addressHistory 리스트에도 추가됩니다.
     */
    public void setMember(Member member) {
        // 기존에 연관된 Member가 있다면, 관계를 제거합니다. (안정성을 위해)
        if (this.member != null) {
            this.member.getAddressHistory().remove(this);
        }
        this.member = member;
        member.getAddressHistory().add(this); // Member의 리스트에도 자신을 추가
    }


    //== Getter 메서드 ==//
    public Long getId() {
        return id;
    }

    public Member getMember() {
        return member;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
}
