package com.appscomm.sport.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

import com.appscomm.sport.common.SportApiCode;
import com.appscomm.sport.model.PersonVO;

/**
 * JSON帮组类
 * @author kuangzhenming
 *
 */
public class JsonUtils {	
	static ObjectMapper mapper = new ObjectMapper();
	/**
	 * 对象转JSON
	 * @param t
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static <T> String toJson(T t) throws JsonGenerationException, JsonMappingException, IOException{
		if(t==null) return "";
		StringWriter strWriter = new StringWriter();
		if (t.getClass().equals(PersonVO.class)){
			mapper.setDateFormat(DateUtils.df);
		} else{
			mapper.setDateFormat(DateUtils.f);
		}
		mapper.writeValue(strWriter, t);
		return strWriter.toString();
	}
/**
 * JSON字符串转Map<String, String>
 * @param jsonString
 * @return
 * @throws JsonGenerationException
 * @throws JsonMappingException
 * @throws IOException
 */
	public static Map<String, String> toMap(String jsonString) throws JsonGenerationException, JsonMappingException, IOException{
		
		if(jsonString==null ||jsonString.equals(""))return null;
		@SuppressWarnings("unchecked")
		Map<String,String> requestMap = mapper.readValue(jsonString, Map.class);
		return requestMap;
	}

	/**
	 * zxh 20131205 add
	 * @param jsonString
	 * @return list返回数组类型值
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static List<Map<String, String>> toList(String jsonString) throws JsonGenerationException, JsonMappingException, IOException{
		
		if(jsonString==null ||jsonString.equals(""))return null;
		@SuppressWarnings("unchecked")
		List<Map<String, String>> result = mapper.readValue(jsonString, TypeFactory.collectionType(ArrayList.class, Map.class));
		//List<String> requestMap = mapper.readValue(jsonString, List.class);
		return result;
	}
	public static void main(String[] args){
		try {
			List list = toList("[{\"id\":\"-1\"}]");
			System.out.println(list.size());
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 返回客户端Json，支持多种SportApiCode结果
	 * @param code
	 * @param t
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static <T> String responseJson(SportApiCode code,T t) throws JsonGenerationException, JsonMappingException, IOException{
		 String json=JsonUtils.toJson(t);
		 if(json==null || json.equals(""))json="{}";
		// String responseStr = "{\"result\":\""+code.getCode()+"\",\"message\":\""+code.getDesc()+"\",\"data\":"+json+"}";
		 String responseStr = "{\"result\":\""+code.getCode()+"\",\"message\":\""+code.getDesc()+"\",\"servertime\":\""+System.currentTimeMillis()/1000+"\",\"data\":"+json+"}";
		return responseStr;
	}
}
