package com.pinku.repository;

import com.pinku.model.Predisenio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PredisenioRepository extends JpaRepository<Predisenio, String> {

}
