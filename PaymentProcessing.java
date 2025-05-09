import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nasya Burrell, Karena Gayle, Justin Moo, Roshane Roach, Katelyn Tait, Tishawn Whyte 
 */

public class PaymentProcessing extends javax.swing.JFrame {

    private void displayAppointmentDetails(Appointment appointment) {
        selectedAppointment = appointment;
        selectedCustomer = paymentHandler.getCustomerById(appointment.getCustomerId());
        
        txtAppointmentId.setText(appointment.getAppointmentId());
        txtCustomerId.setText(appointment.getCustomerId());
        txtVehicle.setText(appointment.getVehicleName());
        txtAmount.setText(String.format("$%.2f", appointment.getDraft()));
        txtCashierId.setText(cshid);
        chkApplyDiscount.setSelected(false);
        receiptTextArea.setText("");
        
        
        if (selectedCustomer != null && selectedCustomer.getStamps() >= 8) {
            lblDiscountInfo.setText(lblDiscountInfo.getText() + " | Eligible for 10% discount (8+ points)");
        }
    }

    String cshid;
    public void setCshID(String cshid) {
        this.cshid = cshid;
    }

    private Payment paymentHandler;
    private Appointment selectedAppointment;
    private Customer selectedCustomer;

    /**
     * Creates new form PaymentProcessing
     */
    public PaymentProcessing() {
        paymentHandler = new Payment();
        initComponents();
        applyStyles();
        pack();
        setLocationRelativeTo(null); 
    }

    private void applyStyles() {
        UIStyle.stylePanel(jPanel1);

        UIStyle.styleButton(jButton1);
        UIStyle.styleButton(jButton2);
        UIStyle.styleButton(selectAppoint);
        UIStyle.styleButton(backbutton);

        jLabel1.setFont(UIStyle.LABEL_FONT);
        jLabel2.setFont(UIStyle.LABEL_FONT);
        jLabel3.setFont(UIStyle.LABEL_FONT);
        jLabel4.setFont(UIStyle.LABEL_FONT);
        jLabel5.setFont(UIStyle.LABEL_FONT);
        jLabel6.setFont(UIStyle.LABEL_FONT);
        jLabel7.setFont(UIStyle.LABEL_FONT);

        searchField.setFont(UIStyle.LABEL_FONT);
        txtAppointmentId.setFont(UIStyle.LABEL_FONT);
        txtCustomerId.setFont(UIStyle.LABEL_FONT);
        txtAmount.setFont(UIStyle.LABEL_FONT);
        txtVehicle.setFont(UIStyle.LABEL_FONT);
        txtCashierId.setFont(UIStyle.LABEL_FONT);

        appointmentTable.setFont(UIStyle.LABEL_FONT);
        appointmentTable.getTableHeader().setFont(UIStyle.LABEL_FONT);
    }


    @SuppressWarnings("unchecked")
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        searchField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        radioAppID = new javax.swing.JRadioButton();
        radioCustID = new javax.swing.JRadioButton();
        mainTabbedPane = new javax.swing.JTabbedPane();
        appointmentSelectionPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        appointmentTable = new javax.swing.JTable();
        selectAppoint = new javax.swing.JButton();
        paymentDetailsPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        chkApplyDiscount = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        receiptTextArea = new javax.swing.JTextArea();
        txtAppointmentId = new javax.swing.JTextField();
        txtCustomerId = new javax.swing.JTextField();
        txtAmount = new javax.swing.JTextField();
        txtVehicle = new javax.swing.JTextField();
        txtCashierId = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        lblDiscountInfo = new javax.swing.JLabel();
        backbutton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 204, 0));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Search");

        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioAppID);
        radioAppID.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        radioAppID.setForeground(new java.awt.Color(25, 25, 25));
        radioAppID.setText("Search By Appointment Id");

        buttonGroup1.add(radioCustID);
        radioCustID.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        radioCustID.setForeground(new java.awt.Color(25, 25, 25));
        radioCustID.setText("Search By Customer ID");

        appointmentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Appointment ID", "Vehicle", "Date", "Customer ID", "Paid"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
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
        jScrollPane1.setViewportView(appointmentTable);

        selectAppoint.setText("Select Appointment");
        selectAppoint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAppointActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout appointmentSelectionPanelLayout = new javax.swing.GroupLayout(appointmentSelectionPanel);
        appointmentSelectionPanel.setLayout(appointmentSelectionPanelLayout);
        appointmentSelectionPanelLayout.setHorizontalGroup(
            appointmentSelectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(appointmentSelectionPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(222, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, appointmentSelectionPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(selectAppoint)
                .addGap(56, 56, 56))
        );
        appointmentSelectionPanelLayout.setVerticalGroup(
            appointmentSelectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(appointmentSelectionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 245, Short.MAX_VALUE)
                .addComponent(selectAppoint)
                .addGap(46, 46, 46))
        );

        mainTabbedPane.addTab("Appointment Selection", appointmentSelectionPanel);

        jLabel2.setText("Appointment ID:");

        jLabel3.setText("Customer ID:");

        jLabel4.setText("Vehicle:");

        jLabel5.setText("Amount Due:");

        jLabel6.setText("Cashier ID:");

        chkApplyDiscount.setText("Apply Loyalty Discount");
        chkApplyDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkApplyDiscountActionPerformed(evt);
            }
        });

        jLabel7.setText("Receipt");

        receiptTextArea.setColumns(20);
        receiptTextArea.setRows(5);
        jScrollPane2.setViewportView(receiptTextArea);

        txtAppointmentId.setEditable(false);
        txtAppointmentId.setEnabled(false);

        txtCustomerId.setEditable(false);
        txtCustomerId.setEnabled(false);

        txtAmount.setEditable(false);
        txtAmount.setEnabled(false);

        txtVehicle.setEditable(false);
        txtVehicle.setEnabled(false);

        txtCashierId.setEditable(false);
        txtCashierId.setEnabled(false);
        txtCashierId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCashierIdActionPerformed(evt);
            }
        });

        jButton2.setText("Proceess Payment");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout paymentDetailsPanelLayout = new javax.swing.GroupLayout(paymentDetailsPanel);
        paymentDetailsPanel.setLayout(paymentDetailsPanelLayout);
        paymentDetailsPanelLayout.setHorizontalGroup(
            paymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paymentDetailsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paymentDetailsPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 664, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 124, Short.MAX_VALUE))
                    .addGroup(paymentDetailsPanelLayout.createSequentialGroup()
                        .addComponent(chkApplyDiscount)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(82, 82, 82))
                    .addGroup(paymentDetailsPanelLayout.createSequentialGroup()
                        .addGroup(paymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(paymentDetailsPanelLayout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(137, 137, 137)
                                .addComponent(lblDiscountInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 129, Short.MAX_VALUE))
                            .addGroup(paymentDetailsPanelLayout.createSequentialGroup()
                                .addGroup(paymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(paymentDetailsPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtAppointmentId, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
                                    .addGroup(paymentDetailsPanelLayout.createSequentialGroup()
                                        .addGroup(paymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(paymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtAmount)
                                            .addComponent(txtVehicle)
                                            .addComponent(txtCustomerId))))
                                .addGap(61, 61, 61)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtCashierId, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        paymentDetailsPanelLayout.setVerticalGroup(
            paymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paymentDetailsPanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(paymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtAppointmentId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtCashierId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCustomerId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(paymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtVehicle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(paymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(paymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkApplyDiscount)
                    .addComponent(jButton2))
                .addGap(12, 12, 12)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDiscountInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        mainTabbedPane.addTab("Payment Selection", paymentDetailsPanel);

        backbutton.setText("Back");
        backbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backbuttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(radioAppID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(radioCustID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(34, 34, 34))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(mainTabbedPane)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(83, 83, 83))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(backbutton)
                        .addGap(134, 134, 134))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(radioAppID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(radioCustID, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(mainTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backbutton)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        String searchValue = searchField.getText().trim();
        
        if (searchValue.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a search value", 
                "Search Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (radioCustID.isSelected()) {
            List<Appointment> appointments = paymentHandler.processPaymentByCustomerId(searchValue);
            
            if (appointments.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "No scheduled appointments found for customer ID: " + searchValue, 
                    "No Appointments", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            DefaultTableModel model = (DefaultTableModel) appointmentTable.getModel();
            model.setRowCount(0);
            
            for (Appointment apt : appointments) {
                model.addRow(new Object[]{
                    apt.getAppointmentId(),
                    apt.getVehicleName(),
                    apt.getAppointmentDate(),
                    apt.getCustomerId(),
                    apt.isPaid() 
                });
            }
            
            mainTabbedPane.setVisible(true);
            mainTabbedPane.setSelectedComponent(appointmentSelectionPanel);
            
        }else if (radioAppID.isSelected()) {
            Appointment appointment = paymentHandler.processPaymentByAppointmentId(searchValue);
            
            if (appointment == null) {
                JOptionPane.showMessageDialog(this, 
                    "No scheduled appointment found with ID: " + searchValue, 
                    "No Appointment", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            displayAppointmentDetails(appointment);
            paymentDetailsPanel.setVisible(true);
            mainTabbedPane.setSelectedComponent(paymentDetailsPanel);
        }else{
            JOptionPane.showMessageDialog(this, "Please select a search option", 
                "Search Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        

        
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        if (txtCashierId.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter cashier ID", 
                "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Double amountPaid = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter amount paid by customer: "));

        if (amountPaid <= 0) {
            JOptionPane.showMessageDialog(this, "Please enter a valid amount", 
                "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double finalAmount = selectedAppointment.getDraft(); 

        if (chkApplyDiscount.isSelected() && selectedCustomer != null && selectedCustomer.getStamps() >= 8) {
            finalAmount = finalAmount * 0.9; 
        }

        if (amountPaid < finalAmount) {
            JOptionPane.showMessageDialog(this, "Amount paid is less than the due amount", 
                "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            boolean applyDiscount = chkApplyDiscount.isSelected();
            String receipt = paymentHandler.processPayment(
                selectedAppointment.getAppointmentId(),
                txtCashierId.getText().trim(),
                applyDiscount
            );

            String id = txtAppointmentId.getText().trim();

            Appointment appointment = paymentHandler.getAppointmentById(id);
            if (appointment != null) {
                appointment.completeService(); 
                appointment.setPaid(true); 

                
                for (int i = 0; i < appointmentTable.getRowCount(); i++) {
                    if (appointmentTable.getValueAt(i, 0).equals(appointment.getAppointmentId())) {
                        appointmentTable.setValueAt(true, i, 4); 
                        break;
                    }
                }
            }

            double change = amountPaid - finalAmount;

            receipt += "\n\nPayment Details:\n";
            receipt += "Amount Paid: $" + amountPaid + "\n";
            receipt += "Final Amount: $" + finalAmount + "\n";
            receipt += "Change: $" + change + "\n"; 
            receiptTextArea.setText(receipt);

            JOptionPane.showMessageDialog(this, "Payment processed successfully", 
                "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error processing payment: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void selectAppointActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = appointmentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an appointment", 
                "Selection Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        
        boolean isPaid = (boolean) appointmentTable.getValueAt(selectedRow, 4); 
        if (isPaid) {
            JOptionPane.showMessageDialog(this, "This appointment has already been paid for and cannot be selected.", 
                "Selection Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String appointmentId = appointmentTable.getValueAt(selectedRow, 0).toString();
        Appointment appointment = paymentHandler.processPaymentByAppointmentId(appointmentId);

        if (appointment != null) {
            displayAppointmentDetails(appointment);
            paymentDetailsPanel.setVisible(true);
            mainTabbedPane.setSelectedComponent(paymentDetailsPanel);
        }
    }
    private void chkApplyDiscountActionPerformed(java.awt.event.ActionEvent evt) {
        if (selectedAppointment == null) return;
        
        if (chkApplyDiscount.isSelected() && selectedCustomer != null) {
            if (selectedCustomer.getStamps() >= 8) {
                lblDiscountInfo.setText("10% discount will be applied");

                txtAmount.setText(String.format("$%.2f", selectedAppointment.getDraft() * 0.9));
                Double finalAmount = selectedAppointment.getDraft() * 0.9;

            } else {
                lblDiscountInfo.setText("Not enough loyalty points (8 required)");
                chkApplyDiscount.setSelected(false);
                txtAmount.setText(String.format("$%.2f", selectedAppointment.getDraft()));
                Double finalAmount = selectedAppointment.getDraft();
            }
        } else {
            lblDiscountInfo.setText("");
            txtAmount.setText(String.format("$%.2f", selectedAppointment.getDraft()));
        }
    }
    private void backbuttonActionPerformed(java.awt.event.ActionEvent evt) {
        CashierMenu cm = new CashierMenu();
        cm.setVisible(true);
        this.dispose();
    }

    private void txtCashierIdActionPerformed(java.awt.event.ActionEvent evt) {
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
            java.util.logging.Logger.getLogger(PaymentProcessing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PaymentProcessing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PaymentProcessing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PaymentProcessing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PaymentProcessing().setVisible(true);
            }
        });
    }

    private javax.swing.JPanel appointmentSelectionPanel;
    private javax.swing.JTable appointmentTable;
    private javax.swing.JButton backbutton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chkApplyDiscount;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDiscountInfo;
    private javax.swing.JTabbedPane mainTabbedPane;
    private javax.swing.JPanel paymentDetailsPanel;
    private javax.swing.JRadioButton radioAppID;
    private javax.swing.JRadioButton radioCustID;
    private javax.swing.JTextArea receiptTextArea;
    private javax.swing.JTextField searchField;
    private javax.swing.JButton selectAppoint;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JTextField txtAppointmentId;
    private javax.swing.JTextField txtCashierId;
    private javax.swing.JTextField txtCustomerId;
    private javax.swing.JTextField txtVehicle;
}
