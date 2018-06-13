package pri.fankehu.BackStage_management;

import java.sql.Connection;

class BackManage {
	/**
	 * 后台管理类
	 * 主要负责归属地区，结算方式，客户类型等的添加修改
	 */
	private Connection con;//连接

	
	/**
	 * 获取客户类型方法
	 * 返回查询得到的对象
	 * @return
	 */
	public void Check_Type() {
		//查询客户类型
	}
	public void Check_Place() {
		//查询归属地
	}
	public void Check_Mon() {
		//查询结算方式
	}
	/**
	 * 更新操作
	 * @return
	 */
	public void Update_Type() {
		
	}
	public void Update_Place() {
		
	}
	public void Update_Mon() {
		
	}
	/**
	 * 删除操作
	 * @return
	 */
	public void Delete_Type() {
		
	}
	public void Delete_Place() {
		
	}
	public void Delete_Mon() {
		
	}
	
	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}
}
