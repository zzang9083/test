package com.naver.boimpl;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naver.bo.translateBO;
import com.naver.dao.translateDAO;


@Service
public class translateBOImpl implements translateBO {

	@Autowired
	private translateDAO translateDAO;
	
	public int AddTranslate(long ID,String KO, String EN)
	{
		return translateDAO.AddTranslate(ID, KO, EN);
	}
	
	public int ConfirmTranslate(long ID,String LANG)
	{
		return translateDAO.ConfirmTranslate(ID,LANG);
	}

	
	public HashMap sendProblem(long ID)
	{
		return translateDAO.sendProblem(ID);		
	}
	
	public int confirmId(long ID)
	{
		return translateDAO.confirmId(ID);		
	}
	
	public int updateFlag(long ID,int FLAG)
	{
		return translateDAO.updateFlag(ID,FLAG);				
	}
	
	public int updateLang(long ID,String LANG)
	{
		return translateDAO.updateLang(ID,LANG);				
			
	}
	
	public HashMap selectIdInfo(long ID)
	{
		return translateDAO.selectIdInfo(ID);				
	}
	
	public int updateIdInfo(long ID,String KO, String EN)
	{
		return translateDAO.updateIdInfo(ID,KO,EN);						
	}

}
