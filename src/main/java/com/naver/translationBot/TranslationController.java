package com.naver.translationBot;

import net.sf.json.JSONObject;
import com.naver.bo.translateBO;
import com.naver.dto.translate;
import com.memetix.mst.detect.Detect;
import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * Handles requests for the application home page.
 */
@Controller
public class TranslationController {
	
	// 쿼리를 수행할 service변수
	@Autowired
	private translateBO translateBO;
	
	
	//receivemessage가 callback url
	@RequestMapping(value = "receivemessage", method =  {RequestMethod.GET,RequestMethod.POST},consumes = {"application/json"})
	@ResponseBody
	public String receivemessage(HttpServletRequest request,HttpSession session,@RequestBody translate list) throws Exception {
		
		//callback input
		String inputMessage = list.getContent().toString();
		long writerUserNo = list.getWriterUserNo();
		long channelNo = list.getChannelNo();
		//System.out.println("ch:"+channelNo);
		//sendmessage로 보낼 출력
		String outputMessage = null;
		
	//		translateBO.confirmId(WriterUserNo); //아이디가 db에 들어가 있나 없나 확인하고 없으면 넣

			
		//flag를 0으로 바꾸면서 단어암기를 끝냄
		if (inputMessage.equals("/단어암기끝") || inputMessage.equals("/end memorizing words")
				&& Integer.parseInt(translateBO.selectIdInfo(writerUserNo).get("flag").toString())==2)
		{
			translateBO.updateFlag(writerUserNo,0);
			sendmessage("단어암기끝!");
			return "endproblem";
		}
	    
		//flag 2로 바꾸면서 단어암기시작(문제가 영어로 나옴), lang으로 언어입력구분
		if (inputMessage.equals("/단어암기")  || Integer.parseInt(translateBO.selectIdInfo(writerUserNo).get("flag").toString())==1)
		{
				translateBO.updateLang(writerUserNo, "ko");
				sendproblem(writerUserNo);  //문제출제
				return "sendproblem";
		}
		
		//flag 2로 바꾸면서 단어암기시작(문제가 한글로 나옴), lang으로 언어입력구분
		if(inputMessage.equals("/memorizing words") || 
				Integer.parseInt(translateBO.selectIdInfo(writerUserNo).get("flag").toString())==1)
		{
			translateBO.updateLang(writerUserNo,"en");
			sendproblem(writerUserNo);   //문제출제 sendMessage
			return "sendproblem";
		}
		
		//정답여부 판단, 메시지
		if(Integer.parseInt(translateBO.selectIdInfo(writerUserNo).get("flag").toString())==2)
		{
			//영문답을 원할때
			if(translateBO.selectIdInfo(writerUserNo).get("lang").toString().equals("en")) {
				//나의영문답과 원래 답을 비교
				//정답
				if(inputMessage.equals(translateBO.selectIdInfo(writerUserNo).get("en").toString())) {
						outputMessage = "정답입니다!";
						sendmessage(outputMessage); //정답여부 sendMessage
						sendproblem(writerUserNo); //문제출제 sendMessage
				}
				//오답
				else {
						outputMessage = "틀렸습니다 정답은 "+translateBO.selectIdInfo(writerUserNo).get("en").toString()+"입니다.";
						sendmessage(outputMessage); //정답여부 sendMessage
						sendproblem(writerUserNo);  //
				}
			}
			
			//한글답을 원할때
			else {
				//나의한글답과 원래답을 비교
				//정답
				if(inputMessage.equals(translateBO.selectIdInfo(writerUserNo).get("ko").toString())) {
						outputMessage = "정답입니다!";
						sendmessage(outputMessage);
						sendproblem(writerUserNo);
				}
				//오답
				else {					
						outputMessage = "틀렸습니다 정답은 "+translateBO.selectIdInfo(writerUserNo).get("ko").toString()+"입니다.";
						sendmessage(outputMessage);
						sendproblem(writerUserNo);
				}
			}
			return "sendproblem";
		}
		//단어테스트 아니고 그냥 번역
		else {
			//bot api

		Translate.setClientId("zzang9083");
		Translate.setClientSecret("beOno9V+dEozWa2P2X9OIeHnGOXYEX7apImLGrXKC4o="); 
        
		//language detect
		Language detect = Detect.execute(inputMessage);
		String detectString = detect.toString();
		
		//translate Language
		if (detectString.equals("en")) {   // 판별된 결과가 영어면
		
			outputMessage = Translate.execute(inputMessage, Language.ENGLISH, Language.KOREAN); //번역하여 값저장
			if (translateBO.ConfirmTranslate(writerUserNo, inputMessage) == 0) { //사용자가 해당메시지를 보낸적이 없으면 
			
				translateBO.AddTranslate(writerUserNo, outputMessage, inputMessage); //DB에 저장	
			}
		}

		else if (detectString.equals("ko")) { // 판별된 결과가 한글이면
			outputMessage = Translate.execute(inputMessage, Language.KOREAN, Language.ENGLISH); //번역저장
			if (translateBO.ConfirmTranslate(writerUserNo, inputMessage) == 0){ //사용자가 해당메시지를 보낸적이 없으면 {
			   translateBO.AddTranslate(writerUserNo, inputMessage,
					outputMessage); //DB에 저장	
			}
			 	
		}
		sendmessage(outputMessage); //결과 sendmessage
		return "translateMessage";
	}

}   
	//sendmessage 함수
	public void sendmessage(String outputMessage) throws Exception { {
		String consumerKey = "FHeYhHcXkIf_mIQgnoso";
		String authorization = "Bearer AAAA7qmUNCMt56THdAqTtwO"
				+ "8EWETkERY2f6iQxtPMk9uLyePg8PAzmcoVLtfew9QPEnUH+Zc"
				+ "CSH2tojjDxo0/Mk/k+lv0BUuWmTA+pvwq7eX6AVx9D6gcKV0d"
				+ "xhm1YtUQrbZwbT10/xvMK+8MYsNVpnep8yzgu3iH/ClgTFU5wx"
				+ "RAeE1kn7oEzA7pb/nS8t62PQjVFT9SWIgIBsCdj6CFFAa7qg5Rx"
				+ "gNEaWmMaHrteOsoVAw6PGFaJJMY+kP+LJyU8D//2iOoLzW99lbo"
				+ "JBYqACIxe7tv3OxcIIpADDw0OKuOO7cKv65O7zc/CX7SyCXKYcWow==";
		String url = "https://alpha-apis.worksmobile.com/kr1kRotQuVbnD"
				+ "/message/sendMessage";
		URL object = new URL(url);
		
		//botapi
		HttpURLConnection con = (HttpURLConnection)object.openConnection();
		//botapi-header
		con.setDoOutput(true);
		con.setDoInput(true);
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("consumerKey", consumerKey);
		con.setRequestProperty("Authorization", authorization);
		con.setRequestMethod("POST"); 
		
		//botapi-body
		JSONObject postParameters = new JSONObject();
		postParameters.put("botNo", 16);
		postParameters.put("content", outputMessage);
		postParameters.put("channelNo", 494391);
		//postParameters.put("emailList","[\"intern002@wdomain8.com\"]");
		postParameters.put("type", 5);
		postParameters.put("push", true);

		OutputStream wr = con.getOutputStream();
		wr.write(postParameters.toString().getBytes("UTF-8"));
		wr.close();

		//display what returns the POST request
   StringBuilder sb = new StringBuilder();  
		int httpResult = con.getResponseCode(); 
		if (httpResult == HttpURLConnection.HTTP_OK) {
		    BufferedReader br = new BufferedReader(
		            new InputStreamReader(con.getInputStream(), "utf-8"));
		    String line = null;  
		    while ((line = br.readLine()) != null) {  
		        sb.append(line + "\n");  
		    }
		    br.close();
		    System.out.println("" + sb.toString());  
		} 
		else {
		    System.out.println(con.getResponseMessage());  
		} 
		
	}
}

	
	//문제 출제 함수
	public void sendproblem(long writerUserNo) throws Exception{
		HashMap problem = translateBO.sendProblem(writerUserNo); //문제출제
		translateBO.updateIdInfo(writerUserNo, problem.get("KO")
		.toString(), problem.get("EN").toString());
		
		//한/영 여부에 따라서
		if (translateBO.selectIdInfo(writerUserNo).get("lang")
				.toString().equals("ko")) {
			sendmessage(problem.get("EN").toString()); 
			//한글로 '/단어암기'를 쳤다면 영문문제 			
		}
		
		else if (translateBO.selectIdInfo(writerUserNo).get("lang")
				.toString().equals("en")) {
			
			sendmessage(problem.get("KO").toString());	
			//영어로 '/memorizing words'를 쳤다
			//면 한글문제
		}
		
	}
}