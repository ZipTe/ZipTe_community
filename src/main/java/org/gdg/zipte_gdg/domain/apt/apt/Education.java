package org.gdg.zipte_gdg.domain.apt.apt;

import jakarta.persistence.*;

@Entity
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String coordination;

    @ManyToOne(fetch = FetchType.LAZY)
    private Apt apt;

}
