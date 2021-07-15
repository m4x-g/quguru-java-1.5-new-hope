package com.app.services;

import com.app.dao.CatalogDao;
import com.app.model.CatalogItem;
import com.app.model.Category;
import com.app.model.Subcategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    private CatalogDao catalogDao;

    public List<Category> getCategories(){
        return catalogDao.getCategories();
    }

    public void storeCategory(Category category){
        catalogDao.storeCategory(category);
    }

    public List<Subcategory> getSubcategories(){
        return catalogDao.getSubcategories();
    }
    public List<Subcategory> getSubcategories(long id){
        return catalogDao.getSubcategories(id);
    }

    public void storeSubcategory(Subcategory subcategory){
        catalogDao.storeSubcategory(subcategory);
    }

    public List<CatalogItem> getCatalogItems(){
        return catalogDao.getCatalogItems();
    }
    public List<CatalogItem> getCatalogItemsById(long id){
        return catalogDao.getCatalogItemsById(id);
    }

    public void storeCatalogItem(CatalogItem catalogItem){
        //some garbage validation here
        String newPrice = catalogItem.getPrice().toString().replace(',', '.');
        catalogItem.setPrice(new BigDecimal(newPrice));
        catalogDao.storeCatalogItem(catalogItem);
    }
}
