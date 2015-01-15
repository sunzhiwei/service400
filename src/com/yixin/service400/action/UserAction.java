package com.yixin.service400.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.yixin.service400.base.ModelDrivenBaseAction;
import com.yixin.service400.bean.TService400Privilege;
import com.yixin.service400.bean.TService400User;
import com.yixin.service400.util.ComparatorImpl;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class UserAction extends ModelDrivenBaseAction<TService400User> {

	static Logger log = Logger.getLogger(UserAction.class);
	/** 登录 */	
	@SuppressWarnings("unchecked")
	public String login()  throws Exception{
		try {
			Map session = ActionContext.getContext().getSession();
			
//			HttpSession session2 = request.getSession(true);  
			// 查询
			TService400User user=null;
//			Map<String, Object> session3 = ActionContext.getContext().getSession();
			if (model != null && model.getUsername() != null && model.getPassword() != null)
				user= userService.getByLoginNameAndPassword(model.getUsername(), model.getPassword());
//			String strs = StringUtil.objToString( session2.getAttribute("authCode")); 
//			String mycode = StringUtil.objToString( request.getParameter("myCode"));
			if (user==null) {
				request.setAttribute("msg", "<font color='red'>请检查用户名和密码是否正确!</font>");
				return "tologin";
			} 
//		else if(!strs.equalsIgnoreCase(mycode)){
//			request.setAttribute("msg", "<font color='red'>请正确输入验证码!</font>");
//			return "tologin";
//		}
			else{ // user!=null && strs.equalsIgnoreCase(mycode)
				//用户id 
				Integer userid = user.getId().intValue();
				//用户的角色集合
				List<BigDecimal> rolelist = userService.userRoleidList(userid);
				session.put("personrole", rolelist);//将角色id集合存入session中
				if(rolelist.size()==0 || rolelist == null)
				{
					request.setAttribute("msg", "<font color='red'>对不起你没有权限进入该系统!</font>");
					return "tologin";
				}
				else
				{
				 //该用户角色对应的所有菜单id集合
				 List<BigDecimal> functlist = new ArrayList<BigDecimal>();
				 for (int i = 0; i < rolelist.size(); i++) {
						Integer roleid = rolelist.get(i).intValue();
						List<BigDecimal> list = userService.selectPrivilegeidByRoleid(roleid);
						functlist.addAll(list);
						
					}
				 
				   //为避免多角色之间有重叠的菜单权限，将list转成set
					Set<BigDecimal> functiset = new HashSet<BigDecimal>(functlist);
					
					Iterator<BigDecimal> it = functiset.iterator();
					List<TService400Privilege> listmenu1 = new ArrayList<TService400Privilege>();
					List<TService400Privilege> listmenu2 = new ArrayList<TService400Privilege>();
					List<TService400Privilege> findbylist1 = new ArrayList<TService400Privilege>();
					List<TService400Privilege> findbylist2 = new ArrayList<TService400Privilege>();
					//根据菜单id查询菜单对象
					while (it.hasNext()) {
						
						Integer menuid = it.next().intValue();
						listmenu1 = findByLevel(menuid,1);
						findbylist1.addAll(listmenu1);
						
						listmenu2 = findByLevel(menuid,2);
						findbylist2.addAll(listmenu2);
					}
					ComparatorImpl c1 = new ComparatorImpl();
					Collections.sort(findbylist1, c1);
					Collections.sort(findbylist2, c1);
					
					session.put("user", user);
					session.put("firstLelelMenus", findbylist1);
					session.put("secondLelelMenus", findbylist2);
					
					log.info(user.getUsername()+"登陆系统！");
					return "toIndex";
				}
			}
		} catch (Exception e) {
			log.error("登陆系统失败！");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 
	 * 描述：退出系统
	 * @version
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String exitLogin() throws Exception{
		Map session = ActionContext.getContext().getSession();
		if (session instanceof org.apache.struts2.dispatcher.SessionMap) {
			((org.apache.struts2.dispatcher.SessionMap) session).invalidate();
		}
		ServletActionContext.getRequest().getSession().invalidate();
		return "tologin";
	}
	
	public void test() throws Exception{
    	try {  
            int width = 50;  
            int height = 18;  
            // 取得一个4位随机字母数字字符串  
            String s = RandomStringUtils.random(4, true, true);  
            //System.err.println("字符："+s);
            
            // 保存入session,用于与用户的输入进行比较.  
            // 注意比较完之后清除session.  
            HttpSession session = request.getSession(true);  
            session.setAttribute("authCode", s);  
  
            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
	        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
	        response.setHeader("Cache-Control", "no-cache");
	        response.setDateHeader("Expire", 0);
  
            ServletOutputStream out = response.getOutputStream();  
            BufferedImage image = new BufferedImage(width, height,  
                    BufferedImage.TYPE_INT_RGB);  
            Graphics g = image.getGraphics();  
            // 设定背景色  
            g.setColor(getRandColor(200, 250));  
            g.fillRect(0, 0, width, height);  
  
            // 设定字体  
            Font mFont = new Font("Times New Roman", Font.BOLD, 18);// 设置字体  
            g.setFont(mFont);  
  
            // 画边框  
            // g.setColor(Color.BLACK);  
            // g.drawRect(0, 0, width - 1, height - 1);  
  
            // 随机产生干扰线，使图象中的认证码不易被其它程序探测到  
            g.setColor(getRandColor(160, 200));  
            // 生成随机类  
            Random random = new Random();  
            for (int i = 0; i < 155; i++) {  
                int x2 = random.nextInt(width);  
                int y2 = random.nextInt(height);  
                int x3 = random.nextInt(12);  
                int y3 = random.nextInt(12);  
                g.drawLine(x2, y2, x2 + x3, y2 + y3);  
            }  
  
            // 将认证码显示到图象中  
            g.setColor(new Color(20 + random.nextInt(110), 20 + random  
                    .nextInt(110), 20 + random.nextInt(110)));  
            g.drawString(s, 2, 16);  
            // 图象生效  
            g.dispose();  
            // 输出图象到页面  
            ImageIO.write(image, "JPEG", out);  
            out.close();  
            
           
        } catch (Exception e) {  
            e.printStackTrace();
            throw new RuntimeException(e);
        }  
    }
    
    private Color getRandColor(int fc, int bc) { // 给定范围获得随机颜色  
        Random random = new Random();  
        if (fc > 255)  
            fc = 255;  
        if (bc > 255)  
            bc = 255;  
        int r = fc + random.nextInt(bc - fc);  
        int g = fc + random.nextInt(bc - fc);  
        int b = fc + random.nextInt(bc - fc);  
        return new Color(r, g, b);  
    }  

    /**
     * 
     * 描述：根据级别查找菜单
     * @version
     * @param level级别
     * @return
     */
    private List<TService400Privilege> findByLevel(Integer menuid,Integer level) throws Exception{
    	List<TService400Privilege> menus=null;
		try {
			menus = privilegeService.findByLevel(menuid,level);
			log.info("查找菜单！");
		} catch (Exception e) {
			log.error("查找菜单失败！");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
    	return menus;
    }

}
