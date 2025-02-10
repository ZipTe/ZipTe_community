package com.eedo.project.zipte.adapter.out.mongo.apt;

import com.eedo.project.zipte.domain.apt.Apt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AptRepository extends JpaRepository<Apt, Long> {
}
