package pe.edu.atencion.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class AtencionDTO {

    private Integer id;

    @NotBlank(message = "nombrePaciente es obligatorio")
    @Size(max = 80)
    private String nombrePaciente;

    @NotBlank(message = "apellidoPaciente es obligatorio")
    @Size(max = 80)
    private String apellidoPaciente;

    @NotBlank(message = "nombreMedico es obligatorio")
    @Size(max = 80)
    private String nombreMedico;

    @NotBlank(message = "apellidoMedico es obligatorio")
    @Size(max = 80)
    private String apellidoMedico;

    @NotNull(message = "fechaAtencion es obligatoria")
    private LocalDate fechaAtencion;

    @Size(max = 200)
    private String referencia;
}
