# Provisio
CSD-460 Green Team Capstone Project Repository

## Setup and Compilation

### MacOS & Linux

#### Create MySQL User
```bash
$ mysql -u root -p
mysql> USE mysql;
mysql> CREATE USER 'user'@'localhost' IDENTIFIED BY 'root@1234';
mysql> GRANT ALL ON *.* TO 'user'@'localhost';
mysql> FLUSH PRIVILEGES;
```

#### Change Credentials In DBHelper.java (If Applicable)
```java
private final static String db_username = "user";
private final static String db_password = "root@1234";
```

#### Compile
```bash
$CATALINA_HOME/webapps/Provisio $ find . -name "*.java" > sources.txt
$CATALINA_HOME/webapps/Provisio $ javac -cp ".:$CATALINA_HOME/lib/servlet-api.jar" @sources.txt -d WEB-INF/classes
```

### Windows
Coming Soon