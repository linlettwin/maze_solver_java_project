//intro window 2

package mazeCollection;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MazeColor2 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MazeColor2 frame = new MazeColor2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MazeColor2() {
		setTitle("Maze Solver");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(80, 50, 823, 589);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(253,213,223));   //253,213,223
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//icon
		Image icon = Toolkit.getDefaultToolkit().getImage("icon.png");
		setIconImage(icon);
		
		JLabel Icon1 = new JLabel((String) null);
		Icon1.setBounds(344, 211, 45, 13);
		contentPane.add(Icon1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("maze_img.png"));
		lblNewLabel.setBounds(40, 286, 275, 256);
		contentPane.add(lblNewLabel);
		
		JLabel cat = new JLabel("");
		cat.setIcon(new ImageIcon("cat2.gif"));
		cat.setBounds(600, 6, 200, 227);
		contentPane.add(cat);
		
		
		JButton btnNewButton = new JButton("MAZE 1");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				Level1Maze new1=new Level1Maze();
				new1.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Elephant", Font.BOLD, 20));
		btnNewButton.setBackground(new Color(200, 222, 202));
		btnNewButton.setBounds(306, 160, 180, 44);
		contentPane.add(btnNewButton);
		
		JButton btnLevel = new JButton("MAZE 2");
		btnLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				Level2Maze new2= new Level2Maze();
				new2.setVisible(true);
			}
		});
		btnLevel.setFont(new Font("Elephant", Font.BOLD, 20));
		btnLevel.setBackground(new Color(200, 222, 202));
		btnLevel.setBounds(306, 251, 180, 44);
		contentPane.add(btnLevel);
		
		JButton btnLevel_1 = new JButton("MAZE 3");
		btnLevel_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				Level3Maze new3=new Level3Maze();
				new3.setVisible(true);
			}
		});
		btnLevel_1.setFont(new Font("Elephant", Font.BOLD, 20));
		btnLevel_1.setBackground(new Color(200, 222, 202));
		btnLevel_1.setBounds(306, 336, 180, 44);
		contentPane.add(btnLevel_1);
		
		JLabel lblNewLabel_1 = new JLabel("SELECT MAZE");
		lblNewLabel_1.setFont(new Font("Elephant", Font.BOLD, 25));
		lblNewLabel_1.setBounds(272, 54, 275, 51);
		contentPane.add(lblNewLabel_1);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				MazeColor1 window1 = new MazeColor1();
				window1.setVisible(true);
			}
		});
		btnExit.setFont(new Font("Elephant", Font.BOLD, 20));
		btnExit.setBackground(new Color(200, 222, 202));
		btnExit.setBounds(577, 465, 180, 44);
		contentPane.add(btnExit);
		
	}
	
	private void close() {
		this.dispose();
		
	}

}
