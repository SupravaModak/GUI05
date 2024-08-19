package guiapp05;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

class Customer{
    private String name;
    private String address;
    private String pwd;
    public Customer()
    {
        name=address=pwd="";
    }
    public Customer(String name,String address,String pwd)
    {
        this.name=name;
        this.address=address.replace('\n',' ');
        this.pwd=pwd;
    }
    public String getName(){
        return this.name;
    }
    public String getAddress(){
        return this.address;
    }
    public String getPwd(){
        return this.pwd;
    }
}
class MainPanel extends JPanel
{
    private JLabel          lblName,lblAddress,lblPwd;
    private JTextField      txtName;
    private JTextArea       txtAddress,txtReport;
    private JScrollPane     spnAddress,spnReport;
    private JPasswordField  txtPwd;
    private JButton btnPwd,btnSubmit,btnCommit,btnShow,btnExit,btnBack;
    private Vector<Customer> custList=new Vector<Customer>();
    private JLabel makeLabel(String cap,int x,int y,int w,int h)
    {
        JLabel temp = new JLabel(cap);
        temp.setFont(new Font("Courier New",1,24));
        temp.setBounds(x,y,w,h);
        add(temp);
        return temp;
    }
    private JComponent makeTextBox(int x,int y,int w,int h,int mode)
    {
        JComponent temp = null;
        if(mode == 0)
            temp = new JTextField();
        else if(mode == 1)
            temp = new JPasswordField();
        temp.setFont(new Font("Courier New",1,22));
        temp.setBounds(x,y,w,h);
        super.add(temp);
        return temp;
    }
    private JButton makeButton(String cap,int x,int y,int w,int h)
    {
        JButton temp = new JButton(cap);
        temp.setFont(new Font("Verdana",1,14));
        temp.setBounds(x,y,w,h);
        temp.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                Object ob = e.getSource();
                 if(ob==btnSubmit)
                {
                    Customer temp=new Customer(txtName.getText(),txtAddress.getText(),txtPwd.getText());
                    custList.add(temp);
                    txtName.setText("");
                    txtAddress.setText("");
                    txtPwd.setText("");
                    txtName.grabFocus();
                }
                else if(ob==btnCommit)
                {
                    
                }
                else if(ob==btnShow)
                {
                    for(Customer temp:custList)
                    {
                        String msg="";
                        msg=String.format("%-20s",temp.getName())+"|"+String.format("%-50s",temp.getAddress()+"|"+String.format("%-30s",temp.getPwd()));
                        txtReport.append(msg);
                    }
                    
                    setVisible();
                }
                else if(ob==btnExit)
                {
                    System.exit(0);
                }
                else if(ob==btnBack)
                {
                    setVisible();
                }
                
            }
        });
        add(temp);
        return temp;
    }
    private void setVisible(){
            
        lblName.setVisible(!lblName.isVisible());
        lblAddress.setVisible(!lblAddress.isVisible());
        lblPwd.setVisible(!lblPwd.isVisible());
        txtName.setVisible(!txtName.isVisible());
        spnAddress.setVisible(!spnAddress.isVisible());
        txtPwd.setVisible(!txtPwd.isVisible());
        spnReport.setVisible(!spnReport.isVisible());
        btnPwd.setVisible(!btnPwd.isVisible());
        btnSubmit.setVisible(!btnSubmit.isVisible());
        btnCommit.setVisible(!btnCommit.isVisible());
        btnShow.setVisible(!btnShow.isVisible());
        btnExit.setVisible(!btnExit.isVisible());
        btnBack.setVisible(!btnBack.isVisible());
        
    }
    public MainPanel()
    {
        lblName = makeLabel("Customer Name",           10,10,250,30);
        txtName = (JTextField)makeTextBox(  250,10,300,30,0);
        txtName.setHorizontalAlignment(JTextField.CENTER);
        
        lblAddress =makeLabel("Customer Address",        10,60,250,30);
        txtAddress = new JTextArea();
        txtAddress.setLineWrap(true);
        txtAddress.setFont(new Font("Courier New",1,22));
        spnAddress = new JScrollPane(txtAddress);
        spnAddress.setBounds(               250,60,300,100);
        spnAddress.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        super.add(spnAddress);
        
        lblPwd = makeLabel("Enter Password",         10,180,250,30);
        txtPwd = (JPasswordField)makeTextBox(250,180,240,30,1);
        txtPwd.setHorizontalAlignment(JTextField.CENTER);
        txtPwd.setEchoChar('*');
        
        btnPwd = makeButton("",500,180,50,30);
        btnPwd.addMouseListener(new MouseListener()
        {
             @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
            
            txtPwd.setEchoChar('\0');
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            
            txtPwd.setEchoChar('*');
            }
               
            @Override
            public void mouseEntered(MouseEvent e) {}
                
            @Override
            public void mouseExited(MouseEvent e) {}
              
        });
        
        btnSubmit = makeButton("Submit",40,240,100,30);
        btnCommit = makeButton("Commit",180,240,100,30);
        btnShow = makeButton("Show",320,240,100,30);
        btnExit = makeButton("Exit",460,240,100,30);
        
        txtReport = new JTextArea();
        txtReport.setEditable(false);
        txtReport.setFont(new Font("Courier New",1,20));
        spnReport = new JScrollPane(txtReport);
        spnReport.setBounds(               10,10,570,220);
        spnReport.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        spnReport.setVisible(false);
        super.add(spnReport);
        btnBack = makeButton("Back",250,240,100,30);
        btnBack.setVisible(false);
    }
}
class MainFrame extends JFrame
{
    private MainPanel panel;
    public MainFrame()
    {
        panel = new MainPanel();
        panel.setBackground(Color.YELLOW);
        panel.setLayout(new BorderLayout());
        super.add(panel);
    }
}
public class MainClass
{
    public static void main(String[] args)
    {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("New User Regestration");
        frame.setSize(600, 330);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }
}
