package consolacomandos;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Swing_CMD extends javax.swing.JFrame {

    ConsolaComandos mf = new ConsolaComandos();
    private boolean usuarioEscribiendo = false;
    private StringBuilder contenidoEscribir = new StringBuilder();
    String nombre = "";

    public Swing_CMD(ConsolaComandos comando) {
        mf = comando != null ? comando : new ConsolaComandos();

        initComponents();

        setLocationRelativeTo(null);

        StringBuilder mensaje = new StringBuilder();

        mensaje.append("\n=====================================\n");
        mensaje.append("BIENVENIDO AL CMD DE WINDOWS. SI DESEA VER LOS COMANDOS DISPONIBLES, POR FAVOR ESCRIBIR ' ver comandos ' . ");
        mensaje.append("\n=====================================\n");
        mensaje.append(mf.getPath());
        mensaje.append(" > ");
        Consola.setText(mensaje.toString());
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Consola = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("WINDOWS CMD         ");
        jLabel1.setOpaque(true);

        Consola.setBackground(new java.awt.Color(0, 0, 0));
        Consola.setColumns(20);
        Consola.setForeground(new java.awt.Color(255, 255, 255));
        Consola.setRows(5);
        Consola.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ConsolaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(Consola);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 932, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ConsolaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ConsolaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String fullText = Consola.getText().trim();
            int lastNewLineIndex = fullText.lastIndexOf("> ");

            if (lastNewLineIndex != -1) {
                String lastLine = fullText.substring(lastNewLineIndex + 1).trim();
                String comando;
                comando = lastLine;

                if (comando.startsWith("ver comandos")) {
                    Consola.append("\nComandos disponibles:\n1. Mkdir\n2. Mfile\n3. Rm\n4. Cd\n5. Dir\n6. Date\n7. Time\n8. wr\n9. rd\n");
                    Consola.append("\n");
                }

                if (comando.startsWith("Mkdir")) {
                    nombre = comando.substring(5).trim();
                    Consola.append(mf.Mkdir(nombre));
                    Consola.append("\n");
                }

                if (comando.startsWith("Mfile")) {
                    nombre = comando.substring(5).trim();

                    try {
                        Consola.append(mf.Mfile(nombre));
                        Consola.append("\n");

                    } catch (IOException ex) {
                        Logger.getLogger(Swing_CMD.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

                if (comando.startsWith("Rm")) {
                    nombre = comando.substring(2).trim();
                    Consola.append(mf.Rm(nombre));
                    Consola.append("\n");
                }

                if (comando.startsWith("Cd")) {
                    nombre = comando.substring(2).trim();
                    Consola.append(mf.Cd(nombre));
                    Consola.append("\n");
                }

                if (comando.startsWith("Dir")) {
                    Consola.append(mf.Dir());
                    Consola.append("\n");
                }

                if (comando.startsWith("Date")) {
                    Consola.append(mf.Date());
                    Consola.append("\n");
                }

                if (comando.startsWith("Time")) {
                    Consola.append(mf.Time());
                    Consola.append("\n");
                }

                if (usuarioEscribiendo) {

                    fullText = Consola.getText().trim();
                    lastNewLineIndex = fullText.lastIndexOf("\n");
                    lastLine = fullText.substring(lastNewLineIndex + 1).trim();
                    contenidoEscribir.append(lastLine);

                    String content = contenidoEscribir.toString();

                    System.out.println(nombre + content);

                    String result = mf.wr(nombre, content);
                    Consola.append("\n");

                    if (result.startsWith("!ERROR!")) {
                        Consola.append("\nError: " + result);
                    } else {
                        Consola.append("\nSe sobreescribio el contenido del archivo "+nombre+".\n");
                    }

                    usuarioEscribiendo = false;
                    contenidoEscribir.setLength(0);

                } else {
                    if (comando.startsWith("wr")) {
                        nombre = comando.substring(2).trim();
                        Consola.append("\nIngrese el contenido a escribir: ");
                        usuarioEscribiendo = true;
                    }
                }

                if (comando.startsWith("rd")) {
                    nombre = comando.substring(2).trim();
                    Consola.append(mf.rd(nombre));
                    Consola.append("\n");
                }
            }

            Consola.append(mf.getPath());
            Consola.append(" > ");
            Consola.setCaretPosition(Consola.getDocument().getLength());
        }
    }//GEN-LAST:event_ConsolaKeyPressed

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
            java.util.logging.Logger.getLogger(Swing_CMD.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Swing_CMD.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Swing_CMD.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Swing_CMD.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Swing_CMD(new ConsolaComandos()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Consola;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
