/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaz;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 *
 * @author andre
 */
public class Tanque extends javax.swing.JFrame {
    /**
     * Creates new form Tanque
     */
    double max = 0;
    Timer timer;
    boolean Detener = false;
    boolean ejecutando = false;
    
    public Tanque() {
        initComponents();
        ImageIcon tanque = new ImageIcon("img/TC8.png");
        int alto = Tanque.getHeight();
        int ancho = Tanque.getWidth();
        Image tanque_escalado = tanque.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        ImageIcon tanque_final = new ImageIcon(tanque_escalado);
        Tanque.setIcon(tanque_final);
    }
    
    public void Cambiar_Imagen(double tanque_nivel) {

        int canIdeal = Integer.parseInt(CantidadIdeal.getText());
        int canMax = Integer.parseInt(CantidadMax.getText());
        ImageIcon tanque=new ImageIcon("img/0.png");
        int margenIdeal =2;
        
        
        if(Abierto_Entrada.isSelected()){
            
            if(Abierto_Salida.isSelected()){
                if(max<canIdeal-(canIdeal/2)){
                    tanque = new ImageIcon("img/1.png");
                }else if(max>=canIdeal-(canIdeal/2) && max<canIdeal+margenIdeal){
                    tanque = new ImageIcon("img/10.png");
                }else if(max>=canIdeal+margenIdeal ){
                    tanque = new ImageIcon("img/19.png");
                }
            }else if(Medio_Salida.isSelected()){
                if(max<canIdeal-(canIdeal/2)){
                    tanque = new ImageIcon("img/2.png");
                }else if(max>=canIdeal-(canIdeal/2) && max<canIdeal+margenIdeal){
                    tanque = new ImageIcon("img/11.png");
                }else if(max>=canIdeal+margenIdeal ){
                    tanque = new ImageIcon("img/20.png");
                }
            }else if(Cerrado_Salida.isSelected()){
                if(max<canIdeal-(canIdeal/2)){
                    tanque = new ImageIcon("img/3.png");
                }else if(max>=canIdeal-(canIdeal/2) && max<canIdeal+margenIdeal){
                    tanque = new ImageIcon("img/12.png");
                }else if(max>=canIdeal+margenIdeal){
                    tanque = new ImageIcon("img/21.png");
                }
            }
            
            
        }
        else if(Medio_Entrada.isSelected()){
            
            if(Abierto_Salida.isSelected()){
                if(max<canIdeal-(canIdeal/2)){
                    tanque = new ImageIcon("img/4.png");
                }else if(max>=canIdeal-(canIdeal/2) && max<canIdeal+margenIdeal){
                    tanque = new ImageIcon("img/13.png");
                }else if(max>=canIdeal+margenIdeal){
                    tanque = new ImageIcon("img/22.png");
                }
            }else if(Medio_Salida.isSelected()){
                if(max<canIdeal-(canIdeal/2)){
                    tanque = new ImageIcon("img/5.png");
                }else if(max>=canIdeal-(canIdeal/2) && max<canIdeal+margenIdeal){
                    tanque = new ImageIcon("img/14.png");
                }else if(max>=canIdeal+margenIdeal){
                    tanque = new ImageIcon("img/23.png");
                }
            }else if(Cerrado_Salida.isSelected()){
                if(max<canIdeal-(canIdeal/2)){
                    tanque = new ImageIcon("img/6.png");
                }else if(max>=canIdeal-(canIdeal/2) && max<canIdeal+margenIdeal){
                    tanque = new ImageIcon("img/15.png");
                }else if(max>=canIdeal+margenIdeal){
                    tanque = new ImageIcon("img/24.png");
                }
            }
            
            
        }
        else if(Cerrado_Entrada.isSelected()){
            
            if(Abierto_Salida.isSelected()){
                if(max<canIdeal-(canIdeal/2)){
                    tanque = new ImageIcon("img/7.png");
                }else if(max>=canIdeal-(canIdeal/2) && max<canIdeal+margenIdeal){
                    tanque = new ImageIcon("img/16.png");
                }else if(max>=canIdeal+margenIdeal){
                    tanque = new ImageIcon("img/25.png");
                }
            }else if(Medio_Salida.isSelected()){
                if(max<canIdeal-(canIdeal/2)){
                    tanque = new ImageIcon("img/8.png");
                }else if(max>=canIdeal-(canIdeal/2) && max<canIdeal+margenIdeal){
                    tanque = new ImageIcon("img/17.png");
                }else if(max>=canIdeal+margenIdeal){
                    tanque = new ImageIcon("img/26.png");
                }
            }else if(Cerrado_Salida.isSelected()){
                if(max<canIdeal-(canIdeal/2)){
                    tanque = new ImageIcon("img/9.png");
                }else if(max>=canIdeal-(canIdeal/2) && max<canIdeal+margenIdeal){
                    tanque = new ImageIcon("img/18.png");
                }else if(max>=canIdeal+margenIdeal){
                    tanque = new ImageIcon("img/27.png");
                }
            }
        }
        //ajuste de imagen 
        int alto = Tanque.getHeight();
        int ancho = Tanque.getWidth();
        Image tanque_escalado = tanque.getImage().getScaledInstance(ancho,alto,Image.SCALE_SMOOTH);
        Tanque.setIcon(new ImageIcon(tanque_escalado));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
        jLabel4.setText("Cantidad actual (L)");

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
        jLabel2.setText("Cantidad ideal");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Cantidad maxima");

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

        javax.swing.GroupLayout FondoLayout = new javax.swing.GroupLayout(Fondo);
        Fondo.setLayout(FondoLayout);
        FondoLayout.setHorizontalGroup(
            FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FondoLayout.createSequentialGroup()
                .addComponent(Imagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FondoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(FondoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(FondoLayout.createSequentialGroup()
                        .addGroup(FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(INICIAR)
                            .addComponent(detener, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(199, 199, 199)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(898, 898, 898))
        );
        FondoLayout.setVerticalGroup(
            FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FondoLayout.createSequentialGroup()
                .addGroup(FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Imagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(FondoLayout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(FondoLayout.createSequentialGroup()
                        .addGroup(FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(INICIAR)
                        .addGap(18, 18, 18)
                        .addComponent(detener))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FondoLayout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addGroup(FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(84, 84, 84)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Fondo, javax.swing.GroupLayout.PREFERRED_SIZE, 737, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fondo, javax.swing.GroupLayout.PREFERRED_SIZE, 656, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void INICIARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_INICIARActionPerformed
        //valida que los campos no esten vacios
        if (CantidadIdeal.getText().isEmpty() || CantidadMax.getText().isEmpty()) {
        mensaje.setText("Campos vacios!!");
        return;
    }
    int canM = Integer.parseInt(CantidadMax.getText());
    int canIdeal = Integer.parseInt(CantidadIdeal.getText());
    
    //valida que la cantidad ideal no sea mayor a el maximo
    if (canM < canIdeal+2) {
        mensaje.setText("La cantidad maxima es menor a la cantidad ideal");
        return;
    }
    //se limpia el label de mensaje cada que de la a iniciar
    mensaje.setText("");
    Detener = false;
    
    if (!ejecutando) {
        ejecutando = true;
        
        //timer sirve para poder hacer la comparacion en intervalos de tiempo para que se vea mas fluido en la simulacion donde se da el tiempo en milisegundos
        timer = new Timer(500, e -> {
            //envio de los datos en porcentaje y en litro de la cantidad actual
 
            cantidadL.setText(max  + "L");
            
            Cambiar_Imagen(max);
            if(max<canM){
                if(Abierto_Entrada.isSelected()){
                    max+=1;
                }else if(Medio_Entrada.isSelected()){ 
                    max+=0.5;
                }else if(Cerrado_Entrada.isSelected()){
                    max+=0;
                }
            }
            if(max>canIdeal){
                if(Abierto_Salida.isSelected()){
                    max-=1;
                }else if(Medio_Salida.isSelected()){
                    max-=0.5;
                }else if(Cerrado_Salida.isSelected()){
                    max-=0;
                }
            }
            if(Detener){
                max=0;
                timer.stop();
            }
        });
        //aqui se inicia todo lo que esta dentro de *time*
        timer.start();
    }
        
    }//GEN-LAST:event_INICIARActionPerformed

    private void detenerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detenerActionPerformed
        Detener=true;
        ejecutando=false;
    }//GEN-LAST:event_detenerActionPerformed

    private void CantidadMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CantidadMaxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CantidadMaxActionPerformed

    private void Abierto_EntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Abierto_EntradaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Abierto_EntradaActionPerformed

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
            java.util.logging.Logger.getLogger(Tanque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tanque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tanque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tanque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tanque().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton Abierto_Entrada;
    private javax.swing.JRadioButton Abierto_Salida;
    private javax.swing.JTextField CantidadIdeal;
    private javax.swing.JTextField CantidadMax;
    private javax.swing.JRadioButton Cerrado_Entrada;
    private javax.swing.JRadioButton Cerrado_Salida;
    private javax.swing.JPanel Fondo;
    private javax.swing.JButton INICIAR;
    private javax.swing.JPanel Imagen;
    private javax.swing.JRadioButton Medio_Entrada;
    private javax.swing.JRadioButton Medio_Salida;
    private javax.swing.JLabel Tanque;
    private javax.swing.ButtonGroup Valvula_Entrada;
    private javax.swing.ButtonGroup Valvula_Salida;
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
