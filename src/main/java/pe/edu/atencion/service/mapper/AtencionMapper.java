package pe.edu.atencion.service.mapper;

import pe.edu.atencion.dto.AtencionDTO;
import pe.edu.atencion.entity.Atencion;

public class AtencionMapper {

    public static AtencionDTO toDto(Atencion entity) {
        AtencionDTO dto = new AtencionDTO();
        dto.setId(entity.getId());
        dto.setNombrePaciente(entity.getNombrePaciente());
        dto.setApellidoPaciente(entity.getApellidoPaciente());
        dto.setNombreMedico(entity.getNombreMedico());
        dto.setApellidoMedico(entity.getApellidoMedico());
        dto.setFechaAtencion(entity.getFechaAtencion());
        dto.setReferencia(entity.getReferencia());
        return dto;
    }

    public static Atencion toEntity(AtencionDTO dto) {
        Atencion entity = new Atencion();
        entity.setId(dto.getId());
        entity.setNombrePaciente(dto.getNombrePaciente());
        entity.setApellidoPaciente(dto.getApellidoPaciente());
        entity.setNombreMedico(dto.getNombreMedico());
        entity.setApellidoMedico(dto.getApellidoMedico());
        entity.setFechaAtencion(dto.getFechaAtencion());
        entity.setReferencia(dto.getReferencia());
        return entity;
    }
}
