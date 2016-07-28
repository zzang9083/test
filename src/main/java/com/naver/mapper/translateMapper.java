package com.naver.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import org.apache.ibatis.annotations.Param;


public interface translateMapper {
	
	int AddTranslate(@Param("id")long ID,@Param("ko")String KO, @Param("en")String EN);
	int ConfirmTranslate(@Param("id")long ID,@Param("lang")String LANG); 
	HashMap sendProblem(@Param("id")long ID);
	int confirmId(@Param("id")long ID);
	int updateFlag(@Param("id")long ID,@Param("flag")int FLAG);
	int updateLang(@Param("id")long ID,@Param("lang")String LANG);
	HashMap selectIdInfo(@Param("id")long ID);
	int updateIdInfo(@Param("id")long ID,@Param("ko")String KO,@Param("en")String EN);
	
}
