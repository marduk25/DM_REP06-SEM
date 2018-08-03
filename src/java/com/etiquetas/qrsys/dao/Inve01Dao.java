package com.etiquetas.qrsys.dao;

import com.etiquetas.qrsys.s.model.Inve01;
import java.util.List;

public interface Inve01Dao {

    public List<String> listaInve01(String articulo);

    public Inve01 obtenerArticuloExistente(Inve01 inve01);

    public Inve01 returnArticuloExistente(Inve01 inve01);

}
