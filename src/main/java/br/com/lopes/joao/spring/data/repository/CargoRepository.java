package br.com.lopes.joao.spring.data.repository;

import br.com.lopes.joao.spring.data.orm.Cargo;
import br.com.lopes.joao.spring.data.orm.CargoProjection;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CargoRepository extends PagingAndSortingRepository<Cargo, Integer>,
        JpaSpecificationExecutor<Cargo> {
    List<Cargo> findByDescription(String description);

    List<Cargo> findByDescriptionContains(String description);

    @Query("SELECT c FROM Cargo c WHERE c.description LIKE :description%")
    List<Cargo> findByDescriptionBeginWith(String description);

    @Query(value="SELECT * FROM cargos c WHERE c.description LIKE %:description",
    nativeQuery = true)
    List<Cargo> findByDescriptionEndWith(String description);

    @Query(value="SELECT c.description FROM cargos c",
            nativeQuery = true)
    List<CargoProjection> findAllDescriptionOnly();


}
