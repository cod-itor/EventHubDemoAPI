package com.example.springsecurityjwt.repository;

import com.example.springsecurityjwt.entities.UserActivityLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserActivityLogRepository {

	@Insert("""
		INSERT INTO user_activity_logs (user_id, action_type, timestamp)
		VALUES (#{userId}, #{actionType}, #{timestamp})
	""")
	void insertLog(UserActivityLog log);
}
