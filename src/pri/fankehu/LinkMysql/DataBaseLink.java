package pri.fankehu.LinkMysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseLink {
	private String host;
	private String user;
	private String password;
	private String database;
	private int port;
	private Connection con;//连接
	
	/**
	 * 设置默认连接信息
	 * host:localhost
	 * user:root
	 * password:root
	 * port:3306
	 * database:***
	 * @return
	 */
	public void SettingInfo() {
		//setHost("192.168.1.101");
		//setHost("192.168.182.1");
		//setHost("192.168.73.1");
		setHost("localhost");
		setUser("fankehu");
		setPassword("123456");
		setPort(3306);
		setDatabase("客户订购管理系统");//测试用
	}
	
	/**
	 * 连接数据库
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public void LinkDatabase() throws ClassNotFoundException, SQLException {
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://"+getHost()+":"
				+getPort()+"/"+getDatabase();
		Class.forName(driver);
		setCon(DriverManager.getConnection(url, getUser(), getPassword()));
	}
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDatabase() {
		return database;
	}
	public void setDatabase(String database) {
		this.database = database;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public Connection getCon() {
		return con;
	}
	public void setCon(Connection con) {
		this.con = con;
	}
}
