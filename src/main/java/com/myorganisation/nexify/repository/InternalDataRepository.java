package com.myorganisation.nexify.repository;

import com.myorganisation.nexify.model.InternalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternalDataRepository extends JpaRepository<InternalData, Long> {
}
