package com.hacra.cjtk.commons.base;

import java.io.Serializable;
import java.util.Date;

/**
 * BaseEntity
 * 
 * @author Hacra
 * @date 2020-11-10
 */
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;			// id
	private Date createDate;	// 创建日期
	private Date updateDate;	// 更新日期
	private String delFlag;		// 删除标记（0：正常；1：删除）
	public static final String DEL_FLAG_NORMAL = "0";
	public static final String DEL_FLAG_DELETE = "1";
	
	public BaseEntity() {
		this.delFlag = DEL_FLAG_NORMAL;
	}
	
	public BaseEntity(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} 
		if (this == obj) {
			return true;
		}
		if (!this.getClass().equals(obj.getClass())) {
			return false;
		}
		BaseEntity that = (BaseEntity) obj;
		return this.getId() == null ? false : this.getId().equals(that.getId());
	}
}
