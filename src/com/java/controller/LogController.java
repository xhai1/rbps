package com.java.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.entity.Attachment;
import com.java.entity.Log;
import com.java.service.AttachmentService;
import com.java.service.LogService;
import com.java.util.PropertiesUtil;
import com.java.util.TimeUtil;
import com.java.util.WriterUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("log")
public class LogController {

	private Log log;
	private Attachment attachment;
	private int page = 1;
	private int rows = 10;
	@Autowired
	private LogService<Log> logService;
	@Autowired
	private AttachmentService<Attachment> attachmentService;
	
	@RequestMapping("logIndex")
	public String index(){
		return "log";
	}
	
	@RequestMapping("logList")
	public void logList(HttpServletRequest request,HttpServletResponse response){
		try {
			page = Integer.parseInt(request.getParameter("page"));
			rows = Integer.parseInt(request.getParameter("rows"));
			log = new Log();
			log.setModule(request.getParameter("module"));
			log.setEnd(request.getParameter("end"));
			log.setOperation(request.getParameter("operation"));
			log.setPage((page-1)*rows);
			log.setRows(rows);
			log.setStart(request.getParameter("start"));
			log.setUserName(request.getParameter("userName"));
			List<Log> list = logService.findLog(log);
			int total = logService.countLog(log);
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("total",total );
			jsonObj.put("rows", list);
	        WriterUtil.write(response,jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("deleteLog")
	public void delLog(HttpServletRequest request,HttpServletResponse response) {
		JSONObject result=new JSONObject();
		try {
			String ids[] = request.getParameter("ids").split(",");
			for (String id : ids) {
				logService.deleteLog(Integer.parseInt(id));
			}	
			result.put("success", true);
			result.put("delNums", ids.length);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("errorMsg", "对不起，删除失败");
		}
		WriterUtil.write(response, result.toString());
	}
	
	@RequestMapping("downloadLog4j")
	public ResponseEntity<byte[]> downloadLog4j(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String path = PropertiesUtil.url;
		File file = new File(path);
		HttpHeaders headers = new HttpHeaders();
		String fileName = new String("log.log".getBytes("UTF-8"), "iso-8859-1");
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);
	}
	
	/**
	 * 备份
	 */
	@RequestMapping("backup")
	public void backup(HttpServletRequest request,HttpServletResponse response){
		JSONObject result = new JSONObject();
		try {
			String time = TimeUtil.formatTime(new Date(), "yyyyMMddHHmmss");
		    String excelName = "手动备份"+time;
			log = new Log();
			log.setEnd(time);
			List<Log> list = logService.findLog(log);
			String[] handers = {"序号","操作人","IP地址","操作时间","操作模块","操作类型","详情"};
			// 1导入硬盘
			ExportExcelToDisk(request,handers,list, excelName);
			// 2导出的位置放入attachment表
			attachment = new Attachment();
			attachment.setAttachmentName(excelName+".xls");
			attachment.setAttachmentPath("log/backup");
			attachment.setAttachmentTime(TimeUtil.formatTime(new Date(), "yyyy-MM-dd HH:mm:ss"));
			attachmentService.insertAttachment(attachment);
			// 3删除log表
			logService.truncateLog();
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("errorMsg", "对不起，备份失败");
		}
		WriterUtil.write(response, result.toString());
	}
	
	// 导出到硬盘
		@SuppressWarnings("deprecation")
		private void ExportExcelToDisk(HttpServletRequest request,
				String[] handers, List<Log> list, String excleName) {
			
			try {
				HSSFWorkbook wb = new HSSFWorkbook();//创建工作簿
				HSSFSheet sheet = wb.createSheet("操作记录备份");//第一个sheet
				HSSFRow rowFirst = sheet.createRow(0);//第一个sheet第一行为标题
				rowFirst.setHeight((short) 500);
				for (int i = 0; i < handers.length; i++) {
					sheet.setColumnWidth((short) i, (short) 4000);// 设置列宽
				}
				//写标题了
				for (int i = 0; i < handers.length; i++) {
				    //获取第一行的每一个单元格
				    HSSFCell cell = rowFirst.createCell(i);
				    //往单元格里面写入值
				    cell.setCellValue(handers[i]);
				}
				for (int i = 0;i < list.size(); i++) {
				    //获取list里面存在是数据集对象
				    log = list.get(i);
				    //创建数据行
				    HSSFRow row = sheet.createRow(i+1);
				    //设置对应单元格的值
				    row.setHeight((short)400);   // 设置每行的高度
				    //"序号","操作人","IP地址","操作时间","操作模块","操作类型","详情"
				    row.createCell(0).setCellValue(i+1);
				    row.createCell(1).setCellValue(log.getUserName());
				    row.createCell(2).setCellValue(log.getIp());
				    row.createCell(3).setCellValue(log.getCreateTime());				    
				    row.createCell(4).setCellValue(log.getModule());
				    row.createCell(5).setCellValue(log.getOperation());
				    row.createCell(6).setCellValue(log.getContent());
				}
				//写出文件（path为文件路径含文件名）
					OutputStream os;
					os = new FileOutputStream(new File(request.getSession().getServletContext().getRealPath("/")+"log"+File.separator+"backup"+File.separator+excleName+".xls"));
					wb.write(os);
					os.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
}
