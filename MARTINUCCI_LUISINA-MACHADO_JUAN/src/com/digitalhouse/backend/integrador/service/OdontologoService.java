package com.digitalhouse.backend.integrador.service;

import com.digitalhouse.backend.integrador.dao.IDao;
import com.digitalhouse.backend.integrador.entity.Odontologo;

import java.util.List;

public class OdontologoService {

    private  IDao<Odontologo> odontologoIDao;

    public OdontologoService(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }

    public Odontologo registrarOdontologo(Odontologo odontologo){
        return odontologoIDao.registrar(odontologo);

    }

    public List<Odontologo> listarOdontologos(){
        return odontologoIDao.listarTodos();
    }

}
