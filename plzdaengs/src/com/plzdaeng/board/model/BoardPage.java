package com.plzdaeng.board.model;

public class BoardPage {
	public static final int BLOCK_SCALE = 10;
	
	private int curPage;
	private int prevPage;
	private int nextPage;
	private int totalPage;
	private int totalBlock;
	private int curBlock;
	private int prevBlock;
	private int nextBlock;
	
	private int pageBegin;
	private int pageEnd;
	private int blockBegin;
	private int blockEnd;
	private int pageScale;
	
	//갯수, 현재페이지
	public BoardPage(int cnt, int curPage, int pageScale) {
		curBlock = 1;
		this.curPage = curPage;
		this.pageScale = pageScale;
		setTotalPage(cnt);
		setPageRange();
		setTotalBlock();
		setBlockRange();
		
	}
	
	public void setTotalPage(int cnt) {
		totalPage = (int) Math.ceil(cnt*1.0 / pageScale);
	}
	
	public void setPageRange() {
		pageBegin = (curPage-1)*pageScale+1;
		pageEnd = pageBegin+pageScale-1;
	}
	
	public void setTotalBlock() {
		totalBlock = (int)Math.ceil(totalPage / BLOCK_SCALE);
	}
	
	public void setBlockRange() {
		curBlock = (int)Math.ceil((curPage - 1)/BLOCK_SCALE)+1;
		blockBegin = (curBlock-1) * BLOCK_SCALE+1;
		blockEnd = blockBegin + BLOCK_SCALE-1;
		
		if(blockEnd > totalPage) blockEnd = totalPage;
		
		prevPage = (curPage == 1) ? 1 : (curBlock-1)*BLOCK_SCALE;
		nextPage = curBlock > totalBlock ? (curBlock*BLOCK_SCALE) : (curBlock*BLOCK_SCALE)+1;
		
		if(nextPage >= totalPage) nextPage = totalPage;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPrevPage() {
		return prevPage;
	}

	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getTotalBlock() {
		return totalBlock;
	}

	public void setTotalBlock(int totalBlock) {
		this.totalBlock = totalBlock;
	}

	public int getCurBlock() {
		return curBlock;
	}

	public void setCurBlock(int curBlock) {
		this.curBlock = curBlock;
	}

	public int getPrevBlock() {
		return prevBlock;
	}

	public void setPrevBlock(int prevBlock) {
		this.prevBlock = prevBlock;
	}

	public int getNextBlock() {
		return nextBlock;
	}

	public void setNextBlock(int nextBlock) {
		this.nextBlock = nextBlock;
	}

	public int getPageBegin() {
		return pageBegin;
	}

	public void setPageBegin(int pageBegin) {
		this.pageBegin = pageBegin;
	}

	public int getPageEnd() {
		return pageEnd;
	}

	public void setPageEnd(int pageEnd) {
		this.pageEnd = pageEnd;
	}

	public int getBlockBegin() {
		return blockBegin;
	}

	public void setBlockBegin(int blockBegin) {
		this.blockBegin = blockBegin;
	}

	public int getBlockEnd() {
		return blockEnd;
	}

	public void setBlockEnd(int blockEnd) {
		this.blockEnd = blockEnd;
	}

	public int getTotalPage() {
		return totalPage;
	}

	@Override
	public String toString() {
		return "BoardPage [curPage=" + curPage + ", prevPage=" + prevPage + ", nextPage=" + nextPage + ", totalPage="
				+ totalPage + ", totalBlock=" + totalBlock + ", curBlock=" + curBlock + ", prevBlock=" + prevBlock
				+ ", nextBlock=" + nextBlock + ", pageBegin=" + pageBegin + ", pageEnd=" + pageEnd + ", blockBegin="
				+ blockBegin + ", blockEnd=" + blockEnd + "]";
	}

	
}
