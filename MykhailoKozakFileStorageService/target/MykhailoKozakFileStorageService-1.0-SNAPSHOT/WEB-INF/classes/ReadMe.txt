Run:
mvn tomcat:run-war
mvn -Dmaven.tomcat.port=8181 tomcat:run-war


URL for SOAP:
http://localhost:8080/MykhailoKozakFileStorageService/fileSOAP?wsdl


URL for REST for Postman:
getListAllFiles  | GET |     http://localhost:8080/MykhailoKozakFileStorageService/fileREST/files
writeFile        | POST |    http://localhost:8080/MykhailoKozakFileStorageService/fileREST/write?fileName=fddd
downloadFile     | POST |    http://localhost:8080/MykhailoKozakFileStorageService/fileREST/download?fileName=fddd&filePath=src/main/resources
deleteFile       | DEL |     http://localhost:8080/MykhailoKozakFileStorageService/fileREST/delete/fddd