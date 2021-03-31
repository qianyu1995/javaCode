package com.higanbana;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.poi.excel.BigExcelWriter;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.StyleSet;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;

import java.util.List;

/**
 * @author 陈明
 * @date 2020/6/18 14:20
 */
public abstract class Application
{
	public abstract void say();
	
	
	
	public static void main(String[] args)
	{
		
		new Thread("test")
		{
			@Override
			public void run()
			{
				System.out.println(Thread.currentThread().getName());
				System.out.println("hello world!");
			}
		}.start();
		
	}
}
