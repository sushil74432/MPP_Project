package librarysystem;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import business.ControllerInterface;
import business.SystemController;
import resources.ColorPalatte;


public class AllBookIdsWindow extends JFrame implements LibWindow {
	private static final long serialVersionUID = 1L;
	public static final AllBookIdsWindow INSTANCE = new AllBookIdsWindow();
    ControllerInterface ci = new SystemController();
    private boolean isInitialized = false;
	
	private JPanel mainPanel;
	private JPanel topPanel;
	private JPanel middlePanel;
	private JPanel lowerPanel;
	private TextArea textArea;
	

	//Singleton class
	private AllBookIdsWindow() {
		init();
	}
	
	public JPanel getMainPanel() {
		return mainPanel;
	}
	
	public void init() {
		mainPanel = new JPanel();
		mainPanel.setBackground(ColorPalatte.BACKGROUND);
		mainPanel.setLayout(new BorderLayout());
		defineTopPanel();
		defineMiddlePanel();
		defineLowerPanel();
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(middlePanel, BorderLayout.CENTER);
		//mainPanel.add(lowerPanel, BorderLayout.SOUTH);
		getContentPane().add(mainPanel);
		isInitialized = true;
		
		setData();
	}
	
	public void defineTopPanel() {
		topPanel = new JPanel();
		topPanel.setBackground(ColorPalatte.BACKGROUND);
		FlowLayout flowLayout = (FlowLayout) topPanel.getLayout();
		flowLayout.setVgap(30);
		JLabel AllIDsLabel = new JLabel("All Book IDs");
		AllIDsLabel.setFont(new Font("Fira Code Retina", Font.BOLD, 20));
		topPanel.add(AllIDsLabel);	
	}
	
	public void defineMiddlePanel() {
		middlePanel = new JPanel();
		middlePanel.setBackground(ColorPalatte.BACKGROUND);
		FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 25, 25);
		middlePanel.setLayout(fl);
		textArea = new TextArea(8, 20);
		//populateTextArea();
		middlePanel.add(textArea);
		
	}
	
	public void defineLowerPanel() {
		
		JButton backToMainButn = new JButton("<= Back to Main");
		backToMainButn.addActionListener(new BackToMainListener());
		lowerPanel = new JPanel();
		lowerPanel.setBackground(ColorPalatte.BACKGROUND);
		lowerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));;
		lowerPanel.add(backToMainButn);
	}
	
	class BackToMainListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent evt) {
			LibrarySystem.hideAllWindows();
			LibrarySystem.INSTANCE.setVisible(true);
    		
		}
	}
	
	public void setData() {
		List<String> ids = ci.allBookIds();
		Collections.sort(ids);
		StringBuilder sb = new StringBuilder();
		for (String s : ids) {
			sb.append(s + "\n");
		}
		setData(sb.toString());
	}
	public void setData(String data) {
		textArea.setText(data);
	}
	
//	private void populateTextArea() {
//		//populate
//		List<String> ids = ci.allBookIds();
//		Collections.sort(ids);
//		StringBuilder sb = new StringBuilder();
//		for(String s: ids) {
//			sb.append(s + "\n");
//		}
//		textArea.setText(sb.toString());
//	}

	@Override
	public boolean isInitialized() {
		// TODO Auto-generated method stub
		return isInitialized;
	}

	@Override
	public void isInitialized(boolean val) {
		isInitialized = val;
		
	}
}
