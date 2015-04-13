import java.awt.*;
import java.net.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.*;
import java.nio.*;
import java.util.zip.*;
import java.lang.*;
import java.util.Vector;



class FileDetails implements Serializable
{
     Vector FileName;
     Vector DirName;
     String Drive;
}



//class tabnetinfo starts here
class TabNetInfo extends JPanel implements ActionListener
{
    JLabel Lblclient,Lbldriver;
    JComboBox Cmbclient,Cmbdrives;
    JButton Btnselect,Btncopy,Btnexpand,Btnback;
    JTable Flistdirectory,Flistfile;
    JScrollPane Jspdirectory,Jspfile;
    Object HEADDIRECTORY[]={"DIRECTORY NAME"};
    Object HEADFILE[]={"FILE NAME"};
    Object DATADIRECTORY[][]=new Object[100][1];
    Object DATAFILE[][]=new Object[100][1];
    TabNetInfo()
    {
        this.setLayout(null);

        Lblclient=new JLabel("Login Clients");
        Lbldriver=new JLabel("Drives");
        Cmbclient=new JComboBox();
        Cmbdrives=new JComboBox();
        Btnselect=new JButton("SELECT");
        this.Btnexpand=new JButton("EXPAND>>");
        this.Btnback=new JButton("<<BACK");
        Btncopy=new JButton("COPY");

        for(int i=0;i<100;i++)
        {
            DATADIRECTORY[i][0]="";
        }
        for(int i=0;i<100;i++)
        {
            DATAFILE[i][0]="";
        }
        this.Cmbdrives.addItem("c:");
        this.Cmbdrives.addItem("d:");
        this.Cmbdrives.addItem("e:");
        this.Cmbdrives.addItem("f:");
        this.Cmbdrives.addItem("g:");
        this.Cmbdrives.addItem("h:");
        this.Btnselect.addActionListener(this);
        this.Btnback.addActionListener(this);
        this.Btnexpand.addActionListener(this);
        this.Btncopy.addActionListener(this);
        Flistdirectory=new JTable(DATADIRECTORY,HEADDIRECTORY);
        Flistfile=new JTable(DATAFILE,HEADFILE);
        Jspdirectory=new JScrollPane(Flistdirectory,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        Jspfile=new JScrollPane(Flistfile,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        Design();
    }

    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==this.Btnselect)
        {
              try
               {
                   ObjectOutputStream Os=new ObjectOutputStream(ClientApp.socket.getOutputStream());
                   Os.writeObject("GetFile");
                   Os.writeObject(this.Cmbclient.getSelectedItem());
                   Os.writeObject(this.Cmbdrives.getSelectedItem() + "\\");
               }
               catch(Exception e)
               {

               }
        }
        if(ae.getSource()==this.Btnexpand)
        {
              try
               {
                   ObjectOutputStream Os=new ObjectOutputStream(ClientApp.socket.getOutputStream());
                   Os.writeObject("GetFile");
                   Os.writeObject(this.Cmbclient.getSelectedItem());
                   Os.writeObject(this.Flistdirectory.getValueAt(this.Flistdirectory.getSelectedRow(),0) + "\\");
               }
               catch(Exception e)
               {

               }
        }
        if(ae.getSource()==this.Btnback)
        {
              try
               {
                   String path=(String)this.Flistdirectory.getValueAt(this.Flistdirectory.getSelectedRow(),0);
                   path=path.substring(0,path.lastIndexOf("\\")-1);
                   path=path.substring(0,path.lastIndexOf("\\"));
                   ObjectOutputStream Os=new ObjectOutputStream(ClientApp.socket.getOutputStream());
                   Os.writeObject("GetFile");
                   Os.writeObject(this.Cmbclient.getSelectedItem());
                   Os.writeObject(path + "\\");
               }
               catch(Exception e)
               {

               }
        }
        if(ae.getSource()==this.Btncopy)
        {
          try
          {
           ObjectOutputStream Os=new ObjectOutputStream(ClientApp.socket.getOutputStream());
           Os.writeObject("CopyFile");
           Os.writeObject(this.Cmbclient.getSelectedItem());
           Os.writeObject(this.Flistfile.getValueAt(this.Flistfile.getSelectedRow(),0));
          }
          catch (Exception e) {

          }
        }
    }

    public void setPos(Component C,int x,int y,int w,int h)
    {
        this.add(C);
        C.setBounds(x,y,w,h);
        C.setVisible(true);
    }

    public void Design()
    {
        setPos(Lblclient,15,10,120,20);
        setPos(Cmbclient,15,30,100,20);
        setPos(Lbldriver,193,10,120,20);
        setPos(Cmbdrives,193,30,100,20);
        setPos(Btnselect,370,30,100,20);
        setPos(Jspdirectory,10,60,470,170);
        setPos(this.Btnback,10,245,100,20);
        setPos(this.Btnexpand,380,245,100,20);
        setPos(Jspfile,10,275,470,175);
        setPos(Btncopy,370,470,100,20);
        this.setBackground(new Color(226,225,251));
    }
}

//class tabinfo ends here



//class tabdownload starts here
class TabDownload extends JPanel implements ActionListener
{
    JComboBox cType;
    JButton submit;
    JButton down;
    JScrollPane jsp;
    JTable jt;
    String Path="";
    Object HEAD[]={"FileName","UploadBy","Size"};
    Object DATA[][]=new Object[100][3];

    public TabDownload()
    {
       this.setLayout(null);
       cType=new JComboBox();
       submit=new JButton("Submit");
       down=new JButton("Download");
       for(int i=0;i<100;i++)
        for(int j=0;j<3;j++)
         DATA[i][j]="";
       jt=new JTable(DATA,HEAD);
       jsp=new JScrollPane(jt,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
       submit.addActionListener(this);
       down.addActionListener(this);
       cType.addItem("Java");
       cType.addItem("C");
       cType.addItem("C++");
       cType.addItem("VB.Net");
       cType.addItem("C#.Net");
       Design();
    }

    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==down)//download1
        {
            if(this.jt.getValueAt(this.jt.getSelectedRow(),0).equals(""))
            {
                JOptionPane.showMessageDialog(this,"No File Selected...");
                return;
            }
            try
            {
                JFileChooser chooser = new JFileChooser();
		        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);//|JFileChooser.MULTI_SELECTION_ENABLED_CHANGED_PROPERTY);
                chooser.setBackground(Color.PINK);
                chooser.setMultiSelectionEnabled(true);

    	        int result = chooser.showOpenDialog(this);
    	        if(result == JFileChooser.CANCEL_OPTION)
    		     return;
    	         File temp = chooser.getSelectedFile();

        	   if(temp == null)//|| temp.getName().equals(""))
        	   {
        		JOptionPane.showMessageDialog(this, "INVALID FILE","File Dialog", JOptionPane.ERROR_MESSAGE);
        	    return;
               }
	           Path=temp.getPath();
               ObjectOutputStream Os=new ObjectOutputStream(ClientApp.socket.getOutputStream());
               Os.writeObject("Download");
               Os.writeObject(this.jt.getValueAt(this.jt.getSelectedRow(),0));
            }
            catch (Exception e)
            {

            }
        }
        if(ae.getSource()==submit)   //submit1
        {
            try
            {
               ObjectOutputStream Os=new ObjectOutputStream(ClientApp.socket.getOutputStream());
               Os.writeObject("GetList");
               Os.writeObject(this.cType.getSelectedItem());

            }
            catch (Exception e)
            {

            }
        }
    }
    private void SetPos(Component C,int x,int y,int w,int h)
    {
        this.add(C);
        C.setBounds(x,y,w,h);
    }
    private void Design()
    {
        SetPos(cType,30,30,120,20);
        SetPos(submit,170,30,100,20);
        SetPos(jsp,30,70,500,400);
        SetPos(down,300,30,130,20);
        this.setBackground(new Color(226,225,251));
    }
}
//class tabdownload ends here
//class new starts here
class NewUserForm extends JFrame
{
    JButton Btncheck,Btncreate,Btnregister,Btnbrowse;
    JLabel Lblloginid,Lblpassword,Lblname,Lblemailid,Lbladdress;
    JLabel Lblphone,LblCheckavailablity,Lblconfirm;
    JTextField Textloginid,Textname,Textemailid,Textphone;
    JTextArea Textaddress;
    JPasswordField Password,Passwordconfirm;
    Container PaneLogin;
    NewUserForm()
    {
        PaneLogin=this.getContentPane();
        PaneLogin.setLayout(null);
        this.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent ae)
            {
                System.exit(1);
            }
        });
        Lblloginid=new JLabel("Enter Login I.D.:");
        Lblpassword=new JLabel("Enter Password:");
        LblCheckavailablity=new JLabel("Check Availability of I.D.");
        Lblconfirm=new JLabel("Enter Confirm Password:");
        Lblname=new JLabel("Enter Your Name:");
        Lblemailid=new JLabel("Enter Your e-mail I.D:");
        Lblphone=new JLabel("Enter your Phone no.:");
        Lbladdress=new JLabel("Enter your Address.:");

        Btncheck=new JButton("Check");
        Btncreate=new JButton("Create");
        Btnregister=new JButton("Register");
        Btnbrowse=new JButton("Browse");

        Textloginid=new JTextField();
        Textname=new JTextField();
        Textemailid=new JTextField();
        Textphone=new JTextField();
        Textaddress=new JTextArea();

        Password=new JPasswordField();
        Passwordconfirm=new JPasswordField();

        Design();
    }

    public void setPos(Component C,int x,int y,int w,int h)
    {
        PaneLogin.add(C);
        C.setBounds(x,y,w,h);
        C.setVisible(true);
    }

    public void Design()
    {
        setPos(Btnbrowse,360,10,80,20);
        setPos(Lblloginid,75,20,150,20);
        setPos(Textloginid,180,20,150,20);
        setPos(LblCheckavailablity,25,50,150,20);
        setPos(Btncheck,180,50,80,20);
        setPos(Lblpassword,65,80,150,20);
        setPos(Password,180,80,150,20);
        setPos(Lblconfirm,15,110,150,20);
        setPos(Passwordconfirm,180,110,150,20);
        setPos(Lblname,60,140,150,20);
        setPos(Textname,180,140,150,20);
        setPos(Lblemailid,40,170,150,20);
        setPos(Textemailid,180,170,150,20);
        setPos(Lblphone,40,200,150,20);
        setPos(Textphone,180,200,150,20);
        setPos(Lbladdress,45,230,150,20);
        setPos(Textaddress,180,230,150,70);
        setPos(Btnregister,145,310,90,20);
    }
}
//class new user terminates here


//attach file class
       class AttachFiles extends JPanel  implements ActionListener
{
   JLabel cnamel,useridl,namel,descl,authorl,subjectl,browsel,sizel;
   JTextField  cnamet,useridt,namet,browset,authort,sizet,addcateg1;
   JTextArea desct;
   JComboBox cType;
   String Path="";
   JScrollPane jsp;
   String Spath="";
   JButton browse,attach,addcateg;
   String name=new String();
   public AttachFiles()
    {
    addcateg1=new JTextField();
    addcateg=new JButton("Add category");
     addcateg.addActionListener(this);
     name="some";
     namel=new JLabel("TITLE");
     descl=new JLabel("DESCRIPTION");
     authorl=new JLabel("AUTHOR");
     subjectl=new JLabel("CATEGORY");
     sizel=new JLabel("SIZE");
     browsel=new JLabel("");
     desct= new JTextArea();
     jsp=new JScrollPane(desct,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
     desct.setBackground(new Color(242,241,253));
     desct.setForeground(new Color(110,12,118));
     namet= new JTextField();
     sizet=new JTextField();
     cType=new JComboBox();

     authort= new JTextField();
     browset= new JTextField();
     cType.addItem("Java");
       cType.addItem("C");
       cType.addItem("C++");
       cType.addItem("VB.Net");
       cType.addItem("C#.Net");
     browse=new JButton("BROWSE");
     attach=new JButton("UPLOAD");
     this.setLayout(null);
     browse.addActionListener(this);
     attach.addActionListener(this);
     design();

    }


   void SetPos(Component C,int x,int y,int  w,int h)
    {
        this.add(C);
        C.setBounds(x,y,w,h);
        C.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae)
        {
          if(ae.getSource()==browse)  //browse1
           {
               JFileChooser chooser = new JFileChooser();
			   chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);//|JFileChooser.MULTI_SELECTION_ENABLED_CHANGED_PROPERTY);
               chooser.setBackground(Color.PINK);
               chooser.setMultiSelectionEnabled(true);

			   int result = chooser.showOpenDialog(this);
			   if(result == JFileChooser.CANCEL_OPTION)
				return;
			   File temp = chooser.getSelectedFile();

			   if(temp == null)//|| temp.getName().equals(""))
			   {
				JOptionPane.showMessageDialog(this, "INVALID FILE","File Dialog", JOptionPane.ERROR_MESSAGE);
			    return;
               }
			   else
			   {
                    Path=temp.getPath();
                    String fname=temp.getName();

                    System.out.println(fname);
                    browsel.setText(Path);

                    long size=temp.length();
                    this.sizet.setText(size + "");
                    if(size > (1024*1024*15))
                    {
                        JOptionPane.showMessageDialog(this,"File size must less than 1 MB","Upload",JOptionPane.ERROR_MESSAGE);
                        this.browsel.setText("");
                        this.sizet.setText("");
                        return;
                    }

                }

           }
           if(ae.getSource()==addcateg)  //add1
           {

                            if(addcateg1.getText().equals(""))
                  {
                    JOptionPane.showMessageDialog(this,"you haven't entered","enter something",JOptionPane.ERROR_MESSAGE);
                    return;
                  }         else
                            {
                            String category;
                            category=addcateg1.getText();
                            cType.addItem(category);
                            }
           }
           if(ae.getSource()==attach)  //upload1
           {
             if(namet.getText().equals("")||desct.getText().equals("")||authort.getText().equals("")||sizet.getText().equals("")||authort.getText().equals(""))
                  {
                    JOptionPane.showMessageDialog(this,"INSUFFICIENT DETAILS...\nRE-ENTER","ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);
                    return;
                  }
                  String name=this.browsel.getText().substring(this.browsel.getText().lastIndexOf("\\") +1);
                  String title=namet.getText();
                  String desc=desct.getText();
                  String auther=authort.getText();
                  String category=(String)cType.getSelectedItem();
                  Spath= browsel.getText();
             String Query= "insert into File_details values(" +
                                        "'" + name + "'," +
                                        "'" + title + "'," +
                                        "'" + auther + "'," +
                                        "'" + category + "'," +
                                        "'" + desc + "'," +
                                        "'" + ClientApp.CurUsr + "'," +
                                        "'" + new java.util.Date() + "'," +
                                        "'" + this.sizet.getText() + "')";

             System.out.println(Query);
             int status=1;//Dbase.Update(Query);
             try{

                    File F=new File(Path);

				    long size=F.length();

				    System.out.println("SIZE : " + size);
				    byte buf[]=new byte[(int)size];
                    //byte buf[]=new byte[(int)size];
                    FileInputStream Fin=new FileInputStream(Path);
				   // System.out.println(fname);
			  	    Fin.read(buf);
			  	    Vector UP=new Vector();
			  	    UP.add(buf);

                    ObjectOutputStream Os=new ObjectOutputStream(ClientApp.socket.getOutputStream());
                    Os.writeObject("Flupload");
                    Os.writeObject(name);
                    Os.writeObject(UP);
                    Os.writeObject(Query);
                  }
                  catch(Exception E)
                  {  System.out.println("in gt fr" + E);
                  }

             if(status>0)
                         {
                          JOptionPane.showMessageDialog(this,"Attached Succussfully","Confirmation",JOptionPane.PLAIN_MESSAGE);
                          this.setVisible(false);
                          try
                          {
                          ObjectOutputStream Os1=new ObjectOutputStream(ClientApp.socket.getOutputStream());
                          Os1.writeObject("successfully upload");
                          }
                           catch(Exception E)
                          {
                          //System.out.println("in gt fr" + E);
                          }
                         }
             else
                 {
                  JOptionPane.showMessageDialog(this," UNSuccessfull Attempt!\nRETRY...","Confirmation",JOptionPane.ERROR_MESSAGE);
                  return;
                 }


            }
        }
    void design()
    {
        SetPos(namel,10,10,120,20);
        SetPos(namet,140,10,200,20);
        SetPos(authorl,10,40,120,20);
        SetPos(authort,140,40,200,20);
        SetPos(subjectl,10,70,120,20);
        SetPos(cType,140,70,200,20);
        SetPos(sizel,10,100,120,20);
        SetPos(sizet,140,100,200,20);
        SetPos(descl,10,130,120,20);
        SetPos(jsp,140,130,300,100);
        SetPos(browse,10,240,120,20);
        SetPos(browsel,140,240,300,20);
        SetPos(attach,10,270,120,20);
        SetPos(addcateg,10,290,120,20);
        SetPos(addcateg1,10,310,120,20);
        this.setBackground(new Color(226,225,251));

     }
 }
//attach file class terminates here

class TabObjectsFrame extends JFrame
{
    JTabbedPane Tabobjectsframe;
    AttachFiles attach;
    TabNetInfo Netinfo;
    TabDownload download;
    Container TabObjectsframepane;

    TabObjectsFrame()
    {
        TabObjectsframepane=this.getContentPane();
        download=new TabDownload();
        attach=new AttachFiles();
        Netinfo=new TabNetInfo();
        Tabobjectsframe=new JTabbedPane();
        Tabobjectsframe.addTab("NetInfo",Netinfo);
        Tabobjectsframe.addTab("Upload",attach);
        Tabobjectsframe.addTab("Download",download);
        this.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent we)
            {
                try
                {
                    ObjectOutputStream Os=new ObjectOutputStream(ClientApp.socket.getOutputStream());
                    Os.writeObject("logout");
                }
                catch (Exception e) {

                }
                System.exit(1);
            }
        });
     TabObjectsframepane.add(Tabobjectsframe);
    }
}

//class ClientSideThread extends Thread starts here
class ClientSideThread extends Thread
{
  ClientSideThread()
  {
   Login.running=true;
   start();
  }

  public void run()
  {
    try
    {

      while(Login.running)
      {
          ObjectInputStream In=new ObjectInputStream(ClientApp.socket.getInputStream());
          String Str=(String)In.readObject();
          if(Str.equals("NewLog"))
          {
           String nname=(String)In.readObject();
           Login.tab.Netinfo.Cmbclient.addItem(nname);
          }

          if(Str.equals("AllLog"))
          {
           String cnt=(String)In.readObject();
            Login.tab.Netinfo.Cmbclient.removeAllItems();
           for(int i=0;i<Integer.parseInt(cnt);i++)
           {
            String nname=(String)In.readObject();
            Login.tab.Netinfo.Cmbclient.addItem(nname);
           }
          }
          if(Str.equals("list"))
          {
               Vector Res=(Vector)In.readObject();
                 for(int i=0;i<100;i++)
                  for(int j=0;j<3;j++)
                     Login.tab.download.DATA[i][j]="";
              for(int i=0;i<Res.size();i++)
              {
                  Vector sub=(Vector)Res.elementAt(i);
                  for(int j=0;j<3;j++)
                     Login.tab.download.DATA[i][j]=sub.elementAt(j);
              }
          }

          if(Str.equals("Download")) //download2
          {
               Vector V =(Vector)In.readObject();
               byte buf[]=(byte[])V.elementAt(0);
               FileOutputStream Fout=new FileOutputStream(Login.tab.download.Path);
               Fout.write(buf);
               Fout.close();
          }

          if(Str.equals("GetFile"))  //Send Drive Listing [request is recieved]
          {
              String dname=(String)In.readObject();
              String N=(String)In.readObject();
              File F=new File(dname);
              FileDetails FD=new FileDetails();
              if(F==null)
              {
                 FD.FileName=new Vector();
                 FD.DirName=new Vector();
                 FD.Drive=dname + " : NO SUCH DRIVE EXISTS...";
              }
              else
              {
                  String Flist[]=F.list();
                  FD.FileName=new Vector();
                  FD.DirName=new Vector();
                  FD.Drive=dname + " DRIVE LISTING...";
                  for(int i=0;i<Flist.length;i++)
                  {
                      File test=new File(dname + Flist[i]);
                      if(test.isDirectory())
                       FD.DirName.add(test.getPath());
                      if(test.isFile())
                       FD.FileName.add(test.getPath());
                  }
              }

              ObjectOutputStream Os=new ObjectOutputStream(ClientApp.socket.getOutputStream());
              Os.writeObject("AllDet"); //sending the drive details
              Os.writeObject(N);
              Os.writeObject(FD);
          }

          if(Str.equals("SendDet"))  //Recieve Drive Details  [someone has send]
          {
              FileDetails FD=(FileDetails)In.readObject();
               for(int i=0;i<100;i++)
                {
                    Login.tab.Netinfo.DATADIRECTORY[i][0]="";
                }
                for(int i=0;i<100;i++)
                {
                    Login.tab.Netinfo.DATAFILE[i][0]="";
                }
                System.out.println(FD.Drive);
              for(int i=0;i<FD.DirName.size();i++)
                  Login.tab.Netinfo.DATADIRECTORY[i][0]=FD.DirName.elementAt(i);
              for(int i=0;i<FD.FileName.size();i++)
                  Login.tab.Netinfo.DATAFILE[i][0]=FD.FileName.elementAt(i);
          }

          if(Str.equals("CopyFile"))  //Send Drive Listing [request is recieved]
          {
              String Path=(String)In.readObject();
              String N=(String)In.readObject();
              File F=new File(Path);

    		    long size=F.length();

    		    System.out.println("SIZE : " + size);
    		    byte buf[]=new byte[(int)size];
                FileInputStream Fin=new FileInputStream(Path);
    	  	    Fin.read(buf);
    	  	    Vector UP=new Vector();
    	  	    UP.add(buf);

                ObjectOutputStream Os=new ObjectOutputStream(ClientApp.socket.getOutputStream());
                Os.writeObject("FileLoad");
                Os.writeObject(N);
                Os.writeObject(UP);
          }

          if(Str.equals("FileLoad"))  //Recieve Drive Details  [someone has send]
          {
              Vector V=(Vector)In.readObject();
               byte buf[]=(byte[])V.elementAt(0);
                JFileChooser chooser = new JFileChooser();
			   chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);//|JFileChooser.MULTI_SELECTION_ENABLED_CHANGED_PROPERTY);
               chooser.setBackground(Color.PINK);
               chooser.setMultiSelectionEnabled(true);

			   int result = chooser.showOpenDialog(Login.tab.Netinfo);
			   if(result == JFileChooser.CANCEL_OPTION)
				return;
			   File temp = chooser.getSelectedFile();

			   if(temp == null)//|| temp.getName().equals(""))
			   {
				JOptionPane.showMessageDialog(Login.tab.Netinfo, "INVALID FILE","File Dialog", JOptionPane.ERROR_MESSAGE);
			    return;
               }
			   else
			   {
                   String Path=temp.getPath();
                   FileOutputStream Fout=new FileOutputStream(Path);
                   Fout.write(buf);
                   Fout.close();
               }
          }
      }
    }
    catch (Exception e)
    {
         System.out.println("Error on client : " + e);
         e.printStackTrace();
    }
  }

}
//class ClientSideThread extends Thread terminates here


//class login starts
class Login extends JFrame implements ActionListener
{
    JButton BtnsigninLogin,BtnregisterLogin,BtncancelLogin;
    JLabel LabelnameLogin,LabelpasswordLogin;
    JTextField TextnameLogin;
    JPasswordField PasswordLogin;
    Container PaneLogin;
    public static TabObjectsFrame tab;
    public static String CurUsr;
    public static transient boolean running=false;
    Login()
    {
        PaneLogin=this.getContentPane();
        PaneLogin.setLayout(null);
        this.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent ae)
            {
                System.exit(1);
            }
        });
        LabelnameLogin=new JLabel("Enter User Name:");
        LabelpasswordLogin=new JLabel("Enter Password:");
        BtnsigninLogin=new JButton("Sign in");
        BtnsigninLogin.addActionListener(this);
        BtnregisterLogin=new JButton("New User Register");
        BtnregisterLogin.addActionListener(this);
        BtncancelLogin=new JButton("Cancel");
        BtncancelLogin.addActionListener(this);
        TextnameLogin=new JTextField();
        PasswordLogin=new JPasswordField();
        Design();
    }

    public void setPos(Component C,int x,int y,int w,int h)
    {
        PaneLogin.add(C);
        C.setBounds(x,y,w,h);
        C.setVisible(true);
    }

    public void Design()
    {
        setPos(LabelnameLogin,40,20,150,20);
        setPos(TextnameLogin,160,20,150,20);
        setPos(LabelpasswordLogin,45,50,150,20);
        setPos(PasswordLogin,160,50,150,20);
        setPos(BtnsigninLogin,70,90,100,20);
        setPos(BtncancelLogin,190,90,100,20);
        setPos(BtnregisterLogin,70,125,220,20);
        PaneLogin.setBackground(new Color(226,225,251));
    }

    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==BtnregisterLogin)   //login1
        {
            final int WIDTH=500;
            final int HEIGHT=400;
            NewUserForm form=new NewUserForm();
            form.setTitle("Registration Form");
            Toolkit T=Toolkit.getDefaultToolkit();
            Dimension D=T.getScreenSize();
            form.setBounds(D.width/2-WIDTH/2,D.height/2-HEIGHT/2,WIDTH,HEIGHT);
            form.setVisible(true);
            this.setVisible(false);

        }
        if(ae.getSource()==BtnsigninLogin)
        {

            if(this.TextnameLogin.getText().equals("") || this.PasswordLogin.getText().equals(""))
            {
              JOptionPane.showMessageDialog(this,"Enter All Details","Login Form",JOptionPane.ERROR_MESSAGE);
              return;
            }
            try
            {
              ObjectOutputStream Os=new ObjectOutputStream(ClientApp.socket.getOutputStream());
              Os.writeObject("LoginDetails");
              Os.writeObject(this.TextnameLogin.getText());
              Os.writeObject(this.PasswordLogin.getText());
            }
            catch (Exception e)
            {

            }
            try
            {
             ObjectInputStream In=new ObjectInputStream(ClientApp.socket.getInputStream());
             String Str=(String)In.readObject();
             if(Str.equals("SUCCESS"))
             {
               final int HEIGHT=560;
               final int WIDTH=550;
               tab=new TabObjectsFrame();
               tab.setTitle("Start Window : " + this.TextnameLogin.getText());
               ClientApp.CurUsr=this.TextnameLogin.getText();
               Toolkit t=Toolkit.getDefaultToolkit();
               Dimension D=t.getScreenSize();
               tab.setBounds(D.width/2-WIDTH/2,D.height/2-HEIGHT/2,WIDTH,HEIGHT);
               tab.setVisible(true);
               this.setVisible(false);
               CurUsr= this.TextnameLogin.getText();
               new ClientSideThread();
               ObjectOutputStream Os=new ObjectOutputStream(ClientApp.socket.getOutputStream());
               Os.writeObject("FreshLog");
             }
             else
             {
              JOptionPane.showMessageDialog(this,"Invalid Login / Password ","Login Form",JOptionPane.ERROR_MESSAGE);
              return;
             }
            }
            catch (Exception e)
            {

            }


        }
         if(ae.getSource()==BtncancelLogin)
         {
             System.exit(1);
            this.setVisible(false);
        }
    }
}
//class login terminates


class IpWindow extends JFrame implements ActionListener
{
JButton Okipadd,Cancelipadd,Packetipadd,commands,repack;
    JLabel Labelipadd;
    JTextField Textipadd;
    Container Paneipadd;
    IpWindow()
    {
        Paneipadd=this.getContentPane();
        Paneipadd.setLayout(null);
        this.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent ae)
            {
                System.exit(1);
            }
        });
        Labelipadd=new JLabel("Enter IP Address");
        Okipadd=new JButton("OK");
        repack=new JButton("Repacketize");
        repack.addActionListener(this);
        Okipadd.addActionListener(this);
        Cancelipadd=new JButton("CANCEL");
        Cancelipadd.addActionListener(this);
        Packetipadd=new JButton("PACKET");
        Packetipadd.addActionListener(this);
        Textipadd=new JTextField();
        commands=new JButton("Commands");
        commands.addActionListener(this);
        Design();
    }
        public void setPos(Component C,int x,int y,int w,int h)
    {
        Paneipadd.add(C);
        C.setBounds(x,y,w,h);
        C.setVisible(true);
    }

    public void Design()
    {
        setPos(Okipadd,30,80,80,20);
        setPos(Cancelipadd,120,80,80,20);
        setPos(Labelipadd,60,20,150,20);
        setPos(Textipadd,30,40,170,20);
        //setPos(Packetipadd,30,100,170,20);
        //setPos(commands,30,120,170,20);
        setPos(repack,30,140,170,20);
        Paneipadd.setBackground(new Color(226,225,251));
    }

    public void actionPerformed(ActionEvent ae)
    {
         if(ae.getSource()==Okipadd)
        {
            if(this.Textipadd.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this,"IP Address not entered","IP Entry",JOptionPane.ERROR_MESSAGE);
                return;
            }
            try
            {
              ClientApp.Inet=InetAddress.getByName(this.Textipadd.getText());
              ClientApp.socket=new Socket(ClientApp.Inet,ClientApp.PORT);
            }
            catch (Exception e)
            {
               JOptionPane.showMessageDialog(this,"Cannot Connect To Server\nRe Enter IP Address","IP Entry",JOptionPane.ERROR_MESSAGE);
               this.Textipadd.setText("");
                return;
            }

            JOptionPane.showMessageDialog(this,"Successfully Connected To Server","IP Entry",JOptionPane.INFORMATION_MESSAGE);
            final int WIDTH=350;
            final int HEIGHT=240;
            Login login=new Login();
            login.setTitle("Login");
            Toolkit T=Toolkit.getDefaultToolkit();
            Dimension D=T.getScreenSize();
            login.setBounds(D.width/2-WIDTH/2,D.height/2-HEIGHT/2,WIDTH,HEIGHT);
            login.setVisible(true);
            this.setVisible(false);

        }
        if(ae.getSource()==repack)
        {
        if(this.Textipadd.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this,"IP Address not entered","IP Entry",JOptionPane.ERROR_MESSAGE);
                return;
            }
            try
            {
              ClientApp.commandinet=InetAddress.getByName(this.Textipadd.getText());
              ClientApp.repaksock=new Socket(ClientApp.commandinet,3399);
            }
            catch (Exception e)
            {
               JOptionPane.showMessageDialog(this,"Cannot Connect To Server\nRe Enter IP Address","IP Entry",JOptionPane.ERROR_MESSAGE);
               this.Textipadd.setText("");
                return;
            }
        JOptionPane.showMessageDialog(this,"Successfully Connected To Server","IP Entry",JOptionPane.INFORMATION_MESSAGE);
        final int WIDTH=1200;
        final int HEIGHT=700;
        commands1 M=new commands1();
        M.setTitle("Re-packetizing");
        M.Labelipadd=new JLabel("Enter file");
        M.Packetipadd.setVisible(false);
        M.commands.setVisible(false);
        M.Okipadd.setVisible(false);
        M.chooser.setVisible(true);
        M.file.setVisible(true);
        Toolkit T=Toolkit.getDefaultToolkit();
        Dimension D=T.getScreenSize();
        M.setBounds(D.width/2-WIDTH/2,D.height/2-HEIGHT/2,WIDTH,HEIGHT);
        M.setVisible(true);
        this.setVisible(false);


        }
        if(ae.getSource()==commands)
        {

        if(this.Textipadd.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this,"IP Address not entered","IP Entry",JOptionPane.ERROR_MESSAGE);
                return;
            }
            try
            {
              ClientApp.commandinet=InetAddress.getByName(this.Textipadd.getText());
              ClientApp.commandsocket=new Socket(ClientApp.commandinet,ClientApp.commandport);
            }
            catch (Exception e)
            {
               JOptionPane.showMessageDialog(this,"Cannot Connect To Server\nRe Enter IP Address","IP Entry",JOptionPane.ERROR_MESSAGE);
               this.Textipadd.setText("");
                return;
            }
        JOptionPane.showMessageDialog(this,"Successfully Connected To Server","IP Entry",JOptionPane.INFORMATION_MESSAGE);
        final int WIDTH=1200;
        final int HEIGHT=700;
        commands1 M=new commands1();
        M.setTitle("Commands Execution");
        M.file.setVisible(false);
        M.chooser.setVisible(false);
        Toolkit T=Toolkit.getDefaultToolkit();
        Dimension D=T.getScreenSize();
        M.setBounds(D.width/2-WIDTH/2,D.height/2-HEIGHT/2,WIDTH,HEIGHT);
        M.setVisible(true);
        this.setVisible(false);

        }
        if(ae.getSource()==Cancelipadd)
        {
            System.exit(1);
            this.setVisible(false);

        }

        if(ae.getSource()==Packetipadd)
        {
         final int WIDTH=350;
            final int HEIGHT=240;
            packet p1=new packet();
            p1.setTitle("Packet Module");
            Toolkit T=Toolkit.getDefaultToolkit();
            Dimension D=T.getScreenSize();
            p1.setBounds(D.width/2-WIDTH/2,D.height/2-HEIGHT/2,WIDTH,HEIGHT);
            p1.setVisible(true);
            this.setVisible(false);

        }
    }
}


class packet extends JFrame implements ActionListener
{
public static Socket spack;
JButton back,exit,enter;
JLabel dir,ip2;
JTextField file,ip;
Container paneipadd1;

InetAddress addr1,forhost;
File file1,ouptputfilechecker;
File outfile= new File("C:/blue/test");
//String outfile1="c:/blue/test1";
String fil,str1;
long checksum; //earlier it was long
//String header="Version=ipv4,
int len,port=3388;
FileInputStream in,intemp;
FileOutputStream out,outemp;
ByteArrayOutputStream bos = new ByteArrayOutputStream();   // writing again for checking
File rewrite2test = new File("c:/blue/java2.pdf");

DatagramPacket pak1;
DatagramSocket sok1;
byte buffer[],string2convert[];
byte buffert1[];
byte buffert2[];
byte buffert3[];
packet()
{
paneipadd1=this.getContentPane();
paneipadd1.setLayout(null);
this.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent ae)
            {
                System.exit(1);
            }
        });
dir=new JLabel("Enter direcotry of the file");
ip2=new JLabel("Enter ip address");
enter=new JButton("After entering directory and ip address click here");
enter.addActionListener(this);
back=new JButton("back");
back.addActionListener(this);
exit=new JButton("exit");
exit.addActionListener(this);

file=new JTextField();
ip=new JTextField();
Design();
}

public void setPos(Component C,int x,int y,int w,int h)
    {
        paneipadd1.add(C);
        C.setBounds(x,y,w,h);
        C.setVisible(true);
    }

public void Design()
    {
        setPos(back,30,160,80,20);
        setPos(exit,120,160,80,20);
        setPos(enter,10,20,320,20);
        setPos(dir,30,40,170,20);
        setPos(file,30,70,170,20);
        setPos(ip,30,140,170,20);
        setPos(ip2,210,140,170,20);
        paneipadd1.setBackground(new Color(226,225,251));
    }

    public void actionPerformed(ActionEvent ae)
    {
          if(ae.getSource()==enter)
        {
                 try
                 {
                 InetAddress addr1 = InetAddress.getLocalHost();
                 byte[] ipAddr1 = addr1.getAddress();
                 String hostname1 = addr1.getHostName();
                 System.out.println("HostName =  "         +hostname1);
                 System.out.println("Ip address  "          +addr1);
                 }
                 catch (UnknownHostException e)
                {
                System.out.println("Can't get the details");
                }
            if(this.file.getText().equals("") || this.ip.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this,"Both the field should be completed....","...please re-enter",JOptionPane.ERROR_MESSAGE);
                return;
            }
            else
            {
                         str1=this.ip.getText();
                         fil=file.getText();
                         file1=new File(fil);
                         if(!file1.exists())
                                {
                                System.out.println("file not found");
                                return;
                                }


            }



            //try
            //{
            //forhost=InetAddress.getByName(this.ip.getText());
            //}
            //catch (UnknownHostException e)
                //{
                //System.out.println("Can't get the hostt details");
                //}
            // now we are trying to get the connection accepted by the server

             try
            {
              //ClientApp.Inet=InetAddress.getByName(this.Textipadd.getText());
              forhost=InetAddress.getByName(this.ip.getText());


            }
            catch (Exception e)
            {
               JOptionPane.showMessageDialog(this,"Cant get ip address","IP Entry",JOptionPane.ERROR_MESSAGE);
               System.out.println("error details...."+e);
               this.ip.setText("");
                return;
            }

            // end of wat we trying to do

            System.out.println("directory address          "+fil);
            System.out.println("ip to which we are sending is          "+forhost);
            int len=0,t1,t2;//used in 60kb concept

            int size7 = (int)file1.length();
            //size7=size7/2;          not sure whether to minimize
            if(size7>1024*1024)
            {
            System.out.println("Size is greater than 1mb it can't continue");// if size is too greater than an mb program will exit
            System.exit(1);
            this.setVisible(false);
            }
            buffer = new byte[size7];

            try
            {

            if(outfile.exists())
                                {
                                System.out.println("file exists");
                                outfile.delete();
                                System.out.println("file deleted");
                                //return;
                                }

            in = new FileInputStream(fil);
            intemp=new FileInputStream(fil);
            out = new FileOutputStream(outfile, true);
            outemp = new FileOutputStream(outfile, true);
            }
            catch (IOException e)
                {
                System.out.println("Can't get the details");
                }


            CheckedInputStream cis;
            BufferedInputStream cis1;
            cis = new CheckedInputStream(intemp, new CRC32());
            cis1 = new BufferedInputStream(cis);
            try{
            while (cis1.read() > 0)
            {
            }
            System.out.println("Checksum is: " + cis.getChecksum().getValue());
            checksum=cis.getChecksum().getValue();
            System.out.println("Stored value of Checksum is: " +checksum);
            }
            catch (IOException e)
                {
                System.out.println("Can't calculate checksum");
                }
                try
                {
                DataOutputStream dos = new DataOutputStream(outemp);
                System.out.println("{{{ip address entered goin in  "+str1);
                String str = "Version = ipv4......ip address=";
                String str2="port no is=3388";

                out.write(str.getBytes());
                out.write(str1.getBytes());
                out.write(str2.getBytes());
                //dos.writeInt(port);
                dos.close();
                }
                catch (java.io.IOException e)
                {
                System.out.println("Can't get the details");
                }



        try
        {
          for (int len1;(len1 = in.read(buffer)) != -1;)
          {
           out.write(buffer, 0, len1); //no doubt here is 0
           //Writes len bytes from the specified byte array starting at offset off to this byte array output stream.
           bos.write(buffer, 0, len1);
           System.out.println("read " + len1 + " bytes,");
           len=len1;
           byte[] bytes = bos.toByteArray();
           FileOutputStream fos = new FileOutputStream(rewrite2test);
        fos.write(bytes);
        fos.flush();
        fos.close();


           }


        //len = in.read(buffer);

        //while (len != -1)
        //{
        //out.write(buffer, 0, len);
        //len = in.read(buffer);
        //}
        if(buffer.length>60*1024)
        {          t1=buffer.length;
                   t2=0;
                   buffert1=new byte[60*1024-1];
                   for(int m=0;m<60*1024-1;m++)
                     {
                            buffert1[m]=buffer[m];
                     }
                   t2=t1;         //storing the orignal length temporary
                   t1=t1-60*1024;//subtracted the amount copied
                   t1=t1+t2;//total range left
         System.out.println("working 1");
        if(t1-t2<61*1024)
        {
        buffert2=new byte[t1-t2+100];


        for(int c=0;c<t1-t2;c++)
                     {
                            buffert2[c]=buffer[c+t2-1];
                     }
        }
        }
        if(buffert1!=null)
        {
        System.out.println("Values in temporary buffer............"      +buffert1);
        System.out.println("length of temporary buffer t1............"   +buffert1.length);
        }
        if(buffert2!=null)
        {
        System.out.println("Values in temporary buffer............."      +buffert2);
        System.out.println("length of temporary buffer t1..........."   +buffert2.length);
        }
       String t4="#header packet=1........";
       String long1=""+checksum;
       String com = t4.concat(long1);
       String end ="........................fin# ";
       String com1=com.concat(end);
       System.out.println("String contatining header............"+com1);
       string2convert = com1.getBytes();
       System.out.println("displaying string to converted byte array............."+string2convert);
       byte[] all = new byte[buffer.length + string2convert.length];
       System.arraycopy(buffer, 0, all, 0, buffer.length-1);
       System.arraycopy(string2convert, 0, all,buffer.length,string2convert.length-1);
       System.out.println("value of all............."+all);

       //System.arraycopy(string2convert, 0, all, 0, string2convert.length-1);
       //System.arraycopy(buffer, 0, all,string2convert.length, buffer.length-1);
       String testlen="dfdefdfdf";
       int tt2,tt3,tt4;
       int tt1=testlen.length();
        //tt2=buffer.length();
        //tt3=string2convert.length();
        //tt4=all.length();
       System.out.println("length of a string ideaaa............."+tt1);
       System.out.println("length of a string buffer............."+buffer.length);
       System.out.println("length of a string string2convert............."+string2convert.length);
       System.out.println("length of a string alll............."+all.length);

       //System.arraycopy(string2convert, 0, all, 0, string2convert.length);     works a bit
       //System.arraycopy(buffer, 0, all,string2convert.length, buffer.length);  works a bit
      // System.arraycopy(string2convert, 0, all, buffer.length, string2convert.length);    dont work
      // System.arraycopy(buffer, 0, all, 0, buffer.length);                                dont work
       System.out.println("converted final byte array named all"+all);


       System.out.println("data in buffer");
       System.out.println(buffer);
       int bytesize;
       bytesize=buffer.length;
       System.out.println(bytesize);
       System.out.println("buffer read length");
       System.out.println(len);      // remove this comment
      in.close();
      out.close();

      buffer=null;//re initialize

      buffer=all; // adding the other details as well such as the string which was written
      }


            catch (java.io.IOException e)
                {
                System.out.println("Can't get the details");
                }




            DatagramPacket pak1 = new DatagramPacket(buffer, buffer.length,forhost,port);
            try
            {
            DatagramSocket sok1 = new DatagramSocket();
            sok1.send(pak1);
            sok1.close();
            }
            catch(Exception e)
            {
            System.out.println("can't send packet" +e);
            }
            System.exit(1);
            this.setVisible(false);
        }
        if(ae.getSource()==back)
        {

        final int WIDTH=250;
        final int HEIGHT=200;
        IpWindow M=new IpWindow();
        M.setTitle("I.P.Address");
        Toolkit T=Toolkit.getDefaultToolkit();
        Dimension D=T.getScreenSize();
        M.setBounds(D.width/2-WIDTH/2,D.height/2-HEIGHT/2,WIDTH,HEIGHT);
        M.setVisible(true);
            this.setVisible(false);
        }
        if(ae.getSource()==exit)
        {
            System.exit(1);
            this.setVisible(false);
        }

    }}

public class ClientApp
{   public static InetAddress commandinet;
    public static InetAddress Inet;
    public static Socket socket;
    public static Socket commandsocket;
    public static final int PORT=3344;
    public static final int commandport=3366;
    public static Socket repaksock;
    public static String CurUsr;
    public static void main(String args[])
    {
        final int WIDTH=250;
        final int HEIGHT=200;
        IpWindow M=new IpWindow();
        M.setTitle("I.P.Address");
        Toolkit T=Toolkit.getDefaultToolkit();
        Dimension D=T.getScreenSize();
        M.setBounds(D.width/2-WIDTH/2,D.height/2-HEIGHT/2,WIDTH,HEIGHT);
        M.setVisible(true);
    }
}


class commands1 extends JFrame implements ActionListener,Serializable
{
JButton Okipadd,Cancelipadd,Packetipadd,commands,file,chooser;
InetAddress localaddress;
byte[] bigboy;
int biglen=0;
FileInputStream intemp;
    JLabel Labelipadd;
    long checksum=0;
    JTextField Textipadd;
    Container Paneipadd;
    int sequence=0,num=1;
    String Path="",localname,stringoffile="";
    commands1()
    {
        Paneipadd=this.getContentPane();
        Paneipadd.setLayout(null);
        this.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent ae)
            {
                System.exit(1);
            }
        });
        Labelipadd=new JLabel("Enter directory");
        Okipadd=new JButton("OK");
        chooser=new JButton("choose file");
        file=new JButton("OK");
        chooser.addActionListener(this);
        Okipadd.addActionListener(this);
        file.addActionListener(this);
        Cancelipadd=new JButton("CANCEL");
        Cancelipadd.addActionListener(this);
        Packetipadd=new JButton("under construction");
        Packetipadd.addActionListener(this);
        Textipadd=new JTextField();
        commands=new JButton("test message");
        commands.addActionListener(this);
        Design();
    }
        public void setPos(Component C,int x,int y,int w,int h)
    {
        Paneipadd.add(C);
        C.setBounds(x,y,w,h);
        C.setVisible(true);
    }

    public void Design()
    {   setPos(chooser,30,60,80,20);
        setPos(Okipadd,30,80,80,20);
        setPos(file,30,80,80,20);
        setPos(Cancelipadd,120,80,80,20);
        setPos(Labelipadd,60,20,150,20);
        setPos(Textipadd,30,40,170,20);
        setPos(Packetipadd,30,100,170,20);
        setPos(commands,30,120,170,20);
        Paneipadd.setBackground(new Color(226,225,251));
    }

    public void actionPerformed(ActionEvent ae)
    {
         if(ae.getSource()==Okipadd)
        {
         try
            {
             if(this.Textipadd.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this,"Directory not entered","diectory entry",JOptionPane.ERROR_MESSAGE);
                return;
            }
              ObjectOutputStream Os=new ObjectOutputStream(ClientApp.commandsocket.getOutputStream());
              Os.writeObject("Directory");
              Os.writeObject(Textipadd.getText());
              System.out.println("value in textbox"+Textipadd.getText());
              //Os.writeObject(this.PasswordLogin.getText());
            }
            catch (Exception e)
            {

            }
             new ClientSideThread1();
        }

        if(ae.getSource()==commands)
        {
        try
            {
              ObjectOutputStream Os=new ObjectOutputStream(ClientApp.commandsocket.getOutputStream());
              Os.writeObject("LoginDetails");
              //Os.writeObject(this.TextnameLogin.getText());
              //Os.writeObject(this.PasswordLogin.getText());
            }
            catch (Exception e)
            {

            }


        }
        if(ae.getSource()==Cancelipadd)
        {

        }

        if(ae.getSource()==Packetipadd)
        {
        }
        if(ae.getSource()==file)       //packet1
        {
        try
        {

        File F=new File(Path);
        long size=F.length();
        byte buf[]=new byte[(int)size];
        FileInputStream Fin=new FileInputStream(Path);
        Fin.read(buf);
        Fin.close();
        //testing
        FileOutputStream pakett=new FileOutputStream("letsgetthat.mp3");
        pakett.write(buf);
        pakett.close();
        long size1=F.length();
        size1=size+1422;
        byte[] buff2=new byte[(int)size1];
        buff2=buf;
        //testing
        int t=0,flag2=0,loop=0,partscheck=0,char2copy=1422;
        long flag=F.length();
        long dataleft=F.length();
        System.out.println("SIZE : " + size);
        System.out.println("chunks to be created are" + num);
        //byte[] buff1=new byte[1048576];
        chunks z[]=new chunks[num];
        tcp tc[]=new tcp[num];
        ipheader ip[]=new ipheader[num];
        //dynamic loop
        int zv=0;
        int zv1=(int)flag;
        for(int i=0;i<num;i++)
        {
        z[i]=new chunks();
        z[i].dataoffset=zv;
        ip[i]=new ipheader();
        tc[i]=new tcp();
                                 for(int mv=0;mv<char2copy && zv<zv1;mv++,zv++)

                                 {
                                 z[i].buff[mv]=buff2[zv];
                                 //System.out.println("value of mv"+mv);
                                 //System.out.println("value of zv"+zv);
                                 //if(zv>4582700)
                                 //{
                                 //System.out.println("value of zv"+zv);
                                 //System.out.println("value of mv"+mv);
                                 //}
                                 }
                                 //System.out.println("value of mv"+mv);
                                 System.out.println("value of zv...."+zv);

                                 //System.out.println("code is working till here ...mark1");
                                  sequence=sequence+1;
                                   //buff1=null;
                                  ip[i].addrt=localaddress;
                                  ip[i].destinationt=ClientApp.commandinet;
                                  z[i].seqno=sequence;
                                  z[i].acknowledgementno=sequence+1;
                                  z[i].destination=ClientApp.commandinet;
                                  z[i].parts=num;
                                  z[i].filelength=(float)size;
                                  z[i].checksum=checksum;
                                  z[i].from=localname;
                                  z[i].addr=localaddress;
                                  z[i].lengthofpackets=z[i].buff.length;
                                  z[i].stringoffile=stringoffile;
                                  biglen=biglen+z[i].buff.length;
                                  loop=loop+1;
                                  System.out.println("now value of marker mv is..."+zv);
                                  int mlen=z[i].buff.length;
                                  System.out.println("length of data in m obj......."+mlen);
                                  System.out.println("packet ended..."+i);

        tc[i].tcpheader=z[i];
        }
        	  	    String p="3399";
			  	    String name=this.Textipadd.getText().substring(this.Textipadd.getText().lastIndexOf("\\") +1);
                    System.out.println(name);
			  	    ObjectOutputStream Os=new ObjectOutputStream(ClientApp.repaksock.getOutputStream());
                    //ObjectOutputStream Os1=new ObjectOutputStream(ClientApp.repaksock.getOutputStream());
                    Os.writeObject("repak");
                    Os.writeObject(loop);

                    for(int mn=0;mn<loop;mn++)
                    {
                    System.out.println("code is working till here1");
                    for(int x=999;x>0;x--)
                    {
                    }

                    Os.writeObject(tc[mn]);
                     System.out.println("code is working till here1");
                    if(loop==0)
                    break;
                     System.out.println("code is working till here1");
                    }

                    Os.writeObject(name);
                    Os.writeObject(z[0]);
                    //Os.writeObject(UP);

                    System.out.println("code is working till here2");
                    System.out.println("length of big boy"+biglen);
                    //test file under development
                    bigboy=new byte[biglen];
                    int stpos=0;
                    for(int x=0;x<loop;x++)
                {
                System.arraycopy(z[x].buff,0,bigboy,stpos,z[x].buff.length);
                stpos=stpos+z[x].buff.length;
                }
                try
                {
                System.out.println("length of bigboy.........."+bigboy.length);

                FileOutputStream paket=new FileOutputStream(name);
                paket.write(bigboy);
                //String pakloc=paket.getFD(
                paket.close();

                }
                catch(Exception e)
                {
                }
                    //


                  }

        catch(Exception E)
                  {  System.out.println("in gt fr" + E);
                  }

        }

        if(ae.getSource()==chooser)
        {

        JFileChooser chooser = new JFileChooser();
			   chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);//|JFileChooser.MULTI_SELECTION_ENABLED_CHANGED_PROPERTY);
               chooser.setBackground(Color.PINK);
               chooser.setMultiSelectionEnabled(true);

			   int result = chooser.showOpenDialog(this);
			   if(result == JFileChooser.CANCEL_OPTION)
				return;
			   File temp = chooser.getSelectedFile();

			   if(temp == null)//|| temp.getName().equals(""))
			   {
				JOptionPane.showMessageDialog(this, "INVALID FILE","File Dialog", JOptionPane.ERROR_MESSAGE);
			    return;
                            }
			   else
			   {
                    Path=temp.getPath();
                    String fname=temp.getName();
                    stringoffile=fname;
                    System.out.println(fname);
                    Textipadd.setText(Path);
                    try
                    {
                    localaddress=InetAddress.getLocalHost();
                    localname = localaddress.getHostName();
                    intemp=new FileInputStream(Path);
                    }
                    catch(Exception e)
                    {
                    }
                    CheckedInputStream cis;
                    BufferedInputStream cis1;
                    cis = new CheckedInputStream(intemp, new CRC32());
                    cis1 = new BufferedInputStream(cis);
                    try
                    {
                    while (cis1.read() > 0)
                    {
                    }
                    checksum=cis.getChecksum().getValue();
                    cis.close();
                    cis1.close();
                    intemp.close();
                    }

                    catch(Exception e)
                    {
                    System.out.println("Can't calculate checksum");
                    }
                    System.out.println("checksum is"+checksum);
                    System.out.println("local name  is"+localname);
                    System.out.println("local address  is"+localaddress);
                    long size=temp.length();

                    if(size > (1024*1024))
                    {
                        JOptionPane.showMessageDialog(this,"File size must less than 1 MB","Upload",JOptionPane.ERROR_MESSAGE);
                        //this.Textipadd.setText("");
                        float num1=size/1422;
                        num=(int)num1;
                        num=num+1;
                        System.out.println("size of file is    "+size);
                        System.out.println("chunks to be created    "+num);

                        //return;
                    }

                          }

        }

    }
}


class ClientSideThread1 extends Thread
{

  ClientSideThread1()
  {
   //commands1.running=true;
   start();
  }

 public void run()
  {
    try
    {

      while(true) //while(commands1.running)
      {
          ObjectInputStream In=new ObjectInputStream(ClientApp.commandsocket.getInputStream());
          String Str=(String)In.readObject();
          if(Str.equals("NewLog"))
          {
           String nname=(String)In.readObject();
           Login.tab.Netinfo.Cmbclient.addItem(nname);
          }

          if(Str.equals("Directory created"))
          {
          System.out.println("Directory created successfuly");
          //JOptionPane.showMessageDialog(this,"Directory created","acknowledgement",JOptionPane.INFORMATION_MESSAGE);
          }
          if(Str.equals("list"))
          {

          }

          if(Str.equals("Download"))
          {

          }

          if(Str.equals("GetFile"))  //Send Drive Listing [request is recieved]
          {

          }

          if(Str.equals("SendDet"))  //Recieve Drive Details  [someone has send]
          {

          }

          if(Str.equals("CopyFile"))  //Send Drive Listing [request is recieved]
          {

          }

          if(Str.equals("FileLoad"))  //Recieve Drive Details  [someone has send]
          {

          }
      }
    }
    catch (Exception e)
    {
         System.out.println("Error on client : " + e);
         e.printStackTrace();
    }
  }

}
class chunks implements Serializable   //packets1
{
int dataoffset=0;
int acknowledgementno=0;
int seqno=0;
short sourceport=3399;
short port=3399;//destination port
InetAddress addr;
long checksum=0;
int parts=0;
int lengthofpackets;
float filelength;
InetAddress destination;
String from="",stringoffile="";
byte buff[]=new byte[1422];
int reserved=0;
}
class ipheader implements Serializable
{
String version="ipv4";
int ihl=0;//internet HEADER LENGTH
String tos="file transfer";// TOS is type of service
InetAddress addrt;
InetAddress destinationt;
}

class tcp implements Serializable
{
chunks tcpheader;
ipheader iheadr;

}