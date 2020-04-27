package com.manage.service;

import com.manage.model.MenuMO;

import java.util.List;

public interface MenuService {

    List<MenuMO> listMenu();

    void addMenu(MenuMO menuMO);
}
