package com.digou.service;

import com.digou.common.ResponseCommon;
import com.digou.entity.*;
import com.digou.mapper.SellerMapper;
import org.omg.CORBA.BAD_INV_ORDER;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
										String password,
										String email) {
		//检查
    	if (sellerMapper.findUser(telephone) != null) {
    		return ResponseCommon.wrappedResponse(null, 102, null);
		}
		//插入
    	SellerUser user = new SellerUser(url, telephone, shopName, nickname, description, major, password,email);
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

	public Map<String, Object> info_modify(HttpServletResponse response,
										   int id, String url, String telephone, String shopName,
										   String major, String description, String nickname, String email) {
		//插入
		SellerUser user = new SellerUser(url, telephone, shopName, nickname, description, major, "", email);
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


		sort_order(orders);
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
		long time_now_to_create=0, time_30d_ago=0, time_20w_ago=0, time_13m_ago=0, time_5y_ago=0;
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
			 * 判断订单状态为已完成后，再计算收入
			 */
			if(orders_price.get(i).isFinish!=2){
				continue;
			}
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
			time_30d_ago = Time_30d_ago(time,29);
			time_20w_ago = Time_20w_ago(time,19);
			time_13m_ago=Time_13m_ago(time,12);//向前12个月，就是最近13个月
			time_5y_ago=Time_5y_ago(time,4);//现在2019  四年前2015

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
				if( (orders_price.get(i).createTime >= Time_13m_ago(time,0)) &&(orders_price.get(i).createTime < time) ){
					j=12;
				}else {
					for (int k = 1; k <= 12; k++) {
						if ((orders_price.get(i).createTime >= Time_13m_ago(time, k)) && (orders_price.get(i).createTime < Time_13m_ago(time, k - 1))) {
							j = 12 - k;
						}
					}
				}
				my.this_month_income[j] += profit;
			}

			//如果在最近5年内
			if(orders_price.get(i).createTime > time_5y_ago){
				int j=0;
				if ((orders_price.get(i).createTime >= Time_5y_ago(time,0)) &&(orders_price.get(i).createTime < time)){
					j=4;
				}else {
					for (int k = 1; k <= 4; k++) {

						if ((orders_price.get(i).createTime >= Time_5y_ago(time, k)) && (orders_price.get(i).createTime < Time_5y_ago(time, k - 1))) {
							j = 4 - k;
						}
					}
				}
				my.this_year_income[j] += profit;
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

		int yea=Integer.parseInt(timetoDate(time,2));
		for (int j = 4; j >= 0 ; j--) {
			my.year_Date[j] = yea;
			yea--;
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

	//物流
	public Map<String, Object> logistics_select(HttpServletResponse response, int orderId,String company,String point) {
		sellerMapper.select_logistics(orderId,company,point);
		//System.out.println("this is a test!");
		return ResponseCommon.wrappedResponse(null, 101, null);
	}

	//申请广告
	public Map<String, Object> apply_advertises(HttpServletResponse response, int sId,int pId){
		sellerMapper.apply_ads(sId,pId);
		return ResponseCommon.wrappedResponse(null, 101, null);
	}

	//显示评论
	public Map<String,Object> comment(HttpServletResponse response, int pId){
		ArrayList<Comment> comments = sellerMapper.comment(pId);
		for (int i = 0; i < comments.size(); i++) {
			comments.get(i).Date=timetoDate(comments.get(i).createTime,1);
		}

		Map<String, Object> data = new HashMap<>();
		data.put("comment",comments);
		return ResponseCommon.wrappedResponse(data, 101, null);
	}

	//查看订单历史
	public Map<String,Object> history(HttpServletResponse response, int date,int sId){
		ArrayList<Order> all_orders = sellerMapper.allOrder(sId);
		ArrayList<Order> need_orders = new ArrayList<Order>();
		//获取当前时间精确到毫秒级的时间戳，例：1525849325942
		long time = System.currentTimeMillis();
		long Time = time;
		long day=24*60*60*1000L;

		long dayly_time=0, weekly_time=0, monthly_time=0, yearly_time=0;
		dayly_time = Time_30d_ago(time,0);
		weekly_time  = Time_20w_ago(time,0);
		monthly_time = Time_13m_ago(time,0);
		yearly_time =Time_5y_ago(time ,0);
			//day
		if (date == 1){
			for (int i = 0; i < all_orders.size() ; i++) {
				if (all_orders.get(i).createTime >= dayly_time){
					all_orders.get(i).Date = timetoDate(all_orders.get(i).createTime,1);
					need_orders.add(all_orders.get(i));
				}
			}
			//week
		}else if(date == 7){
			for (int i = 0; i < all_orders.size() ; i++) {
				if (all_orders.get(i).createTime >= weekly_time){
					all_orders.get(i).Date = timetoDate(all_orders.get(i).createTime,1);
					need_orders.add(all_orders.get(i));
				}
			}
			//month
		}else if(date == 30){
			for (int i = 0; i < all_orders.size() ; i++) {
				if (all_orders.get(i).createTime >= monthly_time){
					all_orders.get(i).Date = timetoDate(all_orders.get(i).createTime,1);
					need_orders.add(all_orders.get(i));
				}
			}
			//year
		}else if(date == 365){
			for (int i = 0; i < all_orders.size() ; i++) {
				if (all_orders.get(i).createTime >= yearly_time){
					all_orders.get(i).Date = timetoDate(all_orders.get(i).createTime,1);
					need_orders.add(all_orders.get(i));
				}
			}
		}else{
			System.out.println("输入正确格式/sellersservice   /查看订单历史");
		}

		sort_order(need_orders);
		Map<String, Object> data = new HashMap<>();
		data.put("history", need_orders);
		return ResponseCommon.wrappedResponse(data, 101, null);
	}

	//订单历史下search
	public Map<String,Object> search_order(HttpServletResponse response, int sId,long start,long end){
	    ArrayList<Order> all_order=sellerMapper.search(sId);

	    System.out.println(timetoDate(start,1));
		System.out.println(timetoDate(end,1));
		ArrayList<Order> time_order=new ArrayList<Order>();
		for (int i = 0; i < all_order.size() ; i++) {
			if ((all_order.get(i).createTime >= start) && (all_order.get(i).createTime < end)){
				all_order.get(i).Date = timetoDate(all_order.get(i).createTime,1);
				time_order.add(all_order.get(i));
			}
		}

		sort_order(time_order);
        Map<String, Object> data = new HashMap<>();
        data.put("an_order",time_order);
        return ResponseCommon.wrappedResponse(data, 101, null);
    }
	/**
	 * 以下为辅助函数部分
	 */
	//为订单按照时间顺序排序
	public void sort_order(ArrayList<Order> order){
		order.sort(new Comparator<Order>() {
			@Override
			public int compare(Order o1, Order o2) {
				if (o1.createTime >= o2.createTime){
					return -1;//-1代表放在前面
				}
				return 1;
			}
		});
	}
	//k年前的准确时间
	public static long Time_5y_ago(long time,int k){
		long new_time=time;
		String now_date=timetoDate(time,2);//获取年份
		int i=Integer.parseInt(now_date);//转换类型计算

		i -= k;
		now_date=String.valueOf(i)+"-01-01 00:00:00";
		new_time=Long.parseLong(dateToStamp(now_date));

		return new_time;
	}
	//k月前的准确时间
	public static long Time_13m_ago(long time,int k) {
		//float yea_month,year;
		long new_time;
		String new_date, now_date;
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
	public static long Time_20w_ago(long time ,int w){

		long day= 24*60*60*1000L;
		long k,new_time_mid,monday;
		k = time - 1546185600151L;
		new_time_mid = ( k )%(7*day);
		monday = time - new_time_mid;
		return  monday - w*7*day;
	}
	//30天前的准确时间
	public static long Time_30d_ago(long time ,int d){

		long day= 24*60*60*1000L;
		long k,new_time_mid,today;
		k = time - 1546185600151L;
		new_time_mid = ( k )%(day);
		today = time - new_time_mid;
		return  today - d*day;
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


