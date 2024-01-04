package hello.hellospring.repository;

import hello.hellospring.domain.Member;


import java.util.List;
import java.util.Optional;
/*
* 회원 저장소 인터페이스
* */
public interface MemberRepository {
    Member save(Member member); // 회원를 저장
    Optional<Member> findById(Long id); //회원 id를 찾음
    Optional<Member> findByName(String name); //회원 이름을 찾음
    List<Member> findAll(); // 모든 회원을 조회
}
