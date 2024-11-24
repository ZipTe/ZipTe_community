package org.gdg.zipte_gdg.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByUsername(String username);

    @Query("select m from Member m where m.email =:email")
    Member findByEmail(@Param("email") String email);
}
