#Docker image'ini configure et
#sifir kurulumu olan bir linux makinaya, bu uygulamayi kurmak gibi dusun

#Java kurmak
#build = takma ad
FROM openjdk:17-slim as build

#Build dosyalarini kopyalamak
#COPY LOCAL_YOL CLOUD YOLU
COPY target/api-gateway-0.0.1-SNAPSHOT.jar api-gateway-0.0.1-SNAPSHOT.jar

#Build dosyalarini ayaga kaldirmak
#java -jar orderservice-0.0.1-SNAPSHOT.jar
#Bosluk tusu ozel karakter oldugu icin entrypoint aralarina bosluk koyarak otomatik yapiyor.
ENTRYPOINT ["java", "-jar", "api-gateway-0.0.1-SNAPSHOT.jar"]

#dockerfile -> image -> container(s)

