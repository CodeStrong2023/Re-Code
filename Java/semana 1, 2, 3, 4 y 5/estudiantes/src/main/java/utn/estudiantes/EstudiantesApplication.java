package utn.estudiantes;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import utn.estudiantes.modelo.Estudiante;
import utn.estudiantes.servicio.EstudianteServicio;


@SpringBootApplication
public class EstudiantesApplication implements CommandLineRunner {
    @Autowired
    private EstudianteServicio estudianteServicio;
    private static final Logger logger = LoggerFactory.getLogger(EstudiantesApplication.class);

    String nl = System.lineSeparator();

    public static void main(String[] args) {
        logger.info("iniciando la aplicacion ...");
        SpringApplication.run(EstudiantesApplication.class, args);
        logger.info("Aplicacion Finalizada!");
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info(nl + "Ejecutando el metodo run de Spring..." + nl);
        var salir = false;
        var consola = new Scanner(System.in);
        while (!salir) {
            mostrarMenu();
            salir = ejecutarOpciones(consola);
            logger.info(nl);

        }
    }

    private void mostrarMenu() {
        logger.info(nl);
        logger.info("""
                ★★★★ SISTEMAS DE ESTUDIANTES ★★★★
                1- Listar estudiantes
                2- Buscar estudiante
                3- Agregar estudiante
                4- Modificar estudiante
                5- Eliminar estudiante
                6- Salir
                   Elija una opcion:""");
    }

    private boolean ejecutarOpciones(Scanner consola) {
        var opcion = Integer.parseInt(consola.nextLine());
        var salir = false;
        switch (opcion) {
            case 1 -> {
                logger.info(nl + "Listado de estudiantes:" + nl);
                List<Estudiante> estudiantes = estudianteServicio.listarEstudiantes();
                estudiantes.forEach((estudiante -> logger.info(estudiante.toString() + nl)));
            }
            case 2 -> {
                logger.info("Digite el id del estudiante a buscar:");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                Estudiante estudiante = estudianteServicio.buscarEstudianteporId(idEstudiante);
                if (estudiante != null) logger.info("Estudiante encontrado" + estudiante + nl);
                else logger.info("Estudiante No encontrado" + estudiante + nl);
            }
            case 3 -> {
                logger.info("Agregar estudiante:" + nl);
                logger.info("Nombre:");
                var Nombre = consola.nextLine();
                logger.info("Apellido:");
                var Apellido = consola.nextLine();
                logger.info("Telefono:");
                var Telefono = consola.nextLine();
                logger.info("Email:");
                var Email = consola.nextLine();
                var estudiante = new Estudiante();
                estudiante.setNombre(Nombre);
                estudiante.setApellido(Apellido);
                estudiante.setTelefono(Telefono);
                estudiante.setEmail(Email);
                estudianteServicio.guardarEstudiante(estudiante);
                logger.info("Estudiante Agregado:" + nl);
            }
            case 4 -> {
                logger.info("Modificar Estudiante:" + nl);
                logger.info("Ingrese el id del estudiante:");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                Estudiante estudiante = estudianteServicio.buscarEstudianteporId(idEstudiante);
                if (estudiante != null) {
                    logger.info("Nombre:");
                    var Nombre = consola.nextLine();
                    logger.info("Apellido:");
                    var Apellido = consola.nextLine();
                    logger.info("Telefono:");
                    var Telefono = consola.nextLine();
                    logger.info("Email:");
                    var Email = consola.nextLine();
                    estudiante.setNombre(Nombre);
                    estudiante.setApellido(Apellido);
                    estudiante.setTelefono(Telefono);
                    estudiante.setEmail(Email);
                    estudianteServicio.guardarEstudiante(estudiante);
                    logger.info("Estudiante Modificado:" + estudiante + nl);
                } else logger.info("Estudiante NO encontrado con id");
            }
            case 5 -> {
                logger.info("Eliminar estudiante:" + nl);
                logger.info("Ingresar id del estudiante");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                var estudiante = estudianteServicio.buscarEstudianteporId(idEstudiante);
                if (estudiante != null) {
                    estudianteServicio.eliminarEstudiante(estudiante);
                    logger.info("Estudiante eliminado:" + estudiante + nl);
                } else logger.info("Estudiante No encontrado con id:" + estudiante + nl);
            }
            case 6 -> {
                logger.info("Hasta pronto" + nl + nl);
                salir = true;
            }
            default -> logger.info("Opcion no reconocida:" + opcion + nl);
        }
        return salir;
    }
}
