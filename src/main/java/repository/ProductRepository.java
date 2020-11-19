package repository;

import entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	/**
	 * Encontra um produto a partir do nome.
	 * 
	 * @param name
	 * @return lista de Product
	 */
	List<Product> findByName(String name);

    /**
     * Encontra um produto a partir da categoria.
     *
     * @param category
     * @return lista de Product
     */
	List<Product> findByCategory(String category);

    /**
     * Encontra um produto pelo nome e o retorna
     *
     * @param name
     * @return Product
     */
	Product findOneByName(String name);

    /**
     * Encontra produtos contendo palavras no nome ou na descricao.
     *
     * @param name
     * @param description
     * @return lista de Product
     */
	List<Product> findByNameContainingOrDescriptionContaining(String name, String description);
}