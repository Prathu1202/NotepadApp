//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.font.*;
import javax.swing.*;
import java.awt.*;

public class NotepadApp extends JFrame implements ActionListener {
    private JTextArea textArea;
    private JFileChooser fileChooser;
    private String currentFile = "";
     

     NotepadApp() {
        // set  main frame
        setTitle("Notepad Application");
        setBounds(100,100,800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon(getClass().getResource("notepad_icon.png"));
        setIconImage(icon.getImage());
        
        // Create a text area
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        textArea.setFont(new Font(Font.SANS_SERIF, Font.PLAIN,30));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(true);
        

        // Create a menu bar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Create the File menu
        JMenu file = new JMenu("File");
        menuBar.add(file);
        JMenu edit = new JMenu("Edit");
        menuBar.add(edit);
        JMenu help = new JMenu("Help");
        menuBar.add(help);
        
// menuItems for File menu*****************
        
        
        JMenuItem newFile = new JMenuItem("New");
        newFile.addActionListener(this);
        file.add(newFile);

        
        JMenuItem openFile = new JMenuItem("Open");
        openFile.addActionListener(this);
        file.add(openFile);

        
        JMenuItem saveFile = new JMenuItem("Save");
        saveFile.addActionListener(this);
        file.add(saveFile);
        
     
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        file.add(exit);

// menuItem for Edit menu********************
        JMenuItem cut = new JMenuItem("Cut");
        cut.addActionListener(this);
        edit.add(cut);
        
        JMenuItem copy = new JMenuItem("Copy");
        copy.addActionListener(this);
        edit.add(copy);
        
        JMenuItem paste = new JMenuItem("Paste");
        paste.addActionListener(this);
        edit.add(paste);
        
        JMenuItem selectall = new JMenuItem("Select All");
        selectall.addActionListener(this);
        edit.add(selectall);
        
// menuItem for About menu********************        
        JMenuItem about = new JMenuItem("About");
        about.addActionListener(this);
        help.add(about);
        
        // adding shortcut keys
        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,KeyEvent.CTRL_DOWN_MASK));
        openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,KeyEvent.CTRL_DOWN_MASK));
        saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,KeyEvent.CTRL_DOWN_MASK));
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,KeyEvent.CTRL_DOWN_MASK));
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,KeyEvent.CTRL_DOWN_MASK));
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,KeyEvent.CTRL_DOWN_MASK));
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,KeyEvent.CTRL_DOWN_MASK));
        selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,KeyEvent.CTRL_DOWN_MASK));
       about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J,KeyEvent.CTRL_DOWN_MASK));
        
       
        
        
        // Create a file chooser
        fileChooser = new JFileChooser();

        // Show the main frame
        setVisible(true);
    }

    // Handle events for the menu items
    public void actionPerformed(ActionEvent e) { // ***************************
        String actionCommand = e.getActionCommand();

        if (actionCommand.equals("New")) {
            currentFile = "";
            textArea.setText(null);
        } else if (actionCommand.equalsIgnoreCase("Open")) {
            int returnVal = fileChooser.showOpenDialog(NotepadApp.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    FileReader fileReader = new FileReader(fileChooser.getSelectedFile().getAbsolutePath());
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String line;
                    textArea.setText("");
                    while ((line = bufferedReader.readLine()) != null) {
                        textArea.append(line + "\n");
                    }
                    bufferedReader.close();
                    currentFile = fileChooser.getSelectedFile().getAbsolutePath();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error reading file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (actionCommand.equalsIgnoreCase("Save")) {
            if (currentFile.equals("")) {
            	
//            	 FileNameExtensionFilter textFilter=new FileNameExtensionFilter("Only Text Files(.txt)","txt");
//            	fileChooser.setAcceptAllFileFilterUsed(false);
//            	fileChooser.addChoosableFileFilter(textFilter);
            	
            	int action = fileChooser.showSaveDialog(NotepadApp.this);
                
                if (action == JFileChooser.APPROVE_OPTION) {
                    currentFile = fileChooser.getSelectedFile().getAbsolutePath().toString();//to string add kela
                    if(!currentFile.contains(".txt")) {
                    	currentFile = currentFile+".txt";
            			try {
            			     BufferedWriter writer=new BufferedWriter(new FileWriter(currentFile));
            			     textArea.write(writer);
            		    }catch(IOException ex) {
            		        ex.printStackTrace();	
            		    }
            		    }

                    
                } else {
                    return;
                }
            }
            try {
                FileWriter fileWriter = new FileWriter(currentFile);
                fileWriter.write(textArea.getText());
                fileWriter.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error writing file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if(actionCommand. equalsIgnoreCase("Exit")) {
        	
        	System.exit(0);
        	
        }else if(e.getActionCommand(). equalsIgnoreCase("Cut")) {
        	
        	textArea.cut();
        	
        }else if(e.getActionCommand(). equalsIgnoreCase("Copy")) {
        	
        	textArea.copy();
        	
        }else if(e.getActionCommand(). equalsIgnoreCase("Paste")) {
        	
        	textArea.paste();
        	
        }else if(e.getActionCommand(). equalsIgnoreCase("Select All")) {
        	textArea.selectAll();
        	
        }else if(e.getActionCommand(). equalsIgnoreCase("About")) {
        	
        	new About().setVisible(true);
        }
        
     
    }
    public static void main(String[] args) throws Exception{
    	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new NotepadApp().setVisible(true);
    }
}