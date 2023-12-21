import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class QuizJava extends JFrame implements ActionListener
{
	//Components for the quiz
	JTextArea textArea;
	JRadioButton jb[] = new JRadioButton[5];    //Radio buttons for options
	JButton b1,b2,b3;    //Buttons for navigation
	ButtonGroup bg;    //Button group for radio buttons
	int count=0,current=0,x=1,y=1,now=0;    //Variables for tracking quiz progress and bookmarks
	int m[]=new int[10];    //Array to store bookmarked questions

	QuizJava(String s)
	{
		super(s);
		//Initializing components
		textArea = new JTextArea();
        textArea.setLineWrap(true); //Enable wrapping for long text
        textArea.setWrapStyleWord(true);
		textArea.setBackground(this.getBackground()); //Set background same as JFrame
		add(textArea);
		bg = new ButtonGroup();    //Initialize button group
		for(int i = 0; i < 5; i++)
		{
			jb[i] = new JRadioButton();	
			add(jb[i]);
			bg.add(jb[i]);    //Add radio buttons to the group
		}

		//Initializing buttons and their actions
		b1 = new JButton("Next");
		b2 = new JButton("Bookmark");
		b3 = new JButton("Back");
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		add (b3);    //Add Back button
		add(b1);    //Add Next button
		add(b2);    //Add Bookmark button
		set();    //Set the quiz questions and options
		
		//Setting the positions of components
        //Adjusted bounds for the question area
         textArea.setBounds(30, 20, 540, 60); //Adjusted bounds for the question area
        //Adjusted bounds for the options to create more space between them
        jb[0].setBounds(50, 90, 500, 20);
        jb[1].setBounds(50, 120, 500, 20);
        jb[2].setBounds(50, 150, 500, 20);
        jb[3].setBounds(50, 180, 500, 20);

		//Adjusted position for the buttons
        b3.setBounds(50, 240, 100, 30);
        b1.setBounds(200, 240, 100, 30); //Adjusted position for the "Next" button
        b2.setBounds(350, 240, 100, 30); //Adjusted position for the "Bookmark" button
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);    //Using absolute positioning
		setLocation(250,100);
		setVisible(true);
		setSize(600,350);
	}

	//Action listener for button clicks
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == b1)
		{
			if(check())
				count = count+1;
			current++;
			set();	
			if(current == 9)
			{
				b1.setEnabled(false);
				b2.setText("Result");
			}
		}

		if(e.getActionCommand().equals("Bookmark"))
		{
			JButton bk = new JButton("Bookmark"+x);
			bk.setBounds(480,20+30*x,100,30);
			add(bk);
			bk.addActionListener(this);
			m[x]=current;
			x++;
			current++;
			set();	
			if(current == 9)
				b2.setText("Result");
			setVisible(false);
			setVisible(true);
		}

		for(int i = 0, y = 1; i < x; i++, y++)
		{
		    if(e.getActionCommand().equals("Bookmark"+y))
            {
                if(check())
                    count = count+1;
                now = current;
                current = m[y];
                set();
                ((JButton)e.getSource()).setEnabled(false);
                current=now;
            }
		}
	
		if(e.getActionCommand().equals("Result"))
		{
			if(check())
				count = count+1;
			current++;
			JOptionPane.showMessageDialog(this,"correct ans="+count);
			System.exit(0);
		}

		if (e.getSource() == b3) {
           if (current > 0) {
               current--;
              set();
          }
        }
	}

	//Method to set questions and options based on the current question number
	void set()
	{
		jb[4].setSelected(true);

		if(current == 0)
		{
			textArea.setText("Que1:Who has been appointed as the chairman of Centraj board of indirect taxes and customs(CBIC)?");
			jb[0].setText(" John Joseph");jb[1].setText(" Vanaja N. Sarna");jb[2].setText(" Mahender Singh");jb[3].setText("S. Ramesh");	
		}

		if(current == 1)
		{
			textArea.setText("Que2: India's first ever national police museum will establish in which city?");
			jb[0].setText(" Chennai");jb[1].setText(" Delhi");jb[2].setText(" Nagpur");jb[3].setText(" Kolkata");
		}

		if(current == 2)
		{
			textArea.setText("Que3: Which country's women cricket team has clinched the Asia cup 20-20 tournament 2018?");
			jb[0].setText(" South Korea");jb[1].setText(" Bangladesh");jb[2].setText(" India");jb[3].setText(" Pakistan");
		}

		if(current == 3)
		{
			textArea.setText("Que4: The major component of LPG is:");
			jb[0].setText(" Methane");jb[1].setText(" Butane");jb[2].setText(" Ethane");jb[3].setText(" Propane");
		}

		if(current == 4)
		{
			textArea.setText("Que5: The Newspaper started by Subhas Chandra Bose?");
			jb[0].setText(" Mumbai Mirrors");jb[1].setText(" Times of India");jb[2].setText(" Swaraj");jb[3].setText(" Kesari");
		}

		if(current == 5)
		{
			textArea.setText("Que6: Which is the world's largest island?");
			jb[0].setText(" Greenland");jb[1].setText(" Lakshadweep");jb[2].setText(" Victoria");jb[3].setText(" None of the above");
		}

		if(current == 6)
		{
			textArea.setText("Que7: Which is the first bollywood movie?");
			jb[0].setText(" Black and White");jb[1].setText(" Pushpak");jb[2].setText(" Mugle Azum");jb[3].setText(" Raja Harish Chandra");
		}

		if(current == 7)
		{
			textArea.setText("Que8: Chemical formula of laughing gas?");
			jb[0].setText(" NH4");jb[1].setText(" NO2");jb[2].setText(" N2O");jb[3].setText(" SO3");		
		}

		if(current == 8)
		{
			textArea.setText("Que9: Which of the following is not in the list of 7 Wonders of the world?");
			jb[0].setText(" Eiffel Tower");jb[1].setText(" Tal Mahal");jb[2].setText(" Great wall of China");jb[3].setText(" Colosseum");
		}

		if(current == 9)
		{
			textArea.setText("Que10: Which is the most luxurious car in the world?");
			jb[0].setText(" Rolls Royce");jb[1].setText(" BMW");jb[2].setText(" Toyota");jb[3].setText(" Lamburgini");
		}

		textArea.setBounds(30, 40, 450, 20);

        for (int i = 0, j = 0; i <= 90; i += 30, j++) 
        {
            jb[j].setBounds(50, 80 + i, 200, 20);
        }
        b3.setBounds(50, 240, 100, 30); //Adjusted back button position
    }

	//Method to check the selected option against the correct answer for the current question
    boolean check()
    {
		if(current == 0)
			return(jb[3].isSelected());

		if(current == 1)
			return(jb[1].isSelected());

		if(current == 2)
			return(jb[1].isSelected());

		if(current == 3)
			return(jb[1].isSelected());

		if(current == 4)
			return(jb[2].isSelected());

		if(current == 5)
			return(jb[0].isSelected());

		if(current == 6)
			return(jb[3].isSelected());

		if(current == 7)
			return(jb[2].isSelected());

		if(current == 8)
			return(jb[0].isSelected());

		if(current == 9)
			return(jb[0].isSelected());

		return false;
	}

	public static void main(String s[])
	{
		new QuizJava("Quiz");
	}
}