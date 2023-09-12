package ed.mx.views;

import ed.mx.controller.HuespedesController;
import ed.mx.controller.ReservasController;
import ed.mx.modelo.Reserva;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;
import java.util.Optional;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

    private JPanel contentPane;
    private JTextField txtBuscar;
    private JTable tbHuespedes;
    private JTable tbReservas;
    private DefaultTableModel modelo;
    private DefaultTableModel modeloHuesped;
    private JLabel labelAtras;
    private JLabel labelExit;
    int xMouse, yMouse;
    private String usuario;
    HuespedesController hc;
    ReservasController rc;
    private final int indexReservas = 0;
    private final int indexHuespedes = 1;


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Busqueda frame = new Busqueda("admin");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Busqueda(String usuario) {
        this.usuario = usuario;
        hc = new HuespedesController();
        rc = new ReservasController();


        setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 910, 571);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        setUndecorated(true);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(536, 127, 193, 31);
        txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contentPane.add(txtBuscar);
        txtBuscar.setColumns(10);
        eventoTeclado();


        JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
        lblNewLabel_4.setForeground(new Color(12, 138, 199));
        lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
        lblNewLabel_4.setBounds(331, 62, 280, 42);
        contentPane.add(lblNewLabel_4);

        JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
        panel.setBackground(new Color(12, 138, 199));
        panel.setFont(new Font("Roboto", Font.PLAIN, 16));
        panel.setBounds(20, 169, 865, 328);
        contentPane.add(panel);


        tbReservas = new JTable();
        tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
        modelo = (DefaultTableModel) tbReservas.getModel();
        modelo.addColumn("Numero de Reserva");
        modelo.addColumn("Fecha Check In");
        modelo.addColumn("Fecha Check Out");
        modelo.addColumn("Valor");
        modelo.addColumn("Forma de Pago");
        JScrollPane scroll_table = new JScrollPane(tbReservas);
        panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table, null);
        scroll_table.setVisible(true);


        tbHuespedes = new JTable();
        tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
        modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
        modeloHuesped.addColumn("Número de Huesped");
        modeloHuesped.addColumn("Nombre");
        modeloHuesped.addColumn("Apellido");
        modeloHuesped.addColumn("Fecha de Nacimiento");
        modeloHuesped.addColumn("Nacionalidad");
        modeloHuesped.addColumn("Telefono");
        modeloHuesped.addColumn("Número de Reserva");
        JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);

        panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
        scroll_tableHuespedes.setVisible(true);

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
        lblNewLabel_2.setBounds(56, 51, 104, 107);
        contentPane.add(lblNewLabel_2);

        JPanel header = new JPanel();
        header.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                headerMouseDragged(e);

            }
        });
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                headerMousePressed(e);
            }
        });
        header.setLayout(null);
        header.setBackground(Color.WHITE);
        header.setBounds(0, 0, 910, 36);
        contentPane.add(header);

        JPanel btnAtras = new JPanel();
        btnAtras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario user = new MenuUsuario(usuario);
                user.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnAtras.setBackground(new Color(12, 138, 199));
                labelAtras.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAtras.setBackground(Color.white);
                labelAtras.setForeground(Color.black);
            }
        });
        btnAtras.setLayout(null);
        btnAtras.setBackground(Color.WHITE);
        btnAtras.setBounds(0, 0, 53, 36);
        header.add(btnAtras);

        labelAtras = new JLabel("<");
        labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
        labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
        labelAtras.setBounds(0, 0, 53, 36);
        btnAtras.add(labelAtras);

        JPanel btnexit = new JPanel();
        btnexit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario user = new MenuUsuario(usuario);
                user.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
                btnexit.setBackground(Color.red);
                labelExit.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
                btnexit.setBackground(Color.white);
                labelExit.setForeground(Color.black);
            }
        });
        btnexit.setLayout(null);
        btnexit.setBackground(Color.WHITE);
        btnexit.setBounds(857, 0, 53, 36);
        header.add(btnexit);

        labelExit = new JLabel("X");
        labelExit.setHorizontalAlignment(SwingConstants.CENTER);
        labelExit.setForeground(Color.BLACK);
        labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelExit.setBounds(0, 0, 53, 36);
        btnexit.add(labelExit);

        JLabel currentUser = new JLabel(usuario);
        currentUser.setBounds(780,0,53,36);
        currentUser.setLayout(null);
        currentUser.setHorizontalAlignment(SwingConstants.CENTER);
        currentUser.setFont(new Font ("Roboto", Font.PLAIN, 14));
        header.add(currentUser);

        JLabel imgUser = new JLabel("");
        imgUser.setBounds(720,0,34,34);
        imgUser.setHorizontalAlignment(SwingConstants.CENTER);
//        imgUser.setBorder();
        imgUser.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/login.png")));
        header.add(imgUser);

        JSeparator separator_1_2 = new JSeparator();
        separator_1_2.setForeground(new Color(12, 138, 199));
        separator_1_2.setBackground(new Color(12, 138, 199));
        separator_1_2.setBounds(539, 159, 193, 2);
        contentPane.add(separator_1_2);

        JPanel btnbuscar = new JPanel();
        btnbuscar.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (panel.getSelectedIndex() == indexReservas &&
                        (txtBuscar.getText().isEmpty() || txtBuscar.getText() == null || txtBuscar.getText() == "")) {
                    limpiarTabla(modelo);
                    cargarTablaReservasPorUsuario();
                    System.out.println("reservas");
                } else if (panel.getSelectedIndex() == indexHuespedes &&
                        (txtBuscar.getText().isEmpty() || txtBuscar.getText() == null || txtBuscar.getText() == "")) {
                    limpiarTabla(modeloHuesped);
                    cargarTablaHuespedesPorUsuario();
                    System.out.println("huespedes");
                } else {
                    if (panel.getSelectedIndex() == indexReservas) {
                        limpiarTabla(modelo);
                        cargarTablaReservasPorId(txtBuscar);
                    } else {
                        limpiarTabla(modeloHuesped);
                        cargarTablaHuespedesPorId(txtBuscar);
                    }
                }

            }
        });
        btnbuscar.setLayout(null);
        btnbuscar.setBackground(new Color(12, 138, 199));
        btnbuscar.setBounds(748, 125, 122, 35);
        btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnbuscar);

        JLabel lblBuscar = new JLabel("BUSCAR");
        lblBuscar.setBounds(0, 0, 122, 35);
        btnbuscar.add(lblBuscar);
        lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
        lblBuscar.setForeground(Color.WHITE);
        lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

        JPanel btnEditar = new JPanel();
        btnEditar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(panel.getSelectedIndex() == indexHuespedes){
                    modificarHuesped(tbHuespedes, modeloHuesped);
                } else if(panel.getSelectedIndex() == indexReservas){
                    modificarReserva();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }


            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        btnEditar.setLayout(null);
        btnEditar.setBackground(new Color(12, 138, 199));
        btnEditar.setBounds(635, 508, 122, 35);
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnEditar);

        JLabel lblEditar = new JLabel("EDITAR");
        lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEditar.setForeground(Color.WHITE);
        lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblEditar.setBounds(0, 0, 122, 35);
        btnEditar.add(lblEditar);

        JPanel btnEliminar = new JPanel();
        btnEliminar.setLayout(null);
        btnEliminar.setBackground(new Color(12, 138, 199));
        btnEliminar.setBounds(767, 508, 122, 35);
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnEliminar);

        JLabel lblEliminar = new JLabel("ELIMINAR");
        lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEliminar.setForeground(Color.WHITE);
        lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblEliminar.setBounds(0, 0, 122, 35);
        btnEliminar.add(lblEliminar);
        setResizable(false);
    }

    //Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
    private void headerMousePressed(MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }

    private void headerMouseDragged(MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }

    private void cargarTablaReservasPorUsuario() {
        var reservas = rc.reservaList(usuario);
        for (Reserva reserva : reservas) {
            modelo.addRow(new Object[]{
                    reserva.getId(),
                    reserva.getFechaE(),
                    reserva.getFechaS(),
                    reserva.getValor(),
                    reserva.getForma_de_pago()
            });

        }
    }

    private void cargarTablaHuespedesPorUsuario() {
        var huespedes = hc.huespedList(usuario);
        huespedes.forEach(huesped -> modeloHuesped.addRow(new Object[]{
                huesped.getId(),
                huesped.getNombre(),
                huesped.getApellido(),
                huesped.getFechaNacimiento(),
                huesped.getNacionalidad(),
                huesped.getTelefono(),
                huesped.getIdReserva()
        }));
    }

    private void limpiarTabla(DefaultTableModel model) {
        for (int i = 0; i < model.getRowCount(); i++) {
            model.removeRow(i);
            i -= 1;
        }
    }

    private void cargarTablaReservasPorId(JTextField idTexto) {
        Long id = Long.valueOf(idTexto.getText());
        var reservas = rc.listarPorId(id, usuario);
        if (reservas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Reserva Inexistente o Reserva No Asociada a este Usuario");
        } else {
            reservas.forEach(reserva -> {
                modelo.addRow(new Object[]{
                        reserva.getId(),
                        reserva.getFechaE(),
                        reserva.getFechaS(),
                        reserva.getValor(),
                        reserva.getForma_de_pago()
                });

            });
        }
    }

    private void cargarTablaHuespedesPorId(JTextField idTexto) {
        Long id = Long.valueOf(idTexto.getText());
        var huespedes = hc.listarPorId(id, usuario);
        if (huespedes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Huesped Inexistente o Huesped No Asociado a este Usuario");
        } else {
            huespedes.forEach(huesped -> {
                modeloHuesped.addRow(new Object[]{
                        huesped.getId(),
                        huesped.getNombre(),
                        huesped.getApellido(),
                        huesped.getFechaNacimiento(),
                        huesped.getNacionalidad(),
                        huesped.getTelefono(),
                        huesped.getIdReserva()
                });
            });
        }
    }

    private boolean tieneFilaElegida(JTable tabla) {
        return tabla.getSelectedRowCount() == 0 || tabla.getSelectedColumnCount() == 0;
    }
    private void modificarHuesped(JTable table, DefaultTableModel modelo) {
        if (tieneFilaElegida(table)) {
            JOptionPane.showMessageDialog(this, "Por favor, elije un item");
            return;
        }
        Optional.ofNullable(modelo.getValueAt(table.getSelectedRow(), table.getSelectedColumn()))
                .ifPresentOrElse(fila -> {
                    try {
                        Long id = Long.valueOf(modelo.getValueAt(table.getSelectedRow(), 0).toString());
                        String nombre = modelo.getValueAt(table.getSelectedRow(), 1).toString();
                        String apellido = modelo.getValueAt(table.getSelectedRow(), 2).toString();
                        Date fechaNacimiento = java.sql.Date.valueOf(modelo.getValueAt(table.getSelectedRow(), 3).toString());
                        String nacionalidad = modelo.getValueAt(table.getSelectedRow(), 4).toString();
                        String telefono = modelo.getValueAt(table.getSelectedRow(), 5).toString();

                        try {
                            hc.modificar(id, nombre, apellido, fechaNacimiento, nacionalidad, telefono);
                            JOptionPane.showMessageDialog(this, "Modificado Con Exito!");
                        } catch (NullPointerException e) {
                            JOptionPane.showMessageDialog(this, "Usuario No encontrado");
                        }
                    } catch (IllegalArgumentException e){
                        JOptionPane.showMessageDialog(this, "Ingresa una Fecha valida");
                    }
                }, () -> System.out.println("Elige pa"));
    }

    private void modificarReserva(){
        if (tieneFilaElegida(tbReservas)) {
            JOptionPane.showMessageDialog(this, "Por favor, elije un item");
            return;
        }
        Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
                .ifPresentOrElse(fila->{
                    try {
                        Long id = Long.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
                        Date fechaE = java.sql.Date.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 1).toString());
                        Date fechaS = java.sql.Date.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 2).toString());
                        long dias;
                        BigDecimal valor;
                        if (fechaE.before(fechaS)) {
                            dias = (fechaS.getTime() - fechaE.getTime()) / 172800;
                            valor = new BigDecimal(dias);
                            try {
                                rc.modificar(id, fechaE, fechaS, valor);
                                JOptionPane.showMessageDialog(this, "Nuevo valor para tu reserva: " + valor);

                            } catch (NullPointerException | IllegalArgumentException e) {
                                JOptionPane.showMessageDialog(this, "Ingresa Datos validos");
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Ingresa una Fecha valida");
                        }
                    }catch (IllegalArgumentException e){
                        JOptionPane.showMessageDialog(this, "Ingresa Datos validos");
                    }

                }, () -> System.out.println("Elige pa"));


    }


    private void eventoTeclado() {
        KeyListener event = new KeyListener() {

            @Override
            public void keyTyped(KeyEvent evt) {
                int character = evt.getKeyChar();
                if ((!(character >= 48 && character <= 57))
                        && (character != KeyEvent.VK_BACK_SPACE)
                        && (character != '.' || txtBuscar.getText().contains("."))) {
                    evt.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent evt) {
                int character = evt.getKeyChar();
                if ((!(character >= 48 && character <= 57))
                        && (character != KeyEvent.VK_BACK_SPACE)
                        && (character != '.' || txtBuscar.getText().contains("."))) {
                    evt.consume();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

        };
        txtBuscar.addKeyListener(event);

    }
}

