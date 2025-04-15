import java.util.ArrayList;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Nasya Burrell, Karena Gayle, Justin Moo, Roshane Roach, Katelyn Tait, Tishawn Whyte 
 */
public class CustomerMenu extends javax.swing.JFrame {


    public CustomerMenu() {
        initComponents();
        applyStyles();
        pack();
        setLocationRelativeTo(null); 
    }

    private void applyStyles() {
        UIStyle.stylePanel(jPanel1);

        UIStyle.styleButton(bookappointment);
        UIStyle.styleButton(jButton1);
        UIStyle.styleButton(jButton2);
    }


    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        bookappointment = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 255));

        bookappointment.setText("Book Appointment");
        bookappointment.setPreferredSize(new java.awt.Dimension(200, 40)); 
        bookappointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookappointmentActionPerformed(evt);
            }
        });

        jButton2.setText("Exit");
        jButton2.setPreferredSize(new java.awt.Dimension(200, 40)); 
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Upcoming Appointments");
        jButton1.setPreferredSize(new java.awt.Dimension(200, 40)); 
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
                    .addGap(145, 145, 145)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(bookappointment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(145, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(50, 50, 50)
                    .addComponent(bookappointment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(50, Short.MAX_VALUE))
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

    String customerId;

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    private void bookappointmentActionPerformed(java.awt.event.ActionEvent evt) {
        
        BookAppointment bk = new BookAppointment();
        bk.setCustomerId(this.customerId);
        bk.setVisible(true);
        this.setVisible(false);

        
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        List<Appointment> matchedAppointments = new ArrayList<>();
        Appointment ap = new Appointment();
        matchedAppointments = ap.searchAppointmentsByCustomerId(customerId);

        if (matchedAppointments.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "No upcoming appointments found.");
        } else {
            StringBuilder appointmentDetails = new StringBuilder();
            int upcomingCount = 0;

            for (Appointment appointment : matchedAppointments) {
                if (!appointment.getStatus().equalsIgnoreCase("Completed") &&
                    !appointment.getStatus().equalsIgnoreCase("Cancelled")) {
                    upcomingCount++;
                }
            }

            if (upcomingCount == 0) {
                javax.swing.JOptionPane.showMessageDialog(this, "No upcoming appointments found.");
                return;
            }

            appointmentDetails.append("You have ").append(upcomingCount).append(" upcoming appointments:\n\n");

            for (Appointment appointment : matchedAppointments) {
                if (!appointment.getStatus().equalsIgnoreCase("Completed") &&
                    !appointment.getStatus().equalsIgnoreCase("Cancelled")) {
                    appointmentDetails.append("Appointment ID: ").append(appointment.getAppointmentId()).append("\n")
                                      .append("Customer ID: ").append(appointment.getCustomerId()).append("\n")
                                      .append("Mechanic ID: ").append(appointment.getMechanicId()).append("\n")
                                      .append("Vehicle Name: ").append(appointment.getVehicleName()).append("\n")
                                      .append("Appointment Date: ").append(appointment.getAppointmentDate()).append("\n")
                                      .append("Appointment Time: ").append(appointment.getAppointmentTime()).append("\n")
                                      .append("Service: ").append(appointment.getService()).append("\n")
                                      .append("Status: ").append(appointment.getStatus()).append("\n")
                                      .append("Paid: ").append(appointment.isPaid() ? "Yes" : "No").append("\n")
                                      .append("Draft Amount: $").append(String.format("%.2f", appointment.getDraft())).append("\n")
                                      .append("----------------------------------------\n");
                }
            }

            JTextArea textArea = new JTextArea(appointmentDetails.toString());
            textArea.setEditable(false);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);

            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new java.awt.Dimension(500, 400));

            javax.swing.JOptionPane.showMessageDialog(this, scrollPane, "Upcoming Appointments", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
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
            java.util.logging.Logger.getLogger(CustomerMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CustomerMenu().setVisible(true);
            }
        });
    }

    private javax.swing.JButton bookappointment;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
}
