//package com.naver.translationBot;
//
//import static org.junit.Assert.*;
//
//import javax.inject.Inject;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import com.naver.bo.translateBO;
//
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
//		                             ,"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
//
//@WebAppConfiguration
//public class TranslationControllerTest {
//
//	@Mock
//	translateBO translateBO;
//	@InjectMocks
//	private TranslationController translationController;
//	
//	@Autowired
//    private WebApplicationContext wac;
//		 private MockMvc mockMvc;
//		 
//		 @Before
//	     public void setUp() throws Exception {
//	          MockitoAnnotations.initMocks(this);
//	          mockMvc = MockMvcBuilders.standaloneSetup(translationController).build();
//	     }
//	 
//	
//	@Test
//	public void test() throws Exception {
//		
//		when(translateBO.method1()).thenReturn(10);
//        mockMvc.perform(get("/category/list")).andExpect(status().isOk());
//
//        verify(translateBO).method1();
//        verifyNoMoreInteractions(translateBO);
//
//	}
//
//}
