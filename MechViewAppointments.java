/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rosha
 */
public class MechViewAppointments extends javax.swing.JFrame {

    private Appointment appointmentManager = new Appointment();
    private String mechanicId;
    /**
     * Creates new form MechViewAppointments
     */
    public MechViewAppointments() {
        initComponents();
        appointmentManager = new Appointment();
        //loadMechanicAppointments(mechanicId);
        setTitle("Appointments for Mechanic ID: " + mechanicId);
    }


    public void setMechanicId(String mechanicId) {
        // Load appointments for the specified mechanic
        this.mechanicId = mechanicId;
        //System.out.println("Mechanic ID set to: " + mechanicId);
        loadMechanicAppointments(mechanicId);
    }

    private void loadMechanicAppointments(String mechanicID) {
        // Ensure the mechanicID is set
        if (mechanicID == null || mechanicID.isEmpty()) {
            System.out.println("Mechanic ID is not set. Cannot load appointments.");
            return;
        }

        // Read appointments from the file
        appointmentManager.readData(appointmentManager.AFile);

        // Filter appointments for this mechanic
        List<Appointment> filteredAppointments = new ArrayList<>();
        for (Appointment appointment : appointmentManager.appointments) {
            if (appointment.getMechanicId().equals(mechanicID)) {
                filteredAppointments.add(appointment);
            }
        }

        // Sort appointments by date in ascending order
        filteredAppointments.sort((a1, a2) -> a1.getAppointmentDate().compareTo(a2.getAppointmentDate()));

        // Create table model
        DefaultTableModel model = new DefaultTableModel(
            new Object[][]{},
            new String[]{
                "Appointment ID", "Customer ID", "Vehicle Name", 
                "Appointment Date", "Service", "Status", "Paid", "Draft Amount"
            }) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 6) { // Paid column
                    return Boolean.class;
                }
                return String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                // Only allow editing of status and paid columns
                return column == 5 || column == 6;
            }
        };

        // Add sorted appointments to the table model
        for (Appointment appointment : filteredAppointments) {
            model.addRow(new Object[]{
                appointment.getAppointmentId(),
                appointment.getCustomerId(),
                appointment.getVehicleName(),
                appointment.getAppointmentDate(),
                appointment.getService(),
                appointment.getStatus(),
                appointment.isPaid(),
                String.format("$%.2f", appointment.getDraft())
            });
        }

        jTable1.setModel(model);

        // Add table model listener to handle updates
        jTable1.getModel().addTableModelListener(e -> {
            if (e.getType() == javax.swing.event.TableModelEvent.UPDATE) {
                int row = e.getFirstRow();
                int column = e.getColumn();

                if (row >= 0 && column >= 0) {
                    String appointmentId = (String) jTable1.getModel().getValueAt(row, 0);
                    Appointment appointment = findAppointmentById(appointmentId);

                    if (appointment != null) {
                        if (column == 5) { // Status column
                            String newStatus = (String) jTable1.getModel().getValueAt(row, 5);
                            appointment.setStatus(newStatus);

                            if ("Completed".equalsIgnoreCase(newStatus)) {
                                jTable1.getModel().setValueAt(true, row, 6);
                                appointment.setPaid(true);
                            }
                        } else if (column == 6) { // Paid column
                            boolean isPaid = (Boolean) jTable1.getModel().getValueAt(row, 6);
                            appointment.setPaid(isPaid);

                            if (isPaid && "Scheduled".equalsIgnoreCase(appointment.getStatus())) {
                                jTable1.getModel().setValueAt("Paid", row, 5);
                                appointment.setStatus("Paid");
                            }
                        }

                        appointmentManager.writeToFile(appointmentManager.AFile, appointmentManager.appointments);
                    }
                }
            }
        });
    }

    private Appointment findAppointmentById(String appointmentId) {
        for (Appointment appointment : appointmentManager.appointments) {
            if (appointment.getAppointmentId().equals(appointmentId)) {
                return appointment;
            }
        }
        return null;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 255, 153));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Appointment ID", "Customer ID", "Vehicle Name", "Appointment Date", "Service"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(1).setResizable(false);
        }

        jButton1.setText("Logout");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 98, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Login lg = new Login();
        lg.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(MechViewAppointments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MechViewAppointments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MechViewAppointments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MechViewAppointments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MechViewAppointments().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
