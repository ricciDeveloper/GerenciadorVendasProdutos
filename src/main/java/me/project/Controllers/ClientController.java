package me.project.Controllers;

import jakarta.websocket.ClientEndpoint;
import me.project.Model.ClientModel;
import me.project.Service.ClientService;
import me.project.Service.Impl.ClientServiceImpl;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ClientController {
    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping("/buscarTodosClientes")
    public ResponseEntity<Iterable<ClientModel>> findAllClients(){
        return ResponseEntity.ok(service.findAllClients());
    }

    @PostMapping("/salvarCliente")
    public ResponseEntity<ClientModel> createClient(@RequestBody ClientModel Client){
        service.createClient(Client);
        return ResponseEntity.status(HttpStatus.CREATED).body(Client);
    }

    @DeleteMapping("/deletarCliente")
    public ResponseEntity<Void> deleteClient(@RequestParam String name){
        service.deleteByName(name);
        return ResponseEntity.ok().build();
    }
}
