package org.gdg.zipte.domain.user.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByUsername(String username);

    @Query("select m from Member m where m.email =:email")
    Member findByEmail(@Param("email") String email);
}
