package pe.edu.atencion.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "atencion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Atencion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 80)
    private String nombrePaciente;

    @Column(nullable = false, length = 80)
    private String apellidoPaciente;

    @Column(nullable = false, length = 80)
    private String nombreMedico;

    @Column(nullable = false, length = 80)
    private String apellidoMedico;

    @Column(nullable = false)
    private LocalDate fechaAtencion;

    @Column(length = 200)
    private String referencia;
}
