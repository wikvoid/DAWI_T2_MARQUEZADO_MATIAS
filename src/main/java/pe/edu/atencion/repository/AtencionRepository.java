package pe.edu.atencion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.atencion.entity.Atencion;

import java.time.LocalDate;
import java.util.List;

public interface AtencionRepository extends JpaRepository<Atencion, Integer> {

    List<Atencion> findByNombrePacienteAndApellidoPacienteOrderByFechaAtencionDesc(
            String nombrePaciente,
            String apellidoPaciente
    );

    List<Atencion> findByFechaAtencionBetweenOrderByFechaAtencionDesc(
            LocalDate fechaInicio,
            LocalDate fechaFin
    );
}
