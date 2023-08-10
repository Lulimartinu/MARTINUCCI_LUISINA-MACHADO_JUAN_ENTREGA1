package com.digitalhouse.backend.integrador.dao.impl;

import com.digitalhouse.backend.integrador.dao.H2Connection;
import com.digitalhouse.backend.integrador.dao.IDao;
import com.digitalhouse.backend.integrador.entity.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao <Odontologo> {
    private final Logger LOGGER = Logger.getLogger(OdontologoDaoH2.class);

    @Override
    public Odontologo registrar(Odontologo odontologo) {
        Connection connection = null;
        Odontologo odontologo1 = null;
        String insert = "INSERT INTO ODONTOLOGOS (NUMERO_MATRICULA, NOMBRE, APELLIDO) VALUES (?, ?, ?)";

        try {

            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            //registro
            PreparedStatement ps = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, odontologo.getNumeroMatricula());
            ps.setString(2, odontologo.getNombre());
            ps.setString(3, odontologo.getApellido());
            ps.execute();

            odontologo1 = new Odontologo(odontologo.getNumeroMatricula(), odontologo.getNombre(), odontologo.getApellido());

            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                odontologo.setId(rs.getInt(1));
            }

            connection.commit();
            if (odontologo1 == null) {
                LOGGER.error("No fue posible registrar el odontologo !");
            } else {
                LOGGER.info("Se ha registrado el odontologo :" + odontologo1);
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    System.out.println("Tuvimos un problema de conexion odontologica :)");
                    e.printStackTrace();
                } catch (SQLException exception) {
                    LOGGER.error(exception.getMessage());
                    exception.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                LOGGER.error("BUMMM... error al cerrar la ODONTO-BaseDeDatos " + e.getMessage());
                e.printStackTrace();
            }
        }

        return odontologo1;
    }


    @Override
    public List<Odontologo> listarTodos() {
        Connection connection = null;
        List<Odontologo> odontologos = new ArrayList<>();

        try {
            connection = H2Connection.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM ODONTOLOGOS");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Odontologo odontologo = new Odontologo(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4));

                odontologos.add(odontologo);
            }
            LOGGER.info("Listado de todos los odontologos : " + odontologos );


        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();

        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("ERORRRR, NO SE PUDO CERRAR LA BBDD" + ex.getMessage());
                ex.printStackTrace();
            }
        }

        return odontologos;
    }
}

