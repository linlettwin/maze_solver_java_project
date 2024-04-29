//intro window 1

package mazeCollection;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MazeColor1 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MazeColor1 frame = new MazeColor1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	 //Create the frame.
	 
	public MazeColor1() {
		setTitle("Maze Solver");
		setSize(850,500);
		setBackground(new Color(253,213,223));   //255,182,193
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(80, 50, 833, 625);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(253,213,223));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//icon
		Image icon = Toolkit.getDefaultToolkit().getImage("icon.png");
		setIconImage(icon);
		
		JLabel lblNewLabel = new JLabel("Help cute cat find path to Reel ");
		lblNewLabel.setFont(new Font("Elephant", Font.BOLD, 25));
		lblNewLabel.setBounds(191, 0, 430, 85);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("cat_find_path.png"));
		lblNewLabel_1.setBounds(40, 78, 659, 500);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("START");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				MazeColor2 window2 = new MazeColor2();
				window2.setVisible(true);
			}

		});
		btnNewButton.setFont(new Font("Elephant", Font.PLAIN, 20));
		btnNewButton.setBackground(new Color(200, 222, 202));
		btnNewButton.setBounds(599, 493, 161, 49);
		contentPane.add(btnNewButton);
	}
	
	private void close() {
		this.dispose();
		
	}

	

}
