package org.gdg.zipte_gdg.domain.own;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.gdg.zipte_gdg.domain.apt.Apt;
import org.gdg.zipte_gdg.domain.member.Member;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Own {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "apt_id")
    private Apt apt;

    private Date ownedAt;

}
