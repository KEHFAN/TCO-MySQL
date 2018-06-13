package pri.fankehu.invoice_management;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibm.icu.util.Calendar;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class Add_invoice {
	private Connection con;
	
	
	public List<String []> check_order() throws SQLException{
		String sql="select no from orde order by no";
		Statement statement=(Statement) getCon().createStatement();
		ResultSet rs=statement.executeQuery(sql);
		List<String[]> list=new ArrayList<String[]>();
		while(rs.next()) {
			String [] ty=new String[1];
			ty[0]=rs.getString("no");
			
//			System.out.println(ty[0]+" "+ty[1]);
			list.add(ty);
		}
		return list;
	}
	public int check_order_n() throws SQLException {
		String sql="select count(no) from orde";
		Statement statement=(Statement) con.createStatement();
		ResultSet rs=statement.executeQuery(sql);
		int i=0;
		while(rs.next()) {
			i=rs.getInt("count(no)");
		}
		return i;
	}
	public String[] check_info(String id) throws SQLException {
		String [] info=new String[8];
		//编号
		info[0]=id;
		
		String sql="select order_view.cno,C.name,order_view.money from order_view "
				+ "join C on C.No=order_view.cno where order_view.no='"+id+"' "
				+ " order by order_view.no";
		Statement statement=(Statement) getCon().createStatement();
		ResultSet rs=statement.executeQuery(sql);
		while(rs.next()) {
//			String [] ty=new String[1];
			//客户编号与客户名
			info[1]=rs.getString("order_view.cno");
			info[2]=rs.getString("C.name");
			info[4]=rs.getString("order_view.money");//发票金额
//			System.out.println(ty[0]+" "+ty[1]);
//			list.add(ty);
		}
		//计算发票编号
		String sqql="select max(no),count(no) from invoice;";
		statement=(Statement) con.createStatement();
		rs=statement.executeQuery(sqql);
		while(rs.next()) {
//			id=Integer.valueOf(rs.getString("count(no)"))>0?String.valueOf((Integer.valueOf(rs.getString("max(no)"))+1)):("1000000001");
			info[3]=Integer.valueOf(rs.getString("count(no)"))>0?String.valueOf((Integer.valueOf(rs.getString("max(no)"))+1)):("2018000101");	
		}
		//计算发票税额
		info[5]=String.valueOf(Double.valueOf(info[4])*0.09);
		//价税合计
		info[6]=String.valueOf(Double.valueOf(info[5])+Double.valueOf(info[4]));
		//计算开票日期
		//计算当前日期
		Calendar now=Calendar.getInstance();
		String now_date;
		now_date=String.valueOf(now.get(Calendar.YEAR))+"/"+String.valueOf(now.get(Calendar.MONTH))+"/"+String.valueOf(now.get(Calendar.DAY_OF_MONTH));
		info[7]=now_date;
		return info;
	}
	//插入发票
	public void add_invoice(String [] info) throws SQLException {
//		String [] info=new String[10];
		for(String string : info) {
			System.out.println(string);
		}
		String sql="insert into invoice (no,ino,C_No,orno,price,tax,"
				+ "ptax,date,taxerNo"
				+ ") values ("
				+ "'"+info[0]+"',"
				+ "'"+info[1]+"',"
				+ "'"+info[2]+"',"
				+ "'"+info[3]+"',"
				+ ""+Double.valueOf(info[4])+","
				+ ""+Double.valueOf(info[5])+","
				+ ""+Double.valueOf(info[6])+","
				+ "'"+info[7]+"',"
				+ "'"+info[8]+"'"
				
				+ ");";
		//查询时自动计算价格
		System.out.println(sql);
		PreparedStatement preparedStatement=(PreparedStatement) getCon().prepareStatement(sql);
		preparedStatement.executeUpdate();
		
	}
	//修改发票
	public void update_invoice(String [] updateinfo) throws SQLException {
		String sql="update invoice set ino='"+updateinfo[1]+"',"
				+ "taxerNo='"+updateinfo[2]+"' "
						+ "where no='"+updateinfo[0]+"';";
		System.out.println(sql);
		PreparedStatement preparedStatement=(PreparedStatement) getCon().prepareStatement(sql);
		preparedStatement.executeUpdate();
		
		/**
		 * 可以这样写
		 * String sql2 = "update person set name=? where number=?";  
            PreparedStatement pst = conn.prepareStatement(sql2);  
            pst.setString(1,"1110520218");  
            pst.setInt(2,198);  
            pst.executeUpdate();  
		 */
		
	}
	//删除发票
	public void delete_invoice(String id) throws SQLException {
		String sql="delete from invoice where no='"+id+"';";
		PreparedStatement preparedStatement=(PreparedStatement) getCon().prepareStatement(sql);
		preparedStatement.executeUpdate();
		
	}
	
	public Connection getCon() {
		return con;
	}
	public void setCon(Connection con) {
		this.con = con;
	}
}
