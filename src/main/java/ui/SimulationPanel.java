/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import model.ProductManagement.Product;
import model.Supplier.Supplier;

/**
 *
 * @author rakshakmoorthy
 */
public class SimulationPanel extends javax.swing.JPanel {
    Supplier supplier;
    JPanel workArea;
    Product product;
    private Map<Product, Double> originalPrices;
    private Map<Product, Double> adjustedPrices;
    /**
     * Creates new form SimulationPanel
     */
     public SimulationPanel(JPanel workArea, Supplier supplier, 
                          Map<Product, Double> originalPrices, 
                          Map<Product, Double> adjustedPrices) {
        initComponents();
        this.workArea = workArea;
        this.supplier = supplier;
        this.originalPrices = originalPrices;
        this.adjustedPrices = adjustedPrices;
        populateTable();
    }
     public SimulationPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ResultLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ResultTable = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();
        btmToMax = new javax.swing.JButton();

        ResultLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        ResultLabel.setText("Simulation Result");

        ResultTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Product", "Previous Price", "Adjusted Price", "Projected Sales Revenue", "Profit Margin"
            }
        ));
        jScrollPane1.setViewportView(ResultTable);

        btnBack.setText("< Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btmToMax.setText("Profit Maximization");
        btmToMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmToMaxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBack)
                        .addGap(241, 241, 241)
                        .addComponent(ResultLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 759, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(276, 276, 276)
                        .addComponent(btmToMax, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack)
                    .addComponent(ResultLabel))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(btmToMax)
                .addContainerGap(282, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
         workArea.remove(this);
        CardLayout layout = (CardLayout)workArea.getLayout();
        layout.previous(workArea);
         
       
    }//GEN-LAST:event_btnBackActionPerformed

    private void btmToMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmToMaxActionPerformed
        // TODO add your handling code here:
    ProfitMaximizationJPanel profitMaximizationJPanel = new ProfitMaximizationJPanel(workArea, supplier);
    workArea.add(profitMaximizationJPanel, "ProfitMaximizationJPanel");
    CardLayout cardLayout = (CardLayout) workArea.getLayout();
    cardLayout.show(workArea, "ProfitMaximizationJPanel");
        
    }//GEN-LAST:event_btmToMaxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ResultLabel;
    private javax.swing.JTable ResultTable;
    private javax.swing.JButton btmToMax;
    private javax.swing.JButton btnBack;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
private void populateTable() {
    DefaultTableModel model = (DefaultTableModel) ResultTable.getModel();
    model.setRowCount(0); // Clear existing rows
    
    if (supplier != null && supplier.getProductCatalog() != null) {
        for (Product product : supplier.getProductCatalog().getProductList()) {
            Object[] row = new Object[5];
            double originalPrice = originalPrices.get(product);
            double adjustedPrice = adjustedPrices.get(product);
            int salesVolume = product.getSalesVolume();
            double priceChange = (adjustedPrice - originalPrice) / originalPrice;
            double elasticity = -1.5; // Example elasticity coefficient
            double volumeChange = elasticity * priceChange;
            int projectedVolume = (int) (salesVolume * (1 + volumeChange));
                
            double projectedRevenue = adjustedPrice * projectedVolume;
                
                // Calculate profit margin
            double costPrice = product.getFloorPrice();
            double profitMargin = ((adjustedPrice - costPrice) / adjustedPrice) * 100;

            row[0] = product.getName(); // Product
            row[1] = String.format("$%.2f", originalPrice); // Previous Price
            
            // Calculate adjusted price (you can modify this logic)
            // Example: 10% increase
            row[2] = String.format("$%.2f", adjustedPrice);
            
            // Calculate projected sales revenue
            int projectedSales = product.getSalesVolume(); // Get current sales volume
            
            row[3] = String.format("$%.2f", projectedRevenue);
            
            // Calculate profit margin
           
            row[4] = String.format("%.2f%%", profitMargin);
            
            model.addRow(row);
        }
    }
    
    // Set column headers if needed
    String[] columnNames = {
        "Product",
        "Previous Price",
        "Adjusted Price",
        "Projected Sales Revenue",
        "Profit Margin"
    };
    
    for (int i = 0; i < columnNames.length; i++) {
        ResultTable.getColumnModel().getColumn(i).setHeaderValue(columnNames[i]);
    }
    
    // Adjust column widths
    ResultTable.getColumnModel().getColumn(0).setPreferredWidth(150);  // Product name
    ResultTable.getColumnModel().getColumn(1).setPreferredWidth(100);  // Previous price
    ResultTable.getColumnModel().getColumn(2).setPreferredWidth(100);  // Adjusted price
    ResultTable.getColumnModel().getColumn(3).setPreferredWidth(150);  // Projected revenue
    ResultTable.getColumnModel().getColumn(4).setPreferredWidth(100);  // Profit margin
}

}