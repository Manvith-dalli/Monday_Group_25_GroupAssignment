/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import model.OrderManagement.OrderItem;
import model.ProductManagement.Product;
import model.Supplier.Supplier;

/**
 *
 * @author purvang
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
     public SimulationPanel(JPanel workArea, Supplier supplier, Map<Product, Double> originalPrices, Map<Product, Double> adjustedPrices) {
    System.out.println("Initializing SimulationPanel");
    initComponents();
    this.workArea = workArea;
    this.supplier = supplier;
    this.originalPrices = new HashMap<>(originalPrices); // Create new copy
    this.adjustedPrices = new HashMap<>(adjustedPrices); // Create new copy
    
    // Debug prints
    System.out.println("Received price maps in SimulationPanel:");
    for (Map.Entry<Product, Double> entry : this.originalPrices.entrySet()) {
        System.out.println("Product: " + entry.getKey().getName() + 
                          " Original: " + entry.getValue() + 
                          " Adjusted: " + this.adjustedPrices.get(entry.getKey()));
    }
    
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

private double calculateProjectedSales(int currentSales, double priceChangePercentage, Product product) {
    // Get price elasticity based on product type or historical data
    double priceElasticity = calculatePriceElasticity(product);
    
    // Calculate projected change in demand
    double demandChangePercentage = priceElasticity * priceChangePercentage;
    
    // Calculate new projected sales volume
    double projectedSales = currentSales * (1 + (demandChangePercentage / 100));
    
    // Apply constraints based on market conditions
    projectedSales = applyMarketConstraints(projectedSales, product);
    
    return projectedSales;
}
private double calculatePriceElasticity(Product product) {
    // Base elasticity
    double baseElasticity = -1.5; // Standard elastic product
    
    // Analyze historical data for more accurate elasticity
    double historicalElasticity = analyzeHistoricalElasticity(product);
    if (historicalElasticity != 0) {
        return historicalElasticity;
    }
    
    // Consider price position relative to market
    double currentPrice = product.getTargetPrice();
    double floorPrice = product.getFloorPrice();
    double ceilingPrice = product.getCeilingPrice();
    
    // Adjust elasticity based on price position
    if (currentPrice <= (floorPrice + (ceilingPrice - floorPrice) * 0.2)) {
        // Lower price range - less elastic
        baseElasticity *= 0.7;
    } else if (currentPrice >= (ceilingPrice - (ceilingPrice - floorPrice) * 0.2)) {
        // Higher price range - more elastic
        baseElasticity *= 1.3;
    }
    
    return baseElasticity;
}
private double analyzeHistoricalElasticity(Product product) {
    if (product.getOrderitems() == null || product.getOrderitems().isEmpty()) {
        return 0;
    }
    
    try {
        // Get historical price and volume changes
        List<OrderItem> orders = product.getOrderitems();
        double totalElasticity = 0;
        int validPeriods = 0;
        
        for (int i = 1; i < orders.size(); i++) {
            OrderItem current = orders.get(i);
            OrderItem previous = orders.get(i-1);
            
            double priceChange = (current.getActualPrice() - previous.getActualPrice()) / previous.getActualPrice();
            double volumeChange = (current.getQuantity() - previous.getQuantity()) / (double)previous.getQuantity();
            
            if (priceChange != 0) {
                double periodElasticity = volumeChange / priceChange;
                totalElasticity += periodElasticity;
                validPeriods++;
            }
        }
        
        return validPeriods > 0 ? totalElasticity / validPeriods : 0;
    } catch (Exception e) {
        return 0;
    }
}
private double applyMarketConstraints(double projectedSales, Product product) {
    // Set minimum and maximum realistic changes
    double maxIncrease = 2.0; // Maximum 100% increase
    double maxDecrease = 0.5; // Maximum 50% decrease
    
    int currentSales = product.getSalesVolume();
    
    // Apply constraints
    double constrainedSales = Math.min(currentSales * maxIncrease, 
                                     Math.max(currentSales * maxDecrease, projectedSales));
    
    // Consider ceiling price constraint
    if (product.getTargetPrice() >= product.getCeilingPrice()) {
        constrainedSales = Math.min(constrainedSales, currentSales);
    }
    
    return constrainedSales;
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ResultLabel;
    private javax.swing.JTable ResultTable;
    private javax.swing.JButton btmToMax;
    private javax.swing.JButton btnBack;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
private void populateTable() {
    DefaultTableModel model = (DefaultTableModel) ResultTable.getModel();
    model.setRowCount(0);
    
    if (supplier != null && supplier.getProductCatalog() != null) {
        for (Product product : supplier.getProductCatalog().getProductList()) {
            Object[] row = new Object[5];
            
            // Get prices
            double originalPrice = originalPrices.get(product);
            double adjustedPrice = adjustedPrices.get(product);
            
            // Get current sales volume
            int currentSalesVolume = product.getSalesVolume();
            
            // Calculate price change percentage
            double priceChangePercentage = ((adjustedPrice - originalPrice) / originalPrice) * 100;
            
            // Calculate projected sales using price elasticity
            double projectedSales = calculateProjectedSales(currentSalesVolume, priceChangePercentage, product);
            
            // Calculate projected revenue
            double projectedRevenue = adjustedPrice * projectedSales;
            
            // Calculate profit margin
            double costPrice = product.getFloorPrice();
            double profitMargin = ((adjustedPrice - costPrice) / adjustedPrice) * 100;

            // Populate row
            row[0] = product.getName();
            row[1] = String.format("$%.2f", originalPrice);
            row[2] = String.format("$%.2f", adjustedPrice);
            row[3] = String.format("$%.2f", projectedRevenue);
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
