package com.example.ServidorJP.controller;

import com.example.ServidorJP.entity.Proveedor;
import com.example.ServidorJP.repository.ProveedorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/proveedor")
public class ProveedorController {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @PostMapping
    public ResponseEntity<Proveedor> addProveedor(@RequestBody @Valid Proveedor proveedor) {
        Proveedor savedProveedor = proveedorRepository.save(proveedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProveedor);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Proveedor> findByRuc(@RequestParam String ruc) {
        Optional<Proveedor> proveedor = proveedorRepository.findByRuc(ruc);
        return proveedor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Proveedor>> findAllProveedores() {
        List<Proveedor> proveedores = proveedorRepository.findAll();
        return !proveedores.isEmpty() ? ResponseEntity.ok(proveedores) : ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteProveedor(@PathVariable Integer id) {
        if (proveedorRepository.existsById(id)) {
            proveedorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/filtrar-por-fechas")
    public ResponseEntity<List<Proveedor>> filterByFechaRegistro(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        List<Proveedor> proveedores = proveedorRepository.findByFecharegistroBetween(startDate, endDate);
        return !proveedores.isEmpty() ? ResponseEntity.ok(proveedores) : ResponseEntity.noContent().build();
    }
}
