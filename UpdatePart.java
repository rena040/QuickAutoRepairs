

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

/**
 *
 * @author Nasya Burrell, Karena Gayle, Justin Moo, Roshane Roach, Katelyn Tait, Tishawn Whyte 
 */
public class UpdatePart extends javax.swing.JFrame {

    /**
     * Creates new form UpdatePart
     */
    public UpdatePart() {
        initComponents();
        applyStyles();
        pack();
        setLocationRelativeTo(null);
    }

    private void applyStyles() {
        UIStyle.stylePanel(jPanel1);

        UIStyle.styleButton(jButton1);
        UIStyle.styleButton(jButton2);
        UIStyle.styleButton(updatebtn);

        jLabel1.setFont(UIStyle.LABEL_FONT);
        jLabel2.setFont(UIStyle.LABEL_FONT);
        jLabel3.setFont(UIStyle.LABEL_FONT);
        jLabel4.setFont(UIStyle.LABEL_FONT);
        jLabel13.setFont(UIStyle.LABEL_FONT);
        jLabel14.setFont(UIStyle.LABEL_FONT);
        jLabel15.setFont(UIStyle.LABEL_FONT);

        searchfield.setFont(UIStyle.LABEL_FONT);
        currPrice.setFont(UIStyle.LABEL_FONT);
        currStock.setFont(UIStyle.LABEL_FONT);
        currSupp.setFont(UIStyle.LABEL_FONT);
        newPrice.setFont(UIStyle.LABEL_FONT);
        newStock.setFont(UIStyle.LABEL_FONT);
        newSupp.setFont(UIStyle.LABEL_FONT);
    }


    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        searchfield = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        currPrice = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        currStock = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        newPrice = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        newStock = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        newSupp = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        currSupp = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        updatebtn = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 255, 0));

        jLabel1.setText("Search");

        searchfield.setText("Search by ID or Name");

        jLabel2.setText("Current Price");

        currPrice.setEditable(false);
        currPrice.setFocusable(false);

        jLabel3.setText("Current Stock");

        currStock.setEditable(false);
        currStock.setFocusable(false);

        jLabel4.setText("New Price");

        jLabel13.setText("New Stock");

        jLabel14.setText("New Supplier");

        jLabel15.setText("Current Supplier");

        currSupp.setEditable(false);
        currSupp.setFocusable(false);

        jButton2.setText("Search");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        updatebtn.setText("Update Part");
        updatebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatebtnActionPerformed(evt);
            }
        });

        jButton1.setText("Back");
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(currPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(searchfield, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(currStock, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(currSupp, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(newPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newStock, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(newSupp, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(updatebtn)
                .addGap(38, 38, 38)
                .addComponent(jButton1)
                .addGap(58, 58, 58))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(searchfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton2))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(newPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(currPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3))
                    .addComponent(newStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(currStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(34, 34, 34))
                            .addComponent(currSupp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(34, 34, 34))
                            .addComponent(newSupp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updatebtn)
                    .addComponent(jButton1))
                .addGap(21, 21, 21))
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

    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        String search = searchfield.getText();
        
        if (search.equals("Search by ID or Name") || search.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a part ID or name to search", "Search Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            File file = new File("parts.txt");
            
            if (!file.exists()) {
                JOptionPane.showMessageDialog(this, "Parts file not found", "File Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            boolean partFound = false;
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                
                if (parts.length >= 5 && (parts[0].equalsIgnoreCase(search) || parts[1].toLowerCase().contains(search.toLowerCase()))) {
                    currPrice.setText(parts[2]);
                    currStock.setText(parts[3]);
                    currSupp.setText(parts[4]);
                    
                    newPrice.setText("");
                    newStock.setText("");
                    newSupp.setText("");
                    
                    partFound = true;
                    break;
                }
            }
            
            reader.close();
            
            if (!partFound) {
                JOptionPane.showMessageDialog(this, "Part not found", "Search Error", JOptionPane.INFORMATION_MESSAGE);
                currPrice.setText("");
                currStock.setText("");
                currSupp.setText("");
                newPrice.setText("");
                newStock.setText("");
                newSupp.setText("");
            }
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading parts file: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void updatebtnActionPerformed(java.awt.event.ActionEvent evt) {
        String search = searchfield.getText();
        
        if (search.equals("Search by ID or Name") || search.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please search for a part first", "Update Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (currPrice.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please search for a part first", "Update Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            File file = new File("parts.txt");
            File tempFile = new File("temp_parts.txt");
            
            if (!file.exists()) {
                JOptionPane.showMessageDialog(this, "Parts file not found", "File Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            BufferedReader reader = new BufferedReader(new FileReader(file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            
            String line;
            boolean partFound = false;
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                
                if (parts.length >= 5 && (parts[0].equalsIgnoreCase(search) || parts[1].toLowerCase().contains(search.toLowerCase()))) {
                    String newPriceValue = newPrice.getText().isEmpty() ? parts[2] : newPrice.getText();
                    String newStockValue = newStock.getText().isEmpty() ? parts[3] : newStock.getText();
                    String newSuppValue = newSupp.getText().isEmpty() ? parts[4] : newSupp.getText();
                    
                    writer.write(parts[0] + ":" + parts[1] + ":" + newPriceValue + ":" + newStockValue + ":" + newSuppValue);
                    writer.newLine();
                    partFound = true;
                } else {
                    writer.write(line);
                    writer.newLine();
                }
            }
            
            reader.close();
            writer.close();
            
            if (!partFound) {
                JOptionPane.showMessageDialog(this, "Part not found", "Update Error", JOptionPane.INFORMATION_MESSAGE);
                tempFile.delete();
                return;
            }
            
            if (!file.delete()) {
                JOptionPane.showMessageDialog(this, "Could not delete original file", "Update Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (!tempFile.renameTo(file)) {
                JOptionPane.showMessageDialog(this, "Could not update parts file", "Update Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            JOptionPane.showMessageDialog(this, "Part updated successfully", "Update Success", JOptionPane.INFORMATION_MESSAGE);
            
            searchfield.setText("Search by ID or Name");
            currPrice.setText("");
            currStock.setText("");
            currSupp.setText("");
            newPrice.setText("");
            newStock.setText("");
            newSupp.setText("");
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error updating parts file: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        InventoryManager im = new InventoryManager();
        im.setVisible(true);
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
            java.util.logging.Logger.getLogger(UpdatePart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdatePart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdatePart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdatePart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UpdatePart().setVisible(true);
            }
        });
    }

    private javax.swing.JTextField currPrice;
    private javax.swing.JTextField currStock;
    private javax.swing.JTextField currSupp;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField newPrice;
    private javax.swing.JTextField newStock;
    private javax.swing.JTextField newSupp;
    private javax.swing.JTextField searchfield;
    private javax.swing.JButton updatebtn;
}
