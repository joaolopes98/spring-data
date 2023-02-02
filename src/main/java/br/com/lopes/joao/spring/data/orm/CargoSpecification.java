package br.com.lopes.joao.spring.data.orm;

import org.springframework.data.jpa.domain.Specification;

public class CargoSpecification {

    public static Specification<Cargo> id(Integer id){
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("id"), id);
    }

    public static Specification<Cargo> description(String description){
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("description"),"%" + description + "%");
    }
}
