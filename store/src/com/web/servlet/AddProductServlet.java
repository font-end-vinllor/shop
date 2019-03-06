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
 * �����Ʒ
 */

public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
				// 0. ʹ��fileupload����ͼƬ�ͽ���Ʒ����Ϣ����map��
				// 0.1 ����map �����Ʒ����Ϣ
				Map<String,Object> map = new HashMap<>();
				// 0.2 ���������ļ����(������ʱ�ļ��Ĵ�С��λ��)
				DiskFileItemFactory factory = new DiskFileItemFactory();
				//0.3 ���������ϴ�����
				ServletFileUpload upload = new ServletFileUpload(factory);
				// 0.4 ����request
				List<FileItem> list = upload.parseRequest(request);
				// 0.5 ����list ��ȡÿһ���ļ���
				for (FileItem fi : list) {
					//0.6 ��ȡname���Ե�ֵ
					String key = fi.getFieldName();
					//0.7 �ж��Ƿ�����ͨ���ϴ����
					if(fi.isFormField()) {
						//��ͨ
						map.put(key, fi.getString());
					}else {
						//�ļ�
						//a. ��ȡ�ļ������� 1.jpg
						String name = fi.getName();
						// b. ��ȡ�ļ�����ʵ���� 1.jpg
						String realName = UploadUtils.getRealName(name);
						//c. ��ȡ�ļ����������  32132.jpg
						String uuidName = UploadUtils.getUUIDName(realName);
						//d. ��ȡ���Ŀ¼a/3
						String dir = UploadUtils.getDir();
						//e. ��ȡ�ļ����ݣ���������
						InputStream is = fi.getInputStream();
						//f. ���������
						//��ȡproductsĿ¼����ʵ·��
						String productPath = getServletContext().getRealPath("/products");
						
						//�������Ŀ¼
						File dirFile = new File(productPath,dir);
						if(!dirFile.exists()) {
							dirFile.mkdirs();
						}
						// d:/tomcat/webapps/store/product/a/s/43252345.jpg
						FileOutputStream os = new FileOutputStream(new File(dirFile,uuidName));
						//g. �Կ���
						IOUtils.copy(is, os);
						//h. �ͷ���Դ
						os.close();
						is.close();
						//i. ɾ����ʱ�ļ�
						fi.delete();
						//j.����Ʒ����map��
						map.put(key, "products"+dir+"/"+uuidName);
					}
				}
  	 //��װproduct����
				//����product����
				Product p = new Product();
				
				//�ֶ���װpid
				p.setPid(UUIDUtils.getId());
				// �ֶ���װpdate
				p.setPdate(new Date(System.currentTimeMillis()));
				// �ֶ���װPflag  �ϼ�
				p.setPflag(Constant.PRODUCT_IS_UP);
				//ʹ��beanUtils��װ
				BeanUtils.populate(p, map);
				
				//�ֶ���װcategory
				Category cg = new Category();
				cg.setCid((String)map.get("cid"));
				p.setCategory(cg);
				BeanUtils.populate(p, map);
				//����service��ɱ���
				ProductService ps = (ProductService) BeanFactory.getBean("ProductService");
				ps.save(p);
				//�ض���
				response.sendRedirect(request.getContextPath()+"/adminProduct?method=findAll");
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("������Ʒʧ�ܣ�");
			}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
