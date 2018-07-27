package com.pesystem.dao;

import java.util.List;

import com.pesystem.entity.SC;

public interface SCDao {
	/**
	 * 
	 * @param sc
	 * @return
	 */
	public boolean delete(SC sc);

	/**
	 * ≤Â»Î
	 * @param tester
	 * @return
	 */
	public boolean insert(List<SC> scs);

	/**
	 * ≤È—Ø
	 * 
	 * @param condition
	 */
	public List<SC> select(String condition);

}
