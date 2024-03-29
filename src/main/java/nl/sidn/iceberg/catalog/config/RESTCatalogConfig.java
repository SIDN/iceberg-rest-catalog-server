package nl.sidn.iceberg.catalog.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.iceberg.CatalogProperties;
import org.apache.iceberg.CatalogUtil;
import org.apache.iceberg.aws.s3.S3FileIOProperties;
import org.apache.iceberg.catalog.Catalog;
import org.apache.iceberg.jdbc.JdbcCatalog;
import org.apache.iceberg.rest.RESTCatalogAdapter;
import org.apache.iceberg.rest.RESTCatalogServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RESTCatalogConfig {

  @Value("${catalog.db.url}")
  private String dbUrl;

  @Value("${catalog.db.user}")
  private String dbUser;

  @Value("${catalog.db.password}")
  private String dbPassword;

  @Value("${catalog.s3.bucket}")
  private String s3Bucket;

  @Value("${catalog.s3.warehouse_dir}")
  private String s3Warehouse;

  @Value("${catalog.s3.endpoint}")
  private String s3endpoint;

  @Value("${catalog.s3.access-key}")
  private String s3AccessKey;

  @Value("${catalog.s3.secret-key}")
  private String s3SecretKey;

  @Bean
  ServletRegistrationBean<RESTCatalogServlet> restCatalogServlet() {

	   RESTCatalogAdapter adapter = new RESTCatalogAdapter(backendCatalog());

	    ServletRegistrationBean<RESTCatalogServlet> bean = new ServletRegistrationBean<>(
	        new RESTCatalogServlet(adapter));
	    bean.setLoadOnStartup(1);
	    return bean;
  }

  @Bean
  Catalog backendCatalog() {
    Map<String, String> catalogProperties = new HashMap<String, String>();

    catalogProperties.put(CatalogProperties.CATALOG_IMPL, "org.apache.iceberg.jdbc.JdbcCatalog");
    catalogProperties.put(CatalogProperties.URI, dbUrl);
    catalogProperties.put(JdbcCatalog.PROPERTY_PREFIX + "user", dbUser);
    catalogProperties.put(JdbcCatalog.PROPERTY_PREFIX + "password", dbPassword);
    catalogProperties.put(CatalogProperties.WAREHOUSE_LOCATION,
        "s3://" + s3Bucket + "/" + s3Warehouse);
    catalogProperties.put(CatalogProperties.FILE_IO_IMPL, "org.apache.iceberg.aws.s3.S3FileIO");
    if (StringUtils.isNotBlank(s3endpoint)) {
      catalogProperties.put(S3FileIOProperties.ENDPOINT, s3endpoint);
    }
    catalogProperties.put(S3FileIOProperties.ACCESS_KEY_ID, s3AccessKey);
    catalogProperties.put(S3FileIOProperties.SECRET_ACCESS_KEY, s3SecretKey);
    catalogProperties.put(S3FileIOProperties.PATH_STYLE_ACCESS, "true");

    return CatalogUtil.buildIcebergCatalog("rest_backend", catalogProperties, null);
  }


}
