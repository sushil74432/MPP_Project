package librarysystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import business.ControllerInterface;
import business.LoginException;
import business.SystemController;

public class LoginWindow extends JFrame implements LibWindow {
	private static final long serialVersionUID = -4332346753166095308L;
	ControllerInterface ci = new SystemController();
	public static final LoginWindow INSTANCE = new LoginWindow();

	private boolean isInitialized = false;

	private JPanel mainPanel;

	private JPanel panel;


	private JTextField username;
	private JPasswordField password;
	
	
	
	private Image imageBackground = new ImageIcon(this.getClass().getResource("images/loginBackground.jpg")).getImage();
	private Image imageUsername = new ImageIcon(this.getClass().getResource("images/username.jpg")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image imagePassword = new ImageIcon(this.getClass().getResource("images/password.jpg")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);


	
	
	

	private Color commonColor = new Color(35, 55, 62);

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public boolean isInitialized() {
		return isInitialized;
	}

	public void isInitialized(boolean val) {
		isInitialized = val;
	}

	private JTextField messageBar = new JTextField();

	public void clear() {
		messageBar.setText("");
	}

	/* This class is a singleton */
	private LoginWindow() {
		init();
	}

	public void init() {
		mainPanel = new JPanel();
		
		defineLeftHalf();

		mainPanel.setBounds(100, 100, 1505, 1040);
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setLayout(null);

//		mainPanel.add(imagePanel);
		mainPanel.add(panel);

		getContentPane().add(mainPanel);
		isInitialized(true);
	}

	private void defineLeftHalf() {

		panel = new JPanel();
		panel.setBounds(10, 10, 1071, 683);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.setEnabled(true);
		btnNewButton.setBackground(Color.BLUE);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(367, 632, 320, 41);
		panel.add(btnNewButton);
		
		addLoginButtonListener(btnNewButton);
		
		JLabel loginBackground = new JLabel("");
		loginBackground.setIcon(new ImageIcon(imageBackground));
		loginBackground.setBounds(0, 0, 1071, 488);
		panel.add(loginBackground);
		
		JPanel panelBackGround = new JPanel();
		panelBackGround.setBackground(Color.WHITE);
		panelBackGround.setBounds(367, 498, 320, 57);
		panel.add(panelBackGround);
		panelBackGround.setLayout(null);
		
		username = new JTextField("");
		username.setBounds(60, 10, 250, 40);
		panelBackGround.add(username);
		username.setFont(new Font("Tahoma", Font.PLAIN, 16));
		username.setColumns(10);
		
		JLabel lblUsernameIcon = new JLabel("");
		lblUsernameIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsernameIcon.setBounds(0, 0, 60, 57);
		lblUsernameIcon.setIcon(new ImageIcon(imageUsername));
		panelBackGround.add(lblUsernameIcon);
		
		JPanel panelPassword = new JPanel();
		panelPassword.setBackground(Color.WHITE);
		panelPassword.setBounds(367, 565, 320, 57);
		panel.add(panelPassword);
		panelPassword.setLayout(null);
		
		password = new JPasswordField("");
		password.setBounds(60, 10, 250, 40);
		panelPassword.add(password);
		password.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblPasswordIcon = new JLabel("");
		lblPasswordIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblPasswordIcon.setBounds(0, 0, 60, 57);
		lblPasswordIcon.setIcon(new ImageIcon(imagePassword));
		panelPassword.add(lblPasswordIcon);

	}
	
	private void addLoginButtonListener(JButton butn) {
		butn.addActionListener(evt -> {

			try {
				ci.login(username.getText(), password.getText());
			} catch (LoginException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, e.getMessage());
				// e.printStackTrace();
				return;
			}

			LibrarySystem.hideAllWindows();
			PortalWindow.INSTANCE.setUser(username.getText(), SystemController.currentAuth);
			Util.centerFrameOnDesktop(PortalWindow.INSTANCE);
			PortalWindow.INSTANCE.setVisible(true);
		});
	}


	public void reset() {
		username.setText("");
		password.setText("");
	}

}
