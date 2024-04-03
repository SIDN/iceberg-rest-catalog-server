# Iceberg REST Catalog Server

This is a Spring Boot based implementation of a [REST catalog](https://iceberg.apache.org/concepts/catalog/#decoupling-using-the-rest-catalog) server for [Apache Iceberg](https://iceberg.apache.org/)  
Applications such as [ENTRADA2](https://github.com/SIDN/entrada2) may use this REST Catalog server for metadata persistence and catalog operations.


# Build

```
export TOOL_VERSION=0.1.1
mvn package && docker build --platform linux/amd64 --tag=sidnlabs/iceberg-rest-catalog-server:$TOOL_VERSION .
```

# Getting started

See [ENTRADA2](https://github.com/SIDN/entrada2) for an example on how to use the REST Catalog Server.