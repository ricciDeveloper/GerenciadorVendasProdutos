package me.project.Service;

import me.project.Model.ClientModel;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<ClientModel> findAllClients();
    Optional<ClientModel> findById(Long id);
    void createClient(ClientModel clientModel);
    void deleteByName(String name);
}
