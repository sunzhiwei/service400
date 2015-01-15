package com.yixin.service400.base;

import java.lang.reflect.ParameterizedType;

import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public abstract class ModelDrivenBaseAction<T> extends BaseAction implements ModelDriven<T> {

	protected T model;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ModelDrivenBaseAction() {
		try {
			// 得到model的类型信息
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class clazz = (Class) pt.getActualTypeArguments()[0];

			// 通过反射生成model的实例
			model = (T) clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public T getModel() {
		return model;
	}


}
