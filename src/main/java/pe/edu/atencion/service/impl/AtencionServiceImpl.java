package pe.edu.atencion.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import pe.edu.atencion.dto.AtencionDTO;
import pe.edu.atencion.entity.Atencion;
import pe.edu.atencion.repository.AtencionRepository;
import pe.edu.atencion.service.AtencionService;
import pe.edu.atencion.service.mapper.AtencionMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AtencionServiceImpl implements AtencionService {

    private final AtencionRepository atencionRepository;

    public AtencionServiceImpl(AtencionRepository atencionRepository) {
        this.atencionRepository = atencionRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AtencionDTO> listarTodas() {
        return atencionRepository.findAll()
                .stream()
                .map(AtencionMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AtencionDTO> historialPaciente(String nombre, String apellido) {
        return atencionRepository
                .findByNombrePacienteAndApellidoPacienteOrderByFechaAtencionDesc(nombre, apellido)
                .stream()
                .map(AtencionMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AtencionDTO> listarPorRango(LocalDate inicio, LocalDate fin) {
        return atencionRepository
                .findByFechaAtencionBetweenOrderByFechaAtencionDesc(inicio, fin)
                .stream()
                .map(AtencionMapper::toDto)
                .toList();
    }

    @Override
    public AtencionDTO crear(AtencionDTO dto) {
        Atencion entity = AtencionMapper.toEntity(dto);
        entity.setId(null); // aseguramos crear
        Atencion saved = atencionRepository.save(entity);
        return AtencionMapper.toDto(saved);
    }

    @Override
    public Optional<AtencionDTO> actualizar(Integer id, AtencionDTO dto) {
        return atencionRepository.findById(id)
                .map(existing -> {
                    existing.setNombrePaciente(dto.getNombrePaciente());
                    existing.setApellidoPaciente(dto.getApellidoPaciente());
                    existing.setNombreMedico(dto.getNombreMedico());
                    existing.setApellidoMedico(dto.getApellidoMedico());
                    existing.setFechaAtencion(dto.getFechaAtencion());
                    existing.setReferencia(dto.getReferencia());
                    Atencion updated = atencionRepository.save(existing);
                    return AtencionMapper.toDto(updated);
                });
    }

    @Override
    public boolean eliminar(Integer id) {
        if (!atencionRepository.existsById(id)) {
            return false;
        }
        atencionRepository.deleteById(id);
        return true;
    }
}
