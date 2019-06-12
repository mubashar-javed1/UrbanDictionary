package com.mobi.urbandictionary.data;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DefinitionResponse {

	@SerializedName("list")
	private List<ItemDefinition> list;

	public void setList(List<ItemDefinition> list){
		this.list = list;
	}

	public List<ItemDefinition> getList(){
		return list;
	}
}