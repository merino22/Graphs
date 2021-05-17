/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JFrame;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import javax.swing.JButton;
import javax.swing.JPanel;
/**
 *
 * @author aaron
 */

public class MenuInput extends javax.swing.JFrame {

    /**
     * Creates new form MenuInput
     */
    public MenuInput() {
        initComponents();
    }
    
    public void GenerateAdjMatrix()
    {
        String vertices = jTextField1.getText();
        String edges = jTextField2.getText();
        ArrayList vertArr =  getVertices(vertices);
        int[][] edgesArr = getEdges(edges,vertArr);
        DrawGraph(vertArr, edgesArr);
    }
    
    public void DrawGraph(ArrayList vertArrx, int[][]edgesArr)
    {
        GraphDraw draw = new GraphDraw(vertArrx, edgesArr);
        draw.setSize(vertArrx.size()*150, vertArrx.size()*100);
        JPanel panelGraph = new JPanel();
        JPanel panelOptions = new JPanel();
        JButton btnGetPeso = new JButton();
        btnGetPeso.setText("Peso");
        panelOptions.add(btnGetPeso);
        //draw.add(panelOptions);
        draw.setVisible(true);
    }
    
    public ArrayList getVertices(String verticesx)
    {
        int counter = 1;
        for (int i = 0; i < verticesx.length(); i++) {
            char currentChar = verticesx.charAt(i);
            if(currentChar != ',')
            {
                counter++;
            }
        }
        ArrayList<String> tmpArr = new ArrayList<String>(); 
        int arrCounter = 0;
        for (int i = 0; i < verticesx.length(); i++) {
            char currentChar = verticesx.charAt(i);
            if(currentChar != ',')
            {
                String tmpStr = "";
                tmpStr += currentChar;
                tmpArr.add(tmpStr);
                arrCounter++;
            }
        }
        return tmpArr;
    }
    
    public int[][] getEdges(String edgesx, ArrayList vertArr)
    {
        int sizeArr = vertArr.size();
        
        
        ArrayList[][] edgesTmpx = new ArrayList[sizeArr][sizeArr];
        int[][] edgesTmp = new int[sizeArr][sizeArr];
        //ArrayList<ArrayList<int>> list2 = new ArrayList(Collections.nCopies(10, new ArrayList(Collections.nCopies(10, 0d))));
        
        for (int i = 0; i < sizeArr; i++) {
            for (int j = 0; j < sizeArr; j++) {
                edgesTmp[i][j] = 0;
                System.out.println(edgesTmp[i][j]);
            }
        }
        
        for (int i = 0; i < edgesx.length(); i++) {
            char cChar = edgesx.charAt(i);
            if(cChar == '(')
            {
                int xpos = 0, ypos = 0;
                int checkComma = 0;
                while(cChar != ')')
                {
                    
                    i = i +1;
                    cChar = edgesx.charAt(i);
                    if(cChar != ',' && cChar != ')')
                    {
                        int idx = vertArr.indexOf(String.valueOf(cChar));
                        //System.out.println(cChar);
                        //System.out.print("Check Comma: " + checkComma);
                        //System.out.print("index: " + idx);
                        //System.out.println("");
                        if(idx >= 0)
                        {
                            if(checkComma == 0)
                            {
                                xpos = idx;
                            }else{
                                ypos = idx;
                            }
                        }
                    }
                    if(cChar == ')')
                    {
                        //System.out.println("( " + xpos + ", " + ypos + " )");
                        edgesTmp[xpos][ypos] = 1;
                        edgesTmp[ypos][xpos] = 1;
                    }
                    if(cChar == ',')
                    {
                        checkComma = 1;
                    }
                }
            }
        }
        
        for (int i = 0; i < edgesTmp.length; i++) {
            for (int j = 0; j < edgesTmp[i].length; j++) {
                System.out.print(edgesTmp[i][j] + " ");
            }
            System.out.println("");
        }
        return edgesTmp;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField1.setText("1,2,3,4,5");

        jTextField2.setText("(1,2),(2,3),(3,4),(4,5)");

        jLabel1.setText("V:");

        jLabel2.setText("E:");

        jButton1.setText("GENERATE");
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
                .addContainerGap(126, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1)
                            .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE))
                        .addGap(67, 67, 67))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(209, 209, 209))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(98, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLayeredPane1.setLayer(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.getAccessibleContext().setAccessibleName("jPanel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("JFrameX");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        GenerateAdjMatrix();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(MenuInput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuInput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuInput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuInput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuInput().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
