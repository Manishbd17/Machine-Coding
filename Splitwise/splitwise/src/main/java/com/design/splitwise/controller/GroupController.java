package com.design.splitwise.controller;

import java.util.ArrayList;
import java.util.List;

import com.design.splitwise.model.Group;
import com.design.splitwise.model.User;

public class GroupController {
	List<Group> groupList; 
	
	public GroupController() {
		groupList = new ArrayList<> (); 
	}
	
	public void createNewGroup(String groupId, String groupName,User createdByUser) {
		Group group = new Group(groupId,groupName);
		group.addMember(createdByUser);
		groupList.add(group); 
	}
	
	public Group getGroup(String groupId) {
		for(Group group: groupList) {
			if(group.getGroupId().equals(groupId)) {
				return group; 
			}
		}
		return null;
	}
}
