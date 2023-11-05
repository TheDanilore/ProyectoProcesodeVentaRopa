/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reporte;

import Class.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author windows2020
 */
public class Grafico {
    public static void Graficar(String fecha) {
        Connection con;
        Conexion cn = new Conexion();
        PreparedStatement ps;
        ResultSet rs;
        try {
            String sql="SELECT total FROM ventas WHERE fecha=?";
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, fecha);
            rs=ps.executeQuery();
            DefaultPieDataset dataset = new DefaultPieDataset();
            
            while(rs.next()){
                dataset.setValue(rs.getString("total"), rs.getDouble("total"));
            }
            
            JFreeChart jf = ChartFactory.createPieChart("Reporte de Venta", dataset);
            ChartFrame f = new ChartFrame("Total de Ventas por día", jf);
            f.setSize(1000, 500);
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
}
