/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package consultas;

import java.sql.CallableStatement;
import conexion.Conexion;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author javie
 */
public class equipos {

    conexion.Conexion conectar = new Conexion();

    public void mostrarEstados(JComboBox comboEstado) {
        String sql = "";
        sql = "SELECT * FROM estado";
        Statement st;
        try {
            st = conectar.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            comboEstado.removeAllItems();

            while (rs.next()) {
                comboEstado.addItem(rs.getString("estMant"));

            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar: " + e);
        }
    }

    public void mostrarIdcombo(JComboBox comboEstado, JTextField txtIdEstado) {
        Object selectedValue = comboEstado.getSelectedItem();

        if (selectedValue != null) {
            String consulta = "SELECT estado.id from estado where estado.estMant =?";
            try {
                CallableStatement cs = conectar.getConnection().prepareCall(consulta);
                cs.setString(1, selectedValue.toString());
                cs.execute();

                ResultSet rs = cs.executeQuery();
                if (rs.next()) {
                    txtIdEstado.setText(rs.getString("id"));
                }
            } catch (SQLException e) {
                System.out.println("Error al mostrar: " + e);
            }
        } else {
        }
    }

}
