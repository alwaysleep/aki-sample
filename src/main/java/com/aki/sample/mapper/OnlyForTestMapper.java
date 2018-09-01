package com.aki.sample.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aki.sample.domain.OnlyForTest;

@Repository
public interface OnlyForTestMapper {
	public Integer insert(OnlyForTest entity);
	public List<OnlyForTest> query(OnlyForTest entity);
	public int delete(Integer id);
}
