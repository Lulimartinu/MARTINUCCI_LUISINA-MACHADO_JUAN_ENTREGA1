package com.digitalhouse.backend.integrador.dao.impl;

import com.digitalhouse.backend.integrador.dao.IDao;
import com.digitalhouse.backend.integrador.entity.Odontologo;
import org.apache.log4j.Logger;

import java.util.List;


public class OdontologoDaoEnMemoria implements IDao<Odontologo>{

    private final Logger LOGGER = Logger.getLogger(OdontologoDaoEnMemoria.class);
    private List<Odontologo> repositorioDeOdontologos;

    public OdontologoDaoEnMemoria(List<Odontologo> repositorioDeOdontologos) {
        this.repositorioDeOdontologos = repositorioDeOdontologos;
    }

    @Override
    public Odontologo registrar(Odontologo odontologo) {
        repositorioDeOdontologos.add(odontologo);
        LOGGER.info("Se ha registrado el odontologo : \n" + odontologo);
        return odontologo;
    }

    @Override
    public List<Odontologo> listarTodos() {
        LOGGER.info("Listado de todos los odontologos : \n" + repositorioDeOdontologos);
        return repositorioDeOdontologos;
    }
}
