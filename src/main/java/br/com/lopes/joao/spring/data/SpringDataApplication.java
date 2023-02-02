package br.com.lopes.joao.spring.data;

import br.com.lopes.joao.spring.data.orm.Cargo;
import br.com.lopes.joao.spring.data.service.CrudCargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private final CrudCargoService cargoService;

	public SpringDataApplication(CrudCargoService cargoService) {
		this.cargoService = cargoService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) {
		boolean system = true;
		Scanner scanner = new Scanner(System.in);

		while (system){
			System.out.println("\n0 - Sair");
			System.out.println("1 - Cargos");
			System.out.println("Digite uma ação: ");

			int action = scanner.nextInt();
			switch (action){
				case 0:
					system = false;
					break;

				case 1:
					cargoService.menu(scanner);
					break;

				default:
			}
		}


	}
}
