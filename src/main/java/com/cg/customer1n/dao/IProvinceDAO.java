package com.cg.customer1n.dao;


import com.cg.customer1n.model.Province;

import java.util.List;

public interface IProvinceDAO {
    List<Province> selectProvinces();
    Province findProvinceById(int id);
}
