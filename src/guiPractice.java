//swing practice for NETbuilder project
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class guiPractice extends JFrame {
	guiPractice(){
		
		//GUI STUFF
		setLayout(new GridLayout());

		//username field
		TextField userField = new TextField("Enter username");
		add(userField);
		//password field
		TextField passField = new TextField("Enter Password");
		add(passField);
		//submit button
		Button submitBut = new Button("Submit");
		add(submitBut);
		//result field
		TextField resultField = new TextField("Result");
		resultField.setEditable(false);
		add(resultField);
		
		setTitle("GUI PRACTICE");
		setSize(500,500);
		setVisible(true);
		
		//nitty gritty
		submitBut.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(userField.getText().equals("Dan") && passField.getText().equals("24")){
					resultField.setText("WELCOME DAN!");
					userField.setEditable(false);
					passField.setEditable(false);
					
				}else{
					resultField.setText("PISS OFF!");
				}
			}
			
		});
	}
	/*public static void main(String args[]){
		guiPractice menu1 = new guiPractice();
	}*/

}
