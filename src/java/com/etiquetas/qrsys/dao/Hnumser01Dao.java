package com.etiquetas.qrsys.dao;

import com.etiquetas.qrsys.s.model.Hnumser01;
import java.util.List;

public interface Hnumser01Dao {

    public List<Hnumser01> obtenerMaximoValor();

    public void saveHmunser(Hnumser01 hnumser);
}
