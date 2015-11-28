package com.marvinswolrd.annotation.demo;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.io.InputStream;
import java.util.Properties;

class DConfigPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer implements InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(DConfigPropertyPlaceholderConfigurer.class);

    private final String[] files;
    private long timeout;
    private boolean ignoreResourceNotFound;

    public DConfigPropertyPlaceholderConfigurer(String... files) {
        Preconditions.checkArgument((files != null) && (files.length > 0), "DConfig files property must be not null!");
        this.files = files;
    }

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {


        super.processProperties(beanFactoryToProcess, props);
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println(this.files);

        Properties[] arr = new Properties[1];

        Properties props = new Properties();
        InputStream in =  this.getClass().getClassLoader().getResourceAsStream("dconfig1.properties");
        props.load(in);
        arr[0]=props;
        //mergeProperties();
        setPropertiesArray(arr);

        //http://blog.csdn.net/feiyu8607/article/details/8282893



//        MapConfig[] configs = new MapConfig[this.files.length];
//
//        ListenableFuture<?>[] futures = new ListenableFuture[this.files.length];
//        for (int i = 0; i < this.files.length; i++) {
//            String file = this.files[i];
//            Map.Entry<String, Feature> entry = Util.parse(file);
//
//            configs[i] = MapConfig.get((String) entry.getKey(), (Feature) entry.getValue());
//
//            futures[i] = configs[i].initFuture();
//        }
//        ListenableFuture<?> future = this.ignoreResourceNotFound ? Futures.successfulAsList(futures) : Futures.allAsList(futures);
//        try {
//            List result = (List) future.get(this.timeout, TimeUnit.MILLISECONDS);
//            Properties[] arr = new Properties[configs.length];
//            for (int i = 0; i < configs.length; i++) {
//                if (result.get(i) != null) {
//                    MapConfig map = configs[i];
//                    arr[i] = map.asProperties();
//                }
//            }
//            setPropertiesArray(arr);
//        } catch (InterruptedException e) {
//            Thread.interrupted();
//        } catch (ExecutionException e) {
//            logger.error("retrieve config from qconfig error", e.getCause());
//            if ((e.getCause() instanceof FileNotFoundException)) {
//                throw new FileNotFoundException(e.getCause().getMessage());
//            }
//        } catch (TimeoutException e) {
//            logger.error("retrieve config from qconfig timeout, timeout: {}", Long.valueOf(this.timeout), e);
//        }
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public void setIgnoreResourceNotFound(boolean ignoreResourceNotFound) {
        this.ignoreResourceNotFound = ignoreResourceNotFound;
    }
}
