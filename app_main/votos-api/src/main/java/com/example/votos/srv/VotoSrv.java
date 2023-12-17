package com.example.votos.srv;

import com.example.votos.dao.VotoDao;
import com.example.votos.dto.Voto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service  // Anotación que indica que esta clase es un componente de servicio.
@RequiredArgsConstructor  // Genera automáticamente el constructor con los campos finales inyectados.
public class VotoSrv {
    final VotoDao votoDao;

    public Voto saveVoto(Map<String, Object> params) {
        Voto voto = votoDao.findFirstById(params.get("id").toString());
        // Actualiza los conteos de votos según el tipo de voto recibido.
        if (Boolean.parseBoolean(params.get("votoValido").toString())) {
            voto.setValidos(voto.getValidos() + 1);
        } else if (Boolean.parseBoolean(params.get("votoNulo").toString())) {
            voto.setNulos(voto.getNulos() + 1);
        } else if (Boolean.parseBoolean(params.get("votoBlanco").toString())) {
            voto.setBlancos(voto.getBlancos() + 1);
        }
        // Guarda el voto actualizado en la base de datos.
        return voto;
    }
}
