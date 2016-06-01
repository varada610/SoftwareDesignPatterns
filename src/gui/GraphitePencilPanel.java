/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import compositePatternPackage.productPackage.GraphiteGrade;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 *Contained in the tabbed menu option for the graphite pencils in the art supplies.
 * @author Aditee Nagle */
public class GraphitePencilPanel extends JPanel implements ActionListener {
    private GUIPanelMediator mediator;
    private JButton clearAll;
    
    /**Constructor of the panel.
     * @param mediator GUIPanelMediator object.*/
    public GraphitePencilPanel(GUIPanelMediator mediator) {
        this.mediator = mediator;
        Border raisedbevel = BorderFactory.createRaisedBevelBorder();
        Border margin = new EmptyBorder(10,20,0,0);
        this.setBorder(BorderFactory.createCompoundBorder(raisedbevel, margin));

        this.clearAll = new JButton("Clear Selection");
        this.clearAll.setFont(new Font("Calibri",Font.BOLD,16));
        this.clearAll.addActionListener(this);
        this.clearAll.setActionCommand("clear");

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.add(Box.createRigidArea(new Dimension(0,10)));
        
        for(int index=0;index < GraphiteGrade.values().length;index++) {
            String gradeName = GraphiteGrade.values()[index].toString();
            JButton graphiteGrade = new JButton();
            graphiteGrade.setFont(new Font("Calibri",Font.BOLD,16));			
            graphiteGrade.addActionListener(this);
            graphiteGrade.setActionCommand(gradeName);
            graphiteGrade.setText("Add "+gradeName);
            this.add(graphiteGrade);
            add(Box.createRigidArea(new Dimension(0,8)));
        }
        
        this.add(Box.createRigidArea(new Dimension(0,30)));
        this.add(clearAll);
        
    }
    
    /**Events handled after button clicks on the panel
	 * @param event the action events initiated by buttons.*/
    @Override
    public void actionPerformed(ActionEvent event) 
    {
            String command = event.getActionCommand();

            if(command.equals("clear"))
                    this.mediator.removeAllGraphitePencils();		
            else
                    this.mediator.addGraphitePencil(GraphiteGrade.valueOf(command));

            this.mediator.setUserSelectionSummary(this.mediator.getOrderDetails());
    }
}
