package com.ombawebsite.ItemFiles;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="SecurityQuestion")
public class SecurityQuestionList {

	private ArrayList<SecurityQuestion> sqList;

	@XmlElement(name="SQ")
	@XmlElementWrapper(name="sqList")
	public ArrayList<SecurityQuestion> getSqList() {
		return sqList;
	}

	public void setSqList(ArrayList<SecurityQuestion> sqList) {
		this.sqList = sqList;
	}
	
	
 }
