package com.example.jpa.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import com.example.jpa.entity.Member;
import com.example.jpa.entity.QMember;
import com.example.jpa.entity.Member.RoleType;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void insertTest() {
        IntStream.rangeClosed(1, 20).forEach(i -> {
            Member member = Member.builder()
                    .name("김민성" + i)
                    .roleType(RoleType.USER)
                    .age(i * 5)
                    .description("user" + i)
                    .build();

            memberRepository.save(member);
        });
    }

    public void queryDslTest() {
        QMember member = QMember.member;

        // where name "김민성3"
        System.out.println(memberRepository.findAll(member.name.eq("김민성3")));

        // where age > 15
        System.out.println(memberRepository.findAll(member.age.gt(15)));

        // where roleType = USER
        System.out.println(memberRepository.findAll(member.roleType.eq(RoleType.USER)));

        // where name like '%민성%'
        System.out.println(memberRepository.findAll(member.name.contains("민성")));

        // 전체 조회 후 no의 내림차순으로 정렬하여 출력
        System.out.println(memberRepository.findAll(Sort.by("no").descending()));
    }

}
