package hello.hellospring.repository;

import hello.hellospring.domain.Member;
//import org.junit.jupiter.api.Assertions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

//    MemberRepository repository = new MemoryMemberRepository();

    MemoryMemberRepository repository = new MemoryMemberRepository();


    /*
    * 한테스트가 끝나면 실행되는 @AfterEach
    * 테스트는 순서의 관계없이, 서로 의존관계 없이 설계가 되어야한다.
    * 테스트가 끝나면 객체가 이미 있기 때문에 다른 테스트케이스가 오류날 수 있다.
    * */
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    /* 회원 저장 테스트*/
    @Test // JUnit Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); //get해서 Member 객체의 result
        System.out.println("result = " + (result == member));
        /* org.junit.jupiter.api.Assertions
        *  assetEquals(변수, 기대값)
        * */
//        Assertions.assertEquals(member, null);
        /*
        * static import된 org.assertj.core.api.Assertions
        * member의 기대값은 result
        * */
        assertThat(member).isEqualTo(result);
    }

    /* 회원 이름 찾기 테스트 */
    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByname("spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    /* 전체 회원 조회 테스트 */
    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
