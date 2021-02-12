package com.humanbooster.examen_spring_elhadji_chanfi.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "advert")
public class Advert {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
    private Long id;
	
	@Column(name = "title", nullable = false, length = 250)
    @NotNull(message = "le titre ne doit pas être nul")
    @NotBlank(message = "le titre ne doit pas être vide")
	private String title;
	
	@Column(name = "image_link", nullable = true, length = 250)
	private String imageLink;
	
	@Column(name = "content", nullable = false)
	@Type(type = "text")
    @NotNull(message = "le contenu ne doit pas être nul")
    @NotBlank(message = "le contenu ne doit pas être vide")
	@Length(min=20, message = "le contenu doit avoir au moins 20 caractères")
	private String content;
	
	@Column(name = "published_at", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date publishedAt;

	public Advert(Long id,
			@NotNull(message = "le titre ne doit pas être nul") @NotBlank(message = "le titre ne doit pas être vide") String title,
			String imageLink,
			@NotNull(message = "le contenu ne doit pas être nul") @NotBlank(message = "le contenu ne doit pas être vide") @Length(min = 20) String content,
			Date publishedAt) {
		this.id = id;
		this.title = title;
		this.imageLink = imageLink;
		this.content = content;
		this.publishedAt = publishedAt;
	}

	public Advert(
			@NotNull(message = "le titre ne doit pas être nul") @NotBlank(message = "le titre ne doit pas être vide") String title,
			String imageLink,
			@NotNull(message = "le contenu ne doit pas être nul") @NotBlank(message = "le contenu ne doit pas être vide") @Length(min = 20) String content,
			Date publishedAt) {
		this.title = title;
		this.imageLink = imageLink;
		this.content = content;
		this.publishedAt = publishedAt;
	}

	public Advert() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(Date publishedAt) {
		this.publishedAt = publishedAt;
	}
	
	

}
