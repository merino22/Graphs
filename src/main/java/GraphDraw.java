
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxFastOrganicLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.util.mxMorphing;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.util.mxUtils;
import com.mxgraph.view.mxGraph;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aaron
 */
public class GraphDraw extends JFrame{
    private static final long serialVersionUID = -2707712944901661771L;

	public GraphDraw(ArrayList vert, int[][] edges)
	{
		super("Oligarquia!!");

		mxGraph graph = new mxGraph();
		Object parent = graph.getDefaultParent();
                ArrayList<Object> vertObjs = new ArrayList<>();
		graph.getModel().beginUpdate();
		try
		{
                    int counterx = 30;
                    int countery = 15;
                    for (int i = 0; i < vert.size(); i++) {
                        graph.orderCells(true);
                        Object v1 = graph.insertVertex(parent, null, vert.get(i), counterx, countery, 80,30);
                        vertObjs.add(v1);
                        //System.out.println(vertObjs.get(i));
                    }
			
                    for (int i = 0; i < edges.length; i++) {
                        for (int j = 0; j < edges[i].length; j++) {
                            int currentIdx = edges[i][j];
                            if(currentIdx == 1)
                            {
                                //graph.insertEdge(parent, null, "(" + (i+1) + "," + (j+1) + ")", vertObjs.get(i), vertObjs.get(j));
                                graph.insertEdge(parent, null, "", vertObjs.get(i), vertObjs.get(j));
                            }
                        }
                    }
		}
		finally
		{
			graph.getModel().endUpdate();
		}
                mxGraphComponent graphComponent = new mxGraphComponent(graph); 
                mxGraphModel graphModel = (mxGraphModel)graphComponent.getGraph().getModel(); 
                Collection<Object> cells = graphModel.getCells().values(); 
                mxUtils.setCellStyles(graphComponent.getGraph().getModel(), cells.toArray(), mxConstants.STYLE_ENDARROW, mxConstants.NONE); 
   
                graphComponent.setEnabled(false);
                morphGraph(graph, graphComponent);
                JPanel panelGraph = new JPanel();
                JPanel panelOptions = new JPanel();
                
                GridLayout layoutOptions = new GridLayout(0,2);
                panelOptions.setLayout(layoutOptions);
                JButton btnxd = new JButton("Weight");
                JTextField path = new JTextField();
                btnxd.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae){  
                        path.setText(getMaxWeight(edges));
                    }
                 });
                path.setEditable(false);
                JButton btnMinor = new JButton("Minor");
                JTextField path2 = new JTextField();
                path2.setEditable(false);
                btnMinor.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae){  
                        path2.setText(getMin(edges));
                    }
                 });
                panelOptions.add(btnxd);
                panelOptions.add(path);
                panelOptions.add(btnMinor);
                panelOptions.add(path2);
                //getContentPane().add(btnxd);
                panelGraph.add(graphComponent);
                
                panelGraph.add(panelOptions);
                getContentPane().add(panelGraph);
                //getContentPane().add(panelOptions);
                
		
	}
        
        private static void morphGraph(mxGraph graph,
                        mxGraphComponent graphComponent) {
                // define layout
                mxIGraphLayout layout = new mxCircleLayout(graph);
                // layout graph
                layout.execute(graph.getDefaultParent());

        }
        
        private String getMaxWeight(int[][] edges)
        {
            int maxWeight = -1;
            int maxIdx = -1;
            
            for (int i = 0; i < edges.length; i++) {
                int cntWeight = 0;
                for (int j = 0; j < edges[i].length; j++) {
                    if(edges[i][j] == 1){
                        cntWeight++;
                    }
                }
                if(cntWeight > maxWeight){
                    maxWeight = cntWeight;
                    maxIdx = i;
                }
            }
            return Integer.toString(maxWeight);
        }
        
        private String getMin(int[][] edges)
        {
            int minWeight = 1000000;
            int maxIdx = -1;
            
            for (int i = 0; i < edges.length; i++) {
                int cntWeight = 0;
                for (int j = 0; j < edges[i].length; j++) {
                    if(edges[i][j] == 1){
                        cntWeight++;
                        
                    }
                }
                System.out.println(cntWeight);
                if(cntWeight < minWeight){
                    minWeight = cntWeight;
                    maxIdx = i;
                }
            }
            return Integer.toString(minWeight);
        }
        
	public static void main(String[] args)
	{
		//GraphDraw frame = new GraphDraw();
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setSize(400, 320);
		//frame.setVisible(true);
	}
}
