package com.tida.manual.util;
class MykockL1 {
	
	//对于非公平锁修改,会执行改方法
	public boolean nonfairTryAcquire(int qcquires ) {
		final Thread current = Thread.currentThread();
		Thread.State c = current.getState();//获得状态量
		return false;
	}
}