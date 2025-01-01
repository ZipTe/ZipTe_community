package org.gdg.zipte_gdg.domain.user.own;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.gdg.zipte_gdg.domain.apartment.apt.Apt;
import org.gdg.zipte_gdg.domain.user.member.Member;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Own {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apt_id")
    private Apt apt;

    private Date ownedAt;

}
