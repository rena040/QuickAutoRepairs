

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nasya Burrell, Karena Gayle, Justin Moo, Roshane Roach, Katelyn Tait, Tishawn Whyte 
 */
public class MechViewAppointments extends javax.swing.JFrame {

    private Appointment appointmentManager = new Appointment();
    private String mechanicId;
    /**
     * Creates new form MechViewAppointments
     */
    public MechViewAppointments() {
        initComponents();
        applyStyles();
        pack();
        setLocationRelativeTo(null); 
    }

    private void applyStyles() {
        UIStyle.stylePanel(jPanel1);
    
        UIStyle.styleButton(jButton1);
    
        jTable1.setFont(UIStyle.LABEL_FONT);
        jTable1.getTableHeader().setFont(UIStyle.LABEL_FONT);
    
        jTable1.setShowGrid(true);
        jTable1.setGridColor(java.awt.Color.BLACK); 
    }

    public void setMechanicId(String mechanicId) {
        this.mechanicId = mechanicId;
        loadMechanicAppointments(mechanicId);
    }

    private void loadMechanicAppointments(String mechanicID) {
        if (mechanicID == null || mechanicID.isEmpty()) {
            System.out.println("Mechanic ID is not set. Cannot load appointments.");
            return;
        }

        appointmentManager.readData(appointmentManager.AFile);

        List<Appointment> filteredAppointments = new ArrayList<>();
        for (Appointment appointment : appointmentManager.appointments) {
            if (appointment.getMechanicId().equals(mechanicID)) {
                filteredAppointments.add(appointment);
            }
        }

        filteredAppointments.sort((a1, a2) -> a1.getAppointmentDate().compareTo(a2.getAppointmentDate()));

        DefaultTableModel model = new DefaultTableModel(
            new Object[][]{},
            new String[]{
                "App ID", "Cust ID", "Vehicle", 
                "App Date", "App Time", "Service", "Status", "Paid", "Amount"
            }) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 7) { 
                    return Boolean.class;
                }
                return String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6 || column == 7;
            }
        };

        for (Appointment appointment : filteredAppointments) {
            model.addRow(new Object[]{
                appointment.getAppointmentId(),
                appointment.getCustomerId(),
                appointment.getVehicleName(),
                appointment.getAppointmentDate(),
                appointment.getAppointmentTime(), 
                appointment.getService(),
                appointment.getStatus(),
                appointment.isPaid(),
                String.format("$%.2f", appointment.getDraft())
            });
        }

        jTable1.setModel(model);

        jTable1.getModel().addTableModelListener(e -> {
            if (e.getType() == javax.swing.event.TableModelEvent.UPDATE) {
                int row = e.getFirstRow();
                int column = e.getColumn();

                if (row >= 0 && column >= 0) {
                    String appointmentId = (String) jTable1.getModel().getValueAt(row, 0);
                    Appointment appointment = findAppointmentById(appointmentId);

                    if (appointment != null) {
                        if (column == 6) { 
                            String newStatus = (String) jTable1.getModel().getValueAt(row, 6);
                            appointment.setStatus(newStatus);

                            if ("Completed".equalsIgnoreCase(newStatus)) {
                                jTable1.getModel().setValueAt(true, row, 7);
                                appointment.setPaid(true);
                            }
                        } else if (column == 7) { 
                            boolean isPaid = (Boolean) jTable1.getModel().getValueAt(row, 7);
                            appointment.setPaid(isPaid);

                            if (isPaid && "Scheduled".equalsIgnoreCase(appointment.getStatus())) {
                                jTable1.getModel().setValueAt("Paid", row, 6);
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



    @SuppressWarnings("unchecked")
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(781, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 752, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        Login lg = new Login();
        lg.setVisible(true);
        this.dispose();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

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


        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MechViewAppointments().setVisible(true);
            }
        });
    }

    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    }

