

/**
 *
 * @author Nasya Burrell, Karena Gayle, Justin Moo, Roshane Roach, Katelyn Tait, Tishawn Whyte 
 */
public class Login extends javax.swing.JFrame {

    public Login() {
        initComponents();
        applyStyles();
        pack(); 
        setLocationRelativeTo(null); // 
    }

    private void applyStyles() {
        UIStyle.stylePanel(jPanel1);

        UIStyle.styleButton(jButton1);
        UIStyle.styleButtonReg(jButton2);

        jLabel1.setFont(UIStyle.LABEL_FONT);
        jLabel2.setFont(UIStyle.LABEL_FONT);
    }



    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("ID");

        jButton1.setText("Login");
        jButton1.addActionListener(evt -> jButton1ActionPerformed(evt));

        jLabel2.setText("Don't have an account?");

        jButton2.setText("Register Now");
        jButton2.addActionListener(evt -> jButton2ActionPerformed(evt));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1)
                    .addComponent(jLabel1)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(jButton1)
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jButton2))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        pack();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        String enteredId = jTextField1.getText().trim();
        boolean found = false;

        try {
            java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader("customer.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length > 0 && parts[0].equals(enteredId)) {
                    found = true;
                    javax.swing.JOptionPane.showMessageDialog(this, "Access Granted: Customer (" + enteredId + ")");
                    CustomerMenu cm = new CustomerMenu();
                    cm.setCustomerId(enteredId);
                    cm.setVisible(true);
                    this.dispose(); 
                    break;
                }
            }
            reader.close();

            if (!found) {
                reader = new java.io.BufferedReader(new java.io.FileReader("admin.txt"));
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(":");
                    if (parts.length > 0 && parts[0].equals(enteredId)) {
                        found = true;
                        javax.swing.JOptionPane.showMessageDialog(this, "Access Granted: Admin (" + enteredId + ")");
                        AdminMenu cm = new AdminMenu();
                        cm.setVisible(true);
                        this.dispose();
                        break;
                    }
                }
                reader.close();
            }

            if (!found) {
                reader = new java.io.BufferedReader(new java.io.FileReader("cashier.txt"));
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(":");
                    if (parts.length > 0 && parts[0].equals(enteredId)) {
                        found = true;
                        javax.swing.JOptionPane.showMessageDialog(this, "Access Granted: Cashier (" + enteredId + ")");
                        CashierMenu cm = new CashierMenu();
                        cm.setCshID(enteredId);
                        cm.setVisible(true);
                        this.dispose(); 
                        break;
                    }
                }
                reader.close();
            }

            if (!found) {
                java.io.File mechanicFile = new java.io.File("mechanics.txt"); 
                if (mechanicFile.exists()) {
                    reader = new java.io.BufferedReader(new java.io.FileReader(mechanicFile));
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(":");
                        if (parts.length > 0 && parts[0].equals(enteredId)) {
                            found = true;
                            javax.swing.JOptionPane.showMessageDialog(this, "Access Granted: Mechanic (" + enteredId + ")");
                            MechViewAppointments cm = new MechViewAppointments();
                            cm.setMechanicId(enteredId); 
                            cm.setVisible(true);

                            this.dispose(); 
                            break;
                        }
                    }
                    reader.close();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(this, "Mechanic file not found.");
                }
            }

            if (!found) {
                javax.swing.JOptionPane.showMessageDialog(this, "ID not found");
            }

        } catch (java.io.IOException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, "Error reading files.");
        }

    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        CreateAccount ca = new CreateAccount();
        ca.setVisible(true);
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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Login().setVisible(true));
    }

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
}
