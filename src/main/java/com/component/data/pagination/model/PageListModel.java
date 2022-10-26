package com.component.data.pagination.model;

import javax.swing.*;

/**
 * 分页的数据模型
 */
public class PageListModel extends DefaultListModel<Integer> {
	public static final int OMIT = -1;
	/** 数据总量 */
	private int total;
	/** 每页数量，就只有计算总页数时用到。默认是 5 */
	private int pageSize = 5;
	/** 总页数 */
	private int totalPage;
	/** 总页数超过多少页时，折叠多余的页码按钮。应该为奇数，且最小为 5。默认是 7 */
	private int pageCount = 7;
	/**
	 * 所选单元格索引值。
	 * <p>
	 * 这里没有把JList传入，为了减少耦合，但也许那样会更简单
	 */
	private int selectedIndex = 0;

	public PageListModel(int total) {
		this.total = total;
		totalPage = total % pageSize == 0 ? total / pageSize : (total / pageSize + 1);
		addRangeElement((pageCount + 1) / 2);
	}

	/**
	 * @param total     数据总量
	 * @param pageSize  每页数量，就只有计算总页数时用到。默认是 5
	 * @param pageCount 总页数超过多少页时，折叠多余的页码按钮。应该为奇数，且最小为 5。默认是 7
	 */
	public PageListModel(int total, int pageSize, int pageCount) {
		this.total = total;
		this.pageSize = pageSize;
		if (!(pageCount > 4 && pageCount % 2 == 1))
			throw new IllegalArgumentException("pageCount 应该为大于等于 5 的奇数");
		this.pageCount = pageCount;
		totalPage = total % pageSize == 0 ? total / pageSize : (total / pageSize + 1);
		addRangeElement((pageCount + 1) / 2);
	}

	/**
	 * 分页插件不应该调用该方法
	 */
	@Override
	public Integer get(int index) {
		return super.get(index);
	}

	/**
	 * 分页插件不应该调用该方法
	 */
	@Override
	public Integer getElementAt(int index) {
		return super.getElementAt(index);
	}

	public int getSelectedIndex() {
		return selectedIndex;
	}

	/**
	 * 对分页单元格的选择进行处理
	 *
	 * @param selectedIndex 所选单元格的索引值
	 * @return 重新排列后所选值的新索引位置
	 */
	public int setSelectedIndex(int selectedIndex) {
		// 数据个数不足折叠显示时，不进行处理
		if (totalPage <= pageCount) return this.selectedIndex = selectedIndex;

		boolean isBegin = isBegin();
		boolean isEnd = isEnd();
		int leftMid = (pageCount - 1) / 2;
		int mid = (pageCount + 1) / 2;

		this.selectedIndex = selectedIndex;
		Integer e = getElementAt(selectedIndex); //点击的数
		if (e == 1) { // 第一个
			if (!isBegin) addRangeElement(mid);
			return this.selectedIndex = 0;
		} else if (e == totalPage) { //最后一个
			if (!isEnd) addRangeElement(totalPage - leftMid);
			return this.selectedIndex = selectedIndex;
		} else if (e == OMIT) {//省略号
			if (selectedIndex == 1) {//左边
				addRangeElement(Math.max(isEnd ?
						getElementAt(2)
						: getElementAt(mid) - 5, mid));
				if (isBegin()) return 0;
				return this.selectedIndex = isEnd() ? pageCount : mid;
			} else {//右边
				addRangeElement(Math.min(isBegin ?
								getElementAt(pageCount - 2)
								: getElementAt(mid) + 5,
						totalPage - leftMid));

				if (isEnd()) //跑到最后一个了
					return this.selectedIndex = pageCount;
				return this.selectedIndex = isBegin() ? 0 : mid;
			}
		} else { //省略号中间的部分
			// 当前在第一轮并且点击的是中位数和其左边的数，不进行处理
			if (isBegin && selectedIndex <= leftMid) return this.selectedIndex = selectedIndex;
			// 当前在最后一轮并且点击的是中位数和其右边的数，不进行处理
			if (isEnd && selectedIndex >= mid) return this.selectedIndex = selectedIndex;

			addRangeElement(Math.min(Math.max(e, mid), totalPage - leftMid));

			if (isBegin() || isEnd()) return this.selectedIndex = selectedIndex;
			return this.selectedIndex = mid;
		}
	}

	/** 上一页 */
	public int lastPage() {
		selectedIndex = Math.max(selectedIndex - 1, 0);
		if (selectedIndex <= (pageCount - 1) / 2) {
			addRangeElement(Math.max(getElementAt(selectedIndex), (pageCount + 1) / 2));
			boolean flag = !isBegin() && !isEnd();
			if (flag) selectedIndex++;
		}

		return selectedIndex;
	}

	/** 下一页 */
	public int nextPage() {
		selectedIndex = Math.min(selectedIndex + 1, Math.min(pageCount, totalPage - 1));
		if (selectedIndex >= (pageCount + 1) / 2) {
			boolean flag = !isEnd() && !isBegin();
			int leftMid = (pageCount - 1) / 2;
			addRangeElement(Math.min(getElementAt(selectedIndex), totalPage - leftMid));
			if (flag) selectedIndex--;
		}

		return selectedIndex;
	}

	/** 是否在初始位置 */
	public boolean isBegin() {
		return getElementAt(1) == 2;
	}

	/** 是否在最终位置 */
	public boolean isEnd() {
		return getElementAt(Math.min(pageCount, totalPage) - 1) == totalPage - 1;
	}

	/**
	 * 重新填充model数据
	 *
	 * @param mid model数据的中间值。注意是页码值，不是索引值
	 */
	private void addRangeElement(int mid) {
		removeAllElements();
		// 总页数不足折叠时
		if (totalPage <= pageCount) {
			for (int i = 1; i <= totalPage; i++) addElement(i);
			return;
		}

		addElement(1);
		if (mid > (pageCount + 1) / 2) addElement(OMIT);

		for (int i = -(pageCount - 3) / 2, len = -i; i <= len; i++) {
			addElement(mid + i);
		}

		if (mid <= totalPage - (pageCount + 1) / 2) addElement(OMIT);
		addElement(totalPage);

	}

	/** 添加一个数据。如果操作使得列表长度增加，别忘了调用updateUI() */
	public void addData() {
		addData(1);
	}

	/** 添加指定个数的数据。如果操作使得列表长度增加，别忘了调用updateUI() */
	public void addData(int count) {
		total += count;

		//不能为负数
		if (total < 0) total = 0;

		int newTotalPage = total % pageSize == 0 ? total / pageSize : (total / pageSize + 1);
		if (newTotalPage != totalPage) setTotalPage(newTotalPage);
	}

	/** 移除一个数据 */
	public void removeData() {
		removeData(1);
	}

	/** 移除指定个数的数据 */
	public void removeData(int count) {
		addData(-count); //符号一换还能用
	}

	/**
	 * 获取数据总量
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * 设置数据总量
	 */
	public void setTotal(int total) {
		int newTotalPage = total % pageSize == 0 ? total / pageSize : (total / pageSize + 1);
		if (newTotalPage == totalPage + 1) setTotalPage(newTotalPage);
	}

	/**
	 * 每页数量
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 获取总页数
	 */
	public int getTotalPage() {
		return totalPage;
	}

	/**
	 * 总页数超过多少页时，折叠多余的页码按钮
	 */
	public int getPageCount() {
		return pageCount;
	}

	/**
	 * 设置总页数
	 */
	public void setTotalPage(int totalPage) {
		if (totalPage < this.totalPage) selectedIndex = 0;
		this.totalPage = totalPage;
		addRangeElement((pageCount + 1) / 2);
	}

	/**
	 * 设置总页数超过多少页时，折叠多余的页码按钮。并且 pageCount 应该为大于等于 5 的奇数
	 */
	public void setPageCount(int pageCount) {
		if (!(pageCount > 4 && pageCount % 2 == 1))
			throw new IllegalArgumentException("pageCount 应该为大于等于 5 的奇数");
		this.pageCount = pageCount;
		addRangeElement((pageCount + 1) / 2);
	}
}
