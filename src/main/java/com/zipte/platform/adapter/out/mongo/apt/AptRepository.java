package com.zipte.platform.adapter.out.mongo.apt;

import com.zipte.platform.domain.apt.Apt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AptRepository extends JpaRepository<Apt, Long> {
}
