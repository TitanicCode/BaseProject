package com.common.shiro.mult.realm;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

@Component("sessionDAO")
public class RedisSessionDao extends AbstractSessionDAO {
	@Resource
	private RedisClient sessionCacheClient;

	Logger log= LoggerFactory.getLogger(getClass());
	@Override
	public void update(Session session) throws UnknownSessionException {
		log.info("更新seesion,id=[{}]",session.getId().toString());
		 try {  
	            sessionCacheClient.replace(session.getId().toString(), session);  
	        } catch (Exception e) {  
	           e.printStackTrace(); 
	        }  
	}

	@Override
	public void delete(Session session) {
		log.info("删除seesion,id=[{}]",session.getId().toString());
		 try {  
	            sessionCacheClient.delete(session.getId().toString());  
	        } catch (Exception e) {  
	           e.printStackTrace(); 
	        }  
	}

	@Override
	public Collection<Session> getActiveSessions() {
		log.info("获取存活的session");
		 return Collections.emptySet(); 
	}

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = generateSessionId(session);  
        assignSessionId(session, sessionId);
//		((SerSession)session).setId(sessionId);
        log.info("创建seesion,id=[{}]",session.getId().toString());
        try {  
            sessionCacheClient.set(sessionId.toString(),  session);  
        } catch (Exception e) {  
            log.error(e.getMessage());  
        }  
        return sessionId;  
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		if (sessionId == null) {
			return null;
		}
		log.info("获取seesion,id=[{}]",sessionId.toString());
		Session session = null;  
        try {  
            session = (Session) sessionCacheClient.get(sessionId.toString());
            return  session;
        } catch (Exception e) {  
            log.error(e.getMessage());  
        }  
        return session;
	}
}

