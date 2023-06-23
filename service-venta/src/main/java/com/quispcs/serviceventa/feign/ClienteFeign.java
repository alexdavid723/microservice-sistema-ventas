package com.quispcs.serviceventa.feign;

import com.quispcs.serviceventa.model.Cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient(name = "Cliente-Service", fallback = ClienteHystrixFallbackFactory.class)// path = "/clientes")
public interface ClienteFeign {
    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> getCliente(@PathVariable("id") long id);

}
