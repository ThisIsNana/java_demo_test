package com.example.java_demo_test.repository;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.util.CollectionUtils;

public class BaseDAO {

	@PersistenceContext // JPA專有的註釋
	private EntityManager entityManager;
	// 使用這個可以創建兩種Query

	// <T> 屬於泛型，可以是任何字，但建議使用有意義的名字。
	// public 跟 protected 皆可，要繼承至少要protected = Query語法 = @Param = Entity型態
	// -常代用class(保留字)
//	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SuppressWarnings("unchecked")
	protected <EntityType> List<EntityType> doQuery(String sql, Map<String, Object> params, Class<EntityType> clazz) {
		Query query = entityManager.createQuery(sql, clazz);
		
//		if(!CollectionUtils.isEmpty(params)) {
//			for (Entry<String, Object> item : params.entrySet()) {
//				query.setParameter(item.getKey(), item.getValue());
//			}
//			// 上與下方法擇一寫即可，上方比較直覺
////			for( Parameter p : query.getParameters()) {   //加一個@SuppressWarning在doQuery上方
////				query.setParameter(p, params.get(p.getName()));
////			}
//		}
//		return query.getResultList();
		return doQuery(sql, params, clazz, -1); //<-因為limitSize要>0才會執行，這邊要讓他失效!所以用0或-1
	}

	/*
	 * limitSize 限制回傳筆數
	 */
	@SuppressWarnings("unchecked")
	protected <EntityType> List<EntityType> doQuery(String sql, Map<String, Object> params,
			Class<EntityType> clazz, int limitSize) {
		Query query = entityManager.createQuery(sql, clazz);
//		if(!CollectionUtils.isEmpty(params)) {
//			for (Entry<String, Object> item : params.entrySet()) {
//				query.setParameter(item.getKey(), item.getValue());
//			}
//			// 上與下方法擇一寫即可，上方比較直覺
////			for( Parameter p : query.getParameters()) {   //加一個@SuppressWarning在doQuery上方
////				query.setParameter(p, params.get(p.getName()));
////			}
//		}
//		if(limitSize > 0) {
//			query.setMaxResults(limitSize);
//		}
//		return query.getResultList();
		return doQuery(sql, params, clazz, limitSize, -1);
	}
	/*
	 * limitSize 限制回傳筆數
	 * startPosition 每個分頁的起始位置
	 */
	@SuppressWarnings("unchecked")
	protected <EntityType> List<EntityType> doQuery(String sql, Map<String, Object> params,
			Class<EntityType> clazz, int limitSize, int startPosition) {
		Query query = entityManager.createQuery(sql, clazz);
		//下面這段可改可不改
//		Query query = null;  
//		if(clazz == null) {
//			entityManager.createQuery(sql);
//		} else {
//			query = entityManager.createQuery(sql, clazz);
//		}
		if(!CollectionUtils.isEmpty(params)) {
			for (Entry<String, Object> item : params.entrySet()) {
				query.setParameter(item.getKey(), item.getValue());
			}
			// 上與下方法擇一寫即可，上方比較直覺
//			for( Parameter p : query.getParameters()) {   //加一個@SuppressWarning在doQuery上方
//				query.setParameter(p, params.get(p.getName()));
//			}
		}
		if(limitSize > 0) {
			query.setMaxResults(limitSize);
		}
		if(startPosition >= 0) {
			query.setFirstResult(startPosition);
		}
		return query.getResultList();
	}
	
	/*
	 * 更新只有兩個結果: 成功 或 失敗
	 */
	protected int doUpdate(String sql, Map<String, Object> params) {
		Query query = entityManager.createQuery(sql);
		if(!CollectionUtils.isEmpty(params)) {
			for (Entry<String, Object> item : params.entrySet()) {
				query.setParameter(item.getKey(), item.getValue());
			}
		}
		return query.executeUpdate(); //更新數據
	}
	/*
	 * 改成NativeQuery
	 */
	@SuppressWarnings("unchecked")
	protected <EntityType> List<EntityType> doNativeQuery(String sql, Map<String, Object> params,
			Class<EntityType> clazz, int limitSize, int startPosition) {
		Query query = entityManager.createNativeQuery(sql, clazz);
		if(!CollectionUtils.isEmpty(params)) {
			for (Entry<String, Object> item : params.entrySet()) {
				query.setParameter(item.getKey(), item.getValue());
			}
		}
		if(limitSize > 0) {
			query.setMaxResults(limitSize);
		}
		if(startPosition >= 0) {
			query.setFirstResult(startPosition);
		}
		
		return query.getResultList();
	}
	
}
