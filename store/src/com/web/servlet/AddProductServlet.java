package com.web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.constant.Constant;
import com.domain.Category;
import com.domain.Product;
import com.service.ProductService;
import com.utils.BeanFactory;
import com.utils.UUIDUtils;
import com.utils.UploadUtils;

/**
 * 添加商品
 */

public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
				// 0. 使用fileupload保存图片和将商品的信息放入map中
				// 0.1 创建map 存放商品的信息
				Map<String,Object> map = new HashMap<>();
				// 0.2 创建磁盘文件项工厂(设置临时文件的大小和位置)
				DiskFileItemFactory factory = new DiskFileItemFactory();
				//0.3 创建核心上传对象
				ServletFileUpload upload = new ServletFileUpload(factory);
				// 0.4 解析request
				List<FileItem> list = upload.parseRequest(request);
				// 0.5 遍历list 获取每一个文件项
				for (FileItem fi : list) {
					//0.6 获取name属性的值
					String key = fi.getFieldName();
					//0.7 判断是否是普通的上传组件
					if(fi.isFormField()) {
						//普通
						map.put(key, fi.getString());
					}else {
						//文件
						//a. 获取文件的名称 1.jpg
						String name = fi.getName();
						// b. 获取文件的真实名称 1.jpg
						String realName = UploadUtils.getRealName(name);
						//c. 获取文件的随机名称  32132.jpg
						String uuidName = UploadUtils.getUUIDName(realName);
						//d. 获取随机目录a/3
						String dir = UploadUtils.getDir();
						//e. 获取文件内容（输入流）
						InputStream is = fi.getInputStream();
						//f. 创建输出流
						//获取products目录的真实路径
						String productPath = getServletContext().getRealPath("/products");
						
						//创建随机目录
						File dirFile = new File(productPath,dir);
						if(!dirFile.exists()) {
							dirFile.mkdirs();
						}
						// d:/tomcat/webapps/store/product/a/s/43252345.jpg
						FileOutputStream os = new FileOutputStream(new File(dirFile,uuidName));
						//g. 对拷流
						IOUtils.copy(is, os);
						//h. 释放资源
						os.close();
						is.close();
						//i. 删除临时文件
						fi.delete();
						//j.将商品放入map中
						map.put(key, "products"+dir+"/"+uuidName);
					}
				}
  	 //封装product对象
				//创建product对象
				Product p = new Product();
				
				//手动封装pid
				p.setPid(UUIDUtils.getId());
				// 手动封装pdate
				p.setPdate(new Date(System.currentTimeMillis()));
				// 手动封装Pflag  上架
				p.setPflag(Constant.PRODUCT_IS_UP);
				//使用beanUtils封装
				BeanUtils.populate(p, map);
				
				//手动封装category
				Category cg = new Category();
				cg.setCid((String)map.get("cid"));
				p.setCategory(cg);
				BeanUtils.populate(p, map);
				//调用service完成保存
				ProductService ps = (ProductService) BeanFactory.getBean("ProductService");
				ps.save(p);
				//重定向
				response.sendRedirect(request.getContextPath()+"/adminProduct?method=findAll");
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("保存商品失败！");
			}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
