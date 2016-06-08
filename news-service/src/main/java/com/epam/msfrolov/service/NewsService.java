package com.epam.msfrolov.service;

import com.epam.msfrolov.exception.DatabaseModuleException;
import com.epam.msfrolov.model.News;
import com.epam.msfrolov.util.FileUtil;

import java.util.List;

public interface NewsService {

    static NewsService newInstance() {
        String serviceImplementName = FileUtil.getProperties("properties/service.properties").getProperty("service.name");
        try {
            return (NewsService) Class.forName(serviceImplementName).newInstance();
        } catch (ClassNotFoundException e) {
            throw new DatabaseModuleException("Class NewsService not found", e);
        } catch (InstantiationException e) {
            throw new DatabaseModuleException("Class NewsService factory not init", e);
        } catch (IllegalAccessException e) {
            throw new DatabaseModuleException("Illegal access on some extended NewsService not found", e);
        } catch (Exception e) {
            throw new DatabaseModuleException("Failed to get NewsService instance", e);
        }
    }

    List<News> getList();

    News findById(int id);

    News save(News news);

    News update(News news);

    int remove(int[] idArray);

    boolean remove(int id);

    News remove(News news);
}