package com.naver.daoimpl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.naver.dao.translateDAO;
import com.naver.mapper.translateMapper;

@Component
public class translateDAOImpl implements translateDAO {
	@Autowired
 private SqlSession sqlSession;
	
	public int AddTranslate(long ID,String KO, String EN) {
		int result;
		translateMapper translateMapper = sqlSession.getMapper(translateMapper.class);
		result=translateMapper.AddTranslate(ID,KO,EN);
				
		return result;
	}
	
	public int ConfirmTranslate(long ID,String LANG) 
	{
		int result;
		translateMapper translateMapper = sqlSession.getMapper(translateMapper.class);
		result=translateMapper.ConfirmTranslate(ID,LANG);
		
		return result;	
	}

	public HashMap sendProblem(long ID)
	{
		HashMap result;
		translateMapper translateMapper = sqlSession.getMapper(translateMapper.class);
		result=translateMapper.sendProblem(ID);
		return result;	
	}
	
	public int confirmId(long ID)
	{
		int result;
		translateMapper translateMapper = sqlSession.getMapper(translateMapper.class);
		result=translateMapper.confirmId(ID);
		return result;	

	}
	

	public int updateFlag(long ID,int FLAG)
	{
		int result;
		translateMapper translateMapper = sqlSession.getMapper(translateMapper.class);
		result=translateMapper.updateFlag(ID,FLAG);
		return result;	
	}
	
	public int updateLang(long ID,String LANG)
	{
		int result;
		translateMapper translateMapper = sqlSession.getMapper(translateMapper.class);
		result=translateMapper.updateLang(ID,LANG);
		return result;	
		
	}
	
	public HashMap selectIdInfo(long ID)
	{
		HashMap result;
		translateMapper translateMapper = sqlSession.getMapper(translateMapper.class);
		result=translateMapper.selectIdInfo(ID);
		return result;	
	}

	public int updateIdInfo(long ID,String KO, String EN)
	{
		int result;
		translateMapper translateMapper = sqlSession.getMapper(translateMapper.class);
		result=translateMapper.updateIdInfo(ID,KO,EN);
		return result;	
		
	}





}
