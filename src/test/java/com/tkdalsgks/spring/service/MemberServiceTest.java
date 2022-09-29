package com.tkdalsgks.spring.service;

import com.tkdalsgks.spring.domain.Member;
import com.tkdalsgks.spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class MemberServiceTest {

      MemberService memberService;
      MemoryMemberRepository memberRepository;

      @BeforeEach
      public void beforeEach() {
            memberRepository = new MemoryMemberRepository();
            memberService = new MemberService(memberRepository);
      }

      // 테스트는 순서 상관없이 실행되기 때문에 메소드 하나 끝날때마다 저장소를 비워줘야 한다.
      @AfterEach
      public void afterEach() {
            memberRepository.clearStore();
      }

      @Test
      void 회원가입() {
            // given
            Member member = new Member();
            member.setName("spring");

            // when
            Long saveId = memberService.join(member);

            // then
            Member findMember = memberService.findOne(saveId).get();
            assertThat(member.getName()).isEqualTo(findMember.getName());
      }

      @Test
      public void 중복_회원_예외() {
            //given
            Member member1 = new Member();
            member1.setName("spring");

            Member member2 = new Member();
            member2.setName("spring");

            //when
            memberService.join(member1);
            IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

            /*
            try {
                  memberService.join(member2);
                  fail();
            } catch(IllegalStateException e) {
                  assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
            }
            */

            //then

      }

      @Test
      void findMembers() {
      }

      @Test
      void findOne() {
      }
}