package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

// 멤버 구현체

public class MemoryMemberRepository implements MemberRepository{

    /*
    * 회원 저장소, 실무에서는 동시성 문제가 있을 수 있어서 공유되는 변수일 떄는
    * Concurrent HashMap을 쓰는게 좋다.
    * */
    private static Map<Long, Member> store = new HashMap<>();

    /*
    * 마찬가지로 동시성 문제로 Atomic Long을 쓰는게 좋다.
    * */
    private static long sequence = 0L; // 회원가입의 번호(인덱스)

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // 회원가입 번호를 늘리고, id에 저장
        store.put(member.getId(), member); // id와 member를 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // null 반환 가능성을 위해 ofNullable을 사용
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //ArrayList로 이용하여 반환
    }

    public void clearStore(){
        store.clear();
    }
}
