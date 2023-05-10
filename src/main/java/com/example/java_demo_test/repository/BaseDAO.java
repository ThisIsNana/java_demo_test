package com.example.java_demo_test.repository;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.util.CollectionUtils;

public class BaseDAO {

	@PersistenceContext // JPA�M��������
	private EntityManager entityManager;
	// �ϥγo�ӥi�H�Ыب��Query

	// <T> �ݩ�x���A�i�H�O����r�A����ĳ�ϥΦ��N�q���W�r�C
	// public �� protected �ҥi�A�n�~�Ӧܤ֭nprotected = Query�y�k = @Param = Entity���A
	// -�`�N��class(�O�d�r)
//	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SuppressWarnings("unchecked")
	protected <EntityType> List<EntityType> doQuery(String sql, Map<String, Object> params, Class<EntityType> clazz) {
		Query query = entityManager.createQuery(sql, clazz);
		
//		if(!CollectionUtils.isEmpty(params)) {
//			for (Entry<String, Object> item : params.entrySet()) {
//				query.setParameter(item.getKey(), item.getValue());
//			}
//			// �W�P�U��k�ܤ@�g�Y�i�A�W������ı
////			for( Parameter p : query.getParameters()) {   //�[�@��@SuppressWarning�bdoQuery�W��
////				query.setParameter(p, params.get(p.getName()));
////			}
//		}
//		return query.getResultList();
		return doQuery(sql, params, clazz, -1); //<-�]��limitSize�n>0�~�|����A�o��n���L����!�ҥH��0��-1
	}

	/*
	 * limitSize ����^�ǵ���
	 */
	@SuppressWarnings("unchecked")
	protected <EntityType> List<EntityType> doQuery(String sql, Map<String, Object> params,
			Class<EntityType> clazz, int limitSize) {
		Query query = entityManager.createQuery(sql, clazz);
//		if(!CollectionUtils.isEmpty(params)) {
//			for (Entry<String, Object> item : params.entrySet()) {
//				query.setParameter(item.getKey(), item.getValue());
//			}
//			// �W�P�U��k�ܤ@�g�Y�i�A�W������ı
////			for( Parameter p : query.getParameters()) {   //�[�@��@SuppressWarning�bdoQuery�W��
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
	 * limitSize ����^�ǵ���
	 * startPosition �C�Ӥ������_�l��m
	 */
	@SuppressWarnings("unchecked")
	protected <EntityType> List<EntityType> doQuery(String sql, Map<String, Object> params,
			Class<EntityType> clazz, int limitSize, int startPosition) {
		Query query = entityManager.createQuery(sql, clazz);
		//�U���o�q�i��i����
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
			// �W�P�U��k�ܤ@�g�Y�i�A�W������ı
//			for( Parameter p : query.getParameters()) {   //�[�@��@SuppressWarning�bdoQuery�W��
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
	 * ��s�u����ӵ��G: ���\ �� ����
	 */
	protected int doUpdate(String sql, Map<String, Object> params) {
		Query query = entityManager.createQuery(sql);
		if(!CollectionUtils.isEmpty(params)) {
			for (Entry<String, Object> item : params.entrySet()) {
				query.setParameter(item.getKey(), item.getValue());
			}
		}
		return query.executeUpdate(); //��s�ƾ�
	}
	/*
	 * �令NativeQuery
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
