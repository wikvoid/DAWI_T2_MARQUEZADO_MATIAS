package pe.edu.atencion.controller;

import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import pe.edu.atencion.dto.AtencionDTO;
import pe.edu.atencion.service.AtencionService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/atenciones")
public class AtencionController {

    private final AtencionService atencionService;

    public AtencionController(AtencionService atencionService) {
        this.atencionService = atencionService;
    }

    @GetMapping
    public ResponseEntity<List<AtencionDTO>> listarTodas() {
        List<AtencionDTO> lista = atencionService.listarTodas();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/historial")
    public ResponseEntity<List<AtencionDTO>> historial(
            @RequestParam String nombrePaciente,
            @RequestParam String apellidoPaciente
    ) {
        if (nombrePaciente == null || nombrePaciente.isBlank() ||
                apellidoPaciente == null || apellidoPaciente.isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        List<AtencionDTO> historial = atencionService.historialPaciente(nombrePaciente, apellidoPaciente);
        if (historial.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(historial);
    }

    @PostMapping
    public ResponseEntity<AtencionDTO> crear(@Valid @RequestBody AtencionDTO dto) {
        AtencionDTO creada = atencionService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @GetMapping("/rango")
    public ResponseEntity<List<AtencionDTO>> listarPorRango(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin
    ) {
        if (fechaInicio == null || fechaFin == null || fechaFin.isBefore(fechaInicio)) {
            return ResponseEntity.badRequest().build();
        }

        List<AtencionDTO> lista = atencionService.listarPorRango(fechaInicio, fechaFin);
        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtencionDTO> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody AtencionDTO dto
    ) {
        return atencionService.actualizar(id, dto)
                .map(actualizada ->
                        ResponseEntity.status(HttpStatus.CREATED).body(actualizada)
                ).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        boolean eliminado = atencionService.eliminar(id);
        if (!eliminado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> manejarValidaciones(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errores.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errores);
    }
}
