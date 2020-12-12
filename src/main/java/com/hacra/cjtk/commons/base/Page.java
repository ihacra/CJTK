package com.hacra.cjtk.commons.base;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hacra.cjtk.commons.util.StringUtils;

/**
 * Page
 * 
 * @author Hacra
 * @date 2020-12-07
 */
public class Page<T> {
	
	private int pageNo = 1;		// 当前页码
	private int pageSize = 10;	// 页面大小
	private int count;			// 总记录数
	private int totalPage;		// 总页数
	private List<T> list;		// 数据集合
	
	/**
	 * 获取当前页面
	 * @param request
	 * @param response
	 */
	public Page(HttpServletRequest request) {
		String no = request.getParameter("pageNo");
		if (StringUtils.isNumeric(no)) {
			this.setPageNo(Integer.parseInt(no));
		} else {
			this.setPageNo(1);
		}
	}

	/**
	 * 设置总页数
	 * @param count
	 */
	public void setCount(int count) {
		this.count = count;
		this.totalPage = count / this.pageSize + (count % this.pageSize == 0 ? 0 : 1);
		if (this.pageSize >= count) {
			pageNo = 1;
		}
	}
	
	/**
	 * 上一页
	 * @return
	 */
	public int getPrev() {
		if (pageNo > 1) {
			return pageNo - 1;
		} else if (pageNo < 1) {
			return 1;
		}
		return pageNo;
	}
	
	/**
	 * 下一页
	 * @return
	 */
	public int getNext() {
		if (pageNo < totalPage) {
			return pageNo + 1;
		} else if (pageNo > totalPage) {
			return totalPage;
		}
		return pageNo;
	}
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getCount() {
		return count;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public int getTotalPage() {
		return totalPage;
	}
}
