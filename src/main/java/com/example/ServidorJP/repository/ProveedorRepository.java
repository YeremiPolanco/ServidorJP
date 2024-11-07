package com.example.ServidorJP.repository;

import com.example.ServidorJP.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {

    Optional<Proveedor> findByRuc(String ruc);

    List<Proveedor> findByFecharegistroBetween(LocalDate startDate, LocalDate endDate);
}
