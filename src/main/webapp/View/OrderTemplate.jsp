
<html>
<head>
    <title>Group 12: Profile</title>
    <script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
</head>
<body>

<h3>Group 12: User Profile</h3>


<form id="createForm" action="/addOrder" method = "post">
    <table>
        <tr>
            <td>chef_id:</td> <td>    <input type="text" name="chef_id" ></td>
        </tr>

        <tr>
            <td>email:</td> <td>    <input type="text" name="email"  ></td>
        </tr>

        <tr>
            <td>start_time </td> <td> <input type="text" name="start_time" ></td>
        </tr>

        <tr>
            <td>end_time</td> <td><input type="text" name="end_time"></td>
        </tr>

        <tr>
            <td>pickup_time: </td>  <td><input type="text" name="pickup_time" ></td>
        </tr>

        <tr>
            <td>quantity</td>  <td><input type="text" name="quantity" ></td>
        </tr>

        <tr>
            <td>menu_id </td>  <td><input type="text" name="menu_id"></td>
        </tr>

        <tr><td><input type="submit" value="CREATE" /></td></tr>

    </table>

</form>
<script>


</script>
</body>
</html>