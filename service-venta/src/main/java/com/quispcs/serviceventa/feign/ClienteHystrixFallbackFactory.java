package com.quispcs.serviceventa.feign;

import com.quispcs.serviceventa.model.Cliente;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

//si la llamada al microservicio Cliente falla se devuelve un cliente con datos vacios
@Component
public class ClienteHystrixFallbackFactory implements ClienteFeign{
    @Override
    public ResponseEntity<Cliente> getCliente(long id) {
        Cliente cliente = Cliente.builder()
                .nombres("microservicio caido: cliente service (nombre)")
                .apellidos("microservicio caido: cliente service (apellidos)")
                .email("microservicio caido: cliente service (email)").build();
        return ResponseEntity.ok(cliente);
    }
}
