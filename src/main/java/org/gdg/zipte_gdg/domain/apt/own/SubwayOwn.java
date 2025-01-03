package org.gdg.zipte_gdg.domain.apt.own;

import jakarta.persistence.*;
import org.gdg.zipte_gdg.domain.apt.apt.Apt;
import org.gdg.zipte_gdg.domain.apt.subway.SubwayStation;

@Entity
public class SubwayOwn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apt_id")
    private Apt apt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_id")
    private SubwayStation subwayStation;
}
