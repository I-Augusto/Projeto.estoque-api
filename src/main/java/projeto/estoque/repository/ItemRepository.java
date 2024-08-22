package projeto.estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projeto.estoque.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{

	boolean existsByName(String Name);
}
