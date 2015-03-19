package com.common;

public class Page {
	
	
    private boolean hasPrePage;  
    private boolean hasNextPage;  
   
    private int totalSize;
    private int pageSize;  
    private int totalPage;  
    private int currentPage;   
  
    private boolean searchByOrderOfTime; 
    private boolean searchByName;
    private String name;
    @Override
	public String toString() {
		// TODO Auto-generated method stub
    	super.toString();
		String str =  "hashcode:" + Integer.toHexString(hashCode())+
			          "\nhasPrePage:"+hasPrePage+
		              "\nhasNextPage:"+hasNextPage+
		              "\npageSize:"+pageSize+
		              "\ntotalPage:"+totalPage+
		              "\ncurrentPage:"+currentPage+
		              "\nsearchByOrderOfTime:"+searchByOrderOfTime+
		              "\nsearchByName:"+searchByName+
		              "\nname:"+name;
		return str;
	}
	public boolean isHasPrePage() {
		searchByOrderOfTime = true;
		return hasPrePage;
	}
  
	public void setHasPrePage(boolean hasPrePage) {
		this.hasPrePage = hasPrePage;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	

	public boolean isSearchByOrderOfTime() {
		return searchByOrderOfTime;
	}

	public void setSearchByOrderOfTime(boolean searchByOrderOfTime) {
		this.searchByOrderOfTime = searchByOrderOfTime;
	}

	public boolean isSearchByName() {
		return searchByName;
	}

	public void setSearchByName(boolean searchByName) {
		this.searchByName = searchByName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		searchByName = true;
		this.name = name;
	}
	public int getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	
   
   
}
