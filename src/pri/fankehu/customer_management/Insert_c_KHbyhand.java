package pri.fankehu.customer_management;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ibm.icu.util.Calendar;
import com.mysql.jdbc.PreparedStatement;

public class Insert_c_KHbyhand {
	private Connection con;//连接
	
	private Statement statement;
	private ResultSet rs;
	
	/**
	 * 查询客户类型，将list<string[]>传入UI
	 * @return
	 * @throws SQLException 
	 */
	public List<String[]> check_type() throws SQLException{
		String sql="select No,type from type";
		statement=con.createStatement();
		rs=statement.executeQuery(sql);
		
		List<String[]> list=new ArrayList<String[]>(); 
		while(rs.next()) {
			String [] ty=new String[2];
			ty[0]=rs.getString("No");
			ty[1]=rs.getString("type");
			list.add(ty);	
		}
		return list;
	}
	public int check_type_n() throws SQLException {
		String sql="select count(No) from type";
		statement=con.createStatement();
		rs=statement.executeQuery(sql);
		int i=0;
		while(rs.next()) {
			i=rs.getInt("count(No)");
		}
		return i;
	}
	/**
	 * 查询客户地区，返回list给UI
	 * @return
	 * @throws SQLException 
	 */
	public List<String[]> check_place() throws SQLException{
		String sql="select No,place from place";
		statement=con.createStatement();
		rs=statement.executeQuery(sql);
		
		List<String[]> list=new ArrayList<String[]>(); 
		while(rs.next()) {
			String [] ty=new String[2];
			ty[0]=rs.getString("No");
			ty[1]=rs.getString("place");
			list.add(ty);	
		}
		return list;
	}
	public int check_place_n() throws SQLException {
		String sql="select count(No) from place";
		statement=con.createStatement();
		rs=statement.executeQuery(sql);
		int i=0;
		while(rs.next()) {
			i=rs.getInt("count(No)");
		}
		return i;
	}
	/**
	 * 查询客户，返回list
	 * @throws SQLException 
	 */
	public List<String[]> check_c() throws SQLException{
		String sql="select No,name,type,place,co_name,y_name,tel,mail,"
				+ "in_date,al_date,addres,"
				+ "note from C_view";
		statement=con.createStatement();
		rs=statement.executeQuery(sql);
		List<String[]> list=new ArrayList<String[]>();
		while(rs.next()) {
			String [] cStrings=new String[12];
			cStrings[0]=rs.getString("No");
			cStrings[1]=rs.getString("name");
			cStrings[2]=rs.getString("type");
			cStrings[3]=rs.getString("place");
			cStrings[4]=rs.getString("co_name");
			cStrings[5]=rs.getString("y_name");
			cStrings[6]=rs.getString("tel");
			cStrings[7]=rs.getString("mail");
			cStrings[8]=rs.getString("in_date");
			cStrings[9]=rs.getString("al_date");
			cStrings[10]=rs.getString("addres");
			cStrings[11]=rs.getString("note");
			list.add(cStrings);
		}
		return list;
	}
	public int check_c_n() throws SQLException {
		String sql="select count(No) from C_view";
		statement=con.createStatement();
		rs=statement.executeQuery(sql);
		int i=0;
		while(rs.next()) {
			i=rs.getInt("count(No)");
		}
		return i;
	}
	
	/**
	 * 插入客户，获取list
	 * @return
	 * @throws SQLException 
	 */
	public void insert(String[] message) throws SQLException {
		//计算客户编号
		String sqql="select max(No),count(No) from C;";
		statement=con.createStatement();
		rs=statement.executeQuery(sqql);
		String id="1000000001";
		while(rs.next()) {
			id=Integer.valueOf(rs.getString("count(No)"))>0?String.valueOf((Integer.valueOf(rs.getString("max(No)"))+1)):("10000000001");
		}
		//计算类型编号
		int c_ty=0;
		sqql="select No from type where type='"+message[1]+"';";//未完
		statement=con.createStatement();
		rs=statement.executeQuery(sqql);
		while(rs.next()) {
			c_ty=rs.getInt("No");
		}
		//计算地区编号
		int c_pl=0;
		sqql="select No from place where place='"+message[2]+"';";
		statement=con.createStatement();
		rs=statement.executeQuery(sqql);
		while(rs.next()) {
			c_pl=rs.getInt("No");
		}
		//计算当前日期
		Calendar now=Calendar.getInstance();
		String now_date;
		now_date=String.valueOf(now.get(Calendar.YEAR))+"/"+String.valueOf(now.get(Calendar.MONTH))+"/"+String.valueOf(now.get(Calendar.DAY_OF_MONTH));
		
		System.out.println(id+" "+c_ty+" "+c_pl+" "+message[3]);
//		System.out.println(message[7]);
		String sql="insert into C (No,name,Typ_No,Pla_No,co_name,y_name,tel,mail,addres,note,in_date,al_date"
				+ ") values ("
				+ "'"+id+"',"
				+ "'"+message[0]+"',"
				+ c_ty+","
				+ c_pl+","
				+ "'"+message[3]+"',"
				+ "'"+message[4]+"',"
				+ "'"+message[5]+"',"
				+ "'"+message[6]+"',"
				+ "'"+message[7]+"',"
				+ "'"+message[8]+"',"
				+ "'"+now_date+"',"
				+ "'"+now_date+"'"
				+ ");";
		System.out.println(sql);
		PreparedStatement preparedStatement=(PreparedStatement) getCon().prepareStatement(sql);
		preparedStatement.executeUpdate();
	}
	
	/**
	 * 删除客户信息
	 * @return
	 * @throws SQLException 
	 */
	public void delete(String No) throws SQLException {
		String sql="delete from C where No='"
				+ No+"';";
		statement=con.createStatement();
		statement.executeUpdate(sql);
	}
	
	public Connection getCon() {
		return con;
	}
	public void setCon(Connection con) {
		this.con = con;
	}
	public Statement getStatement() {
		return statement;
	}
	public void setStatement(Statement statement) {
		this.statement = statement;
	}
	public ResultSet getRs() {
		return rs;
	}
	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
}
