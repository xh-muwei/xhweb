package xh.mybatis.service;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import xh.mybatis.bean.BsstationBean;
import xh.mybatis.bean.ChartBean;
import xh.mybatis.bean.bsLinkConfigBean;
import xh.mybatis.bean.bscConfigBean;
import xh.mybatis.bean.bsrConfigBean;
import xh.mybatis.mapper.BsstationMapper;
import xh.mybatis.tools.DbTools;
import xh.mybatis.tools.MoreDbTools;

public class BsstationService {
	/**
	 * 查询基站信息
	 * 
	 * @param root
	 * @return
	 */
	public static List<BsstationBean> bsInfo(Map<String, Object> map) {
		SqlSession sqlSession = MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.slave);
		BsstationMapper mapper = sqlSession.getMapper(BsstationMapper.class);
		List<BsstationBean> list = new ArrayList<BsstationBean>();
		try {
			list = mapper.bsInfo(map);
			sqlSession.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public static List<HashMap<String, Object>> bsstatusInfo(Map<String, Object> map) {
		SqlSession sqlSession = MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.slave);
		BsstationMapper mapper = sqlSession.getMapper(BsstationMapper.class);
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		try {
			list = mapper.bsstatusInfo(map);
			sqlSession.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public static List<Map<String, Object>> bsInfolimit() {
		SqlSession sqlSession = MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.slave);
		BsstationMapper mapper = sqlSession.getMapper(BsstationMapper.class);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			list = mapper.bsInfolimit();
			sqlSession.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 基站断站列表
	 * @param map
	 * @return
	 */
	public static List<Map<String, Object>> monitorBsofflineList() {
		SqlSession sqlSession = MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.slave);
		BsstationMapper mapper = sqlSession.getMapper(BsstationMapper.class);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			list = mapper.monitorBsofflineList();
			sqlSession.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 基站限制列表
	 * @param map
	 * @return
	 */
	public static List<Map<String, Object>> bslimitList(Map<String, Object> map) {
		SqlSession sqlSession = MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.slave);
		BsstationMapper mapper = sqlSession.getMapper(BsstationMapper.class);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			list = mapper.bslimitList(map);
			sqlSession.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 基站限制列表总数
	 * @param map
	 * @return
	 */
	public static int bslimitListCount() {
		SqlSession sqlSession = MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.slave);
		BsstationMapper mapper = sqlSession.getMapper(BsstationMapper.class);
		int count=0;
		try {
			count = mapper.bslimitListCount();
			sqlSession.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	//基站总数
	public static int bsTotal() {
		SqlSession sqlSession = MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.slave);
		BsstationMapper mapper = sqlSession.getMapper(BsstationMapper.class);
		int count=0;
		try {
			count = mapper.bsTotal();
			sqlSession.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	/**
	 * 新增限制列表
	 * @param map
	 * @return
	 */
	public static int addBsLimit(List<String> list) {
		SqlSession sqlSession = MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.master);
		BsstationMapper mapper = sqlSession.getMapper(BsstationMapper.class);
		int count=0;
		try {
			count = mapper.addBsLimit(list);
			sqlSession.commit();
			sqlSession.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	/**
	 * 删除限制列表
	 * @param map
	 * @return
	 */
	public static int deleteBsLimit(List<String> list) {
		SqlSession sqlSession = MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.master);
		BsstationMapper mapper = sqlSession.getMapper(BsstationMapper.class);
		int count=0;
		try {
			count = mapper.deleteBsLimit(list);
			sqlSession.commit();
			sqlSession.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 查询基站断站列表
	 * @return
	 */
	public static List<HashMap<String,Object>>bsOfflineList() {
		SqlSession sqlSession = MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.slave);
		BsstationMapper mapper = sqlSession.getMapper(BsstationMapper.class);
		List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
		try {
			list = mapper.bsOfflineList();
			sqlSession.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 基站总数
	 * 
	 * @return
	 * @throws Exception
	 */
	public static int bsCount(Map<String, Object> map) {
		SqlSession sqlSession = MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.slave);
		BsstationMapper mapper = sqlSession.getMapper(BsstationMapper.class);
		int count = 0;
		try {
			count = mapper.bsCount(map);
			sqlSession.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	/**
	 * 根据基站ID,判断该基站相邻小区是否存在
	 * @param map
	 * @return
	 */
	public static int neighborExists(Map<String, Object> map) {
		SqlSession sqlSession = MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.slave);
		BsstationMapper mapper = sqlSession.getMapper(BsstationMapper.class);
		int count = 0;
		try {
			count = mapper.neighborExists(map);
			sqlSession.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 新增基站切换参数
	 * @param map
	 * @return
	 */
	public static int addBsHandover(Map<String, Object> map) {
		SqlSession sqlSession = MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.master);
		BsstationMapper mapper = sqlSession.getMapper(BsstationMapper.class);
		int count = 0;
		try {
			count = mapper.addBsHandover(map);
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	/**
	 *  修改基站切换参数
	 * @param map
	 * @return
	 */
	public static int updateBsHandover(Map<String, Object> map) {
		SqlSession sqlSession = MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.master);
		BsstationMapper mapper = sqlSession.getMapper(BsstationMapper.class);
		int count = 0;
		try {
			count = mapper.updateBsHandover(map);
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	
	/**
	 * 新增基站相邻小区
	 * @param map
	 * @return
	 */
	public static int addBsNeighbor(Map<String, Object> map) {
		SqlSession sqlSession = MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.master);
		BsstationMapper mapper = sqlSession.getMapper(BsstationMapper.class);
		int rslt = 0;
		try {
			rslt = mapper.addBsNeighbor(map);
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rslt;
	}
	
	/**
	 * 删除基站相邻小区
	 * @param map
	 * @return
	 */
	public static int delBsNeighbor(Map<String, Object> map) {
		SqlSession sqlSession = MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.master);
		BsstationMapper mapper = sqlSession.getMapper(BsstationMapper.class);
		int rslt = 0;
		try {
			rslt = mapper.delBsNeighbor(map);
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rslt;
	}
	
	
	/**
	 * 根据基站ID,调单好判断该基站传输是否存在
	 * @param map
	 * @return
	 */
	public static int linkconfigExists(Map<String, Object> map) {
		SqlSession sqlSession = MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.slave);
		BsstationMapper mapper = sqlSession.getMapper(BsstationMapper.class);
		int count = 0;
		try {
			count = mapper.linkconfigExists(map);
			sqlSession.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	/**
	 *  新增基站传输
	 * @param map
	 * @return
	 */
	public static int addLinkconfig(bsLinkConfigBean bean) {
		SqlSession sqlSession = MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.master);
		BsstationMapper mapper = sqlSession.getMapper(BsstationMapper.class);
		int rslt = 0;
		try {
			rslt = mapper.addLinkconfig(bean);
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rslt;
	}
	
	/**
	 * 删除基站传输
	 * @param map
	 * @return
	 */
	public static int delLinkconfig(int id) {
		SqlSession sqlSession = MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.master);
		BsstationMapper mapper = sqlSession.getMapper(BsstationMapper.class);
		int rslt = 0;
		try {
			rslt = mapper.delLinkconfig(id);
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rslt;
	}
	
	
	/**
	 * 根据基站ID,bscId,bsrId判断该基站bsr是否存在 
	 * @param map
	 * @return
	 */
	public static int bsrconfigExists(Map<String, Object> map) {
		SqlSession sqlSession = MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.slave);
		BsstationMapper mapper = sqlSession.getMapper(BsstationMapper.class);
		int count = 0;
		try {
			count = mapper.bsrconfigExists(map);
			sqlSession.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 根据基站ID,bscId判断该基站bsc是否存在
	 * @param map
	 * @return
	 */
	public static int bscconfigExists(Map<String, Object> map) {
		SqlSession sqlSession = MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.slave);
		BsstationMapper mapper = sqlSession.getMapper(BsstationMapper.class);
		int count = 0;
		try {
			count = mapper.bscconfigExists(map);
			sqlSession.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	/**
	 *  新增基站bsr
	 * @param map
	 * @return
	 */
	public static int addBsrconfig(bsrConfigBean bean) {
		SqlSession sqlSession = MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.master);
		BsstationMapper mapper = sqlSession.getMapper(BsstationMapper.class);
		int rslt = 0;
		try {
			rslt = mapper.addBsrconfig(bean);
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rslt;
	}
	
	/**
	 *  新增基站bsc
	 * @param map
	 * @return
	 */
	public static int addBscconfig(bscConfigBean bean) {
		SqlSession sqlSession = MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.master);
		BsstationMapper mapper = sqlSession.getMapper(BsstationMapper.class);
		int rslt = 0;
		try {
			rslt = mapper.addBscconfig(bean);
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rslt;
	}
	
	/**
	 * 删除基站bsr
	 * @param map
	 * @return
	 */
	public static int delBsrconfig(int id) {
		SqlSession sqlSession = MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.master);
		BsstationMapper mapper = sqlSession.getMapper(BsstationMapper.class);
		int rslt = 0;
		try {
			rslt = mapper.delBsrconfig(id);
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rslt;
	}
	
	/**
	 * 删除基站bsc
	 * @param map
	 * @return
	 */
	public static int delBscconfig(int id) {
		SqlSession sqlSession = MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.master);
		BsstationMapper mapper = sqlSession.getMapper(BsstationMapper.class);
		int rslt = 0;
		try {
			rslt = mapper.delBscconfig(id);
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rslt;
	}

	/**
	 * 添加基站
	 * 
	 * @return
	 * @throws Exception
	 */
	public static int insertBs(BsstationBean bean) {
		SqlSession sqlSession = MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.master);
		BsstationMapper mapper = sqlSession.getMapper(BsstationMapper.class);
		int count = 0;
		try {
			count = mapper.selectByBsId(bean.getBsId());
			if (count == 0) {
				mapper.insertBs(bean);
			}
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * 修改基站
	 * 
	 * @return
	 * @throws Exception
	 */
	public static int updateBs(BsstationBean bean) {
		SqlSession sqlSession = MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.master);
		BsstationMapper mapper = sqlSession.getMapper(BsstationMapper.class);
		int count = 0;
		try {
			count = mapper.updateByBsId(bean);
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * 删除基站
	 * 
	 * @return
	 * @throws Exception
	 */
	public static void deleteBsByBsId(List<String> list) {
		SqlSession sqlSession = MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.master);
		BsstationMapper mapper = sqlSession.getMapper(BsstationMapper.class);
		try {
			mapper.deleteBsByBsId(list);
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 查询所有基站
	 * @return
	 */
	public static List<HashMap<String, Object>> allBsInfo(Map<String,Object> map) {
		SqlSession sqlSession = MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.slave);
		BsstationMapper mapper = sqlSession.getMapper(BsstationMapper.class);
		List<HashMap> list = new ArrayList<HashMap>();
		List<HashMap<String, Object>> result =new ArrayList<HashMap<String,Object>>();
		
		try {
			list=mapper.allBsInfo(map);
			List<HashMap<String, String>> area =selectAllArea();
			
			
			
			for (HashMap<String, String> map1 : area) {
				List<HashMap<String, String>> list1=new ArrayList<HashMap<String,String>>();
				HashMap<String, Object> map3=new HashMap<String, Object>();
				for (HashMap<String, String> map2 : list) {
					if(map1.get("zone").toString().equals(map2.get("zone").toString())){
						list1.add(map2);
					}
				}
				
				if(list1.size()>0){
					map3.put("zone", map1.get("zone"));
					map3.put("total", list1.size());
					map3.put("item", list1);
					result.add(map3);
				}
				
				
			}
			
			sqlSession.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 根据基站ID查找基站相邻小区
	 * @return
	 */
	public static List<Map<String,Object>>  neighborByBsId(int bsId) {
		SqlSession sqlSession = MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.slave);
		BsstationMapper mapper = sqlSession.getMapper(BsstationMapper.class);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			list=mapper.neighborByBsId(bsId);
			sqlSession.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 根据基站ID查找基站切换参数
	 * @return
	 */
	public static List<Map<String,Object>>  handoverByBsId(int bsId) {
		SqlSession sqlSession = MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.slave);
		BsstationMapper mapper = sqlSession.getMapper(BsstationMapper.class);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			list=mapper.handoverByBsId(bsId);
			sqlSession.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 根据基站ID查找基站BSR配置信息
	 * @return
	 */
	public static List<Map<String,Object>>  bsrconfigByBsId(int bsId) {
		SqlSession sqlSession = MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.slave);
		BsstationMapper mapper = sqlSession.getMapper(BsstationMapper.class);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			list=mapper.bsrconfigByBsId(bsId);
			sqlSession.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 根据基站ID查找基站BSC配置信息
	 * @return
	 */
	public static List<Map<String,Object>>  bscconfigByBsId(int bsId) {
		SqlSession sqlSession = MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.slave);
		BsstationMapper mapper = sqlSession.getMapper(BsstationMapper.class);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			list=mapper.bscconfigByBsId(bsId);
			sqlSession.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	/**
	 * 根据基站ID查找基站传输配置信息
	 * @return
	 */
	public static List<Map<String,Object>>  linkconfigByBsId(int bsId) {
		SqlSession sqlSession = MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.slave);
		BsstationMapper mapper = sqlSession.getMapper(BsstationMapper.class);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			list=mapper.linkconfigByBsId(bsId);
			sqlSession.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 根据所选区域查询所有基站
	 * @author wlk
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> bsByArea(List<String> zone) throws Exception{
		SqlSession session=MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.slave);
		BsstationMapper mapper=session.getMapper(BsstationMapper.class);
		List<HashMap<String, String>> BsStation=mapper.bsByArea(zone);
	        session.commit();  
	        session.close();
	        return BsStation;   
	}
	
	/**
	 * 根据所选级别查询所有基站
	 * @author wlk
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> bsByLevel(String level) throws Exception{
		SqlSession session=MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.slave);
		BsstationMapper mapper=session.getMapper(BsstationMapper.class);
		List<HashMap<String, String>> BsStation=mapper.bsByLevel(level);
	        session.commit();  
	        session.close();
	        return BsStation;   
	}

	
	/**
	 * 查询所有基站区域
	 * @author wlk
	 * @return
	 * @throws Exception
	 */
	public static List<HashMap<String, String>> selectAllArea() throws Exception{
		SqlSession session=MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.slave);
		BsstationMapper mapper=session.getMapper(BsstationMapper.class);
		List<HashMap<String, String>> BsStation=mapper.selectAllArea();
	        session.commit();  
	        session.close();
	        return BsStation;   
	}
	
	/**
	 * 查询所有基站级别
	 * @author wlk
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectLevel() throws Exception{
		SqlSession session=MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.slave);
		BsstationMapper mapper=session.getMapper(BsstationMapper.class);
		List<HashMap<String, String>> BsStation=mapper.selectLevel();
	        session.commit();  
	        session.close();
	        return BsStation;   
	}

	/**
	 * 查询所有基站信息，用于首页展示
	 * @author wlk
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectAllBsStation() throws Exception{
		SqlSession session=MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.slave);
		BsstationMapper mapper=session.getMapper(BsstationMapper.class);
		List<HashMap<String, String>> BsStation=mapper.selectAllBsStation();
	        session.commit();  
	        session.close();
	        return BsStation;   
	}
	/**
	 * 根据选中的基站id进行查询
	 * @author wlk
	 * @param bsId
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectBsStationById(int bsId) throws Exception{
		SqlSession session =MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.slave);
		BsstationMapper mapper=session.getMapper(BsstationMapper.class);
		List<HashMap<String, String>> bsStation = mapper.selectBsStationById(bsId);
		session.commit();
		session.close();
		return bsStation;
	}
	/**
	 * 查询单个基站话务量及其他业务
	 * @author wlk
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectCalllistById(String currentMonth) throws Exception{
		SqlSession session=MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.gps_voice_slave);
		BsstationMapper mapper=session.getMapper(BsstationMapper.class);
		List<HashMap<String, String>> BsStation=mapper.selectCalllistById(currentMonth);
	        session.commit();  
	        session.close();
	        return BsStation;   
	}
	/**
	 * 查询单个基站排队数及其他业务
	 * @author wlk
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectChannelById() throws Exception{
		SqlSession session=MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.slave);
		BsstationMapper mapper=session.getMapper(BsstationMapper.class);
		List<HashMap<String, String>> BsStation=mapper.selectChannelById();
	        session.commit();  
	        session.close();
	        return BsStation;   
	}
	
	/**
	 * 路测数据查询
	 * @author wlk
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectRoadTest() throws Exception{
		SqlSession session=MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.slave);
		BsstationMapper mapper=session.getMapper(BsstationMapper.class);
		List<HashMap<String, String>> BsStation=mapper.selectRoadTest();
	        session.commit();  
	        session.close();
	        return BsStation;   
	}
	
	/**
	 * 圈选基站查询
	 * 
	 * @param root
	 * @return
	 */
	public static List<HashMap<String,String>> rectangle(Map<String, Object> map) {
		SqlSession sqlSession = MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.slave);
		BsstationMapper mapper = sqlSession.getMapper(BsstationMapper.class);
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		try {
			list = mapper.rectangle(map);
			sqlSession.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 圈选基站总数
	 * 
	 * @return
	 * @throws Exception
	 */
	public static int rectangleCount(Map<String, Object> map) {
		SqlSession sqlSession = MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.slave);
		BsstationMapper mapper = sqlSession.getMapper(BsstationMapper.class);
		int count = 0;
		try {
			count = mapper.rectangleCount(map);
			sqlSession.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	

}