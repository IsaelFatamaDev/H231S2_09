/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import com.formdev.flatlaf.FlatLightLaf;
import java.sql.PreparedStatement;
import consultas.equipos;
import java.awt.HeadlessException;
import java.math.BigDecimal;
import java.sql.Connection;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author javie
 */
public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    String terminos;
    conexion.Conexion conectar = new conexion.Conexion();
    Connection cn;
    Statement st;
    ResultSet rs;
    DefaultTableModel modeloTabla;
    Integer id;

    public Principal() {
        initComponents();
        conexion.Conexion conectar = new conexion.Conexion();
        Connection cn;
        Statement st;
        ResultSet rs;
        DefaultTableModel modeloTable;
        listar();
        boton();
        consultas.equipos objConsultas = new equipos();
        objConsultas.mostrarEstados(comboEstado);

    }

    public void boton() {
        buttonGroup1.add(buttonNo);
        buttonGroup1.add(buttonSi);
    }

    void limpiar() {
        txtId.setText("");
        txtDes.setText("");
        txtEspeci.setText("");
        txtIdEstado.setText("");
        txtMarca.setText("");
        txtModelo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
        txtSerie.setText("");
        comboEstado.setSelectedIndex(-1);
        buttonGroup1.clearSelection();
        buttonSi.setSelected(false);
    }

    void listar() {
        String sql = "SELECT equipos.id, equipos.serie, equipos.nombre, equipos.marca, equipos.modelo, equipos.espTecni, estado.estMant, equipos.precio, equipos.cantidad, equipos.descripcion, equipos.estado FROM equipos INNER JOIN estado ON equipos.fkestMant = estado.id";
        try {
            cn = conectar.getConnection();
            st = cn.createStatement();
            rs = st.executeQuery(sql);

            Object[] datos = new Object[11];
            modeloTabla = (DefaultTableModel) tablaDatos.getModel();
            while (rs.next()) {
                datos[0] = rs.getInt("id");
                datos[1] = rs.getString("serie");
                datos[2] = rs.getString("nombre");
                datos[3] = rs.getString("marca");
                datos[4] = rs.getString("modelo");
                datos[5] = rs.getString("espTecni");
                datos[6] = rs.getString("estMant");
                datos[7] = rs.getBigDecimal("precio");
                datos[8] = rs.getInt("cantidad");
                datos[9] = rs.getString("descripcion");
                datos[10] = rs.getString("estado");
                modeloTabla.addRow(datos);
            }
            tablaDatos.setModel(modeloTabla);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void Agregar() {
        String serie = txtSerie.getText();
        String nombre = txtNombre.getText();
        String marca = txtMarca.getText();
        String modelo = txtModelo.getText();
        String idEstado = txtIdEstado.getText();
        BigDecimal precio = BigDecimal.valueOf(((Number) txtPrecio.getValue()).longValue());
        int cantidad = (int) spinner.getValue();
        String descripcion = txtDes.getText();
        String especificaciones = txtEspeci.getText();
        String terminos = "";

        if (buttonSi.isSelected()) {
            terminos = "Si";
        } else if (buttonNo.isSelected()) {
            terminos = "No";
        } else {
            terminos = "N/A";
        }

        if (serie.equals("") || nombre.equals("") || marca.equals("") || modelo.equals("") || idEstado.equals("") || precio == null || cantidad == 0 || descripcion.equals("") || especificaciones.equals("") || terminos.equals("")) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
        } else {
            String sql = "INSERT INTO equipos (serie, nombre, marca, modelo, espTecni, fkestMant, precio, cantidad, descripcion, terminos, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'A')";

            try {
                cn = conectar.getConnection();
                PreparedStatement pst = (PreparedStatement) cn.prepareStatement(sql);

                pst.setString(1, serie);
                pst.setString(2, nombre);
                pst.setString(3, marca);
                pst.setString(4, modelo);
                pst.setString(5, especificaciones); // Cambiado a espTecni, ajusta según tu estructura
                pst.setString(6, idEstado); // Asumiendo que fkestMant es la relación con estado
                pst.setBigDecimal(7, precio);
                pst.setInt(8, cantidad);
                pst.setString(9, descripcion);
                pst.setString(10, terminos);

                int resultado = pst.executeUpdate();

                if (resultado > 0) {
                    JOptionPane.showMessageDialog(null, "Registro insertado correctamente");
                    limpiar();
                    listar(); // Actualizar la tabla después de insertar
                } else {
                    JOptionPane.showMessageDialog(null, "Error al insertar el registro");
                }
                limpiarTabla(modeloTabla);
            } catch (HeadlessException | SQLException e) {
            }
        }
    }

    void limpiarTabla(DefaultTableModel modelo1) {
        for (int i = 0; i < tablaDatos.getRowCount(); i++) {
            modeloTabla.removeRow(i);
            i = i - 1;
        }
    }

    void Eliminar() {
        String sql = "delete from equipos where Id=" + id;
        int fila = tablaDatos.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Usuario no Seleccionado");
        } else {
            try {
                cn = conectar.getConnection();
                st = cn.createStatement();
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Usuario Eliminado");
                limpiarTabla(modeloTabla);

            } catch (Exception e) {

            }
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtSerie = new javax.swing.JTextField();
        txtMarca = new javax.swing.JTextField();
        txtModelo = new javax.swing.JTextField();
        comboEstado = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDes = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtEspeci = new javax.swing.JTextArea();
        txtPrecio = new javax.swing.JFormattedTextField();
        txtIdEstado = new javax.swing.JTextField();
        spinner = new javax.swing.JSpinner();
        jLabel12 = new javax.swing.JLabel();
        buttonSi = new javax.swing.JRadioButton();
        buttonNo = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnAgregar_IsaelFatama = new javax.swing.JButton();
        btnModificar_IsaelFatama = new javax.swing.JButton();
        btnEliminar_IsaelFatama = new javax.swing.JButton();
        btnLimpiar__IsaelFatama = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaDatos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Crud_09");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos"));

        jLabel3.setText("ID:");

        jLabel4.setText("Serie:");

        jLabel5.setText("Marca:");

        jLabel6.setText("Modelo:");

        jLabel7.setText("Estado:");

        jLabel8.setText("Precio:");

        jLabel9.setText("Descripción:");

        jLabel10.setText("Especificaciones:");

        jLabel11.setText("Cantidad:");

        comboEstado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboEstadoItemStateChanged(evt);
            }
        });

        txtDes.setColumns(20);
        txtDes.setRows(5);
        jScrollPane2.setViewportView(txtDes);

        txtEspeci.setColumns(20);
        txtEspeci.setRows(5);
        jScrollPane3.setViewportView(txtEspeci);

        txtPrecio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        jLabel12.setText("Términos y condiciones");

        buttonSi.setText("Si");
        buttonSi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSiActionPerformed(evt);
            }
        });

        buttonNo.setText("No");
        buttonNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNoActionPerformed(evt);
            }
        });

        jLabel13.setText("Nombre");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addComponent(jScrollPane2)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonSi, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonNo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(comboEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtId, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtSerie, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtMarca, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtModelo, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtPrecio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtIdEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(spinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtId, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtModelo)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(spinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(buttonSi)
                    .addComponent(buttonNo))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Acciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N

        btnAgregar_IsaelFatama.setText("Agregar");
        btnAgregar_IsaelFatama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregar_IsaelFatamaActionPerformed(evt);
            }
        });

        btnModificar_IsaelFatama.setText("Modificar");
        btnModificar_IsaelFatama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificar_IsaelFatamaActionPerformed(evt);
            }
        });

        btnEliminar_IsaelFatama.setText("Eliminar");
        btnEliminar_IsaelFatama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminar_IsaelFatamaActionPerformed(evt);
            }
        });

        btnLimpiar__IsaelFatama.setText("Limpiar");
        btnLimpiar__IsaelFatama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiar__IsaelFatamaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnModificar_IsaelFatama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAgregar_IsaelFatama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEliminar_IsaelFatama, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnLimpiar__IsaelFatama, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(60, 60, 60))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar_IsaelFatama)
                    .addComponent(btnEliminar_IsaelFatama))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificar_IsaelFatama)
                    .addComponent(btnLimpiar__IsaelFatama))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("H231S2_09");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Estudiante"));
        jPanel3.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("09_IsaelFatama");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de los equipos"));

        tablaDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Serie", "Nombre", "Marca", "Modelo", "Especificaciones", "Estado - Equipo", "Precio", "Cantidad", "Descripcion", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, true, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaDatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaDatosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaDatos);
        if (tablaDatos.getColumnModel().getColumnCount() > 0) {
            tablaDatos.getColumnModel().getColumn(0).setMinWidth(30);
            tablaDatos.getColumnModel().getColumn(0).setMaxWidth(30);
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 933, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel1)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboEstadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboEstadoItemStateChanged
        consultas.equipos objetoConsulta = new equipos();
        objetoConsulta.mostrarIdcombo(comboEstado, txtIdEstado);
    }//GEN-LAST:event_comboEstadoItemStateChanged

    private void buttonSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSiActionPerformed
        terminos = buttonSi.getText();
    }//GEN-LAST:event_buttonSiActionPerformed

    private void buttonNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNoActionPerformed
        terminos = buttonNo.getText();
    }//GEN-LAST:event_buttonNoActionPerformed

    private void btnAgregar_IsaelFatamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregar_IsaelFatamaActionPerformed
        Agregar();
        listar();
    }//GEN-LAST:event_btnAgregar_IsaelFatamaActionPerformed

    private void btnLimpiar__IsaelFatamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiar__IsaelFatamaActionPerformed
        limpiar();
    }//GEN-LAST:event_btnLimpiar__IsaelFatamaActionPerformed

    private void btnEliminar_IsaelFatamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminar_IsaelFatamaActionPerformed
        // TODO add your handling code here:
        Eliminar();
        listar();
        limpiarTabla(modeloTabla);
    }//GEN-LAST:event_btnEliminar_IsaelFatamaActionPerformed

    private void tablaDatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaDatosMouseClicked
        int row = tablaDatos.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Equipo no seleccionado");
        } else {
            id = Integer.parseInt(tablaDatos.getValueAt(row, 0).toString());
            String serie = (String) tablaDatos.getValueAt(row, 1);
            String nombre = (String) tablaDatos.getValueAt(row, 2);
            String marca = (String) tablaDatos.getValueAt(row, 3);
            String modelo = (String) tablaDatos.getValueAt(row, 4);
            String espTecni = (String) tablaDatos.getValueAt(row, 5);
            String estadoEquipo = (String) tablaDatos.getValueAt(row, 6);
            BigDecimal precio = new BigDecimal(tablaDatos.getValueAt(row, 7).toString());
            int cantidad = Integer.parseInt(tablaDatos.getValueAt(row, 8).toString());
            String descripcion = (String) tablaDatos.getValueAt(row, 9);
            String terminos = (String) tablaDatos.getValueAt(row, 10);

// Asigna valores a los campos de texto
            txtId.setText(String.valueOf(id));
            txtSerie.setText(serie);
            txtNombre.setText(nombre);
            txtMarca.setText(marca);
            txtModelo.setText(modelo);
            txtIdEstado.setText(estadoEquipo);
            txtEspeci.setText(espTecni);
            txtPrecio.setValue(precio);
            spinner.setValue(cantidad);
            txtDes.setText(descripcion);

            if (terminos.equals("Si")) {
                buttonSi.setSelected(true);
            } else if (terminos.equals("No")) {
                buttonNo.setSelected(true);
            } else {
            }

        }

    }//GEN-LAST:event_tablaDatosMouseClicked

    private void btnModificar_IsaelFatamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificar_IsaelFatamaActionPerformed
      
        modificar();
        limpiarTabla(modeloTabla);
        listar();
    }//GEN-LAST:event_btnModificar_IsaelFatamaActionPerformed
    void modificar() {
        // Obtén los valores de los campos
        String serie = txtSerie.getText();
        String nombre = txtNombre.getText();
        String marca = txtMarca.getText();
        String modelo = txtModelo.getText();
        String idEstado = txtIdEstado.getText();
        BigDecimal precio = (BigDecimal) txtPrecio.getValue();
        int cantidad = (int) spinner.getValue();
        String descripcion = txtDes.getText();
        String especificaciones = txtEspeci.getText();
        String terminos = "";

        if (buttonSi.isSelected()) {
            terminos = "Si";
        } else if (buttonNo.isSelected()) {
            terminos = "No";
        } else {
            terminos = "N/A";
        }

        String sqlUpdate = "UPDATE equipos SET serie = '" + serie + "', nombre = '" + nombre + "', marca = '" + marca + "', modelo = '" + modelo + "', espTecni = '" + especificaciones + "', fkestMant = '" + idEstado + "', precio = " + precio + ", cantidad = " + cantidad + ", descripcion = '" + descripcion + "', terminos = '" + terminos + "' WHERE id = " + id;

        if (serie.equals("") || nombre.equals("") || marca.equals("") || modelo.equals("") || idEstado.equals("") || precio == null || cantidad == 0 || descripcion.equals("") || especificaciones.equals("") || terminos.equals("")) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
        } else {
            try {
                cn = conectar.getConnection();
                st = cn.createStatement();
                st.executeUpdate(sqlUpdate);
                JOptionPane.showMessageDialog(null, "Equipo Actualizado");
                limpiar();
                listar(); // Actualizar la tabla después de modificar
            } catch (Exception e) {
                System.out.println("ERROR : " + e);
            }
        }
    }

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
            //UIManager.setLookAndFeel(new FlatLightLaf());
            FlatLightLaf.setup();
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Principal().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar_IsaelFatama;
    private javax.swing.JButton btnEliminar_IsaelFatama;
    private javax.swing.JButton btnLimpiar__IsaelFatama;
    private javax.swing.JButton btnModificar_IsaelFatama;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton buttonNo;
    private javax.swing.JRadioButton buttonSi;
    private javax.swing.JComboBox<String> comboEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSpinner spinner;
    private javax.swing.JTable tablaDatos;
    private javax.swing.JTextArea txtDes;
    private javax.swing.JTextArea txtEspeci;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdEstado;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JFormattedTextField txtPrecio;
    private javax.swing.JTextField txtSerie;
    // End of variables declaration//GEN-END:variables
}
