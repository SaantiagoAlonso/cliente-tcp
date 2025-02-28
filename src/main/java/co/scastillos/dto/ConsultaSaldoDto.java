package co.scastillos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ConsultaSaldoDto {
    private String tipoBusqueda; // "cedula" o "cuenta"
    private Integer valor;
}
