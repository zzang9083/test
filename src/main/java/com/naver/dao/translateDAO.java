package com.naver.dao;

import java.util.ArrayList;
import java.util.HashMap;

public interface translateDAO {

	public int AddTranslate(long ID,String KO, String EN);
	public int ConfirmTranslate(long ID,String Lang);
	public HashMap sendProblem(long ID);
	public int confirmId(long ID);
	public int updateFlag(long ID,int FLAG);
	public int updateLang(long ID,String LANG);
	public HashMap selectIdInfo(long ID);
	public int updateIdInfo(long ID,String KO, String EN);
	
	
}
