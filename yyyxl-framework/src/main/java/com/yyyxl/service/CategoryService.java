package com.yyyxl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yyyxl.domain.ResponseResult;

public interface CategoryService extends IService<entity.Category> {

    ResponseResult getCategoryList();

}
