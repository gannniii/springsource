package com.example.novels.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.novels.entity.Member;
import com.example.novels.entity.Novel;

public interface MemberRepository extends JpaRepository<Member, String> {

}
