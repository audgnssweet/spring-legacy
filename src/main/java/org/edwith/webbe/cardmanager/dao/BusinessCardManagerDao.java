/*
 * 버전 1
 * 
 * 작성자 정명훈
 * 
 * 2021-01-20
 * 
 */


package org.edwith.webbe.cardmanager.dao;

import org.edwith.webbe.cardmanager.dto.BusinessCard;
import org.edwith.webbe.cardmanager.exception.PhoneInvalidException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BusinessCardManagerDao {
	
	private static final String url = "jdbc:mysql://localhost:3306/jdbcexamdb?useSSL=false&serverTimezone=Asia/Seoul";
	private static final String user = "jdbcexamuser";
	private static final String password = "jdbcexamuser123";
	
    public List<BusinessCard> searchBusinessCard(String keyword){
    	List<BusinessCard> businessCards = new ArrayList<BusinessCard>();
    	
    	//1. 드라이버 연결 설정.
    	setDatabaseDriver();
    	
    	//2. DB 연결 설정 및 쿼리 실행
    	String sql = "SELECT * FROM businesscard WHERE name LIKE ?;";
    	try(Connection conn = DriverManager.getConnection(url, user, password);
    			PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, "%" + keyword + "%");
    		try(ResultSet rs = ps.executeQuery()){
    			while(rs.next()) {
    				String name = rs.getString("name");
    				String phone = rs.getString("phone");
    				String companyName = rs.getString("company_name");
    				java.util.Date createDate = rs.getTimestamp("create_date");
    				BusinessCard businessCard = new BusinessCard(name, phone, companyName);
    				businessCard.setCreateDate(createDate);
    				businessCards.add(businessCard);
    			}
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return businessCards;
    }

    public BusinessCard addBusinessCard(BusinessCard businessCard){
    	
    	//입력 양식 맞는지 먼저 확인
    	validateInput(businessCard);
    	
    	//1. 드라이버 연결 설정
    	setDatabaseDriver();
    	
    	//2. DB 연결 설정 및 쿼리실행
    	String sql = "INSERT INTO businesscard (name,phone,company_name,create_date) "
    			+ "VALUES(?,?,?,?);";
    	try(Connection conn = DriverManager.getConnection(url, user, password);
    			PreparedStatement ps = conn.prepareStatement(sql)){
    		ps.setString(1, businessCard.getName());
    		ps.setString(2, businessCard.getPhone());
    		ps.setString(3, businessCard.getCompanyName());
    		ps.setTimestamp(4, new java.sql.Timestamp(businessCard.getCreateDate().getTime()));
    		ps.executeUpdate();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return businessCard;
    }
    
    private void setDatabaseDriver() {
    	//jdbc 드라이버 로드
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    //휴대폰 번호 양식확인
    private boolean isValidPhone(String phone) {
    	int count = phone.length() - phone.replaceAll("-","").length();
    	return count == 2 ? true : false;
    }
    
    //여러 입력값의 오류 핸들러.
    private void validateInput(BusinessCard businessCard) {
    	if(!isValidPhone(businessCard.getPhone())) {
    		throw new PhoneInvalidException("휴대폰 번호의 양식에 맞지 않습니다.");
    	}
    }
}
