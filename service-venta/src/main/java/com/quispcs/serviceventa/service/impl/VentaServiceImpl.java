package com.quispcs.serviceventa.service.impl;

import com.quispcs.serviceventa.entity.Venta;
import com.quispcs.serviceventa.repository.DetalleRepository;
import com.quispcs.serviceventa.repository.VentaRepository;
import com.quispcs.serviceventa.service.VentaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class VentaServiceImpl implements VentaService {
    @Autowired
    VentaRepository ventaRepository;

    @Autowired
    DetalleRepository detalleRepository;

    @Override
    public List<Venta> findVentaAll() {
        return  ventaRepository.findAll();
    }


    @Override
    public Venta createVenta(Venta venta) {
        Venta ventaDB = ventaRepository.findByNumeroVenta (venta.getNumeroVenta() );
        if (ventaDB !=null){
            return  ventaDB;
        }
        venta.setEstado("CREATED");
        return ventaRepository.save(venta);
    }


    @Override
    public Venta updateVenta(Venta venta) {
        Venta ventaDB = getVenta(venta.getId());
        if (ventaDB == null){
            return  null;
        }
        ventaDB.setClienteId(venta.getClienteId());
        ventaDB.setDescripcion(venta.getDescripcion());
        ventaDB.setNumeroVenta(venta.getNumeroVenta());
        ventaDB.getDetalle().clear();
        ventaDB.setDetalle(venta.getDetalle());
        return ventaRepository.save(ventaDB);
    }


    @Override
    public Venta deleteVenta(Venta venta) {
        Venta ventaDB = getVenta(venta.getId());
        if (ventaDB == null){
            return  null;
        }
        ventaDB.setEstado("DELETED");
        return ventaRepository.save(ventaDB);
    }

    @Override
    public Venta getVenta(Long id) {
        return ventaRepository.findById(id).orElse(null);
    }
}
