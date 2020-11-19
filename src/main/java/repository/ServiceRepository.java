package repository;

import entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

    /**
     * Encontra um cliente a partir do nome.
     *
     * @param name
     * @return lista de Users
     */
    List<Service> findByName(String name);

    /**
     * Encontra um cliente pelo nome e o retorna
     *
     * @param name
     * @return User
     */
    Service findOneByName(String name);
    
}