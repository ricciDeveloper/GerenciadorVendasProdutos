package me.project.Service.Impl;

import jakarta.transaction.Transactional;
import me.project.Model.ClientModel;
import me.project.Repository.ClientRepository;
import me.project.Service.ClientService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository repository;

    public ClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ClientModel> findAllClients() {
        return repository.findAll();
    }

    @Override
    public Optional<ClientModel> findById(Long id) {
        return repository.findById(id);
    }


    @Override
    public void createClient(ClientModel clientModel) {
        repository.save(clientModel);
    }
    @Transactional
    @Override
    public void deleteByName(String name) {
        repository.deleteByName(name);
    }
}
