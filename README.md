<p>Install Postgres database. Provide user with admin rights - postgres: admin123. It must contain schema "public"</p>
<p>I</p>
<p>for start application use: mvn clean spring-boot:run</p>
<p>Ithen use information below to test it:</p>
<p>I</p>
<p>IUsers:</p>
<p>Iadmin: admin123</p>
<p>Ipetya: 123456</p>
<p>Ivasya: 123456</p>
<p>Ikolya: 123456</p>
<p>I</p>
<p>Iexamples of url:</p>
<p>Iadd restorant: http://localhost:8080/admin/addrestorant?restorant=res1</p>
<p>Iremove restorant: http://localhost:8080/admin/removerestorant?restorant=res1</p>
<p>Iadd dish: http://localhost:8080/admin/adddish?restorant=res1&name=ryba&price=4.5</p>
<p>Iremove dish: http://localhost:8080/admin/removedish?restorant=res1&name=ryba</p>
<p>Ivote: http://localhost:8080/user/vote?restorant=res1</p>
<p>Istatus: http://localhost:8080/user/status</p>
<p>Ilogout: http://localhost:8080/logout</p>
