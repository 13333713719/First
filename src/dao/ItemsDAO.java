package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.DBHelper;

import entity.Items;

public class ItemsDAO {
	public ArrayList<Items> getAllItems(){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<Items> list=new ArrayList();
		try{
			conn=DBHelper.getConnection();
			String sql="select*from items;";
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Items item=new Items();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setCity(rs.getString("city"));
				item.setNumber(rs.getInt("number"));
				item.setPrice(rs.getInt("price"));
				item.setPicture(rs.getString("picture"));
				list.add(item);
			}
		}catch(Exception e){
			e.getStackTrace();
		}finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				rs=null;
			}
			if(ps!=null){
				try {
					ps.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ps=null;
			}
		}
		return list;
		
	}
	public Items getItemsById(int id){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			conn=DBHelper.getConnection();
			String sql="select*from items where id=?";
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			ps.setInt(1, id);
			if(rs.next()){
				Items item=new Items();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setCity(rs.getString("city"));
				item.setNumber(rs.getInt("number"));
				item.setPrice(rs.getInt("price"));
				item.setPicture(rs.getString("picture"));
				return item;
			}
			else{
				return null;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			if(ps!=null){
				try {
					ps.close();
					ps=null;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			if(rs!=null){
				try {
					rs.close();
					rs=null;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
			}
		}
	}
	public ArrayList<Items> getFirstList(String list){
		ArrayList<Items> listItem=new ArrayList<Items>();
		int iCount=5;
		if(list!=null&&list.length()>0){
			String[] arr=list.split(",");
			if(arr.length<5){
				for(int i=arr.length-1;i>=arr.length-iCount;i--){
					listItem.add(getItemsById(Integer.parseInt(arr[i])));
				}
			}
			else{
				for(int i=arr.length-1;i>=0;i--){
					listItem.add(getItemsById(Integer.parseInt(arr[i])));
				}
			}
			
			return listItem;
		}
		else return null;
		
	}
}
