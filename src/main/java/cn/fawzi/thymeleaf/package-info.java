/**
 *
 *The boolean literals are true and false. For example:

 <div th:if="${user.isAdmin()} == false"> ...
 In this example, the == false is written outside the braces, and so it is Thymeleaf that takes care of it. If it were written inside the braces, it would be the responsibility of the OGNL/SpringEL engines:

 <div th:if="${user.isAdmin() == false}"> ...

 * @author wenqi
 */
package cn.fawzi.thymeleaf;