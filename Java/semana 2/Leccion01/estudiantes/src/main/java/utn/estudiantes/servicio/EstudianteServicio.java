package utn.estudiantes.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.estudiantes.modelo.estudiante;
import utn.estudiantes.repositorio.EstudianteRepositorio;

import java.util.List;

@Service

public class EstudianteServicio implements IEstudianteServicio {
    @Autowired
    private EstudianteRepositorio estudianteRepositorio;

    @Override
    public List<estudiante> listarEstudiantes() {
        List<estudiante> estudiantes = estudianteRepositorio.findAll();
        return estudiantes;
    }

    @Override
    public estudiante buscarEstudiante(Integer idEstudiante) {
        estudiante estudiante = estudianteRepositorio.findById(idEstudiante).orElse(null);
        return estudiante;
    }

    @Override
    public void guardarEstudiante(estudiante estudiante) {
        estudianteRepositorio.save(estudiante);
    }

    @Override
    public void eliminarEstudiante(estudiante estudiante) {
        estudianteRepositorio.delete(estudiante);
    }
}
