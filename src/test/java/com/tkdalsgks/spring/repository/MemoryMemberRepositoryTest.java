package com.tkdalsgks.spring.repository;

import com.tkdalsgks.spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest {

      MemoryMemberRepository repository = new MemoryMemberRepository();

      // 테스트는 순서 상관없이 실행되기 때문에 메소드 하나 끝날때마다 저장소를 비워줘야 한다.
      @AfterEach
      public void afterEach() {
            repository.clearStore();
      }

      @Test
      public void save() {
            Member member = new Member();
            member.setName("spring");

            repository.save(member);

            Member result = repository.findById(member.getId()).get();
            assertThat(member).isEqualTo(result);
      }

      @Test
      public void findByName() {
            Member member1 = new Member();
            member1.setName("spring1");
            repository.save(member1);

            Member member2 = new Member();
            member2.setName("spring1");
            repository.save(member2);

            Member result = repository.findByName("spring1").get();

            assertThat(result).isEqualTo(member1);
      }

      @Test
      public void findAll() {
            Member member1 = new Member();
            member1.setName("spring1");
            repository.save(member1);

            Member member2 = new Member();
            member2.setName("spring1");
            repository.save(member2);

            List<Member> result = repository.findAll();

            assertThat(result.size()).isEqualTo(2);
      }
}
