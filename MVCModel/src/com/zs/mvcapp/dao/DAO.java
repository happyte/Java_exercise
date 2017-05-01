package com.zs.mvcapp.dao;

import java.util.List;

/**
 * 封装了基本的CRUD的方法，以供子类使用
 * @param <T>：当前DAO处理的实体类的类型是什么
 */
public class DAO<T> {
	private Class<T> clazz;  //不明白这个代表的具体含义?
	
	/**
	 * 返回某个字段的值，或返回数据表中记录的个数，E代表任何类型
	 * @param sql
	 * @param args
	 * @return
	 */
	public <E> E getForValue(String sql,Object ... args) {
		return null;
	}
	
	/**
	 * 返回对应T所对应的List
	 * @return
	 */
	public List<T> getForList(String sql,Object ... args) {
		return null;
	}
	
	/**
	 * 返回对应的T的一个实体类对象
	 * @param sql
	 * @param args
	 * @return
	 */
	public T get(String sql,Object ... args) {
		return null;
	}
	
	/**
	 * 该方法封装了INSERT,DELETE,UPDATE
	 * @param sql:SQL语句
	 * @param args:SQL语句绑定的占位符
	 */
	public void update(String sql,Object ... args) {
		
	}
}
