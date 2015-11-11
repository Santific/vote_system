<#import "spring.ftl" as spring />

<form action="<@spring.url '/logout'/>" method="post">
  <input type="submit" value="Log out" />
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
