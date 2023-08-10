package com.digitalhouse.backend.integrador.Test;

import com.digitalhouse.backend.integrador.dao.impl.OdontologoDaoH2;
import com.digitalhouse.backend.integrador.entity.Odontologo;
import com.digitalhouse.backend.integrador.service.OdontologoService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceTest {

    OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());

    @Test
    public void deberiaDarUnaListaConTresValores(){

        List<Odontologo> odontologoTest = odontologoService.listarOdontologos();
        assertFalse(odontologoTest.isEmpty());
        assertTrue(odontologoTest.size() >= 1);
    }
}