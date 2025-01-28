package com.eedo.project.zipte.infrastructure.out.persistence.jpa.user;

import com.eedo.project.zipte.domain.user.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByUsername(String username);

    @Query("select m from Member m where m.email =:email")
    Member findByEmail(@Param("email") String email);
}
