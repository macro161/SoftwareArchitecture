<html>
<head>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.js"></script>
    <style>
        table, th, td {
            border: 1px solid black;
        }
    </style>
</head>
<body>

    <form id="editform" onSubmit="SubmitEdit()" style="display:none">

        <label>ID:</label>
        <br>
        <input type="text" id="fid" name="fstockname" disabled>
        <br>

        <label>Stock name:</label>
        <br>
        <input type="text" id="fstockname" name="fstockname">
        <br>

        <label>Stock price:</label>
        <br>
        <input type="number" step="0.01" id="fstockprice" name="lname">
        <br>

        <label>Available amount:</label>
        <br>
        <input type="number" step="0.01" id="fstockamount" name="lname">
        <br>

        <label>Description</label>
        <br>
        <input type="text" id="fdescription" name="lname">
        <br>


        <br>

        <input type="submit" value="Submit">
    </form>

    <form id="createform" onSubmit="SubmitCreate()" style="display:none">

        <label>Stock name:</label>
        <br>
        <input type="text" id="cstockname" name="fstockname">
        <br>

        <label>Stock price:</label>
        <br>
        <input type="number" step="0.01" id="cstockprice" name="lname">
        <br>

        <label>Available amount:</label>
        <br>
        <input type="number" step="0.01" id="cstockamount" name="lname">
        <br>

        <label>Description</label>
        <br>
        <input type="text" id="cdescription" name="lname">
        <br>


        <br>

        <input type="submit" value="Submit">
    </form>

<br>
	<input type="button" id="getAllButton" value="GET STOCKS" />

    <input type="button" id="createStock" value="CREATE STOCK" onclick="create()"/>

    <p>User: </p><p id="username"></p>
    <br>
    <p>Money: </p><p id="usermoney"></p>


    <table id="stockstable" class="table">
        <tr>
            <th>ID</th>
            <th>Stock name</th>
            <th>Price</th>
            <th>Available amount</th>
            <th>Description</th>
            <th>DELETE</th>
            <th>EDIT</th>
            <th>BUY</th>
        </tr>
    </table>

</body>
</html>

<script>
    $(document).ready(function () {
        displayList()
        displayUser()
    });

    function Buy(id) {
        $.ajax({
            url : 'http://localhost:8080/buystock/' + id,
            type : 'PUT',
            dataType: 'json',
            data: id,
            contentType: 'application/json',
            success : function(response) {
                window.location.reload();
            },
            error: function (data, textStatus, xhr) {
                window.location.reload();
            }
        });
    }

    function displayUser() {
        $.ajax({
            url: 'http://localhost:8080/user',
            type: 'GET',
            success: function (response) {
                document.getElementById("username").innerHTML = response.name;
                document.getElementById("usermoney").innerHTML = response.money;
            },
            error: function (data, textStatus, xhr) {
                alert(data.responseText)
                pause(1000)
            }
        });
    }

    function create() {
        document.getElementById("createform").style.display = 'block';
    }

    function SubmitCreate() {
        document.getElementById("createform").style.display = 'none';

        var data = {
            stockName: document.getElementById("cstockname").value,
            price: Number(document.getElementById("cstockprice").value),
            availableAmount: Number(document.getElementById("cstockamount").value),
            description: document.getElementById("cdescription").value
        };

        $.ajax({
            url : 'http://localhost:8080/createstock',
            type : 'PUT',
            dataType: 'json',
            data: JSON.stringify(data),
            contentType: 'application/json',
            success : function(data) {
                $("tr:has(td)").remove();
                $.ajax({
                    url : 'http://localhost:8080/stocklisting',
                    type : 'GET',
                    success : function(response) {
                        displayList()
                    },
                    error : function(data, textStatus, xhr) {
                        alert(data.responseText)
                        pause(1000)
                    }
                });
            },
            error : function(data, textStatus, xhr) {
                alert(data.responseText)
                pause(1000)
            }
        });
    }

    function SubmitEdit() {
        document.getElementById("editform").style.display = 'none';

        var data = {
            id: Number(document.getElementById("fid").value),
            stockName: document.getElementById("fstockname").value,
            price: Number(document.getElementById("fstockprice").value),
            availableAmount: Number(document.getElementById("fstockamount").value),
            description: document.getElementById("fdescription").value
        };

        $.ajax({
            url : 'http://localhost:8080/editstock',
            type : 'POST',
            dataType: 'json',
            data: JSON.stringify(data),
            contentType: 'application/json',
            success : function(data) {
                $("tr:has(td)").remove();
                $.ajax({
                    url : 'http://localhost:8080/stocklisting',
                    type : 'GET',
                    success : function(response) {
                        displayList()
                    },
                    error : function(data, textStatus, xhr) {
                        alert(data.responseText)
                        pause(1000)
                    }
                });
            },
            error : function(data, textStatus, xhr) {
                alert(data.responseText)
                pause(1000)
            }
        });

    }

    function RemoveRow(ids) {
        document.getElementById("editform").style.display = 'none';

        $.ajax({
            url : 'http://localhost:8080/deletestock/' + ids,
            type : 'DELETE',
            dataType: 'json',
            contentType: 'application/html',
            mimeType: 'application/html',
            success : function(data) {
                displayList()
            },
            error : function(data, textStatus, xhr) {
                alert(data.responseText)
                pause(1000)
            }
        })
    }

    function Edit(id,name,price,amount,description) {
        document.getElementById("editform").style.display = 'block';
        document.getElementById('fid').defaultValue = id;
        document.getElementById('fstockname').defaultValue = name;
        document.getElementById('fstockprice').defaultValue = price;
        document.getElementById('fstockamount').defaultValue = amount;
        document.getElementById('fdescription').defaultValue = description;
    }

    function displayList() {
        $("tr:has(td)").remove();
        $.ajax({
            url : 'http://localhost:8080/stocklisting',
            type : 'GET',
            success : function(response) {
                $("tr:has(td)").remove();
                $.each(response, function(i, data) {
                    var $tr = $('<tr>').append(
                        $('<td>').text(data.id),
                        $('<td>').text(data.stockName),
                        $('<td>').text(data.price),
                        $('<td>').text(data.availableAmount),
                        $('<td>').text(data.description),
                        $('<td>').html("<input type='button' name='' value='Delete' onclick='RemoveRow("+data.id+");'>"),
                        $('<td>').html("<input type='button' name='' value='Edit' onclick='Edit("+data.id+',"'+ data.stockName+'","'+data.price+'","'+data.availableAmount+'","'+data.description+'"'+");'>"),
                        $('<td>').html("<input type='button' name='' value='Buy' onclick='Buy("+data.id+");'>")
                ).appendTo('#stockstable');
                });
            },
            error : function(data, textStatus, xhr) {
                alert(data.responseText)
                pause(1000)
            }
        });
    }

	$("#getAllButton").click(function() {
		displayList()
	});

</script>