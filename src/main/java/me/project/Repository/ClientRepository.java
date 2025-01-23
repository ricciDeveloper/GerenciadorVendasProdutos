package me.project.Repository;

import jakarta.transaction.Transactional;
import me.project.Model.ClientModel;
import org.hibernate.annotations.ManyToAny;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ClientRepository extends JpaRepository<ClientModel, Long> {
    void deleteByName(String name);
}
