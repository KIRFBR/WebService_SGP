package repository;

import entity.MyService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.sql.Date;
import java.util.List;

@Repository
public interface MyServiceRepository extends JpaRepository<MyService, Long> {

    /**
     * Encontra um meu serviço a partir do cpf.
     *
     * @param name
     * @return lista de Users
     */
    List<MyService> findByClient(String client);

    /**
     * Encontra um Meu Serviço pelo nome do serviço
     *
     * @param name
     * @return User
     */
    MyService findOneByService(String service);
    
    @Query("select a from MyService a where a.dtVenc < CURRENT_DATE and a.dtPgm is null")
    Iterable<MyService> findAllDtVenc();
    
    
    MyService findOneById(Long id);
    
}