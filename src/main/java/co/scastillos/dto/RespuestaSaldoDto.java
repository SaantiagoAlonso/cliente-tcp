package co.scastillos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RespuestaSaldoDto {
    private Double saldo;
    private String mensaje;
}
