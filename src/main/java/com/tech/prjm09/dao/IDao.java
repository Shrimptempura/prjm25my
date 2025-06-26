package com.tech.prjm09.dao;

import java.sql.Connection;
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
	void reply(String bid, String bname, String btitle, String bcontent, 
			String bgroup, String bstep, String bindent);
	int replyShape(String strgroup, String strstep);
	void delete(String bid);
	void upHit(String sbid);



}
