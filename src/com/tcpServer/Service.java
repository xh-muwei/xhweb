package com.tcpServer;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import xh.func.plugin.FunUtil;
import xh.mybatis.mapper.EventMapper;
import xh.mybatis.mapper.TcpMapper;
import xh.mybatis.service.WebLogService;
import xh.mybatis.service.WebUserServices;
import xh.mybatis.tools.MoreDbTools;
import xh.org.listeners.SingLoginListener;

import com.tcpBean.DispatchTable;
import com.tcpBean.DispatchTableAck;
import com.tcpBean.ErrCheck;
import com.tcpBean.ErrCheckAck;
import com.tcpBean.ErrProTable;
import com.tcpBean.ErrProTableAck;
import com.tcpBean.GetMovebsInfo;
import com.tcpBean.GetMovebsInfoAck;
import com.tcpBean.GetOwnbsInfo;
import com.tcpBean.GetOwnbsInfoAck;
import com.tcpBean.LoginAck;
import com.tcpBean.MovebsTable;
import com.tcpBean.MovebsTableAck;
import com.tcpBean.NetManagerTable;
import com.tcpBean.NetManagerTableAck;
import com.tcpBean.OwnbsTable;
import com.tcpBean.OwnbsTableAck;
import com.tcpBean.UserInfo;
import com.tcpBean.UserInfoAck;
import com.tcpBean.UserLogin;

public class Service {
	// 发送用对象
	private static LoginAck loginAck = new LoginAck();
	private static UserInfoAck userInfoAck = new UserInfoAck();
	private static ErrProTable errProTable = new ErrProTable();
	private static ErrProTableAck errProTableAck = new ErrProTableAck();
	private static ErrCheckAck errCheckAck = new ErrCheckAck();
	private static NetManagerTableAck netManagerTableAck = new NetManagerTableAck();
	private static DispatchTableAck dispatchTableAck = new DispatchTableAck();
	private static MovebsTableAck movebsTableAck = new MovebsTableAck();
	private static OwnbsTableAck ownbsTableAck = new OwnbsTableAck();
	private static GetMovebsInfoAck getMovebsInfoAck = new GetMovebsInfoAck();
	private static GetOwnbsInfoAck getOwnbsInfoAck = new GetOwnbsInfoAck();
	
	private static FunUtil funUtil = new FunUtil();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(loginAck);
	}

	/**
	 * 登录处理
	 */
	public static LoginAck appLogin(UserLogin userLogin){
		Map<String,Object> map=new HashMap<String, Object>();
		map = WebUserServices.selectUserByRootAndPass(userLogin.getUserid(), funUtil.MD5(userLogin.getPasswd()));
		loginAck.setUserid(userLogin.getUserid());
		loginAck.setPasswd(userLogin.getPasswd());
		loginAck.setSerialnumber(userLogin.getSerialnumber());
		if (map!=null) {			
			loginAck.setAck("0");
		}else{
			loginAck.setAck("1");
		}
		return loginAck;	
	}
	
	/**
	 * 用户信息处理
	 */
	public static UserInfoAck appUserInfo(UserInfo userInfo){
		SqlSession sqlSession=MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.master);
		TcpMapper mapper=sqlSession.getMapper(TcpMapper.class);
		userInfoAck.setSerialnumber(userInfo.getSerialnumber());
		userInfoAck.setUserid(userInfo.getUserid());
		try{
			Map<String,String> map = mapper.selectUserName(userInfo.getUserid());
			userInfoAck.setUsername(map.get("userName"));
			sqlSession.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userInfoAck;
	}
	
	/**
	 * 获取移动基站信息
	 */
	public static GetMovebsInfoAck appGetMovebsInfoAck(GetMovebsInfo getMovebsInfo){
		SqlSession sqlSession=MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.master);
		TcpMapper mapper=sqlSession.getMapper(TcpMapper.class);
		getMovebsInfoAck.setUserid(getMovebsInfo.getUserid());
		String bsId = getMovebsInfo.getBsid();
		getMovebsInfoAck.setBsid(bsId);
		try {
			if(!"".equals(bsId) && bsId!=null){
				Map<String,Object> map = mapper.selectByBsId(bsId);
				if(map.size()>0){
					int bslevel = (Integer) map.get("bslevel");
					getMovebsInfoAck.setBslevel(String.valueOf(bslevel));
					getMovebsInfoAck.setBsname(map.get("bsname").toString());
					getMovebsInfoAck.setAck("0");
				}else{
					getMovebsInfoAck.setAck("1");
				}
				sqlSession.close();
			}else{
				getMovebsInfoAck.setAck("1");
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getMovebsInfoAck;
	}
	
	/**
	 * 获取自建基站信息
	 */
	public static GetOwnbsInfoAck appGetOwnbsInfoAck(GetOwnbsInfo getOwnbsInfo){
		SqlSession sqlSession=MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.master);
		TcpMapper mapper=sqlSession.getMapper(TcpMapper.class);
		getOwnbsInfoAck.setUserid(getOwnbsInfo.getUserid());
		String bsId = getOwnbsInfo.getBsid();
		getOwnbsInfoAck.setBsid(bsId);
		try {
			if(!"".equals(bsId) && bsId!=null){
				Map<String,Object> map = mapper.selectByBsId(bsId);
				if(map.size()>0){
					int bslevel = (Integer) map.get("bslevel");
					getOwnbsInfoAck.setBslevel(String.valueOf(bslevel));
					getOwnbsInfoAck.setBsname(map.get("bsname").toString());
					getOwnbsInfoAck.setAck("0");
				}else{
					getOwnbsInfoAck.setAck("1");
				}
				sqlSession.close();
			}else{
				getOwnbsInfoAck.setAck("1");
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getOwnbsInfoAck;
	}
	
	
	/**
	 * 更新派单状态为处理中
	 */
	public static void updateUserStatus(String serialNum){
		SqlSession sqlSession=MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.master);
		TcpMapper mapper=sqlSession.getMapper(TcpMapper.class);
		try{
			mapper.updateUserStatus(serialNum);
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 派单确认请求
	 */
	public static ErrCheckAck appErrCheck(ErrCheck errCheck){
		SqlSession sqlSession=MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.master);
		TcpMapper mapper=sqlSession.getMapper(TcpMapper.class);
		try {
			Map<String, String> map = mapper.selectOrderStatus(errCheck.getSerialnumber());
			System.out.println("map为："+map);
			String status=map.get("status");
			System.out.println("status为："+status);
			if("2".equals(status)){
				errCheckAck.setResult("0");
			}else{
				errCheckAck.setResult("1");
			}
			sqlSession.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		errCheckAck.setSerialnumber(errCheck.getSerialnumber());
		errCheckAck.setUserid(errCheck.getUserid());		
		return errCheckAck;
	}
	
	/**
	 * 派单完结
	 */
	public static ErrProTableAck appProTableAck(ErrProTable errProTable){
		SqlSession sqlSession=MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.master);
		TcpMapper mapper=sqlSession.getMapper(TcpMapper.class);
		//需要入库
		LinkedList<String> list = new LinkedList<String>();
		list.add(errProTable.getBsid());
		list.add(errProTable.getBsname());
		list.add(errProTable.getDispatchtime());
		list.add(errProTable.getDispatchman());
		list.add(errProTable.getErrtype());
		list.add(errProTable.getErrlevel());
		list.add(errProTable.getErrfoundtime());
		list.add(errProTable.getErrslovetime());
		list.add(errProTable.getProgress());
		list.add(errProTable.getProresult());
		list.add(errProTable.getWorkman());
		list.add(errProTable.getAuditor());
		list.add(errProTable.getLongitude());
		list.add(errProTable.getLatitude());
		list.add(errProTable.getAddress());
		list.add(errProTable.getSerialnumber());
		list.add(errProTable.getUserid());
		//设置派单状态为已完成
		list.add("2");		
		try {
			//派单入库前根据序列号删除原空单
			int count = mapper.deleteBySerialNum(errProTable.getSerialnumber());
			if(count>0){
				//最终完成的派单入库
				mapper.insertFaultOrder(list);
			}		
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		errProTableAck.setSerialnumber(errProTable.getSerialnumber());
		errProTableAck.setUserid(errProTable.getUserid());
		return errProTableAck;
	}
	
	/**
	 * 移动基站巡检表
	 */
	public static MovebsTableAck appMovebsTableAck(MovebsTable movebsTable){
		SqlSession sqlSession=MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.master);
		TcpMapper mapper=sqlSession.getMapper(TcpMapper.class);
		//需要入库
		LinkedList<String> list = new LinkedList<String>();
		list.add(movebsTable.getSerialnumber());
		list.add(movebsTable.getBsid());
		list.add(movebsTable.getBsname());
		list.add(movebsTable.getBslevel());
		list.add(movebsTable.getBstype());
		list.add(movebsTable.getLongitude());
		list.add(movebsTable.getLatitude());
		list.add(movebsTable.getAddress());
		list.add(movebsTable.getCheckman());
		list.add(movebsTable.getCommitdate());
		list.add(movebsTable.getRemainwork());
		list.add(movebsTable.getUserid());
		List<Map<String,String>> message = movebsTable.getMessage();
		for(int i=0;i<message.size();i++){
			Map<String,String> map = message.get(i);
			list.add(map.get("result"));
		}
		for(int i=0;i<message.size();i++){
			Map<String,String> map = message.get(i);			
			list.add(map.get("remarks"));;
		}
		System.out.println("list为："+list);
		try {
			mapper.insertMoveBsTable(list);
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		movebsTableAck.setSerialnumber(movebsTable.getSerialnumber());
		movebsTableAck.setUserid(movebsTable.getUserid());
		movebsTableAck.setAck("0");
		return movebsTableAck;
	}
	
	/**
	 * 800M自建基站巡检表
	 */
	public static OwnbsTableAck appOwnbsTable(OwnbsTable ownbsTable){
		SqlSession sqlSession=MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.master);
		TcpMapper mapper=sqlSession.getMapper(TcpMapper.class);
		//需要入库
		LinkedList<String> list = new LinkedList<String>();
		list.add(ownbsTable.getSerialnumber());
		list.add(ownbsTable.getBsid());
		list.add(ownbsTable.getBsname());
		list.add(ownbsTable.getBslevel());
		list.add(ownbsTable.getBstype());
		list.add(ownbsTable.getAmmeternumber());
		list.add(ownbsTable.getLongitude());
		list.add(ownbsTable.getLatitude());
		list.add(ownbsTable.getAddress());
		list.add(ownbsTable.getCheckman());
		list.add(ownbsTable.getCommitdate());
		list.add(ownbsTable.getRemainwork());
		list.add(ownbsTable.getUserid());	
		List<Map<String,String>> message = ownbsTable.getMessage();
		for(int i=0;i<message.size();i++){
			Map<String,String> map = message.get(i);
			list.add(map.get("result"));
		}
		for(int i=0;i<message.size();i++){
			Map<String,String> map = message.get(i);			
			list.add(map.get("remarks"));;
		}
		System.out.println("list为："+list);
		try {
			mapper.insertOwnBsTable(list);
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ownbsTableAck.setSerialnumber(ownbsTable.getSerialnumber());
		ownbsTableAck.setUserid(ownbsTable.getUserid());
		ownbsTableAck.setAck("0");
		return ownbsTableAck;
	}
	
	/**
	 * 调度台巡检作业表
	 */
	public static DispatchTableAck appDispatchTableAck(DispatchTable dispatchTable){
		SqlSession sqlSession=MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.master);
		TcpMapper mapper=sqlSession.getMapper(TcpMapper.class);
		//需要入库
		LinkedList<String> list = new LinkedList<String>();
		list.add(dispatchTable.getSerialnumber());
		list.add(dispatchTable.getDispatchname());
		list.add(dispatchTable.getDispatchplace());
		list.add(dispatchTable.getLongitude());
		list.add(dispatchTable.getLatitude());
		list.add(dispatchTable.getAddress());
		list.add(dispatchTable.getCheckman());
		list.add(dispatchTable.getCommitdate());
		list.add(dispatchTable.getUserid());		
		List<Map<String,String>> message = dispatchTable.getMessage();
		for(int i=0;i<message.size();i++){
			Map<String,String> map = message.get(i);
			list.add(map.get("comment"));
		}
		for(int i=0;i<message.size();i++){
			Map<String,String> map = message.get(i);			
			list.add(map.get("result"));;
		}
		for(int i=0;i<message.size();i++){
			Map<String,String> map = message.get(i);			
			list.add(map.get("description"));;
		}
		for(int i=0;i<message.size();i++){
			Map<String,String> map = message.get(i);			
			list.add(map.get("remarks"));;
		}
		System.out.println("list为："+list);
		try {
			mapper.insertDispatchTable(list);
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		dispatchTableAck.setSerialnumber(dispatchTable.getSerialnumber());
		dispatchTableAck.setUserid(dispatchTable.getUserid());
		dispatchTableAck.setAck("0");
		return dispatchTableAck;
	}
	
	/**
	 * 网管巡检作业表
	 */
	public static NetManagerTableAck appNetManagerTableAck(NetManagerTable netManagerTable){
		SqlSession sqlSession=MoreDbTools.getSession(MoreDbTools.DataSourceEnvironment.master);
		TcpMapper mapper=sqlSession.getMapper(TcpMapper.class);
		//需要入库
		LinkedList<String> list = new LinkedList<String>();
		list.add(netManagerTable.getSerialnumber());
		list.add(netManagerTable.getManagername());
		list.add(netManagerTable.getManagerplace());
		list.add(netManagerTable.getLongitude());
		list.add(netManagerTable.getLatitude());
		list.add(netManagerTable.getAddress());
		list.add(netManagerTable.getCheckman());
		list.add(netManagerTable.getCommitdata());
		list.add(netManagerTable.getUserid());
		
		List<Map<String,String>> message = netManagerTable.getMessage();
		for(int i=0;i<message.size();i++){
			Map<String,String> map = message.get(i);
			list.add(map.get("comment"));
		}
		for(int i=0;i<message.size();i++){
			Map<String,String> map = message.get(i);			
			list.add(map.get("result"));;
		}
		for(int i=0;i<message.size();i++){
			Map<String,String> map = message.get(i);			
			list.add(map.get("description"));;
		}
		for(int i=0;i<message.size();i++){
			Map<String,String> map = message.get(i);			
			list.add(map.get("remarks"));;
		}
		System.out.println("list为："+list);
		try {
			mapper.insertNetTable(list);
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		netManagerTableAck.setSerialnumber(netManagerTable.getSerialnumber());
		netManagerTableAck.setUserid(netManagerTable.getUserid());
		netManagerTableAck.setAck("0");
		return netManagerTableAck;
	}
	
}
