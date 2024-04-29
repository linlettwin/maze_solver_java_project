package mazeCollection;

import java.awt.*;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List; //required
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import testingProj.Maze;
import java.io.*;
import java.util.*;
import javax.swing.JLabel;

import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


public class Level1Maze extends JFrame {

	private JTextField txtS;
	private JTextField txtS_1;
	ImageIcon image;

	final static int V = 2; // visited node
	final static int B = 1; // wall block node
	final static int START_I = 0, START_J = 1; // starting point (0,7)
	final static int END_I = 11, END_J =1; // ending point (11,5)

	// for calculating duration
	long startTime;
	long stopTime;
	long duration;
	double dfsTime;
	double bfsTime;

	// for clear button to do repainting
	boolean repaint = false;
	boolean show = true;

	// saving original maze
	int[][] savedMaze = new int[][]

	          
	 {  {1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
	    {1, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1},
	    {1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1},
	    {1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
	    {1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1},
	    {1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1},
	    {1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1},
	    {1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1},
	    {1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1},
	    {1, 1, 1, 0, 0, 1, 1, 0, 1, 1, 0, 1},
	    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},  
	    {1, 9, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
	};

	int[][] arr = clone();

	/**
	 * 
	 * 
	 * maze[row][col]
	 * 
	 * Values: 0 = not-visited node 1 = wall (blocked) 2 = visited node 9 = target
	 * node 3 = starting node
	 */

	int[][] maze = new int[][]

	 {  {1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
	    {1, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1},
	    {1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1},
	    {1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
	    {1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1},
	    {1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1},
	    {1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1},
	    {1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1},       
	    {1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1},
	    {1, 1, 1, 0, 0, 1, 1, 0, 1, 1, 0, 1},
	    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
	    {1, 9, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
	};

	

	public int Size = maze.length;
	boolean flag = true;

	// color for actual path
	Color c1 = new Color(51,255,153);
	Color c2 = new Color(251, 220, 220);  //baby pink
	Color c3 = new Color (224,108,96); //orange pink
	

	public Level1Maze() {
		getContentPane().setBackground(new Color(253,213,223));
		setTitle("Maze Solver");
		setSize(980, 600); // 640,480 window size
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(80, 50, 980, 600);
		setBackground(new Color(238, 237, 221));
		getContentPane().setLayout(null);
		setVisible(true);

		// app icon
		Image icon = Toolkit.getDefaultToolkit().getImage("icon.png");
		setIconImage(icon);
		
		//cat image
		JLabel cat = new JLabel();
		//cat.setLocation(10, 10);
		cat.setIcon(new ImageIcon("cat1.png"));
		cat.setLayout(null);
		cat.setBounds(55, 10, 80, 80);
		getContentPane().add(cat);
		
		//ball image
		JLabel ball = new JLabel();
		ball.setIcon(new ImageIcon("ball1.png"));
		ball.setLayout(null);
		ball.setBounds(55, 450, 60, 60);
		getContentPane().add(ball);
		

		// DFS BUTTON
		JButton DFS_B = new JButton("DFS");
		DFS_B.setBounds(519, 54, 144, 47);
		DFS_B.setFont(new Font("Elephant", Font.PLAIN, 16));
		DFS_B.setBackground(new Color(200, 222, 202));
		DFS_B.addActionListener(new ActionListener() { // DFS algorithm
			public void actionPerformed(ActionEvent e) {

				restore(savedMaze); // saving the original maze
				repaint = false; // set to repaint maze solution
				solveStack(); // solves the maze using DFS
				repaint(); // repainting the maze on the JFrame
				show = true; // saving the dfs maze

			}
		});
		DFS_B.setLayout(null);
		getContentPane().add(DFS_B);

		// BFS BUTTON
		JButton BFS = new JButton("BFS");
		BFS.setBounds(519, 165, 144, 52);
		BFS.setFont(new Font("Elephant", Font.PLAIN, 16));
		BFS.setBackground(new Color(200, 222, 202));
		BFS.addActionListener(new ActionListener() { // DFS algorithm
			public void actionPerformed(ActionEvent e) {

				restore(savedMaze); // saving the original maze
				repaint = false; // set to repaint maze solution
				solveQueue(); // solves the maze using DFS
				repaint(); // repainting the maze on the JFrame
				show = true; // saving the bfs maze

			}
		});
		getContentPane().add(BFS);

		// Show Time for DFS
		txtS = new JTextField();
		txtS.setBounds(753, 49, 152, 47);
		txtS.setEditable(false);
		txtS.setFont(new Font("Elephant", Font.BOLD, 15));
		txtS.setText(" 0.0 s");
		txtS.setBackground(new Color(200, 222, 202));
		getContentPane().add(txtS);
		txtS.setColumns(10);

		// Show Time for BFS
		txtS_1 = new JTextField();
		txtS_1.setBounds(753, 165, 152, 47);
		txtS_1.setEditable(false);
		txtS_1.setBackground(new Color(200, 222, 202));
		txtS_1.setText(" 0.0 s");
		txtS_1.setFont(new Font("Elephant", Font.BOLD, 15));
		getContentPane().add(txtS_1);
		txtS_1.setColumns(10);

		// Clear BUTTON
		JButton clear = new JButton("Clear");
		clear.setBounds(519, 452, 136, 47);
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				flag = false; // to clear the shortest path
				restore(savedMaze); // restore the maze to the original
				repaint = true;
				repaint();
				txtS_1.setText(""); // clear the text
				txtS.setText("");

			}
		});
		clear.setBackground(new Color(200, 222, 202));
		clear.setFont(new Font("Elephant", Font.PLAIN, 15));
		getContentPane().add(clear);

		// Exit BUTTON
		JButton exit = new JButton("Exit");
		exit.setBounds(769, 452, 136, 47);
		exit.setForeground(new Color(0, 0, 0));
		exit.setBackground(new Color(200, 222, 202));
		exit.setFont(new Font("Elephant", Font.PLAIN, 15));
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				MazeColor2 win2 = new MazeColor2();
				win2.setVisible(true);
				new_close();

			}
		});
		getContentPane().add(exit);

		// Label for time
		JLabel TimeLabel = new JLabel("Elapsed time:");
		TimeLabel.setFont(new Font("Elephant", Font.BOLD, 13));
		TimeLabel.setBounds(753, 24, 132, 19);
		getContentPane().add(TimeLabel);

		JLabel TimeLabel2 = new JLabel("Elapsed time:");
		TimeLabel2.setFont(new Font("Elephant", Font.BOLD, 13));
		TimeLabel2.setBounds(753, 137, 125, 28);
		getContentPane().add(TimeLabel2);

		// show shortest path
		JButton path = new JButton("Show path");
		path.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				show = false; // only showing the shortest path and dfs and bfs button not click
				actual_path();

			}
		});
		path.setFont(new Font("Elephant", Font.BOLD, 15));
		path.setBackground(new Color(200, 222, 202));
		path.setBounds(630, 272, 144, 47);
		getContentPane().add(path);

	}

	// get size of the maze
	public int Size()

	{
		return Size;
	}

	// Print the Maze to Console
	public void Print() {
		for (int i = 0; i < Size(); i++) {
			for (int J = 0; J < Size(); J++) {
				System.out.print(maze[i][J]);
				System.out.print(' ');
			}
			System.out.println();
		}
	}

	// checking the cell is in the Maze
	public boolean isInMaze(int i, int j) // cell position (i,j)
	{
		if (i >= 0 && i < Size() && j >= 0 && j < Size()) {
			return true;
		} else {
			return false;
		}
	}

	
	public boolean isInMaze(MazePos pos) 
	{
		return isInMaze(pos.i(), pos.j());
	}

	// mark the visited node //set the cell =2 if the node is visited
	public int mark(int i, int j, int value) {
		assert (isInMaze(i, j)); //test erroe
		int temp = maze[i][j]; // store the original value in temp
		maze[i][j] = value; // put the value from the parameter in maze cell with corresponding i,j
		return temp; // return original value
	}

	
	public int mark(MazePos pos, int value) 
	{
		return mark(pos.i(), pos.j(), value);
	}

	// return true if the node equal to V=2 (Green, Explored)
	public boolean isMarked(int i, int j) {
		assert (isInMaze(i, j)); // testing
		return (maze[i][j] == V); // visited ?
	}

	
	public boolean isMarked(MazePos pos) {
		return isMarked(pos.i(), pos.j());
	}

	// return true if the node is equal to 0 (White and Unvisited)
	public boolean isClear(int i, int j) {
		assert (isInMaze(i, j));
		return (maze[i][j] != B && maze[i][j] != V);
	}


	public boolean isClear(MazePos pos) {
		return isClear(pos.i(), pos.j());
	}

	// to make sure if it is reach the goal (Goal Test)
	public boolean isFinal(int i, int j) {
		// boolean temp2= (i == Level2Maze_dfs.END_I && j == Level2Maze_dfs.END_J);
		return (i == Level1Maze.END_I && j == Level1Maze.END_J);
	}


	public boolean isFinal(MazePos pos) {
		return isFinal(pos.i(), pos.j());
	}

	// make Copy from the original maze ( no need )
	public int[][] clone() {
		int[][] mazeCopy = new int[Size()][Size()];
		for (int i = 0; i < Size(); i++) {
			for (int j = 0; j < Size(); j++) {
				mazeCopy[i][j] = maze[i][j];
			}
		}
		return mazeCopy;
	}

	// to restore the maze to the initial state
	public void restore(int[][] savedMazed) {
		for (int i = 0; i < savedMazed.length; i++) {
			for (int j = 0; j < savedMazed.length; j++) {
				maze[i][j] = savedMazed[i][j];
			}
		}
		maze[0][1] = 3; // the start point
		maze[11][1] = 9; // the goal
	}

	// copy to temp array
	public void copy_arr(int[][] cop) {
		for (int i = 0; i < cop.length; i++) {
			for (int j = 0; j < cop.length; j++) {
				arr[i][j] = cop[i][j];
			}

		}
		
	}
    
	
	Image img1=Toolkit.getDefaultToolkit().getImage("paw1.png");
	int w = img1.getWidth(this);
	int h =img1.getHeight(this);
	
	
	
	// Maze Drawing
	@Override
	public void paint(Graphics g) {
		super.paint(g);
 
		g.translate(50, 120);           
		
		// draw the maze
		if (repaint == true) { // (draw the maze as a problem without the solution)
			for (int row = 0; row < maze.length; row++) {
				for (int col = 0; col < maze[0].length; col++) {
					Color color;
					switch (maze[row][col]) {
					case 1:
						color = Color.DARK_GRAY; // block (black)
						//color = c3;
						break;
					case 9:
						color = Color.RED; // goal (red)
						break;
					case 3:
						color = Color.ORANGE; // initial state (yellow)
						break;
					// case '.' : color=Color.ORANGE; break;
					default:
						color = Color.WHITE; // white free space 0 (white)
						//color = c2;
					}
					g.setColor(color);
					g.fillRect(30 * col, 30 * row, 30, 30); // fill rectangular with color
					g.setColor(Color.DARK_GRAY); // the border rectangle color
					g.drawRect(30 * col, 30 * row, 30, 30); // draw rectangular with color

				}
			}
		}

		if (repaint == false) { // (draw the solution for the maze)
			for (int row = 0; row < maze.length; row++) {
				for (int col = 0; col < maze[0].length; col++) {
					Color color;
					switch (maze[row][col]) {
					case 1:
						color = Color.DARK_GRAY; // block (black)
						//color = c3;
						break;
					case 9:
						color = Color.RED; // goal (red)
						break;
					case 3:
						color = Color.ORANGE; // initial state (yellow)
						break;
					case 2:
						color = Color.GREEN; // the path from the initial state to the goal
						break;
					default:
						color = Color.WHITE; // white free space 0 (white)
						//color = c2;
					}
					g.setColor(color);
					g.fillRect(30 * col, 30 * row, 30, 30); // fill rectangular with color
					g.setColor(Color.DARK_GRAY); // the border rectangle color
					g.drawRect(30 * col, 30 * row, 30, 30); // draw rectangular with color

				}

			}
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {

				Level1Maze view = new Level1Maze();
				// view.setVisible(true);

			}
		});
	}

//close window for exit button         	
	public void close() {
		this.dispose();
	}

//DFS 
	public void solveStack() {

		// start time
		startTime = System.nanoTime();

		
		Stack<MazePos> stack = new Stack<MazePos>();

		// insert the start node
		stack.push(new MazePos(START_I, START_J));

		MazePos crt; // current node
		MazePos next; // next node

		while (!stack.empty()) // not empty
		{
			// get current position by popping from stack
			crt = stack.pop();
			if (isFinal(crt)) { // if the goal is reached then exit, no need for further exploration.
				break;
			}

			mark(crt, V); // mark the current position as explored

			// push its neighbours in the stack
			next = crt.north(); // go up from the current node
			if (isInMaze(next) && isClear(next)) { // isClear() method is used to implement Graph Search (in tree we can
													// reExplore nodes, could get stuck in infinite loop which happened
													// to up in previous builds)
				stack.push(next);
			}
			next = crt.east(); // go right from the current node
			if (isInMaze(next) && isClear(next)) {
				stack.push(next);
			}
			next = crt.west(); // go left from the current node
			if (isInMaze(next) && isClear(next)) {
				stack.push(next);
			}
			next = crt.south(); // go down from the current node
			if (isInMaze(next) && isClear(next)) {
				stack.push(next);
			}
		}
		if (!stack.empty()) { 
			stopTime = System.nanoTime();
			JOptionPane.showMessageDialog(rootPane, "It reaches the goal");
		} else { // the stack is empty
			JOptionPane.showMessageDialog(rootPane, "It is stuck in the maze");
		}
		System.out.println("\nFind Goal By DFS : ");
		Print();
		// stop time

		duration = stopTime - startTime; // calculate the elapsed time
		dfsTime = (double) duration / 1000000; // convert to ms
		System.out.println(String.format("Time %1.3f ms", dfsTime));
		txtS.setText(String.format("%1.3f ms", dfsTime));
	}

//BFS
	public void solveQueue() {

		// start the timer
		startTime = System.nanoTime();

		LinkedList<MazePos> list = new LinkedList<MazePos>();

		// add initial node to the list
		list.add(new MazePos(START_I, START_J));

		MazePos crt, next;
		while (!list.isEmpty()) {

			// get current position
			crt = list.removeFirst();

			// to be sure if it reach the goal
			if (isFinal(crt)) { // if the goal is reached then exit, no need for further exploration.
				break;
			}

			// mark the current position as explored
			mark(crt, V);

			// add its neighbors in the queue
			next = crt.north(); // move up
			if (isInMaze(next) && isClear(next)) { 
				list.add(next);
			}
			next = crt.east(); // move right
			if (isInMaze(next) && isClear(next)) {
				list.add(next);
			}
			next = crt.west(); // move left
			if (isInMaze(next) && isClear(next)) {
				list.add(next);
			}
			next = crt.south(); // move down
			if (isInMaze(next) && isClear(next)) {
				list.add(next);
			}

		}

		if (!list.isEmpty()) { 
			stopTime = System.nanoTime(); // stop the timer
			JOptionPane.showMessageDialog(rootPane, "It reaches the goal");
		} else { 
			JOptionPane.showMessageDialog(rootPane, "It is stuck in the maze");
		}

		System.out.println("\nFind Goal By BFS : ");
		Print();

		duration = stopTime - startTime; // calculate the elapsed time

		bfsTime = (double) duration / 1000000; // convert to ms
		System.out.println(String.format("Time %1.3f ms", bfsTime));

		txtS_1.setText(String.format("%1.3f ms", bfsTime));

	}

//actual path (shortest path)
	public final List<Integer> path = new ArrayList<Integer>();
	public int pathIndex;
	public Timer t= new Timer();
	public int count=0;
	
	Clip clip;
	AudioInputStream sound;
	
	public class Music {
	    Clip clip;
	    AudioInputStream sound;
	    public void setFile(String soundFileName) {
	        try {
	            File file = new File(soundFileName);
	            sound = AudioSystem.getAudioInputStream(file);
	            clip = AudioSystem.getClip();
	            clip.open(sound);
	        } catch (Exception e) {
	        }
	    }
	    public void play() {
	        clip.start();
	    }
	    public void stop() throws IOException {
	        sound.close();
	        clip.close();
	        clip.stop();
	    }
	}

	
	public void actual_path() {

		
		DisplayGraphics maze_shortest = new DisplayGraphics();
		
	
		String audioFilePath = "audio9.wav";
		playAudio(audioFilePath);

		new_close();
		

	}
	
	String sound_track = "maze_sound.wav";
	public class DisplayGraphics extends Level1Maze {

		String sound_track;
		Music se=new Music();
		public DisplayGraphics() {

			arr = clone();
    		DepthFirst.searchPath(arr, 1, 1, path);

    		
			System.out.println(path.size());
			pathIndex = path.size() - 2;

			// game loop each 0.5 seconds
			  
			  t.schedule(new TimerTask() {
			  
			  @Override public void run() 
			  { 
				  update(); 
				  repaint(); 
				  count++; 
				  System.out.println(count);
				  //se.setFile(sound_track);
				  //se.play();
				  
				  if (count>= 32)
				  { 
					  t.cancel();
					  t.purge();
					  
					  //se.stop();
				  }
			  } 
			  }, 100, 480);
			  
			 
		}
		
		
	  
		

		
		public void update() {
			pathIndex -= 2;
			if (pathIndex < 0) {
				pathIndex = 0;
			}
		}

		public void paint(Graphics g) {
			super.paint(g);

			// draw the path list
			int n = path.size();
			System.out.println(path.size());

			if (flag == true) {
				System.out.println("flag == true"); // draw the path list
				for (int p = 0; p < path.size(); p += 2) {
					int pathX = path.get(p);
					int pathY = path.get(p + 1);
					// g.setColor(Color.BLUE);
					g.setColor(c1);
					g.fillRect(pathX * 30, pathY * 30, 30, 30);
				}

				// draw the ball on path
				int pathX = path.get(pathIndex);
				int pathY = path.get(pathIndex + 1);
				g.setColor(Color.RED);
				//g.fillOval(pathX * 30, pathY * 30, 30, 30);
				g.drawImage(img1, pathX*30, pathY*30,30,30 , this);
			}
		}
        
		@Override
	    protected void processKeyEvent(KeyEvent ke) {
	        if (ke.getID() != KeyEvent.KEY_PRESSED) {
	            return;
	        }
	        if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
	            pathIndex -= 2;
	            if (pathIndex < 0) {
	                pathIndex = 0;
	            }
	        }
	        else if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
	            pathIndex += 2;
	            if (pathIndex > path.size() - 2) {
	                pathIndex = path.size() - 2;
	            }
	        }
	        repaint(); 
	    }

		
	}

	
	public void new_close()
	{
		this.dispose();
	}
	
	public static void playAudio(String filePath) {
        try {
            File audioFile = new File(filePath);
            if (audioFile.exists()) {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } else {
                System.err.println("Audio file not found: " + filePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

} // end
