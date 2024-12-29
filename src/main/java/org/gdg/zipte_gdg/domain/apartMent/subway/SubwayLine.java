package org.gdg.zipte_gdg.domain.apartMent.subway;

import jakarta.persistence.*;

@Entity
public class SubwayLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; //

//    @OneToMany(mappedBy = "subwayLine")
//    private List<SubwayStation> subwayStation = new ArrayList<>();

}
