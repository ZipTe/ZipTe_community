package org.gdg.zipte_gdg.domain.subway;

import jakarta.persistence.*;

import java.util.List;
import java.util.ArrayList;
@Entity
public class SubwayLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; //

//    @OneToMany(mappedBy = "subwayLine")
//    private List<SubwayStation> subwayStation = new ArrayList<>();

}
