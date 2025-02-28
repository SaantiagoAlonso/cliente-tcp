//package co.scastillos.vista;
//
//import co.scastillos.dto.ConsultaSaldoDto;
//import co.scastillos.dto.ConsultaMovDto;
//import co.scastillos.dto.SolicitudDto;
//import co.scastillos.dto.TransferenciaDto;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import javax.swing.*;
//import java.awt.*;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.Socket;
//
//public class ClienteGUI extends JFrame {
//    private JTextField inputDato, txtCuentaMovimientos;
//    private JTextArea outputArea;
//    private JRadioButton rbCedula, rbCuenta;
//    private JButton btnConectar, btnConsultarSaldo, btnTransferencia, btnConsultarMovimientos;
//    private JTextField txtCuentaRemitente, txtCuentaDestino, txtValorTransferencia;
//    private Socket socket;
//    private PrintWriter writer;
//    private BufferedReader reader;
//
//    public ClienteGUI() {
//        setTitle("Cliente Bancario");
//        setSize(600, 550);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new FlowLayout());
//
//        // Componentes para consulta de saldo
//        rbCedula = new JRadioButton("Buscar por Cédula");
//        rbCuenta = new JRadioButton("Buscar por Cuenta");
//        ButtonGroup grupo = new ButtonGroup();
//        grupo.add(rbCedula);
//        grupo.add(rbCuenta);
//
//        inputDato = new JTextField(20);
//        btnConsultarSaldo = new JButton("Consultar Saldo");
//
//        // Componentes para transferencia
//        txtCuentaRemitente = new JTextField(15);
//        txtCuentaDestino = new JTextField(15);
//        txtValorTransferencia = new JTextField(10);
//        btnTransferencia = new JButton("Realizar Transferencia");
//
//        // Componentes para consulta de movimientos
//        txtCuentaMovimientos = new JTextField(15);
//        btnConsultarMovimientos = new JButton("Consultar Movimientos");
//
//        // Área de salida
//        outputArea = new JTextArea(15, 50);
//        outputArea.setEditable(false);
//
//        // Botón de conexión
//        btnConectar = new JButton("Conectar al Servidor");
//
//        // Agregar componentes al frame
//        add(rbCedula);
//        add(rbCuenta);
//        add(inputDato);
//        add(btnConsultarSaldo);
//
//        add(new JLabel("Cuenta Remitente:"));
//        add(txtCuentaRemitente);
//        add(new JLabel("Cuenta Destino:"));
//        add(txtCuentaDestino);
//        add(new JLabel("Valor:"));
//        add(txtValorTransferencia);
//        add(btnTransferencia);
//
//        add(new JLabel("Cuenta para Movimientos:"));
//        add(txtCuentaMovimientos);
//        add(btnConsultarMovimientos);
//
//        add(btnConectar);
//        add(new JScrollPane(outputArea));
//
//        // Eventos
//        btnConectar.addActionListener(e -> conectarServidor());
//        btnConsultarSaldo.addActionListener(e -> consultarSaldo());
//        btnTransferencia.addActionListener(e -> enviarTransferencia());
//        btnConsultarMovimientos.addActionListener(e -> consultarMovimientos());
//    }
//
//    private void conectarServidor() {
//        try {
//            socket = new Socket("localhost", 5000);
//            writer = new PrintWriter(socket.getOutputStream(), true);
//            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            outputArea.append("Conectado al servidor\n");
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            outputArea.append("Error al conectar con el servidor\n");
//        }
//    }
//
//    private void consultarSaldo() {
//        try {
//            if (socket == null || socket.isClosed()) {
//                outputArea.append("Primero conecta al servidor\n");
//                return;
//            }
//
//            String tipoBusqueda = rbCedula.isSelected() ? "cedula" : "cuenta";
//            int valor = Integer.parseInt(inputDato.getText());
//
//            ConsultaSaldoDto consulta = new ConsultaSaldoDto(tipoBusqueda, valor);
//            SolicitudDto solicitud = new SolicitudDto("consultar", consulta);
//
//            enviarSolicitud(solicitud);
//        } catch (IOException | NumberFormatException ex) {
//            ex.printStackTrace();
//            outputArea.append("Error al enviar la consulta\n");
//        }
//    }
//
//    private void enviarTransferencia() {
//        try {
//            if (socket == null || socket.isClosed()) {
//                outputArea.append("Primero conecta al servidor\n");
//                return;
//            }
//
//            int cuentaRemitente = Integer.parseInt(txtCuentaRemitente.getText());
//            int cuentaDestino = Integer.parseInt(txtCuentaDestino.getText());
//            double valor = Double.parseDouble(txtValorTransferencia.getText());
//
//            TransferenciaDto transferencia = new TransferenciaDto(cuentaRemitente, cuentaDestino, valor);
//            SolicitudDto solicitud = new SolicitudDto("transferencia", transferencia);
//
//            enviarSolicitud(solicitud);
//        } catch (IOException | NumberFormatException ex) {
//            ex.printStackTrace();
//            outputArea.append("Error al enviar la transferencia\n");
//        }
//    }
//
//    private void consultarMovimientos() {
//        try {
//            if (socket == null || socket.isClosed()) {
//                outputArea.append("Primero conecta al servidor\n");
//                return;
//            }
//
//            int cuenta = Integer.parseInt(txtCuentaMovimientos.getText());
//            ConsultaMovDto consulta = new ConsultaMovDto(cuenta,null,null);
//            SolicitudDto solicitud = new SolicitudDto("movimientos", consulta);
//
//            enviarSolicitud(solicitud);
//        } catch (IOException | NumberFormatException ex) {
//            ex.printStackTrace();
//            outputArea.append("Error al consultar movimientos\n");
//        }
//    }
//
//    private void enviarSolicitud(SolicitudDto solicitud) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = objectMapper.writeValueAsString(solicitud);
//
//        writer.println(json);
//        outputArea.append("Enviado: " + json + "\n");
//
//        String respuesta = reader.readLine();
//        outputArea.append("Respuesta del servidor: " + respuesta + "\n");
//    }
//}


package co.scastillos.vista;

import co.scastillos.dto.ConsultaSaldoDto;
import co.scastillos.dto.ConsultaMovDto;
import co.scastillos.dto.SolicitudDto;
import co.scastillos.dto.TransferenciaDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteGUI extends JFrame {
    private JTextField inputDato, txtCuentaMovimientos;
    private JTextArea outputArea;
    private JRadioButton rbCedula, rbCuenta;
    private JButton btnConectar, btnConsultarSaldo, btnTransferencia, btnConsultarMovimientos;
    private JTextField txtCuentaRemitente, txtCuentaDestino, txtValorTransferencia;
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;

    public ClienteGUI() {
        setTitle("Cliente Bancario");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);

        // Panel superior: Conexión
        JPanel connectionPanel = new JPanel();
        connectionPanel.setBorder(new TitledBorder("Conexión al Servidor"));
        btnConectar = new JButton("Conectar al Servidor");
        connectionPanel.add(btnConectar);
        mainPanel.add(connectionPanel, BorderLayout.NORTH);

        // Panel central: Funcionalidades
        JPanel centerPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Consulta de Saldo
        JPanel saldoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        saldoPanel.setBorder(new TitledBorder("Consulta de Saldo"));
        rbCedula = new JRadioButton("Buscar por Cédula");
        rbCuenta = new JRadioButton("Buscar por Cuenta");
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(rbCedula);
        grupo.add(rbCuenta);
        inputDato = new JTextField(15);
        btnConsultarSaldo = new JButton("Consultar Saldo");
        saldoPanel.add(rbCedula);
        saldoPanel.add(rbCuenta);
        saldoPanel.add(inputDato);
        saldoPanel.add(btnConsultarSaldo);
        centerPanel.add(saldoPanel);

        // Transferencia
        JPanel transferenciaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        transferenciaPanel.setBorder(new TitledBorder("Realizar Transferencia"));
        txtCuentaRemitente = new JTextField(15);
        txtCuentaDestino = new JTextField(15);
        txtValorTransferencia = new JTextField(10);
        btnTransferencia = new JButton("Realizar Transferencia");
        transferenciaPanel.add(new JLabel("Cuenta Remitente:"));
        transferenciaPanel.add(txtCuentaRemitente);
        transferenciaPanel.add(new JLabel("Cuenta Destino:"));
        transferenciaPanel.add(txtCuentaDestino);
        transferenciaPanel.add(new JLabel("Valor:"));
        transferenciaPanel.add(txtValorTransferencia);
        transferenciaPanel.add(btnTransferencia);
        centerPanel.add(transferenciaPanel);

        // Consulta de Movimientos
        JPanel movimientosPanel = new JPanel(new BorderLayout());
        movimientosPanel.setBorder(new TitledBorder("Consulta de Movimientos"));
        txtCuentaMovimientos = new JTextField(15);
        btnConsultarMovimientos = new JButton("Consultar Movimientos");
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputPanel.add(new JLabel("Cuenta:"));
        inputPanel.add(txtCuentaMovimientos);
        inputPanel.add(btnConsultarMovimientos);
        movimientosPanel.add(inputPanel, BorderLayout.NORTH);

        // Área de salida para movimientos
        outputArea = new JTextArea(5, 40);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        movimientosPanel.add(scrollPane, BorderLayout.CENTER);
        centerPanel.add(movimientosPanel);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Eventos
        btnConectar.addActionListener(e -> conectarServidor());
        btnConsultarSaldo.addActionListener(e -> consultarSaldo());
        btnTransferencia.addActionListener(e -> enviarTransferencia());
        btnConsultarMovimientos.addActionListener(e -> consultarMovimientos());
    }

    private void conectarServidor() {
        try {
            socket = new Socket("localhost", 5000);
            writer = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outputArea.append("Conectado al servidor\n");
        } catch (IOException ex) {
            ex.printStackTrace();
            outputArea.append("Error al conectar con el servidor\n");
        }
    }

    private void consultarSaldo() {
        try {
            if (socket == null || socket.isClosed()) {
                outputArea.append("Primero conecta al servidor\n");
                return;
            }
            String tipoBusqueda = rbCedula.isSelected() ? "cedula" : "cuenta";
            int valor = Integer.parseInt(inputDato.getText());
            ConsultaSaldoDto consulta = new ConsultaSaldoDto(tipoBusqueda, valor);
            SolicitudDto solicitud = new SolicitudDto("consultar", consulta);
            enviarSolicitud(solicitud);
        } catch (IOException | NumberFormatException ex) {
            ex.printStackTrace();
            outputArea.append("Error al enviar la consulta\n");
        }
    }

    private void enviarTransferencia() {
        try {
            if (socket == null || socket.isClosed()) {
                outputArea.append("Primero conecta al servidor\n");
                return;
            }
            int cuentaRemitente = Integer.parseInt(txtCuentaRemitente.getText());
            int cuentaDestino = Integer.parseInt(txtCuentaDestino.getText());
            double valor = Double.parseDouble(txtValorTransferencia.getText());
            TransferenciaDto transferencia = new TransferenciaDto(cuentaRemitente, cuentaDestino, valor);
            SolicitudDto solicitud = new SolicitudDto("transferencia", transferencia);
            enviarSolicitud(solicitud);
        } catch (IOException | NumberFormatException ex) {
            ex.printStackTrace();
            outputArea.append("Error al enviar la transferencia\n");
        }
    }

    private void consultarMovimientos() {
        try {
            if (socket == null || socket.isClosed()) {
                outputArea.append("Primero conecta al servidor\n");
                return;
            }
            int cuenta = Integer.parseInt(txtCuentaMovimientos.getText());
            ConsultaMovDto consulta = new ConsultaMovDto(cuenta, null, null);
            SolicitudDto solicitud = new SolicitudDto("movimientos", consulta);
            enviarSolicitud(solicitud);
        } catch (IOException | NumberFormatException ex) {
            ex.printStackTrace();
            outputArea.append("Error al consultar movimientos\n");
        }
    }

    private void enviarSolicitud(SolicitudDto solicitud) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(solicitud);
        writer.println(json);
        outputArea.append("Enviado: " + json + "\n");
        String respuesta = reader.readLine();
        outputArea.append("Respuesta del servidor: " + respuesta + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ClienteGUI gui = new ClienteGUI();
            gui.setVisible(true);
        });
    }
}


//package co.scastillos.vista;
//
//import co.scastillos.dto.*;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import javax.swing.*;
//import javax.swing.border.TitledBorder;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.Socket;
//
//public class ClienteGUI extends JFrame {
//    private JTextField inputDato, txtCuentaMovimientos;
//    private JTextArea outputArea;
//    private JRadioButton rbCedula, rbCuenta;
//    private JButton btnConectar, btnConsultarSaldo, btnTransferencia, btnConsultarMovimientos;
//    private JTextField txtCuentaRemitente, txtCuentaDestino, txtValorTransferencia;
//    private Socket socket;
//    private PrintWriter writer;
//    private BufferedReader reader;
//    private JTable movimientosTable;
//    private DefaultTableModel tableModel;
//
//    public ClienteGUI() {
//        setTitle("Cliente Bancario");
//        setSize(800, 600);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new BorderLayout());
//
//        // Panel principal
//        JPanel mainPanel = new JPanel(new BorderLayout());
//        add(mainPanel);
//
//        // Panel superior: Conexión
//        JPanel connectionPanel = new JPanel();
//        connectionPanel.setBorder(new TitledBorder("Conexión al Servidor"));
//        btnConectar = new JButton("Conectar al Servidor");
//        connectionPanel.add(btnConectar);
//        mainPanel.add(connectionPanel, BorderLayout.NORTH);
//
//        // Panel central: Funcionalidades
//        JPanel centerPanel = new JPanel(new GridLayout(3, 1, 10, 10));
//        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//
//        // Consulta de Saldo
//        JPanel saldoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        saldoPanel.setBorder(new TitledBorder("Consulta de Saldo"));
//        rbCedula = new JRadioButton("Buscar por Cédula");
//        rbCuenta = new JRadioButton("Buscar por Cuenta");
//        ButtonGroup grupo = new ButtonGroup();
//        grupo.add(rbCedula);
//        grupo.add(rbCuenta);
//        inputDato = new JTextField(15);
//        btnConsultarSaldo = new JButton("Consultar Saldo");
//        saldoPanel.add(rbCedula);
//        saldoPanel.add(rbCuenta);
//        saldoPanel.add(inputDato);
//        saldoPanel.add(btnConsultarSaldo);
//        centerPanel.add(saldoPanel);
//
//        // Transferencia
//        JPanel transferenciaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        transferenciaPanel.setBorder(new TitledBorder("Realizar Transferencia"));
//        txtCuentaRemitente = new JTextField(15);
//        txtCuentaDestino = new JTextField(15);
//        txtValorTransferencia = new JTextField(10);
//        btnTransferencia = new JButton("Realizar Transferencia");
//        transferenciaPanel.add(new JLabel("Cuenta Remitente:"));
//        transferenciaPanel.add(txtCuentaRemitente);
//        transferenciaPanel.add(new JLabel("Cuenta Destino:"));
//        transferenciaPanel.add(txtCuentaDestino);
//        transferenciaPanel.add(new JLabel("Valor:"));
//        transferenciaPanel.add(txtValorTransferencia);
//        transferenciaPanel.add(btnTransferencia);
//        centerPanel.add(transferenciaPanel);
//
//        // Consulta de Movimientos
//        JPanel movimientosPanel = new JPanel(new BorderLayout());
//        movimientosPanel.setBorder(new TitledBorder("Consulta de Movimientos"));
//        txtCuentaMovimientos = new JTextField(15);
//        btnConsultarMovimientos = new JButton("Consultar Movimientos");
//        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        inputPanel.add(new JLabel("Cuenta:"));
//        inputPanel.add(txtCuentaMovimientos);
//        inputPanel.add(btnConsultarMovimientos);
//        movimientosPanel.add(inputPanel, BorderLayout.NORTH);
//
//        // Tabla de movimientos
//        String[] columnNames = {"Fecha", "Tipo", "Monto"};
//        tableModel = new DefaultTableModel(columnNames, 0);
//        movimientosTable = new JTable(tableModel);
//        JScrollPane scrollPane = new JScrollPane(movimientosTable);
//        movimientosPanel.add(scrollPane, BorderLayout.CENTER);
//        centerPanel.add(movimientosPanel);
//
//        mainPanel.add(centerPanel, BorderLayout.CENTER);
//
//        // Panel inferior: Área de salida
//        outputArea = new JTextArea(5, 50);
//        outputArea.setEditable(false);
//        JScrollPane outputScrollPane = new JScrollPane(outputArea);
//        mainPanel.add(outputScrollPane, BorderLayout.SOUTH);
//
//        // Eventos
//        btnConectar.addActionListener(e -> conectarServidor());
//        btnConsultarSaldo.addActionListener(e -> consultarSaldo());
//        btnTransferencia.addActionListener(e -> enviarTransferencia());
//        btnConsultarMovimientos.addActionListener(e -> consultarMovimientos());
//    }
//
//    private void conectarServidor() {
//        try {
//            socket = new Socket("localhost", 5000);
//            writer = new PrintWriter(socket.getOutputStream(), true);
//            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            outputArea.append("Conectado al servidor\n");
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            outputArea.append("Error al conectar con el servidor\n");
//        }
//    }
//
//    private void consultarSaldo() {
//        try {
//            if (socket == null || socket.isClosed()) {
//                outputArea.append("Primero conecta al servidor\n");
//                return;
//            }
//            String tipoBusqueda = rbCedula.isSelected() ? "cedula" : "cuenta";
//            int valor = Integer.parseInt(inputDato.getText());
//            ConsultaSaldoDto consulta = new ConsultaSaldoDto(tipoBusqueda, valor);
//            SolicitudDto solicitud = new SolicitudDto("consultar", consulta);
//            enviarSolicitud(solicitud);
//        } catch (IOException | NumberFormatException ex) {
//            ex.printStackTrace();
//            outputArea.append("Error al enviar la consulta\n");
//        }
//    }
//
//    private void enviarTransferencia() {
//        try {
//            if (socket == null || socket.isClosed()) {
//                outputArea.append("Primero conecta al servidor\n");
//                return;
//            }
//            int cuentaRemitente = Integer.parseInt(txtCuentaRemitente.getText());
//            int cuentaDestino = Integer.parseInt(txtCuentaDestino.getText());
//            double valor = Double.parseDouble(txtValorTransferencia.getText());
//            TransferenciaDto transferencia = new TransferenciaDto(cuentaRemitente, cuentaDestino, valor);
//            SolicitudDto solicitud = new SolicitudDto("transferencia", transferencia);
//            enviarSolicitud(solicitud);
//        } catch (IOException | NumberFormatException ex) {
//            ex.printStackTrace();
//            outputArea.append("Error al enviar la transferencia\n");
//        }
//    }
//
//    private void consultarMovimientos() {
//        try {
//            if (socket == null || socket.isClosed()) {
//                outputArea.append("Primero conecta al servidor\n");
//                return;
//            }
//            int cuenta = Integer.parseInt(txtCuentaMovimientos.getText());
//            ConsultaMovDto consulta = new ConsultaMovDto(cuenta, null, null);
//            SolicitudDto solicitud = new SolicitudDto("movimientos", consulta);
//            enviarSolicitud(solicitud);
//        } catch (IOException | NumberFormatException ex) {
//            ex.printStackTrace();
//            outputArea.append("Error al consultar movimientos\n");
//        }
//    }
//
//    private void enviarSolicitud(SolicitudDto solicitud) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = objectMapper.writeValueAsString(solicitud);
//        writer.println(json);
//        outputArea.append("Enviado: " + json + "\n");
//        String respuesta = reader.readLine();
//        outputArea.append("Respuesta del servidor: " + respuesta + "\n");
//
//        // Si la solicitud es de movimientos, actualizar la tabla
//        if ("movimientos".equals(solicitud.getAccion())) {
//            ObjectMapper mapper = new ObjectMapper();
//            ConsultaMovDto movimientos = mapper.readValue(respuesta, ConsultaMovDto.class);
//            actualizarTablaMovimientos(movimientos);
//        }
//    }
//
//    private void actualizarTablaMovimientos(ConsultaMovDto movimientos) {
//        tableModel.setRowCount(0); // Limpiar tabla
//        for (MovimientoDto movimiento : movimientos.getMovimientos()) {
//            tableModel.addRow(new MovimientoDto[]{movimiento});
//        }
//    }
//
//}




