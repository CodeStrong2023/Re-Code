package utn.estudiantes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import utn.estudiantes.servicio.EstudianteServicio;
import utn.estudiantes.modelo.Estudiantes2022;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class EstudiantesApplication implements CommandLineRunner {
	@Autowired
	private EstudianteServicio estudianteServicio;
	private static final Logger logger =
			LoggerFactory.getLogger(EstudiantesApplication.class);

	String nl = System.lineSeparator();

	public static void main(String[] args) {
		logger.info("Inicializando el programa...");
		//Levantar la fabrica de Spring
		SpringApplication.run(EstudiantesApplication.class, args);
		logger.info("Terminando el programa...");
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info(nl+"Ejecutando el metodo run de Spring..."+nl);
		var salir = false;
		var consola = new Scanner(System.in);
		while(!salir){
			mostrarMenu();
			salir = ejecutarOpciones(consola);
			logger.info(nl);
		}
	}

	private void mostrarMenu(){
		logger.info("""
				**** Sistema de Estudiantes ****
				1. Listar Estudiantes
				2. Busacar Estudiantes
				3. Agregar Estudiante
				4. Modificar Estudiante
				5. Eliminar Estudiante
				6. Salir
				Elija una opcion:
				""");
	}

	private boolean ejecutarOpciones(Scanner consola){
		var opcion= Integer.parseInt(consola.nextLine());
		var salir = false;
		switch(opcion){
			case 1 -> {
				logger.info(nl+"Listando estudiantes..."+nl);
				List<Estudiantes2022> estudiantes = estudianteServicio.listarEstudiantes();
				estudiantes.forEach((estudiante -> logger.info(estudiante.toString()+nl)));
			}
			case 2 -> {
				logger.info("Digite el id estudiante a buscar: ");
				var idEstudiante = Integer.parseInt(consola.nextLine());
				Estudiantes2022 estudiante =
						estudianteServicio.buscarEstudiante(idEstudiante);
				if(estudiante != null){
					logger.info("Estudiante encontrado: "+estudiante.toString()+nl);
				} else logger.info("Estudiante no encontrado: "+ estudiante + nl);
			}
			case 3 -> {
				logger.info("Agregar estudiante: "+nl);
				logger.info("Nombre: ");
				var nombre = consola.nextLine();
				logger.info("Apellido: ");
				var apellido = consola.nextLine();
				logger.info("Correo: ");
				var correo = consola.nextLine();
				logger.info("Telefono: ");
				var telefono = consola.nextLine();
				var estudiante = new Estudiantes2022();
				estudiante.setNombre(nombre);
				estudiante.setApellido(apellido);
				estudiante.setEmail(correo);
				estudiante.setTelefono(telefono);
				estudianteServicio.guardarEstudiante(estudiante);
				logger.info("Estudiante agregado: "+estudiante.toString()+nl);
			}
			case 4 -> {
				logger.info("Modificar estudiante: "+nl);
				logger.info("Ingrese el id estudiante: ");
				var idEstudiante = Integer.parseInt(consola.nextLine());
				Estudiantes2022 estudiante =
						estudianteServicio.buscarEstudiante(idEstudiante);
				if(estudiante != null){
					logger.info("Nombre: ");
					var nombre = consola.nextLine();
					logger.info("Apellido: ");
					var apellido = consola.nextLine();
					logger.info("Correo: ");
					var correo = consola.nextLine();
					logger.info("Telefono: ");
					var telefono = consola.nextLine();
					estudiante.setNombre(nombre);
					estudiante.setApellido(apellido);
					estudiante.setEmail(correo);
					estudiante.setTelefono(telefono);
					estudianteServicio.guardarEstudiante(estudiante);
					logger.info("Estudiante modificado: "+estudiante.toString()+nl);
				}
				else logger.info("Estudiante no encontrado: "+ estudiante + nl);
			}
			case 5 -> {
				logger.info("Eliminar estudiante "+nl);
				logger.info("Ingrese el id estudiante: ");
				var idEstudiante = Integer.parseInt(consola.nextLine());
				var estudiante = estudianteServicio.buscarEstudiante(idEstudiante);
				if(estudiante != null){
					estudianteServicio.eliminarEstudiante(estudiante);
					logger.info("Estudiante eliminado: "+estudiante.toString()+nl);
				}
				else logger.info("Estudiante no encontrado: "+estudiante + nl);
			}
			case 6 -> {
				logger.info("Hasta pronto!!"+nl+nl);
				salir = true;
			}
			default -> logger.info("opcion no reconocida"+ opcion+nl);
		}return salir;
	}


}
