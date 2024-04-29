//testing gui not used

package testingProj;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLayeredPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.SystemColor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class Testing2 extends JFrame {

	private JLayeredPane contentPane;
	private JTextField txtS;
	private JTextField txtS_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Testing2 frame = new Testing2();
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
	public Testing2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 657, 406);
		contentPane = new JLayeredPane();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setForeground(new Color(255, 175, 175));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton DFS = new JButton("DFS");
		DFS.setFont(new Font("Pacifico", Font.PLAIN, 15));
		DFS.setBackground(new Color(255, 128, 128));
		DFS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		DFS.setBounds(365, 41, 105, 35);
		contentPane.add(DFS);
		
		JButton BFS = new JButton("BFS");
		BFS.setFont(new Font("Pacifico", Font.PLAIN, 15));
		BFS.setBackground(new Color(255, 128, 128));
		BFS.setBounds(365, 116, 105, 35);
		contentPane.add(BFS);
		
		txtS = new JTextField();
		txtS.setEditable(false);
		txtS.setFont(new Font("Pacifico", Font.ITALIC, 15));
		txtS.setText("30.5 s");
		txtS.setBackground(new Color(192, 192, 192));
		txtS.setBounds(511, 41, 96, 35);
		contentPane.add(txtS);
		txtS.setColumns(10);
		
		txtS_1 = new JTextField();
		txtS_1.setEditable(false);
		txtS_1.setBackground(new Color(192, 192, 192));
		txtS_1.setText("30.2 s");
		txtS_1.setFont(new Font("Pacifico", Font.ITALIC, 15));
		txtS_1.setBounds(511, 116, 96, 35);
		contentPane.add(txtS_1);
		txtS_1.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 128, 128));
		panel.setBounds(10, 11, 329, 313);
		contentPane.add(panel);
		
		JButton clear = new JButton("Clear");
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		clear.setBackground(SystemColor.activeCaption);
		clear.setFont(new Font("Pacifico", Font.PLAIN, 15));
		clear.setBounds(107, 335, 89, 23);
		contentPane.add(clear);
		
		JButton exit = new JButton("Exit");
		exit.setForeground(new Color(0, 0, 0));
		exit.setBackground(new Color(255, 128, 0));
		exit.setFont(new Font("Pacifico", Font.PLAIN, 15));
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		exit.setBounds(518, 335, 89, 23);
		contentPane.add(exit);
	}
}
