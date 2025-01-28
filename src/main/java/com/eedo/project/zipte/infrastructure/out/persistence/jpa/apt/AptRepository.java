package com.eedo.project.zipte.infrastructure.out.persistence.jpa.apt;

import com.eedo.project.zipte.domain.apt.Apt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AptRepository extends JpaRepository<Apt, Long> {
}
