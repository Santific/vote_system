Install Postgres database. Provide user with admin rights - postgres: admin123. It must contain schema "public"

for start application use: mvn clean spring-boot:run
then use information below to test it:

Users:
admin: admin123
petya: 123456
vasya: 123456
kolya: 123456

examples of url:
add restorant: http://localhost:8080/admin/addrestorant?restorant=res1
remove restorant: http://localhost:8080/admin/removerestorant?restorant=res1
add dish: http://localhost:8080/admin/adddish?restorant=res1&name=ryba&price=4.5
remove dish: http://localhost:8080/admin/removedish?restorant=res1&name=ryba
vote: http://localhost:8080/user/vote?restorant=res1
status: http://localhost:8080/user/status
logout: http://localhost:8080/logout
