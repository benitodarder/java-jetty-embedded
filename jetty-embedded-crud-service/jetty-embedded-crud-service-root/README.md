# jetty-embedded-crud-service

   A sample CRUD service running inside a Jetty embedded.

   Requires following projects from commons:
   
	* common-base-model-data
	* common-base-model-domain
	* common-base-configuration
	* common-base-service-servlets
	

   Sample HSQLDB command: 

	java -cp /opt/hsqldb/1.8.1.3/lib/hsqldb.jar org.hsqldb.Server -silent false -database.0 file:/home/benitodarder/Development/databases/hsqldb/webappJBoss6.1/products/products.db -dbname.0 products

   Sample persistence.xml:

    <?xml version="1.0" encoding="UTF-8"?>
    <persistence version="2.0" xmlns="http://java.sun.com/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd">
        <persistence-unit name="products" transaction-type="RESOURCE_LOCAL">
            <provider>org.hibernate.ejb.HibernatePersistence</provider>
            <!-- Standalone configuration by properties -->
            <properties>
                <property name="hibernate.archive.autodetection" value="class"/>
                <property name="hibernate.connection.driver_class" value="org.hsqldb.jdbcDriver"/>
                <property name="hibernate.connection.url" value="jdbc:hsqldb:hsql://localhost:9001/products"/>
                <property name="hibernate.connection.username" value="SA"/>
                <property name="hibernate.connection.password" value=""/>
                <property name="hibernate.show_sql" value="true"/>
                <property name="hibernate.dialect" value="org.hibernate.dialect.HSQLDialect"/>  
                <property name="hibernate.generate_statistics" value="true" />
    <!--            <property name="hibernate.cache.use_second_level_cache" value="true"/>
                <property name="hibernate.cache.use_query_cache" value="true"/>
                <property name="hibernate.cache.region.factory_class" value="org.hibernate.cache.infinispan.InfinispanRegionFactory" />
                <property name="hibernate.cache.infinispan.cfg" value="org/hibernate/cache/infinispan/builder/infinispan-configs.xml"/>                               -->
            </properties>
        </persistence-unit>
    </persistence>
