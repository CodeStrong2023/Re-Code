package utn.estudiantes.servicio;

import utn.estudiantes.modelo.estudiante;

import java.util.List;

public interface IEstudianteServicio {
    public List<estudiante> listarEstudiantes();
    public estudiante buscarEstudiante(Integer idEstudiante);
    public void guardarEstudiante(estudiante estudiante);
    public void eliminarEstudiante(estudiante estudiante);
}
