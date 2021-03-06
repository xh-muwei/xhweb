package xh.mybatis.service;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.tribes.util.Arrays;
import org.apache.ibatis.session.SqlSession;

import xh.mybatis.mapper.EastComMapper;
import xh.mybatis.tools.MoreDbTools;

public class EastComService {
	public static List<Map<String,Object>> queueTop5(String time){
		SqlSession session=MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.eastcom);
		SqlSession session2=MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.slave);
		EastComMapper mapper=session.getMapper(EastComMapper.class);
		EastComMapper mapper2=session2.getMapper(EastComMapper.class);
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> bsNames=new ArrayList<Map<String,Object>>();
		List<String> bsIds=new ArrayList<String>();
		
		try {
			list=mapper.queueTop5(time);
			if(list.size()<1){
				return list;
			}		
			for (Map<String,Object> map : list){
				int id=0;
				if(Integer.parseInt(map.get("bsId").toString())>=1200){
					id=Integer.parseInt(map.get("bsId").toString())%1000;
				}else if(Integer.parseInt(map.get("bsId").toString())>1000 && Integer.parseInt(map.get("bsId").toString())<1100){
					id=Integer.parseInt(map.get("bsId").toString());
				}else{
					id=Integer.parseInt(map.get("bsId").toString());
				}
				if(id>0){
					bsIds.add(String.valueOf(id));
				}
				
			}			
			bsNames=mapper2.queueTopBsName(bsIds);
			
			for (Map<String,Object> map2 : bsNames) {
				int bsId=Integer.parseInt(map2.get("bsId").toString());
				String name=map2.get("name").toString();
                for(int j=0;j<list.size();j++){
                	Map<String,Object> map3=new HashMap<String, Object>();
                	map3=list.get(j);
                	if(map3.get("bsId")!=null){
                	if(Integer.parseInt(map3.get("bsId").toString())==bsId){
                		map3.put("name", bsId+"-"+name);
                		map3.remove("bsId");
                		list.set(j, map3);
                	}
                	}
				}
			}
			
			 for(int j=0;j<list.size();j++){
             	Map<String,Object> map3=new HashMap<String, Object>();
             	map3=list.get(j);
             	if(map3.get("bsId")!=null){
             		map3.put("name", map3.get("bsId")+"-应急基站");
             		map3.remove("bsId");
             		list.set(j, map3);
             	}
				}
			
			session.close();
			session2.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
