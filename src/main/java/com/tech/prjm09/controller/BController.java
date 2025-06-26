package com.tech.prjm09.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tech.command.BCommand;
import com.tech.command.BContentCommand;
import com.tech.command.BDeleteCommand;
import com.tech.command.BListCommand;
import com.tech.command.BModifyCommand;
import com.tech.command.BModifyViewCommand;
import com.tech.command.BReplyCommand;
import com.tech.command.BReplyViewCommand;
import com.tech.command.BWriteCommand;
import com.tech.prjm09.dao.IDao;
import com.tech.prjm09.dto.BDto;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BController {
	
	private BCommand command;
	private final IDao iDao;
	
	@Autowired
	public BController(IDao iDao) {
		this.iDao = iDao;
	}

	
	@RequestMapping("list")
	public String list(Model model) {
		System.out.println("list() ctr");
//		command = new BListCommand();
//		command.execute(model);
		
		// mybatis를 사용함에 따라, BListCommand 구현체 필요 없고, 
		// Bdao의 ArrayList<BDto> list() 메소드 자체도 날려도 상관없다.
		ArrayList<BDto> list = iDao.list();
		model.addAttribute("list", list);
		
		
		return "list";
	}
	
	@RequestMapping("write_view")
	public String write_view(Model model) {
		System.out.println("wirte_view() ctr");
		return "write_view";
	}
	
	@RequestMapping("write")
	public String write(HttpServletRequest request, Model model) {
		System.out.println("write() ctr");
		
		// db에 글쓰기 동작
//		model.addAttribute("request", request);
//		command = new BWriteCommand();
//		command.execute(model);
		
		String bname = request.getParameter("bname");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		iDao.write(bname, btitle, bcontent);
		
		return "redirect:list";
	}
	
	@GetMapping("content_view")
	public String content_view(HttpServletRequest request, Model model) {
		
		System.out.println("content_view() ctr");
		
		model.addAttribute("request", request);
		command = new BContentCommand();
		command.execute(model);
		
		return "content_view";
	}
	
	@GetMapping("modify_view")
	public String modify_view(HttpServletRequest request, Model model) {
		System.out.println("modify_view() ctr");
		
		model.addAttribute("request", request);
		command = new BModifyViewCommand();
		command.execute(model);
		
		return "modify_view";
	}
	
	@PostMapping("modify")
	public String modify(HttpServletRequest request, Model model) {
		System.out.println("modify() ctr");
		
		model.addAttribute("request", request);
		command = new BModifyCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	@GetMapping("reply_view")
	public String reply_view(HttpServletRequest request, Model model) {
		System.out.println("reply_view() ctr");
		
		model.addAttribute("request", request);
		command = new BReplyViewCommand();
		command.execute(model);
		
		return "reply_view";
	}
	
	@PostMapping("reply")
	public String reply(HttpServletRequest request, Model model) {
		System.out.println("reply() ctr");
		
		model.addAttribute("request", request);
		command = new BReplyCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("delete")
	public String delete(HttpServletRequest request, Model model) {
		System.out.println("delete ctr");
		// feat
		
		model.addAttribute("request", request);
		command = new BDeleteCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	
	
}
