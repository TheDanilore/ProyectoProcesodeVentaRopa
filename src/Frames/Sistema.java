/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import Class.Cliente;
import Class.Cliente1;
import Class.Configuracion;
import Class.Detalle;
import Class.Evento;
import Class.Producto;
import Class.Producto1;
import Class.Proveedor;
import Class.Proveedor1;
import Class.Venta;
import Class.Venta1;
import Reporte.Excel;
import Reporte.Grafico;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author windows2020
 */
public class Sistema extends javax.swing.JFrame {

    /**
     * Creates new form Sistema
     */
    Date fechaVenta = new Date();
    String fechaActual = new SimpleDateFormat("dd/MM/yyyy").format(fechaVenta);
    Cliente cliente = new Cliente();
    Cliente1 cliente1 = new Cliente1();
    Proveedor proveedor = new Proveedor();
    Proveedor1 proveedor1 = new Proveedor1();
    Producto producto = new Producto();
    Producto1 producto1 = new Producto1();
    Venta venta = new Venta();
    Venta1 venta1 = new Venta1();
    Detalle detalle_venta = new Detalle();
    Configuracion configuracion=new Configuracion();
    Evento event = new Evento();
    DefaultTableModel clase = new DefaultTableModel();
    DefaultTableModel tmp = new DefaultTableModel();
    int item;
    double totalpagar = 0.00;

    public Sistema() {
        initComponents();
        this.setLocationRelativeTo(null);
        txt_IDCliente.setVisible(false);
        txt_IDVenta1.setVisible(false);
        txt_IDProveedor.setVisible(false);
        txt_IDProducto.setVisible(false);
        txt_TelefonoClienteVenta.setVisible(false);
        txt_DireccionClienteVenta.setVisible(false);
        txt_RazonClienteVenta.setVisible(false);
        AutoCompleteDecorator.decorate(combo_ProveedorProducto);
        producto1.ConsultarProveedor(combo_ProveedorProducto);
        txt_IDConfiguracion.setVisible(false);
        ListarConfiguracion();
    }

    public void ListarCliente() {
        List<Cliente> listarcliente = cliente1.ListarCliente();
        clase = (DefaultTableModel) table_Cliente.getModel();
        Object[] ob = new Object[6];

        for (int i = 0; i < listarcliente.size(); i++) {
            ob[0] = listarcliente.get(i).getID();
            ob[1] = listarcliente.get(i).getDNI();
            ob[2] = listarcliente.get(i).getNombre();
            ob[3] = listarcliente.get(i).getTelefono();
            ob[4] = listarcliente.get(i).getDireccion();
            ob[5] = listarcliente.get(i).getRazon();
            clase.addRow(ob);
        }
        table_Cliente.setModel(clase);
    }

    public void ListarProveedor() {
        List<Proveedor> listarproveedor = proveedor1.ListarProveedor();
        clase = (DefaultTableModel) table_Proveedor.getModel();
        Object[] ob = new Object[6];

        for (int i = 0; i < listarproveedor.size(); i++) {
            ob[0] = listarproveedor.get(i).getID();
            ob[1] = listarproveedor.get(i).getRuc();
            ob[2] = listarproveedor.get(i).getNombre();
            ob[3] = listarproveedor.get(i).getTelefono();
            ob[4] = listarproveedor.get(i).getDireccion();
            ob[5] = listarproveedor.get(i).getRazon();
            clase.addRow(ob);
        }
        table_Proveedor.setModel(clase);
    }

    public void ListarProducto() {
        List<Producto> listarproducto = producto1.ListarProducto();
        clase = (DefaultTableModel) table_Producto.getModel();
        Object[] ob = new Object[6];

        for (int i = 0; i < listarproducto.size(); i++) {
            ob[0] = listarproducto.get(i).getID();
            ob[1] = listarproducto.get(i).getCodigo();
            ob[2] = listarproducto.get(i).getNombre();
            ob[3] = listarproducto.get(i).getProveedor();
            ob[4] = listarproducto.get(i).getStock();
            ob[5] = listarproducto.get(i).getPrecio();
            clase.addRow(ob);
        }
        table_Producto.setModel(clase);
    }
    
    public void ListarConfiguracion(){
        configuracion=producto1.BuscarDatos();
        txt_IDConfiguracion.setText(""+configuracion.getID());
        txt_RucConfiguracion.setText(""+configuracion.getRuc());
        txt_NombreConfiguracion.setText(""+configuracion.getNombre());
        txt_TelefonoConfiguracion.setText(""+configuracion.getTelefono());
        txt_DireccionConfiguracion.setText(""+configuracion.getDireccion());
        txt_RazonSocialConfiguracion.setText(""+configuracion.getRazon());
    }
    
    
    public void ListarVentas() {
        List<Venta> listarventa = venta1.ListarVentas();
        clase = (DefaultTableModel) table_Ventas5.getModel();
        Object[] ob = new Object[4];

        for (int i = 0; i < listarventa.size(); i++) {
            ob[0] = listarventa.get(i).getID();
            ob[1] = listarventa.get(i).getCliente();
            ob[2] = listarventa.get(i).getVendedor();
            ob[3] = listarventa.get(i).getTotal();
            clase.addRow(ob);
        }
        table_Ventas5.setModel(clase);
    }

    public void LimpiarTable() {
        for (int i = 0; i < clase.getRowCount(); i++) {
            clase.removeRow(i);
            i = i - 1;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btn_NuevaVenta = new javax.swing.JButton();
        btn_Cliente = new javax.swing.JButton();
        btn_Proveedor = new javax.swing.JButton();
        btn_Productos = new javax.swing.JButton();
        btn_Ventas = new javax.swing.JButton();
        btn_Configuracion = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lbl_vendedor = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btn_EliminarVenta = new javax.swing.JButton();
        txt_CodigoVenta = new javax.swing.JTextField();
        txt_DescripcionVenta = new javax.swing.JTextField();
        txt_CantidadVenta = new javax.swing.JTextField();
        txt_PrecioVenta = new javax.swing.JTextField();
        txt_StockDisponible = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_Venta1 = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_RucVenta = new javax.swing.JTextField();
        txt_NombreClienteVenta = new javax.swing.JTextField();
        btn_GenerarVenta = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        lbl_Total = new javax.swing.JLabel();
        txt_TelefonoClienteVenta = new javax.swing.JTextField();
        txt_DireccionClienteVenta = new javax.swing.JTextField();
        txt_RazonClienteVenta = new javax.swing.JTextField();
        txt_IDVenta1 = new javax.swing.JTextField();
        Btn_Graficar = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        Midate = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txt_DNICliente = new javax.swing.JTextField();
        txt_NombreCliente = new javax.swing.JTextField();
        txt_TelefonoCliente = new javax.swing.JTextField();
        txt_DireccionCliente = new javax.swing.JTextField();
        txt_RazonSocialCliente = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_Cliente = new javax.swing.JTable();
        btn_GuardarCliente = new javax.swing.JButton();
        btn_EditarCliente = new javax.swing.JButton();
        btn_EliminarCliente = new javax.swing.JButton();
        btn_NuevoCliente = new javax.swing.JButton();
        txt_IDCliente = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txt_RUCProveedor = new javax.swing.JTextField();
        txt_NombreProveedor = new javax.swing.JTextField();
        txt_TelefonoProveedor = new javax.swing.JTextField();
        txt_DireccionProveedor = new javax.swing.JTextField();
        txt_RazonSocialProveedor = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_Proveedor = new javax.swing.JTable();
        btn_GuardarProveedor = new javax.swing.JButton();
        btn_ActualizarProveedor = new javax.swing.JButton();
        btn_EliminarProveedor = new javax.swing.JButton();
        btn_NuevoProveedor = new javax.swing.JButton();
        txt_IDProveedor = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txt_CodigoProducto = new javax.swing.JTextField();
        txt_DescripcionProducto = new javax.swing.JTextField();
        txt_CantidadProducto = new javax.swing.JTextField();
        txt_PrecioProducto = new javax.swing.JTextField();
        combo_ProveedorProducto = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        table_Producto = new javax.swing.JTable();
        btn_GuardarProducto = new javax.swing.JButton();
        btn_ActualizarProducto = new javax.swing.JButton();
        btn_EliminarProducto = new javax.swing.JButton();
        btn_NuevoProducto = new javax.swing.JButton();
        btn_ExcelProducto = new javax.swing.JButton();
        txt_IDProducto = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        table_Ventas5 = new javax.swing.JTable();
        btn_PDFVentas = new javax.swing.JButton();
        txt_IDVenta5 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        txt_RucConfiguracion = new javax.swing.JTextField();
        txt_NombreConfiguracion = new javax.swing.JTextField();
        txt_TelefonoConfiguracion = new javax.swing.JTextField();
        txt_DireccionConfiguracion = new javax.swing.JTextField();
        txt_RazonSocialConfiguracion = new javax.swing.JTextField();
        btn_ActualizarConfiguracion = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        txt_IDConfiguracion = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));

        btn_NuevaVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Nventa.png"))); // NOI18N
        btn_NuevaVenta.setText("Nueva Venta");
        btn_NuevaVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_NuevaVentaActionPerformed(evt);
            }
        });

        btn_Cliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Clientes.png"))); // NOI18N
        btn_Cliente.setText("Cliente");
        btn_Cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ClienteActionPerformed(evt);
            }
        });

        btn_Proveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/proveedor.png"))); // NOI18N
        btn_Proveedor.setText("Proveedor");
        btn_Proveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ProveedorActionPerformed(evt);
            }
        });

        btn_Productos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/producto.png"))); // NOI18N
        btn_Productos.setText("Productos");
        btn_Productos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ProductosActionPerformed(evt);
            }
        });

        btn_Ventas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/compras.png"))); // NOI18N
        btn_Ventas.setText("Ventas");
        btn_Ventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_VentasActionPerformed(evt);
            }
        });

        btn_Configuracion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/config.png"))); // NOI18N
        btn_Configuracion.setText("Configuracion");
        btn_Configuracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ConfiguracionActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/chameleon-reloj.png"))); // NOI18N

        lbl_vendedor.setText("danilore");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_Productos, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(btn_NuevaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Ventas, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Configuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 9, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_vendedor)
                .addGap(52, 52, 52))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_vendedor)
                .addGap(29, 29, 29)
                .addComponent(btn_NuevaVenta)
                .addGap(27, 27, 27)
                .addComponent(btn_Cliente)
                .addGap(18, 18, 18)
                .addComponent(btn_Proveedor)
                .addGap(18, 18, 18)
                .addComponent(btn_Productos)
                .addGap(18, 18, 18)
                .addComponent(btn_Ventas)
                .addGap(18, 18, 18)
                .addComponent(btn_Configuracion)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 580));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/encabezado.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 840, 160));

        jTabbedPane1.setToolTipText("");

        jLabel3.setText("Codigo");

        jLabel4.setText("Descripción del producto");

        jLabel5.setText("Cantidad");

        jLabel6.setText("Precio");

        jLabel7.setText("Stock Disponible");

        btn_EliminarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar.png"))); // NOI18N
        btn_EliminarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EliminarVentaActionPerformed(evt);
            }
        });

        txt_CodigoVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_CodigoVentaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_CodigoVentaKeyTyped(evt);
            }
        });

        txt_DescripcionVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_DescripcionVentaKeyTyped(evt);
            }
        });

        txt_CantidadVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_CantidadVentaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_CantidadVentaKeyTyped(evt);
            }
        });

        txt_PrecioVenta.setEditable(false);
        txt_PrecioVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_PrecioVentaActionPerformed(evt);
            }
        });
        txt_PrecioVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_PrecioVentaKeyTyped(evt);
            }
        });

        txt_StockDisponible.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_StockDisponibleKeyTyped(evt);
            }
        });

        table_Venta1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "DESCRIPCIÓN", "CANTIDAD", "PRECIO UNITARIO", "PRECIO TOTAL"
            }
        ));
        jScrollPane1.setViewportView(table_Venta1);
        if (table_Venta1.getColumnModel().getColumnCount() > 0) {
            table_Venta1.getColumnModel().getColumn(0).setPreferredWidth(30);
            table_Venta1.getColumnModel().getColumn(1).setPreferredWidth(100);
            table_Venta1.getColumnModel().getColumn(2).setPreferredWidth(30);
            table_Venta1.getColumnModel().getColumn(3).setPreferredWidth(30);
            table_Venta1.getColumnModel().getColumn(4).setPreferredWidth(40);
        }

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("DNI/RUC");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("NOMBRE");

        txt_RucVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_RucVentaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_RucVentaKeyTyped(evt);
            }
        });

        txt_NombreClienteVenta.setEditable(false);

        btn_GenerarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/print.png"))); // NOI18N
        btn_GenerarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_GenerarVentaActionPerformed(evt);
            }
        });

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/money.png"))); // NOI18N
        jLabel10.setText("TOTAL A PAGAR");

        lbl_Total.setText("----");

        txt_TelefonoClienteVenta.setEnabled(false);

        txt_DireccionClienteVenta.setEnabled(false);

        Btn_Graficar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Btn_Graficar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/grafico-circular.png"))); // NOI18N
        Btn_Graficar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Btn_GraficarMouseClicked(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("72", 1, 10)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(51, 153, 0));
        jLabel33.setText("SELECCIONE UNA FECHA");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txt_CodigoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txt_DescripcionVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txt_CantidadVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(txt_PrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txt_StockDisponible, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_IDVenta1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(btn_EliminarVenta)))
                        .addGap(35, 35, 35))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(txt_RucVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txt_NombreClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_TelefonoClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_DireccionClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_RazonClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_GenerarVenta)
                        .addGap(48, 48, 48)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Btn_Graficar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Midate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_Total)
                        .addGap(226, 226, 226))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4)
                        .addComponent(jLabel7)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_CodigoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_DescripcionVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_CantidadVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_PrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_StockDisponible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_IDVenta1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_EliminarVenta))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_RucVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_NombreClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_TelefonoClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_DireccionClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_RazonClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(lbl_Total)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(Btn_Graficar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10)
                                    .addComponent(Midate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btn_GenerarVenta))))
                .addGap(46, 46, 46))
        );

        jTabbedPane1.addTab("tab1", jPanel2);

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("DNI/RUC:");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 37, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("NOMBRE:");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 75, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("TELEFONO:");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 118, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("DIRECCIÓN:");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 166, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("RAZON SOCIAL:");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 226, -1, -1));
        jPanel3.add(txt_DNICliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 35, 90, -1));
        jPanel3.add(txt_NombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 73, 128, -1));
        jPanel3.add(txt_TelefonoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 116, 128, -1));
        jPanel3.add(txt_DireccionCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 164, 128, -1));
        jPanel3.add(txt_RazonSocialCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 224, 128, -1));

        table_Cliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "DNI/RUC", "NOMBRE", "TELEFONO", "DIRECCION", "RAZON SOCIAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_Cliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_ClienteMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table_Cliente);
        if (table_Cliente.getColumnModel().getColumnCount() > 0) {
            table_Cliente.getColumnModel().getColumn(0).setPreferredWidth(20);
            table_Cliente.getColumnModel().getColumn(1).setPreferredWidth(50);
            table_Cliente.getColumnModel().getColumn(2).setPreferredWidth(100);
            table_Cliente.getColumnModel().getColumn(3).setPreferredWidth(50);
            table_Cliente.getColumnModel().getColumn(4).setPreferredWidth(80);
            table_Cliente.getColumnModel().getColumn(5).setPreferredWidth(80);
        }

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(304, 35, 521, 322));

        btn_GuardarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/GuardarTodo.png"))); // NOI18N
        btn_GuardarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_GuardarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_GuardarClienteActionPerformed(evt);
            }
        });
        jPanel3.add(btn_GuardarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 262, -1, -1));

        btn_EditarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Actualizar (2).png"))); // NOI18N
        btn_EditarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_EditarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EditarClienteActionPerformed(evt);
            }
        });
        jPanel3.add(btn_EditarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 262, -1, -1));

        btn_EliminarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar.png"))); // NOI18N
        btn_EliminarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_EliminarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EliminarClienteActionPerformed(evt);
            }
        });
        jPanel3.add(btn_EliminarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 313, -1, -1));

        btn_NuevoCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/nuevo.png"))); // NOI18N
        btn_NuevoCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_NuevoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_NuevoClienteActionPerformed(evt);
            }
        });
        jPanel3.add(btn_NuevoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 313, 50, 40));

        txt_IDCliente.setEnabled(false);
        jPanel3.add(txt_IDCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(271, 35, 15, -1));

        jTabbedPane1.addTab("tab2", jPanel3);

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("RUC:");
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 47, -1, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("NOMBRE:");
        jPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 95, -1, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("TELEFONO:");
        jPanel4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 146, -1, -1));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("DIRECCIÓN:");
        jPanel4.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 205, -1, -1));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("RAZON SOCIAL:");
        jPanel4.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 245, -1, -1));
        jPanel4.add(txt_RUCProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 47, 79, -1));
        jPanel4.add(txt_NombreProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 93, 150, -1));

        txt_TelefonoProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_TelefonoProveedorActionPerformed(evt);
            }
        });
        jPanel4.add(txt_TelefonoProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 144, 150, -1));
        jPanel4.add(txt_DireccionProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(112, 203, 147, -1));
        jPanel4.add(txt_RazonSocialProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 243, 141, -1));

        table_Proveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "RUC", "NOMBRE", "TELEFONO", "DIRECCIÓN", "RAZON SOCIAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_Proveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_ProveedorMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(table_Proveedor);
        if (table_Proveedor.getColumnModel().getColumnCount() > 0) {
            table_Proveedor.getColumnModel().getColumn(0).setPreferredWidth(20);
            table_Proveedor.getColumnModel().getColumn(1).setPreferredWidth(40);
            table_Proveedor.getColumnModel().getColumn(2).setPreferredWidth(100);
            table_Proveedor.getColumnModel().getColumn(3).setPreferredWidth(50);
            table_Proveedor.getColumnModel().getColumn(4).setPreferredWidth(80);
            table_Proveedor.getColumnModel().getColumn(5).setPreferredWidth(70);
        }

        jPanel4.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(277, 47, 548, 318));

        btn_GuardarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/GuardarTodo.png"))); // NOI18N
        btn_GuardarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_GuardarProveedorActionPerformed(evt);
            }
        });
        jPanel4.add(btn_GuardarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 281, -1, -1));

        btn_ActualizarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Actualizar (2).png"))); // NOI18N
        btn_ActualizarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ActualizarProveedorActionPerformed(evt);
            }
        });
        jPanel4.add(btn_ActualizarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(142, 281, -1, -1));

        btn_EliminarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar.png"))); // NOI18N
        btn_EliminarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EliminarProveedorActionPerformed(evt);
            }
        });
        jPanel4.add(btn_EliminarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 332, -1, -1));

        btn_NuevoProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/nuevo.png"))); // NOI18N
        btn_NuevoProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_NuevoProveedorActionPerformed(evt);
            }
        });
        jPanel4.add(btn_NuevoProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(142, 332, -1, -1));
        jPanel4.add(txt_IDProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 47, 22, -1));

        jTabbedPane1.addTab("tab3", jPanel4);

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setText("Código:");
        jPanel5.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setText("Descripción:");
        jPanel5.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 91, -1, -1));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setText("Cantidad:");
        jPanel5.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 136, -1, -1));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setText("Precio:");
        jPanel5.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setText("Proveedor:");
        jPanel5.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 223, -1, -1));
        jPanel5.add(txt_CodigoProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 38, 120, -1));
        jPanel5.add(txt_DescripcionProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 89, 211, -1));
        jPanel5.add(txt_CantidadProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 134, 107, -1));
        jPanel5.add(txt_PrecioProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 178, 107, -1));

        combo_ProveedorProducto.setEditable(true);
        jPanel5.add(combo_ProveedorProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 221, 159, -1));

        table_Producto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CÓDIGO", "DESCRIPCIÓN", "PROVEEDOR", "STOCK", "PRECIO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_Producto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_ProductoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(table_Producto);
        if (table_Producto.getColumnModel().getColumnCount() > 0) {
            table_Producto.getColumnModel().getColumn(0).setPreferredWidth(20);
            table_Producto.getColumnModel().getColumn(1).setPreferredWidth(50);
            table_Producto.getColumnModel().getColumn(2).setPreferredWidth(100);
            table_Producto.getColumnModel().getColumn(3).setPreferredWidth(60);
            table_Producto.getColumnModel().getColumn(4).setPreferredWidth(40);
            table_Producto.getColumnModel().getColumn(5).setPreferredWidth(50);
        }

        jPanel5.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(348, 11, 477, 304));

        btn_GuardarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/GuardarTodo.png"))); // NOI18N
        btn_GuardarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_GuardarProductoActionPerformed(evt);
            }
        });
        jPanel5.add(btn_GuardarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 263, -1, -1));

        btn_ActualizarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Actualizar (2).png"))); // NOI18N
        btn_ActualizarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ActualizarProductoActionPerformed(evt);
            }
        });
        jPanel5.add(btn_ActualizarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(149, 263, -1, -1));

        btn_EliminarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar.png"))); // NOI18N
        btn_EliminarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EliminarProductoActionPerformed(evt);
            }
        });
        jPanel5.add(btn_EliminarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 316, -1, -1));

        btn_NuevoProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/nuevo.png"))); // NOI18N
        btn_NuevoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_NuevoProductoActionPerformed(evt);
            }
        });
        jPanel5.add(btn_NuevoProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(258, 263, -1, -1));

        btn_ExcelProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/excel.png"))); // NOI18N
        btn_ExcelProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ExcelProductoActionPerformed(evt);
            }
        });
        jPanel5.add(btn_ExcelProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(149, 314, 57, -1));
        jPanel5.add(txt_IDProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(299, 38, -1, -1));

        jTabbedPane1.addTab("tab4", jPanel5);

        table_Ventas5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CLIENTE", "VENDEDOR", "TOTAL"
            }
        ));
        jScrollPane5.setViewportView(table_Ventas5);
        if (table_Ventas5.getColumnModel().getColumnCount() > 0) {
            table_Ventas5.getColumnModel().getColumn(0).setPreferredWidth(20);
            table_Ventas5.getColumnModel().getColumn(1).setPreferredWidth(60);
            table_Ventas5.getColumnModel().getColumn(2).setPreferredWidth(60);
            table_Ventas5.getColumnModel().getColumn(3).setPreferredWidth(60);
        }

        btn_PDFVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/pdf.png"))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(79, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(btn_PDFVentas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_IDVenta5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(578, 578, 578))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_PDFVentas)
                    .addComponent(txt_IDVenta5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("tab5", jPanel6);

        jLabel27.setText("RUC");

        jLabel28.setText("NOMBRE");

        jLabel29.setText("TELEFONO");

        jLabel30.setText("DIRECCIÓN");

        jLabel31.setText("RAZON SOCIAL");

        btn_ActualizarConfiguracion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Actualizar (2).png"))); // NOI18N
        btn_ActualizarConfiguracion.setText("ACTUALIZAR");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel32.setText("DATOS DE LA EMPRESA");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel27)
                            .addComponent(jLabel30)
                            .addComponent(txt_RucConfiguracion)
                            .addComponent(txt_DireccionConfiguracion, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(86, 86, 86)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btn_ActualizarConfiguracion)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txt_RazonSocialConfiguracion, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txt_NombreConfiguracion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(76, 76, 76)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel29)
                                    .addComponent(txt_TelefonoConfiguracion, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(txt_IDConfiguracion, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(jLabel32)))
                .addContainerGap(105, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_IDConfiguracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_RucConfiguracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_NombreConfiguracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_TelefonoConfiguracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jLabel31))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_DireccionConfiguracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_RazonSocialConfiguracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(btn_ActualizarConfiguracion)
                .addGap(59, 59, 59))
        );

        jTabbedPane1.addTab("tab6", jPanel7);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 840, 410));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ClienteActionPerformed
        LimpiarTable();
        ListarCliente();
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_btn_ClienteActionPerformed

    private void txt_PrecioVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_PrecioVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_PrecioVentaActionPerformed

    private void txt_TelefonoProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_TelefonoProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_TelefonoProveedorActionPerformed

    private void btn_ProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ProveedorActionPerformed
        LimpiarTable();
        ListarProveedor();
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_btn_ProveedorActionPerformed

    private void btn_GuardarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GuardarClienteActionPerformed
        if (!"".equals(txt_DNICliente.getText()) && !"".equals(txt_NombreCliente.getText()) && !"".equals(txt_TelefonoCliente.getText()) && !"".equals(txt_DireccionCliente.getText())) {
            cliente.setDNI(Integer.parseInt(txt_DNICliente.getText()));
            cliente.setNombre(txt_NombreCliente.getText());
            cliente.setTelefono(Integer.parseInt(txt_TelefonoCliente.getText()));
            cliente.setDireccion(txt_DireccionCliente.getText());
            cliente.setRazon(txt_RazonSocialCliente.getText());
            cliente1.RegistrarCliente(cliente);
            LimpiarTable();
            LimpiarCliente();
            ListarCliente();
            JOptionPane.showMessageDialog(null, "Cliente Registrado");
        } else {
            JOptionPane.showMessageDialog(null, "Rellene todos los campos");
        }
    }//GEN-LAST:event_btn_GuardarClienteActionPerformed

    private void btn_EliminarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EliminarClienteActionPerformed
        if (!"".equals(txt_IDCliente.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de Eliminar");

            if (pregunta == 0) {
                int ID = Integer.parseInt(txt_IDCliente.getText());
                cliente1.EliminarCliente(ID);
                LimpiarTable();
                LimpiarCliente();
                ListarCliente();
            } else {
                LimpiarCliente();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        }
    }//GEN-LAST:event_btn_EliminarClienteActionPerformed

    private void table_ClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_ClienteMouseClicked
        int fila = table_Cliente.rowAtPoint(evt.getPoint());

        txt_IDCliente.setText(table_Cliente.getValueAt(fila, 0).toString());
        txt_DNICliente.setText(table_Cliente.getValueAt(fila, 1).toString());
        txt_NombreCliente.setText(table_Cliente.getValueAt(fila, 2).toString());
        txt_TelefonoCliente.setText(table_Cliente.getValueAt(fila, 3).toString());
        txt_DireccionCliente.setText(table_Cliente.getValueAt(fila, 4).toString());
        txt_RazonSocialCliente.setText(table_Cliente.getValueAt(fila, 5).toString());
    }//GEN-LAST:event_table_ClienteMouseClicked

    private void btn_EditarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EditarClienteActionPerformed
        if ("".equals(txt_IDCliente.getText())) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        } else {

            if (!"".equals(txt_DNICliente.getText()) && !"".equals(txt_NombreCliente.getText()) && !"".equals(txt_TelefonoCliente.getText()) && !"".equals(txt_DireccionCliente.getText())) {
                cliente.setDNI(Integer.parseInt(txt_DNICliente.getText()));
                cliente.setNombre(txt_NombreCliente.getText());
                cliente.setTelefono(Integer.parseInt(txt_TelefonoCliente.getText()));
                cliente.setDireccion(txt_DireccionCliente.getText());
                cliente.setRazon(txt_RazonSocialCliente.getText());
                cliente.setID(Integer.parseInt(txt_IDCliente.getText()));
                cliente1.ModificarCliente(cliente);
                LimpiarTable();
                LimpiarCliente();
                ListarCliente();
                JOptionPane.showMessageDialog(null, "Cliente Modificado");
            } else {
                JOptionPane.showMessageDialog(null, "Rellene todos los campos");
            }
        }
    }//GEN-LAST:event_btn_EditarClienteActionPerformed

    private void btn_NuevoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NuevoClienteActionPerformed
        LimpiarCliente();
    }//GEN-LAST:event_btn_NuevoClienteActionPerformed

    private void btn_GuardarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GuardarProveedorActionPerformed
        if (!"".equals(txt_RUCProveedor.getText()) && !"".equals(txt_NombreProveedor.getText()) && !"".equals(txt_TelefonoProveedor.getText()) && !"".equals(txt_DireccionProveedor.getText()) && !"".equals(txt_RazonSocialProveedor.getText())) {
            proveedor.setRuc(Integer.parseInt(txt_RUCProveedor.getText()));
            proveedor.setNombre(txt_NombreProveedor.getText());
            proveedor.setTelefono(Integer.parseInt(txt_TelefonoProveedor.getText()));
            proveedor.setDireccion(txt_DireccionProveedor.getText());
            proveedor.setRazon(txt_RazonSocialProveedor.getText());
            proveedor1.RegistrarProveedor(proveedor);
            LimpiarTable();
            ListarProveedor();
            LimpiarProveedor();
            JOptionPane.showMessageDialog(null, "Proveedor Registrado");
        } else {
            JOptionPane.showMessageDialog(null, "Rellene todos los campos");
        }
    }//GEN-LAST:event_btn_GuardarProveedorActionPerformed

    private void table_ProveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_ProveedorMouseClicked
        int fila = table_Proveedor.rowAtPoint(evt.getPoint());
        txt_IDProveedor.setText(table_Proveedor.getValueAt(fila, 0).toString());
        txt_RUCProveedor.setText(table_Proveedor.getValueAt(fila, 1).toString());
        txt_NombreProveedor.setText(table_Proveedor.getValueAt(fila, 2).toString());
        txt_TelefonoProveedor.setText(table_Proveedor.getValueAt(fila, 3).toString());
        txt_DireccionProveedor.setText(table_Proveedor.getValueAt(fila, 4).toString());
        txt_RazonSocialProveedor.setText(table_Proveedor.getValueAt(fila, 5).toString());
    }//GEN-LAST:event_table_ProveedorMouseClicked

    private void btn_EliminarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EliminarProveedorActionPerformed
        if (!"".equals(txt_IDProveedor.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar el Proveedor");
            if (pregunta == 0) {
                int ID = Integer.parseInt(txt_IDProveedor.getText());
                proveedor1.EliminarProveedor(ID);
                LimpiarTable();
                ListarProveedor();
                LimpiarProveedor();
            } else {
                LimpiarProveedor();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        }
    }//GEN-LAST:event_btn_EliminarProveedorActionPerformed

    private void btn_ActualizarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ActualizarProveedorActionPerformed
        if ("".equals(txt_IDProveedor.getText())) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        } else {
            if (!"".equals(txt_RUCProveedor.getText()) && !"".equals(txt_NombreProveedor.getText()) && !"".equals(txt_TelefonoProveedor.getText()) && !"".equals(txt_DireccionProveedor.getText()) && !"".equals(txt_RazonSocialProveedor.getText())) {
                proveedor.setRuc(Integer.parseInt(txt_RUCProveedor.getText()));
                proveedor.setNombre(txt_NombreProveedor.getText());
                proveedor.setTelefono(Integer.parseInt(txt_TelefonoProveedor.getText()));
                proveedor.setDireccion(txt_DireccionProveedor.getText());
                proveedor.setRazon(txt_RazonSocialProveedor.getText());
                proveedor.setID(Integer.parseInt(txt_IDProveedor.getText()));
                proveedor1.ModificarProveedor(proveedor);
                LimpiarTable();
                ListarProveedor();
                LimpiarProveedor();
                JOptionPane.showMessageDialog(null, "Proveedor Modificado");
            } else {
                JOptionPane.showMessageDialog(null, "Rellene todos los campos");
            }
        }
    }//GEN-LAST:event_btn_ActualizarProveedorActionPerformed

    private void btn_NuevoProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NuevoProveedorActionPerformed
        LimpiarProveedor();
    }//GEN-LAST:event_btn_NuevoProveedorActionPerformed

    private void btn_GuardarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GuardarProductoActionPerformed
        if (!"".equals(txt_CodigoProducto.getText()) && !"".equals(txt_DescripcionProducto.getText()) && !"".equals(txt_CantidadProducto.getText()) && !"".equals(txt_PrecioProducto.getText()) && !"".equals(combo_ProveedorProducto.getSelectedItem())) {
            producto.setCodigo(txt_CodigoProducto.getText());
            producto.setNombre(txt_DescripcionProducto.getText());
            producto.setProveedor(combo_ProveedorProducto.getSelectedItem().toString());
            producto.setStock(Integer.parseInt(txt_CantidadProducto.getText()));
            producto.setPrecio(Double.parseDouble(txt_PrecioProducto.getText()));
            producto1.RegistrarProducto(producto);
            LimpiarTable();
            ListarProducto();
            LimpiarProducto();
            JOptionPane.showMessageDialog(null, "Producto Registrado");
        } else {
            JOptionPane.showMessageDialog(null, "Rellene todos los campos");
        }
    }//GEN-LAST:event_btn_GuardarProductoActionPerformed

    private void btn_ProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ProductosActionPerformed
        LimpiarTable();
        ListarProducto();
        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_btn_ProductosActionPerformed

    private void table_ProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_ProductoMouseClicked
        int fila = table_Producto.rowAtPoint(evt.getPoint());
        txt_IDProducto.setText(table_Producto.getValueAt(fila, 0).toString());
        txt_CodigoProducto.setText(table_Producto.getValueAt(fila, 1).toString());
        txt_DescripcionProducto.setText(table_Producto.getValueAt(fila, 2).toString());
        combo_ProveedorProducto.setSelectedItem(table_Producto.getValueAt(fila, 3).toString());
        txt_CantidadProducto.setText(table_Producto.getValueAt(fila, 4).toString());
        txt_PrecioProducto.setText(table_Producto.getValueAt(fila, 5).toString());

    }//GEN-LAST:event_table_ProductoMouseClicked

    private void btn_EliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EliminarProductoActionPerformed
        if (!"".equals(txt_IDProducto.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar el Producto");
            if (pregunta == 0) {
                int ID = Integer.parseInt(txt_IDProducto.getText());
                producto1.EliminarProducto(ID);
                LimpiarTable();
                ListarProducto();
                LimpiarProducto();
            } else {
                LimpiarProducto();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        }
    }//GEN-LAST:event_btn_EliminarProductoActionPerformed

    private void btn_ActualizarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ActualizarProductoActionPerformed
        if ("".equals(txt_IDProducto.getText())) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        } else {
            if (!"".equals(txt_CodigoProducto.getText()) && !"".equals(txt_DescripcionProducto.getText()) && !"".equals(txt_CantidadProducto.getText()) && !"".equals(txt_PrecioProducto.getText())) {
                producto.setCodigo(txt_CodigoProducto.getText());
                producto.setNombre(txt_DescripcionProducto.getText());
                producto.setProveedor(combo_ProveedorProducto.getSelectedItem().toString());
                producto.setStock(Integer.parseInt(txt_CantidadProducto.getText()));
                producto.setPrecio(Double.parseDouble(txt_PrecioProducto.getText()));
                producto.setID(Integer.parseInt(txt_IDProducto.getText()));
                producto1.ModificarProducto(producto);
                LimpiarTable();
                ListarProducto();
                LimpiarProducto();
                JOptionPane.showMessageDialog(null, "Producto Modificado");
            } else {
                JOptionPane.showMessageDialog(null, "Rellene todos los campos");
            }
        }
    }//GEN-LAST:event_btn_ActualizarProductoActionPerformed

    private void btn_NuevoProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NuevoProductoActionPerformed
        LimpiarProducto();
    }//GEN-LAST:event_btn_NuevoProductoActionPerformed

    private void btn_ExcelProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ExcelProductoActionPerformed
        Excel.reporte();
    }//GEN-LAST:event_btn_ExcelProductoActionPerformed

    private void txt_CodigoVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_CodigoVentaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txt_CodigoVenta.getText())) {
                String codigo = txt_CodigoVenta.getText();
                producto = producto1.BuscarProductos(codigo);
                if (producto.getNombre() != null) {
                    txt_DescripcionVenta.setText("" + producto.getNombre());
                    txt_PrecioVenta.setText("" + producto.getPrecio());
                    txt_StockDisponible.setText("" + producto.getStock());
                    txt_CantidadVenta.requestFocus();
                } else {
                    LimpiarVenta();
                    txt_CodigoVenta.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese el código del producto");
                txt_CodigoVenta.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_CodigoVentaKeyPressed

    private void txt_CantidadVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_CantidadVentaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txt_CantidadVenta.getText())) {
                String codigo = txt_CodigoVenta.getText();
                String descripcion = txt_DescripcionVenta.getText();
                int cantidad = Integer.parseInt(txt_CantidadVenta.getText());
                double precio = Double.parseDouble(txt_PrecioVenta.getText());
                double total = cantidad * precio;
                int stock = Integer.parseInt(txt_StockDisponible.getText());
                if (stock >= cantidad) {
                    item = item + 1;
                    tmp = (DefaultTableModel) table_Venta1.getModel();
                    for (int i = 0; i < table_Venta1.getRowCount(); i++) {
                        if (table_Venta1.getValueAt(i, 1).equals(txt_DescripcionVenta.getText())) {
                            JOptionPane.showMessageDialog(null, "El producto ya esta registrado");
                            return;
                        }
                    }
                    ArrayList lista = new ArrayList();
                    lista.add(item);
                    lista.add(codigo);
                    lista.add(descripcion);
                    lista.add(cantidad);
                    lista.add(precio);
                    lista.add(total);
                    Object[] obj = new Object[5];
                    obj[0] = lista.get(1);
                    obj[1] = lista.get(2);
                    obj[2] = lista.get(3);
                    obj[3] = lista.get(4);
                    obj[4] = lista.get(5);
                    tmp.addRow(obj);
                    table_Venta1.setModel(tmp);
                    TotalPagar();
                    LimpiarVenta();
                    txt_CodigoVenta.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, "Stock no disponible");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese cantidad");
            }
        }
    }//GEN-LAST:event_txt_CantidadVentaKeyPressed

    private void btn_EliminarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EliminarVentaActionPerformed
        clase = (DefaultTableModel) table_Venta1.getModel();
        clase.removeRow(table_Venta1.getSelectedRow());
        TotalPagar();
        txt_CodigoVenta.requestFocus();
    }//GEN-LAST:event_btn_EliminarVentaActionPerformed

    private void txt_RucVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_RucVentaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txt_RucVenta.getText())) {
                int DNI = Integer.parseInt(txt_RucVenta.getText());
                cliente = cliente1.BuscarCliente(DNI);
                if (cliente.getNombre() != null) {
                    txt_NombreClienteVenta.setText("" + cliente.getNombre());
                    txt_TelefonoClienteVenta.setText("" + cliente.getTelefono());
                    txt_DireccionClienteVenta.setText("" + cliente.getDireccion());
                    txt_RazonClienteVenta.setText("" + cliente.getRazon());
                } else {
                    txt_RucVenta.setText("");
                    JOptionPane.showMessageDialog(null, "El Cliente no existe");
                }
            }
        }
    }//GEN-LAST:event_txt_RucVentaKeyPressed

    private void btn_GenerarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GenerarVentaActionPerformed
        // TODO add your handling code here:
        if(table_Venta1.getRowCount()>0){
            if (!"".equals(txt_RucVenta.getText())) {
                if (!"".equals(txt_NombreClienteVenta.getText())) {
                RegistrarVenta();
                RegistrarDetalle();
                ActualizarStock();
                PDF();
                LimpiarTableVenta();
                LimpiarClienteVenta();

                } else {
                JOptionPane.showMessageDialog(this, "Ingrese un DNI/RUC del cliente");
                }

            } else {
            JOptionPane.showMessageDialog(this, "Ingrese el DNI/RUC valido");
            txt_RucVenta.requestFocus();
            }
        }else {
            JOptionPane.showMessageDialog(this, "No hay productos en la venta");
            txt_CodigoVenta.requestFocus();
            }
        
        

    }//GEN-LAST:event_btn_GenerarVentaActionPerformed

    private void btn_NuevaVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NuevaVentaActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_btn_NuevaVentaActionPerformed

    private void btn_ConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ConfiguracionActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(5);
        ListarConfiguracion();
    }//GEN-LAST:event_btn_ConfiguracionActionPerformed

    private void btn_VentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_VentasActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(4);
        LimpiarTable();
        ListarVentas();
    }//GEN-LAST:event_btn_VentasActionPerformed

    private void txt_CodigoVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_CodigoVentaKeyTyped
        // TODO add your handling code here:
        event.numberKeyPress(evt);
    }//GEN-LAST:event_txt_CodigoVentaKeyTyped

    private void txt_DescripcionVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_DescripcionVentaKeyTyped
        // TODO add your handling code here:
        event.textKeyPress(evt);
    }//GEN-LAST:event_txt_DescripcionVentaKeyTyped

    private void txt_CantidadVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_CantidadVentaKeyTyped
        // TODO add your handling code here:
        event.numberKeyPress(evt);
    }//GEN-LAST:event_txt_CantidadVentaKeyTyped

    private void txt_PrecioVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_PrecioVentaKeyTyped
        // TODO add your handling code here:
        event.numberDecimalKeyPress(evt, txt_PrecioVenta);
    }//GEN-LAST:event_txt_PrecioVentaKeyTyped

    private void txt_StockDisponibleKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_StockDisponibleKeyTyped
        // TODO add your handling code here:
        event.numberKeyPress(evt);
    }//GEN-LAST:event_txt_StockDisponibleKeyTyped

    private void txt_RucVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_RucVentaKeyTyped
        // TODO add your handling code here:
        event.numberKeyPress(evt);
    }//GEN-LAST:event_txt_RucVentaKeyTyped

    private void Btn_GraficarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Btn_GraficarMouseClicked
        String fechaReporte = new SimpleDateFormat("dd/MM/yyyy").format(Midate.getDate());
        Grafico.Graficar(fechaReporte);       // TODO add your handling code here:
    }//GEN-LAST:event_Btn_GraficarMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sistema().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Btn_Graficar;
    private com.toedter.calendar.JDateChooser Midate;
    private javax.swing.JButton btn_ActualizarConfiguracion;
    private javax.swing.JButton btn_ActualizarProducto;
    private javax.swing.JButton btn_ActualizarProveedor;
    private javax.swing.JButton btn_Cliente;
    private javax.swing.JButton btn_Configuracion;
    private javax.swing.JButton btn_EditarCliente;
    private javax.swing.JButton btn_EliminarCliente;
    private javax.swing.JButton btn_EliminarProducto;
    private javax.swing.JButton btn_EliminarProveedor;
    private javax.swing.JButton btn_EliminarVenta;
    private javax.swing.JButton btn_ExcelProducto;
    private javax.swing.JButton btn_GenerarVenta;
    private javax.swing.JButton btn_GuardarCliente;
    private javax.swing.JButton btn_GuardarProducto;
    private javax.swing.JButton btn_GuardarProveedor;
    private javax.swing.JButton btn_NuevaVenta;
    private javax.swing.JButton btn_NuevoCliente;
    private javax.swing.JButton btn_NuevoProducto;
    private javax.swing.JButton btn_NuevoProveedor;
    private javax.swing.JButton btn_PDFVentas;
    private javax.swing.JButton btn_Productos;
    private javax.swing.JButton btn_Proveedor;
    private javax.swing.JButton btn_Ventas;
    private javax.swing.JComboBox<String> combo_ProveedorProducto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbl_Total;
    private javax.swing.JLabel lbl_vendedor;
    private javax.swing.JTable table_Cliente;
    private javax.swing.JTable table_Producto;
    private javax.swing.JTable table_Proveedor;
    private javax.swing.JTable table_Venta1;
    private javax.swing.JTable table_Ventas5;
    private javax.swing.JTextField txt_CantidadProducto;
    private javax.swing.JTextField txt_CantidadVenta;
    private javax.swing.JTextField txt_CodigoProducto;
    private javax.swing.JTextField txt_CodigoVenta;
    private javax.swing.JTextField txt_DNICliente;
    private javax.swing.JTextField txt_DescripcionProducto;
    private javax.swing.JTextField txt_DescripcionVenta;
    private javax.swing.JTextField txt_DireccionCliente;
    private javax.swing.JTextField txt_DireccionClienteVenta;
    private javax.swing.JTextField txt_DireccionConfiguracion;
    private javax.swing.JTextField txt_DireccionProveedor;
    private javax.swing.JTextField txt_IDCliente;
    private javax.swing.JTextField txt_IDConfiguracion;
    private javax.swing.JTextField txt_IDProducto;
    private javax.swing.JTextField txt_IDProveedor;
    private javax.swing.JTextField txt_IDVenta1;
    private javax.swing.JTextField txt_IDVenta5;
    private javax.swing.JTextField txt_NombreCliente;
    private javax.swing.JTextField txt_NombreClienteVenta;
    private javax.swing.JTextField txt_NombreConfiguracion;
    private javax.swing.JTextField txt_NombreProveedor;
    private javax.swing.JTextField txt_PrecioProducto;
    private javax.swing.JTextField txt_PrecioVenta;
    private javax.swing.JTextField txt_RUCProveedor;
    private javax.swing.JTextField txt_RazonClienteVenta;
    private javax.swing.JTextField txt_RazonSocialCliente;
    private javax.swing.JTextField txt_RazonSocialConfiguracion;
    private javax.swing.JTextField txt_RazonSocialProveedor;
    private javax.swing.JTextField txt_RucConfiguracion;
    private javax.swing.JTextField txt_RucVenta;
    private javax.swing.JTextField txt_StockDisponible;
    private javax.swing.JTextField txt_TelefonoCliente;
    private javax.swing.JTextField txt_TelefonoClienteVenta;
    private javax.swing.JTextField txt_TelefonoConfiguracion;
    private javax.swing.JTextField txt_TelefonoProveedor;
    // End of variables declaration//GEN-END:variables
    private void LimpiarCliente() {
        txt_IDCliente.setText("");
        txt_DNICliente.setText("");
        txt_NombreCliente.setText("");
        txt_TelefonoCliente.setText("");
        txt_DireccionCliente.setText("");
        txt_RazonSocialCliente.setText("");
    }

    private void LimpiarProveedor() {
        txt_IDProveedor.setText("");
        txt_RUCProveedor.setText("");
        txt_NombreProveedor.setText("");
        txt_TelefonoProveedor.setText("");
        txt_DireccionProveedor.setText("");
        txt_RazonSocialProveedor.setText("");
    }

    private void LimpiarProducto() {
        txt_IDProducto.setText("");
        txt_CodigoProducto.setText("");
        combo_ProveedorProducto.setSelectedItem(null);
        txt_DescripcionProducto.setText("");
        txt_CantidadProducto.setText("");
        txt_PrecioProducto.setText("");

    }

    private void TotalPagar() {
        totalpagar = 0.00;
        int nfila = table_Venta1.getRowCount();
        for (int i = 0; i < nfila; i++) {
            double calcular = Double.parseDouble(String.valueOf(table_Venta1.getModel().getValueAt(i, 4)));
            totalpagar = totalpagar + calcular;
        }
        lbl_Total.setText(String.format("%.2f", totalpagar));
    }

    private void LimpiarVenta() {
        txt_CodigoVenta.setText("");
        txt_DescripcionVenta.setText("");
        txt_CantidadVenta.setText("");
        txt_PrecioVenta.setText("");
        txt_StockDisponible.setText("");
        txt_IDVenta1.setText("");
    }

    private void RegistrarVenta() {
        String Cliente = txt_NombreClienteVenta.getText();
        String vendedor = lbl_vendedor.getText();
        Double monto = totalpagar;
        venta.setCliente(Cliente);
        venta.setVendedor(vendedor);
        venta.setTotal(monto);
        venta.setFecha(fechaActual);
        venta1.RegistrarVenta(venta);
    }

    private void RegistrarDetalle() {
        int ID = venta1.IDVenta();
        for (int i = 0; i < table_Venta1.getRowCount(); i++) {
            String codigo_producto = table_Venta1.getValueAt(i, 0).toString();
            int cantidad = Integer.parseInt(table_Venta1.getValueAt(i, 2).toString());
            double precio = Double.parseDouble(table_Venta1.getValueAt(i, 3).toString());

            detalle_venta.setCodigo_producto(codigo_producto);
            detalle_venta.setCantidad(cantidad);
            detalle_venta.setPrecio(precio);
            detalle_venta.setID_Venta(ID);
            venta1.RegistrarDetalle(detalle_venta);

        }
    }

    private void ActualizarStock() {
        for (int i = 0; i < table_Venta1.getRowCount(); i++) {
            String codigo = table_Venta1.getValueAt(i, 0).toString();
            int cantidad = Integer.parseInt(table_Venta1.getValueAt(i, 2).toString());
            producto = producto1.BuscarProductos(codigo);
            int StockActual = producto.getStock() - cantidad;
            venta1.ActualizarStock(StockActual, codigo);
        }
    }

    private void LimpiarTableVenta() {
        tmp = (DefaultTableModel) table_Venta1.getModel();
        int fila = table_Venta1.getRowCount();
        for (int i = 0; i < fila; i++) {
            tmp.removeRow(0);
        }
    }

    private void LimpiarClienteVenta() {
        txt_RucVenta.setText("");
        txt_NombreClienteVenta.setText("");
        txt_TelefonoClienteVenta.setText("");
        txt_DireccionClienteVenta.setText("");
        txt_RazonClienteVenta.setText("");
    }
    
    private void PDF(){
        try{
            int ID=venta1.IDVenta();
            FileOutputStream archivo;
            File file = new File("src/PDF/venta"+ID+".pdf");
            archivo = new FileOutputStream(file);
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();
            Image img = Image.getInstance("src/Imagenes/chameleon-reloj.png");
            
            Paragraph fecha = new Paragraph();
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN,12, Font.BOLD, BaseColor.BLUE);
            fecha.add(Chunk.NEWLINE);
            Date date = new Date();
            fecha.add("factura: "+ID+"\n"+"Fecha: "+new SimpleDateFormat("dd-mm-yyyy").format(date)+"\n\n");
            
            PdfPTable encabezado = new PdfPTable(4);
            encabezado.setWidthPercentage(100);
            encabezado.getDefaultCell().setBorder(0);
            float[] columnaencabezado = new float[]{20f,30f,70f,40f};
            encabezado.setWidths(columnaencabezado);
            encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
            
            encabezado.addCell(img);
      
            String ruc = txt_RucConfiguracion.getText();
            String nombre=txt_NombreConfiguracion.getText();
            String telefono=txt_TelefonoConfiguracion.getText();
            String direccion=txt_DireccionConfiguracion.getText();
            String razon=txt_RazonSocialConfiguracion.getText();
                                   
            encabezado.addCell("");
            encabezado.addCell("RUC: "+ruc+"\nNombre: "+nombre+"\nTelefono: "+telefono+"\nDireccion: "+direccion+"\nRazon: "+razon);
            encabezado.addCell(fecha);
            doc.add(encabezado);
            
            Paragraph clientes=new Paragraph();
            clientes.add(Chunk.NEWLINE);
            clientes.add("Datos de los clientes"+"\n\n");
            doc.add(clientes);
            
            PdfPTable tablacliente = new  PdfPTable(4);
            tablacliente.setWidthPercentage(100);
            tablacliente.getDefaultCell().setBorder(0);
            float[] columnacliente = new float[]{20f,50f,30f,40f};
            tablacliente.setWidths(columnacliente);
            tablacliente.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell clientes1=new PdfPCell(new Phrase("DNI/Ruc", negrita));
            PdfPCell clientes2=new PdfPCell(new Phrase("Nombre", negrita));
            PdfPCell clientes3=new PdfPCell(new Phrase("Telefono",negrita));
            PdfPCell clientes4=new PdfPCell(new Phrase("Direccion", negrita));
            clientes1.setBorder(0);
            clientes2.setBorder(0);
            clientes3.setBorder(0);
            clientes4.setBorder(0);
            tablacliente.addCell(clientes1);
            tablacliente.addCell(clientes2);
            tablacliente.addCell(clientes3);
            tablacliente.addCell(clientes4);
            tablacliente.addCell(txt_RucVenta.getText());
            tablacliente.addCell(txt_NombreClienteVenta.getText());
            tablacliente.addCell(txt_TelefonoClienteVenta.getText());
            tablacliente.addCell(txt_DireccionClienteVenta.getText());
            
            doc.add(tablacliente);
            
            //productos
            
            PdfPTable tablaproductos = new  PdfPTable(4);
            tablaproductos.setWidthPercentage(100);
            tablaproductos.getDefaultCell().setBorder(0);
            float[] columnaproductos = new float[]{10f,50f,15f,20f};
            tablaproductos.setWidths(columnaproductos);
            tablaproductos.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell productos1=new PdfPCell(new Phrase("Cantidad", negrita));
            PdfPCell productos2=new PdfPCell(new Phrase("Descripcion", negrita));
            PdfPCell productos3=new PdfPCell(new Phrase("Precio U.", negrita));
            PdfPCell productos4=new PdfPCell(new Phrase("Precio T.", negrita));
            productos1.setBorder(0);
            productos2.setBorder(0);
            productos3.setBorder(0);
            productos4.setBorder(0);
            productos1.setBackgroundColor(BaseColor.DARK_GRAY);
            productos2.setBackgroundColor(BaseColor.DARK_GRAY);
            productos3.setBackgroundColor(BaseColor.DARK_GRAY);
            productos4.setBackgroundColor(BaseColor.DARK_GRAY);
            tablaproductos.addCell(productos1);
            tablaproductos.addCell(productos2);
            tablaproductos.addCell(productos3);
            tablaproductos.addCell(productos4);
            for(int i=0;i<table_Venta1.getRowCount();i++){
                String productos = table_Venta1.getValueAt(i, 1).toString();
                String cantidad = table_Venta1.getValueAt(i, 2).toString();
                String precio = table_Venta1.getValueAt(i, 3).toString();
                String total = table_Venta1.getValueAt(i, 4).toString();
                tablaproductos.addCell(cantidad);
                tablaproductos.addCell(productos);
                tablaproductos.addCell(precio);
                tablaproductos.addCell(total);
            }
            doc.add(tablaproductos);
            
            
            Paragraph info = new Paragraph();
            info.add(Chunk.NEWLINE);
            info.add("Total a Pagar: "+totalpagar);
            info.setAlignment(Element.ALIGN_RIGHT);
            doc.add(info);
            
            Paragraph firma = new Paragraph();
            firma.add(Chunk.NEWLINE);
            firma.add("Cancelacion y Firma\n\n");
            firma.add("--------------------");
            firma.setAlignment(Element.ALIGN_CENTER);
            doc.add(firma);
            
            Paragraph mensaje = new Paragraph();
            mensaje.add(Chunk.NEWLINE);
            mensaje.add("Gracias por su preferencia");;
            mensaje.setAlignment(Element.ALIGN_CENTER);
            doc.add(mensaje);
            
            doc.close();
            archivo.close();
            Desktop.getDesktop().open(file);
        }catch(DocumentException | IOException e){
            System.out.println(e.toString());
            
        }
    }
}
