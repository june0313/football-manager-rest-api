package com.jun.fm.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by wayne on 2017. 6. 6..
 */
@MappedSuperclass
@Getter
public abstract class BaseEntity {

	@Id
	@GeneratedValue
	private Long id;

	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;

	@PrePersist
	private void prePersist() {
		this.createdDate = LocalDateTime.now();
		this.modifiedDate = LocalDateTime.now();
	}

	@PreUpdate
	private void preUpdate() {
		this.modifiedDate = LocalDateTime.now();
	}

}
