import java.lang.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.nio.*;
import java.util.*;
import java.sql.*;
import java.util.Vector;

class commandscreen extends JFrame implements ActionListener
{   chunks bigchunks[]=new chunks[20000];
    byte[] bigboy;
    int bigboylen=0,length1=0;
    int packetsofcommands=0;
    ServerSocket cserver;
    JLabel name,name1,parts,destinationip,sourcepcname;
    JLabel password,password1;
    JLabel confirm_password,checksum,filelength;
    JLabel login_id,login_id1;
    JLabel mail_id,mail_id1;
    JLabel contact_no,contact_no1,location;
    JTextField nameText,partstext,filelengthtext,destinationiptext,sourcepcnametext,seqbttext;
    JPasswordField pwdText;
    JPasswordField conpwdText;
    JTextField loginText,a1,a2,locationt;
    JTextField mailText;
    JTextField conText;
    JButton OK,CANCEL,seqbt;
    Container pane;

    public commandscreen()
    {   for(int i=0;i<30;i++)
        {
        bigchunks[i]=new chunks();
        }
        pane=this.getContentPane();
        pane.setLayout(null);
            this.name1=new JLabel("Name");
            this.password1=new JLabel("Packet Size");
            this.login_id1=new JLabel("Packet Sequence no");
            this.mail_id1=new JLabel("Port no");
            this.location=new JLabel("Location of file");
            this.sourcepcname=new JLabel("Source pc name");
            this.contact_no1=new JLabel("Source address");
            this.destinationip=new JLabel("Destination ip");
            this.parts=new JLabel("Parts of packet");
            this.filelength=new JLabel("Length of file");
        this.name=new JLabel("under construction");
        this.password=new JLabel("under construction");
        this.confirm_password=new JLabel("under construction");
        this.checksum=new JLabel("Checksum");
        this.login_id=new JLabel("under construction");
        this.mail_id=new JLabel("under construction");
        this.contact_no=new JLabel("under construction");
        this.nameText=new JTextField();
        this.locationt=new JTextField();
        this.sourcepcnametext=new JTextField();
        this.destinationiptext=new JTextField();
        this.seqbttext=new JTextField();
        this.filelengthtext=new JTextField();
        this.partstext=new JTextField();
        this.pwdText=new JPasswordField();
        this.conpwdText=new JPasswordField();

        this.a1=new JTextField();
        this.a2=new JTextField();

        this.loginText=new JTextField();
        this.mailText=new JTextField();
        this.conText=new JTextField();
        OK=new JButton("OK");
        seqbt=new JButton("Pak Sequence");
        CANCEL=new JButton("CANCEL");
        OK.addActionListener(this);
        seqbt.addActionListener(this);
        CANCEL.addActionListener(this);
        Design();
    }
    private void SetPos(Component C,int x,int y,int w,int h)
    {
         pane.add(C);
         C.setBounds(x,y,w,h);
    }

    private void Design()
    {
        SetPos(this.name,20,30,100,20);
        SetPos(this.name1,20,30,100,20);
        SetPos(this.parts,20,50,100,20);
        SetPos(this.nameText,140,30,100,20);    //new
        SetPos(this.seqbt,260,30,100,20);
        SetPos(this.seqbttext,260,50,100,20);
        SetPos(this.partstext,140,50,100,20);   //new
        SetPos(this.password,20,70,100,20);
        SetPos(this.password1,20,70,100,20);
        SetPos(this.pwdText,140,70,100,20);
        SetPos(this.a1,140,70,100,20);
        //
        SetPos(this.filelength,20,90,100,20);
        SetPos(this.filelengthtext,140,90,100,20);
        //
        SetPos(this.confirm_password,20,110,100,20);
        SetPos(this.checksum,20,110,100,20);
        //
        SetPos(this.destinationip,20,130,100,20);
        SetPos(this.destinationiptext,140,130,100,20);
        //
        SetPos(this.conpwdText,140,110,100,20);
        SetPos(this.a2,140,110,100,20);
        SetPos(this.login_id,20,150,100,20);
        SetPos(this.login_id1,20,150,100,20);
        //
        SetPos(this.sourcepcname,20,170,100,20);        //
        SetPos(this.sourcepcnametext,140,170,100,20);
        //
        SetPos(this.loginText,140,150,100,20);
        SetPos(this.mail_id,20,190,100,20);
        SetPos(this.mail_id1,20,190,100,20);
        SetPos(this.location,20,210,100,20);
        SetPos(this.locationt,140,210,100,20);
        SetPos(this.mailText,140,190,100,20);
        SetPos(this.contact_no,20,230,100,20);
        SetPos(this.contact_no1,20,230,100,20);
        SetPos(this.conText,140,230,100,20);
        //SetPos(OK,120,280,80,20);
        SetPos(CANCEL,195,280,80,20);
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==CANCEL)
        {
        System.exit(1);

        }
        if(ae.getSource()==seqbt)
        {//starts
        try
        {
        String inttest;
         int test=0;
         int fail=0;
         try
         {

                      if(this.seqbttext.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this,"Enter the sequence no of packet","...please re-enter",JOptionPane.ERROR_MESSAGE);
                return;
            }
            else
            {
            inttest=this.seqbttext.getText();
            for(int i=0;i<inttest.length();i++)
            {
            if(inttest.charAt(i)>=48|| inttest.charAt(i)<=56)
            {
            fail=0;
            //
            }
            else
            {
            fail=1;
            }
            }

            //test=inttest;

            }


                    if(fail==0)
                {
                 test=Integer.parseInt(inttest);
                }
                }

             catch(Exception e)
             {
             JOptionPane.showMessageDialog(this,"Enter a integer value","...please re-enter",JOptionPane.ERROR_MESSAGE);
                  seqbttext.setText("");
                return;
                }
             System.out.println("inputted value"+test);
             System.out.println("inputted value"+packetsofcommands);
             int t=0,flag=0,tx=0;
             for(int d=0;d<ServerApp.commandscreenobject.bigchunks[d].parts;d++,tx++)  // <= in  d loop previous
             {
             //int t=0;
             //t=d;
             //int tx=0;
             System.out.println("seq no"+ServerApp.commandscreenobject.bigchunks[d].seqno);
             if(ServerApp.commandscreenobject.bigchunks[d].seqno==test)
             {

             System.out.println("found"+test);
             ServerApp.commandscreenobject.nameText.setText(ServerApp.commandscreenobject.bigchunks[d].stringoffile);//"bigchunks[d].seqno");
             ServerApp.commandscreenobject.partstext.setText(Integer.toString(ServerApp.commandscreenobject.bigchunks[d].parts));
             ServerApp.commandscreenobject.a1.setText(Integer.toString(ServerApp.commandscreenobject.bigchunks[d].lengthofpackets));
             ServerApp.commandscreenobject.filelengthtext.setText(Float.toString(ServerApp.commandscreenobject.bigchunks[d].filelength));
             ServerApp.commandscreenobject.a2.setText(Long.toString(ServerApp.commandscreenobject.bigchunks[d].checksum));
             ServerApp.commandscreenobject.destinationiptext.setText(ServerApp.commandscreenobject.bigchunks[d].destination.toString());
             ServerApp.commandscreenobject.loginText.setText(Integer.toString(ServerApp.commandscreenobject.bigchunks[d].seqno));
             ServerApp.commandscreenobject.sourcepcnametext.setText(ServerApp.commandscreenobject.bigchunks[d].from);
             ServerApp.commandscreenobject.mailText.setText(Integer.toString(ServerApp.commandscreenobject.bigchunks[d].port));
             ServerApp.commandscreenobject.conText.setText(ServerApp.commandscreenobject.bigchunks[d].addr.toString());
             ServerApp.commandscreenobject.locationt.setText("Server Folder");
             JOptionPane.showMessageDialog(this,"Sequence matched","...",JOptionPane.INFORMATION_MESSAGE);
             flag=0;
             break;
             }
            else
            {
               if(tx==ServerApp.commandscreenobject.bigchunks[d].parts)// -1 previous
               {
               flag=1;
               }
               //break;
             }
             }

            if(flag==1)
             {
             JOptionPane.showMessageDialog(this,"Sequence no not found","...please re-enter",JOptionPane.ERROR_MESSAGE);
             seqbttext.setText("");
             return;
             }


        }
        catch(Exception e)
             {
             JOptionPane.showMessageDialog(this,"sequence no not found","...please re-enter",JOptionPane.ERROR_MESSAGE);
                  seqbttext.setText("");
                return;
                }
        }//end
        }
    }



class LoggedClients implements Serializable
{
  String Uname;
  Socket socket;
}

class FileDetails implements Serializable
{
     Vector FileName;
     Vector DirName;
     String Drive;
}


//transfer starts
class transfertab extends JPanel implements ActionListener
{
    JComboBox ulist;
    JButton submit;
    JScrollPane jsp;
    JTable jt;
    Object HEAD[]={"FileName","Source[Machine]","Dest.[Machine]","Source[Client]"};
    Object DATA[][]=new Object[100][4];

    public transfertab()
    {
       this.setLayout(null);
       ulist=new JComboBox();
       submit=new JButton("Submit");
       for(int i=0;i<100;i++)
        for(int j=0;j<4;j++)
         DATA[i][j]="";
       jt=new JTable(DATA,HEAD);
       jsp=new JScrollPane(jt,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
       submit.addActionListener(this);
       try
       {
          Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                   Connection con=DriverManager.getConnection("jdbc:odbc:ftpdsn");
                   Statement stmt=con.createStatement();
                   String query="select * from regtable";
                   ResultSet res=stmt.executeQuery(query);
         while(res.next())
         {
             ulist.addItem(res.getString("logid"));
         }

       }
       catch (Exception e)
       {
          System.out.println("Error while loading : " + e);

       }
       Design();
    }

    public void actionPerformed(ActionEvent ae)
    {
        try
       {
          Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                   Connection con=DriverManager.getConnection("jdbc:odbc:ftpdsn");
                   Statement stmt=con.createStatement();
                   String query="select * from Transferlog where Destination_client='" + this.ulist.getSelectedItem() + "'";
                   ResultSet res=stmt.executeQuery(query);
        for(int i=0;i<100;i++)
          for(int j=0;j<4;j++)
           DATA[i][j]="";
           int i=0;
         while(res.next())
         {
            DATA[i][0]=res.getString(1);
            DATA[i][1]=res.getString(2);
            DATA[i][2]=res.getString(3);
            DATA[i][3]=res.getString(4);
            i++;
         }

       }
       catch (Exception e)
       {
          System.out.println("Error while loading : " + e);

       }
    }
    private void SetPos(Component C,int x,int y,int w,int h)
    {
        this.add(C);
        C.setBounds(x,y,w,h);
    }
    private void Design()
    {
        SetPos(ulist,30,30,120,20);
        SetPos(submit,170,30,100,20);
        SetPos(jsp,30,70,500,400);
         this.setBackground(new Color(226,225,251));
    }
}
//transfer ends


//class compiletab starts here
class compiletab extends JPanel implements ActionListener
{
    JComboBox ulist;
    JButton submit;
    JScrollPane jsp;
    JTable jt;
    Object HEAD[]={"FileName","Type","Time"};
    Object DATA[][]=new Object[100][3];

    public compiletab()
    {
       this.setLayout(null);
       ulist=new JComboBox();
       submit=new JButton("Submit");
       for(int i=0;i<100;i++)
        for(int j=0;j<3;j++)
         DATA[i][j]="";
       jt=new JTable(DATA,HEAD);
       jsp=new JScrollPane(jt,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
       submit.addActionListener(this);
       try
       {
          Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                   Connection con=DriverManager.getConnection("jdbc:odbc:ftpdsn");
                   Statement stmt=con.createStatement();
                   String query="select * from regtable";
                   ResultSet res=stmt.executeQuery(query);
         while(res.next())
         {
             ulist.addItem(res.getString("logid"));
         }

       }
       catch (Exception e)
       {
          System.out.println("Error while loading : " + e);

       }
       Design();
    }

    public void actionPerformed(ActionEvent ae)
    {
        try
       {
          Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                   Connection con=DriverManager.getConnection("jdbc:odbc:ftpdsn");
                   Statement stmt=con.createStatement();
                   String query="select * from uploadlog where Download_by='" + this.ulist.getSelectedItem() + "'";
                   ResultSet res=stmt.executeQuery(query);
        for(int i=0;i<100;i++)
          for(int j=0;j<3;j++)
           DATA[i][j]="";
           int i=0;
         while(res.next())
         {
            DATA[i][0]=res.getString(1);
            DATA[i][1]=res.getString(3);
            DATA[i][2]=res.getString(4);
            i++;
         }

       }
       catch (Exception e)
       {
          System.out.println("Error while loading : " + e);

       }
    }
    private void SetPos(Component C,int x,int y,int w,int h)
    {
        this.add(C);
        C.setBounds(x,y,w,h);
    }
    private void Design()
    {
        SetPos(ulist,30,30,120,20);
        SetPos(submit,170,30,100,20);
        SetPos(jsp,30,70,500,400);
         this.setBackground(new Color(226,225,251));
    }
}
//class compiletab ends here




class currenttab extends JPanel
{
    JTextArea jArea;
    JScrollPane jsp;


    public currenttab()
    {
       this.setLayout(null);
       jArea=new JTextArea();
       jsp=new JScrollPane(jArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
       Design();
    }

    private void SetPos(Component C,int x,int y,int w,int h)
    {
        this.add(C);
        C.setBounds(x,y,w,h);
    }
    private void Design()
    {
         SetPos(jsp,30,70,500,400);
          this.setBackground(new Color(226,225,251));
    }
}



//logtab starts
class logtab extends JPanel implements ActionListener
{
    JComboBox ulist;
    Container pane;
    JButton submit;
    JScrollPane jsp;
    JTable jt;
    Object HEAD[]={"LoginTime","LogoutTime","Machine"};
    Object DATA[][]=new Object[100][3];

    public logtab()
    {
       this.setLayout(null);
       ulist=new JComboBox();
       submit=new JButton("Submit");
       for(int i=0;i<100;i++)
        for(int j=0;j<3;j++)
         DATA[i][j]="";
       jt=new JTable(DATA,HEAD);
       jsp=new JScrollPane(jt,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
       submit.addActionListener(this);
       try
       {
          Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                   Connection con=DriverManager.getConnection("jdbc:odbc:ftpdsn");
                   Statement stmt=con.createStatement();
                   String query="select * from regtable";
                   ResultSet res=stmt.executeQuery(query);
                   while(res.next())
                   {
                       ulist.addItem(res.getString("logid"));
                   }


       }
       catch (Exception e)
       {
          System.out.println("Error while loading : " + e);

       }
       Design();
    }

    public void actionPerformed(ActionEvent ae)
    {
        try
       {
          Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                   Connection con=DriverManager.getConnection("jdbc:odbc:ftpdsn");
                   Statement stmt=con.createStatement();
                   String query="select * from logtable where uid='" + this.ulist.getSelectedItem() + "'";
                   ResultSet res=stmt.executeQuery(query);
        for(int i=0;i<100;i++)
          for(int j=0;j<3;j++)
           DATA[i][j]="";
           int i=0;
         while(res.next())
         {
            DATA[i][0]=res.getString(2);
            DATA[i][1]=res.getString(3);
            DATA[i][2]=res.getString(4);
            i++;
         }

       }
       catch (Exception e)
       {
          System.out.println("Error while loading : " + e);

       }
    }
    private void SetPos(Component C,int x,int y,int w,int h)
    {
        this.add(C);
        C.setBounds(x,y,w,h);
        C.setVisible(true);
    }
    private void Design()
    {
           SetPos(ulist,30,30,120,20);
           SetPos(submit,170,30,100,20);
           SetPos(jsp,30,70,500,400);
            this.setBackground(new Color(226,225,251));
    }
}
//logtab ends


//class client thread starts
class ClientThread extends Thread
{
    Socket client;
    String Curusr="";
    String intime="";
    String type="";
    public ClientThread(Socket client)
    {
       this.client=client;
       start();
    }


    public void run()
    {
        try
        {

            while(true)
            {
                ObjectInputStream In=new ObjectInputStream(client.getInputStream());
                String Msg="";
                Msg=(String)In.readObject();
                if(Msg.equals("LoginDetails"))
                {
                   String uname=(String)In.readObject();
                   String pass=(String)In.readObject();
                   Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                   Connection con=DriverManager.getConnection("jdbc:odbc:ftpdsn");
                   Statement stmt=con.createStatement();
                   String query="select * from regtable where logid='" + uname + "' and pass='" + pass + "'";
                   ResultSet res=stmt.executeQuery(query);
                   ObjectOutputStream Os=new ObjectOutputStream(client.getOutputStream());
                   if(res.next())
                   {
                       this.Curusr=uname;
                       res.close();
                       intime=new java.util.Date() +"";
                       query="insert into logtable values(" +
                                     "'" + uname + "'," +
                                     "'" + intime + "'," +
                                     "'" + "" + "'," +
                                     "'" + client.getInetAddress().getHostAddress() + "')";
                       stmt.executeUpdate(query);
                       ServerApp.serverwin.crt.jArea.setText(ServerApp.serverwin.crt.jArea.getText() + "\n" + uname + " : Logged in [" + new java.util.Date() + "]");

                        for(int i=0;i<ServerApp.LogDetails.size();i++)
                         {
                           LoggedClients LC=(LoggedClients)ServerApp.LogDetails.elementAt(i);
                           ObjectOutputStream O=new ObjectOutputStream(LC.socket.getOutputStream());
                           O.writeObject("NewLog");
                           O.writeObject(uname);
                         }
                         LoggedClients LC=new LoggedClients();
                         LC.Uname=uname;
                         LC.socket=client;
                         ServerApp.LogDetails.add(LC);
                         Os.writeObject("SUCCESS");
                   }
                   else
                      Os.writeObject("Failed");
                }
                 if(Msg.equals("successfully upload"))
                 {
                 System.out.println("Uploaded the file successfully by the client");
                 }
                 if(Msg.equals("FreshLog"))
                 {
                  System.out.println("Request Recieved : " + ServerApp.LogDetails.size());

                  ObjectOutputStream O=new ObjectOutputStream(client.getOutputStream());
                  O.writeObject("AllLog");
                  O.writeObject(Integer.toString(ServerApp.LogDetails.size()));

                  for(int i=0;i<ServerApp.LogDetails.size();i++)
                     {
                       LoggedClients LC=(LoggedClients)ServerApp.LogDetails.elementAt(i);

                       O.writeObject(LC.Uname);
                     }
                 }

                if(Msg.equals("logout"))
                {
                   Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                   Connection con=DriverManager.getConnection("jdbc:odbc:ftpdsn");
                   Statement stmt=con.createStatement();
                   String query="update logtable set logouttm='" + new java.util.Date() + "' where uid='" + this.Curusr + "' and logintm='" + this.intime + "'";
                   stmt.executeUpdate(query);
                   ServerApp.serverwin.crt.jArea.setText(ServerApp.serverwin.crt.jArea.getText() + "\n" + this.Curusr + " : Logged out [" + new java.util.Date() + "]");
                   for(int i=0;i<ServerApp.LogDetails.size();i++)
                    {
                        LoggedClients LC=(LoggedClients)ServerApp.LogDetails.elementAt(i);
                        if(LC.Uname.equals(this.Curusr))
                        {
                          ServerApp.LogDetails.removeElementAt(i);
                          break;
                        }
                    }
                   break;
                }
                if(Msg.equals("Flupload"))
                {
                   String Spath=(String)In.readObject();
                   Vector V =(Vector)In.readObject();
                   String Query=(String)In.readObject();
                   byte buf[]=(byte[])V.elementAt(0);
                   FileOutputStream Fout=new FileOutputStream(Spath);
                   Fout.write(buf);
                   Fout.close();
                    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                   Connection con=DriverManager.getConnection("jdbc:odbc:ftpdsn");
                   Statement stmt=con.createStatement();
                   stmt.executeUpdate(Query);
                }
                if(Msg.equals("Download"))
                {
                    String fname=(String)In.readObject();
                    File F=new File(fname);

            	   if(F == null)//|| temp.getName().equals(""))
            	   {
            		System.out.println("Invalid file...");
            	    return;
                   }
        	        String Path=F.getPath();
    			    long size=F.length();
    			    System.out.println("SIZE : " + size);
    			    byte buf[]=new byte[(int)size];
                    FileInputStream Fin=new FileInputStream(Path);
    		  	    Fin.read(buf);
    		  	    java.util.Vector UP=new java.util.Vector();
    		  	    UP.add(buf);
                    ObjectOutputStream Os=new ObjectOutputStream(client.getOutputStream());
                    Os.writeObject("Download");
                    Os.writeObject(UP);
                    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                    Connection con=DriverManager.getConnection("jdbc:odbc:ftpdsn");
                    Statement stmt=con.createStatement();
                    String query="insert into Uploadlog values(" +
                                 "'" + fname + "'," +
                                 "'" + this.Curusr + "'," +
                                 "'" + this.type + "'," +
                                 "'" + new java.util.Date() + "')";

                    stmt.executeUpdate(query);
                }
                if(Msg.equals("GetList"))
                {
                   String tp=(String)In.readObject();
                   this.type=tp;
                   Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                   Connection con=DriverManager.getConnection("jdbc:odbc:ftpdsn");
                   Statement stmt=con.createStatement();
                   String query="Select * from File_details where Category='" + type + "'";
                   ResultSet res=stmt.executeQuery(query);
                   Vector V=new Vector();
                   while(res.next())
                   {
                       Vector sub=new Vector();
                       sub.add(res.getString("File_name"));
                       sub.add(res.getString("Upload_by"));
                       sub.add(res.getString("fsize"));
                       V.add(sub);
                   }
                   ObjectOutputStream Os=new ObjectOutputStream(client.getOutputStream());
                   Os.writeObject("list");
                   Os.writeObject(V);
                }
                if (Msg.equals("GetFile"))
                {
                    String N=(String)In.readObject();
                    String dname=(String)In.readObject();
                    LoggedClients LC=new LoggedClients();
                    boolean flag=false;
                    for(int i=0;i<ServerApp.LogDetails.size();i++)
                    {
                        LC=(LoggedClients)ServerApp.LogDetails.elementAt(i);
                        if(LC.Uname.equals(N))
                        {
                            flag=true;
                            break;
                        }
                    }
                    if(flag==true)
                    {
                        ObjectOutputStream Out=new ObjectOutputStream(LC.socket.getOutputStream());
                        Out.writeObject("GetFile");
                        Out.writeObject(dname);
                        Out.writeObject(this.Curusr);
                    }
                }
                if(Msg.equals("AllDet"))
                {
                    LoggedClients LC=new LoggedClients();
                    String N;
                    String Str=(String)In.readObject();
                    N=Str;
                    FileDetails FD=(FileDetails)In.readObject();
                    boolean flag=false;
                    for(int i=0;i<ServerApp.LogDetails.size();i++)
                    {
                        LC=(LoggedClients)ServerApp.LogDetails.elementAt(i);
                        if(LC.Uname.equals(N))
                        {
                            flag=true;
                            break;
                        }
                    }
                    if(flag==true)
                    {
                        ObjectOutputStream Out=new ObjectOutputStream(LC.socket.getOutputStream());
                        Out.writeObject("SendDet");
                        Out.writeObject(FD);
                    }
                }
                if (Msg.equals("CopyFile"))
                {
                    String N=(String)In.readObject();
                    String fname=(String)In.readObject();
                    LoggedClients LC=new LoggedClients();
                    boolean flag=false;
                    for(int i=0;i<ServerApp.LogDetails.size();i++)
                    {
                        LC=(LoggedClients)ServerApp.LogDetails.elementAt(i);
                        if(LC.Uname.equals(N))
                        {
                            flag=true;
                            break;
                        }
                    }
                    if(flag==true)
                    {
                        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                        Connection con=DriverManager.getConnection("jdbc:odbc:ftpdsn");
                        Statement stmt=con.createStatement();
                        String query="insert into Transferlog values(" +
                                     "'" + fname + "'," +
                                     "'" + LC.socket.getInetAddress().getHostAddress() + "'," +
                                     "'" + client.getInetAddress().getHostAddress() + "'," +
                                     "'" + N + "'," +
                                     "'" + this.Curusr + "')";

                        stmt.executeUpdate(query);
                        ObjectOutputStream Out=new ObjectOutputStream(LC.socket.getOutputStream());
                        Out.writeObject("CopyFile");
                        Out.writeObject(fname);
                        Out.writeObject(this.Curusr);
                    }
                }
                if(Msg.equals("FileLoad"))
                {
                    LoggedClients LC=new LoggedClients();
                    String N;
                    String Str=(String)In.readObject();
                    N=Str;
                    Vector FD=(Vector)In.readObject();


                    boolean flag=false;
                    for(int i=0;i<ServerApp.LogDetails.size();i++)
                    {
                        LC=(LoggedClients)ServerApp.LogDetails.elementAt(i);
                        if(LC.Uname.equals(N))
                        {
                            flag=true;
                            break;
                        }
                    }
                    if(flag==true)
                    {
                        ObjectOutputStream Out=new ObjectOutputStream(LC.socket.getOutputStream());
                        Out.writeObject("FileLoad");
                        Out.writeObject(FD);
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Error on server side : " + e);
            e.printStackTrace();
        }
    }
}
//class clientthreadends


//reg starts
class registration extends JFrame implements ActionListener
{
    JLabel name;
    JLabel password;
    JLabel confirm_password;
    JLabel login_id;
    JLabel mail_id;
    JLabel contact_no;
    JTextField nameText;
    JPasswordField pwdText;
    JPasswordField conpwdText;
    JTextField loginText;
    JTextField mailText;
    JTextField conText;
    JButton OK,CANCEL;
    Container pane;

    public registration()
    {
        pane=this.getContentPane();
        pane.setLayout(null);
        this.name=new JLabel("Enter ur name");
        this.password=new JLabel("Enter password");
        this.confirm_password=new JLabel("Enter confirm password");
        this.login_id=new JLabel("Enter ur login-id");
        this.mail_id=new JLabel("Enter ur mail-id");
        this.contact_no=new JLabel("Enter ur contact no.");
        this.nameText=new JTextField();
        this.pwdText=new JPasswordField();
        this.conpwdText=new JPasswordField();
        this.loginText=new JTextField();
        this.mailText=new JTextField();
        this.conText=new JTextField();
        OK=new JButton("OK");
        CANCEL=new JButton("CANCEL");
        OK.addActionListener(this);
        CANCEL.addActionListener(this);
        Design();
    }
    private void SetPos(Component C,int x,int y,int w,int h)
    {
         pane.add(C);
         C.setBounds(x,y,w,h);
    }

    private void Design()
    {
        SetPos(this.name,20,30,100,20);
        SetPos(this.nameText,140,30,100,20);
        SetPos(this.password,20,70,100,20);
        SetPos(this.pwdText,140,70,100,20);
        SetPos(this.confirm_password,20,110,100,20);
        SetPos(this.conpwdText,140,110,100,20);
        SetPos(this.login_id,20,150,100,20);
        SetPos(this.loginText,140,150,100,20);
        SetPos(this.mail_id,20,190,100,20);
        SetPos(this.mailText,140,190,100,20);
        SetPos(this.contact_no,20,230,100,20);
        SetPos(this.conText,140,230,100,20);
        SetPos(OK,120,280,80,20);
        SetPos(CANCEL,195,280,80,20);
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==OK)
        {
            if(this.nameText.getText().equals("") ||
               this.pwdText.getText().equals("") ||
               this.conpwdText.getText().equals("") ||
               this.loginText.getText().equals("") ||
               this.mailText.getText().equals("") ||
               this.conText.getText().equals("") )
               {
                   JOptionPane.showMessageDialog(this,"Incomplete Data Entry!!!","Registration",JOptionPane.ERROR_MESSAGE);
                   return;
               }
           if(!this.pwdText.getText().equals(this.conpwdText.getText()))
           {
               JOptionPane.showMessageDialog(this,"Passwords Does Not Matches!!!","Registration",JOptionPane.ERROR_MESSAGE);
               return;
           }
           try
           {
               Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
               Connection con=DriverManager.getConnection("jdbc:odbc:remotedsn");
               Statement stmt=con.createStatement();
               String query="insert into regtable values(" +
                                    "'" + this.nameText.getText() + "'," +
                                    "'" + this.pwdText.getText() + "'," +
                                    "'" + this.loginText.getText() + "'," +
                                    "'" + this.mailText.getText() + "'," +
                                    "'" + this.conText.getText() + "')";
               stmt.executeUpdate(query);
               File f=new File(this.loginText.getText());
               f.mkdir();
               JOptionPane.showMessageDialog(this,"Registered Successfully...","Registration",JOptionPane.INFORMATION_MESSAGE);
               this.hide();
           }
           catch (Exception e)
           {
              JOptionPane.showMessageDialog(this,"Error While Registration!!!","Registration",JOptionPane.ERROR_MESSAGE);
           }
        }
    }


}
//reg ends






class ServerWin extends JFrame implements ActionListener
{
   Container pane;
   JButton stpServer;
   JButton depacketizer;
   JButton commands;
   JButton regBtn;
   JTabbedPane jtb;
   logtab lt;
   currenttab crt;
   compiletab ct;
   transfertab tt;

   public ServerWin()
   {
       pane=this.getContentPane();
       pane.setLayout(null);
       this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
       lt=new logtab();
       ct=new compiletab();
       crt=new currenttab();
       tt=new transfertab();
       jtb=new JTabbedPane();
       jtb.addTab("Log Details",lt);
       jtb.add("Downloads",ct);
       jtb.add("Transfers",tt);
       jtb.add("Current State",crt);
       stpServer=new JButton(":Stop Server:");
       commands=new JButton("Commands");
       regBtn=new JButton("Registration");
       this.stpServer.addActionListener(this);
       this.commands.addActionListener(this);
       this.regBtn.addActionListener(this);
       depacketizer=new JButton("Depacktizer");
       this.depacketizer.addActionListener(this);
       Design();
   }

   private void SetPos(Component C,int x,int y,int w,int h)
   {
       pane.add(C);
       C.setBounds(x,y,w,h);
   }
   private void Design()
   {
       SetPos(this.stpServer,40,540,150,20);
       //SetPos(this.commands,310,50,100,50);
       //SetPos(this.depacketizer,420,50,150,50);
       SetPos(this.regBtn,210,540,150,20);
       SetPos(this.jtb,20,20,550,500);
       pane.setBackground(new Color(226,225,251));
   }
   public void actionPerformed(ActionEvent ae)
   {
       if(ae.getSource()==this.regBtn)
       {
            registration regWin=new registration();
            final int WIDTH=400;
            final int HEIGHT=400;
            Toolkit T=Toolkit.getDefaultToolkit();
            Dimension D=T.getScreenSize();
            regWin.setBounds(D.width/2-WIDTH/2,D.height/2-HEIGHT/2,WIDTH,HEIGHT);
            regWin.show();
            regWin.setTitle("REGISTRATION WINDOW");
       }
       if(ae.getSource()==this.stpServer)
       {
           System.exit(1);
       }
       if(ae.getSource()==this.depacketizer)
       {



            final int WIDTH=1000;
            final int HEIGHT=700;
            packet p1=new packet();
            p1.setTitle("De-Packet module");
            Toolkit T=Toolkit.getDefaultToolkit();
            Dimension D=T.getScreenSize();
            p1.setBounds(D.width/2-WIDTH/2,D.height/2-HEIGHT/2,WIDTH,HEIGHT);
            p1.setVisible(true);
            this.setVisible(false);


       }

       if(ae.getSource()==this.commands)
       {

       }
   }
}




public class ServerApp
{   public static commandscreen commandscreenobject=new commandscreen();
    public static ServerWin serverwin;
    public static Vector LogDetails=new Vector();
    //static ServerSocket cserver;
    public static int stoploop;

    public static void main(String args[])
    {   String option="repack";
        System.out.println("Enter server for file sharing mode and packetizing mode,commands for executing commands ");
        //Scanner input = new Scanner (System.in);
        //option = input.next();
        System.out.println("You have entered           "    +option);
        if(option.equals("server"))
        {
        try
        {
            ServerSocket server=new ServerSocket(3344);
            //cserver=new ServerSocket(3366);
            serverwin=new ServerWin();
            final int WIDTH=600;
            final int HEIGHT=600;
            Toolkit T=Toolkit.getDefaultToolkit();
            Dimension D=T.getScreenSize();
            serverwin.setBounds(D.width/2-WIDTH/2,D.height/2-HEIGHT/2,WIDTH,HEIGHT);
            serverwin.show();
            serverwin.setTitle("SERVER WINDOW");
            System.out.println("value of stop loop"+stoploop);

           while(true)
           {
               Socket client=server.accept();
               new ClientThread(client);
           }



        }
        catch (Exception e)
        {

        }
        }
        if(option.equals("commands"))
        {

            ServerApp.stoploop=1;
            final int WIDTH=400;
            final int HEIGHT=400;
            //commandscreen commandscreenobject=new commandscreen();
            ServerApp.commandscreenobject.setTitle("Command scrren window");
            ServerApp.commandscreenobject.name1.setVisible(false);
            ServerApp.commandscreenobject.password1.setVisible(false);
            ServerApp.commandscreenobject.parts.setVisible(false);
            ServerApp.commandscreenobject.partstext.setVisible(false);
            ServerApp.commandscreenobject.login_id1.setVisible(false);
            ServerApp.commandscreenobject.mail_id1.setVisible(false);
            ServerApp.commandscreenobject.contact_no1.setVisible(false);
            ServerApp.commandscreenobject.checksum.setVisible(false);
            ServerApp.commandscreenobject.location.setVisible(false);
            ServerApp.commandscreenobject.locationt.setVisible(false);

            ServerApp.commandscreenobject.a1.setVisible(false);
            ServerApp.commandscreenobject.a2.setVisible(false);

            ServerApp.commandscreenobject.seqbt.setVisible(false);
            ServerApp.commandscreenobject.seqbttext.setVisible(false);

            ServerApp.commandscreenobject.filelength.setVisible(false);
            ServerApp.commandscreenobject.filelengthtext.setVisible(false);

            ServerApp.commandscreenobject.sourcepcname.setVisible(false);
            ServerApp.commandscreenobject.sourcepcnametext.setVisible(false);

            ServerApp.commandscreenobject.destinationip.setVisible(false);
            ServerApp.commandscreenobject.destinationiptext.setVisible(false);
            Toolkit T=Toolkit.getDefaultToolkit();
            Dimension D=T.getScreenSize();
            commandscreenobject.setBounds(D.width/2-WIDTH/2,D.height/2-HEIGHT/2,WIDTH,HEIGHT);
            commandscreenobject.show();
            try
            {

            ServerSocket cserver=new ServerSocket(3366);
            while(true)
           {

               Socket clientcommandsocket=cserver.accept();
               new ClientThread1(clientcommandsocket);


           }
            }
            catch(Exception e)
            {
            System.out.println("Can't create server socket               "+e);
            }



        }
        if(option.equals("repack"))
        {
            ServerApp.stoploop=1;
            final int WIDTH=400;
            final int HEIGHT=400;
            //commandscreen commandscreenobject=new commandscreen();
            commandscreenobject.setTitle("Re-packetizing window");
            commandscreenobject.name.setVisible(false);
            commandscreenobject.password.setVisible(false);
            commandscreenobject.login_id.setVisible(false);
            commandscreenobject.mail_id.setVisible(false);
            commandscreenobject.contact_no.setVisible(false);
            commandscreenobject.confirm_password.setVisible(false);
            commandscreenobject.pwdText.setVisible(false);
            commandscreenobject.conpwdText.setVisible(false);
            commandscreenobject.a1.setVisible(true);
            commandscreenobject.a2.setVisible(true);
            Toolkit T=Toolkit.getDefaultToolkit();
            Dimension D=T.getScreenSize();
            commandscreenobject.setBounds(D.width/2-WIDTH/2,D.height/2-HEIGHT/2,WIDTH,HEIGHT);
            commandscreenobject.show();
            try
            {

            ServerSocket cserver=new ServerSocket(3399);
            while(true)
           {

               Socket clientcommandsocket=cserver.accept();
               new ClientThread1(clientcommandsocket);


           }
            }
            catch(Exception e)
            {
            System.out.println("Can't create server socket               "+e);
            }

        }

    }
}


class packet extends JFrame implements ActionListener
{
   ByteArrayOutputStream bos = new ByteArrayOutputStream();   // writing again for checking
   File rewrite2test = new File("c:/blue/rewrite2testreceive");
   byte all[];
   DatagramSocket ds;
   static BufferedWriter outreceiver;
   String msg;
   DatagramPacket dp;
   int port=3388;
JButton back,exit,enter;
JLabel dir,ip2;
JTextField file,ip;
Container paneipadd1;


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
dir=new JLabel("Click to receive data");
ip2=new JLabel("Enter ip address");
enter=new JButton("Start");
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

            int BUFSIZE = 99999;
            BufferedWriter out;

            byte buffer[] = new byte[BUFSIZE];
            try
      {
      ds = new DatagramSocket(3388);
      }
      catch (java.io.IOException e)
                {
                System.out.println("problem with ds socket");
                }
    while (true)
      {

      try
      {
      dp = new DatagramPacket(buffer,buffer.length);
      }
      catch (Exception e)
                {
                System.out.println("dp socket problem");
                }
      try
      {
      ds.receive(dp);
      System.out.println("displaying encrypted data");
      }
      catch (java.io.IOException e)
                {
                System.out.println("unable to receive");
                }

      System.out.println(new String(dp.getData()));
      System.out.println("displaying orignal data");
      try
      {
      int length=dp.getLength();
      long longlength=dp.getLength();
      System.out.println("length of packet dp           "+length);
      System.out.println("length of packet dp in long   "+longlength);
      msg = new String(buffer, 0, length-1);//, "UTF-8");   //to delete
      byte all[]=new byte[dp.getLength()];
      }
      catch (Exception e)
                {
                System.out.println("Can't decrypt");
                }
      System.out.println(msg);//to delete
      try
      {
      outreceiver = new BufferedWriter(new FileWriter("c:/blue/receivedoutputfile"));
      outreceiver.write(msg);
      outreceiver.close();
      all = msg.getBytes();
      System.out.println("message converted to byte and caught in byte array named all......." +all);
      int len1=buffer.length;
      int len2=all.length;
      System.out.println("length of buffer is" +len1);
      System.out.println("length of byte aaray all is   " +len2);
      bos.write(buffer, 0, len1); //bos.write(all, 0, len2-1);
      byte[] bytes = bos.toByteArray();
      FileOutputStream fos = new FileOutputStream(rewrite2test);
      fos.write(bytes);
      fos.flush();
      fos.close();
      int m,f=0,loop=0;
      m=all.length;
      String t1=new String("#");
      byte[] t2;
      t2 = t1.getBytes();
      int t2length=t2.length;
      System.out.println("length of t2"+t2length);
      int n=len2-2;
      File rewrite2test1 = new File("c:/blue/rewrite.txt");
       FileOutputStream fos1 = new FileOutputStream(rewrite2test1);
        fos1.write(t2);
        fos1.flush();
        fos1.close();
      for( ;n>0;n--)
      {
      System.out.println("value in t2......."+t2[0]);
      if(35 ==all[n])
      {
      System.out.println("push.........."+loop);
      f=n;
      loop=loop+1;
      break;
      }
      }
      System.out.println("starting of the header is at position"+f);

      }

      catch (java.io.IOException e)
                {
                System.out.println("Can't write to file");
                }
                //System.exit(1);
                //this.setVisible(false);


      }


      }

        if(ae.getSource()==back)
        {


        System.exit(1);
        this.setVisible(false);



        }
        if(ae.getSource()==exit)
        {
            System.exit(1);
            this.setVisible(false);
        }

    }}






class ClientThread1 extends Thread
{
    Socket clientcommandsocket;

    public ClientThread1(Socket clientcommandsocket)
    {
       this.clientcommandsocket=clientcommandsocket;
       start();
    }


    public void run()
    {
        try
        {

            while(true)
            {
                ObjectInputStream In1=new ObjectInputStream(clientcommandsocket.getInputStream());
                String Msg="";
                Msg=(String)In1.readObject();
                if(Msg.equals("LoginDetails"))
                {
                System.out.println("message received");
                 }


                if(Msg.equals("repak"))
                {
                int ffl=0;
                String filename="";
                chunks z1=new chunks();
                int len;
                len=(Integer)In1.readObject();
                ServerApp.commandscreenobject.length1=len;//for sequential marking purposes
                ServerApp.commandscreenobject.packetsofcommands=len;
                //chunks bigchunks[]=new chunks[len];
                System.out.println("value of packets to be created is"+len);
                chunks z[]=new chunks[len];
                tcp tc[]=new tcp[len];
                try
                {

                for(int i=0;i<len;i++)
                {
                z[i]=new chunks();
                tc[i]=new tcp();
                //bigchunks[i]=new chunks();
                }
                for(int mn=0;mn<len;mn++)
                {
                tc[mn]=(tcp)In1.readObject();
                z[mn]=tc[mn].tcpheader;
                ServerApp.commandscreenobject.bigboylen=ServerApp.commandscreenobject.bigboylen+z[mn].buff.length;
                ServerApp.commandscreenobject.bigchunks[mn]=z[mn];
                filename=z[0].stringoffile;
                ffl=(int)z[0].filelength;
                }

                for(int mn=0;mn<len;mn++)
                {
                System.out.println("buffer of packet z.........."+z[mn].buff);
                System.out.println("port of packet z.........."+z[mn].port);
                System.out.println("sequence no of packet z.........."+z[mn].seqno);
                System.out.println("destination of packet z.........."+z[mn].destination);
                System.out.println("parts of packet z.........."+z[mn].parts);
                System.out.println("filelength of packet z.........."+z[mn].filelength);
                System.out.println("from computer of packet z.........."+z[mn].from);
                System.out.println("addres of computer frm which data is sent computer of packet z.........."+z[mn].addr);
                System.out.println("individual length of packets is.........."+z[mn].lengthofpackets);
                System.out.println("name of file.........."+z[mn].stringoffile);
                }
                }
                catch(Exception e)
                {
                }

                String p=(String)In1.readObject();

                z1=(chunks)In1.readObject();
                int sequence=0;
                System.out.println("buffer of packet z.........."+z1.buff);
                System.out.println("port of packet z.........."+z1.port);
                System.out.println("buffer of packet z.........."+z1.buff);
                System.out.println("destination of packet z.........."+z1.destination);
                System.out.println("sequence no is.........."+sequence);
                System.out.println("the path                 "+p);
                ServerApp.commandscreenobject.nameText.setText("pjjjjj");
                System.out.println("value of big boy.........."+ServerApp.commandscreenobject.bigboylen);
                ServerApp.commandscreenobject.bigboy=new byte[ServerApp.commandscreenobject.bigboylen];
                int stpos=0;
                for(int x=0;x<len;x++)
                {
                System.arraycopy(z[x].buff,0,ServerApp.commandscreenobject.bigboy,stpos,z[x].buff.length);
                stpos=stpos+z[x].buff.length;
                }
                try
                {
                System.out.println("length of bigboy.........."+ServerApp.commandscreenobject.bigboy.length);

                FileOutputStream paket=new FileOutputStream(filename);
                System.out.println("no of bytes to write.........."+ffl);
                paket.write(ServerApp.commandscreenobject.bigboy,0,ffl);
                //String pakloc=paket.getFD(
                paket.close();

                }
                catch(Exception e)
                {
                }
                //ServerApp.commandscreenobject.loginText.setText(sequence);
                }


                if(Msg.equals("wait"))
                {
                }


                //directory created
                if(Msg.equals("Directory"))
                {
                String direc=(String)In1.readObject();
                 boolean success = (new File(direc)).mkdir();
                 if(success)
                 {
                 System.out.println("Directory: " + direc + " created");
                 ObjectOutputStream Os=new ObjectOutputStream(clientcommandsocket.getOutputStream());
                 Os.writeObject("Directory created");
                 }

                }
            }
        }

        catch (Exception e)
        {
            System.out.println("Error on server side : " + e);
            e.printStackTrace();
        }
    }
}

class chunks implements Serializable
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