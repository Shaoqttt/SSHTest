package com.sqt.bean;

import java.util.List;

public class Page {
	private List<User> list; // Ҫ���ص�ĳһҳ�ļ�¼�б�
	private int allRow; // �ܼ�¼��
	private int totalPage; // ��ҳ��
	private int currentPage; // ��ǰҳ
	private int pageSize; // ÿҳ�ļ�¼��
	private boolean isFirstPage; // �Ƿ�Ϊ��ǰ��һҳ
	private boolean isLastPage; // �Ƿ�Ϊ���һҳ
	private boolean hasPreviousPage; // �Ƿ���ǰһҳ
	private boolean hasNextPage; // �Ƿ�����һҳ

	/**
	 * ��ʼ����ҳ��Ϣ
	 */
	public void init() {
		this.isFirstPage = isFirstPage;
		this.isLastPage = isLastPage;
		this.hasPreviousPage = hasPreviousPage;
		this.hasNextPage = hasNextPage;
	}

	/**
	 * ������ҳ�� ��̬����
	 * 
	 * @param pageSize ÿҳ�ļ�¼��
	 * @param allRow �ܼ�¼��
	 * @return ��ҳ��
	 */
	public static int countTatalPage(final int pageSize, final int allRow) {
		int toalPage = allRow % pageSize == 0 ? allRow / pageSize : allRow / pageSize + 1;
		return toalPage;
	}

	/**
	 * ���㵱ǰҳ��ʼ�ļ�¼
	 * 
	 * @param pageSize  ÿҳ��¼��
	 * @param currentPage  ��ǰ�ڼ�ҳ
	 * @return ��ǰҳ��ʼ��¼��
	 */
	public static int countOffset(final int pageSize, final int currentPage) {
		final int offset = pageSize * (currentPage - 1);
		return offset;
	}

	/**
	 * ���㵱ǰҳ����Ϊ0���������URL��û�С�?page = ������1����
	 * 
	 * @param page
	 *            ����Ĳ���������Ϊ�գ���0 �򷵻�1��
	 * @return
	 */
	public static int countCurrentPage(int page) {
		final int curpage = (page == 0 ? 1 : page);
		return curpage;
	}
	
	public List<User> getList() {
		return list;
	}

	public void setList(List<User> list) {
		this.list = list;
	}

	public int getAllRow() {
		return allRow;
	}

	public void setAllRow(int allRow) {
		this.allRow = allRow;
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

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public boolean isFirstPage() {
		return isFirstPage;
	}

	public void setFirstPage(boolean isFirstPage) {
		this.isFirstPage = isFirstPage;
	}

	public boolean isLastPage() {
		return isLastPage;
	}

	public void setLastPage(boolean isLastPage) {
		this.isLastPage = isLastPage;
	}

	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}

	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

}
