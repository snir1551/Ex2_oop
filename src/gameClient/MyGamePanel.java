package gameClient;


import Server.Game_Server_Ex2;
import api.*;
import gameClient.util.Point3D;
import gameClient.util.Range;
import gameClient.util.Range2D;
import gameClient.util.Range2Range;
import kotlin.Pair;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class MyGamePanel extends JPanel {

    private directed_weighted_graph graph;
    private ArrayList<Pokemon> pokemon;
    private ArrayList<Agent> agent;
    private Arena arena;
    private node_location location;


    /**
     * Constructor
     */

    public void update(Arena arena) {
        this.arena = arena;
        graph = new DWGraph_DS(arena.getGraph());
        location = new NodeLocation(graph);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        if(graph != null) {
            int w = this.getWidth();
            int h = this.getHeight();
            g.clearRect(0, 0, w, h);
            drawGraph(g);
            drawPokemons(g);
            drawAgent(g);
        }
    }




    private void drawGraph(Graphics g)
    {

        g.setColor(Color.blue);
        double maxX = location.getMaxXNodeData().getLocation().x();
        double minX = location.getMinXNodeData().getLocation().x();
        double maxY = location.getMaxYNodeData().getLocation().y();
        double minY = location.getMinYNodeData().getLocation().y();

        int r = 5;
        for(node_data nd : graph.getV())
        {

            double x = scale(nd.getLocation().x(),minX,maxX,20,this.getWidth()-20);
            double y = scale(nd.getLocation().y(),minY,maxY,this.getHeight()-10,150);
            g.fillOval((int)x - r, (int)y - r, 2*r, 2*r);
            g.drawString(""+nd.getKey(), (int)x- r, (int)y- r);

        }

        g.setColor(Color.black);
        for(node_data nd : graph.getV())
        {
            for(edge_data e : graph.getE(nd.getKey()))
            {
                node_data destination = graph.getNode(e.getDest());
                double x = scale(nd.getLocation().x(),minX,maxX,20,this.getWidth()-20);
                double y = scale(nd.getLocation().y(),minY,maxY,this.getHeight()-10,150);
                double xx = scale(destination.getLocation().x(),minX,maxX,20,this.getWidth()-20);
                double yy = scale(destination.getLocation().y(),minY,maxY,this.getHeight()-10,150);
                g.drawLine((int)x,(int)y,(int)xx,(int)yy);
            }
        }

    }

    private void drawPokemons(Graphics g)
    {
        pokemon = new ArrayList<>(arena.getPokemons());
        int r = 3;
        double maxX = location.getMaxXNodeData().getLocation().x();
        double minX = location.getMinXNodeData().getLocation().x();
        double maxY = location.getMaxYNodeData().getLocation().y();
        double minY = location.getMinYNodeData().getLocation().y();
        for(Pokemon p : pokemon)
        {
            if(p.getType() == -1)
                g.setColor(Color.orange);
            else
                g.setColor(Color.green);
            double x = scale(p.getLocation().x(),minX,maxX,20,this.getWidth()-20);
            double y = scale(p.getLocation().y(),minY,maxY,this.getHeight()-10,150);
            g.fillOval((int)x - r, (int)y - r, 2*r, 2*r);
        }
    }

    private void drawAgent(Graphics g)
    {
        agent = arena.getAgents();
        double maxX = location.getMaxXNodeData().getLocation().x();
        double minX = location.getMinXNodeData().getLocation().x();
        double maxY = location.getMaxYNodeData().getLocation().y();
        double minY = location.getMinYNodeData().getLocation().y();
        g.setColor(Color.red);
        int r = 5;
        for(Agent a : agent)
        {
            double x = scale(a.getLocation().x(),minX,maxX,20,this.getWidth()-20);
            double y = scale(a.getLocation().y(),minY,maxY,this.getHeight()-10,150);
            g.fillOval((int)x - r, (int)y - r, 2*r, 2*r);
        }

    }

    private double scale(double data, double r_min, double r_max, double t_min, double t_max)
    {
        double res = ((data - r_min) / (r_max-r_min)) * (t_max - t_min) + t_min;
        return res;
    }




}
