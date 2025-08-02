package hellojpa;

import jakarta.persistence.*;
import java.util.Date;

/**
 * @Entity
 * - 이 클래스가 JPA가 관리하는 '엔티티'임을 나타냅니다.
 * - 이 어노테이션이 붙은 클래스는 데이터베이스의 테이블과 매핑됩니다.
 */
@Entity
public class Member {

    /**
     * @Id
     * - 해당 필드가 테이블의 기본 키(Primary Key)임을 나타냅니다.
     */
    @Id
    private Long id;

    /**
     * @Column(name = "username")
     * - 필드와 테이블의 컬럼을 매핑합니다.
     * - 'name' 속성: 데이터베이스 테이블의 컬럼명을 직접 지정합니다.
     * (만약 생략하면 필드명(name)을 따라 컬럼명이 생성됩니다.)
     * - 'nullable' 속성: false로 설정 시 'NOT NULL' 제약조건이 붙습니다. (기본값: true)
     * - 'length' 속성: 문자열 타입 컬럼의 길이를 지정합니다. (기본값: 255)
     */
    @Column(name = "name", nullable = false, length = 100)
    private String username;

    private Integer age;

    /**
     * @Enumerated(EnumType.STRING)
     * - Enum 타입을 데이터베이스에 어떻게 저장할지 지정합니다.
     * - EnumType.STRING: Enum의 이름을 문자열로 저장합니다. (권장)
     * - EnumType.ORDINAL: Enum의 순서를 숫자로 저장합니다. (사용 비권장)
     */
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    /**
     * @Temporal(TemporalType.TIMESTAMP)
     * - 날짜 타입(java.util.Date, Calendar)을 매핑할 때 사용합니다.
     * - TemporalType.DATE: 날짜 (ex: 2025-08-01)
     * - TemporalType.TIME: 시간 (ex: 23:59:59)
     * - TemporalType.TIMESTAMP: 날짜와 시간 (ex: 2025-08-01 23:59:59)
     * (참고: 최신 Java에서는 LocalDate, LocalDateTime을 사용하면 이 어노테이션이 필요 없습니다.)
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    /**
     * @Lob
     * - 데이터베이스의 BLOB, CLOB 타입과 매핑합니다.
     * - 필드 타입이 문자열이면 CLOB, 바이트 배열이면 BLOB으로 매핑됩니다.
     * - 아주 긴 텍스트나 바이너리 데이터를 저장할 때 사용합니다.
     */
    @Lob
    private String description;

    /**
     * @Transient
     * - 이 필드는 데이터베이스에 저장하거나 조회하지 않습니다.
     * - 객체에만 임시로 어떤 값을 보관하고 싶을 때 사용합니다.
     */
    @Transient
    private int temp;

    // JPA는 기본 생성자가 필수입니다.
    public Member() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }
}