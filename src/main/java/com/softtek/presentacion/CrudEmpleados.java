package com.softtek.presentacion;

import com.softtek.persistencia.AccesoADatosPreparedStatement;
import com.softtek.modelo.Empleado;

import java.sql.SQLException;

public class CrudEmpleados {

    public static void main(String[] args) {
        AccesoADatosPreparedStatement a1 = new AccesoADatosPreparedStatement();

        try {

            imprimirEmpleados(a1);
            System.out.println("\nAñadiendo nuevo empleado con id 10");
            a1.crearEmpleado(new Empleado(10, "Pepe", "Montes", 2));
            System.out.println("\nMostrando empleado con id 10");
            System.out.println(a1.mostrarPorId(10));
            System.out.println("\nModificando empleado con id 10");
            a1.updateEmpleado(10,new Empleado(10,"Ana","Garcia",5));
            System.out.println("\nImpresión del empleado con id 10 tras modificar\n" + a1.mostrarPorId(10));
            System.out.println("\nEliminando empleado con id 10 y mostrando el resto de empleados");
            a1.eliminarEmpleado(10);
            imprimirEmpleados(a1);




        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void imprimirEmpleados(AccesoADatosPreparedStatement a1) throws SQLException, ClassNotFoundException {
        for (Empleado empleados: a1.mostrarTodos()
             ) {
            System.out.println(empleados);
        }
    }
}
