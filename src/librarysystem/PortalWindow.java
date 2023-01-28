package librarysystem;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import dataaccess.Auth;
import librarysystem.Panel.AddNewBookCopyPanel;
import librarysystem.Panel.AddNewBookPanel;
import librarysystem.Panel.AddNewMemberPanel;
import librarysystem.Panel.CheckoutPanel;
import librarysystem.Panel.HomePanel;
import librarysystem.Panel.SearchBookPanel;
import librarysystem.Panel.SearchMemberPanel;
import resources.ColorPalatte;
import resources.ThemeColor;

public class PortalWindow extends JFrame implements LibWindow {

	private static final long serialVersionUID = 3842471112999905561L;

	private boolean isInitialized = false;
	private String username = "";
	private Auth auth = Auth.BOTH;

	private JLabel lblWecome;
	private JButton btnLogout;
	JList<MenuItem> funcList;
	JPanel cards;

	private boolean[] enabledFlags;

	public static String[] funcItems = { "Home", "Member", "Add new members", "Display Checkouts", "All memberIds",
			"Book", "Add new books", "Overdue Books", "Add new copies",
			/* "   Check status of book copy", */ "All bookIds", "Checkout" };

	private static int FUNC_HOME = 0;
	private static int FUNC_MEMBER = 1;
	private static int FUNC_CREATE_NEW_MEM = 2;
	private static int FUNC_SEARCH_MEM = 3;
	private static int FUNC_ALL_MEM = 4;
	private static int FUNC_BOOK = 5;
	private static int FUNC_ADD_NEW_BOOK = 6;
	private static int FUNC_SEARCH_BOOK = 7;
	private static int FUNC_ADD_NEW_COPY = 8;
	// private static int FUNC_CHECK_STATUS = 9;
	private static int FUNC_ALL_BOOK = 9;
	private static int FUNC_CHECKOUT = 10;

	public static final PortalWindow INSTANCE = new PortalWindow();

	public PortalWindow() {
		init(username, auth);
	}

	@Override
	public boolean isInitialized() {
		return isInitialized;
	}

	@Override
	public void isInitialized(boolean val) {
		isInitialized = val;
	}

	@Override
	public void init() {

	}

	List<MenuItem> aliList = new ArrayList<>();

	public void setUser(String name, Auth au) {
		username = name;
		auth = au;
		lblWecome.setText("Welcome " + username);

		for (int i = 0; i < enabledFlags.length; i++)
			enabledFlags[i] = false;

		enabledFlags[FUNC_HOME] = true;
		enabledFlags[FUNC_MEMBER] = true;
		enabledFlags[FUNC_ALL_MEM] = true;
		enabledFlags[FUNC_BOOK] = true;
		enabledFlags[FUNC_ALL_BOOK] = true;

		if (au == Auth.ADMIN || au == Auth.BOTH) {
			enabledFlags[FUNC_CREATE_NEW_MEM] = true;
			enabledFlags[FUNC_ADD_NEW_BOOK] = true;
			enabledFlags[FUNC_ADD_NEW_COPY] = true;
		}

		if (au == Auth.LIBRARIAN || au == Auth.BOTH) {
			enabledFlags[FUNC_BOOK] = true;
			enabledFlags[FUNC_CHECKOUT] = true;
			enabledFlags[FUNC_SEARCH_MEM] = true;
			enabledFlags[FUNC_SEARCH_BOOK] = true;
		}
		if (!aliList.isEmpty())
			for (int i = 0; i < enabledFlags.length; i++) {
				aliList.get(i).setEnabled(enabledFlags[i]);
			}
	}

	private void init(String name, Auth au) {
		enabledFlags = new boolean[funcItems.length];

//		setUser(username, au);

		for (int i = 0; i < funcItems.length; i++) {
			funcItems[i] = " " + funcItems[i];
			String imagePath;
			switch (i) {
			case 0:
				imagePath = "home.png";
				break;
			case 1:
				imagePath = "group.png";
				break;
			case 5:
				imagePath = "book.png";
				break;
			default:
				imagePath = "menu.png";
				funcItems[i] = "" + funcItems[i];
				break;
			}

			MenuItem m = new MenuItem(funcItems[i], imagePath, enabledFlags[i]);
			aliList.add(m);
		}

		funcList = new JList<MenuItem>(aliList.toArray(new MenuItem[0]));

		funcList.setCellRenderer(new MyCustomListItem());

		funcList.setFixedCellHeight(40);

		funcList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				CardLayout cl = (CardLayout) (cards.getLayout());
//				
//				System.out.println(funcList.getSelectedIndex());
//				System.out.println(funcItems[FUNC_ALL_MEM]);

				if (funcList.getSelectedIndex() == FUNC_ALL_MEM) {
					AllMemberIdsWindow.INSTANCE.setData();

				} else if (funcList.getSelectedIndex() == FUNC_ALL_BOOK) {

					AllBookIdsWindow.INSTANCE.setData();
				}

				if (enabledFlags[funcList.getSelectedIndex()]) {
					cl.show(cards, funcItems[funcList.getSelectedIndex()]);
				}
			}
		});

//		funcList.setSelectedIndex(FUNC_HOME);
		
		JPanel panelHome = new JPanel();
		panelHome.setBackground(ColorPalatte.BACKGROUND);

		cards = new JPanel(new CardLayout());
		cards.add(panelHome, funcItems[FUNC_HOME]);
		cards.add(AddNewMemberPanel.getNewMemberPanel(this), funcItems[FUNC_CREATE_NEW_MEM]);

		cards.add(AddNewBookCopyPanel.INSTANCE.getMainPanel(this), funcItems[FUNC_ADD_NEW_COPY]);
		cards.add(SearchMemberPanel.INSTANCE.getMainPanel(this), funcItems[FUNC_SEARCH_MEM]);
		cards.add(SearchBookPanel.getNewSearchBookPanel(this), funcItems[FUNC_SEARCH_BOOK]);
		cards.add(CheckoutPanel.INSTANCE.getMainPanel(this), funcItems[FUNC_CHECKOUT]);

		cards.add(AddNewBookPanel.getNewBookPanel(this), funcItems[FUNC_ADD_NEW_BOOK]);

		cards.add(AllMemberIdsWindow.INSTANCE.getMainPanel(), funcItems[FUNC_ALL_MEM]);
		cards.add(AllBookIdsWindow.INSTANCE.getMainPanel(), funcItems[FUNC_ALL_BOOK]);
		cards.setBackground(ColorPalatte.BACKGROUND);

//		funcList = new JList<String>(dipeshList);

		// [left] create List of functionalities

		// [right] create panels
		createPanels();
		// [middle] create split
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, (Component) funcList, cards);
		splitPane.setDividerLocation(380);
		add(splitPane, BorderLayout.CENTER);

		setSize(1500, 800);

		isInitialized = true;

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				LibrarySystem.hideAllWindows();
				LoginWindow.INSTANCE.reset();
				LibrarySystem.INSTANCE.setVisible(true);
			}

		});

	}

	private void createPanels() {
		// -----top Panel
		JPanel topPanel = new JPanel();
		topPanel.setBackground(ColorPalatte.BACKGROUND);
		topPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));

		lblWecome = new JLabel("Welcome " + username);
		lblWecome.setFont(new Font("Roboto Slab", Font.BOLD, 15));
		topPanel.add(lblWecome);

		btnLogout = new JButton("Logout");
		addLogoutButtonListener(btnLogout);
		topPanel.add(btnLogout);
		add(topPanel, BorderLayout.NORTH);

		// ----middle Panel

		// ------------------------------------
		// add more functional Panel
		// -----------------------------------

		// ------------------------
		// getContentPane().add(mainPanel);
//
//		cards = new JPanel(new CardLayout());
//		cards.add(new JPanel(), funcItems[FUNC_HOME]);
//		cards.add(AddNewMemberPanel.getNewMemberPanel(this), funcItems[FUNC_CREATE_NEW_MEM]);
//
//		cards.add(AddNewBookCopyPanel.INSTANCE.getMainPanel(this), funcItems[FUNC_ADD_NEW_COPY]);
//		cards.add(SearchMemberPanel.INSTANCE.getMainPanel(this), funcItems[FUNC_SEARCH_MEM]);
//		cards.add(SearchBookPanel.getNewSearchBookPanel(this), funcItems[FUNC_SEARCH_BOOK]);
//		cards.add(CheckoutPanel.INSTANCE.getMainPanel(this), funcItems[FUNC_CHECKOUT]);
//
//		cards.add(AddNewBookPanel.getNewBookPanel(this), funcItems[FUNC_ADD_NEW_BOOK]);
//
//		cards.add(AllMemberIdsWindow.INSTANCE.getMainPanel(), funcItems[FUNC_ALL_MEM]);
//		cards.add(AllBookIdsWindow.INSTANCE.getMainPanel(), funcItems[FUNC_ALL_BOOK]);
//
//		// connect JList elements to CardLayout panels
//		funcList.addListSelectionListener(event -> {
//			String value = funcList.getSelectedValue().toString();
//
//			if (value.compareTo(funcItems[FUNC_ALL_MEM]) == 0)
//				AllMemberIdsWindow.INSTANCE.setData();
//			else if (value.compareTo(funcItems[FUNC_ALL_BOOK]) == 0)
//				AllBookIdsWindow.INSTANCE.setData();
//
//			funcList.setSelectionBackground(ThemeColor.primaryColor);
//			funcList.setSelectionForeground(ThemeColor.white);
//
//			CardLayout cl = (CardLayout) (cards.getLayout());
//			cl.show(cards, value);
//		});
	}

	private void addLogoutButtonListener(JButton butn) {
		butn.addActionListener(evt -> {
			LibrarySystem.hideAllWindows();
			LoginWindow.INSTANCE.reset();
			LibrarySystem.INSTANCE.setVisible(true);

		});
	}

	// --------------------------------------------------------
	/*
	 * private class DisabledItemListCellRenderer extends DefaultListCellRenderer {
	 * 
	 * private static final long serialVersionUID = 1L;
	 * 
	 * @Override public Component getListCellRendererComponent(JList list, Object
	 * value, int index, boolean isSelected, boolean cellHasFocus) { Component comp
	 * = super.getListCellRendererComponent(list, value, index, false, false);
	 * //JComponent jc = (JComponent) comp; if (enabledFlags[index]) { if
	 * (isSelected & cellHasFocus) { comp.setForeground(Color.black);
	 * comp.setBackground(Color.red); } else { comp.setForeground(Color.blue); } if
	 * (!isSelected) { if ((value.toString()).trim().equals("yellow")) {
	 * comp.setForeground(Color.orange); comp.setBackground(Color.magenta); } }
	 * return comp; } comp.setEnabled(false); return comp; } }
	 */

	private class DisabledItemListCellRenderer extends DefaultListCellRenderer {

		private static final long serialVersionUID = 1L;

		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {

			if (enabledFlags[index]) {
				return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			}

			Component comp = super.getListCellRendererComponent(list, value, index, false, false);
			comp.setEnabled(false);
			return comp;
		}
	}

	private class DisabledItemSelectionModel extends DefaultListSelectionModel {

		private static final long serialVersionUID = 1L;

		@Override
		public void setSelectionInterval(int index0, int index1) {
			if (enabledFlags[index0]) {
				super.setSelectionInterval(index0, index0);
			} else {
				/*
				 * The previously selected index is before this one, so walk forward to find the
				 * next selectable item.
				 */
				if (getAnchorSelectionIndex() < index0) {
					for (int i = index0; i < enabledFlags.length; i++) {
						if (enabledFlags[i]) {
							super.setSelectionInterval(i, i);
							return;
						}
					}
				} /*
					 * Otherwise, walk backward to find the next selectable item.
					 */ else {
					for (int i = index0; i >= 0; i--) {
						if (enabledFlags[i]) {
							super.setSelectionInterval(i, i);
							return;
						}
					}
				}
			}
		}
	}

}
