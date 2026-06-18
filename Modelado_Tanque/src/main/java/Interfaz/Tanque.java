/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaz;

import java.awt.BorderLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author andre
 */
public class Tanque extends javax.swing.JFrame {
    // ── Estado de simulación ─────────────────────────────────────────────────
    private double nivelActual = 0;
    private int    contadorDesborde = 3;
    private boolean detenerr    = false;
    private boolean ejecutando = false;
    private boolean desbordado = false;

    // ── Gráfica ──────────────────────────────────────────────────────────────
    private final XYSeries serie = new XYSeries("Altura");
    private int puntoX = 0;

    // ── Timers ───────────────────────────────────────────────────────────────
    private Timer timerPrincipal;
    private Timer timerDesborde;

    // ── Tabla de índices de imagen [entrada(0-2)][salida(0-2)][nivel(0-3)] ──
    // nivel: 0=bajo  1=ideal  2=alto  3=desborde
    private static final int[][][] TABLA_IMAGENES = {
        { {1,10,19,30}, {2,11,20,29}, {3,12,21,28} },  // Abierto entrada
        { {4,13,22,-1}, {5,14,23,-1}, {6,15,24,-1} },  // Medio  entrada
        { {7,16,25,-1}, {8,17,26,-1}, {9,18,27,-1} }   // Cerrado entrada
    };
    public Tanque() {
        initComponents();
        escalarImagen("img/9.png");
        inicializarGrafica();
    }
    private void inicializarGrafica() {
        XYSeriesCollection datos = new XYSeriesCollection(serie);
        JFreeChart grafica = ChartFactory.createXYLineChart(
                "Altura Vs Tiempo", "Tiempo", "Altura", datos);
        ChartPanel panel = new ChartPanel(grafica);
        panel.setPreferredSize(Grafica.getSize());
        Grafica.setLayout(new BorderLayout());
        Grafica.removeAll();
        Grafica.add(panel, BorderLayout.CENTER);
        Grafica.revalidate();
        Grafica.repaint();
    }
    
    // ── Helpers de nivel y válvulas ──────────────────────────────────────────

    /** Devuelve el caudal de una válvula: abierto=1, medio=0.5, cerrado=0 */
    private double getValorValvula(javax.swing.JRadioButton abierto,
                                   javax.swing.JRadioButton medio) {
        if (abierto.isSelected()) return 1.0;
        if (medio  .isSelected()) return 0.5;
        return 0.0;
    }

    /** Clasifica el nivel actual: 0=bajo 1=ideal 2=alto 3=desborde */
    private int clasificarNivel(int ideal, int max) {
        if (nivelActual > max * 1.1)           return 3;
        if (nivelActual < ideal - ideal / 2.0) return 0;
        if (nivelActual < ideal + 2)           return 1;
        return 2;
    }

    /** Índice de entrada (fila en la tabla): 0=abierto 1=medio 2=cerrado */
    private int indiceEntrada() {
        if (Abierto_Entrada.isSelected()) return 0;
        if (Medio_Entrada  .isSelected()) return 1;
        return 2;
    }

    /** Índice de salida (columna en la tabla): 0=abierto 1=medio 2=cerrado */
    private int indiceSalida() {
        if (Abierto_Salida.isSelected()) return 0;
        if (Medio_Salida  .isSelected()) return 1;
        return 2;
    }

    // ── Imagen del tanque ────────────────────────────────────────────────────

    private void escalarImagen(String ruta) {
        ImageIcon icono = new ImageIcon(ruta);
        Image escalada  = icono.getImage()
                .getScaledInstance(Tanque.getWidth(), Tanque.getHeight(), Image.SCALE_SMOOTH);
        Tanque.setIcon(new ImageIcon(escalada));
    }

    private void actualizarImagen() {
        int ideal = Integer.parseInt(CantidadIdeal.getText());
        int max   = Integer.parseInt(CantidadMax  .getText());
        int nivel = clasificarNivel(ideal, max);
        int fila  = indiceEntrada();
        int col   = indiceSalida();

        // Las imágenes de desborde sólo existen para entrada ABIERTA; para las
        // demás combinaciones no hay índice de desborde (-1), se ignora.
        int idx = TABLA_IMAGENES[fila][col][nivel];
        if (idx < 0) return;
        escalarImagen("img/" + idx + ".png");
    }

    // ── Lógica de simulación ─────────────────────────────────────────────────

    private void agregarDato() {
        serie.add(puntoX++, nivelActual);
    }

    private void calcularNivel(int canMax) {
        double entradaDelta = getValorValvula(Abierto_Entrada, Medio_Entrada);
        double salidaDelta  = getValorValvula(Abierto_Salida,  Medio_Salida );

        if (nivelActual < canMax * 1.2) nivelActual += entradaDelta;
        if (nivelActual > 0)            nivelActual -= salidaDelta;
        if (nivelActual < 0)            nivelActual  = 0;
    }

    private void manejarDesborde(int canIdeal, int canMax) {
        // Fuerza entrada cerrada y salida abierta para vaciar
        Cerrado_Entrada.setSelected(true);
        Abierto_Salida.setSelected(true);

        // Cuando el tanque esté completamente vacío, termina el modo desborde
        if (nivelActual <= 0) {
            nivelActual = 0;
            desbordado = false;
            mensaje.setText("Tanque vaciado. Puede reiniciar la simulación.");
            detenerSimulacion();
        }
    }

    private void detenerSimulacion() {
        serie.clear();
        automatico.setEnabled(true);
        puntoX      = 0;
        nivelActual = 0;
        timerPrincipal.stop();
    }

    private void iniciarTimerPrincipal(int canIdeal, int canMax) {
        timerPrincipal = new Timer(100, e -> {
            cantidadL.setText(nivelActual + " m");
            actualizarImagen();
            agregarDato();

            if (desbordado) manejarDesborde(canIdeal, canMax);

            calcularNivel(canMax);

            if (nivelActual > canMax * 1.1 && !desbordado) {
                desbordado = true;
                actualizarImagen();
                contadorDesborde = 3;
                timerPrincipal.stop();
                iniciarTimerDesborde(canIdeal, canMax);
            }

            if (detenerr) detenerSimulacion();
        });
        timerPrincipal.start();
    }

    private void iniciarTimerDesborde(int canIdeal, int canMax) {
        timerDesborde = new Timer(1000, e -> {
            agregarDato();
            actualizarImagen();
            mensaje.setText("¡Desborde detectado! Vaciando tanque en "
                    + contadorDesborde + " s");
            if (contadorDesborde-- < 0) {
                mensaje.setText("Vaciando tanque...");
                timerDesborde.stop();
                timerPrincipal.start();
            }
        });
        timerDesborde.start();
    }

    private void configurarModoAutomatico(boolean automatico) {
        if (automatico) {
            Abierto_Entrada.setSelected(true);
            Medio_Salida   .setSelected(true);
        } else {
            Cerrado_Entrada.setSelected(true);
            Cerrado_Salida .setSelected(true);
        }
        boolean manual = !automatico;
        Abierto_Entrada.setEnabled(manual);
        Medio_Entrada  .setEnabled(manual);
        Cerrado_Entrada.setEnabled(manual);
        Abierto_Salida .setEnabled(manual);
        Medio_Salida   .setEnabled(manual);
        Cerrado_Salida .setEnabled(manual);
    }

    // ── Validación de campos ─────────────────────────────────────────────────

    /** Devuelve un mensaje de error o "" si todo es válido */
    private String validarCampos() {
        if (CantidadIdeal.getText().isEmpty() || CantidadMax.getText().isEmpty())
            return "¡Campos vacíos!";
        int canMax   = Integer.parseInt(CantidadMax  .getText());
        int canIdeal = Integer.parseInt(CantidadIdeal.getText());
        if (canMax < 0 || canIdeal < 0)    return "Cantidad inválida";
        if (canMax < canIdeal + 2)          return "La cantidad máxima es menor a la ideal";
        return "";
    }

    // ── Eventos de botones ───────────────────────────────────────────────────


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Valvula_Entrada = new javax.swing.ButtonGroup();
        Valvula_Salida = new javax.swing.ButtonGroup();
        Fondo = new javax.swing.JPanel();
        Imagen = new javax.swing.JPanel();
        Tanque = new javax.swing.JLabel();
        INICIAR = new javax.swing.JButton();
        detener = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cantidadL = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        CantidadMax = new javax.swing.JTextField();
        CantidadIdeal = new javax.swing.JTextField();
        mensaje = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        Abierto_Entrada = new javax.swing.JRadioButton();
        Medio_Entrada = new javax.swing.JRadioButton();
        Cerrado_Entrada = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        Abierto_Salida = new javax.swing.JRadioButton();
        Medio_Salida = new javax.swing.JRadioButton();
        Cerrado_Salida = new javax.swing.JRadioButton();
        automatico = new javax.swing.JRadioButton();
        Grafica = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Fondo.setBackground(new java.awt.Color(102, 255, 204));

        Imagen.setBackground(new java.awt.Color(102, 255, 255));

        javax.swing.GroupLayout ImagenLayout = new javax.swing.GroupLayout(Imagen);
        Imagen.setLayout(ImagenLayout);
        ImagenLayout.setHorizontalGroup(
            ImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Tanque, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
        );
        ImagenLayout.setVerticalGroup(
            ImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Tanque, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
        );

        INICIAR.setText("INICIAR SIMULACION");
        INICIAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                INICIARActionPerformed(evt);
            }
        });

        detener.setText("DETENER SIMULACION");
        detener.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detenerActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(51, 255, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setBackground(new java.awt.Color(51, 255, 51));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Cantidad actual (M)");

        cantidadL.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        cantidadL.setText("00");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(cantidadL)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cantidadL)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Cantidad ideal(M)");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Cantidad maxima(M)");

        CantidadMax.setColumns(3);
        CantidadMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CantidadMaxActionPerformed(evt);
            }
        });

        CantidadIdeal.setColumns(3);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(CantidadMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(CantidadIdeal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(12, 12, 12)
                .addComponent(CantidadMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CantidadIdeal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        mensaje.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        mensaje.setForeground(new java.awt.Color(255, 0, 0));

        jPanel3.setBackground(new java.awt.Color(51, 255, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Valvula Entrada");

        Valvula_Entrada.add(Abierto_Entrada);
        Abierto_Entrada.setText("Abierto");
        Abierto_Entrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Abierto_EntradaActionPerformed(evt);
            }
        });

        Valvula_Entrada.add(Medio_Entrada);
        Medio_Entrada.setText("Medio");

        Valvula_Entrada.add(Cerrado_Entrada);
        Cerrado_Entrada.setSelected(true);
        Cerrado_Entrada.setText("Cerrado");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(Cerrado_Entrada)
                    .addComponent(Abierto_Entrada)
                    .addComponent(Medio_Entrada))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Abierto_Entrada)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Medio_Entrada)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Cerrado_Entrada)
                .addGap(18, 18, 18))
        );

        jPanel4.setBackground(new java.awt.Color(0, 255, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Valvula salida");

        Valvula_Salida.add(Abierto_Salida);
        Abierto_Salida.setText("Abierto");
        Abierto_Salida.setToolTipText("");

        Valvula_Salida.add(Medio_Salida);
        Medio_Salida.setText("Medio");

        Valvula_Salida.add(Cerrado_Salida);
        Cerrado_Salida.setSelected(true);
        Cerrado_Salida.setText("Cerrado");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(Abierto_Salida)
                    .addComponent(Medio_Salida)
                    .addComponent(Cerrado_Salida))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Abierto_Salida)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Medio_Salida)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Cerrado_Salida)
                .addGap(16, 16, 16))
        );

        automatico.setBackground(new java.awt.Color(51, 255, 0));
        automatico.setText("Simulacion automatica");

        Grafica.setBackground(new java.awt.Color(102, 255, 255));

        javax.swing.GroupLayout GraficaLayout = new javax.swing.GroupLayout(Grafica);
        Grafica.setLayout(GraficaLayout);
        GraficaLayout.setHorizontalGroup(
            GraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 493, Short.MAX_VALUE)
        );
        GraficaLayout.setVerticalGroup(
            GraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 363, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout FondoLayout = new javax.swing.GroupLayout(Fondo);
        Fondo.setLayout(FondoLayout);
        FondoLayout.setHorizontalGroup(
            FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FondoLayout.createSequentialGroup()
                .addGroup(FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FondoLayout.createSequentialGroup()
                        .addComponent(Imagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addComponent(Grafica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(FondoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(FondoLayout.createSequentialGroup()
                                .addGroup(FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(INICIAR)
                                    .addComponent(detener, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(199, 199, 199)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(FondoLayout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(mensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(automatico))))))
                .addContainerGap())
        );
        FondoLayout.setVerticalGroup(
            FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FondoLayout.createSequentialGroup()
                .addGroup(FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(Grafica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Imagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(FondoLayout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FondoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(automatico))
                        .addGap(18, 18, 18)
                        .addComponent(INICIAR)
                        .addGap(18, 18, 18)
                        .addComponent(detener))
                    .addGroup(FondoLayout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addGroup(FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addComponent(mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void INICIARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_INICIARActionPerformed
           String error = validarCampos();
        if (!error.isEmpty()) { mensaje.setText(error); return; }

        int canMax   = Integer.parseInt(CantidadMax  .getText());
        int canIdeal = Integer.parseInt(CantidadIdeal.getText());

        mensaje.setText("");
        detenerr = false;

        if (!ejecutando) {
            ejecutando = true;
            automatico.setEnabled(false);
            configurarModoAutomatico(automatico.isSelected());
            iniciarTimerPrincipal(canIdeal, canMax);
        }
    }//GEN-LAST:event_INICIARActionPerformed

    private void detenerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detenerActionPerformed
        detenerr=true;
        ejecutando=false;
    }//GEN-LAST:event_detenerActionPerformed

    private void CantidadMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CantidadMaxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CantidadMaxActionPerformed

    private void Abierto_EntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Abierto_EntradaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Abierto_EntradaActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info :
                    javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Tanque.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new Tanque().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton Abierto_Entrada;
    private javax.swing.JRadioButton Abierto_Salida;
    private javax.swing.JTextField CantidadIdeal;
    private javax.swing.JTextField CantidadMax;
    private javax.swing.JRadioButton Cerrado_Entrada;
    private javax.swing.JRadioButton Cerrado_Salida;
    private javax.swing.JPanel Fondo;
    private javax.swing.JPanel Grafica;
    private javax.swing.JButton INICIAR;
    private javax.swing.JPanel Imagen;
    private javax.swing.JRadioButton Medio_Entrada;
    private javax.swing.JRadioButton Medio_Salida;
    private javax.swing.JLabel Tanque;
    private javax.swing.ButtonGroup Valvula_Entrada;
    private javax.swing.ButtonGroup Valvula_Salida;
    private javax.swing.JRadioButton automatico;
    private javax.swing.JLabel cantidadL;
    private javax.swing.JButton detener;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel mensaje;
    // End of variables declaration//GEN-END:variables
}
