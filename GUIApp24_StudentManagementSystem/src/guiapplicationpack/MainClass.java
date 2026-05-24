package guiapplicationpack;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

class MainPanel extends JPanel
{
    private JLabel lblCaption,lblID,lblIDValue,lblName,lblAddress,lblPhone,lblSex,lblCourse;
    private JTextField txtName,txtAddress,txtPhone;
    private JComboBox cbxSex,cbxCourse;
    private JButton btnAddNew,btnSubmit,btnCancel,btnSave,btnSearchAll,btnSearchID,btnSearchName,btnSearchCourse;
    private JTable tabStudentData;
    private JScrollPane scpStudentData;
    private DefaultTableModel tblModel;
    private DefaultTableColumnModel tblColModel;
    private TableColumn tblColID,tblColName,tblColAddress,tblColPhone,tblColSex,tblColCourse;
    private static SearchFrame searchFrame = null;
    private Connection con ;
    private Statement smt;
    private int StudentID;
    private int studentID;
    private int lastRowIndex;
    private boolean unsavedData = false;
    
    private JLabel makeLabel(String cap,int x,int y,int w,int h,int mode)
    {
        JLabel temp = new JLabel(cap);
        temp.setBounds(x,y,w,h);
        if(mode == 0)
        {
            temp.setOpaque(true);
            temp.setBackground(Color.BLUE);
            temp.setForeground(Color.WHITE);
            temp.setFont(new Font("verdana",1,35));
            temp.setHorizontalAlignment(JLabel.CENTER);
            Border b1 = BorderFactory.createLineBorder(Color.RED, 3);
            Border b2 = BorderFactory.createLineBorder(Color.WHITE, 2);
            Border b3 = BorderFactory.createCompoundBorder(b1, b2);
            temp.setBorder(b3);
        }
        else if(mode == 1)
            temp.setFont(new Font("Courier New",1,16));
        else if(mode == 2)
        {
            temp.setOpaque(true);
            temp.setBackground(Color.WHITE);
            temp.setForeground(Color.BLACK);
            temp.setFont(new Font("Courier New",1,16));
            temp.setHorizontalAlignment(JLabel.CENTER);
            Border brdr = BorderFactory.createLineBorder(Color.BLACK, 1);
            temp.setBorder(brdr);
        }
        super.add(temp);
        return temp;
    }
    private JTextField makeTextField(int x,int y,int w,int h)
    {
        JTextField temp = new JTextField();
        temp.setFont(new Font("Courier New", 1, 18));
        temp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        temp.setBounds(x,y,w,h);
        temp.setHorizontalAlignment(JTextField.CENTER);
        add(temp);
        return temp;
    }
    private JComboBox makeComboBox(String[] items,int x,int y,int w,int h)
    {
        JComboBox temp = new JComboBox(items);
        temp.setFont(new Font("Verdana", 1, 12));
        temp.setBounds(x,y,w,h);
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
                if(ob == btnAddNew )
                {
                    studentID++;
                    lblIDValue.setText(String.valueOf(studentID)); 
               }
                else if(ob == btnSubmit)
                {
                    String id = lblIDValue.getText();
                    String name = txtName.getText();
                    String address = txtAddress.getText();
                    String phone = txtPhone.getText();
                    String sex = (String)cbxSex.getSelectedItem();
                    String course = (String)cbxCourse.getSelectedItem();
                    tblModel.addRow(new String[]{id,name,address,phone,sex,course});
                    lblIDValue.setText("");
                    txtName.setText("");
                    txtAddress.setText("");
                    txtPhone.setText("");
                    txtName.grabFocus();
                    unsavedData = true;
                }
                else if(ob == btnCancel)
                {
                    studentID--;
                    lblIDValue.setText("");
                    txtName.setText("");
                    txtAddress.setText("");
                    txtPhone.setText("");
                }
                else if( ob == btnSave)
                {
                    saveRecord();
                    unsavedData = false;
                }
                else if(ob == btnSearchAll)
                {
                    try
                    {
                        if(unsavedData)
                        {
                            saveRecord();
                            unsavedData = false;
                            
                        }
                        tblModel.setRowCount(0);
                        String qry = "SELECT ID, NAME, ADDRESS, PHONE, SEX, COURSE FROM STUDENT ORDER BY ID";
                        populateTable(qry);
                    }
                    catch(Exception ex)
                    {
                       JOptionPane.showMessageDialog(null,ex); 
                    }
                }
                else if(ob ==btnSearchID)
                {
                    try
                    {
                        if(unsavedData)
                        {
                            saveRecord();
                            unsavedData = false;
                            
                        }
                        System.setProperty("query_on","id");
                        createSeacrhFrame("SEARCHING STUDENT BY ID","ENTER STUDENT ID");
                    }
                    catch(Exception ex)
                    {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }
                else if(ob ==btnSearchCourse)
                {
                    try
                    {
                        if(unsavedData)
                        {
                            saveRecord();
                            unsavedData = false;
                            
                        }
                        System.setProperty("query_on","course");
                        createSeacrhFrame("SEARCHING STUDENT BY Course","ENTER Course");
                    }
                    catch(Exception ex)
                    {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }
                else if(ob ==btnSearchName)
                {
                    try
                    {
                        if(unsavedData)
                        {
                            saveRecord();
                            unsavedData = false;
                            
                        }
                        System.setProperty("query_on","name");
                        createSeacrhFrame("SEARCHING STUDENT BY NAME","ENTER STUDENT NAME");
                    }
                    catch(Exception ex)
                    {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }
            }
        });
        super.add(temp);
        return temp;
    }
    private TableColumn makeTableColumn(int idx,String cap,int width,DefaultTableCellRenderer centreRenderer)
    {
        TableColumn temp = new TableColumn(idx);
        temp.setHeaderValue(cap);
        temp.setMaxWidth(width);
        temp.setCellRenderer(centreRenderer);
        tblColModel.addColumn(temp);
        return temp;
    }
    private String getColCaption(String cap)
    {
        String caption = "<html><p style = 'font-family:verdana;font-weight:bold;font-size:13pt;'>"+cap+"</p></html>";
        return caption;
    }
    private void applicationInit()
    {
        
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","HR","hr");
            DatabaseMetaData dbMeta = con.getMetaData();
            ResultSet dbaseRset = dbMeta.getTables(null,"HR","STUDENT",new String[]{"TABLE"});
            smt = con.createStatement();
            String qry = "";
            studentID = 1000;
            if(!dbaseRset.next())
            {
                qry = "CREATE TABLE STUDENT (ID NUMBER(4) PRIMARY KEY,NAME VARCHAR(20),ADDRESS VARCHAR(20),PHONE VARCHAR(10),SEX VARCHAR(6),COURSE VARCHAR(5))";
                smt.executeUpdate(qry);
                lastRowIndex = 0;
            }
            else
            {
                qry = "SELECT ID,NAME,ADDRESS,PHONE,SEX,COURSE FROM STUDENT ORDER BY ID";
                populateTable(qry);
            }
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
    
    }
   private void populateTable(String qry)
    {
        try
        {
            ResultSet qryRSet = smt.executeQuery(qry);
            while(qryRSet.next())
            {
                studentID = qryRSet.getInt(1);
                String name = qryRSet.getString(2);
                String address = qryRSet.getString(3);
                String phone = qryRSet.getString(4);
                String sex = qryRSet.getString(5);
                String course = qryRSet.getString(6);
                tblModel.addRow(new String[]{String.valueOf(studentID),name,address,phone,sex,course});
                lastRowIndex = qryRSet.getRow();
            }
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
   private void saveRecord()
    {
        try
        {
            String qry = "";
            
            for(;lastRowIndex<tabStudentData.getRowCount();lastRowIndex++)
            {
                int id = Integer.parseInt((String)tabStudentData.getValueAt(lastRowIndex,0));
                String name = "'"+(String)tabStudentData.getValueAt(lastRowIndex,1)+"'";
                String address = "'"+(String)tabStudentData.getValueAt(lastRowIndex,2)+"'";
                String phone = "'"+(String)tabStudentData.getValueAt(lastRowIndex,3)+"'";
                String sex = "'"+(String)tabStudentData.getValueAt(lastRowIndex,4)+"'";
                String course = "'"+(String)tabStudentData.getValueAt(lastRowIndex,5)+"'";
                qry = "INSERT INTO STUDENT VALUES("+id+","+name+","+address+","+phone+","+sex+","+course+")";
                smt.executeUpdate(qry);
            }
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
   private void createSeacrhFrame(String frameTitle,String labelCaption)
   {
       int width = 430;
       int height = 210;
       searchFrame = new SearchFrame(frameTitle,labelCaption,this);
       searchFrame.setModal(true);
       searchFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
       searchFrame.setTitle(frameTitle);
       searchFrame.setSize(width,height);
       searchFrame.setResizable(false);
       searchFrame.setLocationRelativeTo(null);
       searchFrame.setVisible(true);
       
       
   }
   protected void prepareQuery(String qryArgument)
   {
       if(qryArgument != null)
       {
           String qryOn = System.getProperty("query_on");
           String qryString = "";
           if(qryOn.equals("id"))
            qryString = "select id, name, address,phone,sex,course from student where id ="+Integer.parseInt(qryArgument)+"order by id"; 
            else if(qryOn.equals("name"))
            qryString = "select id, name, address,phone,sex,course from student where name like '"+qryArgument+"%'order by id"; 
            else if(qryOn.equals("course"))
            qryString = "select id, name, address,phone,sex,course from student where course = '"+qryArgument+"' order by id";   
           tblModel.setRowCount(0);
           populateTable(qryString);
           
       }
   }
   
    public MainPanel()
    {
        Toolkit tk = Toolkit.getDefaultToolkit();
        lblCaption = makeLabel("STUDENT RECORD MANAGEMENT SYSTEM",10,10,tk.getScreenSize().width-40,60,0);
        lblID      = makeLabel("STUDENT ID",      10, 80,200,30,1);
        lblIDValue = makeLabel("",               210, 80,250,30,2);
        lblName    = makeLabel("STUDENT NAME",    10,120,200,30,1);
        txtName    = makeTextField(                  210,120,250,30);
        lblAddress = makeLabel("ADDRESS",         10,160,200,30,1);
        txtAddress = makeTextField(                  210,160,250,30);
        lblPhone   = makeLabel("PHONE NUMBER",    10,200,200,30,1);
        txtPhone   = makeTextField(                  210,200,250,30);
        lblSex     = makeLabel("SEX",             10,240, 50,30,1);
        cbxSex     = makeComboBox(new String[]{"Male","Female","Other"},70,240,120,30);
        lblCourse  = makeLabel("COURSE",         210,240, 80,30,1);
        cbxCourse  = makeComboBox(new String[]{"BTech","BCA","BBA","BSc","MTech","MCA","MBA","MSc"},310,240,150,30);
        btnAddNew  = makeButton("Add New",     10,280,100,30);
        btnSubmit  = makeButton("Submit",     127,280,100,30);
        btnCancel  = makeButton("Cancel",     244,280,100,30);
        btnSave    = makeButton("Save",       361,280,100,30);
        btnSearchAll = makeButton("Search All",10,320,217,30);
        btnSearchID = makeButton("Search By ID",244,320,217,30);
        btnSearchName = makeButton("Search By Name",10,360,217,30);
        btnSearchCourse = makeButton("Search By Course",244,360,217,30);
        
        int tableWidth = tk.getScreenSize().width - 500;
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        tblColModel = new DefaultTableColumnModel();
        tblColID = makeTableColumn(0,getColCaption("ID No."),(int)(tableWidth*0.10),centerRenderer);
        tblColName = makeTableColumn(1,getColCaption("Student Name"),(int)(tableWidth*0.25),centerRenderer);
        tblColAddress = makeTableColumn(2,getColCaption("Address"),(int)(tableWidth*0.25),centerRenderer);
        tblColPhone = makeTableColumn(3,getColCaption("Phone Number"),(int)(tableWidth*0.20),centerRenderer);
        tblColSex = makeTableColumn(4,getColCaption("Sex"),(int)(tableWidth*0.10),centerRenderer);
        tblColCourse = makeTableColumn(5,getColCaption("Course"),(int)(tableWidth*0.10),centerRenderer);
        
        tblModel = new DefaultTableModel();
        tblModel.setColumnCount(6);
        
        tabStudentData = new JTable(tblModel,tblColModel);
        tabStudentData.setFont(new Font("Courier New",1,16));
        tabStudentData.setRowHeight(25);
        tabStudentData.setEnabled(false);
        
        scpStudentData = new JScrollPane(tabStudentData);
        scpStudentData.setBounds(470,80,tableWidth,310);
        scpStudentData.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        super.add(scpStudentData);
        applicationInit();
        
    }
    
}
class MainFrame extends JFrame
{
    private MainPanel panel;
    public MainFrame()
    {
        panel = new MainPanel();
        panel.setBackground(new Color(225,250,160));
        panel.setLayout(new BorderLayout());
        super.add(panel);
    }
}
public class MainClass
{
    public static void main(String[] args)
    {
        Toolkit tk = Toolkit.getDefaultToolkit();
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(tk.getScreenSize().width, 440);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Student Record Management System");
        frame.setResizable(false);
    }
}