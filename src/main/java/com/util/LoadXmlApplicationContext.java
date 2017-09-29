package com.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;

public class LoadXmlApplicationContext extends ClassPathXmlApplicationContext {
    private Resource[] configResources;

    public LoadXmlApplicationContext() {
    }

    public LoadXmlApplicationContext(ApplicationContext parent) {
        super(parent);
    }

    public LoadXmlApplicationContext(String configLocation) throws BeansException {
        super(configLocation);
    }

    public LoadXmlApplicationContext(String... configLocations) throws BeansException {
        super(configLocations);
    }

    public LoadXmlApplicationContext(String[] configLocations, ApplicationContext parent) throws BeansException {
        super(configLocations, true, parent);
    }

    public LoadXmlApplicationContext(String[] configLocations, boolean refresh) throws BeansException {
        super(configLocations, refresh, (ApplicationContext)null);
    }

    public LoadXmlApplicationContext(String[] configLocations, boolean refresh, ApplicationContext parent) throws BeansException {
        super(configLocations,refresh,parent);
    }

    public LoadXmlApplicationContext(String path, Class<?> clazz) throws BeansException {
        super(new String[]{path}, clazz);
    }

    public LoadXmlApplicationContext(String[] paths, Class<?> clazz) throws BeansException {
        super(paths, clazz, (ApplicationContext)null);
    }

    public LoadXmlApplicationContext(String[] paths, Class<?> clazz, ApplicationContext parent) throws BeansException {
        super(paths,clazz,parent);
    }

    protected Resource[] getConfigResources() {
        return this.configResources;
    }
    @Override
    public Resource getResource(String location) {
        String placeholder="${root}";
        int len = placeholder.length();
        StringBuilder builder = new StringBuilder(location.trim());
        int index = builder.indexOf("${root}");
        if(index>0){
            builder.replace(index,index+ len, getProjectRootPath());
            location = builder.toString();
        }
        return super.getResource(location);
    }

    /**
     * @Method : getProjectRootPath
     * @Description : 获取工程跟路径
     * @return : java.lang.String
     * @author : liuya
     * @CreateDate : 2017-08-28 星期一 14:32:42
     */
    public  String getProjectRootPath() {
        File file = new File("");
        String projectPath = "";
        try {
            projectPath = file.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return projectPath;
    }
}
