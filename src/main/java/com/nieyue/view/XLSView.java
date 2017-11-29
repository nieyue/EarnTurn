package com.nieyue.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.nieyue.bean.FinanceDetailsAcountDTO;
import com.nieyue.util.DateUtil;


@Component("xlsView")
public class XLSView extends AbstractXlsView{
	private String fileName;
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");  
        response.setContentType("application/vnd.ms-excel");  
        String fName = fileName+".xls";  
//      response.setContentType("APPLICATION/OCTET-STREAM");    
        response.setHeader("Content-Disposition", "inline; filename="+new String(fName.getBytes(),"iso8859-1")); 
        
			List<FinanceDetailsAcountDTO> sheetList=(List<FinanceDetailsAcountDTO>)model.get("financeDetailsAcountDTOList");
			sheetList.forEach(System.out::println);
			Font f = workbook.createFont();      
			CellStyle dateStyle = workbook.createCellStyle();
			f.setFontHeightInPoints((short)11);;// 字号   
			f.setBoldweight(Font.BOLDWEIGHT_NORMAL);// 加粗   
			
			dateStyle.setFont(f);      
			dateStyle.setAlignment(CellStyle.ALIGN_CENTER);// 左右居中   
			dateStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 上下居中   
			dateStyle.setRotation((short) 0);;// 单元格内容的旋转的角度  
			
            Sheet sheet =  workbook.createSheet("提现列表");
            Row headrow = sheet.createRow(0);
            Cell headrowcell0 = headrow.createCell(0);
            headrowcell0.setCellValue("序号");
            Cell headrowcell1 = headrow.createCell(1);
            headrowcell1.setCellValue("任务类型");
            Cell headrowcell2 = headrow.createCell(2);
            headrowcell2.setCellValue("任务金额");
            Cell headrowcell3 = headrow.createCell(3);
            headrowcell3.setCellValue("任务状态");
            Cell headrowcell4 = headrow.createCell(4);
            headrowcell4.setCellValue("任务提交时间");
            Cell headrowcell5 = headrow.createCell(5);
            headrowcell5.setCellValue("昵称");
            Cell headrowcell6 = headrow.createCell(6);
            headrowcell6.setCellValue("真实姓名");
            Cell headrowcell7 = headrow.createCell(7);
            headrowcell7.setCellValue("电话");
            Cell headrowcell8= headrow.createCell(8);
            headrowcell8.setCellValue("微信号");
            Cell headrowcell9 = headrow.createCell(9);
            headrowcell9.setCellValue("支付宝账号");
            Cell headrowcell10 = headrow.createCell(10);
            headrowcell10.setCellValue("任务人状态");
            for (int i = 0; i <sheetList.size(); i++) {
            	FinanceDetailsAcountDTO financeDetailsAcountDTO = sheetList.get(i);
                Row row = sheet.createRow(i+1);
                
                row.createCell(0).setCellValue(i+1);
                if(financeDetailsAcountDTO.getFinanceDetails().getType().equals(0)){
                	row.createCell(1).setCellValue("提现");
                }else if(financeDetailsAcountDTO.getFinanceDetails().getType().equals(1)){
                	row.createCell(1).setCellValue("充值");
                }
                row.createCell(2).setCellValue(financeDetailsAcountDTO.getFinanceDetails().getMoney());
                row.createCell(3).setCellValue(financeDetailsAcountDTO.getFinanceDetails().getStatus());
                row.createCell(4).setCellValue(DateUtil.dateFormatSimpleDate(financeDetailsAcountDTO.getFinanceDetails().getUpdateDate(),"yyyy-MM-dd HH:mm:ss"));
                row.createCell(5).setCellValue(financeDetailsAcountDTO.getAcount().getNickname());
                row.createCell(6).setCellValue(financeDetailsAcountDTO.getAcount().getRealname());
                row.createCell(7).setCellValue(financeDetailsAcountDTO.getAcount().getPhone());
                row.createCell(8).setCellValue(financeDetailsAcountDTO.getAcount().getWechat());
                row.createCell(9).setCellValue(financeDetailsAcountDTO.getAcount().getAlipay());
                if(financeDetailsAcountDTO.getAcount().getStatus().equals(0)){
                	row.createCell(10).setCellValue("正常");
                }else if(financeDetailsAcountDTO.getAcount().getStatus().equals(1)){
                	row.createCell(10).setCellValue("锁定");
                }else if(financeDetailsAcountDTO.getAcount().getStatus().equals(2)){
                	row.createCell(10).setCellValue("异常");
                }
            }
	}
			


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}