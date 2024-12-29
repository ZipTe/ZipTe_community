package org.gdg.zipte_gdg.domain.apartMent.subway;

import jakarta.persistence.*;
import org.gdg.zipte_gdg.domain.User.own.SubwayOwn;

@Entity
public class SubwayStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "line_id")
    private SubwayLine subwayLine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "own_id")
    private SubwayOwn subwayOwn;

}
