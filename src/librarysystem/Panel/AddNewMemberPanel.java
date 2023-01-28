package librarysystem.Panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import business.Address;
import business.ControllerInterface;
import business.LibraryMember;
import business.LibrarySystemException;
import business.SystemController;


public class AddNewMemberPanel {

	private AddNewMemberPanel() {
	}
	private final static String MAIN_LABEL = "NEW MEMBER DETAILS";
	private final static String MEMBER_ID = "Member Id ";
	private final static String F_NAME = "First Name ";
	private final static String L_NAME = "Last Name ";
	private final static String STREET = "Street ";
	private final static String CITY = "City ";
	private final static String STATE = "State ";
	private final static String ZIP = "Zip";
	private final static String TELEPPHONE = "Telephone ";
	private final static String CANCEL = " Cancel";
	private final static String ADD = " Add ";
	//private final static String BACK = "Back";

	private static JPanel panel;
	private static JTextField memberIdField;
	private static JTextField firstNameField;
	private static JTextField lastNameField;
	private static JTextField streetField;
	private static JTextField cityField;
	private static JTextField stateField;
	private static JTextField zipField;
	private static JTextField telephoneField;
	
	private JLabel labelMemberId, labelFname, labelLname, labelStreet, labelZip, labelState, labelTelephone, labelCity;

	private static ControllerInterface ci = new SystemController();

	public static Component getNewMemberPanel(JFrame frame) {
		return getPanel(frame);
	}

	private static JPanel getPanel(JFrame frame) {
		panel = new JPanel();
		//panel.setBackground(Color.WHITE);
		panel.setBackground(Color.decode("#b8cdf8"));
		panel.setBounds(100, 100, 870, 468);
		panel.setLayout(null);

		JLabel lblAddNewMember = new JLabel("New Member Details");
		lblAddNewMember.setFont(new Font("Fira Code", Font.BOLD, 18));
		//lblAddNewMember.setForeground(Color.decode("#b8cdf8"));
		lblAddNewMember.setBounds(430, 29, 200, 30);
		panel.add(lblAddNewMember);

		memberIdField = new JTextField();
		memberIdField.setColumns(10);
		memberIdField.setBounds(260,120,200,40);
		panel.add(memberIdField);
		
		

		JLabel lblId = new JLabel(MEMBER_ID);
		lblId.setFont(new Font("Fira Code Retina", Font.BOLD, 15));
		lblId.setBounds(160, 130, 130, 15);
		panel.add(lblId);

		firstNameField = new JTextField();
		firstNameField.setColumns(10);
		firstNameField.setBounds(260, 190, 200, 40);
		panel.add(firstNameField);

		JLabel lblId_1 = new JLabel(F_NAME);
		lblId_1.setFont(new Font("Fira Code Retina", Font.BOLD, 15));
		lblId_1.setBounds(160, 200, 250, 15);
		panel.add(lblId_1);

		lastNameField = new JTextField();
		lastNameField.setColumns(10);
		lastNameField.setBounds(620, 190, 200, 40);
		panel.add(lastNameField);

		JLabel lblId_1_1 = new JLabel(L_NAME);
		lblId_1_1.setFont(new Font("Fira Code Retina", Font.BOLD, 15));
		lblId_1_1.setBounds(520, 200, 250, 15);
		panel.add(lblId_1_1);

		streetField = new JTextField();
		streetField.setColumns(10);
		streetField.setBounds(260, 260, 200, 40);
		panel.add(streetField);
		
		JLabel lblStreet = new JLabel(STREET);
		lblStreet.setFont(new Font("Fira Code Retina", Font.BOLD, 15));
		lblStreet.setBounds(160, 270, 250, 15);
		panel.add(lblStreet);


		cityField = new JTextField();
		cityField.setColumns(10);
		cityField.setBounds(620,260, 200, 40);
		panel.add(cityField);

		JLabel lblId_1_2 = new JLabel(CITY);
		lblId_1_2.setFont(new Font("Fira Code Retina", Font.BOLD, 15));
		lblId_1_2.setBounds(520, 270, 250, 15);
		panel.add(lblId_1_2);

		stateField = new JTextField();
		stateField.setColumns(10);
		stateField.setBounds(260, 340, 200, 40);
		panel.add(stateField);

		JLabel lblId_1_1_1 = new JLabel(STATE);
		lblId_1_1_1.setFont(new Font("Fira Code Retina", Font.BOLD, 15));
		lblId_1_1_1.setBounds(160, 350, 200, 15);
		panel.add(lblId_1_1_1);

		zipField = new JTextField();
		zipField.setColumns(10);
		zipField.setBounds(620, 340, 200, 40);
		panel.add(zipField);

		JLabel lblId_1_2_1 = new JLabel(ZIP);
		lblId_1_2_1.setFont(new Font("Fira Code Retina", Font.BOLD, 15));
		lblId_1_2_1.setBounds(520, 350, 200, 15);
		panel.add(lblId_1_2_1);

		telephoneField = new JTextField();
		telephoneField.setColumns(10);
		telephoneField.setBounds(260, 410, 200, 40);
		panel.add(telephoneField);

		JLabel lblId_1_1_1_1 = new JLabel(TELEPPHONE);
		lblId_1_1_1_1.setFont(new Font("Fira Code Retina", Font.BOLD, 15));
		lblId_1_1_1_1.setBounds(160, 420, 200, 15);
		panel.add(lblId_1_1_1_1);

		
		JPanel backgroundPanel = new JPanel();
		backgroundPanel.setBounds(110, 80, 790, 420);
		panel.add(backgroundPanel);
		backgroundPanel.setBackground(Color.decode("#b8cdf8"));
		backgroundPanel.setLayout(null);

		JButton btnAdd = new JButton("Add");
		
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String fName = firstNameField.getText();
				String lName = lastNameField.getText();
				String zip = zipField.getText();
				String state = stateField.getText();
				String city = cityField.getText();
				String id = memberIdField.getText();
				String phone = telephoneField.getText();
				String street = streetField.getText();
				
				if (id.isEmpty() || fName.isEmpty() || lName.isEmpty() || street.isEmpty() || city.isEmpty()
						|| state.isEmpty() || zip.isEmpty() || phone.isEmpty()) {

					JOptionPane.showMessageDialog(null, "Fields can't be empty.");

				} else if (!fName.matches("^[a-zA-Z ]+$") || !lName.matches("^[a-zA-Z]+$")) {
					JOptionPane.showMessageDialog(null, "Enter only valid alphabets.");

				} else if (!zip.matches("\\d{5}")) {
					JOptionPane.showMessageDialog(null, "Zip code must be of 5 digits");

				} else if (!phone.matches("^[0-9]{10}$")) {
					JOptionPane.showMessageDialog(null, "Enter a valid PhoneNumber");
				} else {
					Address address = new Address(street, city, state, zip);
					LibraryMember member = new LibraryMember(id, fName, lName, phone, address);

					try {
						ci.addMember(member);
					} catch (LibrarySystemException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
						return;
					}
					clearInputField();
					JOptionPane.showMessageDialog(null, "Successfully added!!!");

				}
				
			}

		});
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setBackground(Color.decode("#9D8DF1"));
		btnAdd.setBounds(360, 520, 95, 41);
		panel.add(btnAdd);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(e -> {
			clearInputField();
		});
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setBackground(Color.decode("#9D8DF1"));
		btnCancel.setBounds(510, 520, 95, 41);
		panel.add(btnCancel);

		return panel;

	}

	private static void clearInputField() {
		memberIdField.setText("");
		firstNameField.setText("");
		lastNameField.setText("");
		streetField.setText("");
		cityField.setText("");
		stateField.setText("");
		zipField.setText("");
		telephoneField.setText("");
	}

}