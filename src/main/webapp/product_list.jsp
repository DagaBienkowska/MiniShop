<%@ page import="com.dagabienkowska.shop.JsonPOJO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.dagabienkowska.shop.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Frog's Lil Shop</title>
    <meta charset="UTF-8">
</head>
<body>
<h3>Product List</h3>
<%
    String userName = null;
    Cookie[] cookies = request.getCookies();

    if (cookies != null){
        for (Cookie cookie : cookies){
            if (cookie.getName().equals("userInCookie")){
                userName = cookie.getValue();
            }
        }
    }

    if (userName == null){
        response.sendRedirect("index.jsp");
    }

%>
<h2>Witaj użytkowniku <%=userName%>!</h2><br>
<form action="Logout" method="post">
    <input type="submit" value="Logout">
</form>
<p id="itemCount" align="right">Items in ShoppingCart:</p>
<p id="sumPrice" align="right">Price: </p>
<table>
    <tr>
        <th width="20">ID</th>
        <th width="200">Name</th>
        <th width="300">Description</th>
        <th width="100">Price</th>
        <th width="100">Quantity</th>
        <th width="50"></th>
    </tr>
    <%
        List<Product> products = (List<Product>) request.getAttribute("productList");

        for (Product p : products){

    %>
    <form action="AddProduct" method="post">
        <tr>
            <td width="20"><input readonly value="<%=p.getId()%>" name="product" type="hidden"><%=p.getId()%></td>
            <td width="200"><%=p.getName()%></td>
            <td width="300"><%=p.getDescription()%></td>
            <td width="100"><%=p.getPrice()%></td>
            <td width="100"><%=p.getQuantity()%></td>
            <%
                if (p.getQuantity() > 0) {
            %>
            <td width="50"><input type="submit" value="Add" /></td>
            <%
            } else {
            %>
            <td width="50"></td>
            <%
                }
            %>

        </tr>
    </form>
    <%
        }
    %>
</table>

<p id="cart" align="right"><button name="showCart" value="cart">Show Cart</button></p>
</body>
</html>

