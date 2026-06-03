package com.example.nombreprovisionaldelproyecto.repository;

import com.example.nombreprovisionaldelproyecto.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {
}