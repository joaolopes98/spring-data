package br.com.lopes.joao.spring.data.service;

import br.com.lopes.joao.spring.data.orm.Cargo;
import br.com.lopes.joao.spring.data.orm.CargoProjection;
import br.com.lopes.joao.spring.data.orm.CargoSpecification;
import br.com.lopes.joao.spring.data.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class CrudCargoService {

    private final CargoRepository cargoRepository;

    public CrudCargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public void menu(Scanner scanner){
        boolean system = true;

        while (system){
            System.out.println("\nAções de CARGOS");
            System.out.println("0 - Sair");
            System.out.println("1 - Criar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Visualizar");
            System.out.println("4 - Deletar");
            System.out.println("5 - Pesquisar");
            System.out.println("Digite uma ação: ");

            int action = scanner.nextInt();
            switch (action){
                case 0:
                    system = false;
                    break;

                case 1:
                    save(scanner);
                    break;

                case 2:
                    update(scanner);
                    break;

                case 3:
                    show();
                    break;

                case 4:
                    deletar(scanner);
                    break;

                case 5:
                    search(scanner);
                    break;

                default:
            }
        }
    }

    private void save(Scanner scanner){
        System.out.println("Descrição do cargo : ");
        String descricao = scanner.next();
        Cargo cargo = new Cargo(descricao);
        cargoRepository.save(cargo);
        System.out.println("\n Cargo Salvo");
    }

    private void update(Scanner scanner){
        System.out.println("Digite o ID: ");
        int id = scanner.nextInt();

        System.out.println("Nova Descrição: ");
        String description = scanner.next();

        Cargo cargo = new Cargo(id, description);
        cargoRepository.save(cargo);
    }

    private void show(){
        Pageable pageable = PageRequest.of(1, 1, Sort.by(Sort.Direction.ASC,"description"));
        Page<Cargo> cargos = cargoRepository.findAll(pageable);
        System.out.println(cargos);
        System.out.println("Pagina Atual: " + cargos.getNumber());
        System.out.println("Elementos por pagina: " + cargos.getTotalElements());
//        Iterable<Cargo> cargos = cargoRepository.findAll();
        cargos.forEach(System.out::println);
    }

    private void showDescriptionOnly(){
        Iterable<CargoProjection> cargos = cargoRepository.findAllDescriptionOnly();
        cargos.forEach(c -> System.out.println(c.getDescription()));
    }

    private void deletar(Scanner scanner){
        System.out.println("Digite o Id: ");
        int id = scanner.nextInt();

        cargoRepository.deleteById(id);
        System.out.println("Cargo Deletado");
    }

    private void search(Scanner scanner){
        System.out.println("Digite a descrição: ");
        String description = scanner.next();
        List<Cargo> cargoList = cargoRepository.findByDescriptionContains(description);
        System.out.println(cargoList);
    }

    private void searchDynamic(Scanner scanner){
        System.out.println("Digite a descrição: ");
        String description = scanner.next();

        System.out.println("Digite o ID: ");
        Integer id = scanner.nextInt();
        List<Cargo> cargoList = cargoRepository.findAll(Specification
                .where(CargoSpecification.description(description))
                .or(CargoSpecification.id(id))
        );
        System.out.println(cargoList);
    }
}
