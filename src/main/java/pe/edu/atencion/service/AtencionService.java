package pe.edu.atencion.service;

import pe.edu.atencion.dto.AtencionDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AtencionService {

    List<AtencionDTO> listarTodas();

    List<AtencionDTO> historialPaciente(String nombre, String apellido);

    List<AtencionDTO> listarPorRango(LocalDate inicio, LocalDate fin);

    AtencionDTO crear(AtencionDTO dto);

    Optional<AtencionDTO> actualizar(Integer id, AtencionDTO dto);

    boolean eliminar(Integer id);
}
