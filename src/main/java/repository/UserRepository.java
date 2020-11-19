package repository;

import entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Encontra um usuario a partir do nome.
     *
     * @param name
     * @return lista de Users
     */
    List<User> findByName(String name);

    /**
     * Encontra um usuario pelo nome e o retorna
     *
     * @param name
     * @return User
     */
    User findOneByName(String name);
}