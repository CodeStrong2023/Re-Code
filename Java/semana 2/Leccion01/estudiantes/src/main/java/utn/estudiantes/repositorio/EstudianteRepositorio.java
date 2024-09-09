package utn.estudiantes.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import utn.estudiantes.modelo.estudiante;


public interface EstudianteRepositorio extends JpaRepository<estudiante, Integer> {
}
