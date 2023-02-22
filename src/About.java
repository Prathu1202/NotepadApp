
import java.awt.*;
import javax.swing.*;


public class About  extends JFrame{
    About(){
        setBounds(100,100,500,400);
        setTitle("About Notepad Application");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ImageIcon icon = new ImageIcon(getClass().getResource("notepad2.png"));
        setIconImage(icon.getImage());
        
        setLayout(null);
        JLabel iconLabel = new JLabel(new ImageIcon(getClass().getResource("notepad2.png")));
        iconLabel.setBounds(100,50,80,80);
        add(iconLabel);
        
        
        JLabel textLabel = new JLabel("<html>   Welcome to Notepad<br>Notepad is a simple Text Editor for Microsoft Windows and basic text-editing program which enables computer users to create documents</html>");
        textLabel.setBounds(100,50,350,300);
        textLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        add(textLabel);
        
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		   new About().setVisible(true);

	}
}