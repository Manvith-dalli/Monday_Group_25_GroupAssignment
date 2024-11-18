/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui;

import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.ProductManagement.Product;
import model.Supplier.Supplier;

/**
 *
 * @author rakshakmoorthy
 */
public class FinalReportJPanel extends javax.swing.JPanel {
    private JPanel workArea;
    private Supplier supplier;
    /**
     * Creates new form FinalReportJPanel
     */
    public FinalReportJPanel(JPanel workArea, Supplier supplier) {
        System.out.println("Initializing FinalReportJPanel");
    initComponents();
    this.workArea = workArea;
    this.supplier = supplier;
    System.out.println("Supplier in FinalReportJPanel: " + (supplier != null ? supplier.getName() : "null"));
    initializePanel();
    populateTable();
    System.out.println("FinalReportJPanel initialization complete");
    }
    private void initializePanel() {
        // Set preferred size to match other panels
        setPreferredSize(new Dimension(900, 800));
        
        // Configure the table
        tblReport.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane1.setPreferredSize(new Dimension(850, 400));
        
        // Set column sizes
        tblReport.getColumnModel().getColumn(0).setPreferredWidth(150); // Product Name
        tblReport.getColumnModel().getColumn(1).setPreferredWidth(100); // Target Price
        tblReport.getColumnModel().getColumn(2).setPreferredWidth(120); // Before Adjustment
        tblReport.getColumnModel().getColumn(3).setPreferredWidth(120); // After Adjustment
        tblReport.getColumnModel().getColumn(4).setPreferredWidth(100); // Sales Volume
        tblReport.getColumnModel().getColumn(5).setPreferredWidth(150); // Below Target
        tblReport.getColumnModel().getColumn(6).setPreferredWidth(150); // Above Target
    }
    private void populateTable() {
    DefaultTableModel model = (DefaultTableModel) tblReport.getModel();
    model.setRowCount(0); // Clear existing data

    if (supplier != null && supplier.getProductCatalog() != null) {
        for (Product product : supplier.getProductCatalog().getProductList()) {
            Object[] row = new Object[7];
            
            try {
                // Basic information
                row[0] = product.toString();
                
                // Format prices as integers or doubles based on their type
                int targetPrice = product.getTargetPrice();
                row[1] = String.format("$%d", targetPrice);  // Changed from %.2f to %d
                
                // Previous and current prices
                row[2] = String.format("$%d", targetPrice);  // Changed from %.2f to %d
                row[3] = String.format("$%d", targetPrice);  // Changed from %.2f to %d
                
                // Sales volume - assuming it returns an integer
                row[4] = product.getSalesVolume();
                
                // Calculate frequencies
                int belowTarget = 0;
                int aboveTarget = 0;
                
                // Calculate frequencies if you have the data
                // You'll need to implement this based on your data structure
                
                row[5] = belowTarget;
                row[6] = aboveTarget;
                
                model.addRow(row);
                
            } catch (Exception e) {
                System.out.println("Error processing product: " + product.toString());
                e.printStackTrace();
            }
        }
    }
    
    // Refresh the table
    tblReport.revalidate();
    tblReport.repaint();
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblReport = new javax.swing.JTable();
        lblReport = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();

        tblReport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Product Name", "Target Price", "Price Before Adjustment", "Price After Adjustments", "Sales Volume", "Sales Frequency Below Target", "Sales Frequency Above Target"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblReport);

        lblReport.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        lblReport.setText("Final Report");

        btnBack.setText("<< Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 873, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(btnBack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblReport)
                .addGap(431, 431, 431))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(lblReport))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(btnBack)))
                .addGap(40, 40, 40)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(206, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
       workArea.remove(this);
        CardLayout layout = (CardLayout)workArea.getLayout();
        layout.previous(workArea);
    }//GEN-LAST:event_btnBackActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblReport;
    private javax.swing.JTable tblReport;
    // End of variables declaration//GEN-END:variables
}
