package com.digou.service;

import com.digou.common.ResponseCommon;
import com.digou.entity.Order;
import com.digou.entity.Product;
import com.digou.entity.SellerUser;
import com.digou.entity.income;
import com.digou.mapper.SellerMapper;
import org.omg.CORBA.BAD_INV_ORDER;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ComponentScan({"com.digou.mapper"})
@Service("sellerService")
public class SellerService {
	
    @Resource
    private SellerMapper sellerMapper;

	public Map<String, Object> register(HttpServletResponse response,
										String url,
										String telephone,
										String shopName,
										String major,
										String description,
										String nickname,
										String password) {
		//检查
    	if (sellerMapper.findUser(telephone) != null) {
    		return ResponseCommon.wrappedResponse(null, 102, null);
		}
		//插入
    	SellerUser user = new SellerUser(url, telephone, shopName, nickname, description, major, password);
    	int result = sellerMapper.insertUser(user);
    	if (result != 1) {
			return ResponseCommon.wrappedResponse(null, 106, null);
		}
		//返回
    	Map<String, Object> data = new HashMap<>();
    	data.put("id", user.getId());
    	return ResponseCommon.wrappedResponse(data, 101, null);
    }

	public Map<String, Object> info_get(HttpServletResponse response, int id) {
		//检查
		SellerUser sellerUser = sellerMapper.findUserById(id);
    	if (sellerUser == null) {
    		return ResponseCommon.wrappedResponse(null, 102, null);
		} else {
			Map<String, Object> data = new HashMap<>();
			data.put("data", sellerUser);
			return ResponseCommon.wrappedResponse(data, 101, null);
		}
    }

	public Map<String, Object> info_modify(HttpServletResponse response, int id,String url,String telephone,String shopName,String major,String description,String nickname) {
		//插入
		SellerUser user = new SellerUser(url, telephone, shopName, nickname, description, major, "");
		user.setId(id);
		sellerMapper.modifyUser(user);
		return ResponseCommon.wrappedResponse(null, 101, null);
	}

	public Map<String, Object> good_add(HttpServletResponse response, int id, String url, String goodName, float price, String description, int num) {
		//插入
		Product product = new Product(goodName, description, price, url, id, num);
		System.out.println(num);
		sellerMapper.addGood(product);
		return ResponseCommon.wrappedResponse(null, 101, null);
	}

	public Map<String, Object> good_all(HttpServletResponse response, int id) {
		//插入
		ArrayList<Product> products = sellerMapper.allGood(id);
		Map<String, Object> data = new HashMap<>();
		data.put("array", products);
		return ResponseCommon.wrappedResponse(data, 101, null);
	}

	public Map<String, Object> login(HttpServletResponse response, String telephone, String password) {
		SellerUser user = sellerMapper.findUser(telephone);
		if (user == null || !user.getPassword().equals(password) || user.getIsBlack() == 1 || user.getIsPass() == 0) {
			return ResponseCommon.wrappedResponse(null, 102, null);
		}
		Map<String, Object> data = new HashMap<>();
		data.put("data", user);
		return ResponseCommon.wrappedResponse(data, 101, null);
	}


	//郭伟明
	//查看订单
	public Map<String, Object> look_order(HttpServletResponse response, int id) {
		String time;
		ArrayList<Order> orders = sellerMapper.allOrder(id);
		for (int i = 0; i < orders.size(); i++) {
			orders.get(i).Date = timetoDate(orders.get(i).createTime,1);
		}
		Map<String, Object> data = new HashMap<>();
		data.put("order", orders);
		return ResponseCommon.wrappedResponse(data, 101, null);
	}
	//退款
	public Map<String, Object> refund_order(HttpServletResponse response, int orderId) {
		sellerMapper.refund(orderId);
		return ResponseCommon.wrappedResponse(null, 101, null);
	}

	//待发货、已发货
	public Map<String, Object>  send_order(HttpServletResponse response, int orderId) {
		sellerMapper.sending(orderId);
		return ResponseCommon.wrappedResponse(null, 101, null);
	}

	//修改商品信息
	public Map<String, Object> good_modify(HttpServletResponse response,
										   int pID,
										   String pName, String description, float price, String portraitURL, int sID, int num) {
		Product product = new Product(  pName,  description,  price,  portraitURL,  sID,  num);
		product.setPId(pID);
		sellerMapper.modify(product);
		return ResponseCommon.wrappedResponse(null, 101, null);
	}

	//计算商家日、周、月、年及总收入
	public Map<String, Object> caculate_income(HttpServletResponse response,
											   int id){
		long time_now_to_create=0,time_30d_ago=0,time_20w_ago=0,time_13m_ago=0;
		long day=24*60*60*1000L;
		float income_1=0,income_7=0,income_30=0,income_365=0,income_all=0;
		ArrayList<Order> orders_price = sellerMapper.all_order_price(id);
		ArrayList<income> all_income = new ArrayList<income>();
		float profit=0;
		income my=new income();

		//获取当前时间精确到毫秒级的时间戳，例：1525849325942
		long time = System.currentTimeMillis();
		long Time = time;
		for(int i=0;i<orders_price.size();i++) {
			time_now_to_create = time - orders_price.get(i).createTime;
			profit = orders_price.get(i).orderPrice - orders_price.get(i).adminProfit;
			//计算收入
			/**
			 * 记得之后改成 判断订单完成的钱算作收入
			 */

			if (time_now_to_create < 1 * day) {
				//24小时之内
				income_1 = income_1 + profit;
			} else if (time_now_to_create < 7 * day) {
				//7天之内
				income_7 = income_7 + profit;
			} else if (time_now_to_create < 30 * day) {
				//30天之内
				income_30 = income_30 + profit;
			} else if (time_now_to_create < 365 * day) {
				//365天之内
				income_365 = income_365 + profit;
			} else {
				income_all = income_all + profit;
			}

			//计算拥有收入的时间
			time_30d_ago = Time_30d_ago(time);
			time_20w_ago = Time_20w_ago(time);
			//time_13m_ago=Time_13m_ago(time,12);//向前12个月，就是最近13个月
			//如果在前30天之内
			if (orders_price.get(i).createTime > time_30d_ago) {
				int j = (int) ((orders_price.get(i).createTime - time_30d_ago) / (1 * day));
				my.this_day_income[j] += profit;
			}

			//如果在前20周之内
			//1546185600151--2018.12.31 0:0:0 周一的开始
			if (orders_price.get(i).createTime > time_20w_ago) {
				int j = (int) ((orders_price.get(i).createTime - time_20w_ago) / (7 * day));
				my.this_week_income[j] += profit;
			}

			//如果在最近13个月之内
			if(orders_price.get(i).createTime > time_13m_ago){
				//int j= (int) ((orders_price.get(i).createTime - time_13m_ago) / (30*day));
				int j=0;
				for (int k = 1; k <= 12; k++) {//
					if( (orders_price.get(i).createTime >= Time_13m_ago(time,0)) &&(orders_price.get(i).createTime < time) ){
						j=12;
					}
					else if((orders_price.get(i).createTime >= Time_13m_ago(time,k)) && (orders_price.get(i).createTime < Time_13m_ago(time,k-1))){
						j=12-k;
					}
				}
				my.this_month_income[j] += profit;
			}
		}

		//my.this_day_income[0]=111;
		income_7 = income_1+income_7;
		income_30 = income_7+income_30;
		income_365 = income_30+income_365;
		income_all=income_365+income_all;



		my.income_1=income_1;
		my.income_7=income_7;
		my.income_30=income_30;
		my.income_365=income_365;
		my.income_all=income_all;

		for (int i = 29; i >= 0 ; i--) {
			my.day_Date[i]=timetoDate(Time,4);
			Time -= 1*day;
		}
		Time=time;
		int mon=Integer.parseInt(timetoDate(time,3));
		for (int i = 12; i >= 0 ; i--) {
			my.month_Date[i]=mon;
			if(mon==1){
				mon=12;
			}
			else{
				mon--;
			}
		}
		all_income.add(my);

		Map<String, Object> data = new HashMap<>();
		data.put("income", all_income);
		return ResponseCommon.wrappedResponse(data, 101, null);
	}

	//下架商品
	public Map<String, Object> good_delete(HttpServletResponse response, int id) {
		sellerMapper.delete(id);
		return ResponseCommon.wrappedResponse(null, 101, null);
	}

	//k月前
	public static long Time_13m_ago(long time,int k) {
		//float yea_month,year;
		long new_time;
		String new_date,now_date;
		now_date = timetoDate(time,6);//y-m
		new_date = now_date+"-01 00:00:00";

		while(k!=0) {
			new_time = Long.parseLong(dateToStamp(new_date));
			new_time -= 1;
			new_date = (timetoDate(new_time, 6))+"-01 00:00:00";
			//System.out.println(new_date);
			k--;
		}
		new_time = Long.parseLong(dateToStamp(new_date));
		return new_time;
	}
	//20周前的准确时间
	public static long Time_20w_ago(long time){

		long day= 24*60*60*1000L;
		long k,new_time_mid,monday;
		k = time - 1546185600151L;
		new_time_mid = ( k )%(7*day);
		monday = time - new_time_mid;
		return  monday - 19*7*day;
	}
	//30天前的准确时间
	public static long Time_30d_ago(long time){

		long day= 24*60*60*1000L;
		long k,new_time_mid,today;
		k = time - 1546185600151L;
		new_time_mid = ( k )%(day);
		today = time - new_time_mid;
		return  today - 29*day;
	}
	//时间转化为时间戳
	public static String dateToStamp(String s) {
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = simpleDateFormat.parse(s);
			long ts = date.getTime();
			res = String.valueOf(ts);
			return res;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

	}
	//转换为标准格式
	public static String timetoDate(long time1,int i) {
		String time=Long.toString(time1);//long-->String
		Long timeLong = Long.parseLong(time);
		SimpleDateFormat sdf=null;
		if(i==1) {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");//要转换的时间格式
		}else if(i==2){
			sdf = new SimpleDateFormat("yyyy");//要转换的时间格式
		}else if(i==3){
			sdf = new SimpleDateFormat("M");//要转换的时间格式yyyy-MM-dd HH:mm:ss
		}else if(i==4){
			sdf = new SimpleDateFormat("M.d");//要转换的时间格式yyyy-MM-dd HH:mm:ss
		}else if(i==5){
			sdf = new SimpleDateFormat("MM");//要转换的时间格式yyyy-MM-dd HH:mm:ss
		}else if(i==6){
			sdf = new SimpleDateFormat("yyyy-MM");//要转换的时间格式yyyy-MM-dd HH:mm:ss
		}
		Date date;

		try {
			date = sdf.parse(sdf.format(timeLong));
			return sdf.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

}


