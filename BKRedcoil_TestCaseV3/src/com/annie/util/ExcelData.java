package com.annie.util;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import jxl.*;

public class ExcelData implements Iterator<Object[]> {
	private Workbook book = null;
	private Sheet sheet = null;
	private int rowNum = 0;
	private int curRowNo = 0;
	private int columnNum = 0;
	private String[] columnnName;

	public ExcelData(String filepath, String casename) {
		try {
			File directory = new File(".");
			String ss = "open.anniewang.exceldata.";
			book = Workbook.getWorkbook(new File(directory.getCanonicalPath()
					+ "\\resources\\"
					+ ss.replaceAll("\\.", Matcher.quoteReplacement("\\"))
					+ filepath + ".xls"));
			this.sheet = book.getSheet(casename);
			this.rowNum = sheet.getRows();

			Cell[] c = sheet.getRow(0);
			this.columnNum = c.length;
			columnnName = new String[c.length];
			for (int i = 0; i < c.length; i++) {
				columnnName[i] = c[i].getContents().toString();
			}
			this.curRowNo++;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean hasNext() {

		if (this.rowNum == 0 || this.curRowNo >= this.rowNum) {
			try {
				book.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		} else
			return true;
	}

	@Override
	public Object[] next() {

		Cell[] c = sheet.getRow(this.curRowNo);
		Map<String, String> s = new TreeMap<String, String>();
		for (int i = 0; i < this.columnNum; i++) {
			String temp = "";
			try {
				temp = c[i].getContents().toString();
			} catch (ArrayIndexOutOfBoundsException ex) {
				temp = "";
			}
			s.put(this.columnnName[i], temp);
		}

		Object r[] = new Object[1];
		r[0] = s;
		this.curRowNo++;
		return r;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException("remove unsupported.");
	}
}
