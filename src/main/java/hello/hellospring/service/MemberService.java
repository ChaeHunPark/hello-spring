package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public class MemberService {

    private final MemberRepository memberRepository;

    /* 외부에서 직접 객체를 넣어준다.
    *  DI
    * */

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /*
    * 회원가입
    * */
    public Long join(Member member){

        // ms단위 시간 측정하기.. AOP가 필요한이유

            validateDuplicateMember(member);
            memberRepository.save(member);
            return member.getId();

//        long start = System.currentTimeMillis();
//        try {
//            validateDuplicateMember(member);
//            memberRepository.save(member);
//            return member.getId();
//
//        } finally {
//            long finish = System.currentTimeMillis();
//            long timeMs = finish - start;
//            System.out.println("join = " + timeMs + "ms");
//        }

        
    }

    /*
    * 중복회원이 있는지 조회하는 메소드
    * */
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m ->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
        ;
    }


    /* 전체 회원 조회*/
    public List<Member> findMembers(){
            return memberRepository.findAll();

    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
