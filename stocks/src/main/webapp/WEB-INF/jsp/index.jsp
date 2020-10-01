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
	<h2>Test JSON calls</h2>

	<br>
	<input type="button" id="getAllButton" value="GET ALL TEST" />
	<br>
	<input type="button" id="getByIDButton" value="GET TEST BY ID (1)" />
	<br>
	<input type="button" id="postButton" value="POST TEST" />
	<br>
	<input type="button" id="putButton" value="PUT TEST" />
	<br>
	<input type="button" id="deleteButton" value="DELETE TEST" />

	<table id="stockstable" class="table">
        <tr>
            <th>ID</th>
            <th>Stock name</th>
            <th>Price</th>
            <th>Available amount</th>
            <th>Description</th>
        </tr>
    </table>

</body>
</html>

<script>
    $("tr:has(td)").remove();
	$("#getAllButton").click(function() {
		$.ajax({
			url : 'http://localhost:8080/stocklisting',
			type : 'GET',
			success : function(response) {
                $.each(response, function(i, data) {
                        var $tr = $('<tr>').append(
                            $('<td>').text(data.id),
                            $('<td>').text(data.stockName),
                            $('<td>').text(data.price),
                            $('<td>').text(data.availableAmount),
                            $('<td>').text(data.description)
                        ).appendTo('#stockstable');
                    });
			},
			error : function(data) {
				alert('woops!');
			}
		});
	});
	$("#postButton").click(function() {
		var data = {};
		data['id']=15;
		data['countryName']='America';
		data['population']= 50000;
		$.ajax({
			url : 'http://localhost:8080/SpringRestfulWebServicesCRUDExample/countries',
			type : 'POST',
			dataType: 'json',
			data: JSON.stringify(data),
			contentType: 'application/json',
			mimeType: 'application/json',
			success : function(data) {
				alert(JSON.stringify(data));
			},
			error : function(data) {
				alert('woops!');
			}
		});
	});
	$("#putButton").click(function() {
		var data = {};
		data['id']=15;
		data['countryName']='America';
		data['population']= 90000;
		$.ajax({
			url : 'http://localhost:8080/SpringRestfulWebServicesCRUDExample/countries',
			type : 'PUT',
			dataType: 'json',
			data: JSON.stringify(data),
			contentType: 'application/json',
			mimeType: 'application/json',
			success : function(data) {
				alert(JSON.stringify(data));
			},
			error : function(data) {
				alert('woops!');
			}
		});
	});
	$("#deleteButton").click(function() {
		var data;
		$.ajax({
			url : 'http://localhost:8080/SpringRestfulWebServicesCRUDExample/country/15',
			type : 'DELETE',
			dataType: 'json',
			//data: JSON.stringify(data),
			contentType: 'application/html',
			mimeType: 'application/html',
			success : function(data) {
				alert(JSON.stringify(data));
				alert(data.status);
			},
			error : function(data) {
				alert(JSON.stringify(data));
				alert(data.status);
			}
		});
	});
</script>