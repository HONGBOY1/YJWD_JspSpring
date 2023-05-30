package com.kong.king.spring.guest04.service;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.kong.king.spring.guest04.dto.GuestbookDTO;
import com.kong.king.spring.guest04.dto.PageRequestDTO;
import com.kong.king.spring.guest04.dto.PageResultDTO;
import com.kong.king.spring.guest04.entity.Guestbook;
import com.kong.king.spring.guest04.entity.QGuestbook;
import com.kong.king.spring.guest04.repository.GuestbookRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class GuestbookServiceImpl implements GuestbookService{
	
	private final GuestbookRepository repository;
	
	@Override
	public Long register(GuestbookDTO dto) {
		
		log.info("DTO......");
		log.info(dto);
		
		Guestbook entity = dtoToEntiy(dto);
		
		log.info(entity);
		
		repository.save(entity);
		
		return entity.getGno();
	}
	
	@Override
	public PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requesDTO){
		
		Pageable pageable = requesDTO.getPageble(Sort.by("gno").descending());
		
		BooleanBuilder booleanBuilder =getSearch(requesDTO);
		
		Page<Guestbook> result = repository.findAll(booleanBuilder,pageable);
		
		Function<Guestbook, GuestbookDTO> fn = (entity -> entityToDto(entity));
		
		return new PageResultDTO<>(result, fn);
	}
	
	@Override
	public GuestbookDTO read(Long gno) {
		Optional<Guestbook> result = repository.findById(gno);
		return result.isPresent() ? entityToDto(result.get()) : null;
	}
	
	@Override
	public void remove(Long gno) {
		repository.deleteById(gno);
	}
	
	@Override
	public void modify(GuestbookDTO dto) {
		Optional<Guestbook> result = repository.findById(dto.getGno());
		
		if(result.isPresent()) {
			Guestbook entity = result.get();
			
			entity.changeTitle(dto.getTitle());
			entity.changeContent(dto.getContent());
			
			repository.save(entity);
		}
	}
	
	   private BooleanBuilder getSearch(PageRequestDTO requestDTO) {
	        String type = requestDTO.getType();
	        BooleanBuilder booleanBuilder = new BooleanBuilder();
	        QGuestbook qGuestBook = QGuestbook.guestbook;
	        String keyword = requestDTO.getKeyword();
	        BooleanExpression expression = qGuestBook.gno.gt(0L);
	        booleanBuilder.and(expression);
	        if(type == null || type.trim().length() == 0)
	            return booleanBuilder;

	        BooleanBuilder conditionBuilder = new BooleanBuilder();
	        if(type.contains("t"))
	            conditionBuilder.or(qGuestBook.title.contains(keyword));
	        if(type.contains("c"))
	            conditionBuilder.or(qGuestBook.content.contains(keyword));
	        if(type.contains("w"))
	            conditionBuilder.or(qGuestBook.writer.contains(keyword));

	        booleanBuilder.and(conditionBuilder);
	        
	        return booleanBuilder;
	    }
	
}
