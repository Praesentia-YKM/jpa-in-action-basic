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

            System.out.println("--- commit 호출 ---");
            tx.commit();
            System.out.println("--- commit 완료 ---");

        } catch (Exception e) {
            tx.rollback(); // 실패 시 롤백
            e.printStackTrace();

        } finally {
            // 예외가 발생해도, 성공해도, 무조건 실행되어 커넥션을 반납함
            em.close();
        }

        // 어플리케이션이 완전히 끝나며녀 엔티티매니저팩토리를 닫아줘야한다.
        emf.close();
    }
}