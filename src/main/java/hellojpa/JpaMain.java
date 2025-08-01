package hellojpa;

import jakarta.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        // 엔티티매니저팩토리는 딱 하나마나 생성되어있어야한다.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 트랜잭션단위로 엔티티매니저가 생겨야한다. (디비 커넥션을 얻어서 쿼리를 날리고 종료하는)
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin(); // JPA는 트랜잭션이 존재해야 한다.

        try {
            // 1. INSERT 작업 준비
            Member member = new Member();
            member.setId(2L);
            member.setName("hello");
            em.persist(member); // -> '쓰기 지연' 저장소에 INSERT SQL 저장

            // 2. SELECT 작업
            Member findMember = em.find(Member.class, 1L);

            // 3. Null 체크로 NullPointerException 방지 : 이거없으면 쓰기지연때문에 npe발생으로 인해 전체가 롤백됨
            if (findMember != null) {
                // 이 로직은 실행되지 않음
                findMember.setName("hello_updated");
            }

            // JPQL 조회 코드 추가
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(0) // 페이징 처리 (0번부터 시작)
                    .setMaxResults(8)
                    .getResultList();

            for (Member m : result) {
                System.out.println("member.name = " + m.getName());
            }

            // 4. 예외 없이 commit 호출 성공
            tx.commit();

        } catch (Exception e) {
            tx.rollback(); // 실패 시 롤백

        } finally {
            // 예외가 발생해도, 성공해도, 무조건 실행되어 커넥션을 반납함
            em.close();
        }

        // 어플리케이션이 완전히 끝나며녀 엔티티매니저팩토리를 닫아줘야한다.
        emf.close();
    }
}