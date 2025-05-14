package com.example.test.Model;

public class ResourceFile {
	private Long resourceFileId;
	private String resourceFileName;
	private Long itemId;

	
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public String getResourceFileName() {
		return resourceFileName;
	}
	public void setResourceFileName(String resourceFileName) {
		this.resourceFileName = resourceFileName;
	}
	public Long getResourceFileId() {
		return resourceFileId;
	}
	public void setResourceFileId(Long resourceFileId) {
		this.resourceFileId = resourceFileId;
	}
	
}
