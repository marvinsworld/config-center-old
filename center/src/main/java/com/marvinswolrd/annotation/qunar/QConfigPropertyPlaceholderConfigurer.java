//package com.marvinswolrd.annotation.qunar;
//
//import com.google.common.base.Preconditions;
//import com.google.common.util.concurrent.Futures;
//import com.google.common.util.concurrent.ListenableFuture;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
//import qunar.tc.qconfig.client.Feature;
//import qunar.tc.qconfig.client.MapConfig;
//
//import java.io.FileNotFoundException;
//import java.util.List;
//import java.util.Map;
//import java.util.Properties;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.TimeoutException;
//
//class DConfigPropertyPlaceholderConfigurer
//  extends PropertyPlaceholderConfigurer
//  implements InitializingBean
//{
//  private static final Logger logger = LoggerFactory.getLogger(DConfigPropertyPlaceholderConfigurer.class);
//  private long timeout;
//  private final String[] files;
//  private boolean ignoreResourceNotFound;
//
//  public DConfigPropertyPlaceholderConfigurer(String... files)
//  {
//    Preconditions.checkArgument((files != null) && (files.length > 0), "files��������");
//    this.files = files;
//  }
//
//  public void afterPropertiesSet()
//    throws Exception
//  {
//    MapConfig[] configs = new MapConfig[this.files.length];
//
//    ListenableFuture<?>[] futures = new ListenableFuture[this.files.length];
//    for (int i = 0; i < this.files.length; i++)
//    {
//      String file = this.files[i];
//      Map.Entry<String, Feature> entry = Util.parse(file);
//
//      configs[i] = MapConfig.get((String)entry.getKey(), (Feature)entry.getValue());
//
//      futures[i] = configs[i].initFuture();
//    }
//    ListenableFuture<?> future = this.ignoreResourceNotFound ? Futures.successfulAsList(futures) : Futures.allAsList(futures);
//    try
//    {
//      List result = (List)future.get(this.timeout, TimeUnit.MILLISECONDS);
//      Properties[] arr = new Properties[configs.length];
//      for (int i = 0; i < configs.length; i++) {
//        if (result.get(i) != null)
//        {
//          MapConfig map = configs[i];
//          arr[i] = map.asProperties();
//        }
//      }
//      setPropertiesArray(arr);
//    }
//    catch (InterruptedException e)
//    {
//      Thread.interrupted();
//    }
//    catch (ExecutionException e)
//    {
//      logger.error("retrieve config from qconfig error", e.getCause());
//      if ((e.getCause() instanceof FileNotFoundException)) {
//        throw new FileNotFoundException(e.getCause().getMessage());
//      }
//    }
//    catch (TimeoutException e)
//    {
//      logger.error("retrieve config from qconfig timeout, timeout: {}", Long.valueOf(this.timeout), e);
//    }
//  }
//
//  public void setTimeout(long timeout)
//  {
//    this.timeout = timeout;
//  }
//
//  public void setIgnoreResourceNotFound(boolean ignoreResourceNotFound)
//  {
//    this.ignoreResourceNotFound = ignoreResourceNotFound;
//  }
//}
