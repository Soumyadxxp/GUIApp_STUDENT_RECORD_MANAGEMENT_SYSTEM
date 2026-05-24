package guiapplicationpack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

class SearchPanel extends JPanel
{
    private SearchFrame fatherFrame = null;
    private JLabel lblCaption, lblMSG;
    private JTextField txtValue;
    private JButton btnSubmit, btnCancel;
    private MainPanel targetPanel = null;
    private JLabel makeLabel(String cap,int x,int y,int w,int h,int mode)
    {
        JLabel temp = new JLabel(cap);
        temp.setBounds(x,y,w,h);
        if(mode == 0)
        {
            temp.setOpaque(true);
            temp.setBackground(Color.BLUE);
            temp.setForeground(Color.WHITE);
            temp.setFont(new Font("verdana",1,18));
            temp.setHorizontalAlignment(JLabel.CENTER);
            Border b1 = BorderFactory.createLineBorder(Color.RED, 3);
            Border b2 = BorderFactory.createLineBorder(Color.WHITE, 2);
            Border b3 = BorderFactory.createCompoundBorder(b1, b2);
            temp.setBorder(b3);
        }
        else if(mode == 1)
            temp.setFont(new Font("Courier New",1,16));
        super.add(temp);
        return temp;
    }
    private JTextField makeTextField(int x,int y,int w,int h)
    {
        JTextField temp = new JTextField();
        temp.setFont(new Font("Courier New", 1, 16));
        temp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        temp.setBounds(x,y,w,h);
        temp.setHorizontalAlignment(JTextField.CENTER);
        add(temp);
        return temp;
    }
     private JButton makeButton(String caption,int x,int y,int w,int h)
    {
        JButton temp = new JButton(caption);
        temp.setBounds(x,y,w,h);
        temp.setFont(new Font("Verdana", 1, 12));
        temp.setMargin(new Insets(0,0,0,0));
        temp.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Object ob = e.getSource();
                if( ob == btnSubmit)
                {
                    targetPanel.prepareQuery(txtValue.getText());
                }
                else if(ob == btnCancel)
                {
                    targetPanel.prepareQuery(null);
                }
                fatherFrame.dispose();
            }
        });
        super.add(temp);
        return temp;
    } 
     public SearchPanel(String frameTitle, String labelCaption, SearchFrame sFrame, MainPanel tgtPanel)
     {
         fatherFrame = sFrame;
         targetPanel = tgtPanel;
         Border brdr = BorderFactory.createLineBorder(Color.RED, 2);
         lblCaption = makeLabel(frameTitle,10,10,390,45,0);
         lblMSG = makeLabel(labelCaption,10,70,200,30,1);
         txtValue = makeTextField(210,70,190,30);
         btnSubmit = makeButton("Submit",66,120,120,30);
         btnCancel = makeButton("Cancel",242,120,120,30);
         
         
     }
}
public class SearchFrame extends JDialog
{
    
 private SearchPanel searchPanel = null;
  public SearchFrame(String frameTitle, String labelCaption, MainPanel tgtPanel)
  {
      searchPanel = new SearchPanel(frameTitle,labelCaption,this,tgtPanel);
      searchPanel.setBackground(new Color(220,250,200));
      searchPanel.setLayout(new BorderLayout());
      super.add(searchPanel);
      
  }
}
