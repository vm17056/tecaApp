package com.sv.proye.tecaapp.dto;

import java.util.List;

public interface DataObjectService<Modelo> {
    void almacenarModelo(Modelo modelo);

    int actualizarModelo(Modelo modelo);

    void eliminarModelo(Integer id);

    List<Modelo> listarModelos();

    Modelo buscarModeloPodId(Integer id);

}
