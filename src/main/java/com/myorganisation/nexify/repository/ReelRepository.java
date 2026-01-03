package com.myorganisation.nexify.repository;

import com.myorganisation.nexify.model.Reel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReelRepository extends JpaRepository<Reel, Long> {
}
