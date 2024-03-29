/* Concentration.java
   Kenton Duprey and Ian McMurray
   Java Graphics II G
   Mr. Blondin
   11/29/2016
*/


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Concentration extends JFrame implements ActionListener
	{
	JPanel mainPanel = new JPanel();
	JPanel center = new JPanel();
	JPanel east = new JPanel();
	
	BorderLayout border = new BorderLayout();
	GridLayout board = new GridLayout(4, 4, 1, 1);
	
	int numMatches;
	JButton newGame = new JButton("Start!");
	JLabel matches = new JLabel("Matches: " + numMatches);
	JButton[] cards = new JButton[16];
	
	boolean[] alreadyClicked = new boolean[16];
	
	ImageIcon orig = new ImageIcon("imageOriginal.gif");
	
	ArrayList<String> images; // new ArrayList<String>();
	List<ImageIcon> imageIconsRandom;// = new ArrayList<ImageIcon>();
	
	// variables
	String image1, image2;
	int index1, index2;
	int click = 1;
	boolean match = false;
	
	public Concentration()
		{
		super("Concentration");
		setSize(450, 450);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainPanel.setLayout(border);
		
		Font font = new Font(Font.SANS_SERIF, 1, 18);
		matches.setFont(font);
		newGame.setFont(font);
		
		makeBoard();
		newGame.addActionListener(this);
		
		/*mainPanel.add(center, BorderLayout.CENTER);
		mainPanel.add(east, BorderLayout.EAST);
		
		east.add(newGame, BorderLayout.NORTH);
		east.add(matches, BorderLayout.SOUTH);
		
		
		add(mainPanel);*/
		validate();
		}
	public void makeBoard()
	{
		System.out.print("Here");
		//removeAll();
		addCards();
		addGameimages();
		mainPanel.add(center, BorderLayout.CENTER);
		mainPanel.add(east, BorderLayout.EAST);
		
		east.add(newGame, BorderLayout.NORTH);
		east.add(matches, BorderLayout.SOUTH);
		
		
		add(mainPanel);
		validate();
	}
	
	public void addCards()
		{
		images = new ArrayList<String>();
		center.setLayout(board);
		for (int i = 0; i < cards.length; i++)
			{
			cards[i] = new JButton(orig);
			center.add(cards[i]);
			cards[i].addActionListener(this);
			}
		for (int i = 0; i < 8; i++)
			{
			for (int x = 0; x < 2; x++)
				{
				images.add(new String("image" + i + ".gif"));
				}
			}
			
		}
	
	public void addGameimages()
		{
			imageIconsRandom = new ArrayList<ImageIcon>();
			
		
		for (int i = 0; i<16; i++)
			{
			int num = (int)(Math.random() * images.size());
			imageIconsRandom.add(new ImageIcon(images.get(num)));
			images.remove(num);
				
			}
		/*for (int i = 0; i < images.size(); i++)
			{
				randomImages.add(images.get((int)(Math.random() * 15) + 1));
			}
		for (int i = 0; i < randomImages.size(); i++)
			{
				imageIconsRandom.add(new ImageIcon(randomImages.get(i)));
			}*/
		}
	
	public void actionPerformed(ActionEvent e)
		{
		Object source = e.getSource();
		if (source == newGame)
			{
				center.removeAll();
				makeBoard();
				validate();
				resetAlreadyClicked(alreadyClicked);
			/*for (int i = 0; i < cards.length; i++)
				{
					cards[i] = new JButton(orig);
					cards[i].setEnabled(true);
				}*/
			}
		
		for (int i = 0; i < cards.length; i++)
			{
			if(source == cards[i])
				{
				if(click == 3)
					{
					
					if(!match)
					{
						
						cards[index1].setIcon(orig);
						cards[index2].setIcon(orig);
						resetAlreadyClicked(alreadyClicked);
						
					}
							click = 1;
					}
				if(click == 2 && !alreadyClicked[i])
					{
					
						cards[i].setIcon(imageIconsRandom.get(i));
						image2 = imageIconsRandom.get(i).getDescription();
						
						index2 = i;
						match = checkMatch(image1, image2);
						if (match)
							{
							//click = 1;
							
							resetAlreadyClicked(alreadyClicked);
							cards[index1].setEnabled(false);
							cards[index2].setEnabled(false);
							click++; // HERE
							numMatches++;
							matches.setText("Matches: " + numMatches);
							} else
							click++;
					}
				if(click == 1)
					{
					alreadyClicked[i] = true;
					cards[i].setIcon(imageIconsRandom.get(i));
					image1 = imageIconsRandom.get(i).getDescription();
					//System.out.print(image1);
					index1 = i;
					//System.out.print(index1);
					click++;
					
					}
				
				}
				//System.out.print(click);
			}
		}
	
	public boolean checkMatch(String str1, String str2)
		{
		
		return str1.equals(str2);
		}
	
	public void resetAlreadyClicked(boolean[] arr)
		{
		for (int i = 0; i < arr.length; i++)
			{
				arr[i] = false;
			}
		}
	
	public static void main(String[] args)
		{
		Concentration app = new Concentration();
			
		}// end main method
	}
