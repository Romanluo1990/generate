package com.vip.xpf.model;

import java.util.Date;

public interface Identity {

	Long getId();

	void setId(Long id);

	Boolean getIsDeleted();

	void setIsDeleted(Boolean isDeleted);

	void setCreateTime(Date createTime);

	Date getCreateTime();
}
