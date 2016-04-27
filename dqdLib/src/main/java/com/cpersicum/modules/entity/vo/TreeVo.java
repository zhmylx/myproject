package com.cpersicum.modules.entity.vo;

import java.io.Serializable;

public class TreeVo implements Serializable {
	private boolean isLeaf = true;
	private boolean expanded = true;

	public boolean getIsLeaf() {
		return this.isLeaf;
	}

	public void setIsLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public boolean getExpanded() {
		return this.expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}
}

