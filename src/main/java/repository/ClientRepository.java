package repository;

import entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    /**
     * Encontra um cliente a partir do nome.
     *
     * @param name
     * @return lista de Users
     */
    List<Client> findByName(String name);

    /**
     * Encontra um cliente pelo nome e o retorna
     *
     * @param name
     * @return User
     */
    Client findOneByName(String name);
    
    Client findOneByCpf(String cpf);
}