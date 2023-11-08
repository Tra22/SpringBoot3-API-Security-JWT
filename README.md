# SpringBoot3-API-Security-JWT
An example of a RESTful WebServer developed using Spring & SpringBoot.

This example explained about spring boot3 with Security & JWT. Also explained about swagger config and customize some on it.

## Requirements

The fully fledged server uses the following:

* Spring Framework
* SpringBoot
* Log4j2
* Spring Data JPA
* Postgres Database
* Spring Security
* JWT
* Lombok
* Swagger

## Dependencies
There are a number of third-party dependencies used in the project. Browse the Maven pom.xml file for details of libraries and versions used.<br>

  	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter</artifactId>
			<exclusions>
				<exclusion>
					<groupId>org.springframework.boot</groupId>
					<artifactId>spring-boot-starter-logging</artifactId>
				</exclusion>
			</exclusions>
		</dependency>
		<dependency>
			<groupId>org.yaml</groupId>
			<artifactId>snakeyaml</artifactId>
			<version>2.0</version>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-log4j2</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.postgresql</groupId>
			<artifactId>postgresql</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-api</artifactId>
			<version>0.11.5</version>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-impl</artifactId>
			<version>0.11.5</version>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-jackson</artifactId>
			<version>0.11.5</version>
		</dependency>
		<dependency>
			<groupId>org.springdoc</groupId>
			<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
			<version>2.1.0</version>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

## Building the project
You will need:

*	Java JDK 17 or higher
*	Maven 3+ or higher
*	Tomcat 10.1

Clone the project and use Maven to build the server

	$ mvn clean install

Swagger UI access

 	http://localhost:8080/swagger-ui.html
  
## Languages and Tools
<div>
  <img src="https://spring.io/img/og-spring.png" title="spring framework" alt="spring framework" width="80" height="40"/>&nbsp;
  <img src="https://res.cloudinary.com/practicaldev/image/fetch/s--3ix0rFmo--/c_imagga_scale,f_auto,fl_progressive,h_420,q_auto,w_1000/https://dev-to-uploads.s3.amazonaws.com/uploads/articles/dmmxiwgyuzodl7yqyuca.jpeg" title="spring boot" alt="spring boot" width="80" height="40"/>&nbsp;
  <img src="https://miro.medium.com/v2/resize:fit:800/0*e3yFdW2ChuPGFery.png" title="spring data jpa" alt="spring data jpa" width="40" height="40"/>&nbsp;
  <img src="https://w7.pngwing.com/pngs/441/460/png-transparent-postgresql-plain-wordmark-logo-icon-thumbnail.png" title="postgres db" alt="postgres db" width="40" height="40"/>&nbsp;
  <img src="https://miro.medium.com/v2/resize:fit:400/1*8puK5Pi4_5PZzFFgBtnUhw.jpeg" title="log4j2" alt="log4j2" width="60" height="40"/>&nbsp;
  <img src="https://miro.medium.com/v2/resize:fit:1400/1*Jt34KK87zw10NOLYqU-CNQ.jpeg" title="lombok" alt="lombok" width="80" height="40"/>&nbsp;
  <img src="https://miro.medium.com/v2/resize:fit:1400/1*R36nHDnQ9i7vizbSJqTb1g.png" title="swagger ui" alt="swagger ui" width="80" height="30"/>&nbsp;
</div>
	
