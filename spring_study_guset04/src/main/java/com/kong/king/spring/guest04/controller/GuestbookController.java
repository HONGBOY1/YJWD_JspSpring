package com.kong.king.spring.guest04.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb.PageRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kong.king.spring.guest04.dto.GuestbookDTO;
import com.kong.king.spring.guest04.dto.PageRequestDTO;
import com.kong.king.spring.guest04.service.GuestbookService;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/guestbook")
@Log4j2
public class GuestbookController {
	
	@Autowired
	private GuestbookService service;

	@GetMapping("/")
	   public String list() {
	      log.info("list root.........");
	      return "redirect:/guestbook/list";
	   }
	
	@GetMapping("/list")
	   public void list(PageRequestDTO pageRequesDTO, Model model) {
	      log.info("list root.........");
	      model.addAttribute("result", service.getList(pageRequesDTO));
	   }
	
	
	@GetMapping("/register")
	   public void register() {
	      log.info("register  get.........");
	   }
	
	@PostMapping("/register")
	public String registerPost(GuestbookDTO dto, RedirectAttributes redirectAttributes) {
		log.info("dto..."+dto);
		
		Long gno = service.register(dto);
		
		redirectAttributes.addFlashAttribute("msg", gno);
		return "redirect:/guestbook/list";
	}
	
    @GetMapping({"/read", "/modify"})
    public void read(@RequestParam("gno") long gno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {
    	log.info("gno: " + gno);
        GuestbookDTO dto = service.read(gno);
        model.addAttribute("dto", dto);
	   }
	
	@PostMapping("/remove")
	public String remove(long gno, RedirectAttributes redirectAttributes) {
		log.info("gno"+gno);
		
		service.remove(gno);
		
		redirectAttributes.addFlashAttribute("msg", gno);
		return "redirect:/guestbook/list";
	}
	
	
	@PostMapping("/modify")
	public String modify(GuestbookDTO dto, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, RedirectAttributes redirectAttributes) {

	    log.info("post modify...........");
	    log.info("dto : " + dto);    

        service.modify(dto);
        
        redirectAttributes.addFlashAttribute("page", requestDTO.getPage());
        redirectAttributes.addFlashAttribute("type", requestDTO.getType());
        redirectAttributes.addFlashAttribute("keyword", requestDTO.getKeyword());
        redirectAttributes.addFlashAttribute("gno", dto.getGno());
        
        log.info("dtoGNO : " + dto.getGno());   
        return "redirect:/guestbook/read?gno=" + dto.getGno();
      }
	
	
   
}
