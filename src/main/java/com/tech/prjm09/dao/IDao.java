package com.tech.prjm09.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.tech.prjm09.dto.BDto;

@Mapper
public interface IDao {
	ArrayList<BDto> list();
	void write(String bname, String btitle, String bcontent);
	BDto contentView(String sbid);
	BDto modifyView(String sbid);
	void modify(String bid, String bname, String btitle, String bcontent);
	BDto reply_view(String sbid);



}
