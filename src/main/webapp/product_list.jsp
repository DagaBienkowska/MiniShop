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
<p id="itemCount" align="right">Items in ShoppingCart: </p>
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
        <td width="25%"><button>Add</button></td>
    </tr>
    <% } %>
</table>
<p id="cart" align="right"><button>Show Cart</button></p>
</body>
</html>

