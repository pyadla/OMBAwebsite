package com.ombawebsite.ItemFiles;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.appengine.api.datastore.Key;

@Entity
@XmlRootElement(name="SQ")
public class SecurityQuestion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Key key;
   
	private String questionId;
    private String answer;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    public Key getKey() 
	{
		return key;
	}

	public void setKey(Key key) 
	{
		this.key = key;
	}

    public SecurityQuestion(String questionId,String answer){
    	this.questionId=questionId;
    	this.answer=answer;
    }
    
    @XmlElement(name="Q")
    public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	
	 @XmlElement(name="A")
    public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	 public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
