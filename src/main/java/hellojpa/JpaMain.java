package hellojpa;

import hellojpa.practice.inheritance.Item;
import hellojpa.practice.inheritance.Movie;
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

            Movie movie = new Movie();
            movie.setDirector("aaaa");
            movie.setActor("bbbb");
            movie.setName("바람과함께사라지다"); // 'name'은 부모 클래스인 Item으로부터 상속받은 속성
            movie.setPrice(10000); // 'price'는 부모 클래스인 Item으로부터 상속받은 속성
            
            // 영속성 컨텍스트에 저장
            em.persist(movie);

            // 영속성 컨텍스트의 변경 내용을 데이터베이스에 동기화
            em.flush();
            em.clear(); // 영속성 컨텍스트를 비워서 1차 캐시를 지움

            // 데이터베이스에서 다시 조회 (em.clear() 했기 때문에 SELECT 쿼리가 실행됨)
            Item item = em.find(Item.class, movie.getId());
            System.out.println("findMovie = " + item); // PER_CLASS 상속옵션(InheritanceType) 쓰면 하위항목들 찾을때 union all 로 select 해와서 비효율

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