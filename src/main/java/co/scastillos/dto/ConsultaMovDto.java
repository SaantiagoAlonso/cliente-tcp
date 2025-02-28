package co.scastillos.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsultaMovDto {

    private Integer nCuenta;
    private List<MovimientoDto> movimientos;
    private String mensage;
}
