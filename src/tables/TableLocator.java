package tables;

public class TableLocator {
	private static ActivityTable activityTable;
	private static FriendTable friendTable;
	private static LocationTable locationTable;
	private static NotificationTable notificationTable;
	private static UserTable userTable;
	
	private TableLocator() {}
	
	public static ActivityTable getActivityTable() {
		if (activityTable == null) {
			activityTable = new ActivityTable();
		}
		
		return activityTable;
	}
	
	public static FriendTable getFriendTable() {
		if (friendTable == null) {
			friendTable = new FriendTable();
		}
		
		return friendTable;
	}
	
	public static LocationTable getLocationTable() {
		if (locationTable == null) {
			locationTable = new LocationTable();
		}
		
		return locationTable;
	}
	
	public static NotificationTable getNotificationTable() {
		if (notificationTable == null) {
			notificationTable = new NotificationTable();
		}
		
		return notificationTable;
	}
	
	public static UserTable getUserTable() {
		if (userTable == null) {
			userTable = new UserTable();
		}
		
		return userTable;
	}
}
