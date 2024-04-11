package com.softtek.persistencia;

import com.softtek.modelo.Empleado;
import com.softtek.persistencia.Conexion;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AccesoADatosPreparedStatement extends Conexion {

    //CRUD empleados

    //INSERTAR


    public void crearEmpleado(Empleado empleado) throws SQLException, ClassNotFoundException {
        PreparedStatement sentencia;
        abrirConexion();
        String sql = "INSERT INTO employees (employee_id, last_name, first_name, reports_to) VALUES(?,?,?,?);";
        sentencia = miConexion.prepareStatement(sql);

        sentencia.setInt(1,empleado.getIdEmpleado());
        sentencia.setString(2,empleado.getApellidos());
        sentencia.setString(3, empleado.getNombre());
        sentencia.setInt(4,empleado.getJefe());
        sentencia.executeUpdate();
        miConexion.close();



    }

    public void eliminarEmpleado(int id) throws SQLException, ClassNotFoundException {
        PreparedStatement sentencia;
        abrirConexion();
        String sql = "DELETE FROM employees WHERE employee_id = ?;";
        sentencia = miConexion.prepareStatement(sql);
        sentencia.setInt(1,id);
        sentencia.executeUpdate();
        miConexion.close();

    }

    public void updateEmpleado(int idEmpleado, Empleado empleado) throws SQLException, ClassNotFoundException {
        PreparedStatement sentencia;
        abrirConexion();

        String sql = "UPDATE employees SET last_name = ?, first_name = ?, reports_to = ? WHERE employee_id = ?;";
        sentencia = miConexion.prepareStatement(sql);
        sentencia.setString(1,empleado.getApellidos());
        sentencia.setString(2, empleado.getNombre());
        sentencia.setInt(3, empleado.getJefe());
        sentencia.setInt(4, idEmpleado);
        sentencia.executeUpdate();
        miConexion.close();

    }

    public Empleado mostrarPorId(int id) throws SQLException, ClassNotFoundException {
        PreparedStatement sentencia;
        Empleado empleado = null;
        ResultSet resultado;
        abrirConexion();
        String sql = "SELECT employee_id, last_name, first_name, reports_to FROM employees WHERE employee_id = ?;";
        sentencia = miConexion.prepareStatement(sql);
        sentencia.setInt(1,id);
        resultado = sentencia.executeQuery();

        if(resultado.next()){

            empleado = new Empleado(resultado.getInt("employee_id"),
                    resultado.getString("first_name"),
                    resultado.getString("last_name"),
                    resultado.getInt("reports_to"));
            
            
        }
        
        return empleado;


    }

    public ArrayList<Empleado> mostrarTodos() throws SQLException, ClassNotFoundException {
        PreparedStatement sentencia;
        ResultSet resultado;
        ArrayList<Empleado> listaEmpleados = new ArrayList<>();
        String sql = "SELECT * FROM employees";
        abrirConexion();
        sentencia = miConexion.prepareStatement(sql);
        resultado = sentencia.executeQuery();

        while(resultado.next()){
            listaEmpleados.add(new Empleado(resultado.getInt("employee_id"),
                    resultado.getString("first_name"),
                    resultado.getString("last_name"),
                    resultado.getInt("reports_to")
            ));
        }

        miConexion.close();
        return listaEmpleados;
    }

}
