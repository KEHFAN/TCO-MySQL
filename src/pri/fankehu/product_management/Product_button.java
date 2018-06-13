package pri.fankehu.product_management;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class Product_button {
	/**
	 * 将商品按钮相关增删改查全包含
	 */
	private Connection con;//连接
	
	
	
	/**
	 * 商品分类
	 * 增删查
	 * @throws SQLException 
	 */
	public void insert_type(String insertMessage) throws SQLException {
		String sqql="select max(no),count(no) from P_type;";
		Statement statement=(Statement) con.createStatement();
		ResultSet rs=statement.executeQuery(sqql);
		int i=0;
		while(rs.next()) {
//			System.out.println(Integer.valueOf(rs.getString("max(No)"))+1);
			i=Integer.valueOf(rs.getString("count(no)"))>0?(Integer.valueOf(rs.getString("max(no)"))+1):(1);
//			i=Integer.valueOf(rs.getString("max(No)"))+1;
		}	
		String sql="insert into P_type (no,name) values ("+i+",'"+insertMessage+"');";
		System.out.println(sql);
		PreparedStatement preparedStatement=(PreparedStatement) getCon().prepareStatement(sql);
		
		preparedStatement.executeUpdate();
	}
	public void delete_type(int no) throws SQLException {
		String sql="delete from P_type where no="+no;
		Statement statement=(Statement) con.createStatement();
		statement.executeUpdate(sql);
	}
	public List<String []> check_type() throws SQLException {
		String sql="select no,name from P_type order by no";
		Statement statement=(Statement) getCon().createStatement();
		ResultSet rs=statement.executeQuery(sql);
		List<String[]> list=new ArrayList<String[]>();
		while(rs.next()) {
			String [] ty=new String[2];
			ty[0]=rs.getString("no");
			ty[1]=rs.getString("name");
//			System.out.println(ty[0]+" "+ty[1]);
			list.add(ty);
		}
		return list;
	}
	public int check_type_n() throws SQLException {
		String sql="select count(no) from P_type";
		Statement statement=(Statement) con.createStatement();
		ResultSet rs=statement.executeQuery(sql);
		int i=0;
		while(rs.next()) {
			i=rs.getInt("count(no)");
		}
		return i;
	}
	/**
	 * 单位
	 * 增删查
	 * @throws SQLException 
	 */
	public void insert_unit(String insertMessage) throws SQLException {
		String sqql="select max(no),count(no) from P_u;";
		Statement statement=(Statement) con.createStatement();
		ResultSet rs=statement.executeQuery(sqql);
		int i=0;
		while(rs.next()) {
//			System.out.println(Integer.valueOf(rs.getString("max(No)"))+1);
			i=Integer.valueOf(rs.getString("count(no)"))>0?(Integer.valueOf(rs.getString("max(no)"))+1):(1);
//			i=Integer.valueOf(rs.getString("max(No)"))+1;
		}	
		String sql="insert into P_u (no,unit) values ("+i+",'"+insertMessage+"');";
		System.out.println(sql);
		PreparedStatement preparedStatement=(PreparedStatement) getCon().prepareStatement(sql);
		
		preparedStatement.executeUpdate();
	}
	public void delete_unit(int no) throws SQLException {
		String sql="delete from P_u where no="+no;
		Statement statement=(Statement) con.createStatement();
		statement.executeUpdate(sql);
	}
	public List<String []> check_unit() throws SQLException {
		String sql="select no,unit from P_u order by no";
		Statement statement=(Statement) getCon().createStatement();
		ResultSet rs=statement.executeQuery(sql);
		List<String[]> list=new ArrayList<String[]>();
		while(rs.next()) {
			String [] ty=new String[2];
			ty[0]=rs.getString("no");
			ty[1]=rs.getString("unit");
//			System.out.println(ty[0]+" "+ty[1]);
			list.add(ty);
		}
		return list;
	}
	public int check_unit_n() throws SQLException {
		String sql="select count(no) from P_u";
		Statement statement=(Statement) con.createStatement();
		ResultSet rs=statement.executeQuery(sql);
		int i=0;
		while(rs.next()) {
			i=rs.getInt("count(no)");
		}
		return i;
	}
	/**
	 * 供应商
	 * 增删改查
	 * @throws SQLException 
	 */
	public void insert_p(String[] cStrings) throws SQLException {
		String sqql="select max(no),count(no) from P_p;";
		Statement statement=(Statement) con.createStatement();
		ResultSet rs=statement.executeQuery(sqql);
		String i=null;
		while(rs.next()) {
//			System.out.println(Integer.valueOf(rs.getString("max(No)"))+1);
			i=Integer.valueOf(rs.getString("count(no)"))>0?String.valueOf((Integer.valueOf(rs.getString("max(no)"))+1)):("10001");
//			i=Integer.valueOf(rs.getString("max(No)"))+1;
		}	
		String sql="insert into P_p (no,name,co_name,phone,address) values ("
				+ "'"+i+"',"					//编号
				+ "'"+cStrings[0]+"',"		//姓名
				+ "'"+cStrings[1]+"',"		//联系人
				+ "'"+cStrings[2]+"',"		//电话
				+ "'"+cStrings[3]+"'"		//地址
				+ ");";
		System.out.println(sql);
		PreparedStatement preparedStatement=(PreparedStatement) getCon().prepareStatement(sql);
		
		preparedStatement.executeUpdate();
	}
	public void delete_p(String no) throws SQLException {
		String sql="delete from P_p where no='"+no+"';";
		Statement statement=(Statement) con.createStatement();
		statement.executeUpdate(sql);
	}
	public void update_p() {
		
	}
	public List<String[]> check_p() throws SQLException {
		String sql="select no,name,co_name,phone,address from P_p order by no";
		Statement statement=(Statement) getCon().createStatement();
		ResultSet rs=statement.executeQuery(sql);
		List<String[]> list=new ArrayList<String[]>();
		while(rs.next()) {
			String [] ty=new String[5];
			ty[0]=rs.getString("no");
			ty[1]=rs.getString("name");
			ty[2]=rs.getString("co_name");
			ty[3]=rs.getString("phone");
			ty[4]=rs.getString("address");
//			System.out.println(ty[0]+" "+ty[1]);
			list.add(ty);
		}
		return list;
	}
	public int check_p_n() throws SQLException {
		String sql="select count(no) from P_p";
		Statement statement=(Statement) con.createStatement();
		ResultSet rs=statement.executeQuery(sql);
		int i=0;
		while(rs.next()) {
			i=rs.getInt("count(no)");
		}
		return i;
	}
	/**
	 * 货物信息
	 * 增删改查
	 * @throws SQLException 
	 */
	public void insert_product(String[] cStrings) throws SQLException {
		/**
		 * 类比客户增加
		 * 收到UI数据时
		 * 查询分类、单位、仓库、供应商的编码
		 */
		for(String attare : cStrings) {
			System.out.println(attare);
		}
		//计算货物编号
		String sqql="select max(no),count(no) from P_info;";
		Statement statement=(Statement) con.createStatement();
		ResultSet rs=statement.executeQuery(sqql);
		String id="10001";
		while(rs.next()) {
			id=Integer.valueOf(rs.getString("count(no)"))>0?String.valueOf((Integer.valueOf(rs.getString("max(no)"))+1)):("10001");
		}
		
		//计算分类编号
		int c_ty=0;
		sqql="select no from P_type where name='"+cStrings[1]+"';";
		statement=(Statement) con.createStatement();
		rs=statement.executeQuery(sqql);
		while(rs.next()) {
			c_ty=rs.getInt("no");
		}
		
		//计算单位编号
		int c_unit=0;
		sqql="select no from P_u where unit='"+cStrings[2]+"';";
		statement=(Statement) con.createStatement();
		rs=statement.executeQuery(sqql);
		while(rs.next()) {
			c_unit=rs.getInt("no");
		}
		
		//计算仓库编号
		int c_d=0;
		sqql="select no from P_d where address='"+cStrings[3]+"';";
		statement=(Statement) con.createStatement();
		rs=statement.executeQuery(sqql);
		while(rs.next()) {
			c_d=rs.getInt("no");
		}
		//计算供应商编号
		String c_p=null;
		sqql="select no from P_p where name='"+cStrings[4]+"';";
		statement=(Statement) con.createStatement();
		rs=statement.executeQuery(sqql);
		while(rs.next()) {
			c_p=rs.getString("no");
		}
		
		String sql="insert into P_info (no,name,P_type_no,P_u_no,P_d_no,P_p_no,e_price,o_price"
				+ ") values ("
				+ "'"+id+"',"
				+ "'"+cStrings[0]+"',"
				+ c_ty+","
				+ c_unit+","
				+ c_d+","
				+ "'"+c_p+"',"
				+ ""+Double.valueOf(cStrings[5])+","
				+ ""+Double.valueOf(cStrings[6])+""
				+ ");";
				System.out.println(sql);
				PreparedStatement preparedStatement=(PreparedStatement) getCon().prepareStatement(sql);
				preparedStatement.executeUpdate();
	}
	public void delete_product(String id) throws SQLException {
		//右键菜单
		String sql="delete from P_info where no='"+id+"';";
		System.out.println(sql);
		PreparedStatement preparedStatement=(PreparedStatement) getCon().prepareStatement(sql);
		preparedStatement.executeUpdate();

	}
	public void update_product(String[] info) throws SQLException {
		//右键菜单
		String sql="update P_info set name='"+info[1]+"',"
				+ "e_price="+Double.valueOf(info[2])+","
						+ "o_price="+Double.valueOf(info[3])+" "
								+ "where no='"+info[0]+"';";
		PreparedStatement preparedStatement=(PreparedStatement) getCon().prepareStatement(sql);
		preparedStatement.executeUpdate();

	}
	//视图中查询
	public List<String[]> check_product() throws SQLException {
		String sql="select no,name,type,unit,cang,pname,e_price,o_price from P_p_view order by no";
		Statement statement=(Statement) getCon().createStatement();
		ResultSet rs=statement.executeQuery(sql);
		List<String[]> list=new ArrayList<String[]>();
		while(rs.next()) {
			String [] ty=new String[8];
			ty[0]=rs.getString("no");
			ty[1]=rs.getString("name");
			ty[2]=rs.getString("type");
			ty[3]=rs.getString("unit");
			ty[4]=rs.getString("cang");
			ty[5]=rs.getString("pname");
			ty[6]=rs.getString("e_price");
			ty[7]=rs.getString("o_price");
//			System.out.println(ty[0]+" "+ty[1]);
			list.add(ty);
		}
		return list;
	}
	
	
	public Connection getCon() {
		return con;
	}
	public void setCon(Connection con) {
		this.con = con;
	}
}
