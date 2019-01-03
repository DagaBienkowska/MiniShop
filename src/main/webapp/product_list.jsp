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
    int itemCounter = (int) request.getSession().getAttribute("itemCounter");

%>
<h2>Witaj użytkowniku <%=userName%>!</h2><br>
<form action="Logout" method="post">
    <input type="submit" value="Logout">
</form>
<p id="itemCount" align="right">Items in ShoppingCart: <%=itemCounter%></p>
<p id="sumPrice" align="right">Price: </p>
<table width="100%">
    <tr>
        <th width="20%">Name</th>
        <th width="25%">Description</th>
        <th width="15%">Price</th>
        <th width="15%">In Stock</th>
    </tr>
    <%
        List<Product> productsList = (List<Product>) request.getSession().getAttribute("products");

        for (Product p : productsList){
    %>
    <tr>
        <td><%=p.getName()%></td>
        <td><%=p.getDescription()%></td>
        <td><%=p.getPrice()%></td>
        <td><%=p.getQuantity()%></td>
        <form method="post" action="cart">
        <td width="25%"><button name="add" type="submit">Add</button></td>
            <input type="hidden" name="addToCart" value="<%=p.getId()%>">

        </form>
    </tr>
    <% } %>
</table>

<p id="cart" align="right"><button name="showCart" value="cart">Show Cart</button></p>
</body>
</html>

