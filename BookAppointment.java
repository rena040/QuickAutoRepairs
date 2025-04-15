import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 *
 * @author Nasya Burrell, Karena Gayle, Justin Moo, Roshane Roach, Katelyn Tait, Tishawn Whyte 
 */


public class BookAppointment extends javax.swing.JFrame {

    private String customerId; 
    
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
        custId.setText(customerId);
    }

    private void loadServices() {
        services.removeAllItems();
        
        Service.readData();
        List<Service> allServices = Service.getAllServices();
        
        for (Service service : allServices) {
            services.addItem(service.getServiceType() + " (" + service.getServiceId() + ")");
        }
        
        if (allServices.isEmpty()) {
            services.addItem("No services available");
        }
    }

    private java.util.List<Mechanic> mechanicList = new ArrayList<>();

    public BookAppointment() {
        initComponents();
        AppID.setText(Appointment.generateNextAppointmentId());
        AppID.setEditable(false); 

        Mechanic.loadMechanics();
        mechanicList = Mechanic.getAllMechanics();

        mechanic.removeAllItems();
        for (Mechanic m : mechanicList) {
            mechanic.addItem(m.getName() + " (" + m.getMechanicId() + ")");
        }
        loadServices();
        applyStyles(); 
        pack(); 
        setLocationRelativeTo(null); 
    }

    private void applyStyles() {
        UIStyle.stylePanel(jPanel1);

        UIStyle.styleButton(jButton1); 
        UIStyle.styleButton(jButton2); 

        jLabel1.setFont(UIStyle.LABEL_FONT);
        jLabel2.setFont(UIStyle.LABEL_FONT);
        jLabel3.setFont(UIStyle.LABEL_FONT);
        jLabel4.setFont(UIStyle.LABEL_FONT);
        jLabel5.setFont(UIStyle.LABEL_FONT);
        jLabel6.setFont(UIStyle.LABEL_FONT);
        jLabel7.setFont(UIStyle.LABEL_FONT);

        UIStyle.styleComboBox(mechanic);
        UIStyle.styleComboBox(services);
    }


    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        custId = new javax.swing.JTextField();
        AppID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        mechanic = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        AppDate = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        vehicle = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        services = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        apptime = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);


        jLabel1.setText("Customer ID");

        custId.setEditable(false);
        custId.setEnabled(false);
        custId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                custIdActionPerformed(evt);
            }
        });

        AppID.setEditable(false);
        AppID.setEnabled(false);

        jLabel2.setText("Appointment ID");

        jLabel3.setText("Mechanic");

        mechanic.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("Appointment Date");

        AppDate.setText("YYYY-MM-DD");
        AppDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AppDateActionPerformed(evt);
            }
        });

        jLabel6.setText("Vehicle");

        vehicle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vehicleActionPerformed(evt);
            }
        });

        jButton1.setText("Book Appointment");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Service");

        services.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel7.setText("Appointment Time");

        apptime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apptimeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(AppDate, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(custId, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(AppID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(services, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2)
                            .addComponent(jButton1))
                        .addGap(98, 98, 98))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mechanic, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(apptime, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(custId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AppID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AppDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(apptime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mechanic)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(services, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(85, 85, 85))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void vehicleActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void custIdActionPerformed(java.awt.event.ActionEvent evt) {
        
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        Appointment app = new Appointment();
        String Custid = custId.getText();
        String Appid = AppID.getText();
        String appDate = AppDate.getText();
        String Vehicle = vehicle.getText();
        String appTime = apptime.getText();

        if (Custid.isEmpty() || Appid.isEmpty() || appDate.isEmpty() || Vehicle.isEmpty() || appDate.equals("YYYY-MM-DD") || appTime.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        // Check if the date is valid and not in the past
        try {
            LocalDate appointmentDate = LocalDate.parse(appDate);
            if (appointmentDate.isBefore(LocalDate.now())) {
                JOptionPane.showMessageDialog(this, "The appointment date has already passed. Please select a valid date.");
                return;
            }
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Please use YYYY-MM-DD.");
            return;
        }

        String selectedMechanic = mechanic.getSelectedItem().toString();
        String mechanicId = selectedMechanic.substring(selectedMechanic.indexOf("(") + 1, selectedMechanic.indexOf(")"));

        String selectedService = services.getSelectedItem().toString();
        String serviceId = selectedService.substring(selectedService.indexOf("(") + 1, selectedService.indexOf(")"));

        if (!app.checkAvailability(mechanicId, appDate, appTime)) {
            JOptionPane.showMessageDialog(this, "The selected mechanic is not available at the chosen date and time.");
            return;
        }

        Appointment newAppointment = new Appointment();
        newAppointment.readData(newAppointment.AFile);
        newAppointment.BookAppointment(Appid, Custid, mechanicId, Vehicle, appDate, appTime, serviceId);
        newAppointment.addAppointment(newAppointment);

        newAppointment.setDraft(newAppointment.getDraft() + Service.findServiceById(serviceId).calculateTotalCost(Mechanic.searchMechanicById(mechanicId).getPayRate()));

        BookAppointment ba = new BookAppointment();
        this.dispose();
        ba.setCustomerId(Custid);
        ba.setVisible(true);

        JOptionPane.showMessageDialog(this, "Appointment booked successfully!\nAppointment ID: " + Appid);
    }

    private void AppDateActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        CustomerMenu cm = new CustomerMenu();
        this.dispose();
        cm.setCustomerId(customerId);
        cm.setVisible(true);
    }

    private void apptimeActionPerformed(java.awt.event.ActionEvent evt) {
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
            java.util.logging.Logger.getLogger(BookAppointment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BookAppointment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BookAppointment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BookAppointment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BookAppointment().setVisible(true);
            }
        });
    }

    private javax.swing.JTextField AppDate;
    private javax.swing.JTextField AppID;
    private javax.swing.JTextField apptime;
    private javax.swing.JTextField custId;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox<String> mechanic;
    private javax.swing.JComboBox<String> services;
    private javax.swing.JTextField vehicle;
}
