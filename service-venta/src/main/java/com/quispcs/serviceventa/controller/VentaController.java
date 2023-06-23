package com.quispcs.serviceventa.controller;

import com.quispcs.serviceventa.entity.Venta;
import com.quispcs.serviceventa.model.Cliente;
import com.quispcs.serviceventa.service.VentaService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/venta")
public class VentaController {
    @Autowired
    VentaService ventaService;

    @GetMapping
    public ResponseEntity<List<Venta>> listAllVentas() {
        List<Venta> ventas = ventaService.findVentaAll();
        if (ventas.isEmpty()) {
            return  ResponseEntity.noContent().build();
        }
        return  ResponseEntity.ok(ventas);
    }
    @CircuitBreaker(name="clienteCB", fallbackMethod = "fallbackgetClientes")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Venta> getVenta(@PathVariable("id") long id) {
        log.info("ventas con id {}", id);
        Venta venta  = ventaService.getVenta(id);
        if (null == venta) {
            log.error("id {} no encontrado.", id);
            return  ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(venta);
    }
    @CircuitBreaker(name="clienteCB", fallbackMethod = "fallbacksaveClientes")
    @PostMapping
    public ResponseEntity<Venta> createVenta(@Valid @RequestBody Venta venta, BindingResult result) {
        log.info("creando venta : {}", venta);
        Venta ventaDB = ventaService.createVenta(venta);

        return  ResponseEntity.status( HttpStatus.CREATED).body(ventaDB);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateVenta(@PathVariable("id") long id, @RequestBody Venta venta) {
        log.info("editando venta con id {}", id);

        venta.setId(id);
        Venta currentVenta=ventaService.updateVenta(venta);

        if (currentVenta == null) {
            log.error("id {} no encontrado.", id);
            return  ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(currentVenta);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Venta> deleteVenta(@PathVariable("id") long id) {
        log.info("eliminando venta con id: {}", id);

        Venta venta = ventaService.getVenta(id);
        if (venta == null) {
            log.error("id {} no encontrado.", id);
            return  ResponseEntity.notFound().build();
        }
        venta = ventaService.deleteVenta(venta);
        return ResponseEntity.ok(venta);
    }
    private ResponseEntity <List<Cliente>> fallbackgetClientes(@PathVariable("ventaid")int id ,RuntimeException exception){
        return new ResponseEntity("La venta : "+id+"tiene los clientes fuera de servicio",HttpStatus.OK);

    }
    private ResponseEntity <List<Cliente>> fallbacksaveClientes(@PathVariable("ventaid")int id ,@RequestBody Cliente cliente,RuntimeException exception){
        return new ResponseEntity("La venta : "+id+ "no cuenta con los clientes activos ",HttpStatus.OK);

    }

}
