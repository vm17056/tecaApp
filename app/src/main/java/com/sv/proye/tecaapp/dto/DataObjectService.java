package com.sv.proye.tecaapp.dto;

import java.util.List;

public interface DataObjectService<Modelo> {

    /**
     * @param modelo is List<KeysMatch>
     * @return number row ID or -1 if error
     */
    Long almacenarModelo(Modelo modelo);

    /**
     * @return number of rows affected
     */
    int actualizarModelo(Modelo modelo);

    /**
     * @return number of rows affected
     */
    int eliminarModelo(Integer id);

    List<Modelo> listarModelos();

    Modelo buscarModeloPodId(Integer id);

}
