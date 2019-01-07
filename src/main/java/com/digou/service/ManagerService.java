package com.digou.service;

import com.digou.common.MysqlDatabaseBackup;
import com.digou.common.ResponseCommon;
import com.digou.entity.*;
import com.digou.mapper.ManagerMapper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.digou.service.SellerService.*;

@ComponentScan({"com.digou.mapper"})
@Service("managerService")
public class ManagerService {
	
    @Resource
    private ManagerMapper managerMapper;

	public Map<String, Object> sellerPassInfo(HttpServletResponse response) {
        ArrayList<SellerUser> sellerUsers = managerMapper.sellerPassInfo();
        Map<String, Object> data = new HashMap<>();
        data.put("array", sellerUsers);
        return ResponseCommon.wrappedResponse(data, 101, null);
    }

	public Map<String, Object> sellerBlackInfo(HttpServletResponse response) {
        ArrayList<SellerUser> sellerUsers = managerMapper.sellerBlackInfo();
        Map<String, Object> data = new HashMap<>();
        data.put("array", sellerUsers);
        return ResponseCommon.wrappedResponse(data, 101, null);
    }

	public Map<String, Object> sellerWhiteInfo(HttpServletResponse response, String value) {
        ArrayList<SellerUser> sellerUsers = managerMapper.sellerWhiteInfo(value);
        Map<String, Object> data = new HashMap<>();
        data.put("array", sellerUsers);
        return ResponseCommon.wrappedResponse(data, 101, null);
    }

	public Map<String, Object> sellerBlackCancel(HttpServletResponse response, int id) {
        managerMapper.sellerBlackCancel(id);
        return ResponseCommon.wrappedResponse(null, 101, null);
    }

	public Map<String, Object> sellerWhiteBlock(HttpServletResponse response, int id) {
        managerMapper.sellerWhiteBlock(id);
        return ResponseCommon.wrappedResponse(null, 101, null);
    }

	public Map<String, Object> sellerWhiteInfo(HttpServletResponse response) {
        ArrayList<SellerUser> sellerUsers = managerMapper.sellerPassInfo();
        Map<String, Object> data = new HashMap<>();
        data.put("array", sellerUsers);
        return ResponseCommon.wrappedResponse(data, 101, null);
    }

	public Map<String, Object> sellerPassApprove(HttpServletResponse response, int id) {
        managerMapper.sellerUpdateApprove(id);
        return ResponseCommon.wrappedResponse(null, 101, null);
    }

	public Map<String, Object> sellerPassReject(HttpServletResponse response, int id) {
        managerMapper.sellerUpdateReject(id);
        return ResponseCommon.wrappedResponse(null, 101, null);
    }

    public Map<String, Object> customerBlackInfo(HttpServletResponse response) {
        ArrayList<CUser> customerUsers = managerMapper.customerBlackInfo();
        Map<String, Object> data = new HashMap<>();
        data.put("array", customerUsers);
        return ResponseCommon.wrappedResponse(data, 101, null);
    }
    public Map<String, Object> managerSearchCustomer(HttpServletResponse response, String value) {
        ArrayList<CUser> customerUsers = managerMapper.managerSearchCustomer(value);
        Map<String, Object> data = new HashMap<>();
        data.put("array", customerUsers);
        return ResponseCommon.wrappedResponse(data, 101, null);
    }
    public Map<String, Object> customerBlackCancel(HttpServletResponse response, int id) {
        managerMapper.customerBlackCancel(id);
        return ResponseCommon.wrappedResponse(null, 101, null);
    }

    public Map<String, Object> customerWhiteBlock(HttpServletResponse response, int id) {
        managerMapper.customerWhiteBlock(id);
        return ResponseCommon.wrappedResponse(null, 101, null);
    }
    public Map<String ,Object> allOrder(HttpServletResponse response)
    {
        ArrayList<Order> orders = managerMapper.allOrder();
        Map<String, Object> data = new HashMap<>();
        data.put("array", orders);
        return ResponseCommon.wrappedResponse(data, 101, null);
    }
        public Map<String,Object> searchOrderByTime(HttpServletResponse response,long start,long end){
	    ArrayList<Order> orders = managerMapper.searchOrderByTime(start,end);
        Map<String, Object> data = new HashMap<>();
        data.put("array", orders);

        return ResponseCommon.wrappedResponse(data, 101, null);

    }
    public Map<String, Object> profitRate(HttpServletResponse response) {
        float rate = managerMapper.profitRate();
        Map<String, Object> data = new HashMap<>();
        data.put("array", rate);
        return ResponseCommon.wrappedResponse(data, 101, null);
    }

    public Map<String,Object> changeProfitRate (HttpServletResponse response,float  rate)
    {
        managerMapper.changeProfitRate(rate);
        return  ResponseCommon.wrappedResponse(null, 101, null);
    }
    public Map<String, Object> searchOrderByID(HttpServletResponse response,int orderID) {
        ArrayList<Order> orders = managerMapper.searchOrderByID(orderID);
        Map<String, Object> data = new HashMap<>();
        data.put("order", orders);
        return ResponseCommon.wrappedResponse(data, 101, null);
    }
    public Map<String, Object> allSellerInfo(HttpServletResponse response) {
        ArrayList<SellerUser> sellerUsers = managerMapper.allSellerInfo();
        Map<String, Object> data = new HashMap<>();
        data.put("all", sellerUsers);
        return ResponseCommon.wrappedResponse(data, 101, null);
    }


    public Map<String, Object> sellerTop5Info(HttpServletResponse response) {
        ArrayList<SellerUser> sellerUsers = managerMapper.sellerTop5Info();
        Map<String, Object> data = new HashMap<>();
        data.put("top5", sellerUsers);
        return ResponseCommon.wrappedResponse(data, 101, null);
    }
    public Map<String, Object> sellerTop5InfoAds(HttpServletResponse response) {
        ArrayList<SellerUser> sellerUsers = managerMapper.sellerTop5InfoAds();
        ArrayList<SellerUser> sellerUsers1 =new ArrayList<>();
        int id=0;
        for(int i=0;i<sellerUsers.size();)
        {
            int n=1;
            id=sellerUsers.get(i).getId();
            SellerUser su =new SellerUser();
            su.products = new ArrayList<>();
            su.setId(id);
            su.setShopName(sellerUsers.get(i).getShopName());
            su.setMajor(sellerUsers.get(i).getMajor());
             while(i<sellerUsers.size()&&id==sellerUsers.get(i).getId() && n<=3 )
             {
                 su.products.add(sellerUsers.get(i).product);
                 n++;
                 i++;
             }
             while(i<sellerUsers.size()&&id==sellerUsers.get(i).getId())
                 i++;
            sellerUsers1.add(su);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("top5_ads", sellerUsers1);
        return ResponseCommon.wrappedResponse(data, 101, null);
    }

    public Map<String, Object> sellerToTop5(HttpServletResponse response, int id) {
        ArrayList<SellerUser> sellerUsers =managerMapper.sellerTop5Info();
        if(sellerUsers.size()>=5)
        {
            return ResponseCommon.wrappedResponse(null, 105, null);
        }
        managerMapper.sellerToTop5(id);
        return ResponseCommon.wrappedResponse(null, 101, null);
    }

    public Map<String, Object> sellerTop5Cancel(HttpServletResponse response, int id) {
        managerMapper.sellerTop5Cancel(id);
        return ResponseCommon.wrappedResponse(null, 101, null);
    }
    public Map<String, Object> productTop10Info(HttpServletResponse response) {

        ArrayList<Product> products = managerMapper.productTop10Info();
        Map<String, Object> data = new HashMap<>();
        data.put("top10", products);
        return ResponseCommon.wrappedResponse(data, 101, null);
    }

    public Map<String, Object> productToTop10(HttpServletResponse response, int id) {
        ArrayList<Product> products = managerMapper.productTop10Info();
        if(products.size()>=10)
        {
            return ResponseCommon.wrappedResponse(null, 105, null);
        }
        managerMapper.productToTop10(id);
        return ResponseCommon.wrappedResponse(null, 101, null);
    }

    public Map<String, Object> productTop10Cancel(HttpServletResponse response, int id) {
        managerMapper.productTop10Cancel(id);
        return ResponseCommon.wrappedResponse(null, 101, null);
    }
    public Map<String, Object> top5SellerApplyInfo(HttpServletResponse response) {
        ArrayList<SellerUser> sellerUsers = managerMapper.top5SellerApplyInfo();
        Map<String, Object> data = new HashMap<>();
        data.put("top5_apply", sellerUsers);
        return ResponseCommon.wrappedResponse(data, 101, null);
    }

    public Map<String, Object> top5SellerApplyReject(HttpServletResponse response, int id) {
        managerMapper.top5SellerApplyReject(id);
        return ResponseCommon.wrappedResponse(null, 101, null);
    }
    public Map<String, Object> top10ProductApplyInfo(HttpServletResponse response) {
        ArrayList<Product> products = managerMapper.top10ProductApplyInfo();
        Map<String, Object> data = new HashMap<>();
        data.put("top10_apply", products);
        return ResponseCommon.wrappedResponse(data, 101, null);
    }

    public Map<String, Object> top10ProductApplyReject(HttpServletResponse response, int id) {
        managerMapper.top10ProductApplyReject(id);
        return ResponseCommon.wrappedResponse(null, 101, null);
    }

    public void Download(HttpServletResponse res) {
        MysqlDatabaseBackup.Backup();
        String fileName = MysqlDatabaseBackup.fileName+".sql";

        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1000000];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File(MysqlDatabaseBackup.filePath)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("success");
    }
    public Map<String, Object> caculate_income(HttpServletResponse respons){
        long time_now_to_create=0, time_30d_ago=0, time_20w_ago=0, time_13m_ago=0, time_5y_ago=0;
        long day=24*60*60*1000L;
        float income_1=0,income_7=0,income_30=0,income_365=0,income_all=0;
        ArrayList<Order> orders_price = managerMapper.all_order_price_admin();
        ArrayList<income> all_income = new ArrayList<income>();
        float profit=0;
        income my=new income();

        //获取当前时间精确到毫秒级的时间戳，例：1525849325942
        long time = System.currentTimeMillis();
        long Time = time;
        for(int i=0;i<orders_price.size();i++) {
            time_now_to_create = time - orders_price.get(i).createTime;
            profit = orders_price.get(i).adminProfit;
            //计算收入
            /**
             * 记得之后改成 判断订单完成的钱算作收入
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

/*
	public Map<String, Object> info_get(HttpServletResponse response, int id) {
		//检查
		SellerUser sellerUser = sellerAccountMapper.findUserById(id);
    	if (sellerUser == null) {
    		return ResponseCommon.wrappedResponse(null, 102, null);
		} else {
			Map<String, Object> data = new HashMap<>();
			data.put("data", sellerUser);
			return ResponseCommon.wrappedResponse(data, 101, data);
		}
    }

	public Map<String, Object> info_modify(HttpServletResponse response, int id,String url,String telephone,String shopName,String major,String description,String nickname) {
		//插入
		SellerUser user = new SellerUser(url, telephone, shopName, nickname, description, major, "");
		user.setId(id);
		sellerAccountMapper.modifyUser(user);
		return ResponseCommon.wrappedResponse(null, 101, null);
	}

	public Map<String, Object> login(HttpServletResponse response, String telephone, String password) {
		SellerUser user = sellerAccountMapper.findUser(telephone);
		if (user == null || !user.getPassword().equals(password)) {
			return ResponseCommon.wrappedResponse(null, 102, null);
		}
		Map<String, Object> data = new HashMap<>();
		data.put("data", user);
		return ResponseCommon.wrappedResponse(data, 101, null);
	}*/
}
