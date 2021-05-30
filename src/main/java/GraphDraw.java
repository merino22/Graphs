
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
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author aaron
 */
public class GraphDraw extends JFrame {

    private static final long serialVersionUID = -2707712944901661771L;

    public GraphDraw(ArrayList vert, int[][] edges) {
        super("Graph Representation");

        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();
        ArrayList<Object> vertObjs = new ArrayList<>();
        graph.getModel().beginUpdate();
        try {
            int counterx = 30;
            int countery = 15;
            for (int i = 0; i < vert.size(); i++) {
                graph.orderCells(true);
                Object v1 = graph.insertVertex(parent, null, vert.get(i), counterx, countery, 80, 30);
                vertObjs.add(v1);
            }

            for (int i = 0; i < edges.length; i++) {
                for (int j = 0; j < edges[i].length; j++) {
                    int currentIdx = edges[i][j];
                    if (currentIdx == 1) {
                        graph.insertEdge(parent, null, "", vertObjs.get(i), vertObjs.get(j));
                    }
                }
            }
        } finally {
            graph.getModel().endUpdate();
        }
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        mxGraphModel graphModel = (mxGraphModel) graphComponent.getGraph().getModel();
        Collection<Object> cells = graphModel.getCells().values();
        mxUtils.setCellStyles(graphComponent.getGraph().getModel(), cells.toArray(), mxConstants.STYLE_ENDARROW, mxConstants.NONE);

        graphComponent.setEnabled(false);
        morphGraph(graph, graphComponent);
        JPanel panelGraph = new JPanel();
        JPanel panelOptions = new JPanel();

        GridLayout layoutOptions = new GridLayout(0, 2);
        panelOptions.setLayout(layoutOptions);
        JButton btnxd = new JButton("Weight");
        JTextField path = new JTextField();
        btnxd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                path.setText(getMaxWeight(edges));
            }
        });
        path.setEditable(false);
        JButton btnMinor = new JButton("Minor");
        JTextField path2 = new JTextField();
        path2.setEditable(false);
        btnMinor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                path2.setText(getMin(edges));
            }
        });
        JButton btnSumDegree = new JButton("Sum Of Degree");
        JTextField path3 = new JTextField();
        btnSumDegree.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                path3.setText(sumDegree(edges));
            }
        });
        path3.setEditable(false);

        JButton btnCycle = new JButton("Cycle");
        JTextField path4 = new JTextField();
        btnCycle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String text = detectCycleX(edges) == -1 ? "Cycle" : "No Cycle";
                path4.setText(text);
            }
        });
        JButton btnPath = new JButton("Ingresar Path");
        btnPath.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                JFrame f = new JFrame();
                String name = JOptionPane.showInputDialog(f, "Enter valid path for graph");
                ArrayList newPathVerts = highlightPathVerts(name, vert, edges);
                highlightPath(vert, edges, newPathVerts);
            }
        });
        path3.setEditable(false);
        panelOptions.add(btnxd);
        panelOptions.add(path);
        panelOptions.add(btnMinor);
        panelOptions.add(path2);
        panelOptions.add(btnSumDegree);
        panelOptions.add(path3);
        panelOptions.add(btnCycle);
        panelOptions.add(path4);
        panelOptions.add(btnPath);

        panelGraph.add(graphComponent);

        panelGraph.add(panelOptions);
        getContentPane().add(panelGraph);

    }

    public GraphDraw(ArrayList vert, int[][] edges, ArrayList highlightPath) {
        super("Path Representation");

        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();
        ArrayList<Object> vertObjs = new ArrayList<>();
        graph.getModel().beginUpdate();

        StringBuilder styleSB = new StringBuilder();
        String hexColor = "#00FF00";
        styleSB.append(mxConstants.STYLE_FILLCOLOR).append("=").append(hexColor).append(";");
        try {
            int counterx = 30;
            int countery = 15;
            for (int i = 0; i < vert.size(); i++) {
                graph.orderCells(true);
                Object v1 = graph.insertVertex(parent, null, vert.get(i), counterx, countery, 80, 30);
                if (highlightPath.indexOf(vert.get(i)) != -1) {
                    graph.getModel().setStyle(v1, styleSB.toString());
                }
                vertObjs.add(v1);
            }

            for (int i = 0; i < edges.length; i++) {
                for (int j = 0; j < edges[i].length; j++) {
                    int currentIdx = edges[i][j];
                    if (currentIdx == 1) {
                        graph.insertEdge(parent, null, "", vertObjs.get(i), vertObjs.get(j));
                    }
                }
            }
        } finally {
            graph.getModel().endUpdate();
        }
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        mxGraphModel graphModel = (mxGraphModel) graphComponent.getGraph().getModel();
        Collection<Object> cells = graphModel.getCells().values();
        mxUtils.setCellStyles(graphComponent.getGraph().getModel(), cells.toArray(), mxConstants.STYLE_ENDARROW, mxConstants.NONE);

        graphComponent.setEnabled(false);
        morphGraph(graph, graphComponent);
        JPanel panelGraph = new JPanel();
        JPanel panelOptions = new JPanel();

        GridLayout layoutOptions = new GridLayout(0, 2);
        panelOptions.setLayout(layoutOptions);
        JButton btnxd = new JButton("Weight");
        JTextField path = new JTextField();
        btnxd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                path.setText(getMaxWeight(edges));
            }
        });
        path.setEditable(false);
        JButton btnMinor = new JButton("Minor");
        JTextField path2 = new JTextField();
        path2.setEditable(false);
        btnMinor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                path2.setText(getMin(edges));
            }
        });
        JButton btnSumDegree = new JButton("Sum Of Degree");
        JTextField path3 = new JTextField();
        btnSumDegree.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                path3.setText(sumDegree(edges));
            }
        });
        path3.setEditable(false);

        JButton btnCycle = new JButton("Cycle");
        JTextField path4 = new JTextField();
        btnCycle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String text = detectCycleX(edges) == -1 ? "Cycle" : "No Cycle";
                path4.setText(text);
            }
        });
        JButton btnPath = new JButton("Ingresar Path");
        btnPath.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                JFrame f = new JFrame();
                String name = JOptionPane.showInputDialog(f, "Enter valid path for graph");
                ArrayList newPathVerts = highlightPathVerts(name, vert, edges);
                highlightPath(vert, edges, newPathVerts);
            }
        });
        path3.setEditable(false);
        panelOptions.add(btnxd);
        panelOptions.add(path);
        panelOptions.add(btnMinor);
        panelOptions.add(path2);
        panelOptions.add(btnSumDegree);
        panelOptions.add(path3);
        panelOptions.add(btnCycle);
        panelOptions.add(path4);
        panelOptions.add(btnPath);

        panelGraph.add(graphComponent);

        panelGraph.add(panelOptions);
        getContentPane().add(panelGraph);

    }

    public void highlightPath(ArrayList vert, int[][] edges, ArrayList newPathVerts) {
        GraphDraw draw = new GraphDraw(vert, edges, newPathVerts);
        draw.setSize(vert.size() * 150, vert.size() * 100);
        draw.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(false);
        draw.setVisible(true);
    }

    private static void morphGraph(mxGraph graph,
            mxGraphComponent graphComponent) {
        // define layout
        mxIGraphLayout layout = new mxCircleLayout(graph);
        // layout graph
        layout.execute(graph.getDefaultParent());

    }

    private ArrayList highlightPathVerts(String path, ArrayList verts, int[][]edges) {
        path = path.replace(",","");
        ArrayList<String> tmpArr = new ArrayList<String>();
        //New Codex =================================================
        //String start = String.valueOf(path.charAt(0));
        //int startInt = Integer.parseInt(start);
        tmpArr.add(String.valueOf(path.charAt(0)));
        for (int i = 0; i < path.length(); i++) {
            if(i == path.length()-1){
                return tmpArr;
            }
            String current = String.valueOf(path.charAt(i));
            int currentInt = Integer.parseInt(current);
            String next = String.valueOf(path.charAt(i+1));
            int nextInt = Integer.parseInt(next);
            
            if(edges[currentInt-1][nextInt-1] == 1){
                if (tmpArr.contains(String.valueOf(nextInt))) {
                    JOptionPane.showMessageDialog(new JFrame(), "Repeated vertice introduced", "ERROR",
        JOptionPane.ERROR_MESSAGE);
                    return tmpArr;
                }
                tmpArr.add(String.valueOf(nextInt));
            }
        }
        // ============================================================
        return tmpArr;
    }

    private int[] shortestReach(int startId, ArrayList vert){
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(startId);
        
        boolean[] visited = new boolean[vert.size()];
        int[] distances = new int[vert.size()];
        Arrays.fill(distances, -1);
        return distances;
    }
    
    private int detectCycleX(int[][] edges) {
        int[] visited = new int[edges.length];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = -1;
        }
        Queue<Integer> fifo = new LinkedList<Integer>();

        for (int i = 0; i < edges.length; i++) {
            if (i == 0) {
                fifo.add(i);
            }
            visited[i] = 1;
            for (int j = 0; j < edges[i].length; j++) {
                if (edges[i][j] == 1) {
                    boolean checkVisited = false;
                    boolean checkFifo = false;
                    for (int k = 0; k < visited.length; k++) {
                        if (j == visited[k]) {
                            checkVisited = true;
                        }
                    }
                    if (fifo.contains(j)) {
                        return -1;
                    }
                    if (checkVisited == false) {
                        fifo.add(j);
                    }
                }
            }
            fifo.remove();
        }
        return 0;
    }

    private String sumDegree(int[][] edges) {
        double counter = 0;
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges[i].length; j++) {
                if (edges[i][j] == 1) {
                    counter++;
                }
            }
        }
        double result = Math.ceil(counter / 2);
        return Integer.toString((int) result);
    }

    private String getMaxWeight(int[][] edges) {
        int maxWeight = -1;
        int maxIdx = -1;

        for (int i = 0; i < edges.length; i++) {
            int cntWeight = 0;
            for (int j = 0; j < edges[i].length; j++) {
                if (edges[i][j] == 1) {
                    cntWeight++;
                }
            }
            if (cntWeight > maxWeight) {
                maxWeight = cntWeight;
                maxIdx = i;
            }
        }
        return Integer.toString(maxWeight);
    }

    private String getMin(int[][] edges) {
        int minWeight = 1000000;
        int maxIdx = -1;

        for (int i = 0; i < edges.length; i++) {
            int cntWeight = 0;
            for (int j = 0; j < edges[i].length; j++) {
                if (edges[i][j] == 1) {
                    cntWeight++;

                }
            }
            //System.out.println(cntWeight);
            if (cntWeight < minWeight) {
                minWeight = cntWeight;
                maxIdx = i;
            }
        }
        return Integer.toString(minWeight);
    }

}
