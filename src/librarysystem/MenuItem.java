package librarysystem;

public class MenuItem {

	private String menuLabel;
	private String icon;
	private boolean isEnabled;

	public MenuItem(String menuLabel, String icon, boolean isEnabled) {
		super();
		this.menuLabel = menuLabel;
		this.icon = icon;
		this.isEnabled = isEnabled;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getMenuLabel() {
		return menuLabel;
	}

	public void setMenuLabel(String menuLabel) {
		this.menuLabel = menuLabel;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

}
